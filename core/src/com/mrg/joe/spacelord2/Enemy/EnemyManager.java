package com.mrg.joe.spacelord2.Enemy;

import com.mrg.joe.spacelord2.GameConstants;

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
    private List<Enemy> enemyPlayList;
    private int rowsDeployed;
    private float front_row_y;
    private LinkedList<EnemyConfiguration> configurations;
    private LinkedList<EnemyConfiguration> loadedConfigurations;
    private int total_rows_deployed;
    private boolean bossOut;
    private float interval;
    private EnemyConfigPicker enemyListLoader;
    private EnemyPools pools;




    public EnemyManager(EnemyPools pools){

        this.pools = pools;




        rowsDeployed = 0;
        front_row_y =GameConstants.GAME_HEIGHT/2;
        configurations = new LinkedList<EnemyConfiguration>();

        enemyList = new LinkedList<Enemy>();
        configurations = new LinkedList<EnemyConfiguration>();


        interval = System.nanoTime();

        enemyListLoader = new EnemyConfigPicker(pools);






    }



    public List<Enemy> getEnemyList(){

        return enemyList;
    }


    public void update(){

        if(rowsDeployed <= 0){
            bossOut = false;
        }

        if (rowsDeployed < 5 && System.nanoTime() > interval + ( 2000000000L )) {


            interval = System.nanoTime();
            // deploy a new row

            // don't deploy new enemies when the boss is out
            if(!bossOut) {

                EnemyConfiguration config = enemyListLoader.getConfig(total_rows_deployed);
                this.enemyList.addAll(config.getList());
                this.configurations.add(config);

                if(total_rows_deployed % 20 == 0 && total_rows_deployed > 1){
                    bossOut = true;
                }

                rowsDeployed++;
                total_rows_deployed++;
            }

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



            }


        }








