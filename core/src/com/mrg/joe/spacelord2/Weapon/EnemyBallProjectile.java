package com.mrg.joe.spacelord2.Weapon;

import com.badlogic.gdx.graphics.Texture;
import com.mrg.joe.spacelord2.GameConstants;
import com.mrg.joe.spacelord2.SpaceLord2;

/**
 * Created by Joe on 8/30/2015.
 */
public class EnemyBallProjectile extends Projectile {

    boolean movingLeft;
    float playerx;


    public EnemyBallProjectile(float[] pos) {
        super(pos, 1, new Texture("weapons/enemy_ball_projectile.png"));
        this.playerx = SpaceLord2.player.getPlayerNosePosition()[0];
    }

    @Override
    public void update(float delta) {

        // projectile behaviors here

        if (this.playerx < this.getCenterX()) {
            movingLeft = true;
        } else {
            movingLeft = false;
        }


        this.sprite.setPosition(this.getX(), this.sprite.getY() - (delta * GameConstants.projectile_speed));


        if (movingLeft) {
            this.sprite.setX(this.getX() - (delta*75f));
        } else {
            this.sprite.setX(this.getX() + (delta*75f));
        }





    if(this.sprite.getY()<-200)

    {
        this.remove();
    }
}

    }

