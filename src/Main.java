import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    
    private static AnimalService animalService = new AnimalService();
    private static UserService userService = new UserService();

    public static void main(String[] args) throws SQLException {

        Animal animal = new Animal("Test", "Test", "Test");

        User jordan = new User("Jordan", "Jordan");

        // animalService.addAnimal(animal);

        // userService.registerUser(jordan);

        // userService.authUser("Jordan", "Jordan");

        Scanner sc = new Scanner(System.in);

        int choice = 0;

        do {
            System.out.println("Welcome To Our Aplication!");
            System.out.println("What Would You Like To Do?");
            System.out.println("1: Create A New User");
            System.out.println("2: Login The User");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    registerUser(sc);
                    break;
                case 2:
                    loginUser(sc);
                    break;

                default:
                    break;
            }
        } while (choice != 2);
        {

        }

    }

    private static void loginUser(Scanner sc) throws SQLException {

        System.out.println("Logging In The The System......");
        System.out.println("Enter Username");
        String userName = sc.next();
        System.out.println("Enter The Password: ");
        String password = sc.next();

        User user = userService.authUser(userName, password);

        if(user != null){
            System.out.println("Welcome: " + user.getUserName());

            if(user.getUserName().equals("Jamie")){
                jamieDashboard(sc,user);
            }
        }

        
    
    }

    

    
    

  private static void jamieDashboard(Scanner sc, User user) throws SQLException {
        System.out.println("Welcome To Jamie Dashboard!");

        System.out.println("Press 1 To Delete Jordan");

        int choice = sc.nextInt();

        if(choice == 1){
            userService.deleteUser("Jordan");
        } else{
            return;
        }

    }

  private static void registerUser(Scanner sc) throws SQLException {

        System.out.println("Adding A New User.... ");
        System.out.println("Enter The Username");
        String userName = sc.next();
        System.out.println("Enter The Password: ");
        String password = sc.next();

        User user = new User(userName, password);
        userService.registerUser(user);

    }
}