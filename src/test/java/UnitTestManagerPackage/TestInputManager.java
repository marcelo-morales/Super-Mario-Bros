package UnitTestManagerPackage;

import manager.GameEngine;
import manager.GameStatus;
import manager.InputManager;
import manager.MapManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import view.ImageLoader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class TestInputManager {

    public GameEngine gameEngine;
    public InputManager inputManager;
    public MapManager mapManager;

    @BeforeEach
    public void setupInputManager(){
        //inputManager = new InputManager(gameEngine);
        inputManager = new InputManager(gameEngine);
        gameEngine = new GameEngine();
        gameEngine.setGameStatus(GameStatus.RUNNING);

        ImageLoader imageLoader = new ImageLoader();
        mapManager = new MapManager();
        mapManager.createMap(imageLoader, "Map 1.png");

    }

    @Test
    public void testKeyPressed(){
        //KeyEvent keyEvent = new KeyEvent();
        Button a = new Button("click");
        KeyEvent e;
        e = new KeyEvent(a, 1, 20, 1, 10, 'a');
        System.out.println(""+e.getKeyChar());
        System.out.println(""+e.getKeyCode());

        inputManager.keyPressed(e);
    }
}
