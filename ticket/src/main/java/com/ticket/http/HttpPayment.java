package com.ticket.http;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.ticket.dto.response.ApplicationResponse;
import com.ticket.exception.ApplicationException;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.fluent.Request;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;


import java.io.IOException;

@Service
@Slf4j
public class HttpPayment {

    private final ObjectMapper mapper;

    public HttpPayment(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    public ApplicationResponse getStatus() throws IOException {
        try {
            HttpResponse resp = Request.Get("http://localhost:8080/randomstatus")
                    .execute()
                    .returnResponse();
            //разбираем ответ
            if (resp.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
                throw new ApplicationException("Error get status");
            }
            String response = EntityUtils.toString(resp.getEntity(), "UTF-8");
            return mapper.readValue(response, ApplicationResponse.class);
        } catch (Exception e) {
            log.info("HttpPayment send error", e);
            throw new ApplicationException("HttpPayment send error");
        }
    }
}
