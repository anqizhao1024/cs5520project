package edu.neu.khoury.madsea.anqi.zhao.application;

import org.litepal.LitePal;
import org.litepal.LitePalApplication;

/**
 * init LitePal
 */
public class BaseApplication extends LitePalApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        //init LitePal
        LitePal.initialize(this);
    }
}
