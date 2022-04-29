package UnitTestModelPackage;

import manager.GameEngine;
import model.hero.Mario;
import model.prize.Coin;
import model.prize.FireFlower;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import view.ImageLoader;

import java.awt.image.BufferedImage;

public class TestPrize {
    public static GameEngine eng;

    @BeforeAll
    static void setUpGameEngine() {
        eng = new GameEngine();
    }

    /**
     * Test Coin method onTouch.
     * Todo: interaction based testing
     * Branch 1: not yet acquired
     */
    @Test
    public void testCoinOnTouch00() {
        Mario mario = new Mario(60, 60);
        ImageLoader imageLoader = new ImageLoader();
        BufferedImage sprite = imageLoader.loadImage("/sprite.png");
        BufferedImage coinStyle = imageLoader.getSubImage(sprite, 1, 5, 48, 48);
        Coin coin = new Coin(50, 50, coinStyle, 5);

        int startPoints = mario.getPoints();

        coin.onTouch(mario, eng);

        assert(mario.getPoints() == startPoints + 5);
    }

    /**
     * test Coin method onTouch.
     * Branch 2: acquired
     */
    @Test
    public void testCoinOnTouch01() {
        Mario mario = new Mario(60, 60);
        ImageLoader imageLoader = new ImageLoader();
        BufferedImage sprite = imageLoader.loadImage("/sprite.png");
        BufferedImage coinStyle = imageLoader.getSubImage(sprite, 1, 5, 48, 48);
        Coin coin = new Coin(50, 50, coinStyle, 5);

        int startPoints = mario.getPoints();

        coin.onTouch(mario, eng);
        assert(mario.getPoints() == startPoints + 5);
        startPoints+=5;

        coin.onTouch(mario, eng);
        assert(mario.getPoints() == startPoints);
    }

    /**
     * test Coin updateLocation method.
     * Branch 1: not yet revealed.
     */
    @Test
    public void testCoinUpdateLocation00() {
        ImageLoader imageLoader = new ImageLoader();
        BufferedImage sprite = imageLoader.loadImage("/sprite.png");
        BufferedImage coinStyle = imageLoader.getSubImage(sprite, 1, 5, 48, 48);
        Coin coin = new Coin(50, 50, coinStyle, 5);

        assert(coin.getY() == 50);

        coin.updateLocation();

        assert(coin.getY() == 50);
    }

    /**
     * test Coin updateLocation method.
     * Branch 2: revealed.
     */
    @Test
    public void testCoinUpdateLocation01() {
        ImageLoader imageLoader = new ImageLoader();
        BufferedImage sprite = imageLoader.loadImage("/sprite.png");
        BufferedImage coinStyle = imageLoader.getSubImage(sprite, 1, 5, 48, 48);
        Coin coin = new Coin(50, 50, coinStyle, 5);

        assert(coin.getY() == 50);

        coin.reveal();
        coin.updateLocation();

        assert(coin.getY() == 45);
    }

    /**
     * test FireFlower class reveal method.
     */
    @Test
    public void testFireFlowerReveal() {
        ImageLoader imageLoader = new ImageLoader();
        BufferedImage sprite = imageLoader.loadImage("/sprite.png");
        BufferedImage flowerStyle = imageLoader.getSubImage(sprite, 4, 5, 48, 48);
        FireFlower fireFlower = new FireFlower(50, 50, flowerStyle);

        fireFlower.reveal();

        assert(fireFlower.getY() == 2);
    }
}
