package entity;

public class Player extends Entity implements Actionable {

    private int maxSanity = 100;
    private int maxStealth = 100;
    private int maxCalmness = 100;
    private int maxHealth = 100; // Added a max health constant for consistency

    private int playerHealth;
    private int playerSanity;
    private int playerCalmness;
    private int playerStealth;

    // Constructor with default stats
    public Player(String name, String desc) {
        super(name, desc);
        setDefaultStats();
    }

    // Overloaded constructor to allow custom initial stats
    public Player(String name, String desc, int health, int sanity, int calmness, int stealth) {
        super(name, desc);
        this.playerHealth = Math.min(health, maxHealth);
        this.playerSanity = Math.min(sanity, maxSanity);
        this.playerCalmness = Math.min(calmness, maxCalmness);
        this.playerStealth = Math.min(stealth, maxStealth);
    }

    // Method to reset stats to default values
    public void setDefaultStats() {
        playerHealth = 0;
        playerSanity = maxSanity;
        playerCalmness = 50;
        playerStealth = 30;
    }

    public void resetStats() {
        setDefaultStats();
    }

    // Perform action, example: decrease sanity when performing an action
    @Override
    public void perform() {
        decreaseSanity(5); // Example adjustment to sanity during an action
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

    public int getPlayerCalmness() {
        return playerCalmness;
    }

    public void setPlayerCalmness(int playerCalmness) {
        this.playerCalmness = Math.max(0, Math.min(playerCalmness, maxCalmness));
    }

    public int getPlayerStealth() {
        return playerStealth;
    }

    public void setPlayerStealth(int playerStealth) {
        this.playerStealth = Math.max(0, Math.min(playerStealth, maxStealth));
    }

    // Max value getters for reference
    public int getMaxSanity() {
        return maxSanity;
    }

    public int getMaxStealth() {
        return maxStealth;
    }

    public int getMaxCalmness() {
        return maxCalmness;
    }

    public int getMaxHealth() {
        return maxHealth;
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

    public void increaseCalmness(int amount) {
        setPlayerCalmness(this.playerCalmness + amount);
    }

    public void decreaseCalmness(int amount) {
        setPlayerCalmness(this.playerCalmness - amount);
    }

    public void increaseStealth(int amount) {
        setPlayerStealth(this.playerStealth + amount);
    }

    public void decreaseStealth(int amount) {
        setPlayerStealth(this.playerStealth - amount);
    }
}
