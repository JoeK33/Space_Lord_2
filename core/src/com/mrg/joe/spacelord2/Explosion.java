package com.mrg.joe.spacelord2;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import sun.rmi.runtime.Log;

/**
 * Created by Joe on 9/15/2015.
 */
public class Explosion {

    private Sprite sprite;
    private static final int FRAME_COLS = 5;
    private static final int FRAME_ROWS = 5;

    private Animation explodeAnimation;
    private Texture explodeSheet;
    private TextureRegion[] explodeFrames;
    private float stateTime;


    public Explosion(Sprite sprite) {
        this.sprite = sprite;

        int pick = (int) (3 * Math.random());


        if(pick == 0){
            this.explodeSheet =  new Texture(Gdx.files.internal("explosion_sheet3.png"));
        }else if(pick == 1){
            this.explodeSheet =  new Texture(Gdx.files.internal("explosion_sheet2.png"));
        }else if(pick == 2){
            this.explodeSheet =  new Texture(Gdx.files.internal("explosion_sheet.png"));
        }


        TextureRegion[][] tmp = TextureRegion.split(this.explodeSheet, this.explodeSheet.getWidth() / FRAME_COLS, this.explodeSheet.getHeight() / FRAME_ROWS);
        this.explodeFrames = new TextureRegion[FRAME_COLS * FRAME_ROWS];
        int index = 0;

        for (int i = 0; i < FRAME_ROWS; i++) {
            for (int j = 0; j < FRAME_COLS; j++) {
                this.explodeFrames[index++] = tmp[i][j];
            }
        }

        this.explodeAnimation = new Animation(0.1f, this.explodeFrames);
        this.stateTime = 0f;
        this.explodeAnimation.setPlayMode(Animation.PlayMode.NORMAL);

    }



    public void draw(SpriteBatch batch) {

        if(!this.explodeAnimation.isAnimationFinished(this.stateTime)) {
            this.stateTime += Gdx.graphics.getDeltaTime();
            batch.draw(
                    this.explodeAnimation.getKeyFrame(this.stateTime, false),
                    this.sprite.getX() + this.sprite.getWidth()/2 - ((this.explodeSheet.getWidth() / FRAME_COLS) / 2),
                    this.sprite.getY() + this.sprite.getHeight()/2 - ((this.explodeSheet.getHeight() / FRAME_ROWS) / 2)
            );

            // Gdx.app.log("Explosion", "draw!");
        }

    }

    public void dispose(){
        this.explodeSheet.dispose();
    }

    public boolean isFinished(){
        return this.explodeAnimation.isAnimationFinished(this.stateTime);
    }

   public void resetTime(){
        this.stateTime = 0f;
    }
}
