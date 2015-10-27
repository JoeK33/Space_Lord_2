package com.mrg.joe.spacelord2.Enemy;


import com.badlogic.gdx.utils.Pool;
import com.mrg.joe.spacelord2.GameConstants;


import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Joe on 8/26/2015.
 * These are the "rows" of enemies that stack onto the play area.  This is where they are initialized and configured.
 *
 * Config legend:
 *
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
public class EnemyConfiguration{

    private float row_y;
    private LinkedList<Enemy> enemyList;
    private float rowHeight;
    private int config;




    public EnemyConfiguration(int config, EnemyPools pools){
        float spawn_height = GameConstants.GAME_HEIGHT + 200;
        float screen_width = GameConstants.GAME_WIDTH;
        this.config = config;
        enemyList = new LinkedList<Enemy>();
         this.setY(spawn_height);

        if (config == 0){

        }else if (config == 1){
            // four fighters
            Enemy[] enemies = new Enemy[4];
            for(int i = 0; i < 4; i++){
                enemies[i] = pools.EnemyFighterPool.obtain();
            }
            rowHeight = enemies[0].getHeight();
            float enemyOffset = (enemies[0].getWidth()/4) * 2;
            enemies[0].init((screen_width / 8) * 1 - enemyOffset, this.row_y, Behavior.WIGGLE);
            enemies[1].init((screen_width / 8) * 3 - enemyOffset, this.row_y, Behavior.WIGGLE);
            enemies[2].init((screen_width / 8) * 5 - enemyOffset, this.row_y, Behavior.WIGGLE);
            enemies[3].init((screen_width / 8) * 7 - enemyOffset, this.row_y, Behavior.WIGGLE);
            for(int i = 0; i < 4; i++){
                enemyList.add(enemies[i]);
            }

        }else if(config == 2){
            // a row of 7 small enemies
            Enemy[] enemies = new Enemy[7];
            for(int i = 0; i < 7;i++){
                enemies[i] = pools.EnemySmallPool.obtain();
            }
            rowHeight = enemies[0].getHeight();
            float enemyOffset = (enemies[0].getWidth()/4) * 2;
            for(int i = 1; i < 8; i++) {
                enemies[i - 1].init((screen_width / 8) * i - enemyOffset, this.row_y, Behavior.WIGGLE);
                enemyList.add(enemies[i - 1]);
            }
        } else if (config == 3){

            //single patrolling machine gun enemy
            Enemy e = pools.EnemyMgPool.obtain();
            rowHeight = e.getHeight();
            float enemyX = (0 + (int)(Math.random() * ((screen_width - e.getWidth()) + 1)));
            e.init(enemyX, this.row_y, Behavior.PATROL);
            enemyList.add(e);


        }else if (config == 4){

            // boss
            Enemy e = pools.EnemyBossPool.obtain();
            rowHeight = e.getHeight();
            e.init((screen_width/2) - (e.getWidth()/2),this.row_y,Behavior.TRACK_PLAYER);
            enemyList.add(e);

        }else if (config == 5){

            // single blaster enemy
            Enemy e = pools.EnemyBlasterPool.obtain();
            rowHeight = e.getHeight();
            e.init((screen_width/2) - (e.getWidth()/2),this.row_y, Behavior.TRACK_PLAYER);

            enemyList.add(e);


        } else if (config == 6){

            // single boss 2 and hunter
            Enemy e1 = pools.EnemyBoss2Pool.obtain();
            Enemy e2 = pools.EnemyHunterPool.obtain();
            rowHeight = e1.getHeight() + 200;
            e1.init((screen_width/2) - (e1.getWidth()/2),this.row_y, Behavior.TRACK_PLAYER);
            e2.init((screen_width/2) - (e2.getWidth()/2),this.row_y + 200, Behavior.HUNT);



            enemyList.add(e1);
            enemyList.add(e2);

        } else if (config == 7){

            // single hunter

            Enemy e = pools.EnemyHunterPool.obtain();
            rowHeight = e.getHeight();
            e.init((screen_width / 2) - (e.getWidth() / 2), this.row_y, Behavior.HUNT);
            enemyList.add(e);

        }else if (config == 8){

            // single boss 3

            Enemy e = pools.EnemyBoss3Pool.obtain();
            rowHeight = e.getHeight();
            e.init((screen_width/2) - (e.getWidth()/2),this.row_y, Behavior.PATROL);
            enemyList.add(e);


        }else if (config == 9){

            // single boss 4

            Enemy e = pools.EnemyBoss4Pool.obtain();
            rowHeight = e.getHeight();
            e.init((screen_width / 2) - (e.getWidth() / 2), this.row_y, Behavior.PATROL);
            enemyList.add(e);

        }else if (config == 10){

            //two wiggling machine gun enemies

            Enemy e1 = pools.EnemyMgPool.obtain();
            Enemy e2 = pools.EnemyMgPool.obtain();
            rowHeight = e1.getHeight();

            e1.init(screen_width/4 - e1.getWidth()/2,this.row_y,Behavior.WIGGLE);
            e2.init((screen_width/4) * 3 - e2.getWidth()/2,this.row_y,Behavior.WIGGLE);
            enemyList.add(e1);
            enemyList.add(e2);

        }else if (config == 11){

            //two wiggling blaster enemies

            Enemy e1 = pools.EnemyBlasterPool.obtain();
            Enemy e2 = pools.EnemyBlasterPool.obtain();
            rowHeight = e1.getHeight();

            e1.init(screen_width/4 - e1.getWidth()/2,this.row_y,Behavior.WIGGLE);
            e2.init((screen_width / 4) * 3 - e2.getWidth() / 2, this.row_y, Behavior.WIGGLE);
            enemyList.add(e1);
            enemyList.add(e2);

        }else if (config == 12){

            //three wiggling fighter enemies

            Enemy[] enemies = new Enemy[3];
            for(int i = 0; i < 3; i++){
                enemies[i] = pools.EnemyFighterPool.obtain();
            }
            rowHeight = enemies[0].getHeight();

            enemies[0].init((screen_width/4) - enemies[0].getWidth()/2,this.row_y,Behavior.WIGGLE);
            enemies[1].init((screen_width/4) * 2 - enemies[1].getWidth()/2,this.row_y,Behavior.WIGGLE);
            enemies[2].init((screen_width/4) * 3 - enemies[2].getWidth()/2,this.row_y,Behavior.WIGGLE);

            for(int i = 0; i < 3; i++){
                enemyList.add(enemies[i]);
            }

        }else if (config == 13){

            //two small enemies

            Enemy e1 = pools.EnemySmallPool.obtain();
            Enemy e2 = pools.EnemySmallPool.obtain();
            rowHeight = e1.getHeight();

            e1.init(screen_width/4 - e1.getWidth()/2,this.row_y,Behavior.WIGGLE);
            e2.init((screen_width / 4) * 3 - e2.getWidth() / 2, this.row_y, Behavior.WIGGLE);
            enemyList.add(e1);
            enemyList.add(e2);

        }else if (config == 14){

            // four small enemies

            Enemy[] enemies = new Enemy[4];
            for(int i = 0; i < 4; i++){
                enemies[i] = pools.EnemySmallPool.obtain();
            }
            rowHeight = enemies[0].getHeight();
            float enemyOffset = (enemies[0].getWidth()/4) * 2;
            enemies[0].init((screen_width / 8) * 1 - enemyOffset, this.row_y, Behavior.WIGGLE);
            enemies[1].init((screen_width / 8) * 3 - enemyOffset, this.row_y, Behavior.WIGGLE);
            enemies[2].init((screen_width / 8) * 5 - enemyOffset, this.row_y, Behavior.WIGGLE);
            enemies[3].init((screen_width / 8) * 7 - enemyOffset, this.row_y, Behavior.WIGGLE);
            for(int i = 0; i < 4; i++){
                enemyList.add(enemies[i]);
            }



        }else if (config == 15){

            //two wiggling fighter enemies

            Enemy e1 = pools.EnemyFighterPool.obtain();
            Enemy e2 = pools.EnemyFighterPool.obtain();
            rowHeight = e1.getHeight();

            e1.init(screen_width/4 - e1.getWidth()/2,this.row_y,Behavior.WIGGLE);
            e2.init((screen_width / 4) * 3 - e2.getWidth() / 2, this.row_y, Behavior.WIGGLE);
            enemyList.add(e1);
            enemyList.add(e2);

        }else if (config == 16){

            //single centered wiggling mg

            Enemy e = pools.EnemyMgPool.obtain();
            e.init(screen_width/2 - e.getWidth()/2,this.row_y,Behavior.WIGGLE);
            rowHeight = e.getHeight();

            enemyList.add(e);


        }else if (config == 17){



            // 3 small enemies

            //three wiggling fighter enemies

            Enemy[] enemies = new Enemy[3];
            for(int i = 0; i < 3; i++){
                enemies[i] = pools.EnemySmallPool.obtain();
            }
            rowHeight = enemies[0].getHeight();

            enemies[0].init((screen_width / 4) - enemies[0].getWidth() / 2, this.row_y, Behavior.WIGGLE);
            enemies[1].init((screen_width / 4) * 2 - enemies[1].getWidth() / 2, this.row_y, Behavior.WIGGLE);
            enemies[2].init((screen_width/4) * 3 - enemies[2].getWidth()/2,this.row_y,Behavior.WIGGLE);

            for(int i = 0; i < 3; i++){
                enemyList.add(enemies[i]);
            }
        }


    }






    public void setY(float y){
    this.row_y = y;
}




    public List<Enemy> getEnemyConfiguration(){
        return enemyList;
    }

    public float getRowY(){


        if(!this.isEmpty()) {
            Enemy e = enemyList.getFirst();
            return e.getY();
        }
        return -100;


    }

    public void update(){

        for (Iterator ite = enemyList.iterator(); ite.hasNext(); ) {
            Enemy e = (Enemy) ite.next();

            if(!e.isAlive()){
                ite.remove();
            }

        }

    }

    public int getConfigType(){
        return this.config;
    }

    public float getRowHeight(){
        return rowHeight;
    }

    public boolean isEmpty(){
        return this.enemyList.isEmpty();

    }


    public void advanceRow() {

        for (Iterator ite = enemyList.iterator(); ite.hasNext(); ) {
            Enemy e = (Enemy) ite.next();

            e.setAdvancing(true);

        }
    }

        public void stopRow(){

            for (Iterator ite = enemyList.iterator(); ite.hasNext(); ) {
                Enemy e = (Enemy) ite.next();

                e.setAdvancing(false);

            }
    }

    public List getList(){
        return enemyList;
    }



    }



