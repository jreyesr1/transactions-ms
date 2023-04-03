package com.digital.bank.transactions.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * Client Account
 */
@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor

@Schema(name = "Account", description = "Client Account")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-04-02T18:16:14.627916400-05:00[America/Guayaquil]")
@jakarta.persistence.Entity
public class Account {

  @JsonProperty("account_iban")
  private String accountIban;

  @JsonProperty("balance")
  private java.math.BigDecimal balance;

  @JsonProperty("id")
  @jakarta.persistence.Id @jakarta.persistence.GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
  private Long id;

  public Account accountIban(String accountIban) {
    this.accountIban = accountIban;
    return this;
  }

  /**
   * Get accountIban
   * @return accountIban
  */
  
  @Schema(name = "account_iban", accessMode = Schema.AccessMode.READ_ONLY, requiredMode = Schema.RequiredMode.REQUIRED)
  public String getAccountIban() {
    return accountIban;
  }

  public void setAccountIban(String accountIban) {
    this.accountIban = accountIban;
  }

  public Account balance(java.math.BigDecimal balance) {
    this.balance = balance;
    return this;
  }

  /**
   * Get balance
   * @return balance
  */
  @Valid 
  @Schema(name = "balance", accessMode = Schema.AccessMode.READ_ONLY, requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  public java.math.BigDecimal getBalance() {
    return balance;
  }

  public void setBalance(java.math.BigDecimal balance) {
    this.balance = balance;
  }

  public Account id(Long id) {
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

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Account account = (Account) o;
    return Objects.equals(this.accountIban, account.accountIban) &&
        Objects.equals(this.balance, account.balance) &&
        Objects.equals(this.id, account.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(accountIban, balance, id);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Account {\n");
    sb.append("    accountIban: ").append(toIndentedString(accountIban)).append("\n");
    sb.append("    balance: ").append(toIndentedString(balance)).append("\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
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

