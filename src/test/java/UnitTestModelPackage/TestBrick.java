package UnitTestModelPackage;

import model.brick.GroundBrick;
import model.brick.OrdinaryBrick;
import model.brick.SurpriseBrick;
import model.prize.Coin;
import model.prize.Prize;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import view.Animation;
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
     * test that all fields of an OrdinaryBrick are appropriately
     * updated after calling the updateLocation method.
     * Bricks are never jumping or falling.
     */
    @Test
    public void testOrdinaryBrickUpdateLocation00() {
        ImageLoader imageLoader = new ImageLoader();

        BufferedImage sprite = imageLoader.loadImage("/sprite.png");
        BufferedImage brickStyle = imageLoader.getSubImage(sprite, 1, 1, 48, 48);

        ord = new OrdinaryBrick(50, 50, brickStyle);
        ord.setJumping(false);
        ord.setFalling(false);
        ord.setVelY(0);
        ord.setVelX(0);

        ord.updateLocation();

        assert(!ord.isJumping());
        assert(!ord.isFalling());
        assert(ord.getY() == 50);
        assert(ord.getVelY() == 0);
        assert(ord.getVelX() == 0);
        assert(ord.getX() == 50);
    }

    /**
     * test that all fields of an SurpriseBrick are appropriately
     * updated after calling the updateLocation method.
     * Bricks are never jumping or falling.
     */
    @Test
    public void testSurpriseBrickUpdateLocation() {
        ImageLoader imageLoader = new ImageLoader();

        BufferedImage sprite = imageLoader.loadImage("/sprite.png");
        BufferedImage brickStyle = imageLoader.getSubImage(sprite, 1, 1, 48, 48);

        ord = new OrdinaryBrick(50, 50, brickStyle);
        ord.setJumping(false);
        ord.setFalling(false);
        ord.setVelY(0);
        ord.setVelX(0);

        ord.updateLocation();

        assert(!ord.isJumping());
        assert(!ord.isFalling());
        assert(ord.getY() == 50);
        assert(ord.getVelY() == 0);
        assert(ord.getVelX() == 0);
        assert(ord.getX() == 50);
    }
}
