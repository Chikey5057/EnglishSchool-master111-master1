package ru.sapteh.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import ru.sapteh.dao.Dao;
import ru.sapteh.model.Manufacture;
import ru.sapteh.model.ProductSale;

import java.util.List;

public class ProductSaleService implements Dao<ProductSale,Integer> {

    private final SessionFactory factory;
    public ProductSaleService(SessionFactory factory){
        this.factory=factory;
    }
    @Override
    public void create(ProductSale productSale) {
        try(Session session=factory.openSession()) {
            session.beginTransaction();
            session.save(productSale);
            session.getTransaction().commit();
        }
    }

    @Override
    public void update(ProductSale productSale) {
        try(Session session=factory.openSession()) {
            session.beginTransaction();
            session.update(productSale);
            session.getTransaction().commit();
        }
    }

    @Override
    public void delete(ProductSale productSale) {
        try(Session session=factory.openSession()) {
            session.beginTransaction();
            session.delete(productSale);
            session.getTransaction().commit();
        }
    }

    @Override
    public ProductSale read(Integer id) {
        try(Session session=factory.openSession()) {
            ProductSale productSale=session.get(ProductSale.class,id);
            return productSale;
        }
    }

    @Override
    public List<ProductSale> readByAll() {
        try(Session session= factory.openSession()) {
            Query<ProductSale> query=session.createQuery("FROM ProductSale ");
            return query.list();
        }
    }
}
