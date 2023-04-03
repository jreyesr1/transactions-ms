package com.digital.bank.transactions.model;

import java.net.URI;
import java.util.Objects;
import com.digital.bank.transactions.model.Account;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
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
 * Transaction
 */
@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-04-02T18:16:14.627916400-05:00[America/Guayaquil]")
@jakarta.persistence.Entity
public class Transaction {

  @JsonProperty("reference")
  private String reference;

  @JsonProperty("date")
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private OffsetDateTime date;

  @JsonProperty("amount")
  private java.math.BigDecimal amount;

  @JsonProperty("fee")
  private java.math.BigDecimal fee;

  @JsonProperty("description")
  private String description;

  /**
   * Gets or Sets status
   */
  public enum StatusEnum {
    PENDING("PENDING"),
    
    SETTLED("SETTLED"),
    
    FUTURE("FUTURE"),
    
    INVALID("INVALID");

    private String value;

    StatusEnum(String value) {
      this.value = value;
    }

    @JsonValue
    public String getValue() {
      return value;
    }

    @Override
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static StatusEnum fromValue(String value) {
      for (StatusEnum b : StatusEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
  }

  @JsonProperty("status")
  private StatusEnum status;

  @JsonProperty("id")
  @jakarta.persistence.Id @jakarta.persistence.GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
  private Long id;

  @JsonProperty("account")
  @jakarta.persistence.ManyToOne
  private Account account;

  public Transaction reference(String reference) {
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

  public Transaction date(OffsetDateTime date) {
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

  public Transaction amount(java.math.BigDecimal amount) {
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

  public Transaction fee(java.math.BigDecimal fee) {
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

  public Transaction description(String description) {
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

  public Transaction status(StatusEnum status) {
    this.status = status;
    return this;
  }

  /**
   * Get status
   * @return status
  */
  
  @Schema(name = "status", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  public StatusEnum getStatus() {
    return status;
  }

  public void setStatus(StatusEnum status) {
    this.status = status;
  }

  public Transaction id(Long id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
  */
  
  @Schema(name = "id", accessMode = Schema.AccessMode.READ_ONLY, requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Transaction account(Account account) {
    this.account = account;
    return this;
  }

  /**
   * Get account
   * @return account
  */
  @NotNull @Valid 
  @Schema(name = "account", requiredMode = Schema.RequiredMode.REQUIRED)
  public Account getAccount() {
    return account;
  }

  public void setAccount(Account account) {
    this.account = account;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Transaction transaction = (Transaction) o;
    return Objects.equals(this.reference, transaction.reference) &&
        Objects.equals(this.date, transaction.date) &&
        Objects.equals(this.amount, transaction.amount) &&
        Objects.equals(this.fee, transaction.fee) &&
        Objects.equals(this.description, transaction.description) &&
        Objects.equals(this.status, transaction.status) &&
        Objects.equals(this.id, transaction.id) &&
        Objects.equals(this.account, transaction.account);
  }

  @Override
  public int hashCode() {
    return Objects.hash(reference, date, amount, fee, description, status, id, account);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Transaction {\n");
    sb.append("    reference: ").append(toIndentedString(reference)).append("\n");
    sb.append("    date: ").append(toIndentedString(date)).append("\n");
    sb.append("    amount: ").append(toIndentedString(amount)).append("\n");
    sb.append("    fee: ").append(toIndentedString(fee)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    account: ").append(toIndentedString(account)).append("\n");
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

