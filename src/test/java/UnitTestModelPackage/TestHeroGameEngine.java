package UnitTestModelPackage;

import manager.GameEngine;
import model.hero.Mario;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class TestHeroGameEngine {
    public Mario mario;
    public static GameEngine eng;

    @BeforeAll
    static void setUp() {
        eng = new GameEngine();
    }

    /**
     * test Mario class's onTouchEnemy method.
     * Branch 1: marioForm.isSuper() or marioForm.isFire()
     */
    @Test
    public void testMarioOnTouchEnemy00() {
        mario = new Mario(50, 50);
        mario.getMarioForm().setSuper(true);

        boolean ret = mario.onTouchEnemy(eng);

        assert(ret == false);
        assert(mario.getDimension().height == 48);
        assert(mario.getDimension().width == 48);
    }

    /**
     * test Mario class's onTouchEnemy method.
     * Branch 2: !marioForm.isSuper() and !marioForm.isFire()
     */
    @Test
    public void testMarioOnTouchEnemy01() {
        mario = new Mario(50, 50);
        int startLives = mario.getRemainingLives();

        boolean ret = mario.onTouchEnemy(eng);

        assert(ret == true);
        assert(mario.getRemainingLives() == startLives - 1);
    }

    /**
     * test Mario class jump method.
     * Branch 1: !isJumping() && !isFalling()
     */
    @Test
    public void testMarioJump00() {
        mario = new Mario(50, 50);
        mario.setFalling(false);

        mario.jump(eng);

        assert(mario.isJumping());
        //in the game, the initial velocity of a jump is 10
        assert(mario.getVelY() == 10);
    }

    /**
     * test Mario class jump method.
     * Branch 1: isJumping() or isFalling()
     */
    @Test
    public void testMarioJump01() {
        mario = new Mario(50, 50);
        mario.setJumping(true);
        mario.setVelY(9);

        mario.jump(eng);

        //assert nothing changed
        assert(mario.getVelY() == 9);
    }
}
