package ru.otus.enterprise;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.config.ApplicationCheckConfig;
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
