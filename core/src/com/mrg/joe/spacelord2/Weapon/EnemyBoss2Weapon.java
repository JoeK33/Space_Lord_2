package com.mrg.joe.spacelord2.Weapon;

import com.badlogic.gdx.utils.Timer;
import com.mrg.joe.spacelord2.Enemy.EnemyBoss2;

import java.util.Iterator;

/**
 * Created by Joe on 9/17/2015.  Offset is the offset from the bottom left of the sprite to place the weapon.
 */
public class EnemyBoss2Weapon extends Weapon {

    private int small_projectile_width = 12;



    private EnemyBoss2 enemy;
    private int[] offset;
    private int shot_offset;
    public EnemyBoss2Weapon(EnemyBoss2 enemy, int offset1, int offset2, int offset3){

        this.enemy = enemy;
        offset = new int[3];
        this.offset[0] = offset1;
        this.offset[1] = offset2;
        this.offset[2] = offset3;
        shot_offset = (int)(Math.random() * 10) * 100000;


    }

    @Override
    public void update(float delta) {


        if(this.isOn){

            // creates new projectiles every interval in seconds
            if ( System.nanoTime() > interval + (4L * 1000000000L) + shot_offset) {

                for (int i = 0; i < 3; i++){

                projectiles.add(new EnemyMgProjectile(new float[]{enemy.getX() - small_projectile_width + offset[i], enemy.getNosePos()[1]}));

                interval = System.nanoTime();


                Timer timer = new Timer();
                    final int finalI = i;
                    timer.scheduleTask(new Timer.Task() {
                    @Override
                    public void run() {
                        if(enemy.isAlive()) {
                            projectiles.add(new EnemyMgProjectile(new float[]{enemy.getX() - small_projectile_width + offset[finalI], enemy.getNosePos()[1]}));
                        }

                    }
                }, .2f);

                Timer timer2 = new Timer();
                    final int finalI1 = i;
                    timer2.scheduleTask(new Timer.Task() {
                    @Override
                    public void run() {

                        if(enemy.isAlive()) {
                            projectiles.add(new EnemyMgProjectile(new float[]{enemy.getX() - small_projectile_width + offset[finalI1], enemy.getNosePos()[1]}));
                        }
                    }
                }, .4f);

                    Timer timer3 = new Timer();
                    final int finalI2 = i;
                    timer3.scheduleTask(new Timer.Task() {
                        @Override
                        public void run() {

                            if(enemy.isAlive()) {
                                projectiles.add(new EnemyMgProjectile(new float[]{enemy.getX() - small_projectile_width + offset[finalI2], enemy.getNosePos()[1]}));
                            }
                        }
                    }, .6f);

            }}}

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
