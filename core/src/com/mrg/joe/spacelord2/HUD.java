package com.mrg.joe.spacelord2;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;

import java.text.NumberFormat;


/**
 * Created by Joe on 9/10/2015.
 */
public class HUD {

    private Player player;
    private Texture health;
    private float icon_gap;
    private  BitmapFont font;
    private  BitmapFont font2;
    private  BitmapFont font3;
    private GlyphLayout layout1;
    private GlyphLayout layout2;
    private float restart_display_timer;
    private boolean restart_displayed;
    private boolean paused;



    public HUD(Player player){

        this.player = player;

        this.health = new Texture(Gdx.files.internal("health_hud_icon.png"));
        icon_gap = 30;
        font = new BitmapFont();
        font.getData().setScale(4,4);

        font2 = new BitmapFont();
        font2.getData().setScale(5,5);
        font3 = new BitmapFont();
        font3.getData().setScale(4,4);

        font.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        font2.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        font3.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        layout1 = new GlyphLayout();
        layout2 = new GlyphLayout();

        restart_display_timer = 0;

        this.paused = false;

    }


    public void draw(Batch batch){

        int player_health = player.getHealth();

        for(int i = 1; i <= player_health; i++) {

            batch.draw(health, GameConstants.GAME_WIDTH - (i * health.getWidth()) - (i * icon_gap), GameConstants.GAME_HEIGHT - health.getHeight() - icon_gap);

        }

        // format score int to look better
        CharSequence str = " " +  NumberFormat.getIntegerInstance().format(player.getScore());

        font.draw(batch, str, 0, GameConstants.GAME_HEIGHT - font.getLineHeight()/2);


        if(!player.isAlive()){
            String game_over = "GAME OVER";
            layout1.setText(font2, game_over);
            font2.draw(batch, game_over, GameConstants.GAME_WIDTH / 2 - layout1.width / 2, GameConstants.GAME_HEIGHT / 2 - layout1.height / 2);
            String restart = "Touch to Restart";
            layout2.setText(font3, restart);





            if(restart_display_timer > 1){
                restart_displayed = true;
                font3.draw(batch, restart,GameConstants.GAME_WIDTH / 2 - layout2.width / 2, GameConstants.GAME_HEIGHT / 2 - (layout2.height * 2));
            }

            restart_display_timer += Gdx.graphics.getDeltaTime();



        }

        if(paused){
            String paused = "PAUSED";
            layout1.setText(font2, paused);
            font2.draw(batch, paused, GameConstants.GAME_WIDTH / 2 - layout1.width / 2, ((GameConstants.GAME_HEIGHT / 10) * 7) - layout1.height / 2);
            String restart = "Touch to resume";
            layout2.setText(font3, restart);
            font3.draw(batch, restart, GameConstants.GAME_WIDTH / 2 - layout2.width / 2, ((GameConstants.GAME_HEIGHT / 10) * 7) - (layout2.height * 2));
        }






    }

    public boolean isRestart_displayed(){
        return this.restart_displayed;
    }

    public boolean isPaused(){
        return this.paused;
    }

    public void dispose(){

        health.dispose();
        font.dispose();
        font2.dispose();
        font3.dispose();

    }

    public void reset(){
        this.restart_display_timer = 0;
        this.restart_displayed = false;
    }

    public void pause(){
        this.paused = true;

    }

    public void unpause(){
        this.paused = false;
    }


}
