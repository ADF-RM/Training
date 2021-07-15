package com.naveen.project.adf_bank.Utils.AddOns;

import java.io.Serializable;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

public class AccountNumberGenerator implements IdentifierGenerator {

    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
        AccountNumberInitializer.getInstance();
        System.out.println("\n" + AccountNumberInitializer.accountNumber+"\n");
        return AccountNumberInitializer.accountNumber;
    }
}
