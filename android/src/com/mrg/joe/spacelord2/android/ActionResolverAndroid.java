package com.mrg.joe.spacelord2.android;

import android.content.Context;

import com.mrg.joe.spacelord2.ActionResolver;

/**
 * Created by Joe on 10/14/2015.
 */
public class ActionResolverAndroid implements ActionResolver {

    Context context;
    AndroidLauncher launcher;

    public ActionResolverAndroid(Context context, AndroidLauncher launcher) {
        this.context = context;
        this.launcher = launcher;
    }


    @Override
    public void submitScore(int score) {
        launcher.submitScore(score);
    }

    @Override
    public void showLeaderboard() {
        launcher.showLeaderboard();
    }

    @Override
    public void showAchievements() {
        launcher.showAchievements();
    }

    @Override
    public void signIn() {
        launcher.signIn();
    }

    public boolean signedIn() {
        return launcher.signedIn();
    }
}
