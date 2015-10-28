package com.mrg.joe.spacelord2.Weapon;

import com.mrg.joe.spacelord2.Enemy.EnemyBoss;

import java.util.Iterator;

/**
 * Created by Joe on 8/28/2015.
 */
public class EnemyBossWeapon extends Weapon {


    private EnemyBoss enemy;

    public EnemyBossWeapon(EnemyBoss enemy){

        this.enemy = enemy;


    }



    @Override
    public void update(float delta) {

        if(this.isOn) {


            // creates new projectiles every interval in seconds
            if (System.nanoTime() > interval + (2 * 1000000000L )) {
                projectiles.add(new EnemyMgProjectile(enemy.getWeapon1Pos(), enemy.assets));
                projectiles.add(new EnemyMgProjectile(enemy.getWeapon2Pos(), enemy.assets));
                projectiles.add(new EnemyBallProjectile(enemy.getWeapon3Pos(), enemy.assets));
                interval = System.nanoTime();
            }

        }

        if (!projectiles.isEmpty()) {
            for (Iterator itr = projectiles.iterator(); itr.hasNext();) {
                Projectile p = (Projectile)itr.next();
                p.update(delta);


                // remove projectiles that fly off screen
                if ((p.getY() + p.getHeight()) < 0) {
                    p.remove();
                    itr.remove();

                }


            }

        }
    }
}
