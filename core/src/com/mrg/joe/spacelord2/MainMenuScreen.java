package com.mrg.joe.spacelord2;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.FitViewport;


/**
 * Created by Joe on 10/5/2015.
 *
 * Thanks to sadaf noor for the menu tutorial and sample code http://www.sadafnoor.com/blog/how-to-create-simple-menu-in-libgdx/
 *
 */
public class MainMenuScreen implements Screen {


    final SpaceLord2Game game;
    private Stage stage;
    private Skin skin;


    public MainMenuScreen(final SpaceLord2Game game) {
        this.game = game;

        stage = new Stage(new FitViewport(GameConstants.GAME_WIDTH,GameConstants.GAME_HEIGHT));



        Gdx.input.setInputProcessor(stage);




        // A skin can be loaded via JSON or defined programmatically, either is fine. Using a skin is optional but strongly
        // recommended solely for the convenience of getting a texture, region, etc as a drawable, tinted drawable, etc.
        skin = new Skin();
        skin.add("default", game.font);

        // Generate a 1x1 white texture and store it in the skin named "white".
        Pixmap pixmap = new Pixmap(100, 100, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.GREEN);
        pixmap.fill();

        skin.add("white", new Texture(pixmap));



        // Configure a TextButtonStyle and name it "default". Skin resources are stored by type, so this doesn't overwrite the font.
        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.up = skin.newDrawable("white", Color.DARK_GRAY);
        textButtonStyle.down = skin.newDrawable("white", Color.DARK_GRAY);
        textButtonStyle.checked = skin.newDrawable("white", Color.BLUE);
        textButtonStyle.over = skin.newDrawable("white", Color.LIGHT_GRAY);

        textButtonStyle.font = skin.getFont("default");

        skin.add("default", textButtonStyle);

        // Create a button with the "default" TextButtonStyle. A 3rd parameter can be used to specify a name other than "default".
        final TextButton playButton=new TextButton("Play",textButtonStyle);
        playButton.setPosition(GameConstants.GAME_WIDTH/2, GameConstants.GAME_HEIGHT / 2 - 100);
        stage.addActor(playButton);

        final TextButton leaderboardButton=new TextButton("Leaderboard", textButtonStyle);
        leaderboardButton.setPosition(GameConstants.GAME_WIDTH / 2, GameConstants.GAME_HEIGHT / 2 - 300);
        stage.addActor(leaderboardButton);



        playButton.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(new SpaceLord2(game));
                dispose();

            }
        });


        leaderboardButton.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {

                // do leaderboard stuff
               //dispose
            }
        });



    }



    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        game.batch.begin();
        game.font.draw(game.batch, "Welcome to Space Lord 2!!! ", Gdx.graphics.getWidth()/2 - 100, Gdx.graphics.getHeight()/2);

        game.batch.end();



        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();

    }




    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        stage.dispose();
        skin.dispose();

    }
}
