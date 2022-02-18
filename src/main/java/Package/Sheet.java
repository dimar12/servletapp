package Package;

import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

public class Sheet extends HttpServlet {

    static final String JDBC_DRIVER = "org.postgresql.Driver";
    static final String DATABASE_URL = "jdbc:postgresql://localhost:5432/Employee_absence_sheet";
    static final String DATABASE_USER = "postgres";
    static final String DATABASE_PASSWORD = "1525";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter writer = resp.getWriter();
        String name = req.getParameter("name");
        String post = req.getParameter("post");
        String date = req.getParameter("date");
        String time = req.getParameter("time");
        String reason = req.getParameter("reason");
        String insertTableSQL = "INSERT INTO sheet" + "(fio, post, date,time,reason) " + "VALUES" + "('" + name + "','" + post + "'," + "to_date('" + date + "', 'yyyy-mm-dd'),'" + time + "','" + reason + "')";
        try {
            Class.forName(JDBC_DRIVER);
            Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);
            Statement statement = connection.createStatement();
            statement.executeUpdate(insertTableSQL);
            statement.close();
            connection.close();
            String title = "Form";
            writer.println("<html>" +
                    "<head><title>" + title + "</title></head>\n" +
                    "<body>" +
                    "<h2>Specialty: </h2>" + name +
                    "<h2>Post: </h2>" + post +
                    "<h2>Date: </h2>" + date +
                    "<h2>Time: </h2>" + time +
                    "<h2>Reason: </h2>" + reason
                    + "</body>" +
                    "</html>");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
