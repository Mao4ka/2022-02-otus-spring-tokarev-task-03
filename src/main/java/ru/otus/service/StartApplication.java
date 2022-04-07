package ru.otus.service;

import java.util.Locale;

public interface StartApplication {

    String defineLanguage();

    String greeting(Locale messageLocale);

}
