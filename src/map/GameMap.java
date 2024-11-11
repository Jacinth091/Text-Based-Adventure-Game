package map;


import java.util.HashMap;
import java.util.Map;

public class GameMap {

    private final String[] roomNames = {
            "Research Lab",
            "Control Room",
            "Supplies Room",
            "Armory",
            "Security Door",
            "Decontamination Area",
            "Containment Area",
            "Facility Exit",
    };
    private Map<String, Room> gameMap;
    private Room[] rooms;
    private String[] direction = {
            "North",
            "East",
            "West",
            "South",
    };

    private String startingPoint;

    public GameMap(){
        this.gameMap = new HashMap<>();
        this.rooms = new Room[roomNames.length];
        startingPoint = roomNames[0];


        setGameMap();

//        debugMap();
//
//        System.out.println(gameMap.get(roomNames[1]).getRoomViaDirection("West"));
    }


    public void setGameMap(){

        for(int i =0; i < rooms.length; i++){
            rooms[i] = new Room();
            gameMap.put(roomNames[i], rooms[i]);
        }


        setResearchLabRoom();
        setControlRoom();
        setSuppliesRoom();
        setArmoryRoom();
        setSecurityDoorRoom();
        setDeconRoom();
        setContamRoom();
        setFacilExitRoom();

    }

    public void debugMap(){
        for(String roomName : roomNames){
            System.out.println("Current Room: " + roomName + "\nIs initialized?: " + ((gameMap.containsKey(roomName)) ? "Yes" : "NO") );
            System.out.println(gameMap.get(roomName).getMapConnections() + "\n");

        }


    }



    public void setResearchLabRoom(){
        // Research Lab
        rooms[0].addConnection(direction[0],roomNames[1]);
        rooms[0].addConnection(direction[2],roomNames[2]);
    }


    public void setControlRoom(){
        // Control Room
        rooms[1].addConnection(direction[3],roomNames[0]);
        rooms[1].addConnection(direction[0],roomNames[5]);
    }


    public void setSuppliesRoom(){
        // Supplies Room
        rooms[2].addConnection(direction[1],roomNames[0]);
        rooms[2].addConnection(direction[2],roomNames[3]);
        rooms[2].addConnection(direction[0],roomNames[4]);
    }


    public void setArmoryRoom(){

        // Armory
        rooms[3].addConnection(direction[2],roomNames[2]);

    }


    public void setSecurityDoorRoom(){
        // Security Door
        rooms[4].addConnection(direction[3],roomNames[2]);

        // TODO: make sure the player don't access it
        // TODO: without any conditions (keys, objectives, etc)
        rooms[4].addConnection(direction[0],roomNames[7]);
    }


    public void setDeconRoom(){
        // Decontamination Area
        // TODO: make sure the player don't access it
        // TODO: without any conditions (keys, objectives, etc)
        rooms[5].addConnection(direction[3],roomNames[1]);
        rooms[5].addConnection(direction[1],roomNames[6]);
    }


    public void setContamRoom(){
        // Containment Area
        // TODO: make sure the player don't access it
        // TODO: without any conditions (keys, objectives, etc)
        rooms[6].addConnection(direction[3],roomNames[5]);

    }


    public void setFacilExitRoom(){
        // Facility Exit
        // TODO: make sure the player don't access it
        // TODO: without any conditions (keys, objectives, etc)
        rooms[7].addConnection(direction[3],roomNames[4]);
    }


    public String[] getRoomNames() {
        return roomNames;
    }

    public Room[] getRooms() {
        return rooms;
    }

    public String[] getDirection() {
        return direction;
    }

    public String getStartingPoint() {
        return startingPoint;
    }
}
