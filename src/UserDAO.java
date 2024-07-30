import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {


    public void addUser(User user) throws SQLException{
        String sql = "INSERT INTO users (username, password) VALUES (?, ?)";

        try(Connection conn = DatabseConnection.getConnection()){
            PreparedStatement preparedStatement = conn.prepareStatement(sql);

            
            preparedStatement.setString(1, user.getUserName());
            preparedStatement.setString(2, user.getPassword());

            preparedStatement.executeUpdate();


            
        }
    }

    public User getUserByUsername(String username) throws SQLException {
        String sql = "SELECT * FROM users WHERE username = ?";
        try(Connection conn = DatabseConnection.getConnection()){
            PreparedStatement preparedStatement = conn.prepareStatement(sql);

            preparedStatement.setString(1, username);

            try (ResultSet rs = preparedStatement.executeQuery()){
                if (rs.next()) {
                    return new User(
                            rs.getInt("userId"),
                            rs.getString("username"),
                            rs.getString("password")   
                    );
                }
            }
        }
        return null;    
    }

    public void deleteUser(String userName) throws SQLException {
        String sql = "DELETE FROM users WHERE userName = ?";

        try(Connection conn = DatabseConnection.getConnection();
        PreparedStatement pStatement = conn.prepareStatement(sql)){
            pStatement.setString(1, userName);
            pStatement.executeUpdate();
        }
    }
    
    
}
