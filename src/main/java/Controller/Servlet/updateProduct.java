package Controller.Servlet;

import DAO.DAOManufacturer;
import DAO.Implementation.HiberanteDAOProduct;
import DAO.Implementation.HibernateDAOManufacturer;
import Model.Manufacturer;
import Model.Product;
import Utils.HibernateUtils;
import org.hibernate.Query;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@WebServlet("/updateProduc")
public class updateProduct extends HttpServlet{
    protected void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //Получаем и собираем product
        Query query = HibernateUtils.getSessionFactory().openSession().
                createQuery("Select p from Product p where p.id =:id");

        UUID uuid = UUID.fromString(req.getParameter("UPDATE"));
        query.setParameter("id",uuid);
        Product product = (Product) query.uniqueResult();
        req.setAttribute("productIdToUpdate",uuid);

        //Критерий для ProductCreate.jsp
        req.setAttribute("fromWhatServlet","updateProduct");

        //Берем список Manufacturers з бд для передачи в выпадающие меню при создании Product
        DAOManufacturer daoManufacturer = new HibernateDAOManufacturer();
        List<Manufacturer> manufacturerList = daoManufacturer.getAll();
        List<String> manufacturerNameList = new ArrayList<String>();
        for(Manufacturer m: manufacturerList){
            manufacturerNameList.add(m.getManufactur_name());
        }
        req.setAttribute("list",manufacturerList );


        //Переход на создание/изменение
        req.getRequestDispatcher("/ProductCreate.jsp").forward(req,resp);


    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req,resp);
    }
}
