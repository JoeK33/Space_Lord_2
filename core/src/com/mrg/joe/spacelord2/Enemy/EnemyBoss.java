package com.mrg.joe.spacelord2.Enemy;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mrg.joe.spacelord2.GameConstants;
import com.mrg.joe.spacelord2.Player;
import com.mrg.joe.spacelord2.SpaceLord2;
import com.mrg.joe.spacelord2.Weapon.EnemyBossWeapon;
import com.mrg.joe.spacelord2.Weapon.EnemyMachineGunWeapon;
import com.mrg.joe.spacelord2.Weapon.Weapon;

/**
 * Created by Joe on 8/28/2015.
 */
public class EnemyBoss extends Enemy {

    private Weapon weapon;
    private int small_projectile_width = 12;


    public EnemyBoss(float x, float y, Behavior b) {

        super(GameConstants.enemy_boss_health, x, y, new Texture("enemies/enemy_boss.png"), 5000, b);
        this.weapon = new EnemyBossWeapon(this);
        super.weapon = this.weapon;


    }

    @Override
    public void update(float delta) {

        super.update(delta);


        this.weapon.update(delta);


    }

    public float[] getWeapon1Pos() {
        float[] pos = this.getNosePos();
        pos[0] -= ((this.getWidth() / 10 + small_projectile_width ));
        return pos;
    }

    public float[] getWeapon2Pos() {
        float[] pos = this.getNosePos();
        pos[0] += ((this.getWidth() / 10) - small_projectile_width);
        return pos;
    }

    public float[] getWeapon3Pos() {
        float[] pos = this.getNosePos();
        pos[0] -= this.getWidth() / 10 - (2 * small_projectile_width);
        pos[1] += this.getHeight() / 2;

        return pos;
    }


}
