package userInput;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    int keyCode;

//    public boolean upPressed, downPressed, leftPressed, rightPressed;
    public boolean value;
    public boolean pausedPressed, escPressed;
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        value =true;
        keyCode = e.getKeyCode();

        switch(keyCode){
            case KeyEvent.VK_P:
                pausedPressed = value;
                break;
            case KeyEvent.VK_ESCAPE:
                escPressed = value;
                break;

        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        value =false;
        keyCode = e.getKeyCode();
        switch(keyCode){
            case KeyEvent.VK_P:
                pausedPressed = value;
                break;
            case KeyEvent.VK_ESCAPE:
                escPressed = value;
                break;
        }
    }
}