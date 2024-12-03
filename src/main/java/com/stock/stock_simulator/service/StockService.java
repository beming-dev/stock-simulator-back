package com.stock.stock_simulator.service;

import com.stock.stock_simulator.entity.History;
import com.stock.stock_simulator.entity.Holding;
import com.stock.stock_simulator.interfaces.HistoryRepository;
import com.stock.stock_simulator.interfaces.HoldingRepository;
import com.stock.stock_simulator.interfaces.StockRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.autoconfigure.http.codec.CodecsAutoConfiguration;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class StockService {
    private final HoldingRepository holdingRepository;
    private final HistoryRepository historyRepository;
    private final StockRepository stockRepository;
    private final HttpServletRequest request;
    private final CodecsAutoConfiguration codecsAutoConfiguration;


    public StockService(HoldingRepository holdingRepository, HistoryRepository historyRepository, StockRepository stockRepository, HttpServletRequest request, CodecsAutoConfiguration codecsAutoConfiguration) {
        this.holdingRepository = holdingRepository;
        this.historyRepository = historyRepository;
        this.stockRepository = stockRepository;
        this.request = request;
        this.codecsAutoConfiguration = codecsAutoConfiguration;
    }

    public void handleBuy(String symbol, Integer amount){
        Holding holding = holdingRepository.findBySymbol(symbol);
        if(holding ==null) holding = new Holding();

        System.out.println("buy");

        History history = new History();

        Double price = 10d;
        String gid = (String) request.getAttribute("gid");

        Double currentAvg = holding.getAverage();
        Integer currentAmt = holding.getAmount();
        Double newAvg = (currentAvg * currentAmt + amount * price) / currentAmt + amount;

        holding.setSymbol(symbol);
        holding.setBuyPrice(price);
        holding.setAmount(currentAmt + amount);
        holding.setAverage(newAvg);
        holding.setUserId(gid);

        history.setSymbol(symbol);
        history.setAmount(amount);
        history.setPrice(price);
        history.setTimestamp(new Date().toString());
        history.setType("buy");
        history.setUserId(gid);

        holdingRepository.save(holding);
        historyRepository.save(history);
    }
    public void handleSell(String symbol, Integer amount){
        String gid = (String) request.getAttribute("gid");

        Holding holding = holdingRepository.findBySymbolAndUserId(symbol, gid);
        if(holding == null) return;

        History history = new History();

        Double price = 10d;

        Double currentAvg = holding.getAverage();
        Integer currentAmt = holding.getAmount();

        holding.setSymbol(symbol);
        holding.setBuyPrice(price);
        holding.setAmount(Math.max(currentAmt - amount, 0));
        holding.setUserId("");

        history.setSymbol(symbol);
        history.setAmount(amount);
        history.setPrice(price);
        history.setTimestamp(new Date().toString());
        history.setType("sell");
        history.setUserId(gid);

        holdingRepository.save(holding);
        historyRepository.save(history);
    }
}
