package UnitTestViewPackage;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import view.MapSelection;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Whitebox testing to test the MapSelection class.
 * Goal to achieve branch coverage.
 *
 * Notes:
 *      cannot achieve branch coverage in createItems() method.
 *      the condition "if(maps == null)" is never true because on the class instantiation, maps = new ArrayList<>().
 *      Hence maps is never null.
 */
public class MapSelectionTest {
    String[] maps = {"Map 1.png", "Map 2.png"};
    MapSelection mapSelection;

    @BeforeEach
    void setup() {
        mapSelection = new MapSelection();
    }

    // TODO: test the draw() method??? How to test that...?

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
        assertNull(mapSelection.selectMap(-2));
    }

    /**
     * Test changeSelectedMap().
     *
     * To achieve branch coverage.
     * Up = true, index = 1
     */
    @Test
    void testChangeSelectedMapUp() {
        assertEquals(0, mapSelection.changeSelectedMap(1, true));
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