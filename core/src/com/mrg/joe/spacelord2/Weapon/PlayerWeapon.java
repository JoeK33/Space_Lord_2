package com.mrg.joe.spacelord2.Weapon;

import com.mrg.joe.spacelord2.GameConstants;
import com.mrg.joe.spacelord2.Player;

import java.util.Iterator;

/**
 * Created by Joe on 8/28/2015. Default weapon
 */
public class PlayerWeapon extends Weapon {

    private Player player;


    public PlayerWeapon(Player player) {

        this.player = player;
        this.damage = GameConstants.PLAYER_LASER_DAMAGE;
    }

    @Override
    public void update(float delta) {


        // creates new projectiles every interval in seconds
        if (this.isOn) {
            if (System.nanoTime() > interval + (GameConstants.PROJECTILE_CREATION_INTERVAL * 1000000000)) {
                projectiles.add(new PlayerProjectile(this.player, GameConstants.PLAYER_LASER_DAMAGE));

                interval = System.nanoTime();
            }
        }

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


