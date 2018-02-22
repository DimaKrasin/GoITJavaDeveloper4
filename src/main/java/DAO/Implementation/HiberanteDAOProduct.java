package DAO.Implementation;

import DAO.DAOProduct;
import Model.Product;
import Utils.HibernateUtils;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.UUID;

public class HiberanteDAOProduct implements DAOProduct {

    public Product getById(UUID uuid) {
        Session session = null;
        Transaction tx = null;
        Product result = null;
        try {
            session = HibernateUtils.getSessionFactory().openSession();
            tx = session.beginTransaction();
            result = (Product) session.get(Product.class,uuid);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return result;
    }

    public List<Product> getAll() {
        Session session = null;
        Transaction tx = null;
        List<Product> result = null;
        try {
            session = HibernateUtils.getSessionFactory().openSession();
            tx = session.beginTransaction();

            // HQL
            result = (List<Product>) session.createQuery("FROM Model.Product ").list();

            session.getTransaction().commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return result;
    }

    public List<Product> getByName(String name) {
        Session session = null;
        Transaction tx = null;
        List<Product> result = null;
        try {
            session = HibernateUtils.getSessionFactory().openSession();
            tx = session.beginTransaction();

            // HQL
            Query query = session.createQuery("FROM Product WHERE name = :name");
            query.setParameter("name",name);
            result = (List<Product>) query.list();

            session.getTransaction().commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return result;
    }

    public void create(Product product) {
        Session session = null;
        Transaction tx = null;
        try {
            session = HibernateUtils.getSessionFactory().openSession();
            tx = session.beginTransaction();
            // HQL
            // перевірка чи продукт з таким name існує
            Query query = session.createQuery("FROM Product WHERE name = :name AND manufacturer = :manufacturer");
            query.setParameter("name",product.getName());
            query.setParameter("manufacturer",product.getManufacturer());
            List<Product> results = (List<Product>) query.list();
            if (results.size() > 0){
                System.out.println("Продукт даного виробника з такою назвою вже існує.");
            } else {
                session.save(product);
                System.out.println("Продукт створено успішно.");
            }
            session.getTransaction().commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
            System.out.println("Неможливо створити продукт.");
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public void update(Product product) {
        Session session = null;
        Transaction tx = null;
        try {
            session = HibernateUtils.getSessionFactory().openSession();
            tx = session.beginTransaction();
            session.update(product);
            System.out.println("Продукт змінено успішно.");
            session.getTransaction().commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
            System.out.println("Неможливо змінити продукт.");
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public void delete(Product product) {
        Session session = null;
        Transaction tx = null;
        try {
            session = HibernateUtils.getSessionFactory().openSession();
            tx = session.beginTransaction();
            session.delete(product);
            System.out.println("Продукт вилучено успішно.");
            session.getTransaction().commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
            System.out.println("Неможливо вилучити продукт.");
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }
}
