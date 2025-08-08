package com.stock.stock_simulator.controller;

import com.stock.stock_simulator.DTO.HoldingDto;
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

    @GetMapping("item")
    public HoldingDto getStockItem(
            @RequestParam String symbol
    ) {
        return stockService.getStockItem(symbol);
    }

    @GetMapping("list")
    public List<HoldingDto> getStockList() {
        return stockService.getStockList();
    }

    @GetMapping("history")
    public List<History> getHistoryOfUser() {
        return null;
    }

    @PostMapping("/buy")
    @ResponseBody
    public HoldingDto buy(
            @RequestBody StockOrderDto stockOrderDto
    ) {
        String symbol = stockOrderDto.getSymbol();
        Integer amount = stockOrderDto.getAmount();
        Double price = stockOrderDto.getPrice();

        return stockService.handleBuy(symbol, amount, price);

    }
    @PostMapping("/sell")
    @ResponseBody
    public HoldingDto sell(
            @RequestBody StockOrderDto stockOrderDto
    ) {
        String symbol = stockOrderDto.getSymbol();
        Integer amount = stockOrderDto.getAmount();
        Double price = stockOrderDto.getPrice();

        return stockService.handleSell(symbol, amount, price);
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
