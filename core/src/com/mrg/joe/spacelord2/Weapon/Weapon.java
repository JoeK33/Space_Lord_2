package com.mrg.joe.spacelord2.Weapon;


import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mrg.joe.spacelord2.GameConstants;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Joe on 8/26/2015.  The basic weapon class.  Both player and enemy weapons extended from this.
 */
public class Weapon {

    protected int damage;


    protected List<Projectile> projectiles;
    protected long interval;
    float[] pos;
    protected boolean isOn;


    public Weapon() {

        interval = System.nanoTime();
        projectiles = new ArrayList<Projectile>();
        isOn = true;

    }


    public void turnOff() {
        this.isOn = false;
    }

    public void turnOn() {
        this.isOn = true;
    }

    public boolean isOn() {
        return isOn;
    }


    public void update(float delta) {

        if (!projectiles.isEmpty()) {
            for (Iterator itr = projectiles.iterator(); itr.hasNext();) {
                Projectile p = (Projectile) itr.next();
                p.update(delta);
                // remove projectiles that fly off screen
                if ((p.getY() - p.getHeight()) > GameConstants.GAME_HEIGHT || (p.getY() < 0 - p.getHeight())) {
                    p.remove();
                    itr.remove();
                }
            }
        }
    }

    public void draw(SpriteBatch batch) {

        if (!projectiles.isEmpty()) {

            for (Iterator itr = projectiles.iterator(); itr.hasNext();) {
                Projectile p = (Projectile) itr.next();
                p.draw(batch);

            }
        }
    }

    public List getProjectiles() {
        return projectiles;
    }

    public void clear() {
        this.projectiles.clear();
    }

}
