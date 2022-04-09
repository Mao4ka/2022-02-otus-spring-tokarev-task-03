package ru.otus.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import ru.otus.AbstractConfigurationTest;
import ru.otus.AnswerType;
import ru.otus.dao.entity.Quest;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootApplication
@SpringBootTest
class QuestionnaireServiceImplTest extends AbstractConfigurationTest {

    @Autowired
    private QuestionnaireService questionnaireService;

    @Test
    void getAnswerTypeWhenCorrectAnswer() {
        Quest quest = new Quest("quest", Arrays.asList("answer_1", "answer_2", "answer_3", "answer_4"), "1");
        String userData = "1";

        AnswerType checkedAnswerType = questionnaireService.getAnswerType(quest, userData);

        assertThat(AnswerType.correctAnswer).isEqualTo(checkedAnswerType);
    }

    @Test
    void getAnswerTypeWhenIncorrectAnswer() {
        Quest quest = new Quest("quest", Arrays.asList("answer_1", "answer_2", "answer_3", "answer_4"), "1");
        String userData = "3";

        AnswerType checkedAnswerType = questionnaireService.getAnswerType(quest, userData);

        assertThat(AnswerType.incorrectAnswer).isEqualTo(checkedAnswerType);
    }

    @Test
    void getAnswerTypeWhenInCorrectData() {
        Quest quest = new Quest("quest", Arrays.asList("answer_1", "answer_2", "answer_3", "answer_4"), "1");
        String userData = "5";

        AnswerType checkedAnswerType = questionnaireService.getAnswerType(quest, userData);

        assertThat(AnswerType.incorrectInputData).isEqualTo(checkedAnswerType);
    }
}