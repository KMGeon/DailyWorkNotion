package com.notion.notioncore;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Constrants {
    @Value("${email}")
    public static String COMMON_MAIL_SENDER;
    public static final String INVITE_QR_TEMPLATE = "notion-template-email";
    public static final String SECRET = "SECRET";
}
//  aws ses create-template --cli-input-json file://template.json