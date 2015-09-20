package com.mrg.joe.spacelord2.Weapon;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Timer;
import com.mrg.joe.spacelord2.Enemy.Enemy;
import com.mrg.joe.spacelord2.GameConstants;

import java.util.Iterator;

/**
 * Created by Joe on 8/28/2015.
 */
public class EnemyMachineGunWeapon extends Weapon {

private int small_projectile_width = 12;

    private Enemy enemy;
    private int shot_offset;


    public EnemyMachineGunWeapon(Enemy enemy){

        this.enemy = enemy;
        shot_offset = (int)(Math.random() * 10) * 100000;



    }


    @Override
    public void update(float delta) {


        if(this.isOn){

        // creates new projectiles every interval in seconds
        if ( System.nanoTime() > interval + (4L * 1000000000L +shot_offset )) {
            projectiles.add(new EnemyMgProjectile(new float[]{enemy.getNosePos()[0] - small_projectile_width, enemy.getNosePos()[1]}));

            interval = System.nanoTime();


            Timer timer = new Timer();
            timer.scheduleTask(new Timer.Task() {
                @Override
                public void run() {
                    if(enemy.isAlive()) {
                        projectiles.add(new EnemyMgProjectile(new float[]{enemy.getNosePos()[0] - small_projectile_width, enemy.getNosePos()[1]}));
                    }

                }
            }, .5f);

            Timer timer2 = new Timer();
            timer2.scheduleTask(new Timer.Task() {
                @Override
                public void run() {

                    if(enemy.isAlive()) {
                        projectiles.add(new EnemyMgProjectile(new float[]{enemy.getNosePos()[0] - small_projectile_width, enemy.getNosePos()[1]}));
                    }
                }
            }, 1f);

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
