package com.mrg.joe.spacelord2.Enemy;

import com.mrg.joe.spacelord2.GameConstants;
import com.mrg.joe.spacelord2.Weapon.EnemyGenericWeapon;
import com.mrg.joe.spacelord2.Weapon.Weapon;

/**
 * Created by Joe on 8/28/2015.
 */
public class EnemyFighter extends Enemy {

    private Weapon weapon;

    public EnemyFighter() {
        super(GameConstants.FIGHTER_HEALTH, 100);
        this.weapon = new EnemyGenericWeapon(this);
        super.weapon = this.weapon;
    }

    @Override
    public void update(float delta) {
        this.weapon.update(delta);
        super.update(delta);
    }
}
