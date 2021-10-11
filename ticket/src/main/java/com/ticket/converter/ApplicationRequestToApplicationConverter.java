package com.ticket.converter;

import com.ticket.dto.request.CreateApplicationRequest;
import com.ticket.entity.Application;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ApplicationRequestToApplicationConverter implements Converter<CreateApplicationRequest, Application> {

  @Override
  public Application convert(CreateApplicationRequest source) {
    Application application = new Application();
    application.setClientId(source.getClientId());
    application.setRouteNumber(source.getRouteNumber());
    application.setTimeDeparture(source.getTimeDeparture());
    application.setTicket(source.getTicket());

    return application;
  }
}
