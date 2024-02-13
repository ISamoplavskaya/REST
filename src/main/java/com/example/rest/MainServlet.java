package com.example.rest;

import java.io.*;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet("/users/*")
public class MainServlet extends HttpServlet {
    private final UserService userService = new UserService();
    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        String pathInfo = request.getPathInfo();
        String json = null;
        if (pathInfo == null || pathInfo.equals("/")) {
            List<User> allUsers = userService.getAll();
            json = mapper.writeValueAsString(allUsers);

        } else {
            int userId = Integer.parseInt(pathInfo.substring(1));
            User user = userService.getUser(userId);
            json = mapper.writeValueAsString(user);
        }
        response.getWriter().println(json);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader reader = request.getReader();
        String line;
        while ((line = reader.readLine()) != null) {
            stringBuilder.append(line);
        }
        reader.close();
        String json = stringBuilder.toString();
        User user = mapper.readValue(json, User.class);
        userService.save(user);
        response.getStatus();
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getPathInfo();
        if (pathInfo != null && !pathInfo.equals("/")) {
            int userId = Integer.parseInt(pathInfo.substring(1));
            userService.delete(userId);
        }
    }


}