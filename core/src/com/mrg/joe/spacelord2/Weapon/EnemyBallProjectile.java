package com.mrg.joe.spacelord2.Weapon;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.mrg.joe.spacelord2.GameConstants;
import com.mrg.joe.spacelord2.SpaceLord2;

/**
 * Created by Joe on 8/30/2015.
 */
public class EnemyBallProjectile extends Projectile {


    private float deltax;
    private float deltay;
    private double distance;
    private double velX;
    private double velY;


    public EnemyBallProjectile(float[] pos) {
        super(pos, 1, new Texture(Gdx.files.internal("weapons/enemy_ball_projectile.png")));
        this.deltax = SpaceLord2.player.getPlayerNosePosition()[0] -this.sprite.getX() + this.sprite.getWidth()/2;
        this.deltay = SpaceLord2.player.getPlayerNosePosition()[1] - SpaceLord2.player.getHeight()/2 - this.sprite.getY();
        this.distance = (Math.sqrt((deltax * deltax) + (deltay * deltay)));
        velX = (deltax/distance);
        velY = (deltay/distance);
    }

    @Override
    public void update(float delta) {

        // projectile behaviors here



        this.sprite.setPosition((float)(this.getX() + ((velX) * (delta * GameConstants.projectile_speed))), (this.sprite.getY() + (float)(velY * (delta * GameConstants.projectile_speed))));






    if(this.sprite.getY()<-200)

    {
        this.remove();
    }
}

    }

