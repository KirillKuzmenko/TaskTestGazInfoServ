package dao;

import config.ConfigJdbc;
import model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;

public class UserDaoJdbc implements UserDao {

    private Connection con = null;
    static final Logger logger = LogManager.getRootLogger();

    public UserDaoJdbc() {
        try {
            this.con = ConfigJdbc.getConnection();
        } catch (Exception e) {
            logger.error(e.getStackTrace());
        }
    }

    public UserDaoJdbc(Connection connection) {
        try {
            this.con = connection;
        } catch (Exception e) {
            logger.error(e.getStackTrace());
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
            logger.error(sqlException.getStackTrace());
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
            logger.error(sqlException.getStackTrace());
        }
    }
}
