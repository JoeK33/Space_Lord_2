package com.mrg.joe.spacelord2.Enemy;

import com.mrg.joe.spacelord2.GameConstants;
import com.mrg.joe.spacelord2.Weapon.EnemyBoss3Weapon;
import com.mrg.joe.spacelord2.Weapon.Weapon;

/**
 * Created by Joe on 9/17/2015.
 */
public class EnemyBoss3 extends Enemy {


    private Weapon weapon;


    public EnemyBoss3() {
        super(GameConstants.enemy_boss3_health, 5000);
    }

    public void create() {
        this.weapon = new EnemyBoss3Weapon(this);
        super.weapon = this.weapon;
        this.changeDeathSound();
    }

    @Override
    public void update(float delta) {

        super.update(delta);
        if (this.getHealth() < 250) {
            this.changeBehavior(Behavior.TRACK_PLAYER);
        }
        this.weapon.update(delta);


    }

}
