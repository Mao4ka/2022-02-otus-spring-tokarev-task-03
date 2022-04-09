package ru.otus.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "resourse")
@Component
@Getter
@Setter
public class ApplicationSourceConfig {

    private String fileName;
    private String fileNameRu;
    private String lineSeparator;

}
