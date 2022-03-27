package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        UserService us = new UserServiceImpl();
        us.createUsersTable();
        us.saveUser("Барт", "Симпсон", (byte) 10);
        us.saveUser("Лиза", "Симпсон", (byte) 8);
        us.saveUser("Гомер", "Симпсон", (byte) 66);
        us.saveUser("Марта", "Симпсон", (byte) 34);
        us.getAllUsers().forEach(System.out::println);
        us.cleanUsersTable();
        us.dropUsersTable();

        Util util = new Util();
        util.closeConnection(util.getConnection());
    }
}
