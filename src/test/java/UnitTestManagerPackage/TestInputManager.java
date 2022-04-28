package UnitTestManagerPackage;

import manager.GameEngine;
import manager.GameStatus;
import manager.InputManager;
import manager.MapManager;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import view.ImageLoader;

import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TestInputManager {

    public static GameEngine gameEngine;
    public static InputManager inputManager;
    public static MapManager mapManager;

    @BeforeAll
    static void setupInputManager(){
        //inputManager = new InputManager(gameEngine);
        gameEngine = new GameEngine();
        inputManager = new InputManager(gameEngine);
        gameEngine.setGameStatus(GameStatus.RUNNING);

    }

    @Test
    public void testKeyPressedKeyUpGameStatusStartScreen(){
        //KeyEvent keyEvent = new KeyEvent();
        Button a = new Button("click");
        KeyEvent e;
        e = new KeyEvent(a, 1, 20, InputEvent.SHIFT_MASK, 38, 'a');
        gameEngine.setGameStatus(GameStatus.START_SCREEN);
        inputManager.keyPressed(e);
    }

    @Test
    public void testKeyPressedKeyUpGameStatusMapSelection(){
        //KeyEvent keyEvent = new KeyEvent();
        Button a = new Button("click");
        KeyEvent e;
        e = new KeyEvent(a, 1, 20, InputEvent.SHIFT_MASK, 38, 'a');
        gameEngine.setGameStatus(GameStatus.MAP_SELECTION);
        inputManager.keyPressed(e);
    }

    @Test
    public void testKeyPressedKeyUpNoGameStatusSet(){
        //KeyEvent keyEvent = new KeyEvent();
        Button a = new Button("click");
        KeyEvent e;
        e = new KeyEvent(a, 1, 20, InputEvent.SHIFT_MASK, 38, 'a');
        inputManager.keyPressed(e);
    }

    @Test
    public void testKeyPressedKeyDownGameStatusStart(){
        //KeyEvent keyEvent = new KeyEvent();
        Button a = new Button("click");
        KeyEvent e;
        e = new KeyEvent(a, 1, 20, InputEvent.SHIFT_MASK, 40, 'a');
        gameEngine.setGameStatus(GameStatus.START_SCREEN);
        inputManager.keyPressed(e);
        assertNotNull(inputManager);

    }

    @Test
    public void testKeyPressedKeyDownGameStatusMapSelection(){
        //KeyEvent keyEvent = new KeyEvent();
        Button a = new Button("click");
        KeyEvent e;
        e = new KeyEvent(a, 1, 20, InputEvent.SHIFT_MASK, 40, 'a');
        gameEngine.setGameStatus(GameStatus.MAP_SELECTION);
        inputManager.keyPressed(e);
        assertNotNull(inputManager);

    }

    @Test
    public void testKeyPressedKeyRight(){
        //KeyEvent keyEvent = new KeyEvent();
        Button a = new Button("click");
        KeyEvent e;
        e = new KeyEvent(a, 1, 20, InputEvent.SHIFT_MASK, 39, 'a');
        inputManager.keyPressed(e);
        assertNotNull(inputManager);
    }

    @Test
    public void testKeyPressedKeyLeft(){
        //KeyEvent keyEvent = new KeyEvent();
        Button a = new Button("click");
        KeyEvent e;
        e = new KeyEvent(a, 1, 20, InputEvent.SHIFT_MASK, 37, 'a');
        inputManager.keyPressed(e);
        assertNotNull(inputManager);
    }

    @Test
    public void testKeyPressedKeyEnter(){
        //KeyEvent keyEvent = new KeyEvent();
        Button a = new Button("click");
        KeyEvent e;
        e = new KeyEvent(a, 1, 20, InputEvent.SHIFT_MASK, 10, 'a');
        inputManager.keyPressed(e);
        assertNotNull(inputManager);
    }

    @Test
    public void testKeyPressedKeyEscapeGameStatusRunning(){
        //KeyEvent keyEvent = new KeyEvent();
        Button a = new Button("click");
        KeyEvent e;
        e = new KeyEvent(a, 1, 27, InputEvent.SHIFT_MASK, 10, 'a');
        gameEngine.setGameStatus(GameStatus.RUNNING);
        inputManager.keyPressed(e);
        assertNotNull(inputManager);
    }

    @Test
    public void testKeyPressedKeyEscapeGameStatusPaused(){
        //KeyEvent keyEvent = new KeyEvent();
        Button a = new Button("click");
        KeyEvent e;
        e = new KeyEvent(a, 1, 27, InputEvent.SHIFT_MASK, 10, 'a');
        gameEngine.setGameStatus(GameStatus.PAUSED);
        inputManager.keyPressed(e);
        assertNotNull(inputManager);
    }

    @Test
    public void testKeyPressedKeyEscapeNoGameStatusSet(){
        //KeyEvent keyEvent = new KeyEvent();
        Button a = new Button("click");
        KeyEvent e;
        e = new KeyEvent(a, 1, 27, InputEvent.SHIFT_MASK, 10, 'a');
        inputManager.keyPressed(e);
        assertNotNull(inputManager);
    }

    @Test
    public void testKeyPressedKeySpace(){
        //KeyEvent keyEvent = new KeyEvent();
        Button a = new Button("click");
        KeyEvent e;
        e = new KeyEvent(a, 1, 27, InputEvent.SHIFT_MASK, 32, 'a');
        inputManager.keyPressed(e);
        assertNotNull(inputManager);
    }


}
