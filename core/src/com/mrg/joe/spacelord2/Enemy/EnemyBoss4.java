package com.mrg.joe.spacelord2.Enemy;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.mrg.joe.spacelord2.GameConstants;
import com.mrg.joe.spacelord2.Weapon.EnemyBoss4Weapon;
import com.mrg.joe.spacelord2.Weapon.Weapon;

/**
 * Created by Joe on 9/19/2015.
 */
public class EnemyBoss4 extends Enemy{


    private Weapon weapon;


    public EnemyBoss4(float x, float y, Behavior behavior) {

        super(GameConstants.enemy_boss3_health, x, y, new Texture(Gdx.files.internal("enemies/enemy_boss4.png")), 5000, behavior);

        // offsets for weapon spawns.
        this.weapon = new EnemyBoss4Weapon(this);
        super.weapon = this.weapon;


    }

    @Override
    public void update(float delta) {

        super.update(delta);
        if(this.getHealth() < 100){
            this.changeBehavior(Behavior.TRACK_PLAYER);
        }


        this.weapon.update(delta);


    }

}
