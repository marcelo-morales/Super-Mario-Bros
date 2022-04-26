package UnitTestManagerPackage;

import manager.SoundManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class TestSoundManager {

    SoundManager soundManager;

    @BeforeEach
    public void setUpSoundManager(){
        soundManager = new SoundManager();
    }

    @Test
    public void testResumeBackground() {

        soundManager.resumeBackground();
        assertTrue(soundManager.background.isOpen());
    }

    @Test
    public void testPauseBackground() {
        soundManager.pauseBackground();
        assertFalse(soundManager.background.isRunning());
    }

    @Test
    public void testRestartBackground() {
        soundManager.restartBackground();
        soundManager.pauseBackground();
        soundManager.restartBackground();
        assertTrue(soundManager.background.isRunning());
    }

    @Test
    /*
    Test sound outputted when Mario jumps.
     */
    public void testPlayJump(){
        soundManager.playJump();

    }

    @Test
    /*
    Test sound when user receives a coin.
     */
    public void testPlayCoin(){
        soundManager.playCoin();

    }

    @Test
    /*
    Test sound when user encounters a fireball.
     */
    public void testPlayFireball(){
        soundManager.playFireball();

    }

    @Test
    /*
    Test sound when the game ends.
     */
    public void testGameOver() {
        soundManager.playGameOver();

    }

    @Test
    /*
    Test the sound when Mario stomps on an enemy.
     */
    public void testPlayStomp() {
        soundManager.playStomp();

    }

    @Test
    /*
    Test the sound when Mario receives a one up
     */
    public void testPlayOneUp() {
        soundManager.playOneUp();

    }

    @Test
    /*
    Test the sound that is outputted when a mushroom is received.
     */
    public void testPlaySuperMushroom() {
        soundManager.playSuperMushroom();

    }

    @Test
    /*
    Test the sound that is outputted when Mario dies.
     */
    public void testPlayMarioDies() {
        soundManager.playMarioDies();
    }



}
