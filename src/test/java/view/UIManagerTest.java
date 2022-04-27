package view;

import manager.GameEngine;
import manager.GameStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

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
 */
public class UIManagerTest {

    UIManager uiManager;
    GameEngine engineMock = mock(GameEngine.class);
    final int WIDTH = 1268;
    final int HEIGHT = 708;
    boolean spy = true;

    @BeforeEach
    void setup() {
        // stubbing GameEngine
        when(engineMock.getImageLoader()).thenReturn(new ImageLoader());
        StartScreenSelection startScreenSelection = mock(StartScreenSelection.class);
        when(startScreenSelection.getLineNumber()).thenReturn(1);
        when(engineMock.getStartScreenSelection()).thenReturn(startScreenSelection);
        when(engineMock.getCameraLocation()).thenReturn(new Point(100, 100));

        uiManager = new UIManager(engineMock, WIDTH, HEIGHT);
        if (spy) uiManager = spy(uiManager);
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
     */
    @Test
    void testPaintComponentInteractionsStartScreen() {
        when(engineMock.getGameStatus()).thenReturn(GameStatus.START_SCREEN);
        uiManager.paintComponent(getGraphicsObject());
        verify(uiManager).drawStartScreen(any(Graphics2D.class));
    }

    /**
     * Test paintComponent().
     *
     * Testing the branch where gameStatus == MAP_SELECTION
     */
    @Test
    void testPaintComponentInteractionsMapSelection() {
        when(engineMock.getGameStatus()).thenReturn(GameStatus.MAP_SELECTION);
        uiManager.paintComponent(getGraphicsObject());
        verify(uiManager).drawMapSelectionScreen(any(Graphics2D.class));
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
        when(engineMock.getGameStatus()).thenReturn(GameStatus.GAME_OVER);
        uiManager.paintComponent(getGraphicsObject());
        verify(uiManager).drawGameOverScreen(any(Graphics2D.class));
    }

    /**
     * Test paintComponent().
     *
     * Testing the branch where gameStatus == PAUSED
     */
    @Test
    void testPaintComponentInteractionsPaused() {
        when(engineMock.getGameStatus()).thenReturn(GameStatus.PAUSED);
        uiManager.paintComponent(getGraphicsObject());
        verify(uiManager).drawPauseScreen(any(Graphics2D.class));
    }

    @Test
    void testPaintComponentInteractionsMissionPassed() {
        when(engineMock.getGameStatus()).thenReturn(GameStatus.MISSION_PASSED);
        uiManager.paintComponent(getGraphicsObject());
        verify(uiManager).drawVictoryScreen(any(Graphics2D.class));
    }

    @Test
    void testPaintComponentInteractionsUnknownGameStatus() {
        when(engineMock.getGameStatus()).thenReturn(null);
        uiManager.paintComponent(getGraphicsObject());

        verify(uiManager, never()).drawStartScreen(any(Graphics2D.class));
        verify(uiManager, never()).drawMapSelectionScreen(any(Graphics2D.class));
        verify(uiManager, never()).drawAboutScreen(any(Graphics2D.class));
        verify(uiManager, never()).drawHelpScreen(any(Graphics2D.class));
        verify(uiManager, never()).drawGameOverScreen(any(Graphics2D.class));
        verify(uiManager, never()).drawPauseScreen(any(Graphics2D.class));
        verify(uiManager, never()).drawVictoryScreen(any(Graphics2D.class));
    }

    @Test
    void testSelectMapViaMouse() {
        assertEquals("Map 1.png", uiManager.selectMapViaMouse(new Point(0, 300)));
    }

    @Test
    void testSelectMapViaKeyboard() {
        assertEquals("Map 1.png", uiManager.selectMapViaKeyboard(0));
    }

    @Test
    void testChangeSelectedMap() {
        assertEquals(0, uiManager.changeSelectedMap(1, true));
    }

    private Graphics getGraphicsObject() {
        BufferedImage mario = null;
        try {
            mario = ImageIO.read(new File("src/main/resources/media/mario-forms.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mario.getGraphics();
    }
}
