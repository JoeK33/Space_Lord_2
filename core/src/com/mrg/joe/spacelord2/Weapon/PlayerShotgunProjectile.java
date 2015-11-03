package com.mrg.joe.spacelord2.Weapon;

import com.mrg.joe.spacelord2.GameConstants;
import com.mrg.joe.spacelord2.Player;

/**
 * Created by Joe on 9/10/2015.
 */
public class PlayerShotgunProjectile extends Projectile {

    private ProjectilePosition position;


    public PlayerShotgunProjectile(Player player, ProjectilePosition position) {

        super(player.getPlayerNosePosition(), GameConstants.PLAYER_SHOTGUN_DAMAGE, player.assets, "weapons/player_shotgun_projectile.png");
        this.position = position;


        this.sprite.setX(this.getX() - this.sprite.getWidth() / 2);
    }

    @Override
    public void update(float delta) {
        // projectile behaviors here
        this.sprite.setY(this.sprite.getY() + (delta * GameConstants.PROJECTILE_SPEED));

        // 4 porrible shots from the shotgun
        if (this.position == ProjectilePosition.FAR_LEFT) {
            this.sprite.setX(this.sprite.getX() - (100 * delta));
        } else if (this.position == ProjectilePosition.INNER_LEFT) {
            this.sprite.setX(this.sprite.getX() - (50 * delta));
        } else if (this.position == ProjectilePosition.INNER_RIGHT) {
            this.sprite.setX(this.sprite.getX() + (50 * delta));
        } else if (this.position == ProjectilePosition.FAR_RIGHT) {
            this.sprite.setX(this.sprite.getX() + (100 * delta));
        }


        if (this.sprite.getY() > GameConstants.GAME_HEIGHT) {
            this.remove();
        }

    }


}
