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

    private String apiRequest(String uri, Object requestBody, Map<String, String> headersMap){
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

    @Override
    public String getWebSocketKey() {
        Map<String, String> headersMap = new HashMap<>();
        headersMap.put("Content-Type", "application/json");

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("grant_type", "");
        requestBody.put("appkey", AppKey);
        requestBody.put("secretkey", SecretKey);

        String response = apiRequest("/oauth2/Approval", requestBody, headersMap);

        JSONObject obj = new JSONObject(response);
        String socketKey = obj.getString("approval_key");

        return socketKey;
    }

    @Override
    public String getKoreaStockPrice(String FID_COND_MRKT_DIV_CODE, String FID_INPUT_ISCD) {
        return "";
    }

    @Override
    public String getNasdaqStockPrice(String SYMB) {
        return "";
    }

    @Override
    public String getKoreaRealtimeStock(String tr_key) {
        return "";
    }

    @Override
    public String getNasdaqRealtimeStock(String tr_key) {
        return "";
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
