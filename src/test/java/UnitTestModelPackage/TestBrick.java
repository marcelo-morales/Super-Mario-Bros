package UnitTestModelPackage;

import manager.GameEngine;

import model.brick.GroundBrick;
import model.brick.OrdinaryBrick;
import model.brick.SurpriseBrick;
import model.prize.Coin;
import model.prize.Prize;
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
     * Branch 1, where mario is super, so reveal should
     * update brick location, so brick location should be updated.
     */
    @Test
    public void testOrdinaryBrickReveal00() {
        ImageLoader imageLoader = new ImageLoader();

        GameEngine eng = new GameEngine();
        eng.getMapManager().createMap(imageLoader, "/Map 2.png");
        eng.getMapManager().getMario().getMarioForm().setSuper(true);

        BufferedImage sprite = imageLoader.loadImage("/sprite.png");
        BufferedImage brickStyle = imageLoader.getSubImage(sprite, 1, 1, 48, 48);

        ord = new OrdinaryBrick(50, 50, brickStyle);
        assert(ord.getX() == 50);
        assert(ord.getY() == 50);

        ord.reveal(eng);

        assert(ord.getX() == 23);
        assert(ord.getY() == 23);
    }

    /**
     * test that all fields of a brick are appropriately
     * updated after calling the reveal method.
     * Branch 1, where mario is not super, so reveal
     * should return null, so nothing should be updated.
     */
    @Test
    public void testOrdinaryBrickReveal01() {
        ImageLoader imageLoader = new ImageLoader();

        GameEngine eng = new GameEngine();
        eng.getMapManager().createMap(imageLoader, "/Map 2.png");
        eng.getMapManager().getMario().getMarioForm().setSuper(false);

        BufferedImage sprite = imageLoader.loadImage("/sprite.png");
        BufferedImage brickStyle = imageLoader.getSubImage(sprite, 1, 1, 48, 48);

        ord = new OrdinaryBrick(50, 50, brickStyle);
        assert(ord.getX() == 50);
        assert(ord.getY() == 50);

        ord.reveal(eng);

        assert(ord.getX() == 50);
        assert(ord.getY() == 50);
    }

    /**
     * Todo: make this test interaction based testing?
     * check that correct changes being made to mapManager object
     * Does that count as integration testing?
     */
    @Test
    public void testOrdinaryBrickReveal02() {
        ImageLoader imageLoader = new ImageLoader();

        GameEngine eng = new GameEngine();
        eng.getMapManager().createMap(imageLoader, "/Map 2.png");
        eng.getMapManager().getMario().getMarioForm().setSuper(false);

        BufferedImage sprite = imageLoader.loadImage("/sprite.png");
        BufferedImage brickStyle = imageLoader.getSubImage(sprite, 1, 1, 48, 48);

        ord = new OrdinaryBrick(50, 50, brickStyle);
        assert(ord.getX() == 50);
        assert(ord.getY() == 50);

        ord.reveal(eng);

        assert(true);
    }

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

    @Test
    public void testOrdinaryBrickSetAnimation() {

    }

    @Test
    public void testOrdinaryBrickAnimate() {

    }

    /**
     * test that all fields of a SurpriseBrick are appropriately
     * updated after calling the reveal method.
     * Branch 1, where mario is super, so reveal should
     * update brick location, so brick location should be updated.
     */
    @Test
    public void testSurpriseBrickReveal() {
        GameEngine engine = new GameEngine();

        BufferedImage oldStyle = engine.getImageLoader().loadImage("/sprite.png");
        oldStyle = engine.getImageLoader().getSubImage(oldStyle, 2, 1, 48, 48);
        BufferedImage newStyle = engine.getImageLoader().loadImage("/sprite.png");
        newStyle = engine.getImageLoader().getSubImage(newStyle, 1, 2, 48, 48);
        BufferedImage coinStyle = engine.getImageLoader().loadImage("/sprite.png");
        coinStyle = engine.getImageLoader().getSubImage(coinStyle, 1, 5, 48, 48);
        Prize prize = new Coin(50, 50, coinStyle, 1);

        surp = new SurpriseBrick(50, 50, oldStyle, prize);
        assert(surp.getX() == 50);
        assert(surp.getY() == 50);

        Prize returned = surp.reveal(engine);

        //assert that brick is now empty
        assert(surp.isEmpty());
        //assert that newStyle matches surp's style
        assert(newStyle.equals(surp.getStyle()));
        //assert that prize is returned
        assert(returned.equals(prize));
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
