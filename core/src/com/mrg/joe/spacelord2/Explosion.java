package com.mrg.joe.spacelord2;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by Joe on 9/15/2015.
 * The explosion that happens when something is destroyed.  Sounds are controlled by the thing that is destroyed but the
 * actual explosion animation is randomly 1 of 3 possible animations.
 */
public class Explosion {

    private Sprite sprite;
    private static final int FRAME_COLS = 5;
    private static final int FRAME_ROWS = 5;

    private Animation explodeAnimation;
    private Texture explodeSheet;
    private TextureRegion[] explodeFrames;
    private float stateTime;


    public Explosion(Sprite sprite, Assets manager) {
        this.sprite = sprite;

        int pick = (int) (3 * Math.random());


        if(pick == 0){
            this.explodeSheet =   manager.manager.get("explosion_sheet.png");
        }else if(pick == 1){
            this.explodeSheet =   manager.manager.get("explosion_sheet2.png");
        }else if(pick == 2){
            this.explodeSheet =  manager.manager.get("explosion_sheet3.png");
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
        // explosion is centered on the thing that is exploding's center

        if(!this.explodeAnimation.isAnimationFinished(this.stateTime)) {
            this.stateTime += Gdx.graphics.getDeltaTime();
            batch.draw(
                    this.explodeAnimation.getKeyFrame(this.stateTime, false),
                    this.sprite.getX() + this.sprite.getWidth()/2 - ((this.explodeSheet.getWidth() / FRAME_COLS) / 2),
                    this.sprite.getY() + this.sprite.getHeight()/2 - ((this.explodeSheet.getHeight() / FRAME_ROWS) / 2)
            );

        }

    }

    public boolean isFinished(){
        return this.explodeAnimation.isAnimationFinished(this.stateTime);
    }

   public void resetTime(){
        this.stateTime = 0f;
    }
}
