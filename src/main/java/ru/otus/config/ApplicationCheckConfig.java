package ru.otus.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "check")
@Component
@Getter
@Setter
public class ApplicationCheckConfig {
    private int minimumAcceptableCorrectAnswersCount;
}
