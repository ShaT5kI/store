package com.example.store;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class ProcedureCaller {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ProcedureCaller(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void call() {
        String sql = "SELECT hello_world()";
        System.out.println(jdbcTemplate.queryForObject(sql, String.class));
    }
}