package com.zzh.design;

/**
 * Created by house on 2017/6/30.
 */
public class SingletonZ {
    private static SingletonZ instance = null;
    private SingletonZ() {
    }

    public static SingletonZ getInstance() {
        if (null == instance) {
            instance = new SingletonZ();
        }

        return instance;
    }
}
