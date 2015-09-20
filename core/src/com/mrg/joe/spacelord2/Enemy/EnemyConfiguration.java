package com.mrg.joe.spacelord2.Enemy;

import com.badlogic.gdx.Gdx;
import com.mrg.joe.spacelord2.Enemy.Enemy;
import com.mrg.joe.spacelord2.GameConstants;
import com.mrg.joe.spacelord2.Player;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Joe on 8/26/2015.
 */
public class EnemyConfiguration {

    private float row_y;
    private LinkedList<Enemy> enemyList;
    private float rowHeight;




    public EnemyConfiguration(int config){
        float spawn_height = Gdx.graphics.getHeight() + 200;
        float screen_width = Gdx.graphics.getWidth();
        enemyList = new LinkedList<Enemy>();
         this.setY(spawn_height);

        if (config == 0){

        }else if (config == 1){

            // four fighters
            Enemy e = new EnemyFighter(-200,-200, Behavior.WIGGLE);
            rowHeight = e.getHeight();
            float enemyOffset = (e.getWidth()/4) * 3;
            enemyList.add(new EnemyFighter((screen_width/8) * 1 - enemyOffset,this.row_y, Behavior.WIGGLE));
            enemyList.add(new EnemyFighter((screen_width/8) * 3 - enemyOffset,this.row_y, Behavior.WIGGLE));
            enemyList.add(new EnemyFighter((screen_width/8) * 5 - enemyOffset, this.row_y, Behavior.WIGGLE));
            enemyList.add(new EnemyFighter((screen_width/8) * 7 - enemyOffset,this.row_y, Behavior.WIGGLE));

            e = null;


        }else if(config == 2){

            // a row of 7 small enemies

            Enemy e = new EnemySmall(-200,-200, Behavior.STATIONARY);
            rowHeight = e.getHeight();
            float enemyOffset = (e.getWidth()/4) * 3;

            for(int i = 1; i < 8; i++) {
                Enemy enemy = new EnemySmall((screen_width / 8) * i - enemyOffset, this.row_y, Behavior.STATIONARY);
                enemyList.add(enemy);
            }

            e = null;


        } else if (config == 3){

            //single machine gun enemy

            Enemy e = new EnemyMg(-200,-200, Behavior.PATROL);
            rowHeight = e.getHeight();
            float enemyX = (0 + (int)(Math.random() * ((Gdx.graphics.getWidth() - e.getWidth()) + 1)));
            enemyList.add(new EnemyMg(enemyX,this.row_y,Behavior.PATROL));

            e = null;

        }else if (config == 4){

            // boss


            Enemy e = new EnemyBoss(-1000,-1000, Behavior.TRACK_PLAYER);
            rowHeight = e.getHeight();

            enemyList.add(new EnemyBoss((Gdx.graphics.getWidth()/2) - (e.getWidth()/2),this.row_y,Behavior.TRACK_PLAYER));

            e = null;
        }else if (config == 5){

            // single blaster enemy

            Enemy e = new EnemyBlaster(-1000,-1000, Behavior.PATROL);
            rowHeight = e.getHeight();

            enemyList.add(new EnemyBlaster((Gdx.graphics.getWidth()/2) - (e.getWidth()/2),this.row_y, Behavior.TRACK_PLAYER));

            e = null;
        } else if (config == 6){

            // single boss 2

            Enemy e = new EnemyBoss2(-1000,-1000, Behavior.PATROL);
            rowHeight = e.getHeight();

            enemyList.add(new EnemyBoss2((Gdx.graphics.getWidth()/2) - (e.getWidth()/2),this.row_y, Behavior.PATROL));

            e = null;
        } else if (config == 7){

            // single hunter

            Enemy e = new EnemyHunter(-1000,-1000, Behavior.PATROL);
            rowHeight = e.getHeight();

            enemyList.add(new EnemyHunter((Gdx.graphics.getWidth()/2) - (e.getWidth()/2),this.row_y, Behavior.HUNT));

            e = null;
        }else if (config == 8){

            // single boss 3

            Enemy e = new EnemyBoss3(-1000,-1000, Behavior.PATROL);
            rowHeight = e.getHeight();

            enemyList.add(new EnemyBoss3((Gdx.graphics.getWidth()/2) - (e.getWidth()/2),this.row_y, Behavior.PATROL));

            e = null;
        }else if (config == 9){

            // single boss 4

            Enemy e = new EnemyBoss4(-1000,-1000, Behavior.PATROL);
            rowHeight = e.getHeight();

            enemyList.add(new EnemyBoss4((Gdx.graphics.getWidth()/2) - (e.getWidth()/2),this.row_y, Behavior.PATROL));

            e = null;
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
}
