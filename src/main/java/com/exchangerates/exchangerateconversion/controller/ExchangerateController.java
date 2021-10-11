package com.exchangerates.exchangerateconversion.controller;

import com.exchangerates.exchangerateconversion.Entity.ExchangerateData;
import com.exchangerates.exchangerateconversion.repository.ExchangerateRepository;
import com.exchangerates.exchangerateconversion.service.ExchangerateService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.logging.Logger;

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
public List<ExchangerateData> getExchangeRatesInBwtDates(@PathVariable("fromdate") String fromDate, @PathVariable("todate") String toDate)
{
    return exchangerateService.getExchangeRatesInBwtDates(fromDate, toDate);
}

@GetMapping("/alldata/{date}")
public Double getExchangeRatesInfoByDate(@PathVariable("date") String date){
         return exchangerateService.getExchangeRatesInfoByDate(date);
}


}
