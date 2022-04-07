package ru.otus.enterprise;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.config.ApplicationCheckConfig;
import ru.otus.dao.entity.Quest;
import ru.otus.service.MessageService;

import java.util.Locale;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicReference;

@Service
@RequiredArgsConstructor
public class IOQuestionnaireImpl implements InputQuestionnaire, OutputQuestionnaire {

    private final ApplicationCheckConfig applicationCheckConfig;

    private final MessageService messageService;

    @Override
    public String getUserInput() {
        Scanner sc = new Scanner(System.in);
        return sc.next();
    }

    @Override
    public void outputString(String s) {
        System.out.println(s);
    }

    @Override
    public void printString(String s) {
        System.out.print(s);
    }

    public void printQuestionnaire(Quest quest) {
        outputString(createOutputMessage(quest));
    }

    @Override
    public void printOutputMessage(String studentName, int rightAnswersCount, Locale messageLocale) {
        outputString(messageService.getMessageN("printOutputMessage.endOfTesting", messageLocale));
        outputString(messageService.getMessage("printOutputMessage.resultCount", messageLocale) + " = " + rightAnswersCount);

        if (rightAnswersCount < applicationCheckConfig.getMinimumAcceptableCorrectAnswersCount()) {
            outputString(studentName + ", " + messageService.getMessageN("printOutputMessage.WhoAreYouNegative", messageLocale));
            outputString(messageService.getMessage("printOutputMessage.resultConclusionNegative", messageLocale));
            outputString(messageService.getMessage("printOutputMessage.resultConclusionNegativeAdditional", messageLocale));
        } else {
            outputString(studentName + ", " + messageService.getMessageN("printOutputMessage.WhoAreYouPositive", messageLocale));
            outputString(messageService.getMessage("printOutputMessage.resultConclusionPositive1", messageLocale));
            outputString(messageService.getMessage("printOutputMessage.resultConclusionPositive2", messageLocale));
            outputString(messageService.getMessage("printOutputMessage.resultConclusionPositiveAdditional", messageLocale));
        }
    }
// printOutputMessage.WhoAreYouNegative
    private String createOutputMessage(Quest quest) {
        AtomicReference<String> outputMessage = new AtomicReference<>(quest.getQuestion());
        quest.getAnswers().forEach(answer -> outputMessage.set(outputMessage + addPrefix(answer)));
        return outputMessage + "\n";
    }

    private String addPrefix(String message) {
        return "\n    - " + message;
    }

}
