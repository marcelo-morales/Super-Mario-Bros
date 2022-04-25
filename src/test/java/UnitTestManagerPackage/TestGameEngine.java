package UnitTestManagerPackage;

import manager.ButtonAction;
import manager.GameStatus;
import manager.MapManager;
import model.Map;
import model.hero.Mario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import manager.GameEngine;
import view.ImageLoader;

import static org.junit.jupiter.api.Assertions.*;


public class TestGameEngine {

    public GameEngine gameEngine;
    public MapManager mg;

    @BeforeEach
    public void setUpGameEngine() {

        gameEngine = new GameEngine();
        ImageLoader imageLoader = new ImageLoader();
         mg = new MapManager();
        mg.createMap(imageLoader, "Map 1.png");
    }

    @Test
    public void testGameEngineInit() {
        assertSame(gameEngine.getGameStatus(), GameStatus.START_SCREEN);
        //assertTrue(gameEngine.isRunning);
    }

    @Test
    /*
    Test setting the game as game over.
     */
    public void testGameEngineReset() {
        gameEngine.setGameStatus(GameStatus.GAME_OVER);
        gameEngine.receiveInput(ButtonAction.GO_TO_START_SCREEN);
        assertEquals(gameEngine.getGameStatus(),GameStatus.START_SCREEN);
    }

    @Test
    /*
    Test selecting the map with mouse
     */
    public void testSelectingMapWithMouse() {
        gameEngine.selectMapViaMouse();
        assertEquals(gameEngine.getGameStatus(), GameStatus.START_SCREEN);
    }

//    @Test
//    public void testGameRunning() {
//        gameEngine.run();
//        assertEquals(gameEngine.getGameStatus(), GameStatus.RUNNING);
//        gameEngine.setGameStatus(GameStatus.PAUSED);
//    }

    @Test
    public void testReceiveInputWithButtonSelectStartScreenViewAbout() {
        gameEngine.setGameStatus(GameStatus.START_SCREEN);
        gameEngine.receiveInput(ButtonAction.SELECT);

        assertNotEquals(gameEngine.getGameStatus(),GameStatus.GAME_OVER);
        assertEquals(gameEngine.getGameStatus(),GameStatus.MAP_SELECTION);
    }

    @Test
    public void testReceiveInputStartScreenButtonSelectViewAbout() {
        gameEngine.setGameStatus(GameStatus.START_SCREEN);
        gameEngine.receiveInput(ButtonAction.SELECT);
        assertNotEquals(gameEngine.getGameStatus(),GameStatus.GAME_OVER);
        assertEquals(gameEngine.getGameStatus(),GameStatus.MAP_SELECTION);
    }



    @Test
    public void testReceiveInputWithMapSelectionButtonSelect() {
        gameEngine.setGameStatus(GameStatus.MAP_SELECTION);
        gameEngine.receiveInput(ButtonAction.SELECT);
        assertEquals(GameStatus.MAP_SELECTION, gameEngine.getGameStatus());
    }

    @Test
    public void testReceiveInputWithMapSelectionButtonUp() {
        gameEngine.setGameStatus(GameStatus.MAP_SELECTION);
        gameEngine.receiveInput(ButtonAction.GO_UP);
        assertEquals(GameStatus.MAP_SELECTION, gameEngine.getGameStatus());
    }

    /*
    Test when clicking down button this allows me to select another map
     */
    @Test
    public void testReceiveInputWithMapSelectionButtonDown() {
        gameEngine.setGameStatus(GameStatus.MAP_SELECTION);
        gameEngine.receiveInput(ButtonAction.GO_DOWN);
        assertEquals(GameStatus.MAP_SELECTION, gameEngine.getGameStatus());
    }

    @Test
    public void testReceiveInputGameStatusStartScreenScreenSelectionViewAbout() {
        gameEngine.setGameStatus(GameStatus.START_SCREEN);
        gameEngine.receiveInput(ButtonAction.SELECT);
        //startScreenSelection.VIEW_ABOUT;
    }

    @Test
    public void testGameStatusRunningButtonActionJump() {
        gameEngine.setGameStatus(GameStatus.RUNNING);
        Map myMap = gameEngine.getMapManager().map;
        Mario mario = new Mario(0.1, 0.1);
        myMap.setMario(mario);
        gameEngine.receiveInput(ButtonAction.JUMP);
        //mg.
        //mario should jump
        //assertTrue(gameEngine.getMapManager().getMario().isJumping());
        //how to get complete branch coverage when game status is running, will need to
        //interact with other classes as well
        assertEquals(gameEngine.getGameStatus(), GameStatus.RUNNING);
    }

    @Test
    public void testSelectMapViaMouse() {

    }






}
