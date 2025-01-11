package com.notion.notionapi.application;

import com.google.gson.Gson;
import com.notion.notioncore.Constrants;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.ses.SesClient;
import software.amazon.awssdk.services.ses.model.Destination;
import software.amazon.awssdk.services.ses.model.SendTemplatedEmailRequest;
import software.amazon.awssdk.services.ses.model.SendTemplatedEmailResponse;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class MailService {
    private final SesClient sesClient;

    public void setTemplatedEmail(String templateName,
                                  Map<String, String> templeData,
                                  String... receivers) {
        if (receivers.length == 0) throw  new RuntimeException("exception");

        Destination destination = Destination.builder()
                .toAddresses(receivers)
                .build();

        SendTemplatedEmailResponse response = sesClient.sendTemplatedEmail(
                SendTemplatedEmailRequest.builder()
                        .source(Constrants.COMMON_MAIL_SENDER)
                        .destination(destination)
                        .template(templateName)
                        .templateData(new Gson().toJson(templeData))
                        .build()
        );

    }
}
