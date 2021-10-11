package com.exchangerates.exchangerateconversion.service;

import com.exchangerates.exchangerateconversion.Entity.ExchangerateData;
import com.exchangerates.exchangerateconversion.controller.ExchangerateController;
import com.exchangerates.exchangerateconversion.exceptions.CustomException;
import com.exchangerates.exchangerateconversion.repository.ExchangerateRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;


@Service
public class ExchangerateService {

    @Autowired
    private ExchangerateRepository exchangerateRepository;

    @Autowired
    private ExchangerateData exchangerateData;

    @Autowired
    private ExchangerateController exchangerateController;

public String getExchangerate() throws JsonProcessingException
{
    try {
        List<ExchangerateData> exchangeratelist = new ArrayList<>();

        for (int i = 0; i <= 12; i++) {

            String urlList = "http://api.exchangeratesapi.io/v1/" + YearMonth.now().minusMonths(i) + "-01" + "?access_key=e75bf8b8ba4e743337a00b4de343f0eb&base=eur&symbols=GBP,USD,HKD";
            RestTemplate restTemplate = new RestTemplate();
            String response = restTemplate.getForEntity(urlList, String.class).getBody();

            ObjectMapper objectMapper = new ObjectMapper();

            JsonNode jsonNode = objectMapper.readTree(response);

            exchangerateData = objectMapper.reader().forType(ExchangerateData.class).readValue(response);

            exchangeratelist.add(exchangerateData);
        }
        if (!exchangerateData.getRates().isEmpty()) {
            exchangerateRepository.saveAll(exchangeratelist);
            return "Data is retrived";
        } else
            return "Data is not Retrived";
    }
    catch(JsonProcessingException jpe) {
        throw new CustomException("Unwanted data response from API call");
    }catch(ResourceAccessException rae) {
        throw new CustomException("Internal Server is unavailable");
    }catch(Exception e) {
        throw new CustomException("Somthing went wrong can you try after sometime");
    }
}

public String getexchageratebydate(String date) throws JsonProcessingException
{
    String url = "http://api.exchangeratesapi.io/v1/" + date +"?access_key=e75bf8b8ba4e743337a00b4de343f0eb&base=eur&symbols=GBP";

    //System.out.println(url);

    RestTemplate restTemplate = new RestTemplate();

    String response = restTemplate.getForEntity(url, String.class).getBody();

    ObjectMapper objectMapper = new ObjectMapper();
    JsonNode jsonNode = objectMapper.readTree(response);
    exchangerateData = objectMapper.reader().forType(ExchangerateData.class).readValue(response);
    if(!exchangerateData.getRates().isEmpty()) {
        exchangerateRepository.save(exchangerateData);

        return "data saved by date";
    }
    else
        return "data is not retrived";
}
    public String getallexchangerate() throws JsonProcessingException {
        List<ExchangerateData> exchangeratelist = new ArrayList<>();

        for (int i = 0; i <= 12; i++) {

            String urlList = "http://api.exchangeratesapi.io/v1/" + YearMonth.now().minusMonths(i) + "-01" + "?access_key=e75bf8b8ba4e743337a00b4de343f0eb&base=eur&symbols=";

            System.out.println(urlList);

            RestTemplate restTemplate = new RestTemplate();

            String response = restTemplate.getForEntity(urlList, String.class).getBody();

            ObjectMapper objectMapper = new ObjectMapper();

            JsonNode jsonNode = objectMapper.readTree(response);
            exchangerateData = objectMapper.reader().forType(ExchangerateData.class).readValue(response);

            exchangeratelist.add(exchangerateData);
        }
        if (!exchangerateData.getRates().isEmpty()) {
            exchangerateRepository.saveAll(exchangeratelist);
            return "All data is saved";
        }
        else
            return "Data is not Retrived";
    }
    public List<ExchangerateData> getExchangeRatesInBwtDates(String fromdate,String todate)
    {
        String tday=getTodayDate();
        List<ExchangerateData> exchangeRatesList =exchangerateRepository.getAllByDateBetween(fromdate,todate);
        exchangerateData = exchangerateRepository.findByDate(tday);
        exchangeRatesList.add(exchangerateData);
        return exchangeRatesList;
    }

    public Double getExchangeRatesInfoByDate(String date)
    {
        exchangerateData = exchangerateRepository.findByDate(date);
        Map<String,Double> rate=exchangerateData.getRates();
        Double obj=rate.get("GBP");
        System.out.println("The GBP rate for givendate"+date+" is "+obj);
        return obj;
    }
    public String getTodayDate() {
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }

}
