package UnitTestManagerPackage;

import manager.GameEngine;
import manager.MapManager;
import model.Map;
import model.brick.Brick;
import model.brick.GroundBrick;
import model.brick.OrdinaryBrick;
import model.enemy.Goomba;
import model.hero.Fireball;
import model.hero.Mario;
import model.prize.BoostItem;
import model.prize.Coin;
import model.prize.FireFlower;
import model.prize.Prize;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import view.ImageLoader;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;


public class TestMapManager {

    //blackbox - outputs and inputs, look at method names

    //integration based - testing how two classes are

    //unit test - just care about the output

    //integration - care about those out bound calls

    public GameEngine gameEngine;
    public MapManager mapManager;

    @BeforeEach
    public void setUpGameEngine() {

        gameEngine = new GameEngine();
        mapManager = new MapManager();
    }

    /*
    Test that a map has been created successfully with the image of Map 1.
     */
    @Test
    public void testMapCreationSuccessfully() {
        ImageLoader imageLoader = new ImageLoader();
        mapManager.createMap(imageLoader, "/Map 1.png");
        assertNotNull(mapManager.map);
    }

    /*
    Test that a map has been able to be reset by checking that location of mario has been set to (0, 0).
     */
    @Test
    public void testMapResetMapSuccessfully() {
        ImageLoader imageLoader = new ImageLoader();
        mapManager.createMap(imageLoader, "/Map 1.png");
        mapManager.resetCurrentMap(gameEngine);

        Mario resetMario = mapManager.getMario();

        double marioVelX = resetMario.getVelX();
        assertEquals(marioVelX, 0, 0.001);

        double marioVelY = resetMario.getVelX();
        assertEquals(marioVelY, 0, 0.001);
        //use mockito to check that a method has been called?
    }

    /*
    Test that I am able to acquire points throughout the game.
     */
    @Test
    public void testAcquirePoints() {
        ImageLoader imageLoader = new ImageLoader();
        mapManager.createMap(imageLoader, "/Map 1.png");
        Mario marioWithPoints = mapManager.getMario();
        mapManager.acquirePoints(5);
        assertEquals(marioWithPoints.getPoints(), 5);
    }

    /*
    Test that I am able to send a fireball in the  map for Mario to dodge.
   */
    @Test
    public void testFire() {
        ImageLoader imageLoader = new ImageLoader();
        mapManager.createMap(imageLoader, "/Map 1.png");
        Mario marioWithFire = mapManager.getMario();
        Fireball fireball = marioWithFire.fire();
        marioWithFire.fire();
        mapManager.fire(gameEngine);
        mapManager.map.addFireball(fireball);
        assert(mapManager.map.getFireballs().size() > 0);
    }

    /*
    Test that when a map is created, the game is not over.
    */
    @Test
    public void testIsGameOver() {
        ImageLoader imageLoader = new ImageLoader();

        mapManager.createMap(imageLoader, "/Map 1.png");
        mapManager.fire(gameEngine);

        assertFalse(mapManager.isGameOver());
    }

    /*
    Increase the user score and check that this update is reflected in the game.
     */
    @Test
    public void testGetScore() {
        ImageLoader imageLoader = new ImageLoader();
        mapManager.createMap(imageLoader, "/Map 1.png");
        mapManager.fire(gameEngine);
        Mario mario = mapManager.getMario();
        mario.acquirePoints(5);

        assertEquals(mapManager.getScore(), 5);
    }

    /*
    Change the remaining lives Mario has and check that this update is reflected
    throughout the game.
     */
    @Test
    public void testGetRemainingLives() {
        ImageLoader imageLoader = new ImageLoader();
        mapManager.createMap(imageLoader, "/Map 1.png");
        mapManager.fire(gameEngine);
        Mario mario = mapManager.getMario();
        mario.setRemainingLives(5);
        assertEquals(mapManager.getRemainingLives(), 5);
    }

    /*
    Update the number of coins Mario has and check that this update is propagated
    throughout the game.
     */
    @Test
    public void testGetCoins() {
        ImageLoader imageLoader = new ImageLoader();
        mapManager.createMap(imageLoader, "/Map 1.png");
        mapManager.fire(gameEngine);
        Mario mario = mapManager.getMario();
        mario.acquireCoin();
        mario.acquireCoin();
        mario.acquireCoin();
        assertEquals(mapManager.getCoins(), 3);
    }

    /*
    Test that a map is able to be drawn successfully.
     */
    @Test
    public void testDrawMap() {
        ImageLoader imageLoader = new ImageLoader();
        mapManager.createMap(imageLoader, "/Map 1.png");
        //mapManager.drawMap();

    }

    /*
    Test when Mario is not able to reach the finish line.
     */
    @Test
    public void testMarioDidNotPassMission() {
        ImageLoader imageLoader = new ImageLoader();
        mapManager.createMap(imageLoader, "/Map 1.png");
        mapManager.fire(gameEngine);
        Mario mario = mapManager.getMario();
        int result = mapManager.passMission();
        assertFalse(mapManager.map.getEndPoint().isTouched());
        assertEquals(result, -1);
    }

