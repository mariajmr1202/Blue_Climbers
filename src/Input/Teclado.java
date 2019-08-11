package Input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class Teclado implements KeyListener{
    private boolean teclas[];
    public boolean up, left, down, right, attack, jump,escape;
    
    public Teclado() {
        teclas = new boolean[256];
    }
    
    public void update() {
        left = teclas[KeyEvent.VK_A];
        down = teclas[KeyEvent.VK_S];
        right = teclas[KeyEvent.VK_D];
        attack = teclas[KeyEvent.VK_E];
        jump = teclas[KeyEvent.VK_W];
        escape= teclas[KeyEvent.VK_ESCAPE];
    }
    
    @Override
    public void keyPressed(KeyEvent e) {
        teclas[e.getKeyCode()] = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        teclas[e.getKeyCode()] = false;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }
}
