package com.controllers.user;

import com.repository.PlaneRepository;
import com.service.PlaneService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/user/planes")
public class PlanesController extends HttpServlet {
    private final PlaneService planeService = new PlaneService(new PlaneRepository());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long idProfile = (Long) req.getSession().getAttribute("idProfile");

        req.setAttribute("planeList", planeService.getPlaneList(idProfile));

        req.getRequestDispatcher("/WEB-INF/user/planes.jsp").forward(req, resp);
    }
}
