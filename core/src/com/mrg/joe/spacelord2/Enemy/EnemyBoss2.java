package com.mrg.joe.spacelord2.Enemy;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.mrg.joe.spacelord2.GameConstants;
import com.mrg.joe.spacelord2.Weapon.EnemyBoss2Weapon;
import com.mrg.joe.spacelord2.Weapon.EnemyMachineGunWeapon;
import com.mrg.joe.spacelord2.Weapon.Weapon;

/**
 * Created by Joe on 9/17/2015.
 */
public class EnemyBoss2 extends Enemy {


    private Weapon weapon;


    public EnemyBoss2(float x, float y, Behavior behavior) {

        super(GameConstants.enemy_boss2_health, x, y, new Texture(Gdx.files.internal("enemies/enemy_boss2.png")), 5000, behavior);

                    // offsets for weapon spawns.
        this.weapon = new EnemyBoss2Weapon(this,185,302,420);
        super.weapon = this.weapon;


    }

    @Override
    public void update(float delta) {

        super.update(delta);


        this.weapon.update(delta);


    }


}
