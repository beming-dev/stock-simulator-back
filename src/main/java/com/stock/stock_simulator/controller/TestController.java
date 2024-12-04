package com.stock.stock_simulator.controller;

import com.stock.stock_simulator.service.CrawlingService;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("test")
public class TestController {
    private final CrawlingService crawlingService;

    public TestController(CrawlingService crawlingService) {
        this.crawlingService = crawlingService;
    }

    @GetMapping("crawler")
    @ResponseBody
    public String handleSubscription() {
        this.crawlingService.fetchNasdaqSymbolData();
        this.crawlingService.fetchAmexSymbolData();
        this.crawlingService.fetchNewYorkSymbolData();

        return "complete";
    }

    @GetMapping("kospi")
    @ResponseBody
    public String getKospidata() {
        this.crawlingService.downloadKospiCsvWithOtp();

        return "complete";
    }

    @GetMapping("kosdaq")
    @ResponseBody
    public String getKosdaqdata() {
        this.crawlingService.downloadKosdaqCsvWithOtp();

        return "complete";
    }
}