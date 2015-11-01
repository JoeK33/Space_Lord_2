package com.mrg.joe.spacelord2.Weapon;

import com.mrg.joe.spacelord2.Assets;
import com.mrg.joe.spacelord2.GameConstants;
import com.mrg.joe.spacelord2.SpaceLord2;

/**
 * Created by Joe on 8/30/2015.
 * This projectile is aimed at the player's location when fired.
 */
public class EnemyBallProjectile extends Projectile {


    private double velX;
    private double velY;


    public EnemyBallProjectile(float[] pos, Assets manager) {
        super(pos, 1, manager, "weapons/enemy_ball_projectile.png");

        // aims at player
        float deltax = SpaceLord2.player.getPlayerNosePosition()[0] - (this.sprite.getX() + this.sprite.getWidth() / 2);
        float deltay = SpaceLord2.player.getPlayerNosePosition()[1] - (this.sprite.getY());
        double distance = (Math.sqrt((deltax * deltax) + (deltay * deltay)));
        velX = (deltax / distance);
        velY = (deltay / distance);
    }

    @Override
    public void update(float delta) {
        this.sprite.setPosition((float) (this.getX() + (velX * (delta * GameConstants.projectile_speed))), (float) (this.sprite.getY() + (velY * (delta * GameConstants.projectile_speed))));

        if (this.sprite.getY() < -200) {

            this.remove();
        }
    }
}

