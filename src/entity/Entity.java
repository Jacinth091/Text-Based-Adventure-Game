package entity;

public class Entity{

    private String entName;
    private String entDesc;
    private int entHealth;


    public Entity(String entName, String entDesc){
        this.entName = entName;
        this.entDesc = entDesc;
    }

    public Entity(String entName, String entDesc, int entHealth){
        this.entName = entName;
        this.entDesc = entDesc;
        this.entHealth = entHealth;
    }


    public String getEntName() {
        return entName;
    }

    public String getEntDesc() {
        return entDesc;
    }

    public int getEntHealth() {
        return entHealth;
    }


}
