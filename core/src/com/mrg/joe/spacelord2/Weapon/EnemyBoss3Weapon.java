package com.mrg.joe.spacelord2.Weapon;

import com.mrg.joe.spacelord2.Enemy.EnemyBoss;
import com.mrg.joe.spacelord2.Enemy.EnemyBoss3;

import java.util.Iterator;

/**
 * Created by Joe on 9/17/2015.
 */
public class EnemyBoss3Weapon extends  Weapon {

    private EnemyBoss3 enemy;
    int projectile_size;

    public EnemyBoss3Weapon(EnemyBoss3 enemy){
        Projectile p = new EnemyBallProjectile(new float[]{-200,-200});
        projectile_size = (int)p.getHeight();

        this.enemy = enemy;




    }



    @Override
    public void update(float delta) {

        if(this.isOn) {


            // creates new projectiles every interval in seconds
            if (System.nanoTime() > interval + ( 1000000000L )) {

                projectiles.add(new EnemyBallProjectile(new float[]{enemy.getX(),enemy.getY() - projectile_size}));
                projectiles.add(new EnemyBallProjectile(new float[]{enemy.getX() + enemy.getWidth() - projectile_size,enemy.getY() - projectile_size}));
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
