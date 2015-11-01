package com.mrg.joe.spacelord2;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.Timer;
import com.mrg.joe.spacelord2.Powerups.PowerupType;
import com.mrg.joe.spacelord2.Weapon.PlayerRocketWeapon;
import com.mrg.joe.spacelord2.Weapon.PlayerShotgunWeapon;
import com.mrg.joe.spacelord2.Weapon.PlayerSineWeapon;
import com.mrg.joe.spacelord2.Weapon.PlayerTripleLaserWeapon;
import com.mrg.joe.spacelord2.Weapon.PlayerWeapon;
import com.mrg.joe.spacelord2.Weapon.Weapon;

/**
 * Created by Joe on 8/26/2015.
 *
 * This is the player.  Controls all the player's weapons and the timers that govern them.  Controls player movement and restricts where they can move
 * in the field of play.
 */
public class Player {

    private Texture texture;
    private Sprite sprite;
    private Weapon[] weapons;
    private int health;
    private Color color;
    private int score;
    private Timer Shotguntimer;
    private Timer Rockettimer;
    private Timer Lasertimer;
    private Timer Sinetimer;
    private Explosion explosion;
    private boolean Alive;
    private Sound hitSound;
    private Sound explosionSound;
    private boolean explosionPlayed;
    private long timerDelay;
    private int goToX;
    private int goToY;
    public Assets assets;


    public Player(Assets assets) {
        this.assets = assets;

        this.texture = assets.manager.get("player.png");
        this.sprite = new Sprite(texture);
        this.Alive = true;

        // players weapons go here.
        this.weapons = new Weapon[5];
        this.weapons[0] = new PlayerWeapon(this);
        // this.weapons[0].turnOff();
        this.weapons[1] = new PlayerShotgunWeapon(this);
        this.weapons[1].turnOff();
        this.weapons[2] = new PlayerRocketWeapon(this);
        this.weapons[2].turnOff();
        this.weapons[3] = new PlayerTripleLaserWeapon(this);
        this.weapons[3].turnOff();
        this.weapons[4] = new PlayerSineWeapon(this);
        this.weapons[4].turnOff();


        this.sprite.setPosition((GameConstants.GAME_WIDTH / 2) - (this.sprite.getWidth() / 2), GameConstants.GAME_HEIGHT / 5);
        this.goToY = GameConstants.GAME_HEIGHT / 5;
        this.goToX = ((GameConstants.GAME_WIDTH / 2));
        this.color = this.sprite.getColor();

        // player starts with 3 health
        this.health = 3;

        this.explosion = new Explosion(this.sprite, this.assets);

        hitSound = assets.manager.get("sounds/player_hit.mp3", Sound.class);
        explosionSound = assets.manager.get("sounds/explosion.mp3", Sound.class);


    }

    public float[] getPlayerNosePosition() {

        float[] nose_pos = new float[2];
        nose_pos[0] = this.getX() + this.getWidth() / 2;
        nose_pos[1] = this.getY() + this.getHeight();
        return nose_pos;
    }

    // scale rectangle a bit to make hit detection more forgiving.
    public Rectangle getBoundingRectangle() {

        Rectangle rectangle = this.sprite.getBoundingRectangle();
        rectangle.setSize(this.sprite.getWidth(), (this.sprite.getHeight() / 4) * 3);
        return rectangle;
    }

    public void update(float delta) {

        if (!this.isAlive() && !explosionPlayed) {
            explosionSound.play(.7f);
            explosionPlayed = true;
        }

        if (this.getCenterX() < this.goToX) {
            this.sprite.setX(this.sprite.getX() + (2000 * delta));

        }

        if (this.getCenterX() > this.goToX) {
            this.sprite.setX(this.sprite.getX() - (2000 * delta));

        }

        if (this.getY() > this.goToY) {
            this.sprite.setY(this.sprite.getY() - (2000 * delta));

        }

        if (this.getY() < this.goToY) {
            this.sprite.setY(this.sprite.getY() + (2000 * delta));

        }


        // keep player on screen
        if (this.sprite.getX() + this.sprite.getWidth() > GameConstants.GAME_WIDTH) {
            this.sprite.setPosition(GameConstants.GAME_WIDTH - this.sprite.getWidth(), this.sprite.getY());
        }

        if (this.sprite.getX() < 0) {
            this.sprite.setPosition(0, this.sprite.getY());
        }

        if (this.sprite.getY() < 0) {
            this.sprite.setPosition(this.sprite.getX(), 0);
        }

        if (this.sprite.getY() > GameConstants.GAME_HEIGHT / 4) {
            this.sprite.setPosition(this.sprite.getX(), GameConstants.GAME_HEIGHT / 4);
        }


        if (this.getHealth() <= 0) {
            this.Alive = false;
        }

        // make weapons stop firing when player dies
        if (!this.Alive) {

            for (Weapon w : weapons) {
                w.turnOff();
            }


        }

        // update all player weapons
        for (Weapon w : weapons) {
            w.update(delta);
        }


    }

