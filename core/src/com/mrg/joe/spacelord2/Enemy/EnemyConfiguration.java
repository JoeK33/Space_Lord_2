package com.mrg.joe.spacelord2.Enemy;


import com.mrg.joe.spacelord2.GameConstants;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Joe on 8/26/2015.
 * These are the "rows" of enemies that stack onto the play area.  This is where they are initialized and configured.
 * They are then added to the list of enemies in play
 * <p/>
 * Config legend:
 * <p/>
 * 1: four fighters
 * 2: 7 small enemies
 * 3: 1 patrolling mg
 * 4: boss 1 track player
 * 5: 1 blaster tracking player
 * 6: boss 2 patrol
 * 7: 1 hunter
 * 8: boss 3
 * 9: boss 4
 * 10: 2 wiggling mgs
 * 11: 2 wiggling blasters
 * 12: 3 wiggling fighters
 * 13: 2 small enemies
 * 14: 4 small enemies
 * 15: 2 wiggling fighters
 * 16: single centered wiggling mg
 * 17: 3 small enemies
 */
public class EnemyConfiguration {

    private float rowY;
    private LinkedList<Enemy> enemyList;
    private float rowHeight;

    public EnemyConfiguration(int config, EnemyPools pools) {
        float spawnHeight = GameConstants.GAME_HEIGHT + 200;
        float gameWidth = GameConstants.GAME_WIDTH;
        enemyList = new LinkedList<Enemy>();
        this.setY(spawnHeight);

        if (config == 0) {
        } else if (config == 1) {
            // four fighters
            Enemy[] enemies = new Enemy[4];
            for (int i = 0; i < 4; i++) {
                enemies[i] = pools.enemyFighterPool.obtain();
            }
            rowHeight = enemies[0].getHeight();
            float enemyOffset = (enemies[0].getWidth() / 4) * 2;
            enemies[0].init((gameWidth / 8) * 1 - enemyOffset, this.rowY, Behavior.WIGGLE);
            enemies[1].init((gameWidth / 8) * 3 - enemyOffset, this.rowY, Behavior.WIGGLE);
            enemies[2].init((gameWidth / 8) * 5 - enemyOffset, this.rowY, Behavior.WIGGLE);
            enemies[3].init((gameWidth / 8) * 7 - enemyOffset, this.rowY, Behavior.WIGGLE);
            for (int i = 0; i < 4; i++) {
                enemyList.add(enemies[i]);
            }
        } else if (config == 2) {
            // a row of 7 small enemies
            Enemy[] enemies = new Enemy[7];
            for (int i = 0; i < 7; i++) {
                enemies[i] = pools.enemySmallPool.obtain();
            }
            rowHeight = enemies[0].getHeight();
            float enemyOffset = (enemies[0].getWidth() / 4) * 2;
            for (int i = 1; i < 8; i++) {
                enemies[i - 1].init((gameWidth / 8) * i - enemyOffset, this.rowY, Behavior.WIGGLE);
                enemyList.add(enemies[i - 1]);
            }
        } else if (config == 3) {
            //single patrolling machine gun enemy
            Enemy e = pools.enemyMgPool.obtain();
            rowHeight = e.getHeight();
            float enemyX = (0 + (int) (Math.random() * ((gameWidth - e.getWidth()) + 1)));
            e.init(enemyX, this.rowY, Behavior.PATROL);
            enemyList.add(e);
        } else if (config == 4) {
            // boss
            Enemy e = pools.enemyBossPool.obtain();
            rowHeight = e.getHeight();
            e.init((gameWidth / 2) - (e.getWidth() / 2), this.rowY, Behavior.TRACK_PLAYER);
            enemyList.add(e);
        } else if (config == 5) {
            // single blaster enemy
            Enemy e = pools.enemyBlasterPool.obtain();
            rowHeight = e.getHeight();
            e.init((gameWidth / 2) - (e.getWidth() / 2), this.rowY, Behavior.TRACK_PLAYER);
            enemyList.add(e);
        } else if (config == 6) {
            // single boss 2 and hunter
            Enemy e1 = pools.enemyBoss2Pool.obtain();
            Enemy e2 = pools.enemyHunterPool.obtain();
            rowHeight = e1.getHeight() + 200;
            e1.init((gameWidth / 2) - (e1.getWidth() / 2), this.rowY, Behavior.TRACK_PLAYER);
            e2.init((gameWidth / 2) - (e2.getWidth() / 2), this.rowY + 200, Behavior.HUNT);
            enemyList.add(e1);
            enemyList.add(e2);
        } else if (config == 7) {
            // single hunter
            Enemy e = pools.enemyHunterPool.obtain();
            rowHeight = e.getHeight();
            e.init((gameWidth / 2) - (e.getWidth() / 2), this.rowY, Behavior.HUNT);
            enemyList.add(e);
        } else if (config == 8) {
            // single boss 3
            Enemy e = pools.enemyBoss3Pool.obtain();
            rowHeight = e.getHeight();
            e.init((gameWidth / 2) - (e.getWidth() / 2), this.rowY, Behavior.PATROL);
            enemyList.add(e);
        } else if (config == 9) {
            // single boss 4
            Enemy e = pools.enemyBoss4Pool.obtain();
            rowHeight = e.getHeight();
            e.init((gameWidth / 2) - (e.getWidth() / 2), this.rowY, Behavior.PATROL);
            enemyList.add(e);
        } else if (config == 10) {
            //two wiggling machine gun enemies
            Enemy e1 = pools.enemyMgPool.obtain();
            Enemy e2 = pools.enemyMgPool.obtain();
            rowHeight = e1.getHeight();
            e1.init(gameWidth / 4 - e1.getWidth() / 2, this.rowY, Behavior.WIGGLE);
            e2.init((gameWidth / 4) * 3 - e2.getWidth() / 2, this.rowY, Behavior.WIGGLE);
            enemyList.add(e1);
            enemyList.add(e2);
        } else if (config == 11) {
            //two wiggling blaster enemies
            Enemy e1 = pools.enemyBlasterPool.obtain();
            Enemy e2 = pools.enemyBlasterPool.obtain();
            rowHeight = e1.getHeight();
            e1.init(gameWidth / 4 - e1.getWidth() / 2, this.rowY, Behavior.WIGGLE);
            e2.init((gameWidth / 4) * 3 - e2.getWidth() / 2, this.rowY, Behavior.WIGGLE);
            enemyList.add(e1);
            enemyList.add(e2);
        } else if (config == 12) {
            //three wiggling fighter enemies
            Enemy[] enemies = new Enemy[3];
            for (int i = 0; i < 3; i++) {
                enemies[i] = pools.enemyFighterPool.obtain();
            }
            rowHeight = enemies[0].getHeight();
            enemies[0].init((gameWidth / 4) - enemies[0].getWidth() / 2, this.rowY, Behavior.WIGGLE);
            enemies[1].init((gameWidth / 4) * 2 - enemies[1].getWidth() / 2, this.rowY, Behavior.WIGGLE);
            enemies[2].init((gameWidth / 4) * 3 - enemies[2].getWidth() / 2, this.rowY, Behavior.WIGGLE);
            for (int i = 0; i < 3; i++) {
                enemyList.add(enemies[i]);
            }
        } else if (config == 13) {
            //two small enemies
            Enemy e1 = pools.enemySmallPool.obtain();
            Enemy e2 = pools.enemySmallPool.obtain();
            rowHeight = e1.getHeight();
            e1.init(gameWidth / 4 - e1.getWidth() / 2, this.rowY, Behavior.WIGGLE);
            e2.init((gameWidth / 4) * 3 - e2.getWidth() / 2, this.rowY, Behavior.WIGGLE);
            enemyList.add(e1);
            enemyList.add(e2);
        } else if (config == 14) {
            // four small enemies
            Enemy[] enemies = new Enemy[4];
            for (int i = 0; i < 4; i++) {
                enemies[i] = pools.enemySmallPool.obtain();
            }
            rowHeight = enemies[0].getHeight();
            float enemyOffset = (enemies[0].getWidth() / 4) * 2;
            enemies[0].init((gameWidth / 8) * 1 - enemyOffset, this.rowY, Behavior.WIGGLE);
            enemies[1].init((gameWidth / 8) * 3 - enemyOffset, this.rowY, Behavior.WIGGLE);
            enemies[2].init((gameWidth / 8) * 5 - enemyOffset, this.rowY, Behavior.WIGGLE);
            enemies[3].init((gameWidth / 8) * 7 - enemyOffset, this.rowY, Behavior.WIGGLE);
            for (int i = 0; i < 4; i++) {
                enemyList.add(enemies[i]);
            }
        } else if (config == 15) {
            //two wiggling fighter enemies
            Enemy e1 = pools.enemyFighterPool.obtain();
            Enemy e2 = pools.enemyFighterPool.obtain();
            rowHeight = e1.getHeight();
            e1.init(gameWidth / 4 - e1.getWidth() / 2, this.rowY, Behavior.WIGGLE);
            e2.init((gameWidth / 4) * 3 - e2.getWidth() / 2, this.rowY, Behavior.WIGGLE);
            enemyList.add(e1);
            enemyList.add(e2);
        } else if (config == 16) {
            //single centered wiggling mg
            Enemy e = pools.enemyMgPool.obtain();
            e.init(gameWidth / 2 - e.getWidth() / 2, this.rowY, Behavior.WIGGLE);
            rowHeight = e.getHeight();
            enemyList.add(e);
        } else if (config == 17) {
            // 3 small enemies
            Enemy[] enemies = new Enemy[3];
            for (int i = 0; i < 3; i++) {
                enemies[i] = pools.enemySmallPool.obtain();
            }
            rowHeight = enemies[0].getHeight();
            enemies[0].init((gameWidth / 4) - enemies[0].getWidth() / 2, this.rowY, Behavior.WIGGLE);
            enemies[1].init((gameWidth / 4) * 2 - enemies[1].getWidth() / 2, this.rowY, Behavior.WIGGLE);
            enemies[2].init((gameWidth / 4) * 3 - enemies[2].getWidth() / 2, this.rowY, Behavior.WIGGLE);
            for (int i = 0; i < 3; i++) {
                enemyList.add(enemies[i]);
            }
        }
    }

    public void setY(float y) {
        this.rowY = y;
    }

    public List<Enemy> getEnemyConfiguration() {
        return enemyList;
    }

    public float getRowY() {
        if (!this.isEmpty()) {
            Enemy e = enemyList.getFirst();
            return e.getY();
        }
        return -100;
    }

    public void update() {
        for (Iterator ite = enemyList.iterator(); ite.hasNext();) {
            Enemy e = (Enemy) ite.next();
            if (!e.isAlive()) {
                ite.remove();
            }
        }
    }


    public float getRowHeight() {
        return rowHeight;
    }

    public boolean isEmpty() {
        return this.enemyList.isEmpty();

    }

    public void advanceRow() {
        for (Iterator ite = enemyList.iterator(); ite.hasNext();) {
            Enemy e = (Enemy) ite.next();
            e.setAdvancing(true);
        }
    }

    public void stopRow() {
        for (Iterator ite = enemyList.iterator(); ite.hasNext();) {
            Enemy e = (Enemy) ite.next();
            e.setAdvancing(false);
        }
    }

    public List getList() {
        return enemyList;
    }


}



