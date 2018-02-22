package DAO.Implementation;

import DAO.DAOManufacturer;
import Model.Manufacturer;
import Utils.HibernateUtils;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.UUID;

public class HibernateDAOManufacturer implements DAOManufacturer{

    public Manufacturer getById(UUID uuid) {
        Session session = null;
        Transaction tx = null;
        Manufacturer result = null;
        try {
            session = HibernateUtils.getSessionFactory().openSession();
            tx = session.beginTransaction();
            result = (Manufacturer) session.get(Manufacturer.class,uuid);
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

    public List<Manufacturer> getAll() {
        Session session = null;
        Transaction tx = null;
        List<Manufacturer> result = null;
        try {
            session = HibernateUtils.getSessionFactory().openSession();
            tx = session.beginTransaction();

            result = (List<Manufacturer>) session.createQuery("from Model.Manufacturer").list();

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

    public List<Manufacturer> getByName(String name) {
        Session session = null;
        Transaction tx = null;
        List<Manufacturer> results = null;
        try {
            session = HibernateUtils.getSessionFactory().openSession();
            tx = session.beginTransaction();

            // HQL
            Query query = session.createQuery("FROM Manufacturer WHERE name = :name");
            query.setParameter("name",name);
            results = (List<Manufacturer>) query.list();

            session.getTransaction().commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return results;
    }

    public void create(Manufacturer manufacturer) {
        Session session = null;
        Transaction tx = null;
        try {
            session = HibernateUtils.getSessionFactory().openSession();
            tx = session.beginTransaction();

            Query query = session.createQuery("FROM Manufacturer WHERE manufactur_name = :name");
            query.setParameter("name",manufacturer.getManufactur_name());
            List<Manufacturer> results = (List<Manufacturer>) query.list();
            if (results.size() > 0){
                System.out.println("Виробник з такою назвою вже існує.");
            } else {
                session.save(manufacturer);
                System.out.println("Виробника створено успішно.");
            }

            session.getTransaction().commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
            System.out.println("Неможливо створити виробника.");
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public void update(Manufacturer manufacturer) {
        Session session = null;
        Transaction tx = null;
        try {
            session = HibernateUtils.getSessionFactory().openSession();
            tx = session.beginTransaction();
            session.update(manufacturer);
            System.out.println("Виробника змінено успішно.");
            session.getTransaction().commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
            System.out.println("Неможливо змінити виробника.");
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public void delete(Manufacturer manufacturer) {
        Session session = null;
        Transaction tx = null;
        try {
            session = HibernateUtils.getSessionFactory().openSession();
            tx = session.beginTransaction();
            session.delete(manufacturer);
            System.out.println("Виробника вилучено успішно.");
            session.getTransaction().commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
            System.out.println("Неможливо вилучити виробника.");
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }
}
