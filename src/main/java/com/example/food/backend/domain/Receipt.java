package com.example.food.backend.domain;

import com.example.food.ui.utils.converters.LocalDateConverter;
import net.bytebuddy.implementation.bind.annotation.Default;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "FOOD_RECEIPT")
public class Receipt extends AbstractEntity {

  private static final long serialVersionUID = 1L;

  private LocalDate receiptDate;
  private BigDecimal amount;
  private BigDecimal personalExpenses;
  private String store;

  public Receipt() {
  }

  public Receipt(LocalDate receiptDate, BigDecimal amount) {
    this.receiptDate = receiptDate;
    this.amount = amount;
    this.personalExpenses = new BigDecimal(0);
  }

  @Convert(converter = LocalDateConverter.class)
  @Column(name = "RECEIPT_DATE", nullable = false)
  public LocalDate getReceiptDate() {
    return receiptDate;
  }

  public void setReceiptDate(LocalDate receiptDate) {
    this.receiptDate = receiptDate;
  }

  @Column(name = "AMOUNT", precision = 5, scale = 2, nullable = false)
  public BigDecimal getAmount() {
    return amount;
  }

  public void setAmount(BigDecimal amount) {
    this.amount = amount;
  }

  @Column(name = "STORE")
  public String getStore() {
    return store;
  }

  public void setStore(String store) {
    this.store = store;
  }

  @Column(name = "PERSONAL_EXPENSES", precision = 5, scale = 2)
  public BigDecimal getPersonalExpenses() {
    return personalExpenses;
  }

  public void setPersonalExpenses(BigDecimal personalExpenses) {
    this.personalExpenses = personalExpenses;
  }
}
