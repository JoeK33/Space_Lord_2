package com.mrg.joe.spacelord2.Weapon;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Timer;
import com.mrg.joe.spacelord2.GameConstants;
import com.mrg.joe.spacelord2.Player;


/**
 * Created by Joe on 9/10/2015.
 */
public class PlayerRocketProjectile extends Projectile {

    private ProjectilePosition position;
    private boolean advancing;


    public PlayerRocketProjectile(Player player, ProjectilePosition position) {

        super(player.getPlayerNosePosition(), GameConstants.player_rocket_damage, new Texture(Gdx.files.internal("weapons/player_rocket_projectile.png")));
        this.position = position;

        if (this.position == ProjectilePosition.LEFT_ROCKET) {

            this.sprite.setPosition(this.getX() - this.sprite.getWidth() - player.getWidth() / 2, this.getY() - this.sprite.getHeight());

        } else if (this.position == ProjectilePosition.RIGHT_ROCKET) {

            this.sprite.setPosition(this.getX() + player.getWidth() / 2, this.getY() - this.sprite.getHeight());
        }

        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                advancing = true;
                changeTexture();
            }
        }, .8f);


    }

    @Override
    public void update(float delta) {


        // projectile behaviors here

        if (advancing) {
            // rockets going towards enemy

            this.sprite.setY((this.sprite.getY() + (delta * 2 * GameConstants.projectile_speed)));

        } else {
            // rockets moving sideways away from player
            if (this.position == ProjectilePosition.LEFT_ROCKET) {

                this.sprite.setPosition(this.sprite.getX() - 100 * delta, this.sprite.getY() + 100 * delta);

            } else if (this.position == ProjectilePosition.RIGHT_ROCKET) {

                this.sprite.setPosition(this.sprite.getX() + 100 * delta, this.sprite.getY() + 100 * delta);


            }
        }


        if (this.sprite.getY() > Gdx.graphics.getHeight()) {
            this.remove();
        }

    }

    private void changeTexture(){
        this.sprite.setTexture(new Texture(Gdx.files.internal("weapons/player_rocket_projectile_fired.png")));
    }


}
