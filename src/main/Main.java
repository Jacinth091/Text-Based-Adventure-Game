package main;

import updates.GameThread;

public class Main {



    public static void main(String[ ]args){
        String appTitle = "Adventure Game";
        GameThread gameThread = new GameThread();
        GameLogic gameLogic = new GameLogic(gameThread);

        GameApp gameApp = new GameApp(gameLogic, appTitle);
        gameThread.start();




    }
}
