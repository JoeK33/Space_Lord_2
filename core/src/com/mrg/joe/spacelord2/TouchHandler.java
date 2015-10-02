package com.mrg.joe.spacelord2;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;

/**
 * Created by Joe on 8/26/2015.
 */
public class TouchHandler implements InputProcessor {

    private Player player;
    private long interval;
    public TouchHandler(Player player){
        this.player = player;


    }


    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
    if(player.isAlive()) {

        // move player to touch x pos
        player.goTo(screenX, Gdx.graphics.getHeight() - screenY + (int)player.getHeight()/2);

    }

        if(SpaceLord2.hud.isRestart_displayed()){
            //restart game here
            SpaceLord2.reset();

        }

        if(SpaceLord2.hud.isPaused()){
            SpaceLord2.hud.unpause();
            SpaceLord2.player.resumePowerupTimers();
        }

        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {

        return true;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        if(player.isAlive()) {

            player.goTo(screenX,Gdx.graphics.getHeight() - screenY + (int)player.getHeight()/2);

        }
        return true;
    }

    // unused input listners
    @Override
    public boolean keyDown(int keycode) {

        // pause on back press
        if (Gdx.input.isKeyPressed(Input.Keys.BACK)){

            if(!SpaceLord2.hud.isPaused()){
                interval = System.nanoTime();

            }

            SpaceLord2.hud.pause();
            SpaceLord2.player.pausePowerupTimers();



            // wait a second before closing game if back pressed while paused

            if(System.nanoTime() > (interval + (1000000000L)) && SpaceLord2.hud.isPaused()) {

                     Gdx.app.exit();

            }


        }
        return false;
    }
    @Override
    public boolean keyUp(int keycode) {
        return false;
    }
    @Override
    public boolean keyTyped(char character) {
        return false;
    }
    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }
    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
