package com.mrg.joe.spacelord2.Weapon;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Timer;
import com.mrg.joe.spacelord2.Enemy.Enemy;
import com.mrg.joe.spacelord2.GameConstants;
import com.mrg.joe.spacelord2.SpaceLord2;

/**
 * Created by Joe on 8/28/2015.
 */
public class EnemyBlasterProjectile extends Projectile {

    public boolean launched;
    private  boolean movingLeft;
    private Enemy enemy;


    public EnemyBlasterProjectile(float[] pos, Enemy enemy) {


        super(pos, 1, new Texture("weapons/enemy_blaster_projectile.png"));

        this.enemy = enemy;

        this.launched = false;

        Timer timer = new Timer();
        timer.scheduleTask(new Timer.Task() {
            @Override
            public void run() {
                launched = true;

            }
        }, 4f);

    }

    @Override
    public void update(float delta){
        // projectile behaviors here

        this.sprite.rotate(1);



        if(!launched){
            this.sprite.setPosition(enemy.getX() + enemy.getWidth()/2 - getProjectileHeight()/2, enemy.getY() - getProjectileHeight());

        }else {
            this.sprite.setPosition(this.getX(), this.sprite.getY() - (delta * GameConstants.projectile_speed));


            if (this.sprite.getY() < -200) {
                this.remove();
            }

        }}

    public boolean isLaunched(){
        return this.launched;
    }



    public static int getProjectileHeight(){

        Texture tex =new Texture("weapons/enemy_blaster_projectile.png");

        return tex.getHeight();

    }

}
