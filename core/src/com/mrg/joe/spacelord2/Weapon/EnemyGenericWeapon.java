package com.mrg.joe.spacelord2.Weapon;

import com.mrg.joe.spacelord2.Enemy.Enemy;

import java.util.Iterator;

/**
 * Created by Joe on 9/14/2015.
 */
public class EnemyGenericWeapon extends Weapon {


    private int small_projectile_width = 12;

    private Enemy enemy;

    public EnemyGenericWeapon(Enemy enemy){

        this.enemy = enemy;


    }


    @Override
    public void update(float delta) {


        if(this.isOn){

            if((int)(Math.random() * 70) == 5) {
                // creates new projectiles every interval in seconds
                if (System.nanoTime() > interval + (4L * 1000000000L)) {
                    if((int)(Math.random() * 10) == 5) {
                    projectiles.add(new EnemyMgProjectile(new float[]{enemy.getNosePos()[0] - small_projectile_width, enemy.getNosePos()[1]}));

                    interval = System.nanoTime();
                }
            }

        }}

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
