package com.mrg.joe.spacelord2.Powerups;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Interpolation;
import com.mrg.joe.spacelord2.GameConstants;
import com.mrg.joe.spacelord2.Player;

/**
 * Created by Joe on 9/11/2015.
 */
public class PowerupHandler {

    private Player player;
    private Powerup[] powerups;
    private int number_of_powerups = 5;
    private Sound sound;
    private long interval;

    public PowerupHandler(Player player){
        this.player = player;

        powerups = new Powerup[number_of_powerups];
        powerups[0] = new Powerup(PowerupType.HEALTH);
        powerups[1] = new Powerup(PowerupType.SHOTGUN);
        powerups[2] = new Powerup(PowerupType.ROCKETS);
        powerups[3] = new Powerup(PowerupType.LASER);
        powerups[4] = new Powerup(PowerupType.SINE);

        for(Powerup p: powerups){

            p.remove();
        }

        sound = Gdx.audio.newSound(Gdx.files.internal("sounds/pickup_sound.mp3"));

        this.deploy();
        this.interval = System.nanoTime();


    }


    public void draw(Batch batch){
        for(Powerup p: powerups){
            p.draw(batch);
        }
    }


    public void tryDeploy(){

        // try to deploy a powerup
          if(((int)(Math.random() * 270)) == 50) {

              // reset interval if powerup is deployed
            powerups[(int) (Math.random() * number_of_powerups)].spawn();
              this.interval = System.nanoTime();
        }


    }


    public void deploy(){
        powerups[(int) (Math.random() * number_of_powerups)].spawn();
    }

    public void update(float delta){

        // don't deploy any more powerups within 5 seconds of a deployment

        if(System.nanoTime() > (interval + (5L * 1000000000L))){
            this.tryDeploy();

        }

        // check powerup collisions

        for(Powerup p: powerups){
            p.update(delta);

            if(player.isAlive()) {
            if(p.isColliding(player.getBoundingRectangle())){
                    player.Powerup(p.getType());
                    p.remove();
                sound.play(.4f);
                }

            }


        }


    }

    public void reset(){

        for(Powerup p: powerups){
            p.remove();
                }
    }

    public void dispose(){
        this.sound.dispose();
    }




}
