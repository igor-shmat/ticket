package com.ticket.controller;

import com.ticket.dto.request.CreateApplicationRequest;
import com.ticket.dto.response.ApplicationResponse;
import com.ticket.entity.Application;
import com.ticket.entity.PaymentStatus;
import com.ticket.http.HttpPayment;
import com.ticket.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;


@RestController
@Validated
public class ApplicationController {

  @Autowired
  HttpPayment httpPayment;

  private final ApplicationService applicationService;

  public ApplicationController(ApplicationService applicationService) {
    this.applicationService = applicationService;
  }

  @PostMapping("/save")
  public ResponseEntity<ApplicationResponse> save(@RequestBody @Valid CreateApplicationRequest createApplicationRequest) {
    return ResponseEntity.status(HttpStatus.OK).body(applicationService.save(createApplicationRequest));
  }

  @GetMapping("/searchById")
  public ResponseEntity<List<Application>> getById(@RequestHeader Long clientId) {
    return ResponseEntity.status(HttpStatus.OK).body(applicationService.getById(clientId));
  }

  @GetMapping("/getstatus")
  public ResponseEntity<ApplicationResponse> getStatus(@RequestParam Long id) throws IOException {
    httpPayment.getStatus();
    return ResponseEntity.status(HttpStatus.OK).body(applicationService.getStatus(id));
  }

  @GetMapping("/randomstatus")
  public ResponseEntity<ApplicationResponse> getRandomStatus() {
    return ResponseEntity.status(HttpStatus.OK).body(new ApplicationResponse<>(PaymentStatus.generateRandomStatus().toString()));
  }
}
