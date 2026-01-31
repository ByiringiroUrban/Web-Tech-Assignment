package com.webtech;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;


public class RedirectServlet extends HttpServlet {

    private static final String GOOGLE_SEARCH_URL = "https://www.google.com/search";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String query = request.getParameter("query");
        if (query == null) {
            query = "";
        }

        String encodedQuery = URLEncoder.encode(query.trim(), StandardCharsets.UTF_8);
        String redirectUrl = GOOGLE_SEARCH_URL + "?q=" + encodedQuery;

        response.sendRedirect(redirectUrl);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

                String query = request.getParameter("query");
        if (query == null) {
            query = "";
        }

        String encodedQuery = URLEncoder.encode(query.trim(), StandardCharsets.UTF_8);
        String redirectUrl = GOOGLE_SEARCH_URL + "?q=" + encodedQuery;

        response.sendRedirect(redirectUrl);
    }
}
