package com.mrg.joe.spacelord2.android;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.games.Games;
import com.google.example.games.basegameutils.BaseGameUtils;
import com.mrg.joe.spacelord2.SpaceLord2Game;


public class AndroidLauncher extends AndroidApplication implements
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener {

    private GoogleApiClient mGoogleApiClient;
    ActionResolverAndroid mActionResolver;

    private static final String STATE_RESOLVING_ERROR = "resolving_error";


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        mActionResolver = new ActionResolverAndroid(this.getApplicationContext(), this);

        super.onCreate(savedInstanceState);
        AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
        initialize(new SpaceLord2Game(mActionResolver), config);

        // Create the Google Api Client with access to the Play Games services
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(Games.API).addScope(Games.SCOPE_GAMES)
                .build();


        mResolvingConnectionFailure = savedInstanceState != null
                && savedInstanceState.getBoolean(STATE_RESOLVING_ERROR, false);

    }

    @Override
    protected void onStart() {
        super.onStart();
        mGoogleApiClient.connect();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mGoogleApiClient.disconnect();

    }



    private boolean mResolvingConnectionFailure = false;
    private boolean mAutoStartSignInFlow = true;
    private boolean mSignInClicked = false;

    // Request code to use when launching the resolution activity
    private static final int REQUEST_RESOLVE_ERROR = 1001;
    private static final int RC_SIGN_IN = 9001;

    @Override
    public void onConnectionFailed(ConnectionResult result) {

        //  Toast.makeText(this.getContext(), result.toString(), Toast.LENGTH_SHORT).show();
        if (mResolvingConnectionFailure) {
            // Already resolving
            return;
        }

        // If the sign in button was clicked or if auto sign-in is enabled,
        // launch the sign-in flow
        if (mSignInClicked || mAutoStartSignInFlow) {
            mAutoStartSignInFlow = false;
            mSignInClicked = false;
            mResolvingConnectionFailure = true;

            // Attempt to resolve the connection failure using BaseGameUtils.
            if (!BaseGameUtils.resolveConnectionFailure(this,
                    mGoogleApiClient, result,
                    RC_SIGN_IN, String.valueOf(R.string.signin_other_error))) {
                mResolvingConnectionFailure = false;
            }
        }
    }


    public void signIn() {
        mSignInClicked = true;
        mGoogleApiClient.connect();
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(STATE_RESOLVING_ERROR, mResolvingConnectionFailure);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_RESOLVE_ERROR) {
            mResolvingConnectionFailure = false;
            if (resultCode == RESULT_OK) {
                // Make sure the app is not already connected or attempting to connect
                if (!mGoogleApiClient.isConnecting()
                        && !mGoogleApiClient.isConnected()) {
                    mGoogleApiClient.connect();
                }
            }
        }
    }

    @Override
    public void onConnectionSuspended(int i) {
        // Attempt to reconnect
        mGoogleApiClient.connect();

    }


    @Override
    public void onConnected(Bundle bundle) {

        // send in locally stored high score on connection.
        // this way if a player sets a high score while not connected it still reaches the leaderboard eventually
        SharedPreferences prefs = getSharedPreferences("preferences", MODE_PRIVATE);
        int score = prefs.getInt("highscore", 0);

        if (score > 0) {
            submitScore(score);
        }

    }

    public boolean signedIn() {
        return (mGoogleApiClient != null && mGoogleApiClient.isConnected());
    }

    public void submitScore(final int score) {

        if (signedIn()) {

            Games.Leaderboards.submitScore(mGoogleApiClient, getString(R.string.LEADERBOARD_ID), score);

            if (score >= 10000) {
                Games.Achievements.unlock(mGoogleApiClient, getString(R.string.achievement_id_1));
            }

            if (score >= 20000) {
                Games.Achievements.unlock(mGoogleApiClient, getString(R.string.achievement_id_2));
            }

            if (score >= 30000) {
                Games.Achievements.unlock(mGoogleApiClient, getString(R.string.achievement_id_3));
            }

            if (score >= 40000) {
                Games.Achievements.unlock(mGoogleApiClient, getString(R.string.achievement_id_4));
            }

            if (score >= 50000) {
                Games.Achievements.unlock(mGoogleApiClient, getString(R.string.achievement_id_5));
            }

        }
    }


    public void showLeaderboard() {

        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (signedIn()) {
                    startActivityForResult(Games.Leaderboards.getLeaderboardIntent(mGoogleApiClient, getString(R.string.LEADERBOARD_ID)),
                            1);
                } else {
                    BaseGameUtils.makeSimpleDialog(AndroidLauncher.this, getString(R.string.leaderboards_not_available)).show();
                }
            }
        });


    }

    public void showAchievements() {

        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {

                if (signedIn()) {

                    startActivityForResult(Games.Achievements.getAchievementsIntent(mGoogleApiClient),
                            2);

                } else {
                    BaseGameUtils.makeSimpleDialog(AndroidLauncher.this, getString(R.string.achievements_not_available)).show();
                }


            }
        });

    }
}
