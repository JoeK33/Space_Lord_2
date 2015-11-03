package com.mrg.joe.spacelord2;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by Joe on 8/26/2015.
 * Scrolling space background
 */
public class BackGround {

    private Texture bgSpriteTexture;
    private Sprite bgSprite;
    private float bgScrollTimer = 0.0f;


    public BackGround() {


        int screenHeight = GameConstants.GAME_HEIGHT;
        int screenWidth = GameConstants.GAME_WIDTH;

        bgSpriteTexture = new Texture(Gdx.files.internal("space_tile.png"));
        bgSpriteTexture.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
        bgSprite = new Sprite(bgSpriteTexture, 0, 0, screenWidth, screenHeight);
        bgSprite.setSize(screenWidth, screenHeight);
        this.update();
    }

    public void draw(SpriteBatch batch) {
        bgSprite.draw(batch);
    }

    public void update() {

        bgScrollTimer += Gdx.graphics.getDeltaTime();

        if (bgScrollTimer > 1.0f)
            bgScrollTimer = 0.0f;

        bgSprite.setV(bgScrollTimer);
        bgSprite.setV2(bgScrollTimer - 4f);


    }

    public void dispose() {
        this.bgSpriteTexture.dispose();
    }


}
