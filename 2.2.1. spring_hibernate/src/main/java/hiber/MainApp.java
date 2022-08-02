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

      User user1 = new User("User1", "Lastname1", "user1@mail.ru");
      Car car1 = new Car("Car1",111);


      User user2 = new User("User2", "Lastname2", "user2@mail.ru");
      Car car2 = new Car("Car2",222);

      userService.add(user1.setCar(car1).setUser(user1));
      userService.add(user2.setCar(car2).setUser(user2));

      List<User> users = userService.listUsers();
      for (User user: users) {
         System.out.println("-  -  -  -  -  -  -  -  -");
         System.out.println(user + " " +user.getCar());
         System.out.println("- - - - - - - - - - - - - ");
      }


      System.out.println(userService.getFrom("Car2", 222));
      //Раскомментировать для проверки поиска по автомобилю, не связанному ни с один пользователем!
//      System.out.println(userService.getUserFromCarModelAndSeries("Car3", 333));

//      userService.add(new User("User1", "Lastname1", "user1@mail.ru"));
//      userService.add(new User("User2", "Lastname2", "user2@mail.ru"));
//      userService.add(new User("User3", "Lastname3", "user3@mail.ru"));
//      userService.add(new User("User4", "Lastname4", "user4@mail.ru"));
//
//      List<User> users = userService.listUsers();
//      for (User user : users) {
//         System.out.println("Id = "+user.getId());
//         System.out.println("First Name = "+user.getFirstName());
//         System.out.println("Last Name = "+user.getLastName());
//         System.out.println("Email = "+user.getEmail());
//         System.out.println();


      context.close();
   }
}
