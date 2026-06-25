package jdbcapp;

import java.io.*;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/addUser")
public class MainServlet extends HttpServlet {
    
    private UserDAO userDAO = new UserDAO();
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        // DEBUG: Print all parameters
        System.out.println("=== FORM SUBMITTED ===");
        System.out.println("firstName: " + request.getParameter("firstName"));
        System.out.println("lastName: " + request.getParameter("lastName"));
        System.out.println("age: " + request.getParameter("age"));
        
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String ageStr = request.getParameter("age");
        
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head><title>Save Result</title>");
        out.println("<style>");
        out.println("body { font-family: Arial, sans-serif; background-color: #0a0a0a; color: #e8e8e8; display: flex; justify-content: center; align-items: center; height: 100vh; margin: 0; }");
        out.println(".container { background-color: #1a1a1a; padding: 30px; border-radius: 12px; border: 1px solid #2a2a2a; width: 450px; }");
        out.println("h2 { color: #f0a030; text-align: center; }");
        out.println(".message { color: #66d9d9; text-align: center; margin-top: 10px; font-weight: bold; }");
        out.println(".error { color: #ff6666; text-align: center; margin-top: 10px; font-weight: bold; }");
        out.println("a { color: #88b8ff; display: block; text-align: center; margin-top: 20px; }");
        out.println("</style>");
        out.println("</head>");
        out.println("<body>");
        out.println("<div class='container'>");
        out.println("<h2>Save Result</h2>");
        
        // Check if parameters are null
        if (firstName == null || lastName == null || ageStr == null || 
            firstName.trim().isEmpty() || lastName.trim().isEmpty() || ageStr.trim().isEmpty()) {
            out.println("<p class='error'>❌ All fields are required!</p>");
            System.out.println("ERROR: Missing fields!");
        } else {
            try {
                int age = Integer.parseInt(ageStr);
                System.out.println("Parsed age: " + age);
                boolean success = userDAO.addUser(firstName.trim(), lastName.trim(), age);
                System.out.println("Add user result: " + success);
                if (success) {
                    out.println("<p class='message'>✅ User saved successfully to database!</p>");
                } else {
                    out.println("<p class='error'>❌ Failed to add user. Please try again.</p>");
                }
            } catch (NumberFormatException e) {
                out.println("<p class='error'>❌ Age must be a valid number!</p>");
                System.out.println("ERROR: Invalid age format!");
                e.printStackTrace();
            } catch (Exception e) {
                out.println("<p class='error'>❌ Error: " + e.getMessage() + "</p>");
                System.out.println("ERROR: " + e.getMessage());
                e.printStackTrace();
            }
        }
        
        out.println("<a href='/index.html'>Back to Registration</a>");
        out.println("</div>");
        out.println("</body>");
        out.println("</html>");
        out.close();
    }
}
