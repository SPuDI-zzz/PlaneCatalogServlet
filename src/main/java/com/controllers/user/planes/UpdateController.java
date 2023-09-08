package com.controllers.user.planes;

import com.entity.Plane;
import com.repository.PlaneRepository;
import com.service.PlaneService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/user/planes/edit")
public class UpdateController extends HttpServlet {
    private final PlaneService planeService = new PlaneService(new PlaneRepository());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long idPlane = Long.parseLong(req.getParameter("idPlane"));
        Long idProfile = (Long) req.getSession().getAttribute("idProfile");

        if (planeService.canModifyPlane(idProfile, idPlane)) {
            req.setAttribute("plane", planeService.getPlane(idPlane));
            req.getRequestDispatcher("/WEB-INF/user/planes/edit.jsp").forward(req, resp);
            return;
        }

        req.getRequestDispatcher("/error/404.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Plane plane = mapToPlane(req);

        planeService.updatePlane(plane);

        resp.sendRedirect(req.getContextPath() + "/user/planes");
    }

    private Plane mapToPlane(HttpServletRequest req) {
        return Plane.builder()
                .id(Long.parseLong(req.getParameter("idPlane")))
                .mark(req.getParameter("mark"))
                .model(req.getParameter("model"))
                .type(req.getParameter("type"))
                .mileage(Integer.parseInt(req.getParameter("mileage")))
                .price(Integer.parseInt(req.getParameter("price")))
                .idProfile((Long) (req.getSession().getAttribute("idProfile")))
                .build();
    }
}
