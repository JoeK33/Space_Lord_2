package com.mrg.joe.spacelord2.Enemy;


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
public class EnemyConfiguration {

    private float row_y;
    private LinkedList<Enemy> enemyList;
    private float rowHeight;




    public EnemyConfiguration(int config){
        float spawn_height = GameConstants.GAME_HEIGHT + 200;
        float screen_width = GameConstants.GAME_WIDTH;
        enemyList = new LinkedList<Enemy>();
         this.setY(spawn_height);

        if (config == 0){

        }else if (config == 1){

            // four fighters
            Enemy e = new EnemyFighter(-200,-200, Behavior.WIGGLE);
            rowHeight = e.getHeight();
            float enemyOffset = (e.getWidth()/4) * 2;
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
                Enemy enemy = new EnemySmall((screen_width / 8) * i - enemyOffset, this.row_y, Behavior.WIGGLE);
                enemyList.add(enemy);
            }

            e = null;


        } else if (config == 3){

            //single patrolling machine gun enemy

            Enemy e = new EnemyMg(-200,-200, Behavior.PATROL);
            rowHeight = e.getHeight();
            float enemyX = (0 + (int)(Math.random() * ((screen_width - e.getWidth()) + 1)));
            enemyList.add(new EnemyMg(enemyX,this.row_y,Behavior.PATROL));

            e = null;

        }else if (config == 4){

            // boss


            Enemy e = new EnemyBoss(-1000,-1000, Behavior.TRACK_PLAYER);
            rowHeight = e.getHeight();

            enemyList.add(new EnemyBoss((screen_width/2) - (e.getWidth()/2),this.row_y,Behavior.TRACK_PLAYER));

            e = null;
        }else if (config == 5){

            // single blaster enemy

            Enemy e = new EnemyBlaster(-1000,-1000, Behavior.PATROL);
            rowHeight = e.getHeight();

            enemyList.add(new EnemyBlaster((screen_width/2) - (e.getWidth()/2),this.row_y, Behavior.TRACK_PLAYER));

            e = null;
        } else if (config == 6){

            // single boss 2

            Enemy e = new EnemyBoss2(-1000,-1000, Behavior.PATROL);
            rowHeight = e.getHeight();

            enemyList.add(new EnemyBoss2((screen_width/2) - (e.getWidth()/2),this.row_y, Behavior.TRACK_PLAYER));

            e = null;
        } else if (config == 7){

            // single hunter

            Enemy e = new EnemyHunter(-1000,-1000, Behavior.PATROL);
            rowHeight = e.getHeight();

            enemyList.add(new EnemyHunter((screen_width/2) - (e.getWidth()/2),this.row_y, Behavior.HUNT));

            e = null;
        }else if (config == 8){

            // single boss 3

            Enemy e = new EnemyBoss3(-1000,-1000, Behavior.PATROL);
            rowHeight = e.getHeight();

            enemyList.add(new EnemyBoss3((screen_width/2) - (e.getWidth()/2),this.row_y, Behavior.PATROL));

            e = null;
        }else if (config == 9){

            // single boss 4

            Enemy e = new EnemyBoss4(-1000,-1000, Behavior.PATROL);
            rowHeight = e.getHeight();

            enemyList.add(new EnemyBoss4((screen_width/2) - (e.getWidth()/2),this.row_y, Behavior.PATROL));

            e = null;
        }else if (config == 10){

            //two wiggling machine gun enemies

            Enemy e = new EnemyMg(-200,-200, Behavior.PATROL);
            rowHeight = e.getHeight();

            enemyList.add(new EnemyMg(screen_width/4 - e.getWidth()/2,this.row_y,Behavior.WIGGLE));
            enemyList.add(new EnemyMg((screen_width/4) * 3 - e.getWidth()/2,this.row_y,Behavior.WIGGLE));

            e = null;

        }else if (config == 11){

            //two wiggling blaster enemies

            Enemy e = new EnemyBlaster(-200,-200, Behavior.PATROL);
            rowHeight = e.getHeight();

            enemyList.add(new EnemyBlaster(screen_width/4 - e.getWidth()/2,this.row_y,Behavior.WIGGLE));
            enemyList.add(new EnemyBlaster((screen_width/4) * 3 - e.getWidth()/2,this.row_y,Behavior.WIGGLE));

            e = null;

        }else if (config == 12){

            //three wiggling fighter enemies

            Enemy e = new EnemyFighter(-200,-200, Behavior.PATROL);
            rowHeight = e.getHeight();

            enemyList.add(new EnemyFighter(screen_width/4 - e.getWidth()/2,this.row_y,Behavior.WIGGLE));
            enemyList.add(new EnemyFighter((screen_width/4) * 2 - e.getWidth()/2,this.row_y,Behavior.WIGGLE));
            enemyList.add(new EnemyFighter((screen_width/4) * 3 - e.getWidth()/2,this.row_y,Behavior.WIGGLE));

            e = null;

        }else if (config == 13){

            //two small enemies

            Enemy e = new EnemySmall(-200,-200, Behavior.PATROL);
            rowHeight = e.getHeight();

            enemyList.add(new EnemySmall(screen_width/4 - e.getWidth()/2,this.row_y,Behavior.WIGGLE));
            enemyList.add(new EnemySmall((screen_width/4) * 3 - e.getWidth()/2,this.row_y,Behavior.WIGGLE));

            e = null;

        }else if (config == 14){

            // four small enemies

            Enemy e = new EnemySmall(-200,-200, Behavior.WIGGLE);
            rowHeight = e.getHeight();
            float enemyOffset = (e.getWidth()/4) * 2;
            enemyList.add(new EnemySmall((screen_width/8) * 1 - enemyOffset,this.row_y, Behavior.WIGGLE));
            enemyList.add(new EnemySmall((screen_width/8) * 3 - enemyOffset,this.row_y, Behavior.WIGGLE));
            enemyList.add(new EnemySmall((screen_width/8) * 5 - enemyOffset,this.row_y, Behavior.WIGGLE));
            enemyList.add(new EnemySmall((screen_width/8) * 7 - enemyOffset,this.row_y, Behavior.WIGGLE));

            e = null;

        }else if (config == 15){

            //two wiggling fighter enemies

            Enemy e = new EnemyFighter(-200,-200, Behavior.PATROL);
            rowHeight = e.getHeight();

            enemyList.add(new EnemyFighter(screen_width/4 - e.getWidth()/2,this.row_y,Behavior.WIGGLE));
            enemyList.add(new EnemyFighter((screen_width/4) * 3 - e.getWidth()/2,this.row_y,Behavior.WIGGLE));

            e = null;

        }else if (config == 16){

            //single centered wiggling mg

            Enemy e = new EnemyMg(-200,-200, Behavior.PATROL);
            rowHeight = e.getHeight();

            enemyList.add(new EnemyMg(screen_width/2 - e.getWidth()/2,this.row_y,Behavior.WIGGLE));


            e = null;

        }else if (config == 17){



            // 3 small enemies

            Enemy e = new EnemySmall(-200,-200, Behavior.WIGGLE);
            rowHeight = e.getHeight();
            float enemyOffset = (e.getWidth()/4) * 2;
            enemyList.add(new EnemySmall((screen_width/4) * 1 - enemyOffset,this.row_y, Behavior.WIGGLE));
            enemyList.add(new EnemySmall((screen_width/4) * 2 - enemyOffset,this.row_y, Behavior.WIGGLE));
            enemyList.add(new EnemySmall((screen_width/4) * 3 - enemyOffset,this.row_y, Behavior.WIGGLE));

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
