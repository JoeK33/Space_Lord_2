package com.mrg.joe.spacelord2.Enemy;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mrg.joe.spacelord2.GameConstants;

/**
 * Created by Joe on 8/28/2015.
 */
public class EnemyFighter extends Enemy {


    public EnemyFighter(float x, float y, Behavior b) {


        super(GameConstants.fighter_health, x, y,new Texture("enemies/enemy_fighter.png"), 100, b);

    }

    @Override
    public void update(float delta){

     super.update(delta);

}}
