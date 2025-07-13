package com.stock.stock_simulator.service;

import com.stock.stock_simulator.entity.History;
import com.stock.stock_simulator.entity.Holding;
import com.stock.stock_simulator.interfaces.HistoryRepository;
import com.stock.stock_simulator.interfaces.HoldingRepository;
import com.stock.stock_simulator.interfaces.LikeRepository;
import com.stock.stock_simulator.interfaces.StockRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class StockService {
    private final HoldingRepository holdingRepository;
    private final HistoryRepository historyRepository;
    private final LikeRepository likeRepository;
    private final StockRepository stockRepository;
    private final HttpServletRequest request;


    public StockService(HoldingRepository holdingRepository, HistoryRepository historyRepository, LikeRepository likeRepository, StockRepository stockRepository, HttpServletRequest request) {
        this.holdingRepository = holdingRepository;
        this.historyRepository = historyRepository;
        this.likeRepository = likeRepository;
        this.stockRepository = stockRepository;
        this.request = request;
    }

    @Transactional(readOnly = true)
    public List<Holding> getStockList(){
        String gid = (String) request.getAttribute("gid");

        return holdingRepository.findByUserId(gid);
    }

    @Transactional
    public void handleBuy(String symbol, Integer amount){
        String gid = (String) request.getAttribute("gid");

        Holding holding = holdingRepository.findBySymbolAndUserId(symbol, gid);
        if(holding ==null) holding = new Holding();
        History history = new History();

        Double price = 10d;

        holding.handleBuy(gid, symbol, amount, price);
        history.handleBuy(gid, symbol, amount, price);

        holdingRepository.save(holding);
        historyRepository.save(history);
    }

    @Transactional
    public void handleSell(String symbol, Integer amount){
        String gid = (String) request.getAttribute("gid");

        Holding holding = holdingRepository.findBySymbolAndUserId(symbol, gid);
        if(holding == null) return;
        History history = new History();

        Double price = 10d;

        Integer currentAmt = holding.getAmount();
        Integer newAmt = currentAmt - amount;

        if(newAmt < 0) throw new RuntimeException("You have " + currentAmt + " stocks");
        else if (newAmt == 0) {
            holdingRepository.delete(holding);
        } else{
            holding.handleSell(newAmt);
            history.handleSell(gid, symbol, amount, price);

            holdingRepository.save(holding);
            historyRepository.save(history);
        }
    }

    public void setLike(String symbol){
        String gid = (String) request.getAttribute("gid");
        likeRepository.insertLike(gid, symbol);
    }
}
 // Received Message: 0|HDFSCNT0|001|DNASAAPL^AAPL^4^20241227^20241227^070229^20241227^210229^258.6000^259.0200^258.4000^259.0200^3^0.0000^0.00^258.9600^259.2200^200^20^10^4629^1198097^2222^680^30.60^1