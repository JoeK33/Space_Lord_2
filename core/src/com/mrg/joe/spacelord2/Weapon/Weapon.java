package com.mrg.joe.spacelord2.Weapon;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mrg.joe.spacelord2.GameConstants;
import com.mrg.joe.spacelord2.Player;
import com.mrg.joe.spacelord2.SpaceLord2;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static com.mrg.joe.spacelord2.SpaceLord2.*;

/**
 * Created by Joe on 8/26/2015.
 */
public class Weapon {

    protected int damage;



    protected List<Projectile> projectiles;
    protected long interval;
    float[] pos;
    protected boolean isOn;


    public Weapon(){
        interval = System.nanoTime();
        projectiles = new ArrayList<Projectile>();
        isOn = true;

    }

    public void replaceProjectileList(List p){
        this.projectiles = p;
    }

    public void turnOff(){
        this.isOn = false;
    }

    public boolean isOn(){
        return isOn;
    }




    public void update(float delta) {



        // creates new projectiles every interval in seconds
        if ( System.nanoTime() > interval + (GameConstants.projectile_creation_interval * 1000000000)) {
            projectiles.add(new Projectile(pos,GameConstants.player_laser_damage, new Texture("laser.png")));

            interval = System.nanoTime();
        }

        if (!projectiles.isEmpty()) {
            for (Iterator itr = projectiles.iterator(); itr.hasNext();) {
                Projectile p = (Projectile)itr.next();
                p.update(delta);


                // remove projectiles that fly off screen
                if ((p.getY() - p.getHeight()) > Gdx.graphics.getHeight() || (p.getY() < 0 - p.getHeight())) {
                    p.remove();
                    itr.remove();

                }


            }

        }
    }
    public void draw(SpriteBatch batch){

        if (!projectiles.isEmpty()) {

            for (Iterator itr = projectiles.iterator(); itr.hasNext();) {
            Projectile p = (Projectile)itr.next();
            p.draw(batch);

        }
    }}

    public List getProjectiles(){
        return projectiles;
    }

    public void setDamage(int damage){
        this.damage = damage;
    }

}
