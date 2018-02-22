package Controller.Servlet;


import DAO.DAOProduct;
import DAO.Implementation.HiberanteDAOProduct;
import Model.Product;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/showProducts")
public class ListProducts extends HttpServlet{
    protected void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        DAOProduct daoProduct = new HiberanteDAOProduct();

        List<Product> productList = daoProduct.getAll();

        req.setAttribute("list", daoProduct.getAll());
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("ProductList.jsp");
        requestDispatcher.forward(req,resp);

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req,resp);
    }
}
