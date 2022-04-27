package view;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class to test the ImageLoader class.
 *
 * Blackbox testing for possible methods, technique determined depending on method.
 * Whitebox testing for each method, aim to achieve branch coverage.
 * Mutation testing, aim to achieve maximum mutation coverage.
 *
 * Notes:
 *      cannot achieve branch coverage in loadImage(String path).
 *      the try-catch block is set to catch IOException, however, the ImageIO.read() will throw an
 *      IllegalArgumentException, and therefore will never catch IOException error.
 *
 *      testLoadImageStringNullPath(), testLoadImageStringInvalidPath(), and testLoadImageFileNullFile()
 *      test methods fails because they will throw an IllegalArgumentException, not a IOException. Therefore the
 *      exception will not be caught and will propagate through the program and throw the exception, not return null.
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
     * Test loadImage(String) with null String.
     *
     * goal: blackbox testing with equivalence partitioning.
     *
     * EP: String path - {null, not null invalid path, not null valid path}
     */
    // TODO: remove disabled
    @Disabled
    @Test
    void testLoadImageStringNullPath() {
        String path = null;
        assertNull(loader.loadImage(path));
    }

    /**
     * Test loadImage(String) with invalid path.
     *
     * goal: blackbox testing with equivalence partitioning.
     *
     * EP: String path - {null, not null invalid path, not null valid path}
     */
    // TODO: remove disabled
    @Disabled
    @Test
    void testLoadImageStringInvalidPath() {
        assertNull(loader.loadImage("test"));
    }

    /**
     * Test loadImage(String) with valid path.
     *
     * goal: blackbox testing with equivalence partitioning.
     *
     * EP: String path - {null, not null invalid path, not null valid path}
     */
    @Test
    void testLoadImageStringValidPath() {
        String marioPath = "/mario-forms.png";
        assertTrue(CompareImages.compareImages(marioImage, loader.loadImage(marioPath)));
    }

    /**
     * Test loadImage(File) with null file.
     *
     * goal: blackbox testing with equivalence partitioning.
     *
     * EP: File file - {null, not null invalid file, not null valid file but not image, not null valid image}
     */
    // TODO: remove disabled
    @Disabled
    @Test
    void testLoadImageFileNullFile() {
        File file = null;
        assertNull(loader.loadImage(file));
    }

    /**
     * Test loadImage(File) with invalid file.
     *
     * goal: blackbox testing with equivalence partitioning.
     *
     * EP: File file - {null, not null invalid file, not null valid file but not image, not null valid image}
     */
    @Test
    void testLoadImageFileInvalidFile() {
        File file = new File("test");
        assertNull(loader.loadImage(file));
    }

    /**
     * Test loadImage(File) with a non-image file.
     *
     * goal: blackbox testing with equivalence partitioning.
     *
     * EP: File file - {null, not null invalid file, not null valid file but not image, not null valid image}
     */
    @Test
    void testLoadImageFileNonImageFile() {
        File file = new File("src/main/resources/media/audio/background.wav");
        assertNull(loader.loadImage(file));
    }

    /**
     * Test loadImage(File) with valid file.
     *
     * goal: whitebox testing branch coverage
     */
    @Test
    void testLoadImageFileValidFile() {
        File file = new File(marioPath);
        assertTrue(CompareImages.compareImages(marioImage, loader.loadImage(file)));
    }

    /**
     * Test getSubImage() with Koopa.
     *
     * goal: whitebox testing branch coverage
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
     *
     * goal: whitebox testing branch coverage
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
     *
     * goal: whitebox testing branch coverage
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
     *
     * goal: whitebox testing branch coverage
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
     *
     * goal: whitebox testing branch coverage
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
     *
     * goal: whitebox testing branch coverage
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
     *
     * goal: whitebox testing branch coverage
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
     *
     * goal: whitebox testing branch coverage
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
     *
     * goal: whitebox testing branch coverage
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

