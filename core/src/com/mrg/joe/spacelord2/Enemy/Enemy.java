package com.mrg.joe.spacelord2.Enemy;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Timer;
import com.mrg.joe.spacelord2.Player;
import com.mrg.joe.spacelord2.SpaceLord2;
import com.mrg.joe.spacelord2.Weapon.Weapon;


/**
 * Created by Joe on 8/26/2015.
 */
public class Enemy {
    protected Sprite sprite;
    private int health;
    private Texture enemy_texture;
    private Color color;
    protected Weapon weapon;
    protected boolean alive;
    private int score;
    private Behavior behavior;
    private float degrees;
    private boolean advancing;
    private boolean goingLeft;
    private Player player;



    public Enemy(int health, float x, float y, Texture texture, int score, Behavior behavior){

        this.enemy_texture = texture;
        this.sprite = new Sprite(enemy_texture);
        this.color = this.sprite.getColor();
        this.health = health;
        this.setPosition(x, y);
        this.alive = true;
        this.score = score;
        this.behavior = behavior;
        this.player = SpaceLord2.player;


    }

    public int getHealth(){
        return this.health;
    }

    public Sprite getSprite(){
        return this.sprite;
    }

    public void setSprite(Sprite sprite){
        this.sprite = sprite;
    }

    public void setTexture(Texture texture){
        this.enemy_texture = texture;
    }


    public void draw(SpriteBatch batch){

        if(this.sprite.getY() < Gdx.graphics.getHeight()) {

            if (alive) {
                this.sprite.draw(batch);
            }
        }

            if (weapon != null) {
                this.weapon.draw(batch);
            }

    }

    public void update(float delta){
        if (this.health <= 0){
            this.alive = false;
        }

        if(this.alive){

            if(this.behavior == Behavior.STATIONARY){

            }else
            if(this.behavior == Behavior.WIGGLE){


                double wiggle = Math.sin(degrees) * (50*delta);


                this.setPosition(this.getX() + ((float) (wiggle)), this.getY());



                degrees+= .05f;

                if (degrees > 360){
                    degrees = 0;
                }

            }else
            if(this.behavior == Behavior.TRACK_PLAYER){

                if(player.getCenterX() < this.getCenterX()){
                    goingLeft = true;
                }else goingLeft = false;



                if(goingLeft){
                    this.setPosition(this.getX() -(delta * 75f), this.getY());
                } else{
                    this.setPosition(this.getX() + (delta * 75f), this.getY());
                }



                if(this.getX() < 0){
                    this.setPosition(0, this.getY());
                    this.goingLeft = false;
                }

                if(this.getX() + this.getWidth() > Gdx.graphics.getWidth()){
                    this.goingLeft = true;
                    this.setPosition(Gdx.graphics.getWidth() - this.getWidth(), this.getY());
                }

            }else
            if(this.behavior == Behavior.PATROL){

                if (goingLeft) {
                    this.setPosition(this.getX() - (delta * 75f), this.getY());
                } else {
                    this.setPosition(this.getX() + (delta * 75f), this.getY());
                }


                if (this.getX() < 0) {
                    this.goingLeft = false;
                }

                if (this.getX() + this.getWidth() > Gdx.graphics.getWidth()) {
                    this.goingLeft = true;
                }

            }else
            if(this.behavior == Behavior.CHARGE){
                setAdvancing(true);
            }

            if(this.advancing){

                this.setPosition(this.getX(), this.getY() - (delta * 120f) );
            }




        }


    }

    public boolean isAlive(){
        return this.alive;
    }

    public void setAlive(boolean alive){
        this.alive = alive;
    }

    public boolean isColliding(Rectangle rect){

        if(alive) {
            return this.sprite.getBoundingRectangle().overlaps(rect);
        }else return  false;
    }

    public void setAdvancing(Boolean advancing){
        this.advancing = advancing;
    }

    public boolean getAdvancing(){
        return this.advancing;
    }



    public void doDamage(int i){
        this.health -= i;
        sprite.setColor(256,256,256,256);

        Timer timer = new Timer();
        timer.scheduleTask(new Timer.Task() {
            @Override
            public void run() {
                sprite.setColor(color);

            }
        }, .1f);


    }

    public float getY(){
        return this.sprite.getY();
    }

    public float getX(){
        return this.sprite.getX();
    }

    public float getCenterX(){
        return this.getWidth()/2 + this.getX();
    }

    public float getWidth(){
        return this.sprite.getWidth();
    }

    public void setPosition(float x, float y){
        this.sprite.setPosition(x, y);
    }

    public void dispose(){
        enemy_texture.dispose();
    }

    public float getHeight(){
        return this.sprite.getHeight();
    }

    public float[] getNosePos(){
        float[] nose_pos = new float[2];
        nose_pos[0] = this.getX() + this.getWidth()/2;
        nose_pos[1] = this.getY();
        return  nose_pos;
    }

    public Weapon getWeapon(){
        return this.weapon;
    }

    public int getKillScore(){
        return score;
    }

}
