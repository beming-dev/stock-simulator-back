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
    @ResponseBody
    public String getCurrentPrice(@RequestParam String SYMB) throws Exception {
        return stockApi.getCurrentStockPrice(SYMB);
    }

    @GetMapping("/chartData")
    @ResponseBody
    public String getChartData(@RequestParam String SYMB) throws Exception {
        return stockApi.getChartData(SYMB);
    }

    @GetMapping("/mainList")
    @ResponseBody
    public String getMainList(){
        return stockApi.getMainList();
    }
}
