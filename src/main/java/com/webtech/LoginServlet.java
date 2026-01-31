package com.webtech;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        
        if (username == null) {
            username = "";
        }
        if (password == null) {
            password = "";
        }

        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html><head><title>Login Result</title>");
        out.println("<style>body{font-family:sans-serif;padding:2rem;max-width:500px;margin:auto;}");
        out.println(".result{padding:1rem;border-radius:8px;margin:1rem 0;}");
        out.println(".welcome{background:#d4edda;color:#155724;}");
        out.println(".weak{background:#fff3cd;color:#856404;}");
        out.println("a{color:#007bff;}</style></head><body>");

        if (password.length() < 8) {
            out.println("<div class='result weak'>");
            out.println("Hello " + escapeHtml(username) + ", your password is weak. Try a strong one.");
            out.println("</div>");
        } else {
            out.println("<div class='result welcome'>");
            out.println("Welcome " + escapeHtml(username));
            out.println("</div>");
        }

        out.println("<p><a href='login.jsp'>Back to Login</a></p>");
        out.println("</body></html>");
        out.close();
    }

    private String escapeHtml(String s) {
        if (s == null) return "";
        return s.replace("&", "&amp;")
                .replace("<", "&lt;")
                .replace(">", "&gt;")
                .replace("\"", "&quot;");
    }
}
