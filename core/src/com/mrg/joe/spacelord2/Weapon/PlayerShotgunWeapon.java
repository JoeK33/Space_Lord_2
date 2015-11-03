package com.mrg.joe.spacelord2.Weapon;

import com.mrg.joe.spacelord2.GameConstants;
import com.mrg.joe.spacelord2.Player;

import java.util.Iterator;

/**
 * Created by Joe on 9/10/2015.
 */
public class PlayerShotgunWeapon extends Weapon {

    private Player player;


    public PlayerShotgunWeapon(Player player) {

        this.player = player;
        this.damage = GameConstants.PLAYER_SHOTGUN_DAMAGE;
    }

    @Override
    public void update(float delta) {

        if (this.isOn) {
            // creates new projectiles every interval in seconds
            if (System.nanoTime() > interval + (3 * GameConstants.PROJECTILE_CREATION_INTERVAL * 1000000000)) {
                projectiles.add(new PlayerShotgunProjectile(this.player, ProjectilePosition.FAR_RIGHT));
                projectiles.add(new PlayerShotgunProjectile(this.player, ProjectilePosition.INNER_RIGHT));
                projectiles.add(new PlayerShotgunProjectile(this.player, ProjectilePosition.INNER_LEFT));
                projectiles.add(new PlayerShotgunProjectile(this.player, ProjectilePosition.FAR_LEFT));
                interval = System.nanoTime();
            }
        }


        // update projectiles
        if (!projectiles.isEmpty()) {
            for (Iterator itr = projectiles.iterator(); itr.hasNext();) {
                Projectile p = (Projectile) itr.next();
                p.update(delta);
                // remove projectiles that fly off screen
                if ((p.getY() - p.getHeight()) > GameConstants.GAME_HEIGHT) {
                    p.remove();
                    itr.remove();

                }
            }

        }
    }

}



