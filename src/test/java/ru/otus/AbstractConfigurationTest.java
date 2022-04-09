package ru.otus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import ru.otus.config.ApplicationCheckConfig;
import ru.otus.enterprise.InputQuestionnaire;
import ru.otus.enterprise.OutputQuestionnaire;

@ComponentScan(value = "ru.otus")
public abstract class AbstractConfigurationTest {

    @Autowired
    private ApplicationCheckConfig applicationCheckConfig;

    @MockBean
    private InputQuestionnaire inputQuestionnaire;

    @MockBean
    private OutputQuestionnaire outputQuestionnaire;


}
