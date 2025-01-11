package com.notion.notioncore.infra;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.ses.SesClient;

@Configuration
public class AWSConfig {
    @Value("${aws.ses.access-key}")
    private String AWS_SES_ACCESS_KEY;

    @Value("${aws.ses.secret-key}")
    private String AWS_SES_SECRET_KEY;

    @Value("${aws.ses.region}")
    private String AWS_REGION;

    @Bean
    public SesClient sesClient() {
        return SesClient.builder()
                .region(Region.of(AWS_REGION))
                .credentialsProvider(
                        StaticCredentialsProvider.create(
                                AwsBasicCredentials.create(AWS_SES_ACCESS_KEY, AWS_SES_SECRET_KEY)
                        )
                ).build();
    }
}
