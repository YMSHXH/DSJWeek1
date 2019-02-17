package com.example.king.dsjweek1.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.king.dsjweek1.greendao.DaoMaster;
import com.example.king.dsjweek1.greendao.DaoSession;

public class GreendaoUtils {
    private static GreendaoUtils mInstance;
    private DaoSession daoSession;

    public DaoSession getDaoSession() {
        return daoSession;
    }

    private GreendaoUtils(){

    }
    /**
     * 双重检索锁
     * @return
     */
    public static GreendaoUtils getInstance(){

        if (mInstance==null){
            synchronized (GreendaoUtils.class){
                if (mInstance==null){
                    mInstance = new GreendaoUtils();
                }
            }
        }
        return mInstance;
    }

    /**
     * 初始化GreenDao,直接在Application中进行初始化操作
     */
    public void initGreenDao(Context context,String db_Name){
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(context,db_Name);
        SQLiteDatabase db = helper.getWritableDatabase();

        DaoMaster daoMaster = new DaoMaster(db);
        //创建daosession
        daoSession = daoMaster.newSession();
    }
}
