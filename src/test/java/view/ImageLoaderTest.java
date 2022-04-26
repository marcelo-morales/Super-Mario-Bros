package view;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Whitebox testing to test the ImageLoader class.
 * Goal to achieve branch coverage.
 *
 * Notes:
 *      cannot achieve branch coverage in loadImage(String path).
 *      the try-catch block is set to catch IOException, however, the ImageIO.read() will throw an
 *      IllegalArgumentException, and therefore will never catch IOException error.
 *
 *      testLoadImageStringInvalidPath() test method fails because an invalid path will throw a
 *      IllegalArgumentException, not a IOException. Therefore the exception will not be caught and will propagate
 *      through the program and throw the exception.
 */
public class ImageLoaderTest {
    private final String marioPath = "src/main/resources/media/mario-forms.png";
    private final String spritePath = "src/main/resources/media/sprite.png";
    private ImageLoader loader;
    private BufferedImage marioImage;
    private BufferedImage spriteImage;

    @BeforeEach
    void setup() {
        loader = new ImageLoader();
        File mario = new File(marioPath);
        File sprite = new File(spritePath);
        try {
            marioImage = ImageIO.read(mario);
            spriteImage = ImageIO.read(sprite);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Test loadImage(String) with invalid path.
     */
    @Test
    void testLoadImageStringInvalidPath() {
        assertNull(loader.loadImage("test"));
    }

    /**
     * Test loadImage(File) with valid file.
     */
    @Test
    void testLoadImageFileValidFile() {
        File file = new File(marioPath);
        assertTrue(CompareImages.compareImages(marioImage, loader.loadImage(file)));
    }

    /**
     * Test loadImage(File) with invalid file.
     */
    @Test
    void testLoadImageFileInvalidFile() {
        File file = new File("test");
        assertNull(loader.loadImage(file));
    }

    /**
     * Test getSubImage() with Koopa.
     */
    @Test
    void testGetSubImageKoopa() {
        int col = 1;
        int row = 3;
        BufferedImage koopa = spriteImage.getSubimage((col-1)*48, 128, 48, 48);
        BufferedImage subImage = loader.getSubImage(spriteImage, col, row, 48, 48);
        assertTrue(CompareImages.compareImages(koopa, subImage));
    }

    /**
     * Test getSubImage() with SuperMushroom.
     */
    @Test
    void testGetSubImageSuperMushroom() {
        int col = 2;
        int row = 5;
        BufferedImage superMushroom = spriteImage.getSubimage((col-1)*48, (row-1)*48, 48, 48);
        BufferedImage subImage = loader.getSubImage(spriteImage, col, row, 48, 48);
        assertTrue(CompareImages.compareImages(superMushroom, subImage));
    }

    /**
     * Test getLeftFrames() when marioForm = 0.
     */
    @Test
    void testGetLeftFramesMarioFormEqualsZero() {
        // set marioForm
        int marioForm = 0;

        // set sub-image values
        int col = 1;
        int width = 52;
        int height = 48;

        // execute test method
        BufferedImage[] leftFrames = loader.getLeftFrames(marioForm);

        // assert
        for(int i = 0; i < leftFrames.length; i++){
            assertTrue(
                    CompareImages.compareImages(
                            marioImage.getSubimage((col-1)*width, (i)*height, width, height),
                            leftFrames[i]
                    )
            );
        }
    }

    /**
     * Test getLeftFrames() when marioForm = 1.
     */
    @Test
    void testGetLeftFramesMarioFormEqualsOne() {
        // set marioForm
        int marioForm = 1;

        // set sub-image values
        int col = 4;
        int width = 48;
        int height = 96;

        // execute test method
        BufferedImage[] leftFrames = loader.getLeftFrames(marioForm);

        // assert
        for(int i = 0; i < leftFrames.length; i++){
            assertTrue(
                    CompareImages.compareImages(
                            marioImage.getSubimage((col-1)*width, (i)*height, width, height),
                            leftFrames[i]
                    )
            );
        }
    }

    /**
     * Test getLeftFrames() when marioForm = 2.
     */
    @Test
    void testGetLeftFramesMarioFormEqualsTwo() {
        // set marioForm
        int marioForm = 2;

        // set sub-image values
        int col = 7;
        int width = 48;
        int height = 96;

        // execute test method
        BufferedImage[] leftFrames = loader.getLeftFrames(marioForm);

        // assert
        for(int i = 0; i < leftFrames.length; i++){
            assertTrue(
                    CompareImages.compareImages(
                            marioImage.getSubimage((col-1)*width, (i)*height, width, height),
                            leftFrames[i]
                    )
            );
        }
    }

    /**
     * Test getRightFrames() when marioForm = 0.
     */
    @Test
    void testGetRightFramesMarioFormEqualsZero() {
        // set marioForm
        int marioForm = 0;

        // set sub-image values
        int col = 2;
        int width = 52;
        int height = 48;

        // execute test method
        BufferedImage[] rightFrames = loader.getRightFrames(marioForm);

        // assert
        for(int i = 0; i < rightFrames.length; i++){
            assertTrue(
                    CompareImages.compareImages(
                            marioImage.getSubimage((col-1)*width, (i)*height, width, height),
                            rightFrames[i]
                    )
            );
        }
    }

    /**
     * Test getRightFrames() when marioForm = 1.
     */
    @Test
    void testGetRightFramesMarioFormEqualsOne() {
        // set marioForm
        int marioForm = 1;

        // set sub-image values
        int col = 5;
        int width = 48;
        int height = 96;

        // execute test method
        BufferedImage[] rightFrames = loader.getRightFrames(marioForm);

        // assert
        for(int i = 0; i < rightFrames.length; i++){
            assertTrue(
                    CompareImages.compareImages(
                            marioImage.getSubimage((col-1)*width, (i)*height, width, height),
                            rightFrames[i]
                    )
            );
        }
    }

    /**
     * Test getRightFrames() when marioForm = 2.
     */
    @Test
    void testGetRightFramesMarioFormEqualsTwo() {
        // set marioForm
        int marioForm = 2;

        // set sub-image values
        int col = 8;
        int width = 48;
        int height = 96;

        // execute test method
        BufferedImage[] rightFrames = loader.getRightFrames(marioForm);

        // assert
        for(int i = 0; i < rightFrames.length; i++){
            assertTrue(
                    CompareImages.compareImages(
                            marioImage.getSubimage((col-1)*width, (i)*height, width, height),
                            rightFrames[i]
                    )
            );
        }
    }

    /**
     * Test getBrickFrames().
     */
    @Test
    void testGetBrickFrames() {
        String path = "src/main/resources/media/brick-animation.png";
        File file = new File(path);
        BufferedImage brickAnimation = null;
        BufferedImage brickFrames[] = new BufferedImage[4];
        try {
            brickAnimation = ImageIO.read(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        for(int i = 0; i < 4; i++){
            brickFrames[i] = brickAnimation.getSubimage(i*105, 0, 105, 105);
        }
        BufferedImage[] loadedImages = loader.getBrickFrames();
        for (int i = 0; i < 4; i++) {
            assertTrue(CompareImages.compareImages(brickFrames[i], loadedImages[i]));
        }
    }
}

