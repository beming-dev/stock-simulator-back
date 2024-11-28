package com.stock.stock_simulator.interfaces;

public interface StockApiInterface {
    public String getWebSocketKey();

    public String getKoreaStockPrice(String FID_COND_MRKT_DIV_CODE, String FID_INPUT_ISCD);
    public String getNasdaqStockPrice(String SYMB);

    public String getKoreaRealtimeStock(String tr_key);
    public String getNasdaqRealtimeStock(String tr_key);

    public String getKoreaTickData(String FID_COND_MRKT_DIV_CODE, String FID_INPUT_ISCD, String FID_INPUT_HOUR_1, String FID_PW_DATA_INCU_YN);
    public String getNasdaqTickData(String EXCD, String SYMB, String NMIN, String PINC, String NEXT, String NREC, String FILL, String KEYB);
}
