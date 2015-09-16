package com.mrg.joe.spacelord2.Powerups;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Interpolation;
import com.mrg.joe.spacelord2.Player;

/**
 * Created by Joe on 9/11/2015.
 */
public class PowerupHandler {

    private Player player;
    private Powerup[] powerups;
    private int number_of_powerups = 4;

    public PowerupHandler(Player player){
        this.player = player;

        powerups = new Powerup[number_of_powerups];
        powerups[0] = new Powerup(PowerupType.HEALTH);
        powerups[1] = new Powerup(PowerupType.SHOTGUN);
        powerups[2] = new Powerup(PowerupType.ROCKETS);
        powerups[3] = new Powerup(PowerupType.LASER);

        for(Powerup p: powerups){

            p.remove();
        }

    }


    public void draw(Batch batch){
        for(Powerup p: powerups){
            p.draw(batch);
        }
    }


    public void tryDeploy(){

        // try to deploy a powerup
          if(((int)(Math.random() * 100)) == 50) {

            powerups[(int) (Math.random() * number_of_powerups)].spawn();
        }


    }

    public void update(float delta){

        for(Powerup p: powerups){
            p.update(delta);

            if(player.isAlive()) {
            if(p.isColliding(player.getBoundingRectangle())){
                    player.Powerup(p.getType());
                    p.remove();
                }

            }


        }


    }

    public void reset(){

        for(Powerup p: powerups){
            p.remove();
                }
    }




}
