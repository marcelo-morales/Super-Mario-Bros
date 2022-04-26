package UnitTestManagerPackage;

import manager.Camera;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestCamera {

    Camera camera;

    @BeforeEach
    public void setUpCamera() {
        camera = new Camera();
    }

    @Test
    public void testSetThenGetXCoordinate() {
        camera.setX(1.0);
        assertEquals(camera.getX(), 1.0, 0.001);
    }

    @Test
    public void getSetThenGetYCoordinate() {
        camera.setY(1.0);
        assertEquals(camera.getY(), 1.0, 0.001);
    }

    @Test
    public void testShakeCamera() {
        camera.shakeCamera();
    }

    @Test
    public void testMoveCameraAfterShake() {
        camera.shakeCamera();
        camera.moveCam(1.0, 1.0);
        assertEquals(camera.getX(), 4.0, 0.001);
    }

    @Test
    public void testMoveCameraNoShake(){
        camera.moveCam(5.0, 5.0);
        assertEquals(camera.getX(), 5.0);
        assertEquals(camera.getY(), 5.0);
    }


}
