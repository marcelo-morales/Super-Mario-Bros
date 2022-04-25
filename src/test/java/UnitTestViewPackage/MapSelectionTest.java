package UnitTestViewPackage;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import view.MapSelection;
import view.MapSelectionItem;

import java.awt.*;
import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Whitebox testing to test the MapSelection class.
 * Goal to achieve branch coverage.
 */
public class MapSelectionTest {
    String[] maps = {"Map 1.png", "Map 2.png"};
    MapSelection mapSelection;

    @BeforeEach
    void setup() {
        mapSelection = new MapSelection();
    }

    // TODO: test the draw() method??? How to test that...?

    @Test
    void testCreateItemsWithNoMaps() {
//        try {
//            // modify the private variable maps
//            Field maps = MapSelection.class.getDeclaredField("maps");
//            maps.setAccessible(true);
//            maps.set(mapSelection, null);
//        } catch (NoSuchFieldException | IllegalAccessException e) {
//            e.printStackTrace();
//        }

    }

    @Test
    void testSelectMapWithPointMapOne() {
        Point mouseLocation = new Point(0, 300);
        assertEquals(maps[0], mapSelection.selectMap(mouseLocation));
    }

    @Test
    void testSelectMapWithPointInvalidPoint() {
        Point mouseLocation = new Point(0, 0);
        assertNull(mapSelection.selectMap(mouseLocation));
    }

    @Test
    void testSelectMapWithIndexValidIndex() {
        assertEquals(maps[0], mapSelection.selectMap(0));
    }

    @Test
    void testSelectMapWithIndexInvalidIndex() {
        assertNull(mapSelection.selectMap(5));
    }

    @Test
    void testChangeSelectedMapUp() {
        assertEquals(0, mapSelection.changeSelectedMap(1, true));
    }

    @Test
    void testChangeSelectedMapUpWraparound() {
        assertEquals(1, mapSelection.changeSelectedMap(0, true));
    }

    @Test
    void testChangeSelectedMapDown() {
        assertEquals(1, mapSelection.changeSelectedMap(0, false));
    }

    @Test
    void testChangeSelectedMapDownWraparound() {
        assertEquals(0, mapSelection.changeSelectedMap(1, false));
    }
}