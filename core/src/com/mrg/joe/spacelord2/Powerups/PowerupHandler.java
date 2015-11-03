package com.mrg.joe.spacelord2.Powerups;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.mrg.joe.spacelord2.Player;

/**
 * Created by Joe on 9/11/2015.
 *  Deploys powerups somewhat randomly.  deployed at a maximum 1 every 5 seconds.
 */
public class PowerupHandler {

    private Player player;
    private Powerup[] powerups;
    private int numberOfPowerups = 5;
    private Sound sound;
    private long interval;

    public PowerupHandler(Player player) {
        this.player = player;

        powerups = new Powerup[numberOfPowerups];
        powerups[0] = new Powerup(PowerupType.HEALTH, player.assets);
        powerups[1] = new Powerup(PowerupType.SHOTGUN, player.assets);
        powerups[2] = new Powerup(PowerupType.ROCKETS, player.assets);
        powerups[3] = new Powerup(PowerupType.LASER, player.assets);
        powerups[4] = new Powerup(PowerupType.SINE, player.assets);

        for (Powerup p: powerups) {

            p.remove();
        }

        sound = player.assets.manager.get("sounds/pickup_sound.mp3", Sound.class);

        this.deploy();
        this.interval = System.nanoTime();


    }


    public void draw(Batch batch) {
        for (Powerup p: powerups) {
            p.draw(batch);
        }
    }


    public void tryDeploy() {

        // try to deploy a powerup
          if (((int) (Math.random() * 270)) == 50) {

              // reset interval if powerup is deployed
            powerups[(int) (Math.random() * numberOfPowerups)].spawn();
              this.interval = System.nanoTime();
        }


    }


    public void deploy() {
        powerups[(int) (Math.random() * numberOfPowerups)].spawn();
    }

    public void update(float delta) {

        // don't deploy any more powerups within 5 seconds of a deployment

        if (System.nanoTime() > (interval + (5L * 1000000000L))) {
            this.tryDeploy();

        }

        // check powerup collisions

        for (Powerup p: powerups) {
            p.update(delta);

            if (player.isAlive()) {
            if (p.isColliding(player.getBoundingRectangle())) {
                    player.powerup(p.getType());
                    p.remove();
                sound.play(.4f);
                }

            }


        }


    }

    public void reset() {

        for (Powerup p: powerups) {
            p.remove();
                }
    }





}
