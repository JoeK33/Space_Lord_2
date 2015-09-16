package com.mrg.joe.spacelord2;

import com.badlogic.gdx.Gdx;
import com.mrg.joe.spacelord2.Enemy.Enemy;
import com.mrg.joe.spacelord2.Weapon.Projectile;
import com.mrg.joe.spacelord2.Weapon.Weapon;

import java.util.Iterator;
import java.util.List;

/**
 * Created by Joe on 8/30/2015.
 */
public class CollisionHandler {

    public CollisionHandler(){

    }




    public void handle(float delta, Weapon[] weapons, List enemyProjectiles, List enemyList, Player player){


    // iterate over all player projectles in play
    for (Iterator it = enemyList.iterator(); it.hasNext();){
        Enemy e = (Enemy)it.next();

        if(e.getWeapon() != null) {
            enemyProjectiles.addAll(e.getWeapon().getProjectiles());
        }

        // if player hits enemy, damage player and remove enemy

        if(e.isColliding(player.getBoundingRectangle())){
            it.remove();
            player.takeHit();
        }



for(Weapon w: weapons){
    List projectiles = w.getProjectiles();

    for(Iterator ite = projectiles.iterator(); ite.hasNext();){
        Projectile p = (Projectile)ite.next();

        // if a player shot hits an enemy, damage the enemy and remove the shot
        if(e.isColliding(p.getBoundingRectangle())){
            e.doDamage(p.getDamage());
            player.addScore(p.getDamage());
            p.remove();
            ite.remove();
        }

    }

}






        // purge dead enemies
        if(!e.isAlive()){

            if(e.getWeapon() != null) {
                e.getWeapon().turnOff();
            }

            // remove enemy from list after it's weapon has finished its job

            if(e.getWeapon() == null){
                it.remove();
            }else if (e.getWeapon().getProjectiles().isEmpty()){
                e.dispose();
                it.remove();
            }

        }

        // remove enemies that move off screen
        if(e.getY() < -e.getHeight()){
            e.setAlive(false);
            it.remove();
        }
        // update the survivors
        e.update(delta);

    }

    // go over enemy projectiles to check player hit
        if(player.isAlive()) {
            for (Iterator i = enemyProjectiles.iterator(); i.hasNext(); ) {
                Projectile p = (Projectile) i.next();
                if (p.isColliding(player.getBoundingRectangle())) {
                    p.remove();
                    i.remove();
                    player.takeHit();
                }


            }
        }
}


}
