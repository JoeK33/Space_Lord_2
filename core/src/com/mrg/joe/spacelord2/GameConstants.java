package com.mrg.joe.spacelord2;

/**
 * Created by Joe on 8/26/2015.
 */
public final class GameConstants {

    private GameConstants() {
        //not called
    }

    public static final int PROJECTILE_SPEED = 500;
    public static final int PLAYER_LASER_DAMAGE = 10;
    public static final int PLAYER_MINIGUN_DAMAGE = 5;
    public static final int PLAYER_SHOTGUN_DAMAGE = 5;
    public static final int PLAYER_ROCKET_DAMAGE = 20;
    public static final int PLAYER_SINE_DAMAGE = 2;


    // in seconds
    public static final float PROJECTILE_CREATION_INTERVAL = .5f;

    public static final int FIGHTER_HEALTH = 120;
    public static final int ENEMY_SMALL_HEALTH = 20;
    public static final int ENEMY_HUNTER_HEALTH = 100;
    public static final int ENEMY_MG_HEALTH = 100;
    public static final int ENEMY_BOSS_HEALTH = 1000;
    public static final int ENEMY_BOSS_2_HEALTH = 1000;
    public static final int ENEMY_BOSS_3_HEALTH = 1000;
    public static final int ENEMY_BOSS_4_HEALTH = 1000;
    public static final int ENEMY_BLASTER_HEALTH = 150;

    // gap in pixels between enemy rows
    public static final float ENEMY_ROW_GAP = 75;

    public static final int GAME_WIDTH = 1080;
    public static final int GAME_HEIGHT = 1920;


}
