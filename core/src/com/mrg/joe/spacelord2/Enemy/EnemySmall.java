package com.mrg.joe.spacelord2.Enemy;

import com.badlogic.gdx.graphics.Texture;
import com.mrg.joe.spacelord2.GameConstants;

/**
 * Created by Joe on 8/28/2015.
 */
public class EnemySmall extends Enemy {


    public EnemySmall( float x, float y, Behavior b) {


        super(GameConstants.enemy_small_health, x, y,new Texture("enemies/enemy_small.png"), 50, b);

    }


    @Override
    public void update(float delta){

      super.update(delta);
        }
}
