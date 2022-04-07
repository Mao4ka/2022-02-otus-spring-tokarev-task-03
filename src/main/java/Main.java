import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import ru.otus.application.Application;

import java.util.Locale;

@SpringBootApplication(scanBasePackages = "ru.otus")
public class Main {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(Main.class, args);
        Application application = context.getBean(Application.class);
        application.studentSurvey();

        //System.out.println(Locale.getDefault());

//        Locale locale = new Locale("");
//        //locale.
//        Locale.
    }

}

