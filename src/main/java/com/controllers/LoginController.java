package com.controllers;

import com.dto.ProfileDTO;
import com.repository.ProfileRepository;
import com.service.ProfileService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/login")
public class LoginController extends HttpServlet {
    private final ProfileService profileService = new ProfileService(new ProfileRepository());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("WEB-INF/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        ProfileDTO profileDTO = profileService.login(login, password);

        if (!isProfileDTOExists(profileDTO)) {
            req.setAttribute("error", "Incorrect login or password");
            req.getRequestDispatcher("WEB-INF/login.jsp").forward(req, resp);
            return;
        }

        req.getSession().setAttribute("idProfile", profileDTO.getId());
        req.getSession().setAttribute("login", profileDTO.getLogin());
        req.getSession().setAttribute("logged", true);

        resp.sendRedirect(req.getContextPath()+"/user/planes");
    }

    private boolean isProfileDTOExists(ProfileDTO profileDTO) {
        return profileDTO != null;
    }
}
