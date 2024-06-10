package ru.lfdy;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.lfdy.entity.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;


public class App3 {
    public static void main(String[] args) throws IOException{
        //work();
        prepareData();

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
    public static void prepareData () throws IOException {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();
        Session session = null;
        try{
//            String filename="create_table_2.sql";
//            Path pathToFile = Paths.get(filename);
//            System.out.println(pathToFile);
     String sql = Files.lines(Paths.get("src\\\\main\\\\resources\\\\create_table_2.sql")).collect(Collectors.joining(" "));
//         String sql = Files.lines(Paths.get("create_table_2.sql")).toString();
//            String sql = "select version()";
        System.out.println(sql);
           session = factory.getCurrentSession();
           session.beginTransaction();
          session.createNativeQuery(sql).executeUpdate();

            session.getTransaction().commit();

        }
        finally {
//            factory.close();
//            if (session!=null){
//                session.close();
//            }
        }
    }
}
