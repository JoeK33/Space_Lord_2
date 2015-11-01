package com.mrg.joe.spacelord2;

/**
 * Created by Joe on 10/14/2015.
 * Interface to access google play services in the android launcher class
 */
public interface ActionResolver {

    void submitScore(int score);

    void showLeaderboard();

    void showAchievements();

    void signIn();

    boolean signedIn();
}
