package com.stock.stock_simulator.infra;

import com.google.gson.*;
import com.stock.stock_simulator.entity.Stock;
import com.stock.stock_simulator.entity.Token;
import com.stock.stock_simulator.interfaces.TokenRepository;
import com.stock.stock_simulator.interfaces.StockApiInterface;
import com.stock.stock_simulator.interfaces.StockRepository;
import com.stock.stock_simulator.service.TokenService;
import org.json.JSONObject;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.beans.factory.annotation.Value;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Component
public class StockApiImpl implements StockApiInterface {
    private final TokenRepository tokenRepository;
    private String AppKey;
    private String SecretKey;
    public TokenService tokenService;
    private final StockRepository stockRepository;

    public StockApiImpl(
            @Value("${kinvest.appkey}") String appKey,
            @Value("${kinvest.secretkey}") String secretKey,
            TokenRepository tokenRepository,
            TokenService tokenService, StockRepository stockRepository
    ) {
        this.tokenService = tokenService;
        AppKey = appKey;
        SecretKey = secretKey;
        this.tokenRepository = tokenRepository;
        this.stockRepository = stockRepository;
    }

    private String postApiRequest(String uri, Object requestBody, Map<String, String> headersMap){
        WebClient webClient = WebClient.builder()
                .baseUrl("https://openapi.koreainvestment.com:9443")
                .build();
        String response = webClient.post()
                .uri(uri)
                .headers(headers -> {
                    headersMap.forEach(headers::set); // 동적으로 헤더 추가
                })
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(String.class)
                .block(); // block()은 동기 방식으로 결과를 기다림
        return response;
    }
    private String getApiRequest(String uri, Map<String, String> headersMap, Map<String, Object> queryParams){
        WebClient webClient = WebClient.builder()
                .baseUrl("https://openapi.koreainvestment.com:9443")
                .build();
        String response = webClient.get()
                .uri(uriBuilder -> {
                    uriBuilder.path(uri);
                    queryParams.forEach(uriBuilder::queryParam); // 쿼리 파라미터 추가
                    return uriBuilder.build();
                })
                .headers(headers -> {
                    headersMap.forEach(headers::set); // 동적으로 헤더 추가
                })
                .retrieve()
                .bodyToMono(String.class)
                .block(); // block()은 동기 방식으로 결과를 기다림
        return response;
    }


    @Override
    public String getWebSocketKey() {
        Map<String, String> headersMap = new HashMap<>();
        headersMap.put("Content-Type", "application/json");

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("grant_type", "");
        requestBody.put("appkey", AppKey);
        requestBody.put("secretkey", SecretKey);

        String response = postApiRequest("/oauth2/Approval", requestBody, headersMap);

        JSONObject obj = new JSONObject(response);
        String socketKey = obj.getString("approval_key");

        return socketKey;
    }

    @Override
    public String getAccessKey() {
        Token token = tokenService.getAccessToken();

        if(token!=null && !TokenService.isTokenExpired(token.getExpires())){
            return token.getToken();
        }else{

            //요청 헤더
            Map<String, String> headersMap = new HashMap<>();
            headersMap.put("Content-Type", "application/json");

            //요청 바디
            Map<String, Object> requestBody = new HashMap<>();
            requestBody.put("grant_type", "client_credentials");
            requestBody.put("appkey", AppKey);
            requestBody.put("appsecret", SecretKey);

            String response = postApiRequest("/oauth2/tokenP", requestBody, headersMap);

            //파싱 및 db에 저장
            JSONObject obj = new JSONObject(response);
            String accessToken = obj.getString("access_token");
            String expires = obj.getString("access_token_token_expired");

            Token newToken = new Token();
            newToken.setToken(accessToken);
            newToken.setExpires(expires);
            newToken.setType("access_token");

            tokenRepository.save(newToken);
            
            return accessToken;
        }
    }

    @Override
    public String getKoreaStockPrice(String FID_COND_MRKT_DIV_CODE, String FID_INPUT_ISCD) {
        String accessKey = getAccessKey();
//        System.out.println(accessKey);

        Map<String, String> headersMap = new HashMap<>();
        headersMap.put("Content-Type", "application/json; charset=utf-8");
        headersMap.put("authorization", "Bearer " + accessKey); // 실제 토큰 값 사용
        headersMap.put("appkey", AppKey); // 실제 AppKey 값 사용
        headersMap.put("appsecret", SecretKey); // 실제 SecretKey 값 사용
        headersMap.put("tr_id", "FHKST01010100"); // 예시 거래ID, 실제 ID로 변경

        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("FID_COND_MRKT_DIV_CODE", FID_COND_MRKT_DIV_CODE); // 예시 값, 실제 데이터로 변경
        queryParams.put("FID_INPUT_ISCD", FID_INPUT_ISCD);   // 예시 값, 실제 데이터로 변경

        String url = "/uapi/domestic-stock/v1/quotations/inquire-price";

        String response = getApiRequest(url, headersMap, queryParams);

        return response;
    }


    public String getNasdaqStockPrice(String SYMB, String country) {
        String accessKey = getAccessKey();

        Map<String, String> headersMap = new HashMap<>();
        headersMap.put("Content-Type", "application/json; charset=utf-8");
        headersMap.put("authorization", "Bearer " + accessKey); // 실제 토큰 값 사용
        headersMap.put("appkey", AppKey); // 실제 AppKey 값 사용
        headersMap.put("appsecret", SecretKey); // 실제 SecretKey 값 사용
        headersMap.put("tr_id", "HHDFS00000300"); // 예시 거래ID, 실제 ID로 변경

        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("AUTH", ""); // 예시 값, 실제 데이터로 변경
        queryParams.put("EXCD", country); // 예시 값, 실제 데이터로 변경
        queryParams.put("SYMB", SYMB);   // 예시 값, 실제 데이터로 변경

        String url = "/uapi/overseas-price/v1/quotations/price";

        String response = getApiRequest(url, headersMap, queryParams);

        return response;
    }

    public String getNasdaqStockPrice2(String SYMB, String country) {
        String accessKey = getAccessKey();

        Map<String, String> headersMap = new HashMap<>();
        headersMap.put("Content-Type", "application/json; charset=utf-8");
        headersMap.put("authorization", "Bearer " + accessKey); // 실제 토큰 값 사용
        headersMap.put("appkey", AppKey); // 실제 AppKey 값 사용
        headersMap.put("appsecret", SecretKey); // 실제 SecretKey 값 사용
        headersMap.put("tr_id", "HHDFS76240000");

        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("AUTH", "");
        queryParams.put("EXCD", country);
        queryParams.put("SYMB", SYMB);
        queryParams.put("GUBN", "0");
        queryParams.put("BYMD", "");
        queryParams.put("MODP", "0");

        String url = "/uapi/overseas-price/v1/quotations/dailyprice";

        String response = getApiRequest(url, headersMap, queryParams);

        return response;
    }

    @Override
    public String getKoreaTickData(String FID_COND_MRKT_DIV_CODE, String FID_INPUT_ISCD, String FID_INPUT_HOUR_1, String FID_PW_DATA_INCU_YN) {
        return "";
    }

    @Override
    public String getNasdaqTickData(String EXCD, String SYMB, String NMIN, String PINC, String NEXT, String NREC, String FILL, String KEYB) {
        return "";
    }

