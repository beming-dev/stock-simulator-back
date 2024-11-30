package com.stock.stock_simulator.infra;

import com.stock.stock_simulator.interfaces.StockApiInterface;
import org.json.JSONObject;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.beans.factory.annotation.Value;

import java.util.HashMap;
import java.util.Map;

@Component
public class StockApiImpl implements StockApiInterface {
    private String AppKey;
    private String SecretKey;

    public StockApiImpl(
            @Value("${kinvest.appkey}") String appKey,
            @Value("${kinvest.secretkey}") String secretKey
    ) {
        AppKey = appKey;
        SecretKey = secretKey;
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
        Map<String, String> headersMap = new HashMap<>();
        headersMap.put("Content-Type", "application/json");

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("grant_type", "client_credentials");
        requestBody.put("appkey", AppKey);
        requestBody.put("appsecret", SecretKey);

        String response = postApiRequest("/oauth2/tokenP", requestBody, headersMap);

        JSONObject obj = new JSONObject(response);
        String socketKey = obj.getString("access_token");

        return socketKey;
    }

    @Override
    public String getKoreaStockPrice(String FID_COND_MRKT_DIV_CODE, String FID_INPUT_ISCD) {
        Map<String, String> headersMap = new HashMap<>();
        headersMap.put("Content-Type", "application/json; charset=utf-8");
//        headersMap.put("authorization", "Bearer your_access_token_here"); // 실제 토큰 값 사용
        headersMap.put("appkey", AppKey); // 실제 AppKey 값 사용
        headersMap.put("appsecret", SecretKey); // 실제 SecretKey 값 사용
        headersMap.put("tr_id", "FHKST01010100"); // 예시 거래ID, 실제 ID로 변경

        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("FID_COND_MRKT_DIV_CODE", FID_COND_MRKT_DIV_CODE); // 예시 값, 실제 데이터로 변경
        queryParams.put("FID_INPUT_ISCD", FID_INPUT_ISCD);   // 예시 값, 실제 데이터로 변경

        String url = "/uapi/domestic-stock/v1/quotations/inquire-price";

        String response = getApiRequest(url, headersMap, queryParams);

        return "";
    }

    @Override
    public String getNasdaqStockPrice(String SYMB) {
        String accessKey = getAccessKey();

        Map<String, String> headersMap = new HashMap<>();
        headersMap.put("Content-Type", "application/json; charset=utf-8");
        headersMap.put("authorization", "Bearer " + accessKey); // 실제 토큰 값 사용
        headersMap.put("appkey", AppKey); // 실제 AppKey 값 사용
        headersMap.put("appsecret", SecretKey); // 실제 SecretKey 값 사용
        headersMap.put("tr_id", "HHDFS00000300"); // 예시 거래ID, 실제 ID로 변경

        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("AUTH", ""); // 예시 값, 실제 데이터로 변경
        queryParams.put("EXCD", "NAS"); // 예시 값, 실제 데이터로 변경
        queryParams.put("SYMB", SYMB);   // 예시 값, 실제 데이터로 변경

        String url = "/uapi/overseas-price/v1/quotations/price";

        String response = getApiRequest(url, headersMap, queryParams);

        System.out.println(response);

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
}
