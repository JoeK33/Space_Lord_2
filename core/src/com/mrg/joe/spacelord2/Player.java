package com.mrg.joe.spacelord2;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Timer;
import com.mrg.joe.spacelord2.Powerups.PowerupType;
import com.mrg.joe.spacelord2.Weapon.PlayerRocketWeapon;
import com.mrg.joe.spacelord2.Weapon.PlayerShotgunWeapon;
import com.mrg.joe.spacelord2.Weapon.PlayerTripleLaserWeapon;
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
    private int score;
    private  Timer Shotguntimer;
    private  Timer Rockettimer;
    private  Timer Lasertimer;
    private Explosion explosion;
    private boolean Alive;




    public Player(){

        this.texture = new Texture("player.png");
        this.sprite = new Sprite(texture);
        this.Alive = true;

        // players weapons go here.
        this.weapons = new Weapon[4];
        this.weapons[0] = new PlayerWeapon(this);
       // this.weapons[0].turnOff();
        this.weapons[1] = new PlayerShotgunWeapon(this);
        this.weapons[1].turnOff();
        this.weapons[2] = new PlayerRocketWeapon(this);
        this.weapons[2].turnOff();
        this.weapons[3] = new PlayerTripleLaserWeapon(this);
        this.weapons[3].turnOff();;


        this.sprite.setPosition((Gdx.graphics.getWidth() / 2) - (this.sprite.getWidth() / 2), Gdx.graphics.getHeight() / 6);
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

        if(this.getHealth() <=0){
            this.Alive = false;
        }

        if(!this.Alive){

            for(Weapon w:weapons){
                w.turnOff();
            }


        }

        // update all player weapons
        for(Weapon w : weapons){
            w.update(delta);
        }



        // keep player on screen
        if(this.sprite.getX()+this.sprite.getWidth() > Gdx.graphics.getWidth()){
            this.sprite.setPosition(Gdx.graphics.getWidth() - this.sprite.getWidth(),this.sprite.getY());
        }

        if(this.sprite.getX() < 0){
            this.sprite.setPosition(0, this.sprite.getY());
        }







    }

    public void turnOffWeapon(int weaponSlot){

        if(weaponSlot <= weapons.length && weaponSlot >= 0) {

            this.weapons[weaponSlot].turnOff();
        }
    }

    public boolean isAlive(){
        return this.Alive;
    }

    public void turnOnWeapon(int weaponSlot){

        if(weaponSlot <= weapons.length && weaponSlot >= 0) {
            this.weapons[weaponSlot].turnOn();
        }
    }

    public void takeHit(){
        this.health--;
        sprite.setColor(256, 256, 256, 256);

        Gdx.app.log("Player", "health remaining" + this.health);

        Timer timer = new Timer();
        timer.scheduleTask(new Timer.Task() {
            @Override
            public void run() {
                sprite.setColor(color);

            }
        }, .1f);


    }

    public void Powerup(PowerupType type){

        if(type == PowerupType.HEALTH){

            if(this.health < 4) {
                this.health++;
            }
        } else if(type == PowerupType.SHOTGUN){
            this.weapons[1].turnOn();

            if(Shotguntimer != null){
                Shotguntimer.clear();
            }

            if(Shotguntimer == null) {

                Shotguntimer = new Timer();
            }

            Shotguntimer.scheduleTask(new Timer.Task() {
                @Override
                public void run() {
                    weapons[1].turnOff();

                }
            }, 20f);



        }else if(type == PowerupType.ROCKETS){

            this.weapons[2].turnOn();

            if(Rockettimer != null){
                Rockettimer.clear();
            }

            if(Rockettimer == null) {

                Rockettimer = new Timer();
            }


            Rockettimer.scheduleTask(new Timer.Task() {
                @Override
                public void run() {
                    weapons[2].turnOff();

                }
            }, 20f);

        }else if(type == PowerupType.LASER){

            this.weapons[3].turnOn();

            if(Lasertimer != null){
               Lasertimer.clear();
            }

            if(Lasertimer == null) {

               Lasertimer = new Timer();
            }


           Lasertimer.scheduleTask(new Timer.Task() {
                @Override
                public void run() {
                    weapons[3].turnOff();

                }
            }, 20f);

        }


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


        if(this.Alive) {
            this.sprite.draw(batch);
        }else {

            if(this.explosion == null){
                this.explosion = new Explosion(this);
            }
            this.sprite.setColor(Color.CLEAR);

            this.explosion.draw(batch);

        }


        for(Weapon w:weapons)
        w.draw(batch);
    }

    public void dispose() {
        this.texture.dispose();

    }

    public int getHealth(){
        return this.health;
    }

    public int getScore(){
        return this.score;
    }

    public void addScore(int i){
        if(this.Alive) {
            this.score += i;
        }
    }

    public void  reset(){
        this.health = 3;
        this.Alive = true;
        for(Weapon w:weapons){
            w.turnOff();
            w.clear();
        }
        this.turnOnWeapon(0);
        this.score = 0;
    }

}
