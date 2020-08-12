package com.ywalakamar.conversion.controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import com.ywalakamar.conversion.model.ConversionDAO;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ConversionController {

    @GetMapping("/currency-converter/from/{from}/to/{to}/quantity/{quantity}")
    public ConversionDAO convertCurrency(@PathVariable String from, @PathVariable String to,
            @PathVariable BigDecimal quantity) {
        Map<String, String> uriVariables = new HashMap<>();
        uriVariables.put("from", from);
        uriVariables.put("to", to);
        ResponseEntity<ConversionDAO> responseEntity = new RestTemplate().getForEntity(
                "http://localhost:8000/currency-exchange/from/{from}/to/{to}", ConversionDAO.class, uriVariables);
        ConversionDAO response = responseEntity.getBody();

        return new ConversionDAO(response.getId(), from, to, response.getConversionMultiple(), quantity,
                quantity.multiply(response.getConversionMultiple()), response.getPort());
    }
}