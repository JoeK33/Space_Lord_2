package com.mrg.joe.spacelord2.Enemy;

import com.mrg.joe.spacelord2.GameConstants;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by Joe on 8/26/2015.
 *
 * This class controls how enemies are deployed in the game. The idea here is to make each play similar but unique,
 * so similar enemies will be randomly chosen depending the players progress measured in the number of rows they have completed.
 */
public class EnemyManager {

    private List<Enemy> enemyList;
    private int rowsDeployed;
    private float front_row_y;
    private LinkedList<EnemyConfiguration> configurations;
    private int total_rows_deployed;
    private boolean bossOut;
    private float interval;



    public EnemyManager(){
        rowsDeployed = 0;


        float spawn_height = GameConstants.GAME_HEIGHT + 200;
        front_row_y =GameConstants.GAME_HEIGHT/2;
        configurations = new LinkedList<EnemyConfiguration>();

        enemyList = new LinkedList<Enemy>();

        interval = System.nanoTime();

    }



    public List<Enemy> getEnemyList(){

        return enemyList;
    }


    public void update(){



        if(rowsDeployed <= 0){
            bossOut = false;
        }







        if(!configurations.isEmpty()) {

            // controls the movement of enemies

            for (ListIterator i = configurations.listIterator(); i.hasNext(); ) {

                EnemyConfiguration prev_config = null;


                if(i.hasPrevious()){
                    prev_config = (EnemyConfiguration)i.previous();
                    i.next();
                }

                EnemyConfiguration config = (EnemyConfiguration) i.next();


                if (configurations.getFirst().equals(config) && config.getRowY() > front_row_y) {

                    config.advanceRow();

                } else if (i.hasPrevious() && prev_config != null && (config.getRowY() > prev_config.getRowY() + prev_config.getRowHeight() + GameConstants.enemy_row_gap) ) {

                    config.advanceRow();

                }else config.stopRow();



                }

            // controls the removal of dead rows and enemies
            for (ListIterator i = configurations.listIterator(); i.hasNext(); ) {

                EnemyConfiguration config = (EnemyConfiguration)i.next();
                config.update();

                if (config.isEmpty()) {
                    i.remove();
                    rowsDeployed--;
                }

            }
            }

if(!bossOut) {


    if (rowsDeployed < 5 && System.nanoTime() > interval + ( 2000000000L )) {
        interval = System.nanoTime();
        // deploy a new row


        if (total_rows_deployed < 10) {
            // first rows are easy enemies


            int pick = (int) (Math.random() * 7);


            if (pick == 0) {

                EnemyConfiguration config = new EnemyConfiguration(1);
                configurations.add(config);
                rowsDeployed++;
                total_rows_deployed++;

                enemyList.addAll(config.getEnemyConfiguration());

            } else if (pick == 1) {

                EnemyConfiguration config = new EnemyConfiguration(2);
                configurations.add(config);
                rowsDeployed++;
                total_rows_deployed++;

                enemyList.addAll(config.getEnemyConfiguration());

            } else if (pick == 2) {

                EnemyConfiguration config = new EnemyConfiguration(3);
                configurations.add(config);
                rowsDeployed++;
                total_rows_deployed++;

                enemyList.addAll(config.getEnemyConfiguration());

            } else if (pick == 3) {

                EnemyConfiguration config = new EnemyConfiguration(12);
                configurations.add(config);
                rowsDeployed++;
                total_rows_deployed++;

                enemyList.addAll(config.getEnemyConfiguration());

            } else if (pick == 4) {

                EnemyConfiguration config = new EnemyConfiguration(13);
                configurations.add(config);
                rowsDeployed++;
                total_rows_deployed++;

                enemyList.addAll(config.getEnemyConfiguration());

            } else if (pick == 5) {

                EnemyConfiguration config = new EnemyConfiguration(14);
                configurations.add(config);
                rowsDeployed++;
                total_rows_deployed++;

                enemyList.addAll(config.getEnemyConfiguration());

            } else if (pick == 6) {

                EnemyConfiguration config = new EnemyConfiguration(15);
                configurations.add(config);
                rowsDeployed++;
                total_rows_deployed++;

                enemyList.addAll(config.getEnemyConfiguration());

            }else if (pick == 7) {

                EnemyConfiguration config = new EnemyConfiguration(17);
                configurations.add(config);
                rowsDeployed++;
                total_rows_deployed++;

                enemyList.addAll(config.getEnemyConfiguration());

            }


        } else if (total_rows_deployed < 20 && total_rows_deployed >= 10) {

            // a few more challenging enemies go here diluted with easy ones

            int pick = (int) (Math.random() * 8);

            if (pick == 0) {

                EnemyConfiguration config = new EnemyConfiguration(5);
                configurations.add(config);
                rowsDeployed++;
                total_rows_deployed++;

                enemyList.addAll(config.getEnemyConfiguration());

            } else if (pick == 1) {

                EnemyConfiguration config = new EnemyConfiguration(12);
                configurations.add(config);
                rowsDeployed++;
                total_rows_deployed++;

                enemyList.addAll(config.getEnemyConfiguration());

            } else if (pick == 2) {

                EnemyConfiguration config = new EnemyConfiguration(3);
                configurations.add(config);
                rowsDeployed++;
                total_rows_deployed++;

                enemyList.addAll(config.getEnemyConfiguration());

            } else if (pick == 3) {

                EnemyConfiguration config = new EnemyConfiguration(14);
                configurations.add(config);
                rowsDeployed++;
                total_rows_deployed++;

                enemyList.addAll(config.getEnemyConfiguration());

            } else if (pick == 4) {

                EnemyConfiguration config = new EnemyConfiguration(1);
                configurations.add(config);
                rowsDeployed++;
                total_rows_deployed++;

                enemyList.addAll(config.getEnemyConfiguration());

            } else if (pick == 5) {

                EnemyConfiguration config = new EnemyConfiguration(15);
                configurations.add(config);
                rowsDeployed++;
                total_rows_deployed++;

                enemyList.addAll(config.getEnemyConfiguration());

            }else if (pick == 6) {

                EnemyConfiguration config = new EnemyConfiguration(2);
                configurations.add(config);
                rowsDeployed++;
                total_rows_deployed++;

                enemyList.addAll(config.getEnemyConfiguration());

            }else if (pick == 7) {

                EnemyConfiguration config = new EnemyConfiguration(17);
                configurations.add(config);
                rowsDeployed++;
                total_rows_deployed++;

                enemyList.addAll(config.getEnemyConfiguration());

            }
        } else if (total_rows_deployed % 20 == 0) {
            // every 20 rows is a random boss
            int pick = (int) (Math.random() * 4);
            bossOut = true;

            if (pick == 0) {

                EnemyConfiguration config = new EnemyConfiguration(4);
                configurations.add(config);
                rowsDeployed++;
                total_rows_deployed++;

                enemyList.addAll(config.getEnemyConfiguration());

            } else if (pick == 1) {

                // this boss always comes with a hunter

                EnemyConfiguration config = new EnemyConfiguration(6);
                configurations.add(config);
                rowsDeployed++;
                total_rows_deployed++;

                enemyList.addAll(config.getEnemyConfiguration());

                EnemyConfiguration config2 = new EnemyConfiguration(7);
                configurations.add(config2);
                rowsDeployed++;
                total_rows_deployed++;

                enemyList.addAll(config2.getEnemyConfiguration());

            } else if (pick == 2) {

                EnemyConfiguration config = new EnemyConfiguration(8);
                configurations.add(config);
                rowsDeployed++;
                total_rows_deployed++;

                enemyList.addAll(config.getEnemyConfiguration());

            } else if (pick == 3) {

                EnemyConfiguration config = new EnemyConfiguration(9);
                configurations.add(config);
                rowsDeployed++;
                total_rows_deployed++;

                enemyList.addAll(config.getEnemyConfiguration());

            }
        } else if (total_rows_deployed < 40 && total_rows_deployed >= 20) {

            // after the first boss, make it a little harder by removing some of the easier rows and add the hunter enemy

            int pick = (int) (Math.random() * 7);

            if (pick == 0) {

                EnemyConfiguration config = new EnemyConfiguration(5);
                configurations.add(config);
                rowsDeployed++;
                total_rows_deployed++;

                enemyList.addAll(config.getEnemyConfiguration());

            } else if (pick == 1) {

                EnemyConfiguration config = new EnemyConfiguration(12);
                configurations.add(config);
                rowsDeployed++;
                total_rows_deployed++;

                enemyList.addAll(config.getEnemyConfiguration());

            } else if (pick == 2) {

                EnemyConfiguration config = new EnemyConfiguration(3);
                configurations.add(config);
                rowsDeployed++;
                total_rows_deployed++;

                enemyList.addAll(config.getEnemyConfiguration());

            } else if (pick == 3) {

                EnemyConfiguration config = new EnemyConfiguration(11);
                configurations.add(config);
                rowsDeployed++;
                total_rows_deployed++;

                enemyList.addAll(config.getEnemyConfiguration());

            } else if (pick == 4) {

                EnemyConfiguration config = new EnemyConfiguration(1);
                configurations.add(config);
                rowsDeployed++;
                total_rows_deployed++;

                enemyList.addAll(config.getEnemyConfiguration());

            } else if (pick == 5) {

                EnemyConfiguration config = new EnemyConfiguration(7);
                configurations.add(config);
                rowsDeployed++;
                total_rows_deployed++;

                enemyList.addAll(config.getEnemyConfiguration());

            }else if (pick == 6) {

                EnemyConfiguration config = new EnemyConfiguration(16);
                configurations.add(config);
                rowsDeployed++;
                total_rows_deployed++;

                enemyList.addAll(config.getEnemyConfiguration());

            }
        } else if (total_rows_deployed < 60 && total_rows_deployed >= 40) {

            // after two bosses mostly hard enemies

            int pick = (int) (Math.random() * 6);

            if (pick == 0) {

                EnemyConfiguration config = new EnemyConfiguration(5);
                configurations.add(config);
                rowsDeployed++;
                total_rows_deployed++;

                enemyList.addAll(config.getEnemyConfiguration());

            } else if (pick == 1) {

                EnemyConfiguration config = new EnemyConfiguration(11);
                configurations.add(config);
                rowsDeployed++;
                total_rows_deployed++;

                enemyList.addAll(config.getEnemyConfiguration());

            } else if (pick == 2) {

                EnemyConfiguration config = new EnemyConfiguration(3);
                configurations.add(config);
                rowsDeployed++;
                total_rows_deployed++;

                enemyList.addAll(config.getEnemyConfiguration());

            } else if (pick == 3) {

                EnemyConfiguration config = new EnemyConfiguration(10);
                configurations.add(config);
                rowsDeployed++;
                total_rows_deployed++;

                enemyList.addAll(config.getEnemyConfiguration());

            } else if (pick == 4) {

                EnemyConfiguration config = new EnemyConfiguration(1);
                configurations.add(config);
                rowsDeployed++;
                total_rows_deployed++;

                enemyList.addAll(config.getEnemyConfiguration());

            } else if (pick == 5) {

                EnemyConfiguration config = new EnemyConfiguration(7);
                configurations.add(config);
                rowsDeployed++;
                total_rows_deployed++;

                enemyList.addAll(config.getEnemyConfiguration());

            }
        } else if (total_rows_deployed >= 60) {
            // hard enemies mixed after 60 rows for the rest of the game
            int pick = (int) (Math.random() * 8);

            if (pick == 0) {
                EnemyConfiguration config = new EnemyConfiguration(10);
                configurations.add(config);
                rowsDeployed++;
                total_rows_deployed++;

                enemyList.addAll(config.getEnemyConfiguration());

            } else if (pick == 1) {
                EnemyConfiguration config = new EnemyConfiguration(11);
                configurations.add(config);
                rowsDeployed++;
                total_rows_deployed++;

                enemyList.addAll(config.getEnemyConfiguration());

            } else if (pick == 2) {
                EnemyConfiguration config = new EnemyConfiguration(3);
                configurations.add(config);
                rowsDeployed++;
                total_rows_deployed++;

                enemyList.addAll(config.getEnemyConfiguration());

            } else if (pick == 3) {
                EnemyConfiguration config = new EnemyConfiguration(1);
                configurations.add(config);
                rowsDeployed++;
                total_rows_deployed++;

                enemyList.addAll(config.getEnemyConfiguration());


            } else if (pick == 4) {


                EnemyConfiguration config = new EnemyConfiguration(7);
                configurations.add(config);
                rowsDeployed++;
                total_rows_deployed++;

                enemyList.addAll(config.getEnemyConfiguration());
            } else if (pick == 5) {
                EnemyConfiguration config = new EnemyConfiguration(5);
                configurations.add(config);
                rowsDeployed++;
                total_rows_deployed++;

                enemyList.addAll(config.getEnemyConfiguration());


            } else if (pick == 6) {

                EnemyConfiguration config = new EnemyConfiguration(12);
                configurations.add(config);
                rowsDeployed++;
                total_rows_deployed++;

                enemyList.addAll(config.getEnemyConfiguration());

            }else if (pick == 7) {

                EnemyConfiguration config = new EnemyConfiguration(14);
                configurations.add(config);
                rowsDeployed++;
                total_rows_deployed++;

                enemyList.addAll(config.getEnemyConfiguration());

            }
        }


    }
}


            }

    public void reset(){
        this.total_rows_deployed = 0;
        this.rowsDeployed = 0;

        for (Iterator it = enemyList.iterator(); it.hasNext();) {
            Enemy e = (Enemy) it.next();
            e.dispose();
            it.remove();
        }

    }
        }








