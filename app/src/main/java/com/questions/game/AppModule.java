package com.questions.game;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

import com.questions.game.activities.playchallenge.AnswerFragmentFactory;
import com.questions.game.database.CompletionDataSource;
import com.questions.game.database.UserDataSource;
import com.questions.game.logic.CompletionLogic;
import com.questions.game.logic.SettingsLogic;
import com.questions.game.logic.UserLogicFactory;
import com.questions.game.logic.UserManager;
import com.questions.game.logic.statistics.ChartSettings;

/**
 * Created by funkv on 06.03.2016.
 *
 * Defines how instances are created for the App
 */
@Module
public class AppModule {
    BrainPhaserApplication mApplication;

    public AppModule(BrainPhaserApplication application) {
        mApplication = application;
    }

    @Provides
    @Singleton
    Application providesApplication() {
        return mApplication;
    }

    @Provides
    @Singleton
    BrainPhaserApplication providesBpApp() {
        return mApplication;
    }

    @Provides
    @Singleton
    UserManager providesUserManager(Application application, UserDataSource userDataSource) {
        return new UserManager(application, userDataSource);
    }

    @Provides
    @Singleton
    Context providesContext() {
        return mApplication.getApplicationContext();
    }

    @Provides
    @Singleton
    UserLogicFactory providesUserLogic(BrainPhaserApplication app) {
        UserLogicFactory factory =  new UserLogicFactory();
        app.getComponent().inject(factory);
        return factory;
    }

    @Provides
    @Singleton
    ChartSettings providesChartSettings(BrainPhaserApplication app) {
        return new ChartSettings(app);
    }

    @Provides
    @Singleton
    SettingsLogic providesSettingsLogic() {
        return new SettingsLogic();
    }

    @Provides
    @Singleton
    AnswerFragmentFactory providesFragmentFactory() { return new AnswerFragmentFactory(); }

    @Provides
    @Singleton
    CompletionLogic providesCompletionLogic(CompletionDataSource ds) {
        return new CompletionLogic(ds);
    }
}
