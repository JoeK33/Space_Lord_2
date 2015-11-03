package com.mrg.joe.spacelord2.Enemy;

import com.mrg.joe.spacelord2.GameConstants;
import com.mrg.joe.spacelord2.Weapon.EnemyMachineGunWeapon;
import com.mrg.joe.spacelord2.Weapon.Weapon;

/**
 * Created by Joe on 8/28/2015.
 */
public class EnemyMg extends Enemy {


    private Weapon weapon;

    public EnemyMg() {
        super(GameConstants.ENEMY_MG_HEALTH, 250);
        this.weapon = new EnemyMachineGunWeapon(this);
        super.weapon = this.weapon;
    }


    @Override
    public void update(float delta) {
        this.weapon.update(delta);
        super.update(delta);
    }
}

