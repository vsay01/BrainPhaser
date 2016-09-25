package com.questions.game.activities.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ImageView;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.ProfileTracker;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.questions.game.BrainPhaserComponent;
import com.questions.game.BuildConfig;
import com.questions.game.R;
import com.questions.game.activities.BrainPhaserActivity;
import com.questions.game.activities.createuser.Avatars;
import com.questions.game.activities.main.MainActivity;
import com.questions.game.database.UserDataSource;
import com.questions.game.logic.UserManager;
import com.questions.game.model.User;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends BrainPhaserActivity {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.login_button)
    LoginButton mLoginButton;
    @Inject
    UserManager mUserManager;
    @Inject
    UserDataSource mUserDataSource;
    CallbackManager mCallbackManager;
    ProfileTracker mProfileTracker;

    AccessTokenTracker mAccessTokenTracker;

    /**
     * {@inheritDoc}
     */
    @Override
    protected void injectComponent(BrainPhaserComponent component) {
        component.inject(this);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);
        mCallbackManager = CallbackManager.Factory.create();
        mLoginButton.setReadPermissions(Arrays.asList("public_profile", "email"));

        mLoginButton.registerCallback(mCallbackManager, callback);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // if you don't add following block,
        // your registered `FacebookCallback` won't be called
        if (mCallbackManager.onActivityResult(requestCode, resultCode, data)) {
            return;
        }
    }

    private FacebookCallback<LoginResult> callback = new FacebookCallback<LoginResult>() {
        @Override
        public void onSuccess(LoginResult loginResult) {
            // App code
            fetchProfile();
        }

        @Override
        public void onCancel() {
            // App code
        }

        @Override
        public void onError(FacebookException exception) {
            // App code
        }
    };

    private void fetchProfile() {
        GraphRequest request = GraphRequest.newMeRequest(
                AccessToken.getCurrentAccessToken(),
                new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {
                        // this is where you should have the profile
                        try {
                            Log.v("fetched info", object.getString("id"));
                            Log.v("fetched info", object.getString("name"));
                            Log.v("fetched info", object.getJSONObject("picture").getJSONObject("data").getString("url"));
                            profileCreationFinished(object.getString("name"), object.getJSONObject("picture").getJSONObject("data").getString("url"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        Log.v("fetched info", response.toString());
                    }
                });
        Bundle parameters = new Bundle();
        parameters.putString("fields", "id,name,picture"); //write the fields you need
        request.setParameters(parameters);
        request.executeAsync();
    }

    /**
     * Called when the profile creation has been finished. Depending on the intent the activity was
     * called with, the user is created or updated.
     *
     * @param username           Username that was entered
     * @param avatarResourceName Resource name of the user's selected avatar
     */
    private void profileCreationFinished(String username, String avatarResourceName) {
        if (getIntent().getAction().equals(Intent.ACTION_INSERT)) {
            // Create user
            User user = new User();
            user.setAvatar(avatarResourceName);
            user.setName(username);
            mUserDataSource.create(user);

            // Login user and change to category selection
            mUserManager.switchUser(user);

            startActivity(new Intent(getApplicationContext(), MainActivity.class));
        }
        setResult(RESULT_OK);
        finish();
    }
}
