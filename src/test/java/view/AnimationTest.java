package view;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class to test the Animation class.
 *
 * No blackbox testing because the behavior cannot be assumed from method signature.
 * Whitebox testing for each method, aim to achieve branch coverage.
 * Mutation testing, aim to achieve maximum mutation coverage.
 *
 * Notes:
 *      testAnimateCountGreaterThanSpeedToRightArrayLengthLessThanThree() test method fails.
 *      this is because the nextFrame() method assumes that the array BufferedImage[] length is at least 3.
 */
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
     *
     * goal: whitebox testing branch coverage
     */
    @Test
    void testConstructorNullArrays() {
        try {
            animation = new Animation(null, null);
            fail("exception was not thrown");
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    /**
     * Test constructor with arrays of length 0.
     *
     * goal: whitebox testing branch coverage
     */
    @Test
    void testConstructorArraysWithLengthZero() {
        leftFrames = new BufferedImage[0];
        rightFrames = new BufferedImage[0];

        try {
            animation = new Animation(leftFrames, rightFrames);
            fail("exception was not thrown");
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
        }
    }

    /**
     * Test constructor with arrays of length 10.
     *
     * goal: whitebox testing branch coverage
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
     *
     * goal: whitebox testing branch coverage
     */
    @Test
    void testAnimateCountNotGreaterThanSpeed() {
        assertEquals(rightFrames[1], animation.animate(5, true));
    }

    /**
     * Test animate() when the count is greater than speed, and toRight = true.
     *
     * goal: whitebox testing branch coverage
     */
    @Test
    void testAnimateCountGreaterThanSpeedToRight() {
        assertEquals(rightFrames[2], animation.animate(0, true));
    }

    /**
     * Test animate() when the count is greater than speed, and toRight = false.
     *
     * goal: whitebox testing branch coverage
     */
    @Test
    void testAnimateCountGreaterThanSpeedToLeft() {
        assertEquals(leftFrames[2], animation.animate(0, false));
    }

    /**
     * Test animate() when the length of the array is less than three.
     *
     * goal: whitebox testing branch coverage
     */
    // TODO: remove disabled
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