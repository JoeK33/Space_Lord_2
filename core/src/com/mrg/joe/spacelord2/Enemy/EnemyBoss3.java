package com.mrg.joe.spacelord2.Enemy;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.mrg.joe.spacelord2.GameConstants;
import com.mrg.joe.spacelord2.Weapon.EnemyBoss2Weapon;
import com.mrg.joe.spacelord2.Weapon.EnemyBoss3Weapon;
import com.mrg.joe.spacelord2.Weapon.Weapon;

/**
 * Created by Joe on 9/17/2015.
 */
public class EnemyBoss3 extends Enemy {


    private Weapon weapon;


    public EnemyBoss3() {

        super(GameConstants.enemy_boss3_health, -500, -500, new Texture(Gdx.files.internal("enemies/enemy_boss3.png")), 5000);

        // offsets for weapon spawns.
        this.weapon = new EnemyBoss3Weapon(this);
        super.weapon = this.weapon;
        this.changeDeathSound();


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
