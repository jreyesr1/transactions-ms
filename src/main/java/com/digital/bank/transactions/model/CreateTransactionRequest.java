package com.digital.bank.transactions.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
import java.time.OffsetDateTime;
import org.springframework.format.annotation.DateTimeFormat;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * CreateTransactionRequest
 */
@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor

@JsonTypeName("create_transaction_request")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-04-02T18:16:14.627916400-05:00[America/Guayaquil]")
public class CreateTransactionRequest {

  @JsonProperty("reference")
  private String reference;

  @JsonProperty("account_iban")
  private String accountIban;

  @JsonProperty("date")
  @com.digital.bank.transactions.util.interfaces.ValidOffsetDateTime
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private OffsetDateTime date;

  @JsonProperty("amount")
  private java.math.BigDecimal amount;

  @JsonProperty("fee")
  private java.math.BigDecimal fee;

  @JsonProperty("description")
  private String description;

  public CreateTransactionRequest reference(String reference) {
    this.reference = reference;
    return this;
  }

  /**
   * Get reference
   * @return reference
  */
  
  @Schema(name = "reference", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  public String getReference() {
    return reference;
  }

  public void setReference(String reference) {
    this.reference = reference;
  }

  public CreateTransactionRequest accountIban(String accountIban) {
    this.accountIban = accountIban;
    return this;
  }

  /**
   * Get accountIban
   * @return accountIban
  */
  @NotNull 
  @Schema(name = "account_iban", requiredMode = Schema.RequiredMode.REQUIRED)
  public String getAccountIban() {
    return accountIban;
  }

  public void setAccountIban(String accountIban) {
    this.accountIban = accountIban;
  }

  public CreateTransactionRequest date(OffsetDateTime date) {
    this.date = date;
    return this;
  }

  /**
   * Get date
   * @return date
  */
  @Valid 
  @Schema(name = "date", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  public OffsetDateTime getDate() {
    return date;
  }

  public void setDate(OffsetDateTime date) {
    this.date = date;
  }

  public CreateTransactionRequest amount(java.math.BigDecimal amount) {
    this.amount = amount;
    return this;
  }

  /**
   * Get amount
   * @return amount
  */
  @NotNull @Valid 
  @Schema(name = "amount", requiredMode = Schema.RequiredMode.REQUIRED)
  public java.math.BigDecimal getAmount() {
    return amount;
  }

  public void setAmount(java.math.BigDecimal amount) {
    this.amount = amount;
  }

  public CreateTransactionRequest fee(java.math.BigDecimal fee) {
    this.fee = fee;
    return this;
  }

  /**
   * Get fee
   * @return fee
  */
  @Valid 
  @Schema(name = "fee", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  public java.math.BigDecimal getFee() {
    return fee;
  }

  public void setFee(java.math.BigDecimal fee) {
    this.fee = fee;
  }

  public CreateTransactionRequest description(String description) {
    this.description = description;
    return this;
  }

  /**
   * Get description
   * @return description
  */
  
  @Schema(name = "description", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CreateTransactionRequest createTransactionRequest = (CreateTransactionRequest) o;
    return Objects.equals(this.reference, createTransactionRequest.reference) &&
        Objects.equals(this.accountIban, createTransactionRequest.accountIban) &&
        Objects.equals(this.date, createTransactionRequest.date) &&
        Objects.equals(this.amount, createTransactionRequest.amount) &&
        Objects.equals(this.fee, createTransactionRequest.fee) &&
        Objects.equals(this.description, createTransactionRequest.description);
  }

  @Override
  public int hashCode() {
    return Objects.hash(reference, accountIban, date, amount, fee, description);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CreateTransactionRequest {\n");
    sb.append("    reference: ").append(toIndentedString(reference)).append("\n");
    sb.append("    accountIban: ").append(toIndentedString(accountIban)).append("\n");
    sb.append("    date: ").append(toIndentedString(date)).append("\n");
    sb.append("    amount: ").append(toIndentedString(amount)).append("\n");
    sb.append("    fee: ").append(toIndentedString(fee)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

