package com.ywalakamar.conversion.interfaces;

import com.ywalakamar.conversion.model.ConversionDAO;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "forex-service", url = "localhost:8000")
public interface ForexServiceProxy {
    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    public ConversionDAO retrieveExchangeValue(@PathVariable("from") String from, @PathVariable("to") String to);
}