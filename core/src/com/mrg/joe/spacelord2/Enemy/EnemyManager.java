package com.mrg.joe.spacelord2.Enemy;

import com.badlogic.gdx.Gdx;
import com.mrg.joe.spacelord2.Enemy.Enemy;
import com.mrg.joe.spacelord2.Enemy.EnemyConfiguration;
import com.mrg.joe.spacelord2.GameConstants;
import com.mrg.joe.spacelord2.Player;
import com.mrg.joe.spacelord2.Weapon.Projectile;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by Joe on 8/26/2015.
 */
public class EnemyManager {

    private List<Enemy> enemyList;
    private int rowsDeployed;
    private float front_row_y;

    private LinkedList<EnemyConfiguration> configurations;

    public EnemyManager(){
        rowsDeployed = 0;


        float spawn_height = Gdx.graphics.getHeight() + 200;
        front_row_y = Gdx.graphics.getHeight()/2;
        configurations = new LinkedList<EnemyConfiguration>();

        enemyList = new LinkedList<Enemy>();



        for(int i = 0; i < 2; i++){

            EnemyConfiguration config = new EnemyConfiguration(3);
            configurations.add(config);


            EnemyConfiguration config2 = new EnemyConfiguration(5);
            configurations.add(config2);


            EnemyConfiguration config3 = new EnemyConfiguration(1);
            configurations.add(config3);


            EnemyConfiguration config4 = new EnemyConfiguration(2);
            configurations.add(config4);





            enemyList.addAll(config.getEnemyConfiguration());
            enemyList.addAll(config2.getEnemyConfiguration());
            enemyList.addAll(config3.getEnemyConfiguration());
            enemyList.addAll(config4.getEnemyConfiguration());

        }

        EnemyConfiguration config5 = new EnemyConfiguration(4);
        configurations.add(config5);
        enemyList.addAll(config5.getEnemyConfiguration());


    }



    public List<Enemy> getEnemyList(){

        return enemyList;
    }


    public void update(){

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

                if (rowsDeployed < 5) {
                    // deploy a new row
                }


            }
        }








