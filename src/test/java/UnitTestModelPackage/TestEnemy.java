package UnitTestModelPackage;

import model.enemy.Goomba;
import model.enemy.KoopaTroopa;
import org.junit.jupiter.api.Test;
import view.ImageLoader;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class TestEnemy {
    public Goomba goomba;
    public KoopaTroopa koopa;

    /**
     * Test Goomba draw
     * Branch 1: velX is not > 0
     */
    @Test
    public void testGoombaDraw00() {
        ImageLoader imageLoader = new ImageLoader();
        BufferedImage sprite = imageLoader.loadImage("/sprite.png");
        BufferedImage goombaStyle = imageLoader.getSubImage(sprite, 2, 4, 48, 48);
        goomba = new Goomba(50, 50, goombaStyle);
        goomba.setVelX(0);
        Graphics graphics = goombaStyle.getGraphics();
        goomba.draw(graphics);
    }

    /**
     * Test Goomba draw
     * Branch 2: velX > 0
     */
    @Test
    public void testGoombaDraw01() {
        ImageLoader imageLoader = new ImageLoader();
        BufferedImage sprite = imageLoader.loadImage("/sprite.png");
        BufferedImage goombaStyle = imageLoader.getSubImage(sprite, 2, 4, 48, 48);
        goomba = new Goomba(50, 50, goombaStyle);
        goomba.setVelX(1);
        Graphics graphics = goombaStyle.getGraphics();
        goomba.draw(graphics);
    }

    /**
     * Test Koopa draw
     * Branch 1: velX is not > 0
     */
    @Test
    public void testKoopaDraw00() {
        ImageLoader imageLoader = new ImageLoader();
        BufferedImage sprite = imageLoader.loadImage("/sprite.png");
        BufferedImage koopaStyle = imageLoader.getSubImage(sprite, 1, 3, 48, 64);
        koopa = new KoopaTroopa(50, 50, koopaStyle);
        koopa.setVelX(0);
        Graphics graphics = koopaStyle.getGraphics();
        koopa.draw(graphics);
    }

    /**
     * Test Koopa draw
     * Branch 2: velX > 0
     */
    @Test
    public void testKoopaDraw01() {
        ImageLoader imageLoader = new ImageLoader();
        BufferedImage sprite = imageLoader.loadImage("/sprite.png");
        BufferedImage koopaStyle = imageLoader.getSubImage(sprite, 1, 3, 48, 64);
        koopa = new KoopaTroopa(50, 50, koopaStyle);
        koopa.setVelX(1);
        Graphics graphics = koopaStyle.getGraphics();
        koopa.draw(graphics);
    }
}