    @Override
    public String getCurrentStockPrice(String symbol) throws Exception {
        Stock stockData = stockRepository.findBySymbol(symbol)
                .orElseThrow(() -> new Exception("Invalid symbol: " + symbol));

        String stockCountry = stockData.getCountry();

        if(Objects.equals(stockCountry, "NAS")
            ||Objects.equals(stockCountry, "NYS")
            ||Objects.equals(stockCountry, "AMS")
        ){
            JsonObject transformedJson = new JsonObject();

            String res1 = getNasdaqStockPrice(symbol, stockCountry);
            String res2 = getNasdaqStockPrice2(symbol, stockCountry);

            //customizing
            JsonObject rootObject1 = JsonParser.parseString(res1).getAsJsonObject();
            JsonObject outputObject1 = rootObject1.getAsJsonObject("output");

            JsonObject rootObject2 = JsonParser.parseString(res2).getAsJsonObject();
            JsonArray outputObject2 = rootObject2.getAsJsonArray("output2");

            transformedJson.addProperty("name", stockData.getName());
            transformedJson.addProperty("symbol", stockData.getSymbol());
            transformedJson.addProperty("country", stockData.getCountry());
            transformedJson.addProperty("type", stockData.getType());
            transformedJson.addProperty("price", outputObject1.get("last").getAsString());
            transformedJson.addProperty("high", outputObject2.get(0).getAsJsonObject().get("high").getAsString());
            transformedJson.addProperty("low", outputObject2.get(0).getAsJsonObject().get("low").getAsString());

            return transformedJson.toString();

        }else if(Objects.equals(stockData.getCountry(), "KSD") || Objects.equals(stockData.getCountry(), "KSP")){
            JsonObject transformedJson = new JsonObject();

            String res1 = getKoreaStockPrice("J", symbol);

            JsonObject rootObject1 = JsonParser.parseString(res1).getAsJsonObject();
            JsonObject outputObject1 = rootObject1.getAsJsonObject("output");

            transformedJson.addProperty("name", stockData.getName());
            transformedJson.addProperty("symbol", stockData.getSymbol());
            transformedJson.addProperty("country", stockData.getCountry());
            transformedJson.addProperty("type", stockData.getType());
            transformedJson.addProperty("price", outputObject1.get("stck_prpr").getAsString());
            transformedJson.addProperty("high", outputObject1.get("stck_hgpr").getAsString());
            transformedJson.addProperty("low", outputObject1.get("stck_lwpr").getAsString());

            return transformedJson.toString();
        }
        throw new Exception("Invalid stock data");
    }

    public String getKorChartData(String SYMB) {
        String accessKey = getAccessKey();

        LocalDateTime now = LocalDateTime.now();
        // YYYYMMDD 형식
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        String date = now.format(dateFormatter);

        // HHMMSS 형식
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HHmm");
        String time = now.format(timeFormatter) + "00";

        Map<String, String> headersMap = new HashMap<>();
        headersMap.put("Content-Type", "application/json; charset=utf-8");
        headersMap.put("authorization", "Bearer " + accessKey); // 실제 토큰 값 사용
        headersMap.put("appkey", AppKey); // 실제 AppKey 값 사용
        headersMap.put("appsecret", SecretKey); // 실제 SecretKey 값 사용
        headersMap.put("tr_id", "FHKST03010230");
        headersMap.put("custyype", "P");

        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("FID_COND_MRKT_DIV_CODE", "J");
        queryParams.put("FID_INPUT_ISCD", SYMB);
        queryParams.put("FID_INPUT_DATE_1", date);
        queryParams.put("FID_INPUT_HOUR_1", time);
        queryParams.put("FID_PW_DATA_INCU_YN", "Y");
        queryParams.put("FID_FAKE_TICK_INCU_YN", "N");

        String url = "/uapi/domestic-stock/v1/quotations/inquire-time-dailychartprice";

        String response = getApiRequest(url, headersMap, queryParams);

        return response;
    }

