package ru.otus.enterprise;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.config.ApplicationCheckConfig;
import ru.otus.config.ApplicationSourceConfig;
import ru.otus.dao.entity.Quest;

import java.util.Scanner;
import java.util.concurrent.atomic.AtomicReference;

@Service
@RequiredArgsConstructor
public class IOQuestionnaireImpl implements InputQuestionnaire, OutputQuestionnaire {

    private final ApplicationCheckConfig applicationCheckConfig;

    @Override
    public String getUserInput() {
        Scanner sc = new Scanner(System.in);
        return sc.next();
    }

    @Override
    public String greeting() {
        outputString("It's application for checking you erudition, premonition, foresight etc.");
        outputString("Please introduce yourself: \n");

        String userName = getUserInput();

        outputString("\nHello, " + userName + "!\n");
        outputString("Now it is necessary to check your erudition and foresight.");
        outputString("After each question write the correct answer option number. From 1 till 4. " +
                "\n(aside) For the particularly stupid...");
        outputString("\nStart testing:");

        return userName;
    }

    @Override
    public void outputString(String s) {
        System.out.println(s);
    }

    public void printQuestionnaire(Quest quest) {
        outputString(createOutputMessage(quest));
    }

    @Override
    public void printOutputMessage(String studentName, int rightAnswersCount) {
        outputString("End of testing!\nCount of your right answers = " + rightAnswersCount);

        if (rightAnswersCount < applicationCheckConfig.getMinimumAcceptableCorrectAnswersCount()) {
            outputString(studentName + ", you are {Balbes Men}! Go and study {MatChast}! 0_o");
        } else {
            outputString(studentName + ", you are {Chetkiy Patcanchik}! Keep it up!!!!");
        }
    }

    private String createOutputMessage(Quest quest) {
        AtomicReference<String> outputMessage = new AtomicReference<>(quest.getQuestion());
        quest.getAnswers().forEach(answer -> outputMessage.set(outputMessage + addPrefix(answer)));
        return outputMessage + "\n";
    }

    private String addPrefix(String message) {
        return "\n    - " + message;
    }


}
