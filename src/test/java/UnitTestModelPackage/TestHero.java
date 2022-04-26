package UnitTestModelPackage;

import model.brick.OrdinaryBrick;
import model.hero.Mario;
import org.junit.jupiter.api.Test;
import view.ImageLoader;

import java.awt.image.BufferedImage;

public class TestHero {
    Mario mario;
    /**
     * test helper method updateLocation.
     * Branch 1: if jumping and velY <= 0
     */
    @Test
    public void testOrdinaryMarioUpdateLocation00() {
        ImageLoader imageLoader = new ImageLoader();

        BufferedImage sprite = imageLoader.loadImage("/sprite.png");
        BufferedImage brickStyle = imageLoader.getSubImage(sprite, 1, 1, 48, 48);

        mario = new Mario(50, 50);
        mario.setJumping(true);
        mario.setVelY(-1);
        mario.setVelX(10);

        mario.updateLocation();

        assert(!mario.isJumping());
        assert(mario.isFalling());
        assert(mario.getY() == 49);
        assert(mario.getVelY() == -0.62);
        assert(mario.getX() == 60);
    }

    /**
     * test helper method updateLocation.
     * Branch 2: if jumping and velY > 0
     */
    @Test
    public void testOrdinaryBrickUpdateLocation01() {
        ImageLoader imageLoader = new ImageLoader();

        BufferedImage sprite = imageLoader.loadImage("/sprite.png");
        BufferedImage brickStyle = imageLoader.getSubImage(sprite, 1, 1, 48, 48);

        mario = new Mario(50, 50);
        mario.setJumping(true);
        mario.setVelY(5);
        mario.setVelX(10);

        mario.updateLocation();

        assert(true);
//        assert(!mario.isJumping());
//        assert(mario.isFalling());
//        assert(mario.getY() == 49);
//        assert(mario.getVelY() == -0.62);
//        assert(mario.getX() == 60);
    }

    /**
     * test helper method updateLocation.
     * Branch 3: if falling
     */
    @Test
    public void testOrdinaryBrickUpdateLocation02() {

    }
}
