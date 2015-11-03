package com.mrg.joe.spacelord2;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mrg.joe.spacelord2.Enemy.Enemy;
import com.mrg.joe.spacelord2.Enemy.EnemyManager;
import com.mrg.joe.spacelord2.Enemy.EnemyPools;
import com.mrg.joe.spacelord2.Powerups.PowerupHandler;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/*
Main loop here.  Game music from http://dreade.com/nosoap/

*/


public class SpaceLord2 implements Screen {

    final SpaceLord2Game game;
    static SpriteBatch batch;
    public static Player player;
    private static List<Enemy> enemyList;
    private BackGround background;
    private static EnemyManager manager;
    private static CollisionHandler collisionHandler;
    public static HUD hud;
    private static PowerupHandler powerupHandler;
    private Music music;
    private static EnemyPools enemyPools;
    private Viewport viewport;
    public static Camera camera;
    private static ActionResolver resolver;
    private static boolean scoreSubmited;
    public static boolean toMenuPressed;
    public Preferences prefs;
    private Assets assets;

    public SpaceLord2(SpaceLord2Game game, ActionResolver resolver) {

        this.game = game;
        this.resolver = resolver;
        assets = new Assets();
        assets.load();

        prefs = Gdx.app.getPreferences("preferences");

        batch = new SpriteBatch();
        player = new Player(assets);
        enemyPools = new EnemyPools(assets, player);
        manager = new EnemyManager(enemyPools);
        collisionHandler = new CollisionHandler(enemyPools);
        hud = new HUD(player, prefs);
        powerupHandler = new PowerupHandler(player);

        music = assets.manager.get("sounds/game_music.mp3");
        music.setLooping(true);
        music.setVolume(.8f);
        music.play();

        background = new BackGround();
        Gdx.input.setInputProcessor(new TouchHandler(player, this));
        Gdx.input.setCatchBackKey(true);


        // used to support various screen sizes.
        camera = new OrthographicCamera();
        viewport = new FitViewport(GameConstants.GAME_WIDTH, GameConstants.GAME_HEIGHT, camera);
        viewport.apply();
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);

    }


    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        // needed to handle different resolution sizes
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        assets.manager.update();

        // go to the menu again
        if (toMenuPressed) {
            this.game.setScreen(new MainMenuScreen(this.game, this.resolver));
            toMenuPressed = false;
            dispose();
        }


        // don't update when paused
        if (!SpaceLord2.hud.isPaused()) {

            background.update();
            player.update(delta);
            manager.update();
            powerupHandler.update(delta);
            enemyList = manager.getEnemyList();

            List enemyProjectiles = new ArrayList();

            collisionHandler.handle(delta, player.getWeapons(), enemyProjectiles, enemyList, player);
        }

        // submits score once upon death.
        if (!player.isAlive() && !scoreSubmited) {
            this.submitScore();
            scoreSubmited = true;
        }
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        // draw here
        batch.begin();
        background.draw(batch);
        player.draw(batch);

        // draw all the enemies
        for (Iterator it = enemyList.iterator(); it.hasNext();) {
            Enemy e = (Enemy) it.next();
            e.draw(batch);
        }
        powerupHandler.draw(batch);
        hud.draw(batch);
        batch.end();

    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {
        assets.load();

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        // dispose of all graphic things
        batch.dispose();
        background.dispose();
        hud.dispose();
        assets.dispose();

    }


    public Player getPlayer() {
        return player;
    }

    public void submitScore() {

        // if player beats their own locally stored high score, sent it
        if (this.player.getScore() > prefs.getInteger("highscore", 0)) {
            prefs.putInteger("highscore", this.player.getScore());
            prefs.flush();
            resolver.submitScore(this.player.getScore());
            Gdx.app.log("SCORE:", Integer.toString(this.player.getScore()) + " SUBMITTED");

        }

    }

    // game over, set up a new one
    public void reset() {

        for (Enemy e : enemyList) {
            enemyPools.free(e);
        }

        enemyList.clear();


        batch.dispose();
        batch = new SpriteBatch();
        player = new Player(assets);

        // enemies need a reference to the new player to track them, so here they are provided with one
        enemyPools.changePlayer(player);
        manager = new EnemyManager(enemyPools);
        collisionHandler = new CollisionHandler(enemyPools);
        hud.dispose();
        hud = new HUD(player, prefs);
        powerupHandler = new PowerupHandler(player);

        Timer timer = new Timer();
        timer.scheduleTask(new Timer.Task() {
            @Override
            public void run() {
                Gdx.input.setInputProcessor(new TouchHandler(player, SpaceLord2.this));

            }
        }, .5f);


        scoreSubmited = false;

    }


}
