package view;

import manager.GameEngine;
import manager.GameStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;
import org.mockito.MockedStatic;
import org.mockito.stubbing.Answer;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class to test the UIManager class.
 *
 * Blackbox testing not utilized because it is difficult to assume behavior by method signature.
 * Whitebox testing for each method, aim to achieve branch coverage.
 * Interaction testing to see if all the private methods were called correctly, adding onto branch coverage.
 * Mutation testing, aim to achieve maximum mutation coverage.
 *
 * Notes:
 *      For this test, most of the operations were using private methods, which we cannot access and use Mockito to
 *      view invocations. Therefore we have switched all private methods to protected methods in the source code.
 *
 *      cannot achieve 100% mutation score because the Error.printStackTrace() on line 46 does not change
 *      the state of the program.
 */
public class UIManagerTest {

    UIManager uiManager;
    GameEngine engineMock = mock(GameEngine.class);
    final int WIDTH = 1268;
    final int HEIGHT = 708;
    boolean spy = true;
    Point camLocation;
    Graphics g;
    Graphics2D g2;

    @BeforeEach
    void setup() {
        camLocation = new Point(100, 200);

        // stubbing GameEngine
        when(engineMock.getImageLoader()).thenReturn(new ImageLoader());
        StartScreenSelection startScreenSelection = mock(StartScreenSelection.class);
        when(startScreenSelection.getLineNumber()).thenReturn(1);
        when(engineMock.getStartScreenSelection()).thenReturn(startScreenSelection);
        when(engineMock.getCameraLocation()).thenReturn(camLocation);

        // stubbing
        // get spy objects for Graphics and Graphics2D
        g = getGraphicsObject();
        g = spy(g);
        g2 = (Graphics2D) g.create();
        g2 = spy(g2);
        when(g.create()).thenReturn(g2);

        uiManager = new UIManager(engineMock, WIDTH, HEIGHT);
        if (spy) uiManager = spy(uiManager);
    }

    /**
     * Test if constructor calls its private methods to set Dimensions.
     *
     * goal: mutation testing
     */
    @Test
    void testConstructorCallSettingDimensions() {
        Dimension dimension = new Dimension(WIDTH, HEIGHT);
        assertEquals(dimension, uiManager.getPreferredSize());
        assertEquals(dimension, uiManager.getMaximumSize());
        assertEquals(dimension, uiManager.getMinimumSize());
    }

    /**
     * Test constructor with invalid resource.
     * To execute the catch statement in the try/catch block.
     */
    @Test
    void testConstructorWithInvalidResource() {
        // stubbing static method Font.createFont()
        try (MockedStatic<Font> mockedStatic = mockStatic(Font.class)) {

            // to retrieve the data of a private field
            Field field = UIManager.class.getDeclaredField("gameFont");
            field.setAccessible(true);

            // stub the static method Font.createFont()
            mockedStatic.when(() -> Font.createFont(eq(Font.TRUETYPE_FONT), any(InputStream.class)))
                    .thenThrow(IOException.class);

            // call the constructor inside try block because of MockedStatic scope.
            uiManager = new UIManager(engineMock, WIDTH, HEIGHT);

            // get the private field gameFont
            Font font = (Font) field.get(uiManager);

            // assert
            assertEquals(new Font("Verdana", Font.PLAIN, 12), font);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Test paintComponent().
     *
     * Testing the branch where gameStatus == START_SCREEN
     *
     * goal: interaction testing and mutation testing
     */
    @Test
    void testPaintComponentInteractionsStartScreen() {
        // stub behavior
        when(engineMock.getGameStatus()).thenReturn(GameStatus.START_SCREEN);

        // call to method to test
        uiManager.paintComponent(g);

        // verify
        verify(uiManager).drawStartScreen(g2);
        verify(g2).drawImage(any(BufferedImage.class), eq(0), eq(0), eq(null));
        verify(g2).drawImage(any(BufferedImage.class), eq(375), eq(1 * 70 + 440), eq(null));
    }

    /**
     * Test paintComponent().
     *
     * Testing the branch where gameStatus == MAP_SELECTION
     */
    @Test
    void testPaintComponentInteractionsMapSelection() {
        // stub behavior
        when(engineMock.getGameStatus()).thenReturn(GameStatus.MAP_SELECTION);
        when(engineMock.getSelectedMap()).thenReturn(1);

        // call to method to test
        uiManager.paintComponent(g);

        // verify
        verify(uiManager).drawMapSelectionScreen(g2);
        verify(g2).setFont(any(Font.class));
        verify(g2, atLeast(3)).setColor(Color.WHITE);
        verify(g2).drawImage(any(BufferedImage.class), eq(375), eq(1*100+300-48), eq(null));
    }

    /**
     * Test paintComponent().
     *
     * Testing the branch where gameStatus == ABOUT_SCREEN
     */
    @Test
    void testPaintComponentInteractionsAboutScreen() {
        when(engineMock.getGameStatus()).thenReturn(GameStatus.ABOUT_SCREEN);
        uiManager.paintComponent(getGraphicsObject());
        verify(uiManager).drawAboutScreen(any(Graphics2D.class));
    }

    /**
     * Test paintComponent().
     *
     * Testing the branch where gameStatus == HELP_SCREEN
     */
    @Test
    void testPaintComponentInteractionsHelpScreen() {
        when(engineMock.getGameStatus()).thenReturn(GameStatus.HELP_SCREEN);
        uiManager.paintComponent(getGraphicsObject());
        verify(uiManager).drawHelpScreen(any(Graphics2D.class));
    }

    /**
     * Test paintComponent().
     *
     * Testing the branch where gameStatus == GAME_OVER
     */
    @Test
    void testPaintComponentInteractionsGameOver() {
        int width = 100;
        int height = 100;
        int stringLength = 400;
        int stringHeight = 66;

        // stub
        when(engineMock.getGameStatus()).thenReturn(GameStatus.GAME_OVER);
        when(uiManager.getWidth()).thenReturn(width);
        when(uiManager.getHeight()).thenReturn(height);

        // call to method to test
        uiManager.paintComponent(g);

        // verify
        verify(uiManager).drawGameOverScreen(g2);
        verify(g2).drawImage(any(BufferedImage.class), eq(0), eq(0), eq(null));
        verify(g2).setFont(any(Font.class));
        verify(g2).setColor(new Color(130, 48, 48));
        verify(g2).drawString(any(String.class), eq((width-stringLength) / 2), eq(height-stringHeight*2));
    }

    /**
     * Test paintComponent().
     *
     * Testing the branch where gameStatus == PAUSED
     */
    @Test
    void testPaintComponentInteractionsPaused() {
        int width = 100;
        int height = 100;
        int stringLength = 300;

        // stub
        when(engineMock.getGameStatus()).thenReturn(GameStatus.PAUSED);
        when(uiManager.getWidth()).thenReturn(width);
        when(uiManager.getHeight()).thenReturn(height);

        // setup InOrder objects
        InOrder inOrderUiManager = inOrder(uiManager);
        InOrder inOrderGraphics2D = inOrder(g2);

        // call to method to test
        uiManager.paintComponent(g);

        // verify
        verifyCallsToElseBlock(inOrderUiManager, inOrderGraphics2D, width);
        inOrderUiManager.verify(uiManager).drawPauseScreen(g2);
        inOrderGraphics2D.verify(g2).setFont(any(Font.class));
        inOrderGraphics2D.verify(g2).setColor(Color.WHITE);
        inOrderGraphics2D.verify(g2).drawString(any(String.class), eq((width - stringLength)/2), eq(height/2));
    }

    /**
     * Test paintComponent().
     *
     * Testing the branch where gameStatus == MISSION_PASSED
     */
    @Test
    void testPaintComponentInteractionsMissionPassed() {
        int width = 100;
        int height = 100;
        int stringLength = 400;

        // stub
        when(engineMock.getGameStatus()).thenReturn(GameStatus.MISSION_PASSED);
        when(uiManager.getWidth()).thenReturn(width);
        when(uiManager.getHeight()).thenReturn(height);


        // setup InOrder objects
        InOrder inOrderUiManager = inOrder(uiManager);
        InOrder inOrderGraphics2D = inOrder(g2);

        // call to method to test
        uiManager.paintComponent(g);

        // verify
        verifyCallsToElseBlock(inOrderUiManager, inOrderGraphics2D, width);
        inOrderUiManager.verify(uiManager).drawVictoryScreen(g2);
        inOrderGraphics2D.verify(g2).setFont(any(Font.class));
        inOrderGraphics2D.verify(g2).setColor(Color.WHITE);
        inOrderGraphics2D.verify(g2).drawString(any(String.class), eq((width - stringLength)/2), eq(height/2));
    }

    /**
     * Test paintComponent().
     *
     * Testing if calls to the graphics methods have been made.
     *
     * goal: interaction testing and mutation testing
     */
    @Test
    void testPaintComponentInteractionsUnknownGameStatus() {
        // stub engine mock behavior
        when(engineMock.getGameStatus()).thenReturn(null);
        
        // setup InOrder
        InOrder inOrderUiManager = inOrder(uiManager);
        InOrder inOrderGraphics2D = inOrder(g2);

        // get uiManager width
        int width = uiManager.getWidth();
        
        // calls method to test
        uiManager.paintComponent(g);
        
        // verify none of these methods were called
        inOrderUiManager.verify(uiManager, never()).drawStartScreen(any(Graphics2D.class));
        inOrderUiManager.verify(uiManager, never()).drawMapSelectionScreen(any(Graphics2D.class));
        inOrderUiManager.verify(uiManager, never()).drawAboutScreen(any(Graphics2D.class));
        inOrderUiManager.verify(uiManager, never()).drawHelpScreen(any(Graphics2D.class));
        inOrderUiManager.verify(uiManager, never()).drawGameOverScreen(any(Graphics2D.class));
        inOrderUiManager.verify(uiManager, never()).drawPauseScreen(any(Graphics2D.class));
        inOrderUiManager.verify(uiManager, never()).drawVictoryScreen(any(Graphics2D.class));

        //
        verifyCallsToElseBlock(inOrderUiManager, inOrderGraphics2D, width);

        // verify calls to dispose() method
        verify(g2, times(2)).dispose();
    }

    /**
     * Test selectMapViaMouse().
     */
    @Test
    void testSelectMapViaMouse() {
        assertEquals("Map 1.png", uiManager.selectMapViaMouse(new Point(0, 300)));
    }

    /**
     * Test selectMapViaKeyboard().
     */
    @Test
    void testSelectMapViaKeyboard() {
        assertEquals("Map 1.png", uiManager.selectMapViaKeyboard(0));
    }

    /**
     * Test changeSelectedMap().
     */
    @Test
    void testChangeSelectedMap() {
        assertEquals(1, uiManager.changeSelectedMap(0, false));
    }

    /**
     * Helper method to return a Graphics objects of the mario-forms.png.
     */
    private Graphics getGraphicsObject() {
        BufferedImage mario = null;
        try {
            mario = ImageIO.read(new File("src/main/resources/media/mario-forms.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mario.getGraphics();
    }

    /**
     * This method verifies that the method in the else block of paintComponent() lines-73-81 were called correctly.
     */
    private void verifyCallsToElseBlock(InOrder inOrderUiManager, InOrder inOrderGraphics2D, int width) {
        // verify calls
        inOrderGraphics2D.verify(g2).translate(-camLocation.x, -camLocation.y);
        verify(engineMock).drawMap(g2);
        inOrderGraphics2D.verify(g2).translate(camLocation.x, camLocation.y);
        // verify calls to and inside drawPoints()
        inOrderUiManager.verify(uiManager).drawPoints(g2);
        inOrderGraphics2D.verify(g2).setFont(any(Font.class));
        inOrderGraphics2D.verify(g2).setColor(Color.WHITE);
        verify(engineMock).getScore();
        inOrderGraphics2D.verify(g2).getFontMetrics();
        inOrderGraphics2D.verify(g2).drawString(any(String.class), eq(300), eq(50));
        // verify calls to and inside drawRemainingLives()
        inOrderUiManager.verify(uiManager).drawRemainingLives(g2);
        inOrderGraphics2D.verify(g2).setFont(any(Font.class));
        inOrderGraphics2D.verify(g2).setColor(Color.WHITE);
        inOrderGraphics2D.verify(g2).drawImage(any(Image.class), eq(50), eq(10), eq(null));
        inOrderGraphics2D.verify(g2).drawString(any(String.class), eq(100), eq(50));
        // verify calls to and inside drawAcquiredCoins()
        inOrderUiManager.verify(uiManager).drawAcquiredCoins(g2);
        inOrderGraphics2D.verify(g2).setFont(any(Font.class));
        inOrderGraphics2D.verify(g2).setColor(Color.WHITE);
        inOrderGraphics2D.verify(g2).drawImage(any(Image.class), eq(width-115), eq(10), eq(null));
        inOrderGraphics2D.verify(g2).drawString(any(String.class), eq(width-65), eq(50));
        // verify calls to and inside drawRemainingTime()
        inOrderUiManager.verify(uiManager).drawRemainingTime(g2);
        inOrderGraphics2D.verify(g2).setFont(any(Font.class));
        inOrderGraphics2D.verify(g2).setColor(Color.WHITE);
        inOrderGraphics2D.verify(g2).drawString(any(String.class), eq(750), eq(50));
    }
}
