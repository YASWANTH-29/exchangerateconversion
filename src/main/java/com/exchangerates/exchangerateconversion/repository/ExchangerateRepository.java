package com.exchangerates.exchangerateconversion.repository;

import com.exchangerates.exchangerateconversion.Entity.ExchangerateData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExchangerateRepository extends JpaRepository<ExchangerateData,Integer> {
ExchangerateData findByDate(@Param("date") String date);
List<ExchangerateData> findAllByDateBetween(String fromdate, String todate);
}
