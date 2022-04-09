package ru.otus.enterprise;

import ru.otus.dao.entity.Quest;

import java.util.Locale;

public interface OutputQuestionnaire {

    void printQuestionnaire(Quest quest);

    void outputString(String s);

    void printString(String s);

    void printOutputMessage(String studentName, int rightAnswersCount, Locale messageLocale);
}
