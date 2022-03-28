package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    private Connection connection;

    public UserDaoJDBCImpl() {
    }

    public void createUsersTable() {
        try {
            PreparedStatement ps = connection.prepareStatement("CREATE TABLE IF NOT EXISTS user"
                    + "("
                    + " id serial,"
                    + " name varchar(40) NOT NULL,"
                    + " lastname varchar(40) NOT NULL,"
                    + " age numeric(3) NOT NULL,"
                    + "PRIMARY KEY (id)"
                    + ")"
            );
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try {
            PreparedStatement ps = connection.prepareStatement("DROP TABLE IF EXISTS user");
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO user (name, lastname, age) VALUES (?,?,?)");
            ps.setString(1, name);
            ps.setString(2, lastName);
            ps.setInt(3, age);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        try {
            PreparedStatement ps = connection.prepareStatement("DELETE FROM user WHERE id = ?");
            ps.setInt(1, (int) id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();

        try {
            PreparedStatement ps = connection.prepareStatement("SELECT id, name, lastname, age FROM USER");
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                User user = new User(rs.getString(2), rs.getString(3), (byte) rs.getInt(4));
                user.setId(rs.getLong(1));
                list.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void cleanUsersTable() {
        try {
            Statement st = connection.createStatement();
            st.executeUpdate("TRUNCATE user");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
