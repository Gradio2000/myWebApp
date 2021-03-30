package ru.laskin.myWebApp.dao;

import org.springframework.stereotype.Component;
import ru.laskin.myWebApp.model.Test;
import ru.laskin.myWebApp.utils.JdbsConnectionUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Component
public class TestDao {
    private static Connection connection;

    static {
        try {
            connection = JdbsConnectionUtils.getConnection();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Test> getAllTests(){
        Statement statement;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from tests");
            List<Test> testList = new ArrayList<>();
            while (resultSet.next()){
                int id = resultSet.getInt("test_id");
                String testName = resultSet.getString("test_name");
                Test test = new Test(id, testName);
                testList.add(test);
            }
            return testList;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
}
