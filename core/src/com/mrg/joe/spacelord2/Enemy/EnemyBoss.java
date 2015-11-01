package com.mrg.joe.spacelord2.Enemy;

import com.mrg.joe.spacelord2.GameConstants;
import com.mrg.joe.spacelord2.Weapon.EnemyBossWeapon;
import com.mrg.joe.spacelord2.Weapon.Weapon;

/**
 * Created by Joe on 8/28/2015.
 */
public class EnemyBoss extends Enemy {

    private Weapon weapon;
    private int small_projectile_width = 12;


    public EnemyBoss() {

        super(GameConstants.enemy_boss_health, 5000);


    }

    public void create() {

        this.weapon = new EnemyBossWeapon(this);
        super.weapon = this.weapon;
        this.changeDeathSound();
    }

    @Override
    public void update(float delta) {

        super.update(delta);
        this.weapon.update(delta);
    }

    public float[] getWeapon1Pos() {
        float[] pos = this.getNosePos();
        pos[0] -= ((this.getWidth() / 10 + small_projectile_width));
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
