package com.stock.stock_simulator.service;

import com.stock.stock_simulator.DTO.HoldingDto;
import com.stock.stock_simulator.entity.History;
import com.stock.stock_simulator.entity.Holding;
import com.stock.stock_simulator.entity.Stock;
import com.stock.stock_simulator.entity.User;
import com.stock.stock_simulator.interfaces.*;
import com.stock.stock_simulator.utils.StockUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class StockService {
    private final HoldingRepository holdingRepository;
    private final HistoryRepository historyRepository;
    private final LikeRepository likeRepository;
    private final UserRepository userRepository;
    private final StockRepository stockRepository;
    private final HttpServletRequest request;


    public StockService(
            UserRepository userRepository,
            HoldingRepository holdingRepository,
            HistoryRepository historyRepository,
            LikeRepository likeRepository,
            StockRepository stockRepository,
            HttpServletRequest request
    ) {
        this.holdingRepository = holdingRepository;
        this.historyRepository = historyRepository;
        this.likeRepository = likeRepository;
        this.stockRepository = stockRepository;
        this.userRepository = userRepository;
        this.request = request;
    }

    public List<Stock> searchStock(Integer page, String keyword, String country) {
        boolean isKo = "KO".equalsIgnoreCase(country);
        PageRequest pageable = PageRequest.of(page, 50);

        if(!keyword.isEmpty()){
            List<Stock> stockList = stockRepository
                    .findBySymbolContainingIgnoreCaseOrNameContainingIgnoreCase(
                            keyword.trim(), keyword.trim()
                    );
            return stockList;
        }else{
            List<String> countries = isKo
                    ? List.of("KSD","KSP")
                    : List.of("NAS","NYS","AMS");

            Page<Stock> stockList =  stockRepository.findAllByCountryIn(countries, pageable);
            return stockList.getContent();
        }
    }

    public HoldingDto getStockItem(String symbol) {
        String gid = (String) this.request.getAttribute("gid");

        Holding holding =  holdingRepository.findBySymbolAndUserId(symbol, gid);

        if(holding == null) {
            return null;
        }

        return new HoldingDto(
                holding.getId(),
                holding.getAmount(),
                holding.getAverage(),
                holding.getBuyPrice(),
                holding.getSymbol()
        );
    }

    @Transactional(readOnly = true)
    public List<HoldingDto> getStockList(){
        String gid = (String) request.getAttribute("gid");

        List<Holding> holdings =  holdingRepository.findByUserId(gid);
        List<String> symbols = holdings.stream()
                .map(Holding::getSymbol)
                .distinct()
                .toList();
        if (symbols.isEmpty()) {
            return Collections.emptyList();
        }

        List<Stock> stocks = stockRepository.findBySymbolIn(symbols);
        // 4) symbol → name 맵으로 변환
        Map<String, String> nameMap = stocks.stream()
                .collect(Collectors.toMap(Stock::getSymbol, Stock::getName));

        List<HoldingDto> dtos = holdings.stream()
                .map(h -> new HoldingDto(
                        h.getId(),
                        h.getAmount(),
                        h.getAverage(),
                        h.getBuyPrice(),
                        h.getSymbol(),
                        nameMap.get(h.getSymbol())
                ))
                .collect(Collectors.toList());

        return dtos;
    }

    @Transactional
    public HoldingDto handleBuy(String symbol, Integer amount, Double price) {
        String gid = (String) request.getAttribute("gid");

        Holding holding = holdingRepository.findBySymbolAndUserId(symbol, gid);
        if(holding == null) holding = new Holding();
        History history = new History();

        holding.handleBuy(gid, symbol, amount, price);
        history.handleBuy(gid, symbol, amount, price);

        User user = userRepository.findByGid(gid);
        String country = StockUtil.koEnBySymbol(symbol);

        user.updateAsset(country, -price * amount);

        userRepository.save(user);
        holdingRepository.save(holding);
        historyRepository.save(history);

        return new HoldingDto(
                holding.getId(),
                holding.getAmount(),
                holding.getAverage(),
                holding.getBuyPrice(),
                holding.getSymbol()
        );
    }

    @Transactional
    public HoldingDto handleSell(String symbol, Integer amount, Double price) {
        String gid = (String) request.getAttribute("gid");

        Holding holding = holdingRepository.findBySymbolAndUserId(symbol, gid);
        if(holding == null) return null;
        History history = new History();

        Integer currentAmt = holding.getAmount();
        Integer newAmt = currentAmt - amount;

        if(newAmt < 0) throw new RuntimeException("You have " + currentAmt + " stocks");
        else{
            holding.handleSell(newAmt);
            history.handleSell(gid, symbol, amount, price);
        }

        // update user asset
        Double priceDiff = (holding.buyPrice - price) * amount;
        User user = userRepository.findByGid(gid);

        String country = StockUtil.koEnBySymbol(symbol);
        user.updateAsset(country, priceDiff);

        holdingRepository.save(holding);
        historyRepository.save(history);
        userRepository.save(user);

        if (newAmt == 0) {
            holdingRepository.delete(holding);
            return null;
        }else{
            return new HoldingDto(
                    holding.getId(),
                    holding.getAmount(),
                    holding.getAverage(),
                    holding.getBuyPrice(),
                    holding.getSymbol()
            );
        }
    }

    public void setLike(String symbol){
        String gid = (String) request.getAttribute("gid");
        likeRepository.insertLike(gid, symbol);
    }
}
 // Received Message: 0|HDFSCNT0|001|DNASAAPL^AAPL^4^20241227^20241227^070229^20241227^210229^258.6000^259.0200^258.4000^259.0200^3^0.0000^0.00^258.9600^259.2200^200^20^10^4629^1198097^2222^680^30.60^1