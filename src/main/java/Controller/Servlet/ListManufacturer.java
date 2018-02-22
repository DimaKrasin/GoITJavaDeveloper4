package Controller.Servlet;

import DAO.DAOManufacturer;
import DAO.Implementation.HibernateDAOManufacturer;
import Model.Manufacturer;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/showManufacturers")
public class ListManufacturer extends HttpServlet {

    protected void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        DAOManufacturer daoManufacturer = new HibernateDAOManufacturer();

        req.setAttribute("list", daoManufacturer.getAll());
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("ManufacturerList.jsp");
        requestDispatcher.forward(req,resp);

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req,resp);
    }
}
