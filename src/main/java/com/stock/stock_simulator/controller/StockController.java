package com.stock.stock_simulator.controller;

import com.stock.stock_simulator.DTO.StockOrderDto;
import com.stock.stock_simulator.entity.History;
import com.stock.stock_simulator.service.StockService;
import jakarta.servlet.http.HttpServletRequest;
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
    @ResponseBody
    public String buy(
            @RequestBody StockOrderDto stockOrderDto
    ) {
        String symbol = stockOrderDto.getSymbol();
        Integer amount = stockOrderDto.getAmount();

        stockService.handleBuy(symbol, amount);
        return "";
    }
    @PostMapping("/sell")
    @ResponseBody
    public String sell(
            @RequestBody StockOrderDto stockOrderDto
    ) {
        String symbol = stockOrderDto.getSymbol();
        Integer amount = stockOrderDto.getAmount();

        stockService.handleSell(symbol, amount);
        return "";
    }
}
