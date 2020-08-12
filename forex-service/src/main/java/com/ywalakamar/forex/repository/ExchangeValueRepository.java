package com.ywalakamar.forex.repository;

import com.ywalakamar.forex.entities.ExchangeValue;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ExchangeValueRepository extends JpaRepository<ExchangeValue, Long> {
    ExchangeValue findByFromAndTo(String from, String to);
}