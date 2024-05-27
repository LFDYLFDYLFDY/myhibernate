package org.example;


import jakarta.persistence.Id;
import org.entity.UserDatum;
import org.hibernate.Session;
import org.hibernate.query.Query;

public class Main {

    public static void main(String[] args) {

        // сразу получаем готовый SessionFactory и сразу создаем сессию
        System.out.println("______________________");

        Session session = HibernateUtil.getSessionFactory().openSession();
        System.out.println("______________________");
        UserDatum userDatum = new UserDatum();


        Query<UserDatum> query = session.createQuery("from UserDatum",UserDatum.class);
        System.out.println(query.list().stream().findFirst());
        UserDatum userDatum1 = new UserDatum();
//        userDatum1.setId(11111111L);
        userDatum1.setUsername("LFDY3");
        userDatum1.setEmail("lfdy3@gmail.com");
        userDatum1.setUserpassword("12345");
        session.save(userDatum1);
        System.out.println(userDatum1);

        session.close();
        System.out.println("______________________");

        HibernateUtil.close();
        System.out.println("______________________");

    }

}
