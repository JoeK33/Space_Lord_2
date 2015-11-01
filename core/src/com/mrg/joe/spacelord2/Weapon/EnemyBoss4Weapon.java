package com.mrg.joe.spacelord2.Weapon;

import com.badlogic.gdx.utils.Timer;
import com.mrg.joe.spacelord2.Enemy.EnemyBoss4;
import com.mrg.joe.spacelord2.SpaceLord2;

import java.util.Iterator;

/**
 * Created by Joe on 9/19/2015.
 */
public class EnemyBoss4Weapon extends Weapon {
    private EnemyBoss4 enemy;
    int projectile_Halfsize;
    int shot_offset;

    public EnemyBoss4Weapon(EnemyBoss4 enemy) {
        Projectile p = new EnemyBallProjectile(new float[]{-200, -200}, enemy.assets);
        projectile_Halfsize = (int) p.getHeight() / 2;

        this.enemy = enemy;
        shot_offset = (int) (Math.random() * 10) * 100000;

    }

    @Override
    public void update(float delta) {

        if (this.isOn) {

            // creates new projectiles every interval in seconds
            if (System.nanoTime() > interval + (3L * 1000000000L + shot_offset)) {
                projectiles.add(new EnemyMgProjectile(new float[]{enemy.getNosePos()[0] - 130, enemy.getNosePos()[1] + 130}, enemy.assets));
                projectiles.add(new EnemyMgProjectile(new float[]{enemy.getNosePos()[0] - projectile_Halfsize + 130, enemy.getNosePos()[1] + 130}, enemy.assets));

                interval = System.nanoTime();


                Timer timer = new Timer();
                timer.scheduleTask(new Timer.Task() {
                    @Override
                    public void run() {
                        if (enemy.isAlive() && !SpaceLord2.hud.isPaused()) {
                            projectiles.add(new EnemyMgProjectile(new float[]{enemy.getNosePos()[0] - 130, enemy.getNosePos()[1] + 130}, enemy.assets));
                            projectiles.add(new EnemyMgProjectile(new float[]{enemy.getNosePos()[0] - projectile_Halfsize + 130, enemy.getNosePos()[1] + 130}, enemy.assets));
                        }

                    }
                }, .2f);

                Timer timer2 = new Timer();
                timer2.scheduleTask(new Timer.Task() {
                    @Override
                    public void run() {

                        if (enemy.isAlive() && !SpaceLord2.hud.isPaused()) {
                            projectiles.add(new EnemyMgProjectile(new float[]{enemy.getNosePos()[0] - 130, enemy.getNosePos()[1] + 130}, enemy.assets));
                            projectiles.add(new EnemyMgProjectile(new float[]{enemy.getNosePos()[0] - projectile_Halfsize + 130, enemy.getNosePos()[1] + 130}, enemy.assets));
                        }
                    }
                }, .4f);

                Timer timer3 = new Timer();
                timer3.scheduleTask(new Timer.Task() {
                    @Override
                    public void run() {

                        if (enemy.isAlive() && !SpaceLord2.hud.isPaused()) {
                            projectiles.add(new EnemyBallProjectile(new float[]{enemy.getNosePos()[0] - 12, enemy.getNosePos()[1]}, enemy.assets));
                        }
                    }
                }, 1f);


            }
        }

        if (!projectiles.isEmpty()) {
            for (Iterator itr = projectiles.iterator(); itr.hasNext(); ) {
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
