package com.ticket.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sun.istack.NotNull;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.Date;


@Data
public class CreateApplicationRequest {
  @NotNull
  private Long clientId;
  @NotNull
  private Long routeNumber;
  @NotNull
  @NotBlank
  private String ticket;

  //Дата и время отправления
  @NotNull
  @JsonFormat(pattern = "YYYY-MM-dd HH:mm")
  private Date timeDeparture;
}
