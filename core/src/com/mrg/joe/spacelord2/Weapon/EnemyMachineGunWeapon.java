package com.mrg.joe.spacelord2.Weapon;

import com.badlogic.gdx.utils.Timer;
import com.mrg.joe.spacelord2.Enemy.Enemy;
import com.mrg.joe.spacelord2.SpaceLord2;

import java.util.Iterator;

/**
 * Created by Joe on 8/28/2015.
 */
public class EnemyMachineGunWeapon extends Weapon {

    private int smallProjectileWidth = 12;

    private Enemy enemy;
    private int shotOffset;

    public EnemyMachineGunWeapon(Enemy enemy) {

        this.enemy = enemy;
        shotOffset = (int) (Math.random() * 10) * 100000;
    }

    @Override
    public void update(float delta) {

        if (this.isOn) {
            // creates new projectiles every interval in seconds
            if (System.nanoTime() > interval + (2L * 1000000000L + shotOffset)) {
                projectiles.add(new EnemyMgProjectile(new float[]{enemy.getNosePos()[0] - smallProjectileWidth,
                        enemy.getNosePos()[1]}, enemy.assets));

                interval = System.nanoTime();


                Timer timer = new Timer();
                timer.scheduleTask(new Timer.Task() {
                    @Override
                    public void run() {
                        if (enemy.isAlive() && !SpaceLord2.hud.isPaused()) {
                            projectiles.add(new EnemyMgProjectile(new float[]{enemy.getNosePos()[0] - smallProjectileWidth,
                                    enemy.getNosePos()[1]}, enemy.assets));
                        }
                    }
                }, .5f);

                Timer timer2 = new Timer();
                timer2.scheduleTask(new Timer.Task() {
                    @Override
                    public void run() {

                        if (enemy.isAlive() && !SpaceLord2.hud.isPaused()) {
                            projectiles.add(new EnemyMgProjectile(new float[]{enemy.getNosePos()[0] - smallProjectileWidth,
                                    enemy.getNosePos()[1]}, enemy.assets));
                        }
                    }
                }, 1f);
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
