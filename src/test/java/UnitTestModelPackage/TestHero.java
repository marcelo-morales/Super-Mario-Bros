package UnitTestModelPackage;

import manager.Camera;
import model.brick.OrdinaryBrick;
import model.hero.Fireball;
import model.hero.Mario;
import model.hero.MarioForm;
import org.junit.jupiter.api.Test;
import view.Animation;
import view.ImageLoader;

import java.awt.image.BufferedImage;

import static view.CompareImages.compareImages;

public class TestHero {
    Mario mario;
    MarioForm mariof;
    /**
     * test helper method updateLocation.
     * Branch 1: if jumping and velY <= 0
     */
    @Test
    public void testMarioUpdateLocation00() {
        ImageLoader imageLoader = new ImageLoader();

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
    public void testMarioUpdateLocation01() {
        ImageLoader imageLoader = new ImageLoader();

        mario = new Mario(50, 50);
        mario.setJumping(true);
        mario.setFalling(false);
        mario.setVelY(5);
        mario.setVelX(10);

        mario.updateLocation();

        assert(mario.isJumping());
        assert(!mario.isFalling());
        assert(mario.getY() == 45.38);
        assert(mario.getVelY() == 4.62);
        assert(mario.getX() == 60);
    }

    /**
     * Test MarioForm getCurrentStyle.
     * Branch 1: movingInY && toRight
     */
    @Test
    public void testMarioFormGetCurrentStyle00() {
        ImageLoader imageLoader = new ImageLoader();

        BufferedImage[] leftFrames = imageLoader.getLeftFrames(MarioForm.SMALL);
        BufferedImage[] rightFrames = imageLoader.getRightFrames(MarioForm.SMALL);
        Animation animation = new Animation(leftFrames, rightFrames);

        BufferedImage style = animation.getRightFrames()[0];

        mariof = new MarioForm(animation, false, false);
        BufferedImage retStyle = mariof.getCurrentStyle(true, false, true);

        assert(compareImages(retStyle, style));
    }

    /**
     * Test MarioForm getCurrentStyle.
     * Branch 1: movingInY && !toRight
     */
    @Test
    public void testMarioFormGetCurrentStyle01() {
        ImageLoader imageLoader = new ImageLoader();

        BufferedImage[] leftFrames = imageLoader.getLeftFrames(MarioForm.SMALL);
        BufferedImage[] rightFrames = imageLoader.getRightFrames(MarioForm.SMALL);
        Animation animation = new Animation(leftFrames, rightFrames);

        BufferedImage style = animation.getLeftFrames()[0];

        mariof = new MarioForm(animation, false, false);
        BufferedImage retStyle = mariof.getCurrentStyle(false, false, true);

        assert(compareImages(retStyle, style));
    }

    /**
     * Test MarioForm getCurrentStyle.
     * Branch 1: movingInX, !movingInY && !toRight
     */
    @Test
    public void testMarioFormGetCurrentStyle02() {
        ImageLoader imageLoader = new ImageLoader();

        BufferedImage[] leftFrames = imageLoader.getLeftFrames(MarioForm.SMALL);
        BufferedImage[] rightFrames = imageLoader.getRightFrames(MarioForm.SMALL);
        Animation animation = new Animation(leftFrames, rightFrames);

        BufferedImage style = animation.animate(5, true);

        mariof = new MarioForm(animation, false, false);
        BufferedImage retStyle = mariof.getCurrentStyle(false, true, false);

        assert(compareImages(retStyle, style));
    }

    /**
     * Test MarioForm getCurrentStyle.
     * Branch 1: !movingInX, !movingInY, toRight
     */
    @Test
    public void testMarioFormGetCurrentStyle03() {
        ImageLoader imageLoader = new ImageLoader();

        BufferedImage[] leftFrames = imageLoader.getLeftFrames(MarioForm.SMALL);
        BufferedImage[] rightFrames = imageLoader.getRightFrames(MarioForm.SMALL);
        Animation animation = new Animation(leftFrames, rightFrames);

        BufferedImage style = animation.getRightFrames()[1];

        mariof = new MarioForm(animation, false, false);
        BufferedImage retStyle = mariof.getCurrentStyle(true, false, false);

        assert(compareImages(retStyle, style));
    }

    /**
     * Test MarioForm getCurrentStyle.
     * Branch 1: !movingInX, !movingInY, !toRight
     */
    @Test
    public void testMarioFormGetCurrentStyle04() {
        ImageLoader imageLoader = new ImageLoader();

        BufferedImage[] leftFrames = imageLoader.getLeftFrames(MarioForm.SMALL);
        BufferedImage[] rightFrames = imageLoader.getRightFrames(MarioForm.SMALL);
        Animation animation = new Animation(leftFrames, rightFrames);

        BufferedImage style = animation.getLeftFrames()[1];

        mariof = new MarioForm(animation, false, false);
        BufferedImage retStyle = mariof.getCurrentStyle(false, false, false);

        assert(compareImages(retStyle, style));
    }

    /**
     * test MarioForm class fire method.
     * Branch 1: isFire
     */
    @Test
    public void testMarioFormFire00() {
        ImageLoader imageLoader = new ImageLoader();
        BufferedImage[] leftFrames = imageLoader.getLeftFrames(MarioForm.SMALL);
        BufferedImage[] rightFrames = imageLoader.getRightFrames(MarioForm.SMALL);
        Animation animation = new Animation(leftFrames, rightFrames);

        BufferedImage style = animation.getLeftFrames()[1];

        mariof = new MarioForm(animation, false, true);
        Fireball fireball = mariof.fire(true, 10, 10);

        assert(fireball != null);
    }

    /**
     * test MarioForm class fire method.
     * Branch 2: !isFire
     */
    @Test
    public void testMarioFormFire01() {
        ImageLoader imageLoader = new ImageLoader();
        BufferedImage[] leftFrames = imageLoader.getLeftFrames(MarioForm.SMALL);
        BufferedImage[] rightFrames = imageLoader.getRightFrames(MarioForm.SMALL);
        Animation animation = new Animation(leftFrames, rightFrames);

        BufferedImage style = animation.getLeftFrames()[1];

        mariof = new MarioForm(animation, false, false);
        Fireball fireball = mariof.fire(true, 10, 10);

        assert(fireball == null);
    }

    /** test Mario class move method.
     * Branch 1: toRight
     */
    @Test
    public void testMarioMove00() {
        Camera cam = new Camera();
        mario = new Mario(50, 50);

        mario.move(true, cam);

        assert(mario.getVelX() == 5);
        assert(mario.getToRight());
    }

    /** test Mario class move method.
     * Integration testing to check correct interaction
     * between move method and Camera class.
     * Branch 1: !toRight and camera.getX() < getX()
     */
    @Test
    public void testMarioMove01() {
        Camera cam = new Camera();
        cam.setX(1);
        mario = new Mario(50, 50);

        mario.move(false, cam);

        assert(mario.getVelX() == -5);
        assert(!mario.getToRight());
    }
}
