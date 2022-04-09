package ru.otus.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.AnswerType;
import ru.otus.config.ApplicationSourceConfig;
import ru.otus.dao.entity.Quest;
import ru.otus.dao.repository.QuestRepository;
import ru.otus.enterprise.InputQuestionnaire;
import ru.otus.enterprise.OutputQuestionnaire;
import ru.otus.enterprise.IntermediateMessageService;
import ru.otus.service.QuestionnaireService;
import ru.otus.service.StartApplication;

import java.util.List;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@RequiredArgsConstructor
public class Application {

    public static Locale applicationLocal;

    private final QuestRepository questRepository;
    private final QuestionnaireService questionnaireService;
    private final OutputQuestionnaire outputQuestionnaire;
    private final InputQuestionnaire inputQuestionnaire;
    private final IntermediateMessageService intermediateMessageService;
    private final ApplicationSourceConfig applicationSourceConfig;
    private final StartApplication startApplication;

    public void studentSurvey() {
        applicationLocal = questionnaireService.getApplicationLocale(startApplication.defineLanguage());
        String studentName = startApplication.greeting(applicationLocal);
        int rightAnswersCount = processQuestionnaire();
        outputQuestionnaire.printOutputMessage(studentName, rightAnswersCount, applicationLocal);
    }

    private int processQuestionnaire() {
        List<Quest> questionnaire = questRepository.getQuestionnaire(applicationSourceConfig.getFileName(),
                applicationSourceConfig.getLineSeparator());
        AtomicInteger rightAnswerCount = new AtomicInteger();

        questionnaire.forEach(quest -> processQuest(rightAnswerCount, quest));

        return rightAnswerCount.get();
    }

    private void processQuest(AtomicInteger rightAnswerCount, Quest quest) {
        outputQuestionnaire.printQuestionnaire(quest);
        System.out.print("Your choice: ");

        String userData = inputQuestionnaire.getUserInput();

        AnswerType answerType = questionnaireService.getAnswerType(quest, userData);
        intermediateMessageService.commentUserAnswer(answerType, applicationLocal);
        rightAnswerCount.addAndGet(answerType.equals(AnswerType.correctAnswer) ? 1 : 0);
    }

}
