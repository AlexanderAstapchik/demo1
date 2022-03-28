package com.example.demo1.servlet;


import com.example.demo1.school.Employee;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmplServlet extends HttpServlet {
    private static final String url = "jdbc:mysql://localhost:3306/school";
    private static final String user = "root";
    private static final String password = "root";
    private List<Employee> employees = new ArrayList<>();
    private static Connection connection;

    {
       employees.add(new Employee(1, "Ivan","Vorontsov",12,"Biology"));
        employees.add(new Employee(2, "Oleg","Evenko",4,"Chemistry"));
        employees.add(new Employee(3, "Vlad","Sakov",4,"Math"));

        try {
            Class.forName("com.mysql.jdbc.Driver");

            connection = DriverManager.getConnection(url, user, password);
            connection.setAutoCommit(false);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void init() {
        try {
            PreparedStatement statement = connection.prepareStatement("insert into employee values (?, ?,?,?,?)");
            for (Employee employee : employees) {
                statement.setLong(1, employee.getId());
                statement.setString(2, employee.getFirstName());
                statement.setString(3, employee.getLastName());
                statement.setLong(4, employee.getWorkExperience());
                statement.setString(5, employee.getSpecialization());
                statement.execute();
            }
            statement.close();
            connection.commit();
        } catch (SQLException e) {
            System.out.println("Something wrong in init!");
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        try {
            PrintWriter out = resp.getWriter();
            out.println("<html><body>");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from employee");
            out.println("<h1>" +"School employees:"+ "<br/>"+ "</h1>");
            while (resultSet.next()) {
                out.println("<h3>" +
                        "Employee id: " + resultSet.getLong(1) + "<br/>" +
                        " FirstName: " + resultSet.getString(2) + "<br/>" +
                        " LastName: " + resultSet.getString(3) + "<br/>" +
                        " WorkExperience: " + resultSet.getLong(4) + "<br/>" +
                        " Specialization: " + resultSet.getString(5) + ". " + "<br/>" +
                        "</h3>");
            }
            out.println("</body></html>");
            resultSet.close();
            statement.close();
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void destroy() {
        try {
            employees.clear();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
