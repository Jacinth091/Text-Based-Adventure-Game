package entity;

public class Enemy extends Entity implements Actionable{




    public Enemy(String name, String desc, int health){
        super(name, desc,health);
    }


    @Override
    public void perform(){

    }
}
