package Controller.Servlet;

import DAO.Implementation.HibernateDAOManufacturer;
import Model.Manufacturer;
import Utils.HibernateUtils;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

@WebServlet("/deleteManufacturerFromDB")
public class deleteManufacturerFromDB extends HttpServlet{
    protected void processRequest(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
/*
        Query query = HibernateUtils.getSessionFactory().openSession().
                createQuery("Select m from Manufacturer m where m.id =:id");

        UUID uuid = UUID.fromString(req.getParameter("DELETE"));
        query.setParameter("id",uuid);
        Manufacturer manufacturer = (Manufacturer) query.uniqueResult();

        HibernateUtils.getSessionFactory().openSession();
        HibernateUtils.getSessionFactory().close();

        System.out.println("LOLOLOLOLOLOLOLOLOL " +
                HibernateUtils.getSessionFactory().isClosed());

        HibernateDAOManufacturer hibernateDAOManufacturer = new HibernateDAOManufacturer();
        hibernateDAOManufacturer.delete(manufacturer);
*/

        Session session = null;
        Transaction tx = null;
        Manufacturer manufacturer = new Manufacturer();
        try {
            session = HibernateUtils.getSessionFactory().openSession();
            tx = session.beginTransaction();
            Query query = session.createQuery("Select m from Manufacturer m where m.id =:id");
            UUID uuid = UUID.fromString(req.getParameter("DELETE"));
            query.setParameter("id",uuid);
            manufacturer = (Manufacturer) query.uniqueResult();

            session.getTransaction().commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }

        HibernateDAOManufacturer hibernateDAOManufacturer = new HibernateDAOManufacturer();
        hibernateDAOManufacturer.delete(manufacturer);

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
