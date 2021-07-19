package com.naveen.project.adf_bank.helpers.add_ons;

import java.io.Serializable;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

public class AccountNumberGenerator implements IdentifierGenerator {

    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
        AccountNumberInitializerHelper.getInstance();
        System.out.println("\n" + AccountNumberInitializerHelper.accountNumber+"\n");
        return AccountNumberInitializerHelper.accountNumber;
    }
}
