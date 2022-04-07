package ru.otus.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.AnswerType;
import ru.otus.config.ApplicationCheckConfig;
import ru.otus.dao.entity.Quest;

import java.util.Locale;

@Service
@RequiredArgsConstructor
public class QuestionnaireServiceImpl implements QuestionnaireService {

    private static final int MINIMUM_CORRECT_OPTION_NUMBER = 1;

    private final ApplicationCheckConfig applicationCheckConfig;

    @Override
    public Locale getApplicationLocale(String userLanguageChoise) {

        if ("1".equals(userLanguageChoise)) {
            return new Locale("ru_RU");
        } else {
            return new Locale("");
        }
    }

    @Override
    public AnswerType getAnswerType(Quest quest, String userData) {
        if (checkCorrectInputData(userData)) {
            boolean userAnswerIsRight = checkUserAnswer(userData, quest);
            return userAnswerIsRight ? AnswerType.correctAnswer : AnswerType.incorrectAnswer;
        } else {
            return AnswerType.incorrectInputData;
        }
    }

    private boolean checkUserAnswer(String userData, Quest quest) {
        return userData.equals(quest.getRightAnswer());
    }

    private boolean checkCorrectInputData(String userData) {
        try {
            int intValue = Integer.parseInt(userData);
            return intValue >= MINIMUM_CORRECT_OPTION_NUMBER && intValue <= applicationCheckConfig.getMaximumCorrectOptionNumber();
        } catch (Exception e) {
            return false;
        }
    }
}
