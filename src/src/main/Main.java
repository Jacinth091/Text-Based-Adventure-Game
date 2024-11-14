package src.main;

import main.GameApp;
import main.GameLogic;
import src.updates.GameThread;

public class Main {



    public static void main(String[ ]args){
        String appTitle = "Adventure Game";
        GameThread gameThread = new GameThread();
        main.GameLogic gameLogic = new GameLogic(gameThread);

        main.GameApp gameApp = new GameApp(gameLogic, appTitle);
        gameThread.start();




    }
}
