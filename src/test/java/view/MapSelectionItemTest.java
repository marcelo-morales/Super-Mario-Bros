package view;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import view.MapSelectionItem;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Whitebox testing to test the MapSelectionItem class.
 * Goal to achieve branch coverage.
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
     * Test the constructor and if the values are set correctly.
     */
    @Test
    void testConstructor() {
        assertEquals(mapName, map.getName());
        assertEquals(location, map.getLocation());
        assertEquals(new Dimension(), map.getDimension());
    }

    /**
     * Test setDimension().
     */
    @Test
    void testSetDimension() {
        Dimension dimension = new Dimension(10, 20);
        map.setDimension(dimension);
        assertEquals(dimension, map.getDimension());
    }

    /**
     * Test setLocation().
     */
    @Test
    void testSetLocation() {
        Point location = new Point(20, 30);
        map.setLocation(location);
        assertEquals(location, map.getLocation());
    }
}
