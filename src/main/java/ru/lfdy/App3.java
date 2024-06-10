package ru.lfdy;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.lfdy.entity.*;

public class App3 {
    public static void main(String[] args) {
        work();

    }

    public static void work() {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Customer.class)
                .addAnnotatedClass(Manufacturer.class)
                .addAnnotatedClass(Products.class)
                .addAnnotatedClass(BigItem.class)
                .buildSessionFactory();
        Session session = null;

        try {
            session=factory.getCurrentSession();
            session.beginTransaction();
            Products products = session.get(Products.class, 1L);
           System.out.println(products);
            Customer customer = session.get(Customer.class, 1L);
            System.out.println(customer);
            Manufacturer manufacturer = session.get(Manufacturer.class, 1L);
           System.out.println(manufacturer);

//            System.out.println("-------------------");
           System.out.println(manufacturer.getProductses());
//            System.out.println("-------------------");

            Products products1 = session.get(Products.class,1L);
            System.out.println(products1);
//            System.out.println(manufacturer.getProductses());
          session.getTransaction().commit();
        }
        finally {
            factory.close();
            if (session!=null){
                session.close();
            }
        }
    }
}
