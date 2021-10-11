package com.ticket.converter;

import com.ticket.dto.request.CreateApplicationRequest;
import com.ticket.entity.Application;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ApplicationRequestToApplicationConverterTest {


  private ApplicationRequestToApplicationConverter applicationRequestToApplicationConverter;

  @BeforeEach
  public void setUp() {
    applicationRequestToApplicationConverter= new ApplicationRequestToApplicationConverter();
  }

  @Test
  public void convert() throws ParseException {
    String myDate = "31/12/1998";
    Date date = new SimpleDateFormat("dd/MM/yyyy").parse(myDate);
    CreateApplicationRequest applicationRequest = new CreateApplicationRequest();
    applicationRequest.setRouteNumber(5L);
    applicationRequest.setTimeDeparture(date);

    Application application = applicationRequestToApplicationConverter.convert(applicationRequest);

    assertTrue(application.getRouteNumber().equals(applicationRequest.getRouteNumber()));
  }
}