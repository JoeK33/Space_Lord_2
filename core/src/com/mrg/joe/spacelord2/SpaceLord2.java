package com.mrg.joe.spacelord2;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mrg.joe.spacelord2.Enemy.Enemy;
import com.mrg.joe.spacelord2.Enemy.EnemyManager;
import com.mrg.joe.spacelord2.Powerups.PowerupHandler;
import com.mrg.joe.spacelord2.Weapon.Projectile;
import com.mrg.joe.spacelord2.Weapon.Weapon;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/*
Main loop here.  Game music from http://dreade.com/nosoap/

*/


public class SpaceLord2 implements Screen {
	final SpaceLord2Game game;
	static SpriteBatch batch;

	public static int screenWidth;
	public static int screenHeight;

	public static Player player;
	private List<Enemy> enemyList;
	private BackGround background;
	private static EnemyManager manager;
	private static CollisionHandler collisionHandler;
	public static HUD hud;
	private static PowerupHandler powerupHandler;
	private Music music;

	private Viewport viewport;
	public static Camera camera;




	
	public SpaceLord2(SpaceLord2Game game){
		this.game = game;



		screenWidth = Gdx.graphics.getWidth();
		screenHeight = Gdx.graphics.getHeight();
		batch = new SpriteBatch();
		player = new Player();
		manager = new EnemyManager();
		collisionHandler = new CollisionHandler();
		hud = new HUD(player);
		powerupHandler = new PowerupHandler(player);
		music = Gdx.audio.newMusic(Gdx.files.internal("sounds/game_music.mp3"));
		music.setLooping(true);
		music.setVolume(1);
		music.play();






		background = new BackGround();
		Gdx.input.setInputProcessor(new TouchHandler(player));
		Gdx.input.setCatchBackKey(true);

		/* camera = new PerspectiveCamera();
		viewport = new FitViewport(1080, 1920, camera);

		viewport.update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

		viewport.apply(); */


		camera = new OrthographicCamera();
		viewport = new FitViewport(GameConstants.GAME_WIDTH,GameConstants.GAME_HEIGHT,camera);
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


		if(!SpaceLord2.hud.isPaused()) {




			background.update();
			player.update(delta);
			manager.update();
			powerupHandler.update(delta);

			enemyList = manager.getEnemyList();


			List enemyProjectiles = new ArrayList();

			collisionHandler.handle(delta, player.getWeapons(), enemyProjectiles, enemyList, player);


		}



		// draw here
		batch.begin();
		background.draw(batch);
		player.draw(batch);


		// draw all the enemies
		for (Iterator it = enemyList.iterator(); it.hasNext();){
			Enemy e = (Enemy)it.next();
			e.draw(batch);
		}

		powerupHandler.draw(batch);

		hud.draw(batch);
		batch.end();

	}

	@Override
	public void resize(int width, int height){
		viewport.update(width, height);
		camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
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
	public void dispose(){
		// dispose of all graphic things
		batch.dispose();
		player.dispose();
		background.dispose();
		hud.dispose();
		for (Iterator it = enemyList.iterator(); it.hasNext();){
			Enemy e = (Enemy)it.next();
			e.dispose();
		}
		music.dispose();
		powerupHandler.dispose();



	}


	public Player getPlayer(){
		return player;
	}

	public static void reset(){
		screenWidth = GameConstants.GAME_WIDTH;
		screenHeight = GameConstants.GAME_HEIGHT;
		batch.dispose();
		batch = new SpriteBatch();
		player.dispose();
		player = new Player();
		manager = new EnemyManager();
		collisionHandler = new CollisionHandler();
		hud.dispose();
		hud = new HUD(player);
		powerupHandler.dispose();
		powerupHandler = new PowerupHandler(player);

		Timer timer = new Timer();
		timer.scheduleTask(new Timer.Task() {
			@Override
			public void run() {
				Gdx.input.setInputProcessor(new TouchHandler(player));

			}
		}, .5f);



	}





}
