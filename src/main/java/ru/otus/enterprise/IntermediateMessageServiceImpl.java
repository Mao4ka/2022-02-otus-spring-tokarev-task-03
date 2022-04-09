package ru.otus.enterprise;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.AnswerType;
import ru.otus.service.MessageService;

import java.util.Locale;

@Service
@RequiredArgsConstructor
public class IntermediateMessageServiceImpl implements IntermediateMessageService {

    private final OutputQuestionnaire outputQuestionnaire;

    private final MessageService messageService;

    @Override
    public void commentUserAnswer(AnswerType answerType, Locale messageLocale) {
        if (answerType.equals(AnswerType.correctAnswer)) {
            outputQuestionnaire.outputString(messageService.getMessageN("intermediate.correctAnswer", messageLocale));
        }

        if (answerType.equals(AnswerType.incorrectAnswer)) {
            outputQuestionnaire.outputString(messageService.getMessageN("intermediate.incorrectAnswer", messageLocale));
        }

        if (answerType.equals(AnswerType.incorrectInputData)) {
            outputQuestionnaire.outputString(messageService.getMessage("intermediate.incorrectInputData", messageLocale));
            outputQuestionnaire.outputString(messageService.getMessage("intermediate.incorrectInputDataAdditing1", messageLocale));
            outputQuestionnaire.outputString(messageService.getMessageN("intermediate.incorrectInputDataAdditing2", messageLocale));
        }
    }

}
