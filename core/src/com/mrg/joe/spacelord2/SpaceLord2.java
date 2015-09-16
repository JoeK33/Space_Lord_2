package com.mrg.joe.spacelord2;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Timer;
import com.mrg.joe.spacelord2.Enemy.Enemy;
import com.mrg.joe.spacelord2.Enemy.EnemyManager;
import com.mrg.joe.spacelord2.Powerups.PowerupHandler;
import com.mrg.joe.spacelord2.Weapon.Projectile;
import com.mrg.joe.spacelord2.Weapon.Weapon;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class SpaceLord2 extends ApplicationAdapter {
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




	
	@Override
	public void create () {

		screenWidth = Gdx.graphics.getWidth();
		screenHeight = Gdx.graphics.getHeight();
		batch = new SpriteBatch();
		player = new Player();
		manager = new EnemyManager();
		collisionHandler = new CollisionHandler();
		hud = new HUD(player);
		powerupHandler = new PowerupHandler(player);






		background = new BackGround();
		Gdx.input.setInputProcessor(new TouchHandler(player));





	}

	@Override
	public void render () {

		float delta = Gdx.graphics.getDeltaTime();
		// update here
		background.update();
		player.update(delta);
		manager.update();
		powerupHandler.update(delta);
		powerupHandler.tryDeploy();

		enemyList = manager.getEnemyList();




		List enemyProjectiles = new ArrayList();

		collisionHandler.handle(delta, player.getWeapons(), enemyProjectiles, enemyList, player);



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

	}


	public Player getPlayer(){
		return player;
	}

	public static void reset(){
		screenWidth = Gdx.graphics.getWidth();
		screenHeight = Gdx.graphics.getHeight();
		batch = new SpriteBatch();
		player = new Player();
		manager = new EnemyManager();
		collisionHandler = new CollisionHandler();
		hud = new HUD(player);
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
