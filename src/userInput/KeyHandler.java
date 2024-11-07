package userInput;

import main.GameLogic;
import events.GameState;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    private GameLogic gameLogic;

    int keyCode;
//    public boolean upPressed, downPressed, leftPressed, rightPressed;
    public boolean value;
    public boolean pausedPressed = false, escPressed = false, enterKeyPressed = false;
    public boolean tKeyPressed = false;


    public KeyHandler(GameLogic gameLogic){
        this.gameLogic = gameLogic;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        value =true;
        keyCode = e.getKeyCode();

        switch(keyCode){
            case KeyEvent.VK_T:
                if(tKeyPressed){
                    tKeyPressed = false;
                }
                else if(!tKeyPressed){
                    tKeyPressed = true;
                }
                break;
            case KeyEvent.VK_ESCAPE:
                escPressed = value;
                break;

            case KeyEvent.VK_P:
                if(!pausedPressed){
                    pausedPressed = true;
                    if(gameLogic.getCurrentState() == GameState.game_PlayState){
                        gameLogic.setCurrentState(GameState.game_PauseState);
                    }
                }
                else if(pausedPressed){
                    pausedPressed = false;
                    if(gameLogic.getCurrentState() == GameState.game_PauseState){
                        gameLogic.setCurrentState(GameState.game_PlayState);
                    }
                }
                break;

        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        value =false;
        keyCode = e.getKeyCode();
//        switch(keyCode){
//            case KeyEvent.VK_P:
//                pausedPressed = value;
//                break;
//            case KeyEvent.VK_ESCAPE:
//                escPressed = value;
//                break;
//        }
    }
}