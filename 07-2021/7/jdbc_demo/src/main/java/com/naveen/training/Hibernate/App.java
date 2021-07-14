package com.naveen.training.Hibernate;

import java.math.BigInteger;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import redis.clients.jedis.Jedis;

public class App {
    static Long ACCOUNT_NO = 1000200030004000L;

    public static void main(String[] args) {

        Jedis jd = new Jedis();
        System.out.println(jd.ping());
        ACCOUNT_NO = Long.parseLong(jd.get("ACCOUNT_NO")) + 1;
        jd.set("ACCOUNT_NO", ACCOUNT_NO.toString());

        System.out.println("A/c No : " + ACCOUNT_NO);

        jd.close();

        Long testerId = ACCOUNT_NO - 1000200030004000L;

        DemoAccount dAccount = new DemoAccount();
        dAccount.setACCOUNT_NO(new BigInteger(ACCOUNT_NO.toString()));
        dAccount.setACCOUNT_TYPE("SAVINGS");
        dAccount.setTRANSACTION_FEE(1500);
        dAccount.setHOLDER_NAME("BETA TESTER - " + testerId);

        Configuration configuration = new Configuration().configure().addAnnotatedClass(DemoAccount.class);
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();

        Transaction transaction = session.beginTransaction();
        session.save(dAccount);
        transaction.commit();
    }
}
