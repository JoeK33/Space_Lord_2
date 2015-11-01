package com.mrg.joe.spacelord2;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;

import java.text.NumberFormat;


/**
 * Created by Joe on 9/10/2015.
 * Display's player's health, score, and the text concerning pauses and restarts.
 */
public class HUD {

    private Player player;
    private Texture health;
    private float icon_gap;
    private BitmapFont font;
    private BitmapFont font2;
    private GlyphLayout layout1;
    private GlyphLayout layout2;
    private GlyphLayout layout3;
    private GlyphLayout layout4;
    private float restart_display_timer;
    private boolean restart_displayed;
    private boolean paused;
    private Preferences preferences;


    public HUD(Player player, Preferences preferences) {
        this.preferences = preferences;

        this.player = player;

        this.health = player.assets.manager.get("health_hud_icon.png");
        icon_gap = 30;
        font = new BitmapFont();
        font.getData().setScale(4, 4);

        font2 = new BitmapFont();
        font2.getData().setScale(5, 5);


        font.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        font2.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);


        layout1 = new GlyphLayout();
        layout2 = new GlyphLayout();
        layout3 = new GlyphLayout();
        layout4 = new GlyphLayout();


        restart_display_timer = 0;

        this.paused = false;

    }


    public void draw(Batch batch) {

        int player_health = player.getHealth();

        for (int i = 1; i <= player_health; i++) {

            batch.draw(health, GameConstants.GAME_WIDTH - (i * health.getWidth()) - (i * icon_gap), GameConstants.GAME_HEIGHT - health.getHeight() - icon_gap);

        }

        // format score int to look better
        CharSequence str = " " + NumberFormat.getIntegerInstance().format(player.getScore());

        font.draw(batch, str, 0, GameConstants.GAME_HEIGHT - font.getLineHeight() / 2);

        // display score and previous high score when killed.
        if (!player.isAlive()) {
            String game_over = "GAME OVER";
            layout1.setText(font2, game_over);

            font2.draw(batch, game_over, GameConstants.GAME_WIDTH / 2 - layout1.width / 2, GameConstants.GAME_HEIGHT / 2 - layout1.height / 2);
            String restart = "Touch to Restart";
            layout2.setText(font, restart);

            String score = "Score: " + NumberFormat.getIntegerInstance().format(player.getScore());
            layout3.setText(font, score);
            font.draw(batch, score, GameConstants.GAME_WIDTH / 2 - layout3.width / 2, GameConstants.GAME_HEIGHT / 2 - layout3.height * 5);

            String high_score = "Your High Score: " + NumberFormat.getIntegerInstance().format(preferences.getInteger("highscore", 0));
            layout4.setText(font, high_score);
            font.draw(batch, high_score, GameConstants.GAME_WIDTH / 2 - layout4.width / 2, GameConstants.GAME_HEIGHT / 2 - layout4.height * 7);


            if (restart_display_timer > 1) {
                restart_displayed = true;
                font.draw(batch, restart, GameConstants.GAME_WIDTH / 2 - layout2.width / 2, GameConstants.GAME_HEIGHT / 2 - (layout2.height * 2));
            }

            restart_display_timer += Gdx.graphics.getDeltaTime();


        }

        if (paused) {
            String paused = "PAUSED";
            layout1.setText(font2, paused);
            font2.draw(batch, paused, GameConstants.GAME_WIDTH / 2 - layout1.width / 2, ((GameConstants.GAME_HEIGHT / 10) * 7) - layout1.height / 2);
            String restart = "Touch to resume";
            layout2.setText(font, restart);
            font.draw(batch, restart, GameConstants.GAME_WIDTH / 2 - layout2.width / 2, ((GameConstants.GAME_HEIGHT / 10) * 7) - (layout2.height * 2));
        }


    }

    public boolean isRestart_displayed() {
        return this.restart_displayed;
    }

    public boolean isPaused() {
        return this.paused;
    }

    public void dispose() {

        font.dispose();
        font2.dispose();

    }

    public void reset() {
        this.restart_display_timer = 0;
        this.restart_displayed = false;
    }

    public void pause() {
        this.paused = true;

    }

    public void unpause() {
        this.paused = false;
    }


}
