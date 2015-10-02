package com.mrg.joe.spacelord2.Weapon;

import com.badlogic.gdx.Gdx;
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
    private Enemy enemy;
    private static Texture tex;


    private float deltax;
    private float deltay;
    private double distance;
    private double velX;
    private double velY;


    public EnemyBlasterProjectile(float[] pos, Enemy enemy) {


        super(pos, 1, new Texture(Gdx.files.internal("weapons/enemy_blaster_projectile.png")));

        this.enemy = enemy;

        this.launched = false;

        Timer timer = new Timer();
        timer.scheduleTask(new Timer.Task() {
            @Override
            public void run() {
                launched = true;

            }
        }, 3f);

        tex =new Texture(Gdx.files.internal("weapons/enemy_blaster_projectile.png"));

        this.deltax = SpaceLord2.player.getPlayerNosePosition()[0] - (this.sprite.getX() + this.sprite.getWidth()/2);
        this.deltay = SpaceLord2.player.getPlayerNosePosition()[1] - (this.sprite.getY());
        this.distance = (Math.sqrt((deltax * deltax) + (deltay * deltay)));
        velX = (deltax/distance);
        velY = (deltay/distance);

    }

    @Override
    public void update(float delta){
        // projectile behaviors here

        this.sprite.rotate(1);



        if(!launched){
            this.sprite.setPosition(enemy.getX() + enemy.getWidth()/2 - getProjectileHeight()/2, enemy.getY() - getProjectileHeight() + 15);

            this.deltax = SpaceLord2.player.getPlayerNosePosition()[0] - (this.sprite.getX() + this.sprite.getWidth()/2);
            this.deltay = SpaceLord2.player.getPlayerNosePosition()[1] - (this.sprite.getY());
            this.distance = (Math.sqrt((deltax * deltax) + (deltay * deltay)));
            velX = (deltax/distance);
            velY = (deltay/distance);

        }else {
            this.sprite.setPosition((float)(this.getX() + (velX * (delta * GameConstants.projectile_speed))), (float)(this.sprite.getY() + (velY * (delta * GameConstants.projectile_speed))));

            if (this.sprite.getY() < -200) {
                this.remove();
            }

        }}

    public boolean isLaunched(){
        return this.launched;
    }



    public static int getProjectileHeight(){


        return tex.getHeight();

    }

}
