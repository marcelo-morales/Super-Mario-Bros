import manager.ButtonAction;
import manager.GameStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import manager.GameEngine;
import view.StartScreenSelection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

@Disabled
public class testGameEngine {

    public GameEngine gameEngine;

    @BeforeEach
    public void setUpGameEngine() {
         gameEngine = new GameEngine();

    }

    @Test
    public void testGameEngineInit() {
        assertSame(gameEngine.getGameStatus(), GameStatus.START_SCREEN);
        //assertTrue(gameEngine.isRunning);
    }

    @Test
    public void testGameEngineReset() {
        gameEngine.setGameStatus(GameStatus.GAME_OVER);
        gameEngine.receiveInput(ButtonAction.GO_TO_START_SCREEN);
        assertEquals(gameEngine.getGameStatus(),GameStatus.START_SCREEN);
    }

    @Test
    public void testSelectingMapWithMouse() {
        gameEngine.selectMapViaMouse();
        assertEquals(gameEngine.getGameStatus(), GameStatus.START_SCREEN);
    }

    @Test
    public void testGameRunning() {
        gameEngine.run();
        assertEquals(gameEngine.getGameStatus(), GameStatus.RUNNING);
    }

    @Test
    public void testReceiveInput() {
        gameEngine.setGameStatus(GameStatus.START_SCREEN);
        gameEngine.receiveInput(ButtonAction.SELECT);
        //gameEngine.startScreenSelection = StartScreenSelection.START_GAME;
    }





}
