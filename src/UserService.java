import java.sql.SQLException;

import org.mindrot.jbcrypt.BCrypt;

public class UserService {

    UserDAO userDAO = new UserDAO();

    public UserService(){
        userDAO = new UserDAO();
    }

    public boolean registerUser(User user) throws SQLException{
        if(user.equals(null)){
            System.out.println("User Is Null");
            return false;
        }
        String hashedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());

        User newUser = new User(0, user.getUserName(), hashedPassword);
        userDAO.addUser(newUser);
        System.out.println("User Created");


        return true;
    }


    public User authUser(String username,String password) throws SQLException{
        if(username == null || password == null){
            System.out.println("The User Does Not Exist");
        }

        User user = userDAO.getUserByUsername(username);

        if(user == null){
            System.out.println("The User Does Not Exist! ");
            return null;
        }

        if(!BCrypt.checkpw(password, user.getPassword())){
            System.out.println("Wrong Password, Please Try Again!");
            return null;
        }

        System.out.println("User Has Passed Auth");

        return user;
    }


    
}
