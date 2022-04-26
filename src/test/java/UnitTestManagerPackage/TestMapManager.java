package UnitTestManagerPackage;

import manager.GameEngine;
import manager.MapManager;
import model.hero.Mario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import view.ImageLoader;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TestMapManager {

    //blackbox - outputs and inputs, look at method names

    //integration based - testing how two classes are

    //unit test - just cre about the output

    //intgration - care about those out bound calls

    public GameEngine gameEngine;

    @BeforeEach
    public void setUpGameEngine() {
        gameEngine = new GameEngine();
    }

    @Test
    public void testMapCreationSucessfully() {
        ImageLoader imageLoader = new ImageLoader();
        MapManager mg = new MapManager();
        mg.createMap(imageLoader, "Map 1.png");
        assertNotNull(mg.map);
    }

    @Test
    public void testMapResetMapSucessfully() {
        ImageLoader imageLoader = new ImageLoader();
        MapManager mg = new MapManager();
        mg.createMap(imageLoader, "Map 1.png");
        mg.resetCurrentMap(gameEngine);

        Mario resetMario = mg.getMario();

        double marioVelX = resetMario.getVelX();
        assertEquals(marioVelX, 0, 0.001);

        double marioVelY = resetMario.getVelX();
        assertEquals(marioVelY, 0, 0.001);
        //use mockito to check that a method has been called?
    }



}
