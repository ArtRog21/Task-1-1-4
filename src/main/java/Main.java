import service.UserService;
import service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {

        UserService userService = new UserServiceImpl();
        userService.createUsersTable();

        userService.saveUser("Konstantin", "Petrov", (byte) 25);
        userService.saveUser("Alexey", "Konovalov", (byte) 31);
        userService.saveUser("Ivan", "Prytko", (byte) 44);
        userService.saveUser("Elena", "Zaytseva", (byte) 23);
        System.out.println(userService.getAllUsers());

        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
