package com.example.demo.controller;

import com.example.demo.entity.Product;
import com.example.demo.repository.AccountRepository;
import com.example.demo.repository.MarginCreditRepository;
import com.example.demo.repository.ProductRepository;
import com.example.demo.specification.ProductSpecification;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MarginCalculationController {

  private final AccountRepository accountRepository;
  private final MarginCreditRepository creditRepository;
  private final ProductRepository productRepository;

  @GetMapping
  public ResponseEntity<?> getMarginLevel() {
    BigDecimal debts = creditRepository.calculateDebts();
    BigDecimal assets = accountRepository.calculateAssets();

//    BigDecimal interests = creditRepository.calculateInterests();

    BigDecimal marginLevel = assets.divide(debts, MathContext.DECIMAL128);

    Map<String, Object> res = new HashMap<>();

    res.put("debts", debts);
    res.put("assets", assets);
    res.put("marginLevel", marginLevel);
//    res.put("interests",interests);
    res.put("quoteCurrency","USDT");

    return new ResponseEntity<>(res, HttpStatus.ACCEPTED);
  }

  @GetMapping("/specification")
  public ResponseEntity<?> getMarginLevelTest(@Param("quote") String quote) {
    List<Product> res = productRepository.findAll(ProductSpecification.quoteCurrencyEqual(quote));
    return new ResponseEntity<>(res, HttpStatus.ACCEPTED);
  }
}
