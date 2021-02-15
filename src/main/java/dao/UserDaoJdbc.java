package dao;

import config.ConfigJdbc;
import model.User;

import java.sql.*;

public class UserDaoJdbc implements UserDao {

    private Connection con = null;

    public UserDaoJdbc() {
        try {
            con = ConfigJdbc.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public User getUserByName(String name) {

        User user = new User();

        String str = "SELECT Id, Name, LastName FROM users u WHERE TRIM(LOWER(u.name)) LIKE TRIM(LOWER(?)) LIMIT 1";

        try (PreparedStatement preparedStatement = con.prepareStatement(str)) {
            preparedStatement.setString(1, name);
            ResultSet result = preparedStatement.executeQuery();

            while (result.next()){
                user.setId(result.getLong("id"));
                user.setName(result.getString("name"));
                user.setLastName(result.getString("lastname"));
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

        return user;
    }

    @Override
    public void editLastName(User user) {

        String str = "UPDATE users SET lastname=? WHERE name = ?";

        try (PreparedStatement preparedStatement = con.prepareStatement(str)) {

            preparedStatement.setString(1, user.getLastName());
            preparedStatement.setString(2, user.getName());
            preparedStatement.execute();

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }
}
