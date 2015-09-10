package com.mrg.joe.spacelord2.Weapon;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.mrg.joe.spacelord2.GameConstants;
import com.mrg.joe.spacelord2.Player;

/**
 * Created by Joe on 8/28/2015.
 */
public class PlayerProjectile extends Projectile {


    public PlayerProjectile(Player player, int damage) {

        super(player.getPlayerNosePosition(), damage, new Texture("weapons/laser.png"));

    }

    @Override
    public void update(float delta){
        // projectile behaviors here
        this.sprite.setY(this.sprite.getY()+(delta * GameConstants.projectile_speed));

        if(this.sprite.getY() > Gdx.graphics.getHeight()){
            this.remove();
        }

    }



}
