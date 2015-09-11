package com.mrg.joe.spacelord2.Weapon;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.mrg.joe.spacelord2.Enemy.Enemy;
import com.mrg.joe.spacelord2.GameConstants;

import java.util.Iterator;

/**
 * Created by Joe on 8/28/2015.
 */
public class EnemyBlasterWeapon extends Weapon {



    private Enemy enemy;
    private float proj_size;


    public EnemyBlasterWeapon(Enemy enemy){

        this.enemy = enemy;
        Projectile proj = new EnemyBlasterProjectile(new float[]{-300,-300}, enemy);

        // even sided projectile
        proj_size = proj.getHeight();

        proj = null;


    }



    @Override
    public void update(float delta) {


        if(this.isOn) {

            // creates new projectiles every interval in seconds
            if (System.nanoTime() > interval + (5 * 1000000000L )) {


                projectiles.add(new EnemyBlasterProjectile(new float[]{enemy.getX() + enemy.getWidth() / 2 - proj_size / 2, enemy.getY() - proj_size}, enemy));


                interval = System.nanoTime();
            }
        }

        if (!projectiles.isEmpty()) {
            for (Iterator itr = projectiles.iterator(); itr.hasNext();) {
                EnemyBlasterProjectile p = (EnemyBlasterProjectile)itr.next();
                p.update(delta);



                // remove projectiles that fly off screen
                if ((p.getY() + p.getHeight()) < 0) {
                    p.remove();
                    itr.remove();

                }

                // remove primed projectile if enemy dies
                if(!this.enemy.isAlive() && !p.isLaunched()){
                    p.remove();
                    itr.remove();

                }


            }

        }
    }
}
