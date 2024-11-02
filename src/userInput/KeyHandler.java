package userInput;

import main.GamePanel;
import main.GameState;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    private GamePanel gp;

    int keyCode;
//    public boolean upPressed, downPressed, leftPressed, rightPressed;
    public boolean value;
    public boolean pausedPressed = false, escPressed = false, enterKeyPressed = false;
    public boolean tKeyPressed = false;


    public KeyHandler(GamePanel gp){
        this.gp = gp;
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
                    if(gp.getCurrentState() == GameState.game_PlayState){
                        gp.setCurrentState(GameState.game_PauseState);
                    }
                }
                else if(pausedPressed){
                    pausedPressed = false;
                    if(gp.getCurrentState() == GameState.game_PauseState){
                        gp.setCurrentState(GameState.game_PlayState);
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