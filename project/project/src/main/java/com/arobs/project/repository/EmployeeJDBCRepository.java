package com.arobs.project.repository;

import com.arobs.project.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class EmployeeJDBCRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Employee> findAll() {
        String sql = "select * from employees";

        List<Employee> employeeList = jdbcTemplate.query(sql, (rs, rowNum) ->
                new Employee(rs.getInt("employee_id"),
                        rs.getString("employee_name"),
                        rs.getString("employee_pass"),
                        rs.getString("employee_email"),
                        rs.getString("employee_role")));

        return  employeeList;
    }

    public int insertEmployee(Employee employee){
        String sql="insert into employees(employee_name,employee_pass,employee_email, employee_role) values(?,md5(?),?,?)";

        KeyHolder keyHolder= new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, employee.getEmployeeName());
            ps.setString(2, employee.getEmployeePass());
            ps.setString(3, employee.getEmployeeEmail());
            ps.setString(4, employee.getEmployeeRole());
            return ps;
        }, keyHolder );

        return keyHolder.getKey().intValue();
    }

    public String getPasswordByEmail(String employeeEmail){

        String sql="select employee_pass from employees where employee_email=?";
        String oldPassword= jdbcTemplate.queryForObject(sql,new Object[]{employeeEmail},String.class);
        return oldPassword;

    }

    public void updatePassword(String employeeEmail, String employeePass){
        String sql="update employees set employee_pass=md5(?) where employee_email=?";
        jdbcTemplate.update(sql,employeePass,employeeEmail);
    }

    public void deleteByEmail(String employeeEmail){
        String sql= "delete from employees where employee_email=?";
        jdbcTemplate.update(sql,employeeEmail);
    }




}
