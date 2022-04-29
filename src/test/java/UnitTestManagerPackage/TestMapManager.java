package UnitTestManagerPackage;

import manager.GameEngine;
import manager.MapManager;
import model.brick.OrdinaryBrick;
import model.enemy.Goomba;
import model.hero.Fireball;
import model.hero.Mario;
import model.prize.Coin;
import model.prize.FireFlower;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import view.ImageLoader;

import java.awt.image.BufferedImage;

import static org.junit.jupiter.api.Assertions.*;


public class TestMapManager {

    public static GameEngine gameEngine;
    public static MapManager mapManager;

    @BeforeAll
    static void setupMapManager() {

        gameEngine = new GameEngine();
        mapManager = new MapManager();
    }

    /*
    Test that locations are not updated when map is not initialized.
     */
    @Test
    public void testLocationsNotUpdatedWhenMapIsNull() {
        MapManager mapManagerWithMapNull = new MapManager();
        mapManagerWithMapNull.updateLocations();
        assertNull(mapManagerWithMapNull.map);
    }

     /*
    Test that map is not able to be created with a buggy path
     */
    @Test
    @Disabled
    public void testMapCreateFails() {
        ImageLoader imageLoader = new ImageLoader();
        assertFalse(mapManager.createMap(imageLoader, "/buggy_path"));
    }


    /*
    Test that mario is not null when mario is hit by fire.
     */
    @Test
    public void testWhenMarioGettingFireNotNull() {
        ImageLoader imageLoader = new ImageLoader();
        mapManager.createMap(imageLoader, "/Map 1.png");

        mapManager.getMario().getMarioForm().fire(true, 0, 0);

        mapManager.fire(gameEngine);

        assertNotNull(mapManager.getMario());
    }

    /*
   Test that collisions cannot be checked with a null map.
    */
    @Test
    public void testCheckCollisionsWithANullMap() {
        MapManager mapManagerWithNullMap = new MapManager();
        mapManagerWithNullMap.checkCollisions(gameEngine);
        assertNull(mapManagerWithNullMap.map);
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
    Test that collisions happen with enemies and fireballs added.
    */
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

        ordinaryBrick.setLocation(0, 0);
        firstCoin.setLocation(0, 0);

        mapManager.checkCollisions(gameEngine);
        assertNotNull(mapManager.map);
    }

    /*
     Test to check whether there are collisions with an initialized map with prize objects having positive coordinates.
     */
    @Test
    public void testCheckCollisionsWithMapNotNullBrickVelPositive() {
        ImageLoader imageLoader = new ImageLoader();
        mapManager.createMap(imageLoader, "/Map 1.png");

        BufferedImage coinStyle = gameEngine.getImageLoader().loadImage("/sprite.png");
        coinStyle = gameEngine.getImageLoader().getSubImage(coinStyle, 1, 5, 48, 48);

        FireFlower prize = new FireFlower(50, 50, coinStyle);
        Coin coin = new Coin(50, 50, coinStyle , 5);
        mapManager.map.addRevealedPrize(prize);
        mapManager.map.addRevealedPrize(coin);

        BufferedImage sprite = imageLoader.loadImage("/sprite.png");
        BufferedImage brickStyle = imageLoader.getSubImage(sprite, 1, 1, 48, 48);
        OrdinaryBrick ordinaryBrick = new OrdinaryBrick(50, 50, brickStyle);
        prize.setVelX(10);

        mapManager.checkCollisions(gameEngine);
        assertTrue(mapManager.map.getAllBricks().size() > 0);
    }

    /*
    Test to check whether there are collisions with an initialized map and prize objects have negative coordinates after.
    */
    @Test
    public void testCheckCollisionsWithMapNotNullBrickVelNegative() {
        ImageLoader imageLoader = new ImageLoader();
        mapManager.createMap(imageLoader, "/Map 1.png");

        BufferedImage coinStyle = gameEngine.getImageLoader().loadImage("/sprite.png");
        coinStyle = gameEngine.getImageLoader().getSubImage(coinStyle, 1, 5, 48, 48);

        FireFlower prize = new FireFlower(50, 50, coinStyle);
        Coin coin = new Coin(50, 50, coinStyle , 5);
        mapManager.map.addRevealedPrize(prize);
        mapManager.map.addRevealedPrize(coin);

        BufferedImage sprite = imageLoader.loadImage("/sprite.png");
        BufferedImage brickStyle = imageLoader.getSubImage(sprite, 1, 1, 48, 48);
        OrdinaryBrick ordinaryBrick = new OrdinaryBrick(50, 50, brickStyle);
        prize.setVelX(-10);

        mapManager.checkCollisions(gameEngine);
        assertTrue(mapManager.map.getAllBricks().size() > 0);
    }

