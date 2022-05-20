package com.example.demo.entity;

import java.math.BigDecimal;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "margin_credit")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class MarginCredit {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private String currency ;
  private BigDecimal amount;
  private BigDecimal interest;
}
