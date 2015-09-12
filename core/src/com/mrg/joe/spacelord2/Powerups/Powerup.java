package com.mrg.joe.spacelord2.Powerups;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;

/**
 * Created by Joe on 9/11/2015.
 */
public class Powerup {

    private PowerupType type;
    private Sprite sprite;
    private boolean deployed;

    public Powerup(PowerupType type){
        this.type = type;

        if(this.type == PowerupType.HEALTH){

            this.sprite = new Sprite(new Texture("health_pickup.png"));
        } else if(this.type == PowerupType.SHOTGUN){

            this.sprite = new Sprite(new Texture("shotgun_pickup.png"));
        }else if(this.type == PowerupType.ROCKETS){

            this.sprite = new Sprite(new Texture("rocket_pickup.png"));
        }

    }



    public void draw(Batch batch){
        this.sprite.draw(batch);
    }


    public void update(float delta){

        if(deployed) {

            this.sprite.setY(this.sprite.getY() - (150 * delta));
        }

        if(this.sprite.getY() < 0 - this.sprite.getHeight()){
            this.deployed = false;
            this.sprite.setPosition(0, Gdx.graphics.getHeight() + this.sprite.getHeight());
        }


    }

    public PowerupType getType(){
        return this.type;
    }

    public boolean isColliding(Rectangle rect){

        if (this.sprite.getBoundingRectangle().overlaps(rect)){
            this.deployed = false;
            return true;
        }else return false;
    }

    public void spawn(){

        if(!deployed) {

            this.sprite.setPosition((float) (Math.random() * (Gdx.graphics.getWidth() - this.sprite.getWidth())), Gdx.graphics.getHeight() + this.sprite.getHeight());
            this.deployed = true;
        }
    }

    public void remove(){
        this.deployed = false;
        this.sprite.setPosition(0, Gdx.graphics.getHeight() + this.sprite.getHeight());
    }


}
