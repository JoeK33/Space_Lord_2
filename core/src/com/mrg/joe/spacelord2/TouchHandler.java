package com.mrg.joe.spacelord2;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;

/**
 * Created by Joe on 8/26/2015.
 */
public class TouchHandler implements InputProcessor {

    private Player player;

    public TouchHandler(Player player){
        this.player = player;


    }


    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {


            // move player to touch x pos
            player.setXPosition((float)screenX);

        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {

        return true;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {

        player.setXPosition((float)screenX);
        return true;
    }

    // unused input listners
    @Override
    public boolean keyDown(int keycode) {
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
