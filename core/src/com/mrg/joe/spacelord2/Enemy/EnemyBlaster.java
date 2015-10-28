package com.mrg.joe.spacelord2.Enemy;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.mrg.joe.spacelord2.GameConstants;
import com.mrg.joe.spacelord2.Weapon.EnemyBlasterWeapon;
import com.mrg.joe.spacelord2.Weapon.Weapon;

/**
 * Created by Joe on 8/28/2015.
 */
public class EnemyBlaster extends Enemy {

    private Weapon weapon;


    public EnemyBlaster() {
        super(GameConstants.enemy_blaster_health, 500);


    }


    @Override
    public void update(float delta) {
        this.weapon.update(delta);
        super.update(delta);


    }

    public void create(){
        this.weapon = new EnemyBlasterWeapon(this);
        super.weapon = this.weapon;

    }


}
