package com.naveen.training;

import java.util.Set;
import redis.clients.jedis.Jedis;

public class RedisDemo {
    static String ACCOUNT_NO = "6100580029003700";
    static String TRANSACTION_FEE = "100";
    static String ACCOUNT_TYPE = "SAVINGS";
    static String HOLDER_NAME = "NITHISH C";

    public static void main(String[] args) {

        try {
            Jedis jd = new Jedis();
            System.out.println(jd.ping());

            jd.set("account_no", ACCOUNT_NO);
            jd.set("account_type", ACCOUNT_TYPE);
            jd.set("transaction_fee", TRANSACTION_FEE);
            jd.set("holder_name", HOLDER_NAME);
            jd.sadd("account_details", ACCOUNT_NO, TRANSACTION_FEE, ACCOUNT_TYPE, HOLDER_NAME);

            Set<String> account_details = jd.smembers("account_details");

            jd.set(HOLDER_NAME, account_details.toString());

            System.out.println("A/c No. :" + jd.get("account_no"));
            System.out.println("A/c type : " + jd.get("account_type"));
            System.out.println("Transaction Fee : " + jd.get("transaction_fee"));
            System.out.println("Holder Name : " + jd.get("holder_name"));
            System.out.println("\nAccount Details ..\n"+jd.get(HOLDER_NAME));

            jd.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
