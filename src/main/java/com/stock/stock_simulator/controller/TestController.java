package com.stock.stock_simulator.controller;

import com.stock.stock_simulator.interfaces.StockApiInterface;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Please explain the class!!
 *
 * @author : jonghoon
 * @fileName : TestController
 * @since : 2/5/24
 */
@RestController
@RequestMapping("/api2")
public class TestController {
    private final StockApiInterface stockApi;

    public TestController(StockApiInterface stockApi) {
        this.stockApi = stockApi;
    }

    @GetMapping("/hello")
    public ResponseEntity<Object> testApi() {
        System.out.println("hello");
        String result = "API 통신에 성공하였습니다.";
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/test")
    public String test() {
        return stockApi.getWebSocketKey();
    }
}