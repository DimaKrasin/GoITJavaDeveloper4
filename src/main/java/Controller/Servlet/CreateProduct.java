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
import java.util.ArrayList;
import java.util.List;

@WebServlet("/createProduct")
public class CreateProduct extends HttpServlet{

    protected void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        DAOManufacturer daoManufacturer = new HibernateDAOManufacturer();
        List<Manufacturer> manufacturerList = daoManufacturer.getAll();
        List<String> manufacturerNameList = new ArrayList<String>();
        for(Manufacturer m: manufacturerList){
            manufacturerNameList.add(m.getManufactur_name());
        }

        req.setAttribute("fromWhatServlet","createProduct");

        req.setAttribute("list",manufacturerList );
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("ProductCreate.jsp");
        requestDispatcher.forward(req,resp);

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req,resp);
    }


}
