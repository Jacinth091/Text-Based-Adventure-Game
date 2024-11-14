package map;

import entity.Enemy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Room {
    private Map<String, String> mapConnections;
    private ArrayList<Enemy> enemyList;
    private int enemyCount;
    private boolean hasEnemy;
    private ArrayList<String> items;

    public Room() {
        this.mapConnections = new HashMap<>();

    }



    public Map<String, String> getMapConnections() {
        return mapConnections;
    }

    public ArrayList<String> getItems() {
        return items;
    }

    public ArrayList<Enemy> getEnemyList() {
        return enemyList;
    }

    public int getEnemyCount() {
        return enemyCount;
    }

    public String getRoomViaDirection(String direction){
        return mapConnections.get(direction);
    }



    public boolean isHasEnemy() {
        return hasEnemy;
    }

    public void addEnemy(Enemy enemy){
        enemyList.add(enemy);
    }

    public void addConnection(String direction, String roomName) {
        mapConnections.put(direction, roomName);
    }


    @Override
    public String toString() {
        return "Room{" +
                "mapConnections=" + mapConnections +
                '}';
    }
}
