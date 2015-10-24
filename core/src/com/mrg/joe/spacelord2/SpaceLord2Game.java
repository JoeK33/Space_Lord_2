package com.mrg.joe.spacelord2;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;



/**
 * Created by Joe on 10/5/2015.
 */
public class SpaceLord2Game extends Game {



    public SpriteBatch batch;
    public BitmapFont font;
    private ActionResolver resolver;

    public SpaceLord2Game(ActionResolver resolver){
this.resolver = resolver;
    }


    public void create() {
        batch = new SpriteBatch();
        //Use LibGDX's default Arial font.
        font = new BitmapFont();
        font.getData().scale(2);
        this.setScreen(new MainMenuScreen(this, resolver));
    }

    public void render() {
        super.render();
    }

    public void dispose() {
        batch.dispose();
        font.dispose();
    }

}
