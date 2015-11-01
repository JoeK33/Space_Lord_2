package com.mrg.joe.spacelord2.Weapon;

import com.mrg.joe.spacelord2.GameConstants;
import com.mrg.joe.spacelord2.Player;

/**
 * Created by Joe on 9/29/2015.
 */
public class PlayerSineProjectile extends Projectile {

    private float degrees;
    private ProjectilePosition position;


    public PlayerSineProjectile(Player player, ProjectilePosition position) {

        // used same texture as shotgun
        super(player.getPlayerNosePosition(), GameConstants.player_sine_damage, player.assets, "weapons/player_shotgun_projectile.png");
        this.position = position;
        this.sprite.setX(this.getX() - this.sprite.getWidth() / 2);
    }

    @Override
    public void update(float delta) {
        // projectile behaviors here
        this.sprite.setY(this.sprite.getY() + (delta * GameConstants.projectile_speed));
        double wiggle = Math.cos(degrees) * (600 * delta);

        if (this.position == ProjectilePosition.FAR_LEFT) {


            this.sprite.setX(this.getX() + ((float) (wiggle)));
        } else if (this.position == ProjectilePosition.FAR_RIGHT) {

            this.sprite.setX(this.getX() - ((float) (wiggle)));
        }

        degrees += .05f;
        if (degrees > 360) {
            degrees = 0;
        }


        if (this.sprite.getY() > GameConstants.GAME_HEIGHT) {
            this.remove();
        }

    }

}
