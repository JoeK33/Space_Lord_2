package com.mrg.joe.spacelord2.Enemy;

import com.mrg.joe.spacelord2.GameConstants;
import com.mrg.joe.spacelord2.Weapon.EnemyBoss2Weapon;
import com.mrg.joe.spacelord2.Weapon.Weapon;

/**
 * Created by Joe on 9/17/2015.
 */
public class EnemyBoss2 extends Enemy {


    private Weapon weapon;


    public EnemyBoss2() {

        super(GameConstants.ENEMY_BOSS_2_HEALTH, 5000);
    }

    public void create() {

        // offsets for weapon spawns.
        this.weapon = new EnemyBoss2Weapon(this, 185, 302, 420);
        super.weapon = this.weapon;
        this.changeDeathSound();
    }


    @Override
    public void update(float delta) {

        super.update(delta);
        this.weapon.update(delta);
    }

}
