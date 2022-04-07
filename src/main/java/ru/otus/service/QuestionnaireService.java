package ru.otus.service;

import ru.otus.AnswerType;
import ru.otus.dao.entity.Quest;

import java.util.Locale;

public interface QuestionnaireService {

    Locale getApplicationLocale(String userLanguageChoise);

    AnswerType getAnswerType(Quest quest, String userData);
}
