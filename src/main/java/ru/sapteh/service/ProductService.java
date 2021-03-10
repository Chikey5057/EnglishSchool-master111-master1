package ru.sapteh.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import ru.sapteh.dao.Dao;
import ru.sapteh.model.Product;

import java.util.List;

public class ProductService implements Dao<Product,Integer> {

    private final SessionFactory factory;

    public ProductService(SessionFactory factory){
        this.factory=factory;
    }
    public void create(Product product) {
        try(Session session=factory.openSession()) {
            session.beginTransaction();
            session.save(product);
            session.getTransaction().commit();
        }
    }

    public void update(Product product) {
        try(Session session=factory.openSession()) {
            session.beginTransaction();
            session.update(product);
            session.getTransaction().commit();
        }
    }

    public void delete(Product product) {
        try(Session session=factory.openSession()) {
            session.beginTransaction();
            session.delete(product);
            session.getTransaction().commit();
        }
    }

    public Product read(Integer id) {
        try(Session session= factory.openSession()) {
            Product product=session.get(Product.class,id);
            return product;
        }
    }

    public List<Product> readByAll() {
       try(Session session= factory.openSession()) {
           Query<Product> query=session.createQuery("FROM Product ");
           return query.list();
       }
    }
}
