package com.mrg.joe.spacelord2;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.Sprite;

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

        layout1 = new GlyphLayout();
        layout2 = new GlyphLayout();

        restart_display_timer = 0;

    }


    public void draw(Batch batch){

        int player_health = player.getHealth();

        for(int i = 1; i <= player_health; i++) {

            batch.draw(health, Gdx.graphics.getWidth() - (i * health.getWidth()) - (i * icon_gap), Gdx.graphics.getHeight() - health.getHeight() - icon_gap);

        }

        CharSequence str = " " + Integer.toString(player.getScore());

        font.draw(batch, str, 0, Gdx.graphics.getHeight() - font.getLineHeight()/2);


        if(!player.isAlive()){
            String game_over = "GAME OVER";
            layout1.setText(font2, game_over);
            font2.draw(batch, game_over, Gdx.graphics.getWidth() / 2 - layout1.width / 2, Gdx.graphics.getHeight() / 2 - layout1.height / 2);
            String restart = "Touch to Restart";
            layout2.setText(font3, restart);





            if(restart_display_timer > 2){
                restart_displayed = true;
                font3.draw(batch, restart, Gdx.graphics.getWidth() / 2 - layout2.width / 2, Gdx.graphics.getHeight() / 2 - (layout2.height * 2));
            }

            restart_display_timer += Gdx.graphics.getDeltaTime();



        }






    }

    public boolean isRestart_displayed(){
        return this.restart_displayed;
    }

    public void dispose(){

        health.dispose();
        font.dispose();
        font2.dispose();

    }

    public void reset(){
        this.restart_display_timer = 0;
        this.restart_displayed = false;
    }


}
