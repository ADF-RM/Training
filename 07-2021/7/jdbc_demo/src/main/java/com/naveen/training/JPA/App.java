package com.naveen.training.JPA;

import java.math.BigInteger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.naveen.training.JPA.Models.DemoBankAccount;

public class App {
    public static void main(String[] args) {

        DemoBankAccount demoBankAccount = new DemoBankAccount();
        demoBankAccount.setACCOUNT_NO(new BigInteger("1001200030004000"));
        demoBankAccount.setACCOUNT_TYPE("SAVINGS");
        demoBankAccount.setTRANSACTION_FEE(1500);
        demoBankAccount.setHOLDER_NAME("FAIYAZ K K");

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        em.persist(demoBankAccount);
        em.getTransaction().commit();

        DemoBankAccount bAccount = em.find(DemoBankAccount.class,new BigInteger("1001200030004000"));

        System.out.println("\n" + bAccount + "\n");

    }
}
