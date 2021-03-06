package com.mrg.joe.spacelord2;

import com.mrg.joe.spacelord2.Enemy.Enemy;
import com.mrg.joe.spacelord2.Enemy.EnemyPools;
import com.mrg.joe.spacelord2.Weapon.Projectile;
import com.mrg.joe.spacelord2.Weapon.Weapon;

import java.util.Iterator;
import java.util.List;

/**
 * Created by Joe on 8/30/2015.
 * This class iterates through enemies and projectiles and handles hit detection and enemy damaging and removal.
 */
public class CollisionHandler {

    private EnemyPools pools;

    public CollisionHandler(EnemyPools pools) {

        this.pools = pools;

    }


    public void handle(float delta, Weapon[] weapons, List enemyProjectiles, List enemyList, Player player) {


        // iterate over all player projectles in play
        for (Iterator it = enemyList.iterator(); it.hasNext();) {
            Enemy e = (Enemy) it.next();

            // add the projectiles from an enemy's weapon to the master list
            if (e.getWeapon() != null) {
                enemyProjectiles.addAll(e.getWeapon().getProjectiles());
            }


            for (Weapon w : weapons) {
                List projectiles = w.getProjectiles();

                for (Iterator ite = projectiles.iterator(); ite.hasNext();) {
                    Projectile p = (Projectile) ite.next();

                    // if a player shot hits an enemy, damage the enemy and remove the shot
                    if (e.isColliding(p.getBoundingRectangle())) {
                        e.doDamage(p.getDamage());
                        player.addScore(p.getDamage());
                        // remove projectile from play
                        p.remove();
                        // remove projectile from master list
                        ite.remove();
                    }

                }

            }


            // purge dead enemies
            if (e.readyToRemove()) {

                player.addScore(e.getKillScore());
                pools.free(e);
                it.remove();


            }

            // remove enemies that move off screen
            if (e.getY() < -e.getHeight()) {
                e.setAlive(false);
                pools.free(e);
                it.remove();

            }
            // update the survivors
            e.update(delta);

        }

        // go over enemy projectiles to check player hit
        if (player.isAlive()) {
            for (Iterator i = enemyProjectiles.iterator(); i.hasNext();) {
                Projectile p = (Projectile) i.next();
                if (p.isColliding(player.getBoundingRectangle())) {
                    p.remove();
                    i.remove();
                    player.takeHit();
                }


            }
        }
    }


}
