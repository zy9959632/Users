package com.example.administrator.day02_text;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import com.example.xts.greendaodemo.db.DaoMaster;
import com.example.xts.greendaodemo.db.DaoSession;

/**
 * Created by Administrator on 2019/11/19 0019.
 */

public class MyApp extends Application {

    public static DaoSession mDaoSession;

    @Override
    public void onCreate() {
        super.onCreate();
        mDaoSession = setUsersDao();
    }

    public DaoSession setUsersDao() {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "users.db", null);
        SQLiteDatabase db = helper.getWritableDatabase();
        db.disableWriteAheadLogging();
        DaoMaster daoMaster = new DaoMaster(db);
        mDaoSession = daoMaster.newSession();
        return mDaoSession;
    }
}
