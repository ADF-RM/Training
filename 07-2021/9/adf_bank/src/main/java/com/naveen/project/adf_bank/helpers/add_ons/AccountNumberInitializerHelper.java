package com.naveen.project.adf_bank.helpers.add_ons;

import redis.clients.jedis.Jedis;

final class AccountNumberInitializerHelper {
    private static AccountNumberInitializerHelper accountNumberInitializer;

    private static Object mutex = new Object();

    public static Long accountNumber;

    static Jedis jd = new Jedis();

    private AccountNumberInitializerHelper() {
        jd.set("A/c No", accountNumber.toString());
    }

    public static AccountNumberInitializerHelper getInstance() {
        AccountNumberInitializerHelper result = accountNumberInitializer;
        if (result == null) {
            synchronized (mutex) {
                accountNumber = 1000000000000L;
                accountNumberInitializer = new AccountNumberInitializerHelper();
            }
        } else {
            accountNumber = Long.parseLong(jd.get("A/c No")) + 1;
            jd.set("A/c No", accountNumber.toString());
        }
        return accountNumberInitializer;
    }
}