    /*
    Test when Mario is able to reach the finish line.
     */
    @Test
    public void testMarioDidPassMission() {
        ImageLoader imageLoader = new ImageLoader();
        mapManager.createMap(imageLoader, "/Map 1.png");
        mapManager.fire(gameEngine);
        Mario mario = mapManager.getMario();

        mario.setX(9482.0);
        mapManager.passMission();

        assertTrue(mapManager.map.getEndPoint().isTouched());
    }

    /*
   Test when the level ends, and the level should not end in this case because Mario was just initiailized.
     */
    @Test
    public void testEndLevel() {
        ImageLoader imageLoader = new ImageLoader();
        mapManager.createMap(imageLoader, "/Map 1.png");
        mapManager.fire(gameEngine);
        Mario mario = mapManager.getMario();

        assertFalse(mapManager.endLevel());

    }

    /*
    Test to check whether there are collisions with an uninitialized map.
     */
    @Test
    public void testCheckCollisionsWithMapNull() {
        BufferedImage coinStyle = gameEngine.getImageLoader().loadImage("/sprite.png");

        coinStyle = gameEngine.getImageLoader().getSubImage(coinStyle, 1, 5, 48, 48);
        Coin firstCoin = new Coin(50, 50, coinStyle, 1);
        Coin secondCoin = new Coin(0.5, 0.5, coinStyle , 5);

        assertNull(mapManager.map);
    }

    @Test
    public void testCheckCollisionWithEnemiesFireballsAdded() {
        ImageLoader imageLoader = new ImageLoader();
        BufferedImage coinStyle = gameEngine.getImageLoader().loadImage("/sprite.png");

        coinStyle = gameEngine.getImageLoader().getSubImage(coinStyle, 1, 5, 48, 48);
        Coin firstCoin = new Coin(50, 50, coinStyle, 1);
        Coin secondCoin = new Coin(0.5, 0.5, coinStyle , 5);

        Fireball fireball = new Fireball(50, 50, coinStyle, false);
        Goomba goomba = new Goomba(50, 50, coinStyle);

        mapManager.createMap(imageLoader, "/Map 1.png");

        mapManager.map.addEnemy(goomba);
        mapManager.map.addFireball(fireball);

        mapManager.map.addRevealedPrize(firstCoin);
        mapManager.map.addRevealedPrize(secondCoin);

        BufferedImage sprite = imageLoader.loadImage("/sprite.png");
        BufferedImage brickStyle = imageLoader.getSubImage(sprite, 1, 1, 48, 48);
        OrdinaryBrick ordinaryBrick = new OrdinaryBrick(50, 50, brickStyle);
        mapManager.map.addBrick(ordinaryBrick);

        mapManager.checkCollisions(gameEngine);
        assertNotNull(mapManager.map);

    }

    /*
     Test to check whether there are collisions with an initialized map.
     */
    @Test
    public void testCheckCollisionsWithMapNotNull() {
        ImageLoader imageLoader = new ImageLoader();
        mapManager.createMap(imageLoader, "/Map 1.png");

        BufferedImage coinStyle = gameEngine.getImageLoader().loadImage("/sprite.png");
        coinStyle = gameEngine.getImageLoader().getSubImage(coinStyle, 1, 5, 48, 48);

        FireFlower prize = new FireFlower(50, 50, coinStyle);
        Coin coin = new Coin(0.5, 0.5, coinStyle , 5);
        mapManager.map.addRevealedPrize(prize);
        mapManager.map.addRevealedPrize(coin);

        BufferedImage sprite = imageLoader.loadImage("/sprite.png");
        BufferedImage brickStyle = imageLoader.getSubImage(sprite, 1, 1, 48, 48);
        OrdinaryBrick ordinaryBrick = new OrdinaryBrick(50, 50, brickStyle);
        mapManager.map.addBrick(ordinaryBrick);

        mapManager.checkCollisions(gameEngine);
        assertTrue(mapManager.map.getAllBricks().size() > 0);
    }

    /*
    Test whether a revealed brick is able to be added successfully to the map.
     */
    @Test
    public void testAddRevealedBrick() {
        ImageLoader imageLoader = new ImageLoader();
        BufferedImage sprite = imageLoader.loadImage("/sprite.png");
        BufferedImage brickStyle = imageLoader.getSubImage(sprite, 1, 1, 48, 48);

        OrdinaryBrick ordinaryBrick = new OrdinaryBrick(50, 50, brickStyle);
        mapManager.createMap(imageLoader, "/Map 1.png");
        mapManager.addRevealedBrick(ordinaryBrick);
        assert(mapManager.map.getAllBricks().size() > 0);
    }

    /*
    Test whether we are able to update the playing time for the game.
     */
    @Test
    public void testUpdateTime() {
        ImageLoader imageLoader = new ImageLoader();
        mapManager.createMap(imageLoader, "/Map 1.png");
        mapManager.updateTime();
        assertEquals(mapManager.getRemainingTime(), 399);
    }


}
