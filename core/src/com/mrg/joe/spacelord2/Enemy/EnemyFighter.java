package com.mrg.joe.spacelord2.Enemy;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mrg.joe.spacelord2.GameConstants;
import com.mrg.joe.spacelord2.Weapon.EnemyGenericWeapon;
import com.mrg.joe.spacelord2.Weapon.Weapon;

/**
 * Created by Joe on 8/28/2015.
 */
public class EnemyFighter extends Enemy {

    private Weapon weapon;

    public EnemyFighter(float x, float y, Behavior b) {


        super(GameConstants.fighter_health, x, y,new Texture("enemies/enemy_fighter.png"), 100, b);
        this.weapon = new EnemyGenericWeapon(this);
        super.weapon = this.weapon;


    }

    @Override
    public void update(float delta){
        this.weapon.update(delta);
     super.update(delta);

}}
