package entity;

public class Player extends Entity implements Actionable{




    public Player(String name, String desc, int health){
        super(name, desc,health);
    }


    @Override
    public void perform(){

    }


}
