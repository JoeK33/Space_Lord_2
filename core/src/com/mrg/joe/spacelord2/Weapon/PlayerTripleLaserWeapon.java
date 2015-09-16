package com.mrg.joe.spacelord2.Weapon;

import com.badlogic.gdx.Gdx;
import com.mrg.joe.spacelord2.GameConstants;
import com.mrg.joe.spacelord2.Player;

import java.util.Iterator;

/**
 * Created by Joe on 9/14/2015.
 */
public class PlayerTripleLaserWeapon extends Weapon {


    private Player player;


    public PlayerTripleLaserWeapon(Player player) {

        this.player = player;
        this.damage = GameConstants.player_laser_damage;
    }


    @Override
    public void update(float delta) {



        // creates new projectiles every interval in seconds
        if(this.isOn) {
            if (System.nanoTime() > interval + (GameConstants.projectile_creation_interval * 1000000000)) {
                projectiles.add(new PlayerProjectile(GameConstants.player_laser_damage,new float[] {player.getCenterX() - (player.getWidth()/4), player.getY() + player.getHeight()}));
                projectiles.add(new PlayerProjectile(GameConstants.player_laser_damage,new float[] {player.getCenterX() + (player.getWidth()/4), player.getY() + player.getHeight()}));


                interval = System.nanoTime();
            }
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
