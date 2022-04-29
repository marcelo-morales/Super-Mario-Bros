package UnitTestManagerPackage;

import manager.SoundManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class TestSoundManager {

    SoundManager soundManager;

    @BeforeEach
    public void setUpSoundManager(){
        soundManager = new SoundManager();
    }

    /*
    Test that when resume background sound can successfully be played.
     */
    @Test
    public void testResumeBackground() {

        soundManager.resumeBackground();
        assertTrue(soundManager.background.isOpen());
    }

    /*
   Test that when pause background sound can successfully be played.
    */
    @Test
    public void testPauseBackground() {
        soundManager.pauseBackground();
        assertFalse(soundManager.background.isRunning());
    }

    /*
   Test that restart background sound can successfully be played.
    */
    @Test
    public void testRestartBackground() {
        soundManager.restartBackground();
        soundManager.pauseBackground();
        soundManager.restartBackground();
        assertTrue(soundManager.background.isRunning());
    }

    /*
    Test sound outputted when Mario jumps.
     */
    @Test
    public void testPlayJump(){
        soundManager.playJump();
        assertNotNull(soundManager);
    }

    /*
   Test sound when user receives a coin.
    */
    @Test
    public void testPlayCoin(){
        soundManager.playCoin();
        assertNotNull(soundManager);
    }

    /*
  Test sound when user encounters a fireball.
   */
    @Test
    public void testPlayFireball(){
        soundManager.playFireball();
        assertNotNull(soundManager);
    }

    /*
  Test sound when the game ends.
   */
    @Test
    public void testGameOver() {
        soundManager.playGameOver();
        assertNotNull(soundManager);
    }

    /*
 Test the sound when Mario stomps on an enemy.
  */
    @Test
    public void testPlayStomp() {
        soundManager.playStomp();
        assertNotNull(soundManager);
    }

    /*
  Test the sound when Mario receives a one up
   */
    @Test
    public void testPlayOneUp() {
        soundManager.playOneUp();
        assertNotNull(soundManager);

    }

    /*
   Test the sound that is outputted when a mushroom is received.
    */
    @Test
    public void testPlaySuperMushroom() {
        soundManager.playSuperMushroom();
        assertNotNull(soundManager);

    }

    /*
   Test the sound that is outputted when Mario dies.
    */
    @Test
    public void testPlayMarioDies() {
        soundManager.playMarioDies();
        assertNotNull(soundManager);
    }



}
