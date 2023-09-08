package com.controllers.user.planes;

import com.repository.PlaneRepository;
import com.service.PlaneService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/user/planes/remove")
public class DeleteController extends HttpServlet {
    private final PlaneService planeService = new PlaneService(new PlaneRepository());

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long idPlane = Long.parseLong(req.getParameter("idPlane"));
        Long idProfile = (Long) req.getSession().getAttribute("idProfile");

        if (planeService.canModifyPlane(idProfile, idPlane)) {
            planeService.removePlane(idPlane);
        }

        resp.sendRedirect(req.getContextPath() + "/user/planes");
    }
}
