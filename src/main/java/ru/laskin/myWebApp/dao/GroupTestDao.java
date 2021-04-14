package ru.laskin.myWebApp.dao;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.laskin.myWebApp.model.GroupTest;

import java.util.List;

@Component
public class GroupTestDao {
    private JdbcTemplate jdbcTemplate;
    private BeanPropertyRowMapper<GroupTest> groupTestRowMapper = new BeanPropertyRowMapper<>(GroupTest.class);

    public GroupTestDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<GroupTest> getAllGroupTest(){
        List<GroupTest> list = jdbcTemplate.query("SELECT * FROM group_test", groupTestRowMapper);
        return jdbcTemplate.query("SELECT * FROM group_test", groupTestRowMapper);
    }
}