    public String getNasChartData(String SYMB) throws Exception {
        Optional<Stock> bySymbol = stockRepository.findBySymbol(SYMB);
        if(!bySymbol.isPresent()){throw new Exception("no stock");}

        Stock stock = bySymbol.get();

        String accessKey = getAccessKey();

        Map<String, String> headersMap = new HashMap<>();
        headersMap.put("Content-Type", "application/json; charset=utf-8");
        headersMap.put("authorization", "Bearer " + accessKey); // 실제 토큰 값 사용
        headersMap.put("appkey", AppKey); // 실제 AppKey 값 사용
        headersMap.put("appsecret", SecretKey); // 실제 SecretKey 값 사용
        headersMap.put("tr_id", "HHDFS76950200");

        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("AUTH", "");
        queryParams.put("EXCD", stock.getCountry());
        queryParams.put("SYMB", SYMB);
        queryParams.put("NMIN", "1");
        queryParams.put("PINC", "1");
        queryParams.put("NEXT", "");
        queryParams.put("NREC", "120");
        queryParams.put("FILL", "");
        queryParams.put("KEYB", "");

        String url = "/uapi/overseas-price/v1/quotations/inquire-time-itemchartprice";

        String response = getApiRequest(url, headersMap, queryParams);

        return response;
    }

    @Override
    public String getChartData(String symbol) throws Exception {
        if(symbol.length() == 6){
            //국내 주식
            String res = getKorChartData(symbol);

            JsonObject rootObject = JsonParser.parseString(res).getAsJsonObject();
            JsonArray outputArray = rootObject.getAsJsonArray("output2");

            JsonArray newArray = new JsonArray();

            for (JsonElement element : outputArray) {
                JsonObject obj = element.getAsJsonObject();

                JsonObject newObj = new JsonObject();
                newObj.addProperty("date", obj.get("stck_bsop_date").getAsString() + obj.get("stck_cntg_hour").getAsString());
                newObj.addProperty("open", obj.get("stck_oprc").getAsString());
                newObj.addProperty("close", obj.get("stck_prpr").getAsString());
                newObj.addProperty("high", obj.get("stck_hgpr").getAsString());
                newObj.addProperty("low", obj.get("stck_lwpr").getAsString());
                newObj.addProperty("volume", obj.get("cntg_vol").getAsString());

                newArray.add(newObj);
            }

            return newArray.toString();
        }else{
            //해외 주식
            String res = getNasChartData(symbol);

            JsonObject rootObject = JsonParser.parseString(res).getAsJsonObject();
            JsonArray outputArray = rootObject.getAsJsonArray("output2");

            JsonArray newArray = new JsonArray();

            for (JsonElement element : outputArray) {
                JsonObject obj = element.getAsJsonObject();

                JsonObject newObj = new JsonObject();
                newObj.addProperty("date", obj.get("kymd").getAsString() + obj.get("khms").getAsString());
                newObj.addProperty("open", obj.get("open").getAsString());
                newObj.addProperty("close", obj.get("last").getAsString());
                newObj.addProperty("high", obj.get("high").getAsString());
                newObj.addProperty("low", obj.get("low").getAsString());
                newObj.addProperty("volume", obj.get("evol").getAsString());

                newArray.add(newObj);
            }

            return newArray.toString();
        }
    }

