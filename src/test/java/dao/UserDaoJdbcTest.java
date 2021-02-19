package dao;


import model.User;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

class UserDaoJdbcTest {

    ResultSet resultSet;
    PreparedStatement preparedStatement;
    UserDao userDao;
    Connection connection;

    @Test
    public void getUserByNameTest() throws SQLException{

        resultSet = mock(ResultSet.class);
        preparedStatement = mock(PreparedStatement.class);
        connection = mock(Connection.class);
        userDao = new UserDaoJdbc(connection);

        User user = new User(1L, "kirill", "kirill");
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(Boolean.TRUE, Boolean.FALSE);
        when(resultSet.getLong("id")).thenReturn(1L);
        when(resultSet.getString("name")).thenReturn("kirill");
        when(resultSet.getString("lastname")).thenReturn("kirill");

        assertEquals(userDao.getUserByName("kirill"), user);
        verify(connection, times(1)).prepareStatement(anyString());
        verify(preparedStatement, times(1)).executeQuery();
        verify(preparedStatement, times(1)).setString(anyInt(),anyString());
        verify(preparedStatement, times(1)).close();
        verify(resultSet, times(2)).next();
        verify(resultSet, times(1)).getLong(anyString());
        verify(resultSet, times(2)).getString(anyString());
        verifyNoMoreInteractions(preparedStatement);
        verifyNoMoreInteractions(connection);
        verifyNoMoreInteractions(resultSet);
    }

    @Test
    public void editLastNameTest() throws SQLException {

        preparedStatement = mock(PreparedStatement.class);
        connection = mock(Connection.class);
        userDao = new UserDaoJdbc(connection);

        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(preparedStatement.execute()).thenReturn(Boolean.TRUE);

        userDao.editLastName(new User(1L, "kirill", "kirill"));

        verify(preparedStatement, times(1)).execute();
        verify(preparedStatement, times(2)).setString(anyInt(), anyString());
        verify(preparedStatement, times(1)).close();
        verify(connection, times(1)).prepareStatement(anyString());

        verifyNoMoreInteractions(connection);
        verifyNoMoreInteractions(preparedStatement);
    }
}