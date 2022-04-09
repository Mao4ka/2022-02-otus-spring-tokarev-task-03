package ru.otus.enterprise;

import ru.otus.AnswerType;

import java.util.Locale;

public interface IntermediateMessageService {
    void commentUserAnswer(AnswerType answerType, Locale messageLocale);
}
