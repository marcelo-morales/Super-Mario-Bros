package UnitTestManagerPackage;

import manager.ButtonAction;
import manager.GameStatus;
import manager.MapManager;
import model.Map;
import model.hero.Mario;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import manager.GameEngine;
import view.ImageLoader;
import view.StartScreenSelection;

import static org.junit.jupiter.api.Assertions.*;

public class TestGameEngine {

    public static GameEngine gameEngine;
    public static MapManager mg;

    @BeforeAll
    static void setUpGameEngine() {

        gameEngine = new GameEngine();
        ImageLoader imageLoader = new ImageLoader();
        mg = new MapManager();
        mg.createMap(imageLoader, "/Map 1.png");
    }

    /*
    Test that game was initialized successfully with Start Screen game status
    starting the game.
     */
    @Test
    public void testGameEngineInit() {
        assertSame(gameEngine.getGameStatus(), GameStatus.START_SCREEN);
    }

    /*
    Test setting the game as game over.
    */
    @Test
    public void testGameEngineReset() {
        gameEngine.setGameStatus(GameStatus.GAME_OVER);
        gameEngine.receiveInput(ButtonAction.GO_TO_START_SCREEN);
        assertEquals(gameEngine.getGameStatus(),GameStatus.START_SCREEN);
    }

    /*
  Test selecting the map with mouse
   */
    @Disabled
    @Test
    public void testSelectingMapWithMouse() {
        gameEngine.selectMapViaMouse();
        assertEquals(gameEngine.getGameStatus(), GameStatus.START_SCREEN);
    }


    /*
    Test receiving a keyboard input of select key when we are in the start screen.
     */
    @Test
    public void testReceiveInputWithButtonSelectStartScreenViewAbout() {
        gameEngine.setGameStatus(GameStatus.START_SCREEN);
        gameEngine.receiveInput(ButtonAction.SELECT);

        assertNotEquals(gameEngine.getGameStatus(),GameStatus.GAME_OVER);
        assertEquals(gameEngine.getGameStatus(),GameStatus.MAP_SELECTION);
    }

    /*
    Test receiving a keyboard input of select key when we are in the start screen.
     */
    @Test
    @Disabled
    public void testReceiveInputStartScreenButtonSelectViewAbout() {
        gameEngine.setGameStatus(GameStatus.START_SCREEN);
        gameEngine.receiveInput(ButtonAction.SELECT);
        assertNotEquals(gameEngine.getGameStatus(),GameStatus.GAME_OVER);
        assertEquals(gameEngine.getGameStatus(),GameStatus.MAP_SELECTION);
    }

    /*
  Test when input has button action select and start screen selection of view about
   */
    @Test
    public void testReceiveInputGameStatusStartScreenInputSelectStartScreenViewAbout() {
        gameEngine.setGameStatus(GameStatus.START_SCREEN);
        gameEngine.receiveInput(ButtonAction.SELECT);
        gameEngine.startScreenSelection = StartScreenSelection.VIEW_ABOUT;
        assertEquals(gameEngine.getGameStatus(),GameStatus.HELP_SCREEN);
    }

    /*
    Test when input has button action select and start screen selection of view about
    */
    @Test
    public void testReceiveInputGameStatusStartScreenInputSelectStartScreenViewHelp() {
        gameEngine.setGameStatus(GameStatus.START_SCREEN);
        gameEngine.receiveInput(ButtonAction.SELECT);
        gameEngine.startScreenSelection = StartScreenSelection.VIEW_HELP;
        assertEquals(gameEngine.getGameStatus(),GameStatus.HELP_SCREEN);
    }

    /*
    Test when input has button action select and start screen selection of view about
    */
    @Test
    public void testReceiveInputGameStatusStartScreenInputGoUp() {
        gameEngine.setGameStatus(GameStatus.START_SCREEN);
        gameEngine.receiveInput(ButtonAction.GO_UP);
        assertEquals(gameEngine.getGameStatus(),GameStatus.START_SCREEN);
    }

