package com.mrg.joe.spacelord2.Enemy;

import com.mrg.joe.spacelord2.GameConstants;
import com.mrg.joe.spacelord2.Weapon.EnemyMachineGunWeapon;
import com.mrg.joe.spacelord2.Weapon.Weapon;

/**
 * Created by Joe on 9/17/2015.
 */
public class EnemyHunter extends Enemy {
    private Weapon weapon;

    public EnemyHunter() {


        super(GameConstants.ENEMY_HUNTER_HEALTH, 200);
        this.weapon = new EnemyMachineGunWeapon(this);
        super.weapon = this.weapon;

    }


    @Override
    public void update(float delta) {
        this.weapon.update(delta);
        super.update(delta);
    }


}
