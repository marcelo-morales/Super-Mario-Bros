package UnitTestModelPackage;

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

    @Test
    public void testOrdinaryBrickReveal() {

    }

    @Test
    public void testOrdinaryBrickAnimate() {

    }

    @Test
    public void testSurpriseBrickReveal() {

    }
}
