package com.mrg.joe.spacelord2.Enemy;

import com.mrg.joe.spacelord2.GameConstants;
import com.mrg.joe.spacelord2.Weapon.EnemyBoss4Weapon;
import com.mrg.joe.spacelord2.Weapon.Weapon;

/**
 * Created by Joe on 9/19/2015.
 */
public class EnemyBoss4 extends Enemy {


    private Weapon weapon;


    public EnemyBoss4() {
        super(GameConstants.enemy_boss4_health, 5000);
    }

    public void create() {
        this.weapon = new EnemyBoss4Weapon(this);
        super.weapon = this.weapon;
        this.changeDeathSound();
    }

    @Override
    public void update(float delta) {

        super.update(delta);
        if (this.getHealth() < 2500) {
            this.changeBehavior(Behavior.TRACK_PLAYER);
        }
        this.weapon.update(delta);


    }

}
