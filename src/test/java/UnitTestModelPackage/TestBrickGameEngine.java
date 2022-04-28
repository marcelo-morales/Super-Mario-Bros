package UnitTestModelPackage;

import manager.GameEngine;
import model.brick.OrdinaryBrick;
import model.brick.SurpriseBrick;
import model.prize.Coin;
import model.prize.Prize;
import org.junit.jupiter.api.Test;
import view.Animation;
import view.ImageLoader;

import java.awt.image.BufferedImage;

import static UnitTestModelPackage.CompareImages.compareImages;

public class TestBrickGameEngine {
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
     * test that all fields of a SurpriseBrick are appropriately
     * updated after calling the reveal method.
     * Branch 1, where prize is not null.
     */
    @Test
    public void testSurpriseBrickReveal00() {
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
        assert(compareImages(newStyle, surp.getStyle()));
        //assert that prize is returned
        assert(returned.equals(prize));
    }

    /**
     * test that all fields of a SurpriseBrick are appropriately
     * updated after calling the reveal method.
     * Branch 2, where prize is null.
     */
    @Test
    public void testSurpriseBrickReveal01() {
        GameEngine engine = new GameEngine();

        BufferedImage oldStyle = engine.getImageLoader().loadImage("/sprite.png");
        oldStyle = engine.getImageLoader().getSubImage(oldStyle, 2, 1, 48, 48);
        BufferedImage newStyle = engine.getImageLoader().loadImage("/sprite.png");
        newStyle = engine.getImageLoader().getSubImage(newStyle, 1, 2, 48, 48);
        Prize prize = null;

        surp = new SurpriseBrick(50, 50, oldStyle, prize);
        assert(surp.getX() == 50);
        assert(surp.getY() == 50);

        Prize returned = surp.reveal(engine);

        //assert that brick is now empty
        assert(surp.isEmpty());
        //assert that newStyle matches surp's style
        assert(compareImages(newStyle, surp.getStyle()));
        //assert that prize is returned
        assert(returned == null);
    }

    /**
     * test OrdinaryBrick animate.
     * Branch 1, where breaking is true.
     */
    @Test
    public void testOrdinaryBrickAnimate() {
        ImageLoader imageLoader = new ImageLoader();
        BufferedImage[] leftFrames = imageLoader.getBrickFrames();
        Animation animation = new Animation(leftFrames, leftFrames);
        BufferedImage anim = animation.animate(3, true);

        GameEngine eng = new GameEngine();
        eng.getMapManager().createMap(imageLoader, "/Map 2.png");
        eng.getMapManager().getMario().getMarioForm().setSuper(true);

        BufferedImage sprite = imageLoader.loadImage("/sprite.png");
        BufferedImage brickStyle = imageLoader.getSubImage(sprite, 1, 1, 48, 48);
        ord = new OrdinaryBrick(50, 50, brickStyle);
        int oldFrames = ord.getFrames();
        ord.setBreakable(true);
        ord.reveal(eng);

        ord.animate();

        assert(ord.getFrames() == (oldFrames - 1));
        assert(compareImages(anim, ord.getStyle()));
    }
}
