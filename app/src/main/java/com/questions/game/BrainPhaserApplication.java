package com.questions.game;

import android.app.Application;
import android.content.res.Resources;
import android.util.DisplayMetrics;

import com.questions.game.database.DatabaseModule;

import net.danlew.android.joda.JodaTimeAndroid;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by funkv on 17.02.2016.
 * <p>
 * Custom Application class for hooking into App creation
 */
public class BrainPhaserApplication extends Application {
    public static String PACKAGE_NAME;
    public static BrainPhaserComponent component;

    /**
     * Creates the Production app Component
     */
    protected BrainPhaserComponent createComponent() {
        return com.questions.game.DaggerBrainPhaserApplication_ApplicationComponent.builder()
                .appModule(new AppModule(this))
                .databaseModule(new DatabaseModule(getApplicationContext(), "prodDb"))
                .build();
    }

    /**
     * Returns the Component for use with Dependency Injection for this
     * Application.
     *
     * @return compoenent to use for DI
     */
    public BrainPhaserComponent getComponent() {
        return component;
    }

    /**
     * initializes the DaoManager with a writeable database
     */
    @Override
    public void onCreate() {
        super.onCreate();
        JodaTimeAndroid.init(this);
        component = createComponent();
        PACKAGE_NAME = getApplicationContext().getPackageName();
    }

    /**
     * Defines the Component to use in the Production Application.
     * The component is a bridge between Modules and Injects.
     * It creates instances of all the types defined.
     */
    @Singleton
    @Component(modules = {AppModule.class, DatabaseModule.class})
    public interface ApplicationComponent extends BrainPhaserComponent {
    }

    /**
     * Licensed under CC BY-SA (c) 2012 Muhammad Nabeel Arif
     * http://stackoverflow.com/questions/4605527/converting-pixels-to-dp
     * <p>
     * This method converts device specific pixels to density independent pixels.
     *
     * @param px A value in px (pixels) unit. Which we need to convert into dp
     * @return A float value to represent dp equivalent to px value
     */
    public float convertPixelsToDp(float px) {
        Resources resources = getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        float dp = px / ((float) metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT);
        return dp;
    }
}