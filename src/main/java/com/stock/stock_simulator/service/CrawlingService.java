package com.stock.stock_simulator.service;

import com.opencsv.CSVReader;
import com.stock.stock_simulator.entity.Stock;
import com.stock.stock_simulator.interfaces.StockRepository;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

@Service
public class CrawlingService {
    private final WebClient webClient;
    private final StockRepository stockRepository;


    public CrawlingService(WebClient.Builder webClientBuilder, StockRepository stockRepository) {
        this.webClient = webClientBuilder.baseUrl("https://www.hankyung.com").build();
        this.stockRepository = stockRepository;
    }

    //    https://www.hankyung.com/globalmarket/data/get/price?order=marketcap&type=nasdaq&page=1
    public List<JSONObject> fetchData(String type, Integer page) {
        System.out.println(type + page);
        try{
            String endpoint = "/globalmarket/data/get/price?order=marketcap&type=" + type + "&page=" + page;

            // WebClient로 데이터 가져오기
            String response = webClient.get()
                    .uri(endpoint)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();

            // JSON 데이터 파싱
            JSONObject jsonObject = new JSONObject(response);
            JSONArray dataArray = jsonObject.getJSONArray("list");

            List<JSONObject> results = new ArrayList<>();
            for (int i = 0; i < dataArray.length(); i++) {
                JSONObject item = dataArray.getJSONObject(i);
                results.add(item); // 예: 'name' 필드 가져오기
            }

            return results;
        }catch (Exception e){
            e.printStackTrace();
            System.out.println(e.getMessage());
            return null;
        }
    }

    public void fetchNasdaqSymbolData(){
        Integer pageSize = 67;
        for (int i = 1; i <= pageSize; i++) {
            List<JSONObject> dataList = fetchData("nasdaq", i);

            pushAmericaStockData(dataList, "NAS");
        }
    }

    public void fetchNewYorkSymbolData(){
        Integer pageSize = 40;
        for (int i = 1; i <= pageSize; i++) {
            List<JSONObject> dataList = fetchData("nyse", i);

            pushAmericaStockData(dataList, "NYS");

        }
    }

    public void fetchAmexSymbolData(){
        Integer pageSize = 5;
        for (int i = 1; i <= pageSize; i++) {
            List<JSONObject> dataList = fetchData("amex", i);

            pushAmericaStockData(dataList, "AMS");
        }
    }

    private void pushAmericaStockData(List<JSONObject> dataList, String country){
        dataList.forEach(data -> {
            String symbol = data.getString("symbol");
            String name = data.getString("hname");
            String sector = data.getString("sector_nm");
            String industry = data.getString("industry_nm");

            Stock stock = stockRepository.findBySymbol(symbol)
                    .orElse(new Stock());
            stock.setSymbol(symbol);
            stock.setName(name);
            stock.setCountry("AMS");
            stock.setSector(sector);
            stock.setIndustry(industry);

            stockRepository.save(stock);
        });
    }

    private void downloadCsv(String downloadUrl, String otpCode, Path outputPath) throws IOException {
        byte[] csvData = webClient.post()
                .uri(downloadUrl)
                .header("Content-Type", "application/x-www-form-urlencoded") // Form Data의 Content-Type 명시
                .bodyValue(getFormData(otpCode)) // Form Data 전달
                .retrieve()
                .bodyToMono(byte[].class)
                .block();

        // CSV 파일 저장
        try (FileOutputStream fos = new FileOutputStream(outputPath.toFile())) {
            fos.write(csvData);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String requestOtp(String otpUrl, String mktId) {
        // Form 데이터 구성
        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add("locale", "ko_KR");
        formData.add("mktId", mktId);
        formData.add("trdDd", "20241129");
        formData.add("money", "1");
        formData.add("csvxls_isNo", "false");
        formData.add("name", "fileDown");
        formData.add("url", "dbms/MDC/STAT/standard/MDCSTAT03901");

        try {
            // POST 요청으로 OTP 받기
            return webClient.post()
                    .uri(otpUrl)
                    .header("Referer", "http://data.krx.co.kr/contents/MDC/MDI/mdiLoader") // Referer 헤더 추가
                    .bodyValue(formData) // Form 데이터를 POST 요청의 body로 추가
                    .retrieve()
                    .bodyToMono(String.class)
                    .block(); // 동기 처리
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("OTP 요청 중 오류 발생: " + e.getMessage(), e);
        }
    }

    private void processCsvFile(String filePath){
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), "EUC-KR"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(","); // 콤마 기준으로 나눔

                String symbol = values[0].replace("\"", "").trim();
                String name = values[1].replace("\"", "").trim();
                String country = values[2].replace("\"", "").trim();
                String sector = values[3].replace("\"", "").trim();

                Stock newStock = stockRepository.findBySymbol(symbol)
                                .orElse(new Stock());
                newStock.setSymbol(symbol);
                newStock.setName(name);
                if(country.equals("KOSDAQ")){
                    newStock.setCountry("KSD");
                }else if(country.equals("KOSPI")){
                    newStock.setCountry("KSP");
                }else{
                    newStock.setCountry("UNKNOWN");
                }
                newStock.setSector(sector);

                stockRepository.save(newStock);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private MultiValueMap<String, String> getFormData(String otpCode) {
        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add("code", otpCode); // Form Data 키-값 추가
        return formData;
    }

    public void downloadKospiCsvWithOtp() {
        Path outputPath = Path.of("E:\\develop\\githubProject\\stock-simulator-backend\\stock-simulator\\kospi.csv");
        String otpUrl = "http://data.krx.co.kr/comm/fileDn/GenerateOTP/generate.cmd";
        String downloadUrl = "http://data.krx.co.kr/comm/fileDn/download_csv/download.cmd";
        try {
            if (Files.exists(outputPath)) {
                Files.delete(outputPath);
                System.out.println("기존 파일 삭제 완료: " + outputPath);
            }

            // Step 1: OTP 요청
            String otpCode = requestOtp(otpUrl, "STK");
            // Step 2: CSV 다운로드
            downloadCsv(downloadUrl, otpCode, outputPath);

            System.out.println("CSV 파일이 성공적으로 다운로드되었습니다: " + outputPath);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("OTP 요청 또는 CSV 다운로드 중 오류 발생: " + e.getMessage());
        }

        processCsvFile(outputPath.toString());
    }

    public void downloadKosdaqCsvWithOtp() {
        Path outputPath = Path.of("E:\\develop\\githubProject\\stock-simulator-backend\\stock-simulator\\kosdaq.csv");
        String otpUrl = "http://data.krx.co.kr/comm/fileDn/GenerateOTP/generate.cmd";
        String downloadUrl = "http://data.krx.co.kr/comm/fileDn/download_csv/download.cmd";
        try {
            if (Files.exists(outputPath)) {
                Files.delete(outputPath);
                System.out.println("기존 파일 삭제 완료: " + outputPath);
            }

            // Step 1: OTP 요청
            String otpCode = requestOtp(otpUrl, "KSQ");
            // Step 2: CSV 다운로드
            downloadCsv(downloadUrl, otpCode, outputPath);

            System.out.println("CSV 파일이 성공적으로 다운로드되었습니다: " + outputPath);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("OTP 요청 또는 CSV 다운로드 중 오류 발생: " + e.getMessage());
        }

        processCsvFile(outputPath.toString());
    }
}
