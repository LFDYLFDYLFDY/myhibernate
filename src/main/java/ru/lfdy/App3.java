package ru.lfdy;

import jakarta.persistence.LockModeType;
import jakarta.persistence.OptimisticLockException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.lfdy.entity.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.stream.Collectors;


public class App3 {
    public static void main(String[] args) throws IOException{
    prepareData();
        work();
//      optimisticVersioningTest();
//      optimisticVersioningThreadingTest();
queryPessimisticLockHandle();

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
    public static void uncheckableSleep( int ms) {
        try {
            Thread.sleep(ms);

        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
    public static void optimisticVersioningTest(){
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(BigItem.class)
                .buildSessionFactory();
        Session session = null;
        try {
            session = factory.getCurrentSession();
            session.beginTransaction();
            BigItem bigItem = new BigItem(20);
            session.save(bigItem);
            System.out.println(bigItem);
            bigItem.setVal(25);
            System.out.println(bigItem);
            session.save(bigItem);
            System.out.println(bigItem);
            session.getTransaction().commit();

            session = factory.getCurrentSession();
            session.beginTransaction();
            bigItem = session.get(BigItem.class,1L);
            System.out.println(bigItem);
            session.getTransaction().commit();


        }
        finally {
            factory.close();
            if (session!=null){
                session.close();
            }
        }
    }
    public static void optimisticVersioningThreadingTest() {
        CountDownLatch countDownLatch = new CountDownLatch(2);
//        SessionFactory factory = new Configuration()
//                .configure("hibernate.cfg.xml")
//                .addAnnotatedClass(BigItem.class)
//                .buildSessionFactory();
//        Session session = factory.getCurrentSession();
//        session.beginTransaction();
//        BigItem bigItem = session.get(BigItem.class,1L);
//        System.out.println(bigItem);

        try {
            new Thread(()->{

                System.out.println("Thread 1 begin");
                SessionFactory factory = new Configuration()
                        .configure("hibernate.cfg.xml")
                        .addAnnotatedClass(BigItem.class)
                        .buildSessionFactory();
                Session session = factory.getCurrentSession();

                session.beginTransaction();
                BigItem bigItem = session.get(BigItem.class,1L);
                bigItem.setVal(100);
                  uncheckableSleep(1000);
                session.save(bigItem);
                session.getTransaction().commit();
                System.out.println("Thread 1 end");
                if(session!=null){
                    session.close();
                }
                countDownLatch.countDown();
            }).start();
            new Thread(()->{
                SessionFactory factory = new Configuration()
                        .configure("hibernate.cfg.xml")
                        .addAnnotatedClass(BigItem.class)
                        .buildSessionFactory();
                System.out.println("Thread 2 begin");
                Session session = factory.getCurrentSession();
                session.beginTransaction();
                BigItem bigItem = session.get(BigItem.class,1L);
                bigItem.setVal(500);
                uncheckableSleep(3000);
                try {
                    session.save(bigItem);
                    session.getTransaction().commit();
                    System.out.println("Thread 2 end");

                }
                catch (OptimisticLockException e) {
                    session.getTransaction().rollback();
//                    System.out.println("Optimistic lock exception");
                    System.err.println(e.getMessage());
                }
                if(session!=null){
                    session.close();

                }
            }).start();
       }
        finally {
//            factory.close();

            }
//

    }
    public static void queryPessimisticLockHandle(){
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Customer.class)
                .addAnnotatedClass(Manufacturer.class)
                .addAnnotatedClass(Products.class)
                .addAnnotatedClass(BigItem.class)
                .buildSessionFactory();
        Session session = null;
        try{
            session = factory.getCurrentSession();
            session.beginTransaction();
            BigDecimal totalCost = new BigDecimal(0);
            List<Products> products = session.createQuery("SELECT p FROM Products p WHERE p.title= :title", Products.class)
               .setLockMode(LockModeType.PESSIMISTIC_READ)
              .setHint("javax.persistence.lock.timeout",5000)
                    .setParameter("title","Sprite")
                    .getResultList();
            for (Products p : products ) {
                System.out.println(p.toString());
            }
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
