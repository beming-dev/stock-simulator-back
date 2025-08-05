package com.stock.stock_simulator.utils;

import com.stock.stock_simulator.constant.StockConstant;

public final class StockUtil {
    private StockUtil() {
        // 인스턴스화 방지
    }

    public static String koEnBySymbol(String symbol) {
        if (symbol == null || symbol.isEmpty()) {
            throw new IllegalArgumentException("symbol은 null 또는 빈 문자열일 수 없습니다.");
        }
        // 첫 글자가 숫자인지 확인
        return Character.isDigit(symbol.charAt(0))
                ? StockConstant.KO
                : StockConstant.EN;
    }
}
