package com.mrg.joe.spacelord2.Weapon;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.mrg.joe.spacelord2.GameConstants;
import com.mrg.joe.spacelord2.Assets;

/**
 * Created by Joe on 8/26/2015.
 */
public class Projectile {

    private Assets manager;
    protected Sprite sprite;
    private int damage;



    public Projectile(float[] pos, int damage, Assets manager, String projectile_texture_filepath){

        this.manager = manager;
        this.sprite = new Sprite(manager.manager.get(projectile_texture_filepath, Texture.class));
        this.sprite.setPosition(pos[0], pos[1]);
        this.damage = damage;


    }

    public void update(float delta){
        // projectile behaviors here
        this.sprite.setY(this.sprite.getY()+ (delta * GameConstants.projectile_speed));

        if(this.sprite.getY() > GameConstants.GAME_HEIGHT){
            this.remove();
        }

    }

    public float getY(){
        return this.sprite.getY();
    }

    public float getX(){
        return this.sprite.getX();
    }

    public float getHeight(){
        return this.sprite.getHeight();
    }

    public void draw(SpriteBatch batch){
        this.sprite.draw(batch);
    }

    public int getDamage(){
        return this.damage;
    }

    public boolean isColliding(Rectangle rect){

        if(rect.overlaps(this.sprite.getBoundingRectangle())){
            this.sprite.setColor(Color.CLEAR);
            return true;
        }

        return false;
    }

    public Rectangle getBoundingRectangle(){
        return this.sprite.getBoundingRectangle();
    }

    public float getCenterX(){
        return this.getX() + this.sprite.getWidth()/2;
    }

    public void remove(){
        // move projectile away so it does not make multiple "hits"
        this.sprite.setX(0 - this.sprite.getWidth() - 5);
    }




}
