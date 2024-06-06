package ru.lfdy;

import jakarta.persistence.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.lfdy.entity.Order;
import ru.lfdy.entity.OrderKey;
import ru.lfdy.entity.Person;
import ru.lfdy.entity.Product;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;


/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
//        System.err.println("Initial SessionFactory creation failed.");
        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Person.class)
                .addAnnotatedClass(Product.class)
                .addAnnotatedClass(Order.class)
                .addAnnotatedClass(OrderKey.class)
                .buildSessionFactory();
        Session session = null;
        String[] commandsParts = null;

//       throw new ExceptionInInitializerError();
        try {
            Scanner sc = new Scanner(System.in);
            System.out.print("Enter command: ");
            while (sc.hasNextLine()) {
                String command = sc.nextLine();
                if (command.equals("exit")) {
                    break;
                }
//                if (command.equals("help")) {
//                    System.out.println("--------------------------------");
//                    System.out.println(" /help  - Вызов справки");
//                    System.out.println("--------------------------------");
//                    System.out.println("Enter new command: ");
//
//                }

//                 /buy Person_1 Product_1

                commandsParts = command.split(" ");
                switch (commandsParts[0]) {

                    case "/help":
                        System.out.println("-------------------------------------------------------");

                        System.out.println(" exit                    - Выход");
                        System.out.println(" /help                   - Вызов справки");
                        System.out.println(" /listPerson             - Все записи Person");
                        System.out.println(" /listProduct            - Все записи Product");
                        System.out.println(" /listOrder              - Все записи Order");
                        System.out.println(" /addPerson Name         - Добавить запись в Person");
                        System.out.println(" /addProduct Name        - Добавить запись в Product");
                        System.out.println(" /changePrice Product_id - Изменение цены на продукт");


                        System.out.println(" /buy Person Product     - Покупка");

                        System.out.println("--------------------------------------------------------");
                        System.out.print("Enter new command: ");
                        break;

                        case "/listPerson":
                            session = sessionFactory.getCurrentSession();
                            session.beginTransaction();
//                            System.out.println("---------------------");

                            Iterator it1 = session
                                    .createQuery("from Person ")
                                    .getResultList()
                                    .iterator();

                        while (it1.hasNext()) {
                            Person ps = (Person) it1.next();
                            System.out.println("---------------------");
                             System.out.println("ID   : " + ps.getId());
                             System.out.println("NAME : " + ps.getName());

                            }
                            System.out.println("---------------------");
                            System.out.print("Enter new command: ");
                            session.getTransaction().commit();
                            break;

                    case "/listProduct":
                        session = sessionFactory.getCurrentSession();
                        session.beginTransaction();
//                            System.out.println("---------------------");

                        Iterator it2 = session
                                .createQuery("from Product ")
                                .getResultList()
                                .iterator();

                        while (it2.hasNext()) {
                            Product pd = (Product) it2.next();
                            System.out.println("---------------------");
                            System.out.println("ID   : " + pd.getId());
                            System.out.println("NAME : " + pd.getName());
                            System.out.println("PRICE : " + pd.getPrice());

                        }
                        System.out.println("---------------------");
                        System.out.print("Enter new command: ");
                        session.getTransaction().commit();
                        break;

                    case "/listOrder":
                        session = sessionFactory.getCurrentSession();
                        session.beginTransaction();
//                            System.out.println("---------------------");

                        Iterator it3 = session
                                .createQuery("from Order ")
                                .getResultList()
                                .iterator();

                        while (it3.hasNext()) {
                            Order order = (Order) it3.next();
                            System.out.println("---------------------");
                            System.out.println("Person    : " + order.getPerson().getId());
                            System.out.println("Product   : " + order.getProduct().getId());
                            System.out.println("Price    : " + order.getPrice());
                            System.out.println("Order Key : " + order.getOrderKey().toString());


                        }
                        System.out.println("---------------------");
                        System.out.print("Enter new command: ");
                        session.getTransaction().commit();
                        break;

                    case "/addPerson":
                        session = sessionFactory.getCurrentSession();
                        session.beginTransaction();
                        Person ps = null;
                        try {
                            ps = new Person();
                            ps.setName(commandsParts[1]);
                            session.save(ps);
                            session.getTransaction().commit();

                        } catch (Exception e) {
                            System.err.println(" Не возможно сохранить запись!!" +e);
//                            throw new RuntimeException(e);
                        }
                        finally {

                        }
                        System.out.print("Enter new command: ");
                        break;

                    case "/addProduct":
                        session = sessionFactory.getCurrentSession();
                        session.beginTransaction();
                        Product pr1 = null;
                        try {
                            pr1 = new Product();
                            pr1.setName(commandsParts[1]);
                            pr1.setPrice(Double.valueOf(commandsParts[2]));
                            session.save(pr1);
                            session.getTransaction().commit();

                        } catch (Exception e) {
                            System.err.println(" Не возможно сохранить запись!!" +e);
//                            throw new RuntimeException(e);
                        }
                        finally {

                        }
                        System.out.print("Enter new command: ");
                        break;

                    case "/changePrice":
                        session = sessionFactory.getCurrentSession();
                        session.beginTransaction();
                        Product pr2 = null;
                        try {
                            pr2 = session.get(Product.class,commandsParts[1]);
                            pr2.setPrice(Double.valueOf(commandsParts[2]));
                            session.save(pr2);
                            session.getTransaction().commit();


                        } catch (Exception e) {
                            System.err.println(" Не возможно сохранить запись!!" +e);
//                            throw new RuntimeException(e);
                        }
                        finally {

                        }
                        System.out.print("Enter new command: ");
                        break;

                    case "/buy":
                        System.out.println("/buy");
                        session = sessionFactory.getCurrentSession();
                        session.beginTransaction();
                        Query query1 = session
                                .createQuery("from Person p where p.name = :name")
                                .setParameter("name", commandsParts[1]);
                        System.out.println(query1.getResultList());
                        Person person = (Person) session
                                .createQuery("from Person  p where p.name = :name")
                                .setParameter("name", commandsParts[1]).getSingleResult();
                        System.out.println("1111111111111111111111111111111/buy Person_1 Product_1");
                        Product product = (Product) session
                                .createQuery("from Product p where p.name = :name")
                                        .setParameter("name", commandsParts[2]).getSingleResult();
                        OrderKey orderKey = new OrderKey();
                        orderKey.setPersonID(person.getId());
                        orderKey.setProductID(product.getId());

                        Order order = new Order();
                        order.setOrderKey(orderKey);
                        order.setPrice(product.getPrice());
                        session.save(order);
                        session.getTransaction().commit();
                      System.out.println("Saved the next order: " + order.toString());
                      System.out.println("Enter new command: ");
                      break;
                    default:
                        System.out.println("Invalid command: " + commandsParts[0]);
                        System.out.print("Enter new command: ");
                }


            }
//            System.out.println(Arrays.deepToString(commandsParts));
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }
}
