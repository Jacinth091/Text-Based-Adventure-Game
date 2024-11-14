package entity;

public class Player extends Entity implements Actionable {
    private int maxSanity = 100;
    private int maxHealth = 100; // Added a max health constant for consistency

    private int playerHealth;
    private int playerSanity;


    private String weapon;
    private String currentLocation;


    // Constructor with default stats
    public Player(String name, String desc) {
        super(name, desc);
        setDefaultStats();
    }

    // Overloaded constructor to allow custom initial stats
    public Player(String name, String desc, int health, int sanity) {
        super(name, desc);
        this.playerHealth = Math.min(health, maxHealth);
        this.playerSanity = Math.min(sanity, maxSanity);

    }




    @Override
    public void perform() {
        decreaseSanity(5);
    }
    // Method to reset stats to default values


    public void setDefaultStats() {
        playerHealth = maxHealth;
        playerSanity = maxSanity;

    }

    public void resetStats() {
        setDefaultStats();
    }



    // Helper methods for stat adjustments
    public void increaseHealth(int amount) {
        setPlayerHealth(this.playerHealth + amount);
    }

    public void decreaseHealth(int amount) {
        setPlayerHealth(this.playerHealth - amount);
    }

    public void increaseSanity(int amount) {
        setPlayerSanity(this.playerSanity + amount);
    }

    public void decreaseSanity(int amount) {

        setPlayerSanity(this.playerSanity - amount);
    }


    // Getters and setters with value limits

    public int getPlayerSanity() {
        return playerSanity;
    }

    public void setPlayerSanity(int playerSanity) {
        this.playerSanity = Math.max(0, Math.min(playerSanity, maxSanity));
    }

    public int getPlayerHealth() {
        return playerHealth;
    }

    public void setPlayerHealth(int playerHealth) {
        this.playerHealth = Math.max(0, Math.min(playerHealth, maxHealth));
    }

    // Max value getters for reference
    public int getMaxSanity() {
        return maxSanity;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public String getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(String currentLocation) {
        this.currentLocation = currentLocation;
    }

    public String getWeapon() {
        return weapon;
    }

    public void setWeapon(String weapon) {
        this.weapon = weapon;
    }
}
