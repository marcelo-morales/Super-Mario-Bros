package UnitTestViewPackage;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import view.ImageLoader;

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
 */
public class ImageLoaderTest {
    private final String imagePath = "src/main/resources/media/mario-forms.png";
    private ImageLoader loader;
    private BufferedImage marioImage;

    @BeforeEach
    void setup() {
        loader = new ImageLoader();
        File file = new File(imagePath);
        try {
            marioImage = ImageIO.read(file);
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
        File file = new File(imagePath);
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

    // TODO: test getSubImage()???

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

    // TODO: test getBrickFrames() ???
}

