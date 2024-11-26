package com.stock.stock_simulator.controller;

import com.stock.stock_simulator.entity.History;
import com.stock.stock_simulator.service.StockService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/stock")
public class StockController {
    private final StockService stockService;
    public StockController(StockService stockService) {
        this.stockService = stockService;
    }

    @GetMapping("list")
    public List<History> getStockList() {
        return null;
    }

    @GetMapping("history")
    public List<History> getHistoryOfUser() {
        return null;
    }

    @PostMapping("/buy")
    public String buy(
            @RequestBody String ticker,
            @RequestBody Integer amount
    ) {
        return "";
    }
    @PostMapping("/sell")
    public String sell(
            @RequestBody String ticker,
            @RequestBody Integer amount
    ) {
        return "";
    }
}
