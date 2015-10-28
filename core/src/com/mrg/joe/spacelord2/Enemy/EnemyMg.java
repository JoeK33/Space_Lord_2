package com.mrg.joe.spacelord2.Enemy;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mrg.joe.spacelord2.GameConstants;
import com.mrg.joe.spacelord2.SpaceLord2;
import com.mrg.joe.spacelord2.Weapon.EnemyMachineGunWeapon;
import com.mrg.joe.spacelord2.Weapon.Weapon;

/**
 * Created by Joe on 8/28/2015.
 */
public class EnemyMg extends Enemy{



    private Weapon weapon;

    public EnemyMg() {


        super(GameConstants.enemy_mg_health, 250);
        this.weapon = new EnemyMachineGunWeapon(this);
        super.weapon = this.weapon;



    }



    @Override
    public void update(float delta){
this.weapon.update(delta);
        super.update(delta);


    }


}

