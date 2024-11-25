package com.stock.stock_simulator;

import com.microsoft.playwright.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ExcelDownloader {
    private static final String DOWNLOAD_URL = "https://example.com/path/to/download-page"; // 다운로드 페이지 URL
    private static final String DOWNLOAD_BUTTON_SELECTOR = "button#download-excel"; // 다운로드 버튼의 선택자
    private static final String DOWNLOAD_DIR = "./downloads"; // 다운로드 디렉토리
    private static final String JSON_OUTPUT_PATH = "./data.json"; // 저장할 JSON 파일 경로

    public static void main(String[] args) {
        // Playwright 초기화
        try (Playwright playwright = Playwright.create()) {
            BrowserType chromium = playwright.chromium();
            Browser browser = chromium.launch(new BrowserType.LaunchOptions().setHeadless(true));
            BrowserContext context = browser.newContext(new Browser.NewContextOptions().setAcceptDownloads(true));
            Page page = context.newPage();

            // 다운로드 디렉토리 생성
            File downloadDir = new File(DOWNLOAD_DIR);
            if (!downloadDir.exists()) {
                downloadDir.mkdirs();
            }

            // 페이지 이동
            page.navigate(DOWNLOAD_URL);

            // 다운로드 버튼 클릭
            Download download = page.waitForDownload(() -> {
                page.click(DOWNLOAD_BUTTON_SELECTOR);
            });

            // 다운로드된 파일 저장 경로 설정
            Path downloadedFilePath = Paths.get(DOWNLOAD_DIR, download.suggestedFilename());
            download.saveAs(downloadedFilePath);

            System.out.println("파일 다운로드 완료: " + downloadedFilePath);

            // 엑셀 파일을 JSON으로 변환
            JSONArray jsonArray = convertExcelToJson(downloadedFilePath.toString());

            // JSON 파일 저장
            try (FileWriter file = new FileWriter(JSON_OUTPUT_PATH)) {
                file.write(jsonArray.toString(4)); // 예쁘게 출력
                System.out.println("JSON 파일 저장 완료: " + JSON_OUTPUT_PATH);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 엑셀 파일을 JSON으로 변환
    public static JSONArray convertExcelToJson(String excelFilePath) {
        JSONArray jsonArray = new JSONArray();

        try (FileInputStream fis = new FileInputStream(new File(excelFilePath));
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheetAt(0); // 첫 번째 시트 읽기
            Row headerRow = sheet.getRow(0); // 헤더 행 가져오기

            for (int i = 1; i <= sheet.getLastRowNum(); i++) { // 데이터 행 반복
                Row dataRow = sheet.getRow(i);
                JSONObject jsonObject = new JSONObject();

                for (int j = 0; j < headerRow.getLastCellNum(); j++) { // 열 반복
                    Cell headerCell = headerRow.getCell(j);
                    Cell dataCell = dataRow.getCell(j);

                    String header = headerCell.getStringCellValue();
                    String value = dataCell != null ? dataCell.toString() : ""; // null 처리
                    jsonObject.put(header, value);
                }
                jsonArray.put(jsonObject);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        return jsonArray;
    }
}

