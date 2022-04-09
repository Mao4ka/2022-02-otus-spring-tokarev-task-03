package ru.otus.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.otus.config.ApplicationCheckConfig;
import ru.otus.enterprise.InputQuestionnaire;
import ru.otus.enterprise.OutputQuestionnaire;

import java.util.Locale;

@Component
@RequiredArgsConstructor
public class StartApplicationImpl implements StartApplication {

    private final OutputQuestionnaire outputQuestionnaire;

    private final InputQuestionnaire inputQuestionnaire;

    private final ApplicationCheckConfig applicationCheckConfig;

    private final MessageService messageService;

    @Override
    public String defineLanguage() {
        outputQuestionnaire.printString("\nThis application works in English by default.\n" +
                "Do you want to take the test in Russian?\n" +
                "If you want - press simbol \"1\", else - enter any other simbol.\n" +
                "Your choice : ");

        return inputQuestionnaire.getUserInput();
    }

    @Override
    public String greeting(Locale messageLocale) {

        outputQuestionnaire.outputString(messageService.getNMessage("greeting.description", messageLocale));
        outputQuestionnaire.printString(messageService.getMessage("greeting.introduce", messageLocale));

        String userName = inputQuestionnaire.getUserInput();

        outputQuestionnaire.outputString(messageService.getNMessage("greeting.hello", messageLocale) + ", " + userName + "!\n");
        outputQuestionnaire.outputString(messageService.getMessage("greeting.nowNecessaryCheck", messageLocale));
        outputQuestionnaire.outputString(messageService.getMessage("greeting.manual", messageLocale) +
                applicationCheckConfig.getMaximumCorrectOptionNumber() + "." +
                messageService.getMessage("greeting.manualAddidional", messageLocale));
        outputQuestionnaire.outputString(messageService.getMessage("greeting.startTesting", messageLocale) + ":\n");

        return userName;
    }

}
