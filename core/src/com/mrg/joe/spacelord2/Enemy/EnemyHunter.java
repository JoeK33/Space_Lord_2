package com.mrg.joe.spacelord2.Enemy;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.mrg.joe.spacelord2.GameConstants;
import com.mrg.joe.spacelord2.Weapon.EnemyGenericWeapon;
import com.mrg.joe.spacelord2.Weapon.EnemyMachineGunWeapon;
import com.mrg.joe.spacelord2.Weapon.Weapon;

/**
 * Created by Joe on 9/17/2015.
 */
public class EnemyHunter extends Enemy{
    private Weapon weapon;

    public EnemyHunter( ) {


        super(GameConstants.enemy_hunter_health,-500, -500,new Texture(Gdx.files.internal("enemies/enemy_hunter.png")), 50);
        this.weapon = new EnemyMachineGunWeapon(this);
        super.weapon = this.weapon;

    }


    @Override
    public void update(float delta){
        this.weapon.update(delta);
        super.update(delta);
    }


}
