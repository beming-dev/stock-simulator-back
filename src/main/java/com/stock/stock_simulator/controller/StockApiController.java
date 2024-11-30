package com.stock.stock_simulator.controller;

import com.stock.stock_simulator.interfaces.StockApiInterface;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/stockApi")
public class StockApiController {
    private final StockApiInterface stockApi;

    public StockApiController(StockApiInterface stockApiInterface) {
        this.stockApi = stockApiInterface;
    }

    @GetMapping("/currentPrice")
    public String getCurrentPrice() {
        return stockApi.getKoreaStockPrice("J", "005930");
    }

    @GetMapping("/overseas/currentPrice")
    @ResponseBody
    public String getOverseasCurrentPrice(@RequestParam String SYMB) {
        System.out.println(SYMB);
        return stockApi.getNasdaqStockPrice("AAPL");
    }
}
