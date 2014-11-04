package com.capstoneglass.backend;

import com.capstoneglass.backend.Models.PointOfInterest;
import com.capstoneglass.backend.Models.World;

import java.io.IOException;
import javax.servlet.http.*;

public class MyServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        resp.setContentType("text/plain");
        resp.getWriter().println("Please use the form to POST to this url");
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        String id = (String) req.getParameter("id");
        resp.setContentType("text/plain");
        if(id == null) {
            resp.getWriter().println("Please enter a valid World Id");
        }

        World world = new World();
        world.Id = 1;
        world.Name = "Test";
        PointOfInterest poi = new PointOfInterest();
        poi.Id = 12;
        poi.Latitude = 12.233;
        poi.Longitude = -122.3;
        poi.Name = "POI1";
        world.PointsOfInterest.add(poi);

        String response = world.toJson();

        resp.getWriter().println(response);
    }
}