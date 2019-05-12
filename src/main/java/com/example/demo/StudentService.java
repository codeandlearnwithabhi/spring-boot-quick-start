package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;

@Service
public class StudentService {

    private NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public StudentService(NamedParameterJdbcTemplate jdbcTemplate) {

        this.jdbcTemplate = jdbcTemplate;
    }

    public long addStudent(Student student) {

        String query = "INSERT INTO STUDENTS(first_name, last_name) " +
                "values(:firstName, :lastName)";

        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("firstName", student.getFirstName());
        namedParameters.addValue("lastName", student.getLastName());

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(query, namedParameters, keyHolder);

        return keyHolder.getKey().longValue();
    }

    public Student getStudent(long studentId) {

        String query = "select id, first_name, last_name from students where id = :studentId";

        MapSqlParameterSource namedParameter = new MapSqlParameterSource();
        namedParameter.addValue("studentId", studentId);

        return jdbcTemplate.queryForObject(query, namedParameter, new StudentRowMapper());
    }

    public void updateStudent(Student student) {

        String query = "UPDATE STUDENTS SET first_name = :firstName, last_name = :lastName WHERE id = :studentId";

        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("studentId", student.getId());
        namedParameters.addValue("firstName", student.getFirstName());
        namedParameters.addValue("lastName", student.getLastName());

        jdbcTemplate.update(query, namedParameters);
    }

    public void deleteStudent(long studentId) {

        String query = "DELETE FROM STUDENTS WHERE id = :studentId";

        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("studentId", studentId);

        jdbcTemplate.update(query, namedParameters);
    }
}

class StudentRowMapper implements RowMapper<Student> {

    @Override
    public Student mapRow(ResultSet resultSet, int i) throws SQLException {

        Student student = new Student();
        student.setId(resultSet.getLong("id"));
        student.setFirstName(resultSet.getString("first_name"));
        student.setLastName(resultSet.getString("last_name"));

        return student;
    }
}
