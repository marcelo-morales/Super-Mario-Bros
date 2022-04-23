package UnitTestViewPackage;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import view.Animation;
import view.ImageLoader;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class AnimationTest {
    private Animation animation;
    private BufferedImage[] leftFrames, rightFrames;
    private BufferedImage currentFrame;

    @BeforeEach
    void setup() {
        final String imagePath = "/about-screen.png";

        animation = null;

        // load images
        try {
            currentFrame = ImageIO.read(getClass().getResource("/media" + imagePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Test constructor with null arrays.
     */
    // TODO: remove disabled. what is the desired outcome here?
    @Disabled
    @Test
    void testConstructorNullArrays() {
        try {
            animation = new Animation(null, null);
        } catch (Exception e) {
            fail("Unexpected exception was thrown");
        }

        assertEquals(null, animation.getLeftFrames());
        assertEquals(null, animation.getRightFrames());
    }

    /**
     * Test constructor with arrays of length 1.
     */
    // TODO: remove disabled. desired outcome?
    @Disabled
    @Test
    void testConstructorArraysWithLengthZero() {
        leftFrames = new BufferedImage[0];
        rightFrames = new BufferedImage[0];

        try {
            animation = new Animation(leftFrames, rightFrames);
        } catch (Exception e) {
            fail("Unexpected exception was thrown");
        }

        assertEquals(leftFrames, animation.getLeftFrames());
        assertEquals(rightFrames, animation.getRightFrames());
    }

    /**
     * Test constructor with arrays of length 10.
     */
    @Test
    void testConstructorArraysWithLengthTen() {
        leftFrames = new BufferedImage[10];
        rightFrames = new BufferedImage[10];

        for (int i = 0; i < 10; i++) {
            leftFrames[i] = currentFrame;
            rightFrames[i] = currentFrame;
        }

        try {
            animation = new Animation(leftFrames, rightFrames);
        } catch (Exception e) {
            fail("Unexpected exception was thrown");
        }

        assertEquals(leftFrames, animation.getLeftFrames());
        assertEquals(rightFrames, animation.getRightFrames());
    }

    // TODO: how do we unit test methods that modify private variables?
    void testNextFrameArrayLengthTwo() {

    }

}
