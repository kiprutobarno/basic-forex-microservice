package com.ywalakamar.conversion.controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import com.ywalakamar.conversion.interfaces.ForexServiceProxy;
import com.ywalakamar.conversion.model.ConversionDAO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConversionController {
        @Autowired
        private ForexServiceProxy proxy;
        private Logger logger = LoggerFactory.getLogger(this.getClass());

        @GetMapping("/currency-converter-feign/from/{from}/to/{to}/quantity/{quantity}")
        public ConversionDAO convertCurrency(@PathVariable String from, @PathVariable String to,
                        @PathVariable BigDecimal quantity) {
                Map<String, String> uriVariables = new HashMap<>();
                uriVariables.put("from", from);
                uriVariables.put("to", to);
                ConversionDAO response = proxy.retrieveExchangeValue(from, to);
                logger.info("{}", response);

                return new ConversionDAO(response.getId(), from, to, response.getConversionMultiple(), quantity,
                                quantity.multiply(response.getConversionMultiple()), response.getPort());
        }
}