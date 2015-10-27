package com.mrg.joe.spacelord2;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by Joe on 8/26/2015.
 */
public class BackGround {

    private Texture bg_spriteTexture;
    private Sprite bg_sprite;
    private float bg_scrollTimer = 0.0f;




    public BackGround(){



        int screenHeight = GameConstants.GAME_HEIGHT;
        int screenWidth = GameConstants.GAME_WIDTH;

        // this stuff is for the scrolling tiling space bg
        bg_spriteTexture = new Texture(Gdx.files.internal("space_tile.png"));
        bg_spriteTexture.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
        bg_sprite = new Sprite(bg_spriteTexture, 0, 0, screenWidth, screenHeight);
        bg_sprite.setSize(screenWidth, screenHeight);
        this.update();
    }

    public void draw(SpriteBatch batch){
        bg_sprite.draw(batch);
    }

    public void update(){

        bg_scrollTimer+= Gdx.graphics.getDeltaTime();

        if(bg_scrollTimer>1.0f)
            bg_scrollTimer = 0.0f;

        bg_sprite.setV(bg_scrollTimer);
        bg_sprite.setV2(bg_scrollTimer-4f);


    }

    public void dispose(){
        this.bg_spriteTexture.dispose();
    }


}
