package com.arobs.project.repository.jdbc;

import com.arobs.project.entity.Employee;
import com.arobs.project.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Objects;

@Repository
public class EmployeeJDBCRepository implements EmployeeRepository {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public EmployeeJDBCRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Employee> findAll() {
        String sql = "select * from employees";

        return jdbcTemplate.query(sql, (rs, rowNum) ->
                new Employee(rs.getInt("employee_id"),
                        rs.getString("employee_name"),
                        rs.getString("employee_pass"),
                        rs.getString("employee_email"),
                        rs.getString("employee_role")));

    }

    public Employee insertEmployee(Employee employee) {
        String sql = "insert into employees(employee_name,employee_pass,employee_email, employee_role) values(?,md5(?),?,?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, employee.getEmployeeName());
            ps.setString(2, employee.getEmployeePass());
            ps.setString(3, employee.getEmployeeEmail());
            ps.setString(4, employee.getEmployeeRole());
            return ps;
        }, keyHolder);

        employee.setEmployeeId(Objects.requireNonNull(keyHolder.getKey()).intValue());
        return employee;
    }

    public String getPasswordById(int employeeId) {

        String sql = "select employee_pass from employees where employee_id=?";
        return jdbcTemplate.queryForObject(sql, new Object[]{employeeId}, String.class);
    }

    public Employee updatePassword(int employeeId, String employeePass) {
        String sql = "update employees set employee_pass=md5(?) where employee_id=?";
        jdbcTemplate.update(sql, employeePass, employeeId);
        return findById(employeeId);
    }

    public List<Employee> findByEmail(String employeeEmail) {
        String sql = "select * from employees where employee_email=?";
        return jdbcTemplate.query(sql, new Object[]{employeeEmail}, (rs, rowNum) ->
                new Employee(rs.getInt("employee_id"),
                        rs.getString("employee_name"),
                        rs.getString("employee_pass"),
                        rs.getString("employee_email"),
                        rs.getString("employee_role")));
    }


    public Employee findById(int employeeId) {
        String sql = "select * from employees where employee_id=?";
        return jdbcTemplate.queryForObject(sql, new Object[]{employeeId}, (rs, rowNum) ->
                new Employee(rs.getInt("employee_id"),
                        rs.getString("employee_name"),
                        rs.getString("employee_pass"),
                        rs.getString("employee_email"),
                        rs.getString("employee_role")));

    }

    public boolean deleteById(int employeeId) {
        String sql = "delete from employees where employee_id=?";
        return jdbcTemplate.update(sql, employeeId) != 0;
    }

}
