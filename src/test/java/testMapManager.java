import manager.MapManager;
import org.junit.jupiter.api.Test;
import view.ImageLoader;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class testMapManager {

    @Test
    public void testMapCreationSucessfully() {
        ImageLoader imageLoader = new ImageLoader();
        MapManager mg = new MapManager();
        mg.createMap(imageLoader, "Map 1.png");
        assertNotNull(mg.map);
    }

}
