package com.ticket.scheduler;

import com.ticket.dto.response.ApplicationResponse;
import com.ticket.entity.Application;
import com.ticket.entity.PaymentStatus;
import com.ticket.http.HttpPayment;
import com.ticket.service.ApplicationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Slf4j
public class SchedulerStatus {

  private final HttpPayment httpPayment;
  private final ApplicationService applicationService;

  public SchedulerStatus(HttpPayment httpPayment, ApplicationService applicationService) {
    this.httpPayment = httpPayment;
    this.applicationService = applicationService;
  }

  @Scheduled(cron = "10 * * * * ?")
  public void updateStatus() {
    Application application = applicationService.getApplication();
    if (application == null) {
      log.info("application NEW not found");
      return;
    }
    String status = null;
    do {
      try {
        ApplicationResponse responseStatus = httpPayment.getStatus();
        if (responseStatus == null) {
          log.info("getStatus== null");
          break;
        }
        status = (String) responseStatus.getResult();
        if (PaymentStatus.COMPLETED.toString().equals(status) || PaymentStatus.ERROR.toString().equals(status)) {
          application.setPaymentStatus(PaymentStatus.valueOf(status));
          applicationService.save(application);
          log.info("status update for id =" + application.getId() + " new status= " + status);
          break;
        }
        log.info("try again status = " + status);
      } catch (IOException e) {
        log.error("getStatus send error", e);
        break;
      }
    } while (PaymentStatus.PROCESSED.toString().equals(status));
  }
}
