package com.questions.game.activities.main;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.questions.game.BrainPhaserApplication;
import com.questions.game.BrainPhaserComponent;
import com.questions.game.R;
import com.questions.game.activities.BrainPhaserActivity;
import com.questions.game.activities.createuser.CreateUserActivity;
import com.questions.game.activities.login.LoginActivity;
import com.questions.game.database.ChallengeDataSource;
import com.questions.game.logic.UserManager;
import com.questions.game.logic.fileimport.FileImport;

import java.io.InputStream;

import javax.inject.Inject;

/**
 * Created by funkv on 29.02.2016.
 * <p>
 * The activity redirects to user creation on first launch. On later launches it loads last selected
 * user and redirects to the main activity.
 */
public class ProxyActivity extends BrainPhaserActivity {
    @Inject
    UserManager mUserManager;
    @Inject
    ChallengeDataSource mChallengeDataSource;

    /**
     * {@inheritDoc}
     */
    @Override
    protected void injectComponent(BrainPhaserComponent component) {
        component.inject(this);
    }

    /**
     * This method is called when the activity is created
     *
     * @param savedInstanceState handed over to super constructor
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_proxy);
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(getApplication());

        BrainPhaserApplication application = (BrainPhaserApplication) getApplication();
        if (mUserManager.logInLastUser()) {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            intent.putExtra(MainActivity.EXTRA_SHOW_LOGGEDIN_SNACKBAR, true);

            startActivity(intent);
            finish();
        } else {
            // Import challenges if the database does not include any
            if (mChallengeDataSource.getAll().size() == 0) {
                InputStream is = getResources().openRawResource(R.raw.challenges);
                try {
                    FileImport.importBPC(is, application);
                } catch (Exception e) {
                    throw new RuntimeException("An unexpected error has occured when trying to add " +
                            "example challenges!");
                }
            }

            //startActivity(new Intent(Intent.ACTION_INSERT, Uri.EMPTY, getApplicationContext(), CreateUserActivity.class));
            startActivity(new Intent(Intent.ACTION_INSERT, Uri.EMPTY, getApplicationContext(), LoginActivity.class));
            finish();
        }
    }
}