package UnitTestModelPackage;

import manager.GameEngine;

import model.brick.GroundBrick;
import model.brick.OrdinaryBrick;
import model.brick.SurpriseBrick;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import view.ImageLoader;

import java.awt.image.BufferedImage;

/**
 * GroundBrick, OrdinaryBrick, Pipe, and SurpriseBrick
 * are model classes that extend abstract class Brick.
 * Only classes OrdinaryBrick and SurpriseBrick contain
 * methods other than constructors and setter/getters
 * that are tested in this test class.
 */
public class TestBrick {
    OrdinaryBrick ord;
    SurpriseBrick surp;

    /**
     * test that all fields of a brick are appropriately
     * updated after calling the reveal method.
     */
    @Test
    public void testOrdinaryBrickReveal00() {
        GameEngine eng = new GameEngine();
        ImageLoader imageLoader = new ImageLoader();
        BufferedImage sprite = imageLoader.loadImage("/sprite.png");
        BufferedImage brickStyle = imageLoader.getSubImage(sprite, 1, 1, 48, 48);

        ord = new OrdinaryBrick(50, 50, brickStyle);
        ord.setJumping(true);
        ord.setVelY(10);
        ord.reveal(eng);

        //check if ord sets correct for both jumping and falling
        assert(ord.getVelX() == 0);
        assert(ord.getVelY() == (10-0.38));
        assert(ord.getX() == 50);
        assert(ord.getY() == 60);
    }

    /**
     * Todo: make this test interaction based testing
     * check that correct changes being made to mapManager object
     */
    @Test
    public void testOrdinaryBrickReveal01() {
        GameEngine eng = new GameEngine();
        ImageLoader imageLoader = new ImageLoader();
        BufferedImage sprite = imageLoader.loadImage("/sprite.png");
        BufferedImage brickStyle = imageLoader.getSubImage(sprite, 1, 1, 48, 48);

        ord = new OrdinaryBrick(50, 50, brickStyle);
        ord.reveal(eng);

        //check if ord sets correct location
    }

    /**
     * test helper method updateLocation
     */
    @Test
    public void testOrdinaryBrickUpdateLocation() {

    }

    @Test
    public void testOrdinaryBrickAnimate() {

    }

    /**
     * Might be better for interaction-based testing,
     * because function primarily only changes given
     * game engine object.
     * Have to run main to get GameEngine object,
     * opportunity for mock-testing?
     */
    @Test
    public void testSurpriseBrickReveal() {

    }

    @Test
    public void testSurpriseBrickUpdateLocation() {

    }
}
