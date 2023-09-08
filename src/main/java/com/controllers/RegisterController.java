package com.controllers;

import com.entity.Profile;
import com.repository.ProfileRepository;
import com.service.ProfileService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/register")
public class RegisterController extends HttpServlet {
    ProfileService profileService = new ProfileService(new ProfileRepository());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");

        if (!profileService.canRegister(login)) {
            req.setAttribute("error", "User with this login already exists");
            req.getRequestDispatcher("/WEB-INF/register.jsp").forward(req, resp);
            return;
        }
        String password = req.getParameter("password");

        Profile profile = Profile.builder()
                .login(login)
                .password(password)
                .build();

        profileService.register(profile);

        resp.sendRedirect(req.getContextPath() + "/login");
    }
}
