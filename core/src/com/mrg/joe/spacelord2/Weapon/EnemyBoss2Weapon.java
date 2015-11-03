package com.mrg.joe.spacelord2.Weapon;

import com.badlogic.gdx.utils.Timer;
import com.mrg.joe.spacelord2.Enemy.EnemyBoss2;
import com.mrg.joe.spacelord2.SpaceLord2;

import java.util.Iterator;

/**
 * Created by Joe on 9/17/2015.  Offset is the offset from the bottom left of the sprite to place the weapon.
 * This boss fires just like a row of mg enemies
 */
public class EnemyBoss2Weapon extends Weapon {

    private int smallProjectileWidth = 12;

    private EnemyBoss2 enemy;
    private int[] offset;
    private int shotOffset;

    public EnemyBoss2Weapon(EnemyBoss2 enemy, int offset1, int offset2, int offset3) {

        this.enemy = enemy;
        offset = new int[3];
        this.offset[0] = offset1;
        this.offset[1] = offset2;
        this.offset[2] = offset3;
        shotOffset = (int) (Math.random() * 10) * 100000;


    }

    @Override
    public void update(float delta) {

        if (this.isOn) {

            // creates new projectiles every interval in seconds
            if (System.nanoTime() > interval + (2 * 1000000000L) + shotOffset) {

                for (int i = 0; i < 3; i++) {

                    projectiles.add(new EnemyMgProjectile(new float[]{enemy.getX() - smallProjectileWidth + offset[i],
                            enemy.getNosePos()[1]}, enemy.assets));

                    interval = System.nanoTime();


                    Timer timer = new Timer();
                    final int finalI = i;
                    timer.scheduleTask(new Timer.Task() {
                        @Override
                        public void run() {
                            if (enemy.isAlive() && !SpaceLord2.hud.isPaused()) {
                                projectiles.add(new EnemyMgProjectile(new float[]{enemy.getX() - smallProjectileWidth + offset[finalI],
                                        enemy.getNosePos()[1]}, enemy.assets));
                            }

                        }
                    }, .2f);

                    Timer timer2 = new Timer();
                    final int finalI1 = i;
                    timer2.scheduleTask(new Timer.Task() {
                        @Override
                        public void run() {

                            if (enemy.isAlive() && !SpaceLord2.hud.isPaused()) {
                                projectiles.add(new EnemyMgProjectile(new float[]{enemy.getX() - smallProjectileWidth + offset[finalI1],
                                        enemy.getNosePos()[1]}, enemy.assets));
                            }
                        }
                    }, .4f);

                    Timer timer3 = new Timer();
                    final int finalI2 = i;
                    timer3.scheduleTask(new Timer.Task() {
                        @Override
                        public void run() {

                            if (enemy.isAlive() && !SpaceLord2.hud.isPaused()) {
                                projectiles.add(new EnemyMgProjectile(new float[]{enemy.getX() - smallProjectileWidth + offset[finalI2],
                                        enemy.getNosePos()[1]}, enemy.assets));
                            }
                        }
                    }, .6f);

                }
            }
        }

        if (!projectiles.isEmpty()) {
            for (Iterator itr = projectiles.iterator(); itr.hasNext();) {
                Projectile p = (Projectile) itr.next();
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
