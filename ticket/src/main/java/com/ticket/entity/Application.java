package com.ticket.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class Application {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  //идентификатор клиента
  private Long clientId;
  //Номер маршрута
  private Long routeNumber;
  //Дата и время отправления
  private Date timeDeparture;
  @Column(unique = true)
  private String ticket;
  //Cтатус заявки
  @Enumerated(EnumType.STRING)
  private PaymentStatus paymentStatus=PaymentStatus.NEW;
}
