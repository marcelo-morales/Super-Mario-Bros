package view;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import view.MapSelectionItem;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class to test the MapSelection class.
 *
 * Blackbox testing for possible methods, technique determined depending on method.
 * Whitebox testing for each method, aim to achieve branch coverage.
 * Mutation testing, aim to achieve maximum mutation coverage.
 *
 * Notes:
 *      testConstructorInvalidMap() and testConstructorNullMap() both fail because the method signature does not
 *      indicate throwing exceptions, but if the String map is null or invalid, the constructor will throw an
 *      IllegalArgumentException.
 */
public class MapSelectionItemTest {

    int defaultGridSize = 100;
    String mapName = "Map 1.png";
    MapSelectionItem map;
    Point location = new Point(0, (1)*defaultGridSize+200);

    @BeforeEach
    void setup() {
        map = new MapSelectionItem(mapName, location);
    }

    /**
     * Test constructor with map = null.
     *
     * goal: blackbox testing with equivalence partitioning.
     *       use base choice coverage
     *
     * EP:
     *      String path - {null, not null invalid path, valid path}
     *          base: valid path
     *      Point location - {null, not null}
     *          base: not null
     */
    @Test
    void testConstructorNullMap() {
        String mapName = null;
        map = new MapSelectionItem(mapName, location);
        assertEquals(mapName, map.getName());
        assertEquals(location, map.getLocation());
    }

    /**
     * Test constructor with map = not null invalid path.
     *
     * goal: blackbox testing with equivalence partitioning.
     *       use base choice coverage
     *
     * EP:
     *      String path - {null, not null invalid path, valid path}
     *          base: valid path
     *      Point location - {null, not null}
     *          base: not null
     */
    @Test
    void testConstructorInvalidMap() {
        String mapName = "test";
        map = new MapSelectionItem(mapName, location);
        assertEquals(mapName, map.getName());
        assertEquals(location, map.getLocation());
    }

    /**
     * Test constructor with map = valid.
     *
     * goal: blackbox testing with equivalence partitioning.
     *       use base choice coverage
     *
     * EP:
     *      String path - {null, not null invalid path, valid path}
     *          base: valid path
     *      Point location - {null, not null}
     *          base: not null
     */
    @Test
    void testConstructorNullLocation() {
        Point location = null;
        map = new MapSelectionItem(mapName, location);
        assertEquals(mapName, map.getName());
        assertEquals(location, map.getLocation());
    }

    /**
     * Test constructor with map & location valid.
     *
     * goal: blackbox testing with equivalence partitioning.
     *       use base choice coverage
     *
     * EP:
     *      String path - {null, not null invalid path, valid path}
     *          base: valid path
     *      Point location - {null, not null}
     *          base: not null
     */
    @Test
    void testConstructorValidMapValidLocation() {
        assertEquals(mapName, map.getName());
        assertEquals(location, map.getLocation());
    }

    /**
     * Test setDimension().
     *
     * goal: whitebox testing branch coverage
     */
    @Test
    void testSetDimension() {
        Dimension dimension = new Dimension(10, 20);
        map.setDimension(dimension);
        assertEquals(dimension, map.getDimension());
    }

    /**
     * Test setLocation().
     *
     * goal: whitebox testing branch coverage
     */
    @Test
    void testSetLocation() {
        Point location = new Point(20, 30);
        map.setLocation(location);
        assertEquals(location, map.getLocation());
    }
}
