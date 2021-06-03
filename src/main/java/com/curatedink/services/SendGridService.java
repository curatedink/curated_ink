package com.curatedink.services;

import com.curatedink.models.*;
import com.sendgrid.*;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service("sendGridService")
public class SendGridService {
    SendGrid sendGrid;

    public SendGridService(SendGrid sendGrid) {
        this.sendGrid = sendGrid;
    }

    public Response sendEmail(String from, String to, String subject, String body) {
        Mail mail = new Mail(new Email(from), subject, new Email(to), new Content("text/plain", body));
        mail.setReplyTo(new Email("curated.ink.com@gmail.com"));
        Request request = new Request();
        Response response = null;

        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            this.sendGrid.api(request);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return response;
    }
}
