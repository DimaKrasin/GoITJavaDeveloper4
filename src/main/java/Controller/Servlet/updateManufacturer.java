package Controller.Servlet;

import Model.Manufacturer;
import Utils.HibernateUtils;
import org.hibernate.Query;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

@WebServlet("/updateManufacturer")
public class updateManufacturer extends HttpServlet {
    protected void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //Получаем и собираем manufacturer
        Query query = HibernateUtils.getSessionFactory().openSession().
                createQuery("Select m from Manufacturer m where m.id =:id");

        UUID uuid = UUID.fromString(req.getParameter("UPDATE"));
        query.setParameter("id",uuid);
        Manufacturer manufacturer = (Manufacturer) query.uniqueResult();
        req.setAttribute("manufacturerIdToUpdate",uuid);

        //Критерий для ManufactuerCreate.jsp
        req.setAttribute("fromWhatServlet","updateManufacturer");


        //Переход на создание/изменение
        req.getRequestDispatcher("/ManufacturerCreate.jsp").forward(req,resp);

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

}