    @Override
    public String getMainList() {
        int count = 0;  // 항목 개수를 제한하기 위한 카운터
        int maxCount = 5;

        String korTopVolResponse = this.getKorTopVol();
        String nasTopVolResponse = this.getNasTopVol();

        JsonObject korTopObj = JsonParser.parseString(korTopVolResponse).getAsJsonObject();
        JsonArray korTopArr = korTopObj.getAsJsonArray("output");
        List<Map<String, Object>> newKorList = new ArrayList<>();

        // 배열 순회
        for (JsonElement element : korTopArr) {
            if (count >= maxCount) break;  // 5개를 초과하면 종료
            JsonObject obj = element.getAsJsonObject();

            Map<String, Object> stockInfo = new HashMap<>();
            stockInfo.put("symb", obj.get("mksc_shrn_iscd").getAsString());
            stockInfo.put("name", obj.get("hts_kor_isnm").getAsString());
            stockInfo.put("last", obj.get("stck_prpr").getAsString());
            stockInfo.put("rate", obj.get("prdy_ctrt").getAsString());

            // DTO 객체 생성 후 리스트에 추가
            newKorList.add(stockInfo);
            count++;
        }

        count = 0;
        JsonObject nasTopObj = JsonParser.parseString(nasTopVolResponse).getAsJsonObject();
        JsonArray nasTopArr = nasTopObj.getAsJsonArray("output2");
        List<Map<String, Object>> newNasList = new ArrayList<>();

        // 배열 순회
        for (JsonElement element : nasTopArr) {
            if (count >= maxCount) break;  // 5개를 초과하면 종료
            JsonObject obj = element.getAsJsonObject();

            Map<String, Object> stockInfo = new HashMap<>();
            stockInfo.put("symb", obj.get("symb").getAsString());
            stockInfo.put("name", obj.get("name").getAsString());
            stockInfo.put("last", obj.get("last").getAsString());
            stockInfo.put("rate", obj.get("rate").getAsString());

            // DTO 객체 생성 후 리스트에 추가
            newNasList.add(stockInfo);
            count++;
        }

        Gson gson = new Gson();
        JsonObject newJsonObject = new JsonObject();
        newJsonObject.add("nas", gson.toJsonTree(newNasList));
        newJsonObject.add("kor", gson.toJsonTree(newKorList));

        return newJsonObject.toString();
    }

    private String getNasTopVol(){
        String accessKey = getAccessKey();

        Map<String, String> headersMap = new HashMap<>();
        headersMap.put("Content-Type", "application/json; charset=utf-8");
        headersMap.put("authorization", "Bearer " + accessKey); // 실제 토큰 값 사용
        headersMap.put("appkey", AppKey); // 실제 AppKey 값 사용
        headersMap.put("appsecret", SecretKey); // 실제 SecretKey 값 사용
        headersMap.put("tr_id", "HHDFS76310010");
        headersMap.put("custtype", "P");

        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("AUTH", "");
        queryParams.put("EXCD", "NAS");
        queryParams.put("NDAY", "3");
        queryParams.put("PRC1", "10");
        queryParams.put("PRC2", "10000000");
        queryParams.put("VOL_RANG", "0");
        queryParams.put("KEYB", "");

        String url = "/uapi/overseas-stock/v1/ranking/trade-vol";

        String response = getApiRequest(url, headersMap, queryParams);

        return response;
    }

    private String getKorTopVol(){
        String accessKey = getAccessKey();

        Map<String, String> headersMap = new HashMap<>();
        headersMap.put("Content-Type", "application/json; charset=utf-8");
        headersMap.put("authorization", "Bearer " + accessKey); // 실제 토큰 값 사용
        headersMap.put("appkey", AppKey); // 실제 AppKey 값 사용
        headersMap.put("appsecret", SecretKey); // 실제 SecretKey 값 사용
        headersMap.put("tr_id", "FHPST01710000");

        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("FID_COND_MRKT_DIV_CODE", "J");
        queryParams.put("FID_COND_SCR_DIV_CODE", "20171");
        queryParams.put("FID_INPUT_ISCD", "0000");
        queryParams.put("FID_DIV_CLS_CODE", "0");
        queryParams.put("FID_BLNG_CLS_CODE", "0");
        queryParams.put("FID_TRGT_CLS_CODE", "111111111");
        queryParams.put("FID_TRGT_EXLS_CLS_CODE", "1111111111");
        queryParams.put("FID_INPUT_PRICE_1", "0");
        queryParams.put("FID_INPUT_PRICE_2", "0");
        queryParams.put("FID_VOL_CNT", "0");
        queryParams.put("FID_INPUT_DATE_1", "0");

        String url = "/uapi/domestic-stock/v1/quotations/volume-rank";

        String response = getApiRequest(url, headersMap, queryParams);

        return response;
    }
}
