package com.ticket.service;

import com.ticket.converter.ApplicationRequestToApplicationConverter;
import com.ticket.dao.ApplicationRepository;
import com.ticket.dto.request.CreateApplicationRequest;
import com.ticket.dto.response.ApplicationResponse;
import com.ticket.entity.Application;
import com.ticket.entity.PaymentStatus;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.Date;
import java.util.List;

@Service
public class ApplicationService {

  private final ApplicationRepository applicationRepository;
  private final ApplicationRequestToApplicationConverter applicationRequestToApplicationConverter;


  public ApplicationService(ApplicationRepository applicationRepository,
                            ApplicationRequestToApplicationConverter applicationRequestToApplicationConverter) {
    this.applicationRepository = applicationRepository;
    this.applicationRequestToApplicationConverter = applicationRequestToApplicationConverter;
  }

  public void save(Application application) {
    applicationRepository.save(application);
  }

  public List<Application> getById(Long clientId) {
    Date dateNow = new Date();
    List<Application> applications = applicationRepository.getByClientIdAndTimeDepartureAfter(clientId,dateNow);
    applications.sort(Comparator.comparing(o -> o.getTimeDeparture()));
    return applications;
  }

  public ApplicationResponse save(CreateApplicationRequest request) {
    Application application = applicationRepository.save(applicationRequestToApplicationConverter.convert(request));
    return new ApplicationResponse<>(application.getId());
  }

  public ApplicationResponse getStatus(Long id) {
    String status = applicationRepository.getPaymentStatus(id);
    return new ApplicationResponse<>(status);
  }

  public Application getApplication() {
    return applicationRepository.findFirstByPaymentStatus(PaymentStatus.NEW);
  }
}
