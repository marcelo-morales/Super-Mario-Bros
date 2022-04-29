package UnitTestManagerPackage;

import manager.Camera;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestCamera {

    Camera camera;

    @BeforeEach
    public void setUpCamera() {
        camera = new Camera();
    }

    /*
    Whitebox testing - camera's X coordinate changes after setting it.
     */
    @Test
    public void testSetThenGetXCoordinate() {
        camera.setX(1.0);
        assertEquals(camera.getX(), 1.0, 0.001);
    }

    /*
    Whitebox testing - camera's Y coordinate changes after setting it.
     */
    @Test
    public void getSetThenGetYCoordinate() {
        camera.setY(1.0);
        assertEquals(camera.getY(), 1.0, 0.001);
    }


    /*
    Whitebox testing - camera is able to be successfully moved after shaking the camera.
     */
    @Test
    public void testMoveCameraAfterShake() {
        camera.shakeCamera();
        camera.moveCam(1.0, 1.0);
        assertEquals(camera.getX(), 4.0, 0.001);
    }

    /*
    Whitebox testing - camera is moved to actual coordinates after moveCam() is called.
     */
    @Test
    public void testShakeCameraNoShake(){
        camera.moveCam(5.0, 5.0);
        assertEquals(camera.getX(), 5.0);
        assertEquals(camera.getY(), 5.0);
    }

    @Test
     /*
     Whitebox testing - camera does not shake with a provided negative number and shaking set to true previously.
      */
    public void testShakeCameraWithShakingAndNoFrameNumber() {
        camera.shaking = true;
        camera.frameNumber = -5;
        camera.moveCam(6.0, 6.0);
        assertFalse(camera.shaking);
    }

    /*
    Whitebox testing - camera does  shake when camera is moved wih a positive frame number.
     */
    @Test
    public void testShakeCameraWithShakingAndFrameNumber() {
        camera.shaking = true;
        camera.frameNumber = 20;
        camera.moveCam(6.0, 6.0);
        assertTrue(camera.shaking);
    }

    /*
    Whitebox testing - camera does not shake when camera is moved wih a positive frame number.
     */
    @Test
    public void testShakeCameraNoShakingAndFrameNumber() {
        camera.shaking = false;
        camera.frameNumber = 20;
        camera.moveCam(6.0, 6.0);
        assertFalse(camera.shaking);
    }

    /*
    Whitebox testing - camera does not shake when camera is moved wih a negative frame number with shaking set to false before.
     */
    @Test
    public void testShakeCameraNoShakingAndNoFrameNumber() {
        camera.shaking = false;
        camera.frameNumber = -20;
        camera.moveCam(6.0, 6.0);
        assertFalse(camera.shaking);
    }

    /*
     Whitebox testing - camera does not shake when camera is moved wih a negative frame number.
      */
    @Test
    public void testMoveCamWithNegativeFrameNum() {
        camera.frameNumber = -5;
        camera.moveCam(10.0, 10.0);
        assertFalse(camera.shaking);
    }




}
