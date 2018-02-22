package Controller.Servlet;

import DAO.Implementation.HiberanteDAOProduct;
import Model.Product;
import Utils.HibernateUtils;
import org.hibernate.Query;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

@WebServlet("/deleteProjectFromDB")
public class deleteProjectFromDB extends HttpServlet {
    protected void processRequest(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        Query query = HibernateUtils.getSessionFactory().openSession().
                createQuery("Select p from Product p where p.id =:id");

        UUID uuid = UUID.fromString(req.getParameter("DELETE"));
        query.setParameter("id",uuid);
        Product product = (Product) query.uniqueResult();
        HiberanteDAOProduct hiberanteDAOProduct = new HiberanteDAOProduct();
        hiberanteDAOProduct.delete(product);

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
