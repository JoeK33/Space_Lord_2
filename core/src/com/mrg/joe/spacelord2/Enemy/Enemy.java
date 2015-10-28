package com.mrg.joe.spacelord2.Enemy;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Timer;
import com.mrg.joe.spacelord2.Explosion;
import com.mrg.joe.spacelord2.GameConstants;
import com.mrg.joe.spacelord2.Player;
import com.mrg.joe.spacelord2.Assets;
import com.mrg.joe.spacelord2.Weapon.Weapon;


/**
 * Created by Joe on 8/26/2015.
 */
public class Enemy implements Pool.Poolable{
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
    private Explosion explosion;
    private Sound deathSound;
    private boolean explosionPlayed;
    private int startingHealth;
    public Assets assets;



    public Enemy(int health, int score){


        this.health = health;
        this.startingHealth = health;
        this.alive = true;
        this.score = score;




    }

    // makes a sprite from the texture in the manager
    public void makeSprite(Assets manager, String filepath){
        this.assets = manager;

        this.enemy_texture = manager.manager.get(filepath, Texture.class);
        this.sprite = new Sprite(enemy_texture);
        this.color = this.sprite.getColor();


        // need assets to make the explosion and sound so we make it here instead of in the constructor
        this.explosion = new Explosion(this.sprite, this.assets);
        deathSound = assets.manager.get("sounds/explosion3.wav", Sound.class);
    }

    public void changePlayer(Player player){
        this.player = player;
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

        if(this.sprite.getY() < GameConstants.GAME_HEIGHT) {

            if (alive) {
                this.sprite.draw(batch);
            }else  this.explosion.draw(batch);
        }

            if (weapon != null) {
                this.weapon.draw(batch);
            }

    }

    public void update(float delta){
        if (this.health <= 0){
            this.alive = false;
        }

        if(!this.isAlive() && !explosionPlayed){
            deathSound.play(.5f);
            explosionPlayed = true;
        }

        if(this.alive){


            if(this.behavior == Behavior.WIGGLE){


                double wiggle = Math.cos(degrees) * (50*delta);


                this.setPosition(this.getX() + ((float) (wiggle)), this.getY());



                degrees+= .05f;

                if (degrees > 360){
                    degrees = 0;
                }

            }else
            if(this.behavior == Behavior.TRACK_PLAYER){

                if(player.getCenterX() <= this.getCenterX()){
                    goingLeft = true;
                }else goingLeft = false;


            if(this.getCenterX() != player.getCenterX()) {
                if (goingLeft) {
                    this.setPosition(this.getX() - (delta * 75f), this.getY());
                } else {
                    this.setPosition(this.getX() + (delta * 75f), this.getY());
                }
            }



                if(this.getX() < 0){
                    this.setPosition(0, this.getY());
                    this.goingLeft = false;
                }

                if(this.getX() + this.getWidth() > GameConstants.GAME_WIDTH){
                    this.goingLeft = true;
                    this.setPosition(GameConstants.GAME_WIDTH - this.getWidth(), this.getY());
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

                if (this.getX() + this.getWidth() > GameConstants.GAME_WIDTH) {
                    this.goingLeft = true;

                }

            }else
            if(this.behavior == Behavior.CHARGE){
                setAdvancing(true);
            }else
        if(this.behavior == Behavior.HUNT){

            if(player.getCenterX() < this.getCenterX()){
                goingLeft = true;
            }else goingLeft = false;


            if(this.getCenterX() < player.getCenterX() - 5 || this.getCenterX() > player.getCenterX() + 5) {

                if (goingLeft) {
                    this.setPosition(this.getX() - (delta * 400f), this.getY());
                } else {
                    this.setPosition(this.getX() + (delta * 400f), this.getY());
                }
            }



            if(this.getX() < 0){
                this.setPosition(0, this.getY());
                this.goingLeft = false;
            }

            if(this.getX() + this.getWidth() > GameConstants.GAME_WIDTH){
                this.goingLeft = true;
                this.setPosition(GameConstants.GAME_WIDTH - this.getWidth(), this.getY());
            }

        }

            if(this.advancing){

                this.setPosition(this.getX(), this.getY() - (delta * 120f) );
            }




        } else  if(this.getWeapon() != null) {
            this.getWeapon().turnOff();
        }


    }

    public boolean readyToRemove(){
        if(!this.isAlive() && this.explosion.isFinished()){

            if(this.weapon == null) {
                return true;
            }else if(getWeapon().getProjectiles().isEmpty()){
                return true;
            }
        }

        return false;
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

        this.deathSound.dispose();
        this.explosion.dispose();
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

    public void changeBehavior(Behavior behavior){
        this.behavior = behavior;
    }

    // changes death sound to one used for bosses
    public void changeDeathSound(){
        deathSound =  assets.manager.get("sounds/explosion7.wav", Sound.class);
    }

    @Override
    public void reset() {
        this.health = startingHealth;
        this.alive = true;
        this.sprite.setY(GameConstants.GAME_HEIGHT + 200);
        this.weapon.clear();
    }

    public void init(float posX, float posY, Behavior behavior) {

        this.sprite.setPosition(posX, posY);
        this.health = startingHealth;
        this.weapon.turnOn();
        explosionPlayed = false;
        this.explosion.resetTime();
        degrees = 0;
        this.behavior = behavior;
    }


}
