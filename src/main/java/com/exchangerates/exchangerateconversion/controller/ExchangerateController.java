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

    /**
     * This method received the request for GBP/USD/HKD currencies Exchange rate details
     * and it will call service layer and pull rates details from Exchange API and save
     * details into H2 DB and sending response back to user.
     *
     * @return Success message response
     * @throws JsonProcessingException
     */
    @GetMapping("/data")  //First end point
    public String getexchangerate() throws JsonProcessingException
    {
        return exchangerateService.getExchangerate();
    }
    /**
     * This method received the request for GBP currencies for particular date Exchange rate details
     * and it will call service layer and pull rates details for that date from Exchange API and save
     * details into H2 DB and sending response back to user.
     *
     * @param date
     * @return Success message response
     * @throws JsonProcessingException
     */
    @GetMapping("/{date}") //Second end point
    public String getexchageratebydate(@PathVariable("date") String date) throws JsonProcessingException
    {
        return exchangerateService.getexchageratebydate(date);
    }

    /**
     * This method received the request for All currencies Exchange rate details
     * and it will call service layer and pull rates details Exchange API and save
     * details into H2 DB and sending response back to user.
     *
     * @return Success message response
     * @throws JsonProcessingException
     */
    @GetMapping("/alldata")  //Third end point
    public String getallexchangerate() throws JsonProcessingException
    {
        return exchangerateService.getallexchangerate();
    }

    /**
     * This method received the request for All currencies Exchange rate details for particular date
     * and it will call service layer and pull rates details from H2 DB and Display
     * details into user console
     *
     * @param date
     * @return Exchange Rates
     */
    @GetMapping("/alldata/{date}")  //Fourth end point
    public Double getExchangeRatesInfoByDate(@PathVariable("date") String date){
        return exchangerateService.getExchangeRatesInfoByDate(date);
    }

    /**
     * This method received the request for All currencies Exchange rate details in between two
     * dates and it will call service layer and pull rates details from H2 DB and Display
     * details into user console
     *
     * @param fromDate
     * @param toDate
     * @return List of Exchange Rates
     */
    @GetMapping("/alldata/{fromdate}/{todate}") //Fifth end point
public List<ExchangerateData> getExchangeRatesInBwtDates(@PathVariable("fromdate") String fromDate, @PathVariable("todate") String toDate)
{
    return exchangerateService.getExchangeRatesInBwtDates(fromDate, toDate);
}




}