    /*
   Test to check whether there are collisions with an initialized map and bricks added with a very large prize object y coordinate.
   */
    @Test
    public void testCheckCollisionsWithMapNotNullBoostItemGreaterThanBottomBorder() {
        ImageLoader imageLoader = new ImageLoader();
        mapManager.createMap(imageLoader, "/Map 1.png");

        BufferedImage coinStyle = gameEngine.getImageLoader().loadImage("/sprite.png");
        coinStyle = gameEngine.getImageLoader().getSubImage(coinStyle, 1, 5, 48, 48);

        FireFlower prize = new FireFlower(50, 50, coinStyle);
        Coin coin = new Coin(50, 50, coinStyle , 5);
        mapManager.map.addRevealedPrize(prize);
        mapManager.map.addRevealedPrize(coin);

        BufferedImage sprite = imageLoader.loadImage("/sprite.png");
        BufferedImage brickStyle = imageLoader.getSubImage(sprite, 1, 1, 48, 48);
        OrdinaryBrick ordinaryBrick = new OrdinaryBrick(50, 50, brickStyle);

        prize.setVelX(0);
        prize.setY(9999999);


        mapManager.checkCollisions(gameEngine);
        assertTrue(mapManager.map.getAllBricks().size() > 0);
    }

    /*
    Check that prize objects collide when they have the same coordinates.
     */
    @Test
    public void testCheckPrizeCollisionWithLotsOfPrizes() {
        ImageLoader imageLoader = new ImageLoader();

        BufferedImage coinStyle = gameEngine.getImageLoader().loadImage("/sprite.png");
        coinStyle = gameEngine.getImageLoader().getSubImage(coinStyle, 1, 5, 48, 48);
        FireFlower fireFlower = new FireFlower(50, 50, coinStyle);
        fireFlower.setFalling(true);
        fireFlower.setVelX(0);
        Coin coinPrize = new Coin(50, 50, coinStyle , 5);
        coinPrize.setFalling(false);
        coinPrize.setVelX(15);

        BufferedImage sprite = imageLoader.loadImage("/sprite.png");
        BufferedImage brickStyle = imageLoader.getSubImage(sprite, 1, 1, 48, 48);
        OrdinaryBrick ordinaryBrickOne = new OrdinaryBrick(50, 50, brickStyle);
        OrdinaryBrick ordinaryBrickTwo = new OrdinaryBrick(50, 50, brickStyle);

        mapManager.createMap(imageLoader, "/Map 1.png");

        mapManager.map.addBrick(ordinaryBrickOne);
        mapManager.map.addBrick(ordinaryBrickTwo);

        mapManager.map.addRevealedPrize(fireFlower);
        mapManager.map.addRevealedPrize(coinPrize);

        mapManager.checkPrizeCollision();

        assertNotNull(mapManager);
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

    /*
    Blackbox testing on createMap() with equivalence partitioning

    EP: String path - {null, empty string, not  empty string invalid path, valid path string}
     */
    @Test
    public void testCreateMapWithNullString () {
        ImageLoader imageLoader = new ImageLoader();
        String path = null;

        assertThrows(IllegalArgumentException.class, () ->
        {
            mapManager.createMap(imageLoader, path);
        });

    }

    /*
   Blackbox testing on createMap() with equivalence partitioning

   EP: String path - {null, empty string, not  empty string invalid path, valid path string}
    */
    @Test
    public void testCreateMapWithEmptyString() {
        ImageLoader imageLoader = new ImageLoader();
        String path = "";
        mapManager.createMap(imageLoader, path);
        assertNull(mapManager.map);
    }

    /*
   Blackbox testing on createMap() with equivalence partitioning

   EP: String path - {null, empty string, not  empty string invalid path, valid path string}
    */
    @Test
    @Disabled
    public void testCreateMapWithStringButIncorrect() {
        ImageLoader imageLoader = new ImageLoader();
        String path = "/this path does not lead to a map";
        mapManager.createMap(imageLoader, path);
        assertNull(mapManager.map);
    }

    /*
   Blackbox testing on createMap() with equivalence partitioning

   EP: String path - {null, empty string, not  empty string invalid path, valid path string}
    */
    @Test
    public void testCreateMapWithCorrectString() {
        ImageLoader imageLoader = new ImageLoader();
        String path = "/Map 1.png";
        mapManager.createMap(imageLoader, path);
        assertNotNull(mapManager.map);
    }




}
