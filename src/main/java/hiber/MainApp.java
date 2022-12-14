package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      userService.add(new User("User5", "Lastname5", "user5@mail.ru", new Car("BMW", 5)));
      userService.add(new User("User6", "Lastname6", "user6@mail.ru", new Car("AUDI", 7)));
      userService.add(new User("User7", "Lastname7", "user7@mail.ru", new Car("INFINITY", 45)));
      userService.add(new User("User8", "Lastname8", "user8@mail.ru", new Car("MERCEDES", 320)));

      List<User> usersAndCars = userService.listUsers();
      for (User user : usersAndCars) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println("Car = "+user.getUserCar());
         System.out.println();
      }

      System.out.println(userService.getUser("AUDI", 7).toString());
      context.close();
   }
}
