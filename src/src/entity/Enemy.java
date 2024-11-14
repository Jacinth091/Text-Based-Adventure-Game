package src.entity;

import entity.Actionable;
import entity.Entity;

public class Enemy extends Entity implements Actionable {
    private String hostility;
    private float diff_multiplier;



    public Enemy(String name, String desc, String hostility, float diff_multiplier){
        super(name, desc);
        this.hostility = hostility;
        this.diff_multiplier = diff_multiplier;
    }


    @Override
    public void perform(){

    }
}
