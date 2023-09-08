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

@WebServlet("/user/planes/new")
public class InsertController extends HttpServlet {
    private final PlaneService planeService = new PlaneService(new PlaneRepository());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/user/planes/new.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Plane plane = mapToPlane(req);

        planeService.addPlane(plane);

        resp.sendRedirect(req.getContextPath() + "/user/planes");
    }

    private Plane mapToPlane(HttpServletRequest req) {
        return Plane.builder()
                .mark(req.getParameter("mark"))
                .model(req.getParameter("model"))
                .type(req.getParameter("type"))
                .mileage(Integer.parseInt(req.getParameter("mileage")))
                .price(Integer.parseInt(req.getParameter("price")))
                .idProfile((Long) (req.getSession().getAttribute("idProfile")))
                .build();
    }
}
