package com.mycompany.sign.in.web_app.resources;

import java.io.*;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/viewUsers")
public class ViewUsersServlet extends HttpServlet {
    
    private UserDAO userDAO = new UserDAO();
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        List<User> users = userDAO.getAllUsers();
        
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head><title>User List</title>");
        out.println("<style>");
        out.println("body { font-family: Arial, sans-serif; background-color: #0a0a0a; color: #e8e8e8; display: flex; justify-content: center; align-items: center; height: 100vh; margin: 0; }");
        out.println(".container { background-color: #1a1a1a; padding: 30px; border-radius: 12px; border: 1px solid #2a2a2a; width: 600px; }");
        out.println("h2 { color: #f0a030; text-align: center; }");
        out.println("table { width: 100%; border-collapse: collapse; margin-top: 20px; }");
        out.println("th, td { border: 1px solid #333; padding: 8px; text-align: left; }");
        out.println("th { background-color: #2a2a2a; color: #88bbff; }");
        out.println("a { color: #88b8ff; display: block; text-align: center; margin-top: 20px; }");
        out.println("</style>");
        out.println("</head>");
        out.println("<body>");
        out.println("<div class='container'>");
        out.println("<h2>User List</h2>");
        
        if (users.isEmpty()) {
            out.println("<p style='color:#66d9d9; text-align:center;'>No users found in database.</p>");
        } else {
            out.println("<table>");
            out.println("<tr><th>ID</th><th>First Name</th><th>Last Name</th><th>Age</th></tr>");
            for (User u : users) {
                out.println("<tr>");
                out.println("<td>" + u.getId() + "</td>");
                out.println("<td>" + u.getFirstName() + "</td>");
                out.println("<td>" + u.getLastName() + "</td>");
                out.println("<td>" + u.getAge() + "</td>");
                out.println("</tr>");
            }
            out.println("</table>");
        }
        
        out.println("<a href='/index.html'>Back to Registration</a>");
        out.println("</div>");
        out.println("</body>");
        out.println("</html>");
        out.close();
    }
}