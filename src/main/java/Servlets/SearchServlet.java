package Servlets;

import Entities.Sheet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;


public class SearchServlet extends HttpServlet {

    static final String JDBC_DRIVER = "org.postgresql.Driver";
    static final String DATABASE_URL = "jdbc:postgresql://localhost:5432/Employee_absence_sheet";
    static final String DATABASE_USER = "postgres";
    static final String DATABASE_PASSWORD = "1525";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ArrayList<Sheet> sheets = new ArrayList<Sheet>();
        req.setAttribute("sheets",sheets);
        getServletContext().getRequestDispatcher("/search.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String date = req.getParameter("date");
        String selectTableSQL;
        if (date.isEmpty()){
            selectTableSQL = "select * from sheet where fio='" + name + "'";
        } else if(name.isEmpty()) {
            selectTableSQL = "select * from sheet where date='" + date + "'";
        } else {
            selectTableSQL = "select * from sheet where fio='" + name + "' and date='" + date + "'";
        }
        ArrayList<Sheet> sheets = new ArrayList<Sheet>();
        try {
            Class.forName(JDBC_DRIVER);
            Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(selectTableSQL);
            while (rs.next()) {
                sheets.add(new Sheet(rs.getString("fio"),rs.getString("post"),rs.getString("date"),rs.getString("time"),rs.getString("reason")));
            }
            statement.close();
            connection.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        req.setAttribute("sheets",sheets);
        getServletContext().getRequestDispatcher("/search.jsp").forward(req,resp);
    }
}
