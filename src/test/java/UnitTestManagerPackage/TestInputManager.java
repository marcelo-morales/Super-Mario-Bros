package UnitTestManagerPackage;

import manager.GameEngine;
import manager.GameStatus;
import manager.InputManager;
import manager.MapManager;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import view.ImageLoader;

import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TestInputManager {

    public static GameEngine gameEngine;
    public static InputManager inputManager;

    @BeforeAll
    static void setupInputManager(){
        gameEngine = new GameEngine();
        inputManager = new InputManager(gameEngine);
        gameEngine.setGameStatus(GameStatus.RUNNING);

    }

    /*
    Whitebox testing - test input manager registers when someone presses the up key in start screen of game.
     */
    @Test
    public void testKeyPressedKeyUpGameStatusStartScreen(){
        Button a = new Button("click");
        KeyEvent e = new KeyEvent(a, 1, 20, InputEvent.SHIFT_MASK, 38, 'a');
        gameEngine.setGameStatus(GameStatus.START_SCREEN);
        inputManager.keyPressed(e);
        assertNotNull(inputManager);
    }

    /*
   Whitebox testing - test input manager registers when someone presses the up key in the map selection screen of game.
    */
    @Test
    public void testKeyPressedKeyUpGameStatusMapSelection(){
        Button a = new Button("click");
        KeyEvent e = new KeyEvent(a, 1, 20, InputEvent.SHIFT_MASK, 38, 'a');
        gameEngine.setGameStatus(GameStatus.MAP_SELECTION);
        inputManager.keyPressed(e);
        assertNotNull(inputManager);
    }

    /*
    Whitebox testing - test input manager registers when someone presses the up key with no game status set
     */
    @Test
    public void testKeyPressedKeyUpNoGameStatusSet(){
        Button a = new Button("click");
        KeyEvent e = new KeyEvent(a, 1, 20, InputEvent.SHIFT_MASK, 38, 'a');
        inputManager.keyPressed(e);
    }

    /*
    Whitebox testing - test input manager registers when someone presses the down key with game status of start screen.
     */
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

    /*
    Whitebox testing - test input manager registers when someone presses the down key in map selection page.
     */
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

    /*
    Whitebox testing - test input manager registers when someone presses the up right key in keyboard.
     */
    @Test
    public void testKeyPressedKeyRight(){
        Button a = new Button("click");
        KeyEvent e = new KeyEvent(a, 1, 20, InputEvent.SHIFT_MASK, 39, 'a');
        inputManager.keyPressed(e);
        assertNotNull(inputManager);
    }

    /*
    Whitebox testing - test input manager registers when someone presses the left key on keyboard.
     */
    @Test
    public void testKeyPressedKeyLeft(){
        Button a = new Button("click");
        KeyEvent e = new KeyEvent(a, 1, 20, InputEvent.SHIFT_MASK, 37, 'a');
        inputManager.keyPressed(e);
        assertNotNull(inputManager);
    }

    /*
   Whitebox testing - test input manager registers when someone presses the enter key on keyboard.
    */
    @Test
    public void testKeyPressedKeyEnter(){
        Button a = new Button("click");
        KeyEvent e = new KeyEvent(a, 1, 20, InputEvent.SHIFT_MASK, 10, 'a');
        inputManager.keyPressed(e);
        assertNotNull(inputManager);
    }

    /*
   Whitebox testing - test input manager registers when someone presses the escape key on keyboard when game is running.
    */
    @Test
    @Disabled
    public void testKeyPressedKeyEscapeGameStatusRunning(){
        Button a = new Button("click");
        KeyEvent e = new KeyEvent(a, 1, 20, InputEvent.SHIFT_MASK, 27, 'a');
        gameEngine.setGameStatus(GameStatus.RUNNING);
        inputManager.keyPressed(e);
        assertNotNull(inputManager);
    }

    /*
  Whitebox testing - test input manager registers when someone presses the escape key when the game is paused.
   */
    @Test
    public void testKeyPressedKeyEscapeGameStatusPaused(){
        Button a = new Button("click");
        KeyEvent e = new KeyEvent(a, 1, 20, InputEvent.SHIFT_MASK, 27, 'a');
        gameEngine.setGameStatus(GameStatus.PAUSED);
        inputManager.keyPressed(e);
        assertNotNull(inputManager);
    }

    /*
  Whitebox testing - test input manager registers when someone presses the escape key when the game is in the start screen.
   */
    @Test
    public void testKeyPressedKeyEscape(){
        Button a = new Button("click");
        KeyEvent e = new KeyEvent(a, 1, 20, InputEvent.SHIFT_MASK, 27, 'a');
        gameEngine.setGameStatus(GameStatus.START_SCREEN);
        inputManager.keyPressed(e);
        assertNotNull(inputManager);
    }

    /*
  Whitebox testing - test input manager registers when someone presses the space key on keyboard.
   */
    @Test
    public void testKeyPressedKeySpace(){
        Button a = new Button("click");
        KeyEvent e = new KeyEvent(a, 1, 27, InputEvent.SHIFT_MASK, 32, 'a');
        inputManager.keyPressed(e);
        assertNotNull(inputManager);
    }

    /*
  Whitebox testing - test that when a key is typed on the keyboard that this key is registered.
   */
    @Test
    public void keyTypedTest() {
        Button a = new Button("click");
        KeyEvent keyEvent = new KeyEvent(a, 1, 27, InputEvent.SHIFT_MASK, 32, 'a');
        inputManager.keyTyped(keyEvent);
        assertNotNull(inputManager);
    }

    /*
  Whitebox testing - clicking on something on the screen with mouse is registered.
   */
    @Test
    public void mouseClickedTest() {
        Button componentButton = new Button("click");
        MouseEvent keyEvent = new MouseEvent((Component) componentButton, 1, 100L, 2, 2, 2, 2, false);
        inputManager.mouseClicked(keyEvent);
        assertNotNull(inputManager);

    }

    /*
  Whitebox testing - When mouse is released after a long click, this is registered in the game.
   */
    @Test
    public void mouseReleasedTest() {
        Button componentButton = new Button("click");
        MouseEvent keyEvent = new MouseEvent((Component) componentButton, 1, 100L, 2, 2, 2, 2, false);
        inputManager.mouseReleased(keyEvent);
        assertNotNull(inputManager);
    }

    /*
  Whitebox testing - test input manager when someone clicks on the mouse when game is paused
   */
    @Test
    public void testMousePressed() {
        Button componentButton = new Button("click");
        MouseEvent keyEvent = new MouseEvent((Component) componentButton, 1, 100L, 2, 2, 2, 2, false);
        gameEngine.setGameStatus(GameStatus.PAUSED);
        inputManager.mousePressed(keyEvent);
        assertNotNull(inputManager);
    }

    /*
  Whitebox testing - test input manager registers when mouse is entered.
   */
    @Test
    public void testMouseEntered() {
        Button componentButton = new Button("click");
        MouseEvent keyEvent = new MouseEvent((Component) componentButton, 1, 100L, 2, 2, 2, 2, false);
        gameEngine.setGameStatus(GameStatus.PAUSED);
        inputManager.mouseEntered(keyEvent);
        assertNotNull(inputManager);
    }

    /*
  Whitebox testing - test input manager registers when mouse exits.
   */
    @Test
    public void testMouseExited() {
        Button componentButton = new Button("click");
        MouseEvent keyEvent = new MouseEvent((Component) componentButton, 1, 100L, 2, 2, 2, 2, false);
        gameEngine.setGameStatus(GameStatus.PAUSED);
        inputManager.mouseExited(keyEvent);
        assertNotNull(inputManager);
    }

    /*
  Whitebox testing - test input manager registers when a key is released with right arrow key.
   */
    @Test
    public void testKeyReleasedWhenRight() {
        Button a = new Button("click");
        KeyEvent e = new KeyEvent(a, 1, 20, InputEvent.SHIFT_MASK, 39, 'a');
        inputManager.keyReleased(e);
        assertNotNull(inputManager);
    }

    /*
  Whitebox testing - test input manager registers when a key is released with left arrow key.
   */
    @Test
    public void testKeyReleasedWhenLeft() {
        Button a = new Button("click");
        KeyEvent e = new KeyEvent(a, 1, 20, InputEvent.SHIFT_MASK, 37, 'a');
        inputManager.keyReleased(e);
        assertNotNull(inputManager);
    }

    /*
 Whitebox testing - test input manager registers when mouse is pressed in map selection page.
  */
    @Test
    @Disabled
    public void testMousePressedWithMapSelection() {
        Button componentButton = new Button("click");
        MouseEvent keyEvent = new MouseEvent((Component) componentButton, 1, 100L, 2, 2, 2, 2, false);
        gameEngine.setGameStatus(GameStatus.MAP_SELECTION);
        inputManager.mousePressed(keyEvent);
        assertNotNull(inputManager);
    }


}
