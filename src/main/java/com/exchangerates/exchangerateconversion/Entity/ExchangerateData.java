package com.exchangerates.exchangerateconversion.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.hibernate.exception.DataException;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Date;
import java.util.Map;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Component
public class ExchangerateData {

private String success;
    private String timestamp;
    private String historical;
private String base;
    @Id
    private String date;
    @ElementCollection
    @MapKeyColumn(name = "item")
    @CollectionTable(name = "ITEM_RATE")
@Column
   @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
private Map<String,Double> rates;

}
