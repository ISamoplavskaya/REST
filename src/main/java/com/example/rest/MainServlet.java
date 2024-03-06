package com.example.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/users/*")
public class MainServlet extends HttpServlet {
    private final UserService userService = new UserService();
    private final ServletHelper servletHelper = new ServletHelper();
    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String pathInfo = request.getPathInfo();
        if (pathInfo == null || pathInfo.equals("/")) {
            servletHelper.writeJsonResponse(response, userService.getAll());
        } else {
            Long userId = Long.parseLong(pathInfo.substring(1));
            servletHelper.writeJsonResponse(response, userService.getUser(userId));
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String json = servletHelper.readRequestBody(request);
        User user = mapper.readValue(json, User.class);
        userService.save(user);
        response.setStatus(HttpServletResponse.SC_CREATED);
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) {
        String pathInfo = request.getPathInfo();
        if (pathInfo != null && !pathInfo.equals("/")) {
            Long userId = Long.parseLong(pathInfo.substring(1));
            userService.delete(userId);
            response.setStatus(HttpServletResponse.SC_NO_CONTENT);
        } else {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }


}