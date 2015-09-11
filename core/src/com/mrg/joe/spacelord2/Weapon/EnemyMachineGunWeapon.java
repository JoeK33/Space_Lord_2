package com.mrg.joe.spacelord2.Weapon;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.mrg.joe.spacelord2.Enemy.Enemy;
import com.mrg.joe.spacelord2.GameConstants;

import java.util.Iterator;

/**
 * Created by Joe on 8/28/2015.
 */
public class EnemyMachineGunWeapon extends Weapon {



    private Enemy enemy;

    public EnemyMachineGunWeapon(Enemy enemy){

        this.enemy = enemy;


    }


    @Override
    public void update(float delta) {


        if(this.isOn){

        // creates new projectiles every interval in seconds
        if ( System.nanoTime() > interval + (5L * 1000000000L )) {
            projectiles.add(new EnemyMgProjectile(enemy.getNosePos()));

            interval = System.nanoTime();
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
