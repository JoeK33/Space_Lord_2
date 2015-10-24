package com.mrg.joe.spacelord2;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputListener;
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
    private  ActionResolver resolver;
    private Texture title;
    private BackGround backGround;
    private Table table;



    public MainMenuScreen(final SpaceLord2Game game, final ActionResolver resolver) {
        this.game = game;
        this.resolver = resolver;

        this.game.font = new BitmapFont();
        this.game.font.getData().scale(3);
        this.game.font.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        title = new Texture(Gdx.files.internal("space_lord_2_title.png"));

        backGround = new BackGround();

        table = new Table();
        table.defaults().width(400).padBottom(100);





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
        textButtonStyle.down = skin.newDrawable("white", Color.WHITE);
        textButtonStyle.checked = skin.newDrawable("white", Color.DARK_GRAY);
        textButtonStyle.over = skin.newDrawable("white", Color.LIGHT_GRAY);

        textButtonStyle.font = skin.getFont("default");

        skin.add("default", textButtonStyle);

        // Create a button with the "default" TextButtonStyle. A 3rd parameter can be used to specify a name other than "default".
        final TextButton playButton = new TextButton("Play",textButtonStyle);
       table.add(playButton);
        table.row();
     //  playButton.setPosition(GameConstants.GAME_WIDTH / 4, GameConstants.GAME_HEIGHT / 2);
       // stage.addActor(playButton);

        final TextButton leaderboardButton = new TextButton("Leaderboard", textButtonStyle);
        table.add(leaderboardButton);
        table.row();
      //  leaderboardButton.setPosition(GameConstants.GAME_WIDTH / 4, GameConstants.GAME_HEIGHT / 2 - 400);
      //  stage.addActor(leaderboardButton);

        final TextButton signInButton = new TextButton("Sign In", textButtonStyle);
       table.add(signInButton);
        table.row();
     //   signInButton.setPosition(GameConstants.GAME_WIDTH / 4, GameConstants.GAME_HEIGHT / 2 - 800);
      //  stage.addActor(signInButton);



        table.setPosition(GameConstants.GAME_WIDTH / 3, (GameConstants.GAME_HEIGHT / 5) * 2);

        stage.addActor(table);





        playButton.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(new SpaceLord2(game, resolver, MainMenuScreen.this));
                dispose();

            }
        });


        leaderboardButton.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {
                resolver.showLeaderboard();
            }
        });

        signInButton.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {
                resolver.signIn();

            }
        });



    }



    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {


        // quit game if back pressed on main menu
        if (Gdx.input.isKeyPressed(Input.Keys.BACK)){
            Gdx.app.exit();
        }


        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        backGround.update();


        game.batch.begin();

        backGround.draw(game.batch);
     //   game.font.draw(game.batch, "Welcome to Space Lord 2!!! ", Gdx.graphics.getWidth() / 2 - 100, Gdx.graphics.getHeight() / 2);
        game.batch.draw(title, 0, GameConstants.GAME_HEIGHT - title.getHeight());
        table.draw(game.batch,1);
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
        title.dispose();

    }
}
