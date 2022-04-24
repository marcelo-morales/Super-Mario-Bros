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
    final String imagePath = "/about-screen.png";

    @BeforeEach
    void setup() {
        animation = null;
        leftFrames = new BufferedImage[10];
        rightFrames = new BufferedImage[10];

        // load images
        try {
            for (int i = 0; i < leftFrames.length; i++) {
                BufferedImage frame = ImageIO.read(getClass().getResource("/media" + imagePath));
                leftFrames[i] = frame;
            }
            for (int i = 0; i < rightFrames.length; i++) {
                BufferedImage frame = ImageIO.read(getClass().getResource("/media" + imagePath));
                rightFrames[i] = frame;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        animation = new Animation(leftFrames, rightFrames);
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
     * Test constructor with arrays of length 0.
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
        try {
            animation = new Animation(leftFrames, rightFrames);
        } catch (Exception e) {
            fail("Unexpected exception was thrown");
        }

        assertEquals(leftFrames, animation.getLeftFrames());
        assertEquals(rightFrames, animation.getRightFrames());
    }

    /**
     * Test animate() when the count is not greater than speed.
     */
    @Test
    void testAnimateCountNotGreaterThanSpeed() {
        assertEquals(rightFrames[1], animation.animate(5, true));
    }

    /**
     * Test animate() when the count is greater than speed, and toRight = true.
     */
    @Test
    void testAnimateCountGreaterThanSpeedToRight() {
        assertEquals(rightFrames[2], animation.animate(0, true));
    }

    /**
     * Test animate() when the count is greater than speed, and toRight = false.
     */
    @Test
    void testAnimateCountGreaterThanSpeedToLeft() {
        assertEquals(leftFrames[2], animation.animate(0, false));
    }

    /**
     * Test animate() when the length of the array is less than three.
     */
    @Disabled
    @Test
    void testAnimateCountGreaterThanSpeedToRightArrayLengthLessThanThree() {
        rightFrames = new BufferedImage[2];

        // load images
        try {
            for (int i = 0; i < rightFrames.length; i++) {
                BufferedImage frame = ImageIO.read(getClass().getResource("/media" + imagePath));
                rightFrames[i] = frame;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        animation = new Animation(leftFrames, rightFrames);

        try {
            animation.animate(0, true);
            assertEquals(rightFrames[2], animation.animate(0, true));
        } catch (Exception e) {
            fail("Unexpected exception thrown\n" + e);
        }
    }
}
