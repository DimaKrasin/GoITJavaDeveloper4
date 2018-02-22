package Controller.Servlet;


import DAO.Implementation.HiberanteDAOProduct;
import DAO.Implementation.HibernateDAOManufacturer;
import Model.Manufacturer;
import Model.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.UUID;

@WebServlet("/addProductToDB")
public class addProductToDB extends HttpServlet{

    protected void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Product product = new Product();

        //name
        product.setName(req.getParameter("productName"));
        //price
        BigDecimal price = new BigDecimal
                (req.getParameter("productPrice").replaceAll(",", ""));
        product.setPrice( price);

        //manufacture
        String strId = req.getParameter("manufacturer");
        UUID uuid = UUID.fromString(strId);
        Manufacturer manufacturer = new HibernateDAOManufacturer().getById(uuid);
        product.setManufacturer(manufacturer);

        HiberanteDAOProduct hib = new HiberanteDAOProduct();
        hib.create(product);

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
