package com.mrg.joe.spacelord2.Weapon;

import com.mrg.joe.spacelord2.GameConstants;
import com.mrg.joe.spacelord2.Player;

/**
 * Created by Joe on 8/28/2015.
 * This is the default player projectile from the main gun.
 */
public class PlayerProjectile extends Projectile {


    public PlayerProjectile(Player player, int damage) {

        super(player.getPlayerNosePosition(), damage, player.assets, "weapons/laser.png");
        this.sprite.setX(this.getX() - this.sprite.getWidth() / 2);

    }

    public PlayerProjectile(Player player, int damage, float[] position) {

        super(position, damage, player.assets, "weapons/laser.png");
        this.sprite.setX(this.getX() - this.sprite.getWidth() / 2);

    }


    @Override
    public void update(float delta) {
        // projectile behaviors here
        this.sprite.setY(this.sprite.getY() + (delta * GameConstants.projectile_speed));

        if (this.sprite.getY() > GameConstants.GAME_HEIGHT) {
            this.remove();
        }

    }


}
