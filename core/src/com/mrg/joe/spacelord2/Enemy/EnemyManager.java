package com.mrg.joe.spacelord2.Enemy;

import com.mrg.joe.spacelord2.GameConstants;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by Joe on 8/26/2015.
 * This class puts the enemies into play.  It keeps 5 rows in play  unless a boss is deployed.
 * Once a boss is out, no more rows are deployed until all the current enemies are destroyed.
 * It also removes empty rows and controls the "stacking" of the rows on the screen.
 */
public class EnemyManager {

    private List<Enemy> enemyList;
    private List<Enemy> enemyPlayList;
    private int rowsDeployed;
    private float frontRowY;
    private LinkedList<EnemyConfiguration> configurations;
    private int totalRowsDeployed;
    private boolean bossOut;
    private float interval;
    private EnemyConfigPicker enemyListLoader;

    public EnemyManager(EnemyPools pools) {
        rowsDeployed = 0;
        totalRowsDeployed = 0;
        // enemies can only go half way down the screen
        frontRowY = GameConstants.GAME_HEIGHT / 2;
        configurations = new LinkedList<EnemyConfiguration>();
        enemyList = new LinkedList<Enemy>();
        configurations = new LinkedList<EnemyConfiguration>();
        interval = System.nanoTime();
        enemyListLoader = new EnemyConfigPicker(pools);
    }


    public List<Enemy> getEnemyList() {

        return enemyList;
    }


    public void update() {

        // boss is defeated when all rows are cleared.  new rows will not be added once a boss is out unless all enemies out are destroyed.
        if (rowsDeployed <= 0) {
            bossOut = false;
        }
        // deploy a new row every two seconds until 5 rows are out.
        if (rowsDeployed < 5 && System.nanoTime() > interval + (2000000000L)) {


            // a row is being deployed so reset the timer to do another in 2 seconds if need be
            interval = System.nanoTime();


            // don't deploy new enemies when the boss is out
            if (!bossOut) {

                // get a row based on the number of rows that has already been completed
                EnemyConfiguration config = enemyListLoader.getConfig(totalRowsDeployed);
                // add all the newly introduced enemies to the master list.
                this.enemyList.addAll(config.getList());
                this.configurations.add(config);

                // bosses are every 20 rows
                if (totalRowsDeployed % 20 == 0 && totalRowsDeployed > 1) {
                    bossOut = true;
                }

                rowsDeployed++;
                totalRowsDeployed++;
            }

        }


        if (!configurations.isEmpty()) {

            // controls the movement of enemies

            for (ListIterator i = configurations.listIterator(); i.hasNext();) {

                EnemyConfiguration prevConfig = null;


                if (i.hasPrevious()) {
                    prevConfig = (EnemyConfiguration) i.previous();
                    i.next();
                }

                EnemyConfiguration config = (EnemyConfiguration) i.next();

                // if this row is the first row, move it forward until it is halfway down the screen.
                if (configurations.getFirst().equals(config) && config.getRowY() > frontRowY) {

                    config.advanceRow();

                    // if there is a gap between this row and the one in front, move forward until the gap is closed.
                } else if (i.hasPrevious() && prevConfig != null
                        && (config.getRowY() > prevConfig.getRowY() + prevConfig.getRowHeight() + GameConstants.ENEMY_ROW_GAP)) {
                    config.advanceRow();
                } else config.stopRow();


            }

            // controls the removal of dead rows and enemies
            for (ListIterator i = configurations.listIterator(); i.hasNext();) {
                EnemyConfiguration config = (EnemyConfiguration) i.next();
                config.update();

                // drop the rows deployed so another can be released and remove the empty configuration to allow the next one to advance
                if (config.isEmpty()) {
                    i.remove();
                    rowsDeployed--;
                }

            }
        }


    }


}








