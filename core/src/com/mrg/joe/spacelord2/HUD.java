package com.mrg.joe.spacelord2;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * Created by Joe on 9/10/2015.
 */
public class HUD {

    private Player player;
    private Texture health;
    private float icon_gap;

    public HUD(Player player){

        this.player = player;

        this.health = new Texture("health_hud_icon.png");
        icon_gap = 30;

    }


    public void draw(Batch batch){

        int player_health = player.getHealth();

        for(int i = 1; i <= player_health; i++) {

            batch.draw(health, Gdx.graphics.getWidth() - (i * health.getWidth()) - (i * icon_gap), Gdx.graphics.getHeight() - health.getHeight() - icon_gap);

        }


        CharSequence str = "Score: " + player.getScore();
        BitmapFont font = new BitmapFont();
        font.getData().setScale(2,2);


        font.draw(batch, str, 0, Gdx.graphics.getHeight() - font.getLineHeight());






    }

    public void dispose(){

        health.dispose();

    }


}