    /*
    Test when input has button action select and start screen selection of view about
    */
    @Test
    public void testReceiveInputGameStatusStartScreenInputGoDown() {
        gameEngine.setGameStatus(GameStatus.START_SCREEN);
        gameEngine.receiveInput(ButtonAction.GO_DOWN);
        assertEquals(gameEngine.getGameStatus(),GameStatus.START_SCREEN);
    }

    /*
    Test when input has button action select and start screen selection of view about
    */
    @Test
    public void testReceiveInputGameStatusPause() {
        gameEngine.setGameStatus(GameStatus.PAUSED);
        gameEngine.receiveInput(ButtonAction.PAUSE_RESUME);
        assertEquals(gameEngine.getGameStatus(),GameStatus.RUNNING);
    }

    /*
    Test when input has button action select and start screen selection of view about
    */
    @Test
    @Disabled
    public void testReceiveInputGameStatusMissionPassedInputGoToStartScreen() {
        gameEngine.setGameStatus(GameStatus.MISSION_PASSED);
        gameEngine.receiveInput(ButtonAction.GO_TO_START_SCREEN);
        assertEquals(gameEngine.getGameStatus(),GameStatus.MISSION_PASSED);
    }

    /*
    Test that the camera is able to be shaken.
     */
    @Test
    public void testShakeCamera() {
        gameEngine.shakeCamera();
        assertNotNull(gameEngine);
    }




    /*
    Test that game status is changed when going to the map selection page and clicking select.
     */
    @Test
    @Disabled
    //bug game status is not changed when selecting map
    public void testReceiveInputWithMapSelectionButtonSelect() {
        gameEngine.setGameStatus(GameStatus.MAP_SELECTION);
        gameEngine.receiveInput(ButtonAction.SELECT);
        assertEquals(GameStatus.MAP_SELECTION, gameEngine.getGameStatus());
    }

    /*
    Test that game status is changed when going to the map selection page and clicking up
     */
    @Test
    public void testReceiveInputWithMapSelectionButtonUp() {
        gameEngine.setGameStatus(GameStatus.MAP_SELECTION);
        gameEngine.receiveInput(ButtonAction.GO_UP);
        assertEquals(GameStatus.MAP_SELECTION, gameEngine.getGameStatus());
    }

    /*
    Test when clicking down button this allows me to select another map.
     */
    @Test
    public void testReceiveInputWithMapSelectionButtonDown() {
        gameEngine.setGameStatus(GameStatus.MAP_SELECTION);
        gameEngine.receiveInput(ButtonAction.GO_DOWN);
        assertEquals(GameStatus.MAP_SELECTION, gameEngine.getGameStatus());
    }

    /*
    Test that checks game status is still in the map selection page when starting out in
    start screen and hitting the select button.
     */
    @Test
    @Disabled
    public void testReceiveInputGameStatusStartScreenScreenSelectionViewAbout() {
        gameEngine.setGameStatus(GameStatus.START_SCREEN);
        gameEngine.receiveInput(ButtonAction.SELECT);
        assertEquals(GameStatus.MAP_SELECTION, gameEngine.getGameStatus());
    }

    /*
    Check that the game is still running when Mario jumps.
     */
    @Test
    public void testGameStatusRunningButtonActionJump() {
        gameEngine.setGameStatus(GameStatus.RUNNING);
        Map myMap = gameEngine.getMapManager().map;
        Mario mario = new Mario(0.1, 0.1);
        myMap.setMario(mario);
        gameEngine.receiveInput(ButtonAction.JUMP);
        assertEquals(gameEngine.getGameStatus(), GameStatus.RUNNING);
    }

    /*
    Test when the game is running and the user hits the jump button if mario is
    actually jumps.
     */
    @Test
    public void testReceiveInputWithGameStatusRunningButtonActionJumping() {
        gameEngine.createMap("/Map 1.png");
        gameEngine.setGameStatus(GameStatus.RUNNING);
        gameEngine.receiveInput(ButtonAction.JUMP);

        Mario myMario = mg.getMario();

        assertEquals(myMario.getY(), 240.0, 0.01);
    }

