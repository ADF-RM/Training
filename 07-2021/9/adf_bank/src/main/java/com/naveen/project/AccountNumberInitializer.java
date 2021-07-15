package com.naveen.project;

import redis.clients.jedis.Jedis;

public class AccountNumberInitializer {
    private static AccountNumberInitializer accountNumberInitializer = null;

    public static Long accountNumber;

    static Jedis jd = new Jedis();

    private AccountNumberInitializer(){
        accountNumber = 1000000000000L;
        jd.set("A/c No", accountNumber.toString());
    }

    public static AccountNumberInitializer getInstance(){
        if (accountNumberInitializer == null){
            accountNumberInitializer = new AccountNumberInitializer();
        }
        else{
            accountNumber = Long.parseLong(jd.get("A/c No")) + 1;
            jd.set("A/c No", accountNumber.toString());
        }
        return accountNumberInitializer;
    }
}
