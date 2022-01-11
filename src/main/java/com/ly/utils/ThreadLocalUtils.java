package com.ly.utils;

public class ThreadLocalUtils {
    private static ThreadLocal<String> threadLocal
            = new ThreadLocal<String>();

    public static void setAccount(String account) {
        threadLocal.set(account);
    }
    public static String getAccount(){
        return threadLocal.get();
    }


}
