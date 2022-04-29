package view;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;
import view.MapSelection;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Test class to test the MapSelection class.
 *
 * Blackbox testing not utilized because it is difficult to assume behavior with method signature.
 * Whitebox testing for each method, aim to achieve branch coverage.
 * Mutation testing, aim to achieve maximum mutation coverage.
 *
 * Notes:
 *      cannot achieve branch coverage in createItems() method.
 *      the condition "if(maps == null)" is never true because on the class instantiation, maps = new ArrayList<>().
 *      Hence maps is never null.
 *
 *      cannot achieve branch coverage in draw() method.
 *      the condition "if(mapSelectionItems=null)" is never true because in the createItems() method, it always
 *      returns a non-null array (although it can be empty).
 *      Hence the branch never executes.
 *
 *      cannot achieve 100% mutation score in this class. lines 21 and 48 are unreachable, line 34, 64, and 65
 *      is not possible to kill mutant because we have no way of changing the dimensions, and condition on line 80
 *      is not possible to kill mutant because the mapSelectionItems.length is 2, and therefore will always wraparound
 *      to each other.
 */
public class MapSelectionTest {
    String[] maps = {"Map 1.png", "Map 2.png"};
    MapSelection mapSelection;

    @BeforeEach
    void setup() {
        mapSelection = new MapSelection();
    }

    /**
     * Test draw() method.
     *
     * To achieve branch coverage and mutation coverage.
     */
    @Test
    void testDraw() {
        String title = "Select a Map";
        BufferedImage mario = null;
        try {
            mario = ImageIO.read(new File("src/main/resources/media/mario-forms.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        // get Point objs
        int defaultGridSize = 100;
        String nameOne = "Map 1.png";
        String nameTwo = "Map 2.png";

        // create spy
        Graphics graphics = mario.getGraphics();
        int x_location = (1280 - graphics.getFontMetrics().stringWidth(title))/2;
        graphics = spy(graphics);

        // call method to test
        mapSelection.draw(graphics);

        // verification
        InOrder inOrder = inOrder(graphics);
        inOrder.verify(graphics).setColor(Color.BLACK);
        inOrder.verify(graphics).fillRect(0, 0, 1280, 720);
        inOrder.verify(graphics).setColor(Color.YELLOW);
        inOrder.verify(graphics).drawString(title, x_location, 150);
        inOrder.verify(graphics).setColor(Color.WHITE);
        inOrder.verify(graphics).drawString(nameOne.split("[.]")[0], 621, 300);
        inOrder.verify(graphics).setColor(Color.WHITE);
        inOrder.verify(graphics).drawString(nameTwo.split("[.]")[0], 621, 400);
    }

    /**
     * Test selectMap(Point) with selecting map 1.
     *
     * To achieve branch coverage in both the inX and inY branches.
     * Set the first condition to true and second to true (for both inX and inY) branches.
     */
    @Test
    void testSelectMapWithPointMapOne() {
        Point mouseLocation = new Point(0, 300);
        assertEquals(maps[0], mapSelection.selectMap(mouseLocation));
    }

    /**
     * Test selectMap(Point) with invalid point.
     *
     * To achieve branch coverage in both the inX and inY branches.
     * Set the first condition to true and second to false (for both inX and inY) branches.
     */
    @Test
    void testSelectMapWithPointInvalidPointTrueFalse() {
        Point mouseLocation = new Point(1, 301);
        assertNull(mapSelection.selectMap(mouseLocation));
    }

    /**
     * Test selectMap(Point) with invalid point.
     *
     * To achieve branch coverage in both the inX and inY branches.
     * Set the first condition to false and second to true (for both inX and inY) branches.
     */
    @Test
    void testSelectMapWithPointInvalidPointFalseTrue() {
        Point mouseLocation = new Point(-1, 299);
        assertNull(mapSelection.selectMap(mouseLocation));
    }

    /**
     * Test selectMap(Point) with invalid point.
     *
     * To achieve branch coverage in the inX && inY branch.
     * Set inX to true and inY to false.
     */
    @Test
    void testSelectMapWithPointInvalidPointFalseFalse() {
        Point mouseLocation = new Point(0, 301);
        assertNull(mapSelection.selectMap(mouseLocation));
    }

//    @Test
//    void testSelectMapWithPoint

    /**
     * Test selectMap(int) with valid index.
     */
    @Test
    void testSelectMapWithIndexValidIndex() {
        assertEquals(maps[0], mapSelection.selectMap(0));
    }

    /**
     * Test selectMap(int) with invalid index.
     *
     * To achieve branch coverage.
     * Line 74: false, true
     */
    @Test
    void testSelectMapWithIndexInvalidIndex() {
        assertNull(mapSelection.selectMap(5));
    }

    /**
     * Test selectMap(int) with invalid index.
     *
     * To achieve branch coverage.
     * Line 74: true, false
     */
    @Test
    void testSelectMapWithIndexInvalidIndexTwo() {
        assertNull(mapSelection.selectMap(-1));
    }

    /**
     * Test selectMap(int) with index = 2
     *
     * goal: mutation testing coverage increase
     */
    @Test
    void testSelectMapWithIndexMutation() {
        assertNull(mapSelection.selectMap(2));
    }

    /**
     * Test changeSelectedMap().
     *
     * To achieve branch coverage.
     * Up = true, index = 1
     */
    @Test
    void testChangeSelectedMapUp() {
        assertEquals(1, mapSelection.changeSelectedMap(2, true));
    }

    /**
     * Test changeSelectedMap().
     *
     * To achieve branch coverage.
     * Up = true, index = 0
     */
    @Test
    void testChangeSelectedMapUpWraparound() {
        assertEquals(1, mapSelection.changeSelectedMap(0, true));
    }

    /**
     * Test changeSelectedMap().
     *
     * To achieve branch coverage.
     * Up = false, index = 0
     */
    @Test
    void testChangeSelectedMapDown() {
        assertEquals(1, mapSelection.changeSelectedMap(0, false));
    }

    /**
     * Test changeSelectedMap().
     *
     * To achieve branch coverage.
     * Up = false, index = 1
     */
    @Test
    void testChangeSelectedMapDownWraparound() {
        assertEquals(0, mapSelection.changeSelectedMap(1, false));
    }
}