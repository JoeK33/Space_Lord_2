package com.mrg.joe.spacelord2.Weapon;

import com.mrg.joe.spacelord2.Assets;
import com.mrg.joe.spacelord2.GameConstants;

/**
 * Created by Joe on 8/28/2015.
 */
public class EnemyMgProjectile extends Projectile {

    public EnemyMgProjectile(float[] pos, Assets manager) {

        super(pos, 1, manager, "weapons/enemy_mg_laser.png");
    }

    @Override
    public void update(float delta) {
        // projectile behaviors here
        this.sprite.setY(this.sprite.getY() - (delta * GameConstants.projectile_speed));

        if (this.sprite.getY() < -20) {
            this.remove();
        }

    }

}
