package com.mrg.joe.spacelord2.Powerups;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.mrg.joe.spacelord2.GameConstants;

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

            this.sprite = new Sprite(new Texture(Gdx.files.internal("health_pickup.png")));
        } else if(this.type == PowerupType.SHOTGUN){

            this.sprite = new Sprite(new Texture(Gdx.files.internal("shotgun_pickup.png")));
        }else if(this.type == PowerupType.ROCKETS){

            this.sprite = new Sprite(new Texture(Gdx.files.internal("rocket_pickup.png")));
        }else if(this.type == PowerupType.LASER){

            this.sprite = new Sprite(new Texture(Gdx.files.internal("minigun_pickup.png")));
        }else if(this.type == PowerupType.SINE){

            this.sprite = new Sprite(new Texture(Gdx.files.internal("sine_pickup.png")));
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
            this.sprite.setPosition(0, GameConstants.GAME_HEIGHT + this.sprite.getHeight());
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

            this.sprite.setPosition((float) (Math.random() * (GameConstants.GAME_WIDTH - this.sprite.getWidth())), GameConstants.GAME_HEIGHT + this.sprite.getHeight());
            this.deployed = true;
        }
    }

    public void remove(){
        this.deployed = false;
        this.sprite.setPosition(0, GameConstants.GAME_HEIGHT + this.sprite.getHeight());
    }


}
