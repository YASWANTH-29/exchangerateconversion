package com.exchangerates.exchangerateconversion.repository;

import com.exchangerates.exchangerateconversion.Entity.ExchangerateData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ExchangerateRepository extends JpaRepository<ExchangerateData,Integer> {


 ExchangerateData findByDate(@Param("date") String date);

//@Query("Select a from ExchangerateData a where a.date<=fromdate and a.date >=todate")
    List<ExchangerateData> getAllByDateBetween(@Param("fromdate") String fromdate,@Param("todate") String todate);


}
