package com.exchangerates.exchangerateconversion.controller;

import com.exchangerates.exchangerateconversion.Entity.ExchangerateData;
import com.exchangerates.exchangerateconversion.repository.ExchangerateRepository;
import com.exchangerates.exchangerateconversion.service.ExchangerateService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
public class ExchangerateController {

    @Autowired
    private ExchangerateRepository exchangerateRepository;

    @Autowired
    private ExchangerateData exchangerateData;

    @Autowired
    private ExchangerateService exchangerateService;

    @GetMapping("/data")
    public String getexchangerate() throws JsonProcessingException
    {
        return exchangerateService.getExchangerate();
    }
    @GetMapping("/{date}")
    public String getexchageratebydate(@PathVariable("date") String date) throws JsonProcessingException
    {
        return exchangerateService.getexchageratebydate(date);
    }
    @GetMapping("/alldata")
    public String getallexchangerate() throws JsonProcessingException
    {
        return exchangerateService.getallexchangerate();
    }

@GetMapping("/alldata/{fromdate}/{todate}")
public List<ExchangerateData> getExchangeRatesInBwtDates(@PathVariable("fromdate") String fromDate, @PathVariable("todate") String toDate) throws JsonProcessingException {
    return exchangerateService.getExchangeRatesInBwtDates(fromDate, toDate);
}

@GetMapping("/alldata/{date}")
public void getExchangeRatesInfoByDate(@PathVariable("date") String date){
         exchangerateService.getExchangeRatesInfoByDate(date);
}


}
