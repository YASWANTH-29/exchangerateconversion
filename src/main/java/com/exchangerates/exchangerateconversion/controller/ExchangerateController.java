package com.exchangerates.exchangerateconversion.controller;

import com.exchangerates.exchangerateconversion.Entity.ExchangerateData;
import com.exchangerates.exchangerateconversion.repository.ExchangerateRepository;
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
import java.util.List;

@RestController
public class ExchangerateController {

    @Autowired
    private ExchangerateRepository exchangerateRepository;

    @Autowired
    private ExchangerateData exchangerateData;

    @GetMapping("/data")
    public String getexchangerate() throws JsonProcessingException
    {
        List<ExchangerateData> exchangeratelist = new ArrayList<>();

        for (int i = 0; i <= 12 ; i++) {

            String urlList = "http://api.exchangeratesapi.io/v1/" + YearMonth.now().minusMonths(i) + "-01" + "?access_key=9acd520731b3cae9fa41659c7483e8fb&base=eur&symbols=GBP,USD,HKD";

            System.out.println(urlList);

            RestTemplate restTemplate = new RestTemplate();

            String response = restTemplate.getForEntity(urlList, String.class).getBody();

            ObjectMapper objectMapper = new ObjectMapper();

            JsonNode jsonNode = objectMapper.readTree(response);

            exchangerateData = objectMapper.reader().forType(ExchangerateData.class).readValue(response);

                    exchangeratelist.add(exchangerateData);
        }
        exchangerateRepository.saveAll(exchangeratelist);
        return "data is saved";
    }
    @GetMapping(value="/{date}")
    public String getexchageratebydate(@PathVariable("date") String date) throws JsonProcessingException
    {
        String url = "http://api.exchangeratesapi.io/v1/" + date +"?access_key=9acd520731b3cae9fa41659c7483e8fb&base=eur&symbols=GBP";

        System.out.println(url);

        RestTemplate restTemplate = new RestTemplate();

        String response = restTemplate.getForEntity(url, String.class).getBody();

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(response);
        exchangerateData = objectMapper.reader().forType(ExchangerateData.class).readValue(response);
        exchangerateRepository.save(exchangerateData);

        return "data saved by date";
    }
    @GetMapping("/alldata")
    public String getallexchangerate() throws JsonProcessingException
    {
        List<ExchangerateData> exchangeratelist = new ArrayList<>();

        for (int i = 0; i <= 12 ; i++) {

            String urlList = "http://api.exchangeratesapi.io/v1/" + YearMonth.now().minusMonths(i) + "-01" + "?access_key=9acd520731b3cae9fa41659c7483e8fb&base=eur&symbols=";

            System.out.println(urlList);

            RestTemplate restTemplate = new RestTemplate();

            String response = restTemplate.getForEntity(urlList, String.class).getBody();

            ObjectMapper objectMapper = new ObjectMapper();

            JsonNode jsonNode = objectMapper.readTree(response);
            exchangerateData = objectMapper.reader().forType(ExchangerateData.class).readValue(response);

            exchangeratelist.add(exchangerateData);
        }
        exchangerateRepository.saveAll(exchangeratelist);
        return "All data is saved";
    }

@GetMapping("/{fromdate}/{todate}")
public List<ExchangerateData> getExchangeRatesInBwtDates(String fromDate, String toDate) {
    //logger.info("ExchangeRateService : getExchangeRatesInfoByDate");
    String todayDate = getTodayDate();
    List<ExchangerateData> exchangeRatesList = exchangerateRepository.findAllByDateBetween(fromDate,toDate);
    ExchangerateData exchangerateData= exchangerateRepository.findByDate(todayDate);
    exchangeRatesList.add(exchangerateData);
    return exchangeRatesList;
}

@GetMapping("/info/{date}")
public Object getExchangeRatesInfoByDate(@PathVariable("date") String date) {

    Object obj = new Object();
   // boolean result = ExchangeRatesUtility.isDateValid(date, EchangeRatesConstants.DATE_FORMATE);
    //if(result) {
        exchangerateData = exchangerateRepository.findByDate(date);
        obj = exchangerateData;

    return obj;
}

    public static String getTodayDate() {
     //   logger.info("ExchangeRatesUtility : getTodayDate");
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }
}
