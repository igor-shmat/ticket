package com.ticket.dao;

import com.ticket.entity.Application;
import com.ticket.entity.PaymentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface ApplicationRepository extends JpaRepository<Application, Long> {

    @Query("select paymentStatus from Application where id=:id")
    String getPaymentStatus(Long id);

    List<Application> getByClientIdAndTimeDepartureAfter(Long clientId, Date dateNow);

    Application findFirstByPaymentStatus(PaymentStatus status);
}
