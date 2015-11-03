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
    private float iconGap;
    private BitmapFont font;
    private BitmapFont font2;
    private GlyphLayout layout1;
    private GlyphLayout layout2;
    private GlyphLayout layout3;
    private GlyphLayout layout4;
    private float restartDisplayTimer;
    private boolean restartDisplayed;
    private boolean paused;
    private Preferences preferences;


    public HUD(Player player, Preferences preferences) {
        this.preferences = preferences;

        this.player = player;

        this.health = player.assets.manager.get("health_hud_icon.png");
        iconGap = 30;
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


        restartDisplayTimer = 0;

        this.paused = false;

    }


    public void draw(Batch batch) {

        int playerHealth = player.getHealth();

        for (int i = 1; i <= playerHealth; i++) {

            batch.draw(health,
                    GameConstants.GAME_WIDTH - (i * health.getWidth()) - (i * iconGap),
                    GameConstants.GAME_HEIGHT - health.getHeight() - iconGap);
        }

        // format score int to look better
        CharSequence str = " " + NumberFormat.getIntegerInstance().format(player.getScore());

        font.draw(batch, str, 0, GameConstants.GAME_HEIGHT - font.getLineHeight() / 2);

        // display score and previous high score when killed.
        if (!player.isAlive()) {
            String gameOver = "GAME OVER";
            layout1.setText(font2, gameOver);

            font2.draw(batch, gameOver,
                    GameConstants.GAME_WIDTH / 2 - layout1.width / 2,
                    GameConstants.GAME_HEIGHT / 2 - layout1.height / 2);

            String restart = "Touch to Restart";
            layout2.setText(font, restart);

            String score = "Score: " + NumberFormat.getIntegerInstance().format(player.getScore());
            layout3.setText(font, score);
            font.draw(batch, score,
                    GameConstants.GAME_WIDTH / 2 - layout3.width / 2,
                    GameConstants.GAME_HEIGHT / 2 - layout3.height * 5);

            String highScore = "Your High Score: " + NumberFormat.getIntegerInstance().format(preferences.getInteger("highscore", 0));
            layout4.setText(font, highScore);
            font.draw(batch, highScore,
                    GameConstants.GAME_WIDTH / 2 - layout4.width / 2,
                    GameConstants.GAME_HEIGHT / 2 - layout4.height * 7);


            if (restartDisplayTimer > 1) {
                restartDisplayed = true;
                font.draw(batch, restart,
                        GameConstants.GAME_WIDTH / 2 - layout2.width / 2,
                        GameConstants.GAME_HEIGHT / 2 - (layout2.height * 2));
            }

            restartDisplayTimer += Gdx.graphics.getDeltaTime();


        }

        if (paused) {
            String paused = "PAUSED";
            layout1.setText(font2, paused);
            font2.draw(batch, paused, GameConstants.GAME_WIDTH / 2 - layout1.width / 2,
                    ((GameConstants.GAME_HEIGHT / 10) * 7) - layout1.height / 2);
            String restart = "Touch to resume";
            layout2.setText(font, restart);
            font.draw(batch, restart, GameConstants.GAME_WIDTH / 2 - layout2.width / 2,
                    ((GameConstants.GAME_HEIGHT / 10) * 7) - (layout2.height * 2));
        }


    }

    public boolean isRestartDisplayed() {
        return this.restartDisplayed;
    }

    public boolean isPaused() {
        return this.paused;
    }

    public void dispose() {

        font.dispose();
        font2.dispose();

    }

    public void reset() {
        this.restartDisplayTimer = 0;
        this.restartDisplayed = false;
    }

    public void pause() {
        this.paused = true;

    }

    public void unpause() {
        this.paused = false;
    }


}
