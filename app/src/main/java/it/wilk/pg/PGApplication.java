package it.wilk.pg;

import android.app.Application;

import com.orhanobut.logger.Logger;

import it.wilk.pg.model.ParseResult;

/**
 * PG APPLICATION
 * Created by Mr.Wilk on 2016/08/12 0012.
 */

public class PGApplication extends Application {
    public static ParseResult mResult = new ParseResult();

    @Override
    public void onCreate() {
        super.onCreate();
        Logger.init("WILK");
    }
}
