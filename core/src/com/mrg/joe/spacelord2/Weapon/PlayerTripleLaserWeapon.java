package com.mrg.joe.spacelord2.Weapon;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Timer;
import com.mrg.joe.spacelord2.GameConstants;
import com.mrg.joe.spacelord2.Player;
import com.mrg.joe.spacelord2.SpaceLord2;

import java.util.Iterator;

/**
 * Created by Joe on 9/14/2015.
 */
public class PlayerTripleLaserWeapon extends Weapon {


    private Player player;


    public PlayerTripleLaserWeapon(Player player) {

        this.player = player;
        this.damage = GameConstants.player_minigun_damage;
    }


    @Override
    public void update(float delta) {



        // creates new projectiles every interval in seconds
        if(this.isOn) {
            if (System.nanoTime() > interval + (GameConstants.projectile_creation_interval * 1000000000)) {
                projectiles.add(new PlayerProjectile(GameConstants.player_minigun_damage, new float[]{player.getCenterX() - (player.getWidth() / 4), player.getY() + player.getHeight()}));

                // offset firing to make it look better
                Timer timer = new Timer();
                timer.scheduleTask(new Timer.Task() {
                    @Override
                    public void run() {
                        if (player.isAlive() && !SpaceLord2.hud.isPaused()) {
                            projectiles.add(new PlayerProjectile(GameConstants.player_minigun_damage, new float[]{player.getCenterX() + (player.getWidth() / 4), player.getY() + player.getHeight()}));

                        }

                    }
                }, 1f);

                Timer timer2 = new Timer();
                timer2.scheduleTask(new Timer.Task() {
                    @Override
                    public void run() {
                        if (player.isAlive() && !SpaceLord2.hud.isPaused()) {
                            projectiles.add(new PlayerProjectile(GameConstants.player_minigun_damage, player.getPlayerNosePosition()));

                        }

                    }
                }, 2f);


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
