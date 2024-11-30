package com.stock.stock_simulator.interfaces;

public interface StockApiInterface {
    String getWebSocketKey();

    String getAccessKey();

    String getKoreaStockPrice(String FID_COND_MRKT_DIV_CODE, String FID_INPUT_ISCD);
    String getNasdaqStockPrice(String SYMB);

    String getKoreaTickData(String FID_COND_MRKT_DIV_CODE, String FID_INPUT_ISCD, String FID_INPUT_HOUR_1, String FID_PW_DATA_INCU_YN);
    String getNasdaqTickData(String EXCD, String SYMB, String NMIN, String PINC, String NEXT, String NREC, String FILL, String KEYB);

    String getCurrentStockPrice(String symbol) throws Exception;
}
