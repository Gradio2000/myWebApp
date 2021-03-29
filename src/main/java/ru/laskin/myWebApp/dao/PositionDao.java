package ru.laskin.myWebApp.dao;

import org.springframework.stereotype.Component;
import ru.laskin.myWebApp.model.Position;
import ru.laskin.myWebApp.utils.JdbsConnectionUtils;

import javax.management.Query;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Set;
import java.util.TreeSet;

@Component
public class PositionDao {

    private static Connection connection;

    static {
        try {
            connection = JdbsConnectionUtils.getConnection();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public Set<String> getAllPosition(){
        Statement statement;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from positions");

            Set<String> positionSet = new TreeSet<>();
            while (resultSet.next()){
                String pos = resultSet.getString("position");
                positionSet.add(pos);
            }
            return positionSet;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

}
