package UnitTestModelPackage;

import manager.GameEngine;
import model.hero.Mario;
import model.hero.MarioForm;
import model.prize.FireFlower;
import model.prize.OneUpMushroom;
import model.prize.SuperMushroom;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import view.Animation;
import view.ImageLoader;

import java.awt.*;
import java.awt.image.BufferedImage;

public class TestPrizeGameEngine {
    public Mario mario;
    public ImageLoader imageLoader;
    public static GameEngine eng;

    @BeforeAll
    static void setUp() {
        eng = new GameEngine();
    }

    /**
     * test FireFlower class onTouch method.
     * Integration test to ensure that method onTouch
     * interacts correctly with given Mario instance.
     * Branch 1: !mario.getMarioForm().isFire()
     */
    @Test
    public void testFireFlowerOnTouch00() {
        imageLoader = new ImageLoader();
        BufferedImage sprite = imageLoader.loadImage("/sprite.png");
        BufferedImage flowerStyle = imageLoader.getSubImage(sprite, 4, 5, 48, 48);
        FireFlower fireFlower = new FireFlower(50, 50, flowerStyle);

        BufferedImage[] leftFrames = imageLoader.getLeftFrames(MarioForm.FIRE);
        BufferedImage[] rightFrames = imageLoader.getRightFrames(MarioForm.FIRE);
        Animation animation = new Animation(leftFrames, rightFrames);
        MarioForm newForm = new MarioForm(animation, true, true);

        Mario mario = new Mario(60, 60);
        int startPoints = mario.getPoints();

        fireFlower.setPoint(1);
        fireFlower.onTouch(mario, eng);

        assert(mario.getPoints() == startPoints + 1);
        assert(mario.getDimension().width == 48);
        assert(mario.getDimension().height == 96);
    }

    /**
     * test OneUpMushroom class onTouch method.
     * Integration test to check that onTouch method
     * interacts correctly with given Mario instance.
     */
    @Test
    public void testOneUpMushroomOnTouch() {
        ImageLoader imageLoader = new ImageLoader();
        BufferedImage sprite = imageLoader.loadImage("/sprite.png");;
        BufferedImage mushroomStyle = imageLoader.getSubImage(sprite, 3, 5, 48, 48);
        OneUpMushroom mushroom = new OneUpMushroom(50, 50, mushroomStyle);

        mario = new Mario(60, 60);
        int startLives = mario.getRemainingLives();
        mushroom.onTouch(mario, eng);
        assert(mario.getRemainingLives() == startLives + 1);
    }

    /**
     * test SuperMushroom class onTouch method.
     * Integration test to ensure that method onTouch
     * interacts correctly with given Mario instance.
     * Branch 1: !mario.getMarioForm().isFire()
     */
    @Test
    public void testSuperMushroomOnTouch() {
        imageLoader = new ImageLoader();
        BufferedImage sprite = imageLoader.loadImage("/sprite.png");
        BufferedImage flowerStyle = imageLoader.getSubImage(sprite, 4, 5, 48, 48);
        SuperMushroom mushroom = new SuperMushroom(50, 50, flowerStyle);

        BufferedImage[] leftFrames = imageLoader.getLeftFrames(MarioForm.FIRE);
        BufferedImage[] rightFrames = imageLoader.getRightFrames(MarioForm.FIRE);
        Animation animation = new Animation(leftFrames, rightFrames);
        MarioForm newForm = new MarioForm(animation, true, true);

        Mario mario = new Mario(60, 60);
        int startPoints = mario.getPoints();

        mushroom.setPoint(1);
        mushroom.onTouch(mario, eng);

        assert(mario.getPoints() == startPoints + 1);
        assert(mario.getDimension().width == 48);
        assert(mario.getDimension().height == 96);
    }
}
