package com.questions.game;

import android.app.Application;
import android.content.Context;

import org.mockito.Mockito;

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

/**
 * Created by funkv on 07.03.2016.
 */
@Module
public class TestAppModule {
    BrainPhaserApplication mApplication;

    public TestAppModule(BrainPhaserApplication application) {
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
        return Mockito.mock(UserLogicFactory.class);
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
