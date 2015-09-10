package com.mrg.joe.spacelord2.Weapon;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.mrg.joe.spacelord2.GameConstants;

/**
 * Created by Joe on 8/28/2015.
 */
public class EnemyMgProjectile extends Projectile {



    public EnemyMgProjectile(float[] pos) {

        super(pos, 1, new Texture("weapons/enemy_mg_laser.png"));
    }

    @Override
    public void update(float delta){
        // projectile behaviors here
        this.sprite.setY(this.sprite.getY() - (delta * GameConstants.projectile_speed));

        if(this.sprite.getY() < -20){
            this.remove();
        }



    }



}
