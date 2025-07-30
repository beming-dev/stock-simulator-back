package com.stock.stock_simulator.controller;

import com.stock.stock_simulator.DTO.LikeRequestDTO;
import com.stock.stock_simulator.DTO.StockOrderDto;
import com.stock.stock_simulator.entity.History;
import com.stock.stock_simulator.entity.Holding;
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
    public List<Holding> getStockList() {
        return stockService.getStockList();
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

        System.out.println(symbol);
        System.out.println(amount);
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

    @PostMapping("/like")
    @ResponseBody
    public String setLike(
            @RequestBody LikeRequestDTO likeRequest
    ){
        String symbol = likeRequest.getSymbol();
        stockService.setLike(symbol);
        return "";
    }
}