    public void turnOffWeapon(int weaponSlot) {

        if (weaponSlot <= weapons.length && weaponSlot >= 0) {

            this.weapons[weaponSlot].turnOff();
        }
    }

    public boolean isAlive() {
        return this.Alive;
    }

    public void turnOnWeapon(int weaponSlot) {

        if (weaponSlot <= weapons.length && weaponSlot >= 0) {
            this.weapons[weaponSlot].turnOn();
        }
    }

    public void takeHit() {
        this.health--;
        hitSound.play(.8f);
        sprite.setColor(256, 256, 256, 256);

        Gdx.app.log("Player", "health remaining" + this.health);

        Timer timer = new Timer();
        timer.scheduleTask(new Timer.Task() {
            @Override
            public void run() {
                sprite.setColor(color);

            }
        }, .1f);


    }


    // timers for the powerup weapons
    public void Powerup(PowerupType type) {

        if (type == PowerupType.HEALTH) {

            if (this.health < 4) {
                this.health++;
            }
        } else if (type == PowerupType.SHOTGUN) {
            this.weapons[1].turnOn();

            if (Shotguntimer != null) {
                Shotguntimer.clear();
            }

            if (Shotguntimer == null) {

                Shotguntimer = new Timer();
            }

            Shotguntimer.scheduleTask(new Timer.Task() {
                @Override
                public void run() {
                    weapons[1].turnOff();

                }
            }, 20f);


        } else if (type == PowerupType.ROCKETS) {

            this.weapons[2].turnOn();

            if (Rockettimer != null) {
                Rockettimer.clear();
            }

            if (Rockettimer == null) {

                this.Rockettimer = new Timer();
            }


            Rockettimer.scheduleTask(new Timer.Task() {
                @Override
                public void run() {
                    weapons[2].turnOff();

                }
            }, 20f);

        } else if (type == PowerupType.LASER) {

            this.weapons[3].turnOn();

            if (Lasertimer != null) {
                Lasertimer.clear();
            }

            if (Lasertimer == null) {

                this.Lasertimer = new Timer();
            }


            Lasertimer.scheduleTask(new Timer.Task() {
                @Override
                public void run() {
                    weapons[3].turnOff();

                }
            }, 20f);

        } else if (type == PowerupType.SINE) {

            this.weapons[4].turnOn();

            if (Sinetimer != null) {
                Sinetimer.clear();
            }

            if (Sinetimer == null) {

                this.Sinetimer = new Timer();
            }


            Sinetimer.scheduleTask(new Timer.Task() {
                @Override
                public void run() {
                    weapons[4].turnOff();

                }
            }, 20f);


        }
    }

    public float getX() {
        return this.sprite.getX();
    }

    public float getCenterX() {
        return this.getWidth() / 2 + this.getX();
    }

    public float getY() {
        return this.sprite.getY();
    }

    public float getHeight() {
        return this.sprite.getHeight();
    }

    public float getWidth() {
        return this.sprite.getWidth();
    }

    public Weapon[] getWeapons() {
        return this.weapons;
    }


    public void draw(SpriteBatch batch) {


        if (this.Alive) {
            this.sprite.draw(batch);
        } else {
            this.explosion.draw(batch);
        }


        for (Weapon w : weapons)
            w.draw(batch);
    }



    public int getHealth() {
        return this.health;
    }

    public int getScore() {
        return this.score;
    }

    public void addScore(int i) {
        if (this.Alive) {
            this.score += i;
        }
    }

    public void goTo(int x, int y) {

        this.goToX = x;
        this.goToY = y;


    }

    // used for pausing the game.
    public void pausePowerupTimers() {

        timerDelay = TimeUtils.nanosToMillis(TimeUtils.nanoTime());

        if (Shotguntimer != null) {
            Shotguntimer.stop();
        }

        if (Rockettimer != null) {
            Rockettimer.stop();
        }
        if (Lasertimer != null) {
            Lasertimer.stop();
        }
        if (Sinetimer != null) {
            Sinetimer.stop();
        }
    }

    // resumes power up timers.  time paused must be added to the timer otherwise it will have kept running
    //  in its own thread while the game was paused.
    public void resumePowerupTimers() {

        if (Shotguntimer != null) {
            Shotguntimer.delay(TimeUtils.nanosToMillis(TimeUtils.nanoTime()) - timerDelay);
            Shotguntimer.start();
        }

        if (Rockettimer != null) {
            Rockettimer.delay(TimeUtils.nanosToMillis(TimeUtils.nanoTime()) - timerDelay);
            Rockettimer.start();
        }
        if (Lasertimer != null) {
            Lasertimer.delay(TimeUtils.nanosToMillis(TimeUtils.nanoTime()) - timerDelay);
            Lasertimer.start();
        }
        if (Sinetimer != null) {
            Sinetimer.delay(TimeUtils.nanosToMillis(TimeUtils.nanoTime()) - timerDelay);
            Sinetimer.start();
        }

    }

}
