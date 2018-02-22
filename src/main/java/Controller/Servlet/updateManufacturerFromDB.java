package Controller.Servlet;

import DAO.Implementation.HibernateDAOManufacturer;
import Model.Manufacturer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

@WebServlet("/updateManufacturerFromDB")
public class updateManufacturerFromDB extends HttpServlet {
    protected void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Manufacturer manufacturer = new Manufacturer();

        //id
        manufacturer.setId(UUID.fromString(req.getParameter("id").toString()));

        //name
        manufacturer.setManufactur_name(req.getParameter("manufacturerName"));


        HibernateDAOManufacturer hib = new HibernateDAOManufacturer();
        hib.update(manufacturer);

        //redirect
        resp.sendRedirect("index.jsp");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req,resp);
    }
}
