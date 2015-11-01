package com.mrg.joe.spacelord2.Weapon;

import com.mrg.joe.spacelord2.GameConstants;
import com.mrg.joe.spacelord2.Player;

import java.util.Iterator;

/**
 * Created by Joe on 9/10/2015.
 */
public class PlayerRocketWeapon extends Weapon {

    private Player player;


    public PlayerRocketWeapon(Player player) {

        this.player = player;
        this.damage = GameConstants.player_shotgun_damage;
    }

    @Override
    public void update(float delta) {


        if (this.isOn) {

            // creates new projectiles every interval in seconds
            if (System.nanoTime() > interval + (2 * GameConstants.projectile_creation_interval * 1000000000)) {

                projectiles.add(new PlayerRocketProjectile(this.player, ProjectilePosition.LEFT_ROCKET));
                projectiles.add(new PlayerRocketProjectile(this.player, ProjectilePosition.RIGHT_ROCKET));

                interval = System.nanoTime();
            }

        }


        // update projectiles
        if (!projectiles.isEmpty()) {
            for (Iterator itr = projectiles.iterator(); itr.hasNext(); ) {
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
