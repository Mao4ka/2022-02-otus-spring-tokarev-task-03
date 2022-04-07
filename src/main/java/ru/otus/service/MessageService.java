package ru.otus.service;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
@RequiredArgsConstructor
public class MessageService {

    private final MessageSource messageSource;

    public String getMessage(String messageCode, Locale locale) {
        return messageSource.getMessage(messageCode, null, locale);
    }

    public String getMessageN(String messageCode, Locale locale) {
        return getMessage(messageCode, locale) + "\n" ;
    }

    public String getNMessage(String messageCode, Locale locale) {
        return "\n" + getMessage(messageCode, locale);
    }

    public String getNMessageN(String messageCode, Locale locale) {
        return "\n" + getMessage(messageCode, locale) + "\n" ;
    }
}
