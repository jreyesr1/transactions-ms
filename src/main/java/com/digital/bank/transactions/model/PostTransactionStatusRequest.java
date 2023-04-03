package com.digital.bank.transactions.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.annotation.JsonValue;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * PostTransactionStatusRequest
 */
@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor

@JsonTypeName("post_transaction_status_request")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-04-02T18:16:14.627916400-05:00[America/Guayaquil]")
public class PostTransactionStatusRequest {

  @JsonProperty("reference")
  private String reference;

  /**
   * Gets or Sets channel
   */
  public enum ChannelEnum {
    CLIENT("CLIENT"),
    
    ATM("ATM"),
    
    INTERNAL("INTERNAL");

    private String value;

    ChannelEnum(String value) {
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
    public static ChannelEnum fromValue(String value) {
      for (ChannelEnum b : ChannelEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
  }

  @JsonProperty("channel")
  private ChannelEnum channel;

  public PostTransactionStatusRequest reference(String reference) {
    this.reference = reference;
    return this;
  }

  /**
   * Get reference
   * @return reference
  */
  @NotNull 
  @Schema(name = "reference", requiredMode = Schema.RequiredMode.REQUIRED)
  public String getReference() {
    return reference;
  }

  public void setReference(String reference) {
    this.reference = reference;
  }

  public PostTransactionStatusRequest channel(ChannelEnum channel) {
    this.channel = channel;
    return this;
  }

  /**
   * Get channel
   * @return channel
  */
  
  @Schema(name = "channel", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  public ChannelEnum getChannel() {
    return channel;
  }

  public void setChannel(ChannelEnum channel) {
    this.channel = channel;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PostTransactionStatusRequest postTransactionStatusRequest = (PostTransactionStatusRequest) o;
    return Objects.equals(this.reference, postTransactionStatusRequest.reference) &&
        Objects.equals(this.channel, postTransactionStatusRequest.channel);
  }

  @Override
  public int hashCode() {
    return Objects.hash(reference, channel);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PostTransactionStatusRequest {\n");
    sb.append("    reference: ").append(toIndentedString(reference)).append("\n");
    sb.append("    channel: ").append(toIndentedString(channel)).append("\n");
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

