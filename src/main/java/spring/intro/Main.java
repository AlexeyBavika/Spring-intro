package spring.intro;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import spring.intro.config.ApplicationConfig;
import spring.intro.model.User;
import spring.intro.service.UserService;

public class Main {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext annotationConfigApplicationContext
                = new AnnotationConfigApplicationContext(ApplicationConfig.class);
        UserService userService = annotationConfigApplicationContext
                .getBean(UserService.class);
        userService.add(new User("Alex"));
        userService.add(new User("Matvey"));
        userService.add(new User("Vadim"));
        userService.add(new User("Egor"));
        userService.add(new User("Danylo"));
        userService.listUsers().forEach(System.out::print);
    }
}