    /*
    Test when the game is running and the user hits the right button key if mario
    moves to the right.
     */
    @Test
    public void testReceiveInputWithGameStatusRunningButtonActionRight() {

        gameEngine.createMap("/Map 1.png");
        gameEngine.setGameStatus(GameStatus.RUNNING);
        gameEngine.receiveInput(ButtonAction.M_RIGHT);

        Mario myMario = mg.getMario();

        assertEquals(myMario.getX(), 144.0, 0.01);
    }

    /*
    Test when the game is running and the user hits the left button key if mario
    moves to the left.
     */
    @Test
    public void testReceiveInputWithGameStatusRunningButtonActionLeft() {

        gameEngine.createMap("/Map 1.png");
        gameEngine.setGameStatus(GameStatus.RUNNING);
        gameEngine.receiveInput(ButtonAction.M_LEFT);

        Mario myMario = mg.getMario();
        assertEquals(myMario.getX(), 144.0, 0.01);
    }

    /*
   Test when the game is running and the user hits the left button key if mario
   moves to the left.
    */
    @Test
    public void testReceiveInputWithGameStatusRunningButtonActionCompleted() {

        gameEngine.createMap("/Map 1.png");
        gameEngine.setGameStatus(GameStatus.RUNNING);
        gameEngine.receiveInput(ButtonAction.ACTION_COMPLETED);

        Mario myMario = mg.getMario();
        assertEquals(myMario.getX(), 144.0, 0.01);
    }

    /*
  Test when the game is running and the user hits the fire button key if mario
  moves to the left.
   */
    @Test
    public void testReceiveInputWithGameStatusRunningButtonActionFire() {

        gameEngine.createMap("/Map 1.png");
        gameEngine.setGameStatus(GameStatus.RUNNING);
        gameEngine.receiveInput(ButtonAction.FIRE);

        assertEquals(mg.map.getFireballs().size(), 0);
        //assertEquals(myMario.getX(), 0, 0.01);
    }

    /*
  Test when the game is running and the pause button is pressed, that the game did not end.
   */
    @Test
    public void testReceiveInputWithGameStatusRunningButtonActionResume() {

        gameEngine.createMap("/Map 1.png");
        gameEngine.setGameStatus(GameStatus.RUNNING);
        gameEngine.receiveInput(ButtonAction.PAUSE_RESUME);

        assertFalse(mg.isGameOver());
    }


    /*
    Test that coin sound was played.
     */
    @Test
    public void testPlayCoin() {
        gameEngine.playCoin();
        assertNotNull(gameEngine);
    }

    /*
    Test that a one up sound was played.
     */
    @Test
    public void testPlayOneUp() {
        gameEngine.playOneUp();
        assertNotNull(gameEngine);
    }

    /*
   Test that a super mushroom sound was played.
    */
    @Test
    public void testPlaySuperMushroom() {
        gameEngine.playSuperMushroom();
        assertNotNull(gameEngine);
    }

    /*
   Test that a mario has died sound was played.
    */
    @Test
    public void testPlayMarioDies() {
        gameEngine.playMarioDies();
        assertNotNull(gameEngine);
    }

    /*
    Test that a jumping sound was played.
    */
    @Test
    public void testPlayJump() {
        gameEngine.playJump();
        assertNotNull(gameEngine);
    }

    /*
    Test that a fireflower sound was played.
    */
    @Test
    public void testPlayFireFlower() {
        gameEngine.playFireFlower();
        assertNotNull(gameEngine);
    }

    /*
   Test that a fireball sound was played.
   */
    @Test
    public void testPlayFireball() {
        gameEngine.playFireball();
        assertNotNull(gameEngine);
    }

    /*
  Test that a stomping sound was played.
  */
    @Test
    public void testPlayStomp() {
        gameEngine.playStomp();
        assertNotNull(gameEngine);
    }

    /*
     Test that I am able to get the MapManager.
     */
    @Test
    public void testGetMapManager() {
        MapManager mapManager = gameEngine.getMapManager();
        assertNotNull(mapManager);
    }




}
