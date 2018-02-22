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

@WebServlet("/addManufacturerToDB")
public class addManufacturerToDB extends HttpServlet {

    protected void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        Manufacturer manufacturer = new Manufacturer();

        UUID id = UUID.randomUUID();

        manufacturer.setId(id);

        manufacturer.setManufactur_name(req.getParameter("manufacturerName"));

        HibernateDAOManufacturer hib = new HibernateDAOManufacturer();
        hib.create(manufacturer);

        resp.sendRedirect("index.jsp");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }
}
