package com.mrg.joe.spacelord2.Weapon;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.mrg.joe.spacelord2.GameConstants;
import com.mrg.joe.spacelord2.Player;

/**
 * Created by Joe on 9/10/2015.
 */
public class PlayerShotgunProjectile extends Projectile {

    private ProjectilePosition position;


    public PlayerShotgunProjectile(Player player, ProjectilePosition position) {

        super(player.getPlayerNosePosition(),  GameConstants.player_shotgun_damage, new Texture("weapons/player_shotgun_projectile.png"));
        this.position = position;


        this.sprite.setX(this.getX() - this.sprite.getWidth()/2);
    }

    @Override
    public void update(float delta){
        // projectile behaviors here
        this.sprite.setY(this.sprite.getY() + (delta * GameConstants.projectile_speed));


        if(this.position == ProjectilePosition.FAR_LEFT){

            this.sprite.setX(this.sprite.getX() - (100 * delta));

        }else if (this.position == ProjectilePosition.INNER_LEFT){

            this.sprite.setX(this.sprite.getX() - (50 * delta));

        }else if (this.position == ProjectilePosition.INNER_RIGHT){

            this.sprite.setX(this.sprite.getX() + (50 * delta));

        }else if (this.position == ProjectilePosition.FAR_RIGHT){

            this.sprite.setX(this.sprite.getX() + (100 * delta));

        }




        if(this.sprite.getY() > Gdx.graphics.getHeight()){
            this.remove();
        }

    }


}