package com.mrg.joe.spacelord2;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Timer;
import com.mrg.joe.spacelord2.Weapon.PlayerWeapon;
import com.mrg.joe.spacelord2.Weapon.Weapon;

/**
 * Created by Joe on 8/26/2015.
 */
public class Player {

    private Texture texture;
    private Sprite sprite;
    private Weapon[] weapons;
    private int health;
    private Color color;




    public Player(){

        this.texture = new Texture("player.png");
        this.sprite = new Sprite(texture);


        // players weapons go here.
        this.weapons = new Weapon[1];
        this.weapons[0] = new PlayerWeapon(this);


        this.sprite.setPosition((Gdx.graphics.getWidth()/2) - (this.sprite.getWidth()/2), Gdx.graphics.getHeight()/6);
        this.color = this.sprite.getColor();


        this.health = 3;



    }

    public float[] getPlayerNosePosition(){

        float[] nose_pos = new float[2];
        nose_pos[0] = this.getX() + this.getWidth()/2;
        nose_pos[1] = this.getY() + this.getHeight();
        return  nose_pos;
    }

    // scale rectangle a bit to make hit detection better.
    public Rectangle getBoundingRectangle(){

        Rectangle rectangle = this.sprite.getBoundingRectangle();
        rectangle.setSize(this.sprite.getWidth(), (this.sprite.getHeight()/4) * 3);
        return rectangle;
    }

    public void update(float delta){

        // update all player weapons
        for(Weapon w : weapons){
            w.update(delta);
        }



        // keep player on screen
        if(this.sprite.getX()+this.sprite.getWidth() > Gdx.graphics.getWidth()){
            this.sprite.setPosition(Gdx.graphics.getWidth() - this.sprite.getWidth(),this.sprite.getY());
        }

        if(this.sprite.getX() < 0){
            this.sprite.setPosition(0,this.sprite.getY());
        }




    }

    public void takeHit(){
        this.health--;
        sprite.setColor(256,256,256,256);
        //Gdx.app.log("Player", "Took damage");

        Timer timer = new Timer();
        timer.scheduleTask(new Timer.Task() {
            @Override
            public void run() {
                sprite.setColor(color);

            }
        }, .1f);


    }

    public void setXPosition(Float f){
        this.sprite.setPosition(f, this.sprite.getY());
    }

    public float getX(){
        return this.sprite.getX();
    }

    public float getCenterX(){
        return this.getWidth()/2 + this.getX();
    }

    public float getY(){
        return this.sprite.getY();
    }

    public float getHeight(){
        return this.sprite.getHeight();
    }

    public float getWidth(){
        return this.sprite.getWidth();
    }

    public Weapon[] getWeapons(){
        return this.weapons;
    }


    public void draw(SpriteBatch batch){
        this.sprite.draw(batch);

        for(Weapon w:weapons)
        w.draw(batch);
    }

    public void dispose(){
        this.texture.dispose();
        this.dispose();
    }

}
