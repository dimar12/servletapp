package Servlets;

import Entities.Sheet;

import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

public class FormServlet extends HttpServlet {

    static final String JDBC_DRIVER = "org.postgresql.Driver";
    static final String DATABASE_URL = "jdbc:postgresql://localhost:5432/Employee_absence_sheet";
    static final String DATABASE_USER = "postgres";
    static final String DATABASE_PASSWORD = "1525";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String selectNameSQL = "select fio from name";
        String selectPostSQL = "select post from post";
        ArrayList<String> names = new ArrayList<String>();
        ArrayList<String> posts = new ArrayList<String>();
        try {
            Class.forName(JDBC_DRIVER);
            Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(selectNameSQL);
            while (rs.next()) {
                names.add(rs.getString("fio"));
            }
            rs = statement.executeQuery(selectPostSQL);
            while (rs.next()) {
                posts.add(rs.getString("post"));
            }
            statement.close();
            connection.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        req.setAttribute("names",names);
        req.setAttribute("posts",posts);
        getServletContext().getRequestDispatcher("/form.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Sheet sheet = new Sheet(req.getParameter("name"),req.getParameter("post"),req.getParameter("date"),req.getParameter("time"),req.getParameter("reason"));
        String insertTableSQL = "INSERT INTO sheet" + "(fio, post, date,time,reason) " + "VALUES" + "('" + sheet.getName() + "','" + sheet.getPost() + "'," + "to_date('" + sheet.getDate() + "', 'yyyy-mm-dd'),'" + sheet.getTime() + "','" + sheet.getReason() + "')";
        try {
            Class.forName(JDBC_DRIVER);
            Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);
            Statement statement = connection.createStatement();
            statement.executeUpdate(insertTableSQL);
            statement.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        doGet(req,resp);
    }
}
