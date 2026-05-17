import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class addfeedback
 */
@WebServlet("/addfeedback")
public class addfeedback extends HttpServlet {
    
    private static final long serialVersionUID = 1L;

    /**
     * Default constructor.
     */
    public addfeedback() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // Load MySQL driver
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/disaster", "root", "jasjot1011");
            Statement statement = connection.createStatement();

            // Retrieve parameters from the request
            String name = request.getParameter("name");
            String phone_no = request.getParameter("phone_no");
            String address = request.getParameter("address");
            String feedback = request.getParameter("feedback");

            // Insert the feedback into the database
            String insertQuery = "INSERT INTO feedback(`name`, `phone_no`, `address`, `feedback`) VALUES ('" 
                + name + "', '" + phone_no + "', '" + address + "', '" + feedback + "')";
            int result = statement.executeUpdate(insertQuery);

            // Redirect based on the result
            if (result == 1) {
                response.sendRedirect("thank.html");
            } else {
                response.sendRedirect("feedback.html");
            }

            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("feedback.html");
        }
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
