package map;

import java.util.HashMap;
import java.util.Map;

public class GameMap {

    private Map<String, Map<String, Room>> gameMap = new HashMap<>();

    public GameMap(){




    }


    public void initGameMap(){







    }


    public Map<String, Map<String, Room>> getGameMap() {
        return gameMap;
    }

    public void setGameMap(Map<String, Map<String, Room>> gameMap) {
        this.gameMap = gameMap;
    }
}
