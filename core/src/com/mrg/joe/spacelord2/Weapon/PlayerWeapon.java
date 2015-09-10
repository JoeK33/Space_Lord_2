package com.mrg.joe.spacelord2.Weapon;

import com.badlogic.gdx.Gdx;
import com.mrg.joe.spacelord2.GameConstants;
import com.mrg.joe.spacelord2.Player;

import java.util.Iterator;

/**
 * Created by Joe on 8/28/2015.
 */
public class PlayerWeapon extends Weapon {

    private Player player;


    public PlayerWeapon(Player player) {

        this.player = player;
        this.damage = GameConstants.player_laser_damage;
    }


    @Override
    public void update(float delta) {



        // creates new projectiles every interval in seconds
        if ( System.nanoTime() > interval + (GameConstants.projectile_creation_interval * 1000000000)) {
            projectiles.add(new PlayerProjectile(this.player, GameConstants.player_laser_damage));

            interval = System.nanoTime();
        }

        if (!projectiles.isEmpty()) {
            for (Iterator itr = projectiles.iterator(); itr.hasNext();) {
                Projectile p = (Projectile)itr.next();
                p.update(delta);


                // remove projectiles that fly off screen
                if ((p.getY() - p.getHeight()) > Gdx.graphics.getHeight()) {
                    p.remove();
                    itr.remove();

                }


            }

        }
    }

}
