package view;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class to test the StartScreenSelection enum class.
 *
 * Blackbox testing not utilized because it is not possible to assume behavior of enum class.
 * Whitebox testing for each method, aim to achieve branch coverage.
 * Mutation testing, aim to achieve maximum mutation coverage.
 *
 * Notes:
 *      not possible to achieve branch coverage in the select() method.
 *      line 24 logical condition never evaluates to false because there are no enumerations with integer values
 *      smaller than 0 or greater than 2.
 */
public class StartScreenSelectionTest {

    /**
     * Test getSelection() with 0.
     */
    @Test
    void testGetSelectionZero() {
        assertEquals(StartScreenSelection.START_GAME, StartScreenSelection.START_GAME.getSelection(0));
    }

    /**
     * Test getSelection() with 1.
     */
    @Test
    void testGetSelectionOne() {
        assertEquals(StartScreenSelection.VIEW_HELP, StartScreenSelection.START_GAME.getSelection(1));
    }

    /**
     * Test getSelection() with 2.
     */
    @Test
    void testGetSelectionTwo() {
        assertEquals(StartScreenSelection.VIEW_ABOUT, StartScreenSelection.START_GAME.getSelection(2));
    }

    /**
     * Test getSelection() with invalid integer.
     */
    @Test
    void testGetSelectionInvalid() {
        assertEquals(null, StartScreenSelection.START_GAME.getSelection(-1));
    }

    /**
     * Test select() with no wraparound executed.
     */
    @Test
    void testSelectNoWraparound() {
        assertEquals(StartScreenSelection.VIEW_HELP, StartScreenSelection.START_GAME.select(false));
    }

    /**
     * Test select() with a wraparound upwards.
     */
    @Test
    void testSelectWraparoundUp() {
        assertEquals(StartScreenSelection.VIEW_ABOUT, StartScreenSelection.START_GAME.select(true));
    }

    /**
     * Test select() with a wraparound downwards.
     */
    @Test
    void testSelectWraparoundDown() {
        assertEquals(StartScreenSelection.START_GAME, StartScreenSelection.VIEW_ABOUT.select(false));
    }

    /**
     * Test getLineNumber().
     */
    @Test
    void testGetLineNumber() {
        assertEquals(0, StartScreenSelection.START_GAME.getLineNumber());
    }
}
