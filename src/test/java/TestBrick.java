import manager.GameEngine;
import model.brick.GroundBrick;
import model.brick.OrdinaryBrick;
import model.brick.SurpriseBrick;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * GroundBrick, OrdinaryBrick, Pipe, and SurpriseBrick
 * are model classes that extend abstract class Brick.
 * Only classes OrdinaryBrick and SurpriseBrick contain
 * methods other than constructors and setter/getters
 * that are tested in this test class.
 */
public class TestBrick {
    OrdinaryBrick ord;
    SurpriseBrick surp;

    /**
     * Might be better for interaction-based testing,
     * because function primarily only changes given
     * game engine object.
     * Have to run main to get GameEngine object,
     * opportunity for mock-testing?
     */
    @Test
    public void testOrdinaryBrickReveal() {
        //GameEngine eng = new GameEngine();

    }

    @Test
    public void testOrdinaryBrickUpdateLocation() {

    }

    @Test
    public void testOrdinaryBrickAnimate() {

    }

    /**
     * Might be better for interaction-based testing,
     * because function primarily only changes given
     * game engine object.
     * Have to run main to get GameEngine object,
     * opportunity for mock-testing?
     */
    @Test
    public void testSurpriseBrickReveal() {

    }

    @Test
    public void testSurpriseBrickUpdateLocation() {

    }
}
