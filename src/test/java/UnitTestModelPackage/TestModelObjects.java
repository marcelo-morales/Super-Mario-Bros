package UnitTestModelPackage;

import model.EndFlag;
import model.Map;
import model.brick.OrdinaryBrick;
import model.enemy.Goomba;
import model.hero.Fireball;
import model.hero.Mario;
import model.prize.Coin;
import model.prize.FireFlower;
import org.junit.jupiter.api.Test;
import view.ImageLoader;

import java.awt.*;
import java.awt.image.BufferedImage;

public class TestModelObjects {
    /**
     * test Map class updateLocation method.
     * Integration testing. Tests that map calls all appropriate methods
     * from classes of its fields when updating location.
     */
    @Test
    public void testMapUpdateLocations() {
        ImageLoader imageLoader = new ImageLoader();
        BufferedImage mapStyle = imageLoader.loadImage("/background.png");
        Map map = new Map(50, mapStyle);
        //add mario
        Mario mario1 = new Mario(50, 50);
        Mario mario2 = new Mario(50, 50);
        map.setMario(mario1);
        //add enemies
        BufferedImage sprite = imageLoader.loadImage("/sprite.png");
        BufferedImage goombaStyle = imageLoader.getSubImage(sprite, 2, 4, 48, 48);
        Goomba goomba1 = new Goomba(60, 60, goombaStyle);
        Goomba goomba2 = new Goomba(60, 60, goombaStyle);
        map.addEnemy(goomba1);
        //add prizes
        BufferedImage coinStyle = imageLoader.getSubImage(sprite, 1, 5, 48, 48);
        Coin coin = new Coin(70, 70, coinStyle, 5);
        coin.reveal();
        map.addRevealedPrize(coin);
        BufferedImage fireStyle = imageLoader.getSubImage(sprite, 4, 5, 48, 48);
        FireFlower fireFlower = new FireFlower(110, 110, fireStyle);
        map.addRevealedPrize(fireFlower);
        //add fireballs
        BufferedImage fireballStyle = imageLoader.getSubImage(sprite, 4, 5, 48, 48);
        Fireball fireball1 = new Fireball(80, 80, fireballStyle, true);
        Fireball fireball2 = new Fireball(80, 80, fireballStyle, true);
        map.addFireball(fireball1);
        //add bricks
        BufferedImage brickStyle = imageLoader.getSubImage(sprite, 1, 1, 48, 48);
        OrdinaryBrick brick = new OrdinaryBrick(100, 100, brickStyle);
        map.addRevealedBrick(brick);
        //add endpoint
        BufferedImage flagStyle = imageLoader.getSubImage(sprite, 5, 1, 48, 48);
        EndFlag endflag1 = new EndFlag(90, 90, flagStyle);
        EndFlag endflag2 = new EndFlag(90, 90, flagStyle);
        map.setEndPoint(endflag1);

        map.updateLocations();

        //check mario
        mario2.updateLocation();
        assert(map.getMario().getX() == mario2.getX());
        assert(map.getMario().getY() == mario2.getY());
        //check enemies
        goomba2.updateLocation();
        assert(map.getEnemies().get(0).getX() == goomba2.getX());
        assert(map.getEnemies().get(0).getY() == goomba2.getY());
        //check fireballs
        fireball2.updateLocation();
        assert(map.getFireballs().get(0).getX() == fireball2.getX());
        assert(map.getFireballs().get(0).getY() == fireball2.getY());
        //update endpoint
        endflag2.updateLocation();
        assert(map.getEndPoint().getX() == endflag2.getX());
        assert(map.getEndPoint().getY() == endflag2.getY());
    }

    /**
     * test EndFlag class updateLocation method.
     * Branch 1: touched and (getY() + getDimension().getHeight() >= 576)
     */
    @Test
    public void testEndFlagUpdateLocation00() {
        ImageLoader imageLoader = new ImageLoader();
        BufferedImage flagStyle = imageLoader.loadImage("/sprite.png");
        flagStyle = imageLoader.getSubImage(flagStyle, 5, 1, 48, 48);
        EndFlag flag = new EndFlag(50, 50, flagStyle);

        flag.setTouched(true);
        flag.setY(575);
        flag.setDimension(5, 3);
        flag.updateLocation();

        assert(!flag.isFalling());
        assert(flag.getY() == 573);
    }

    /**
     * test EndFlag class updateLocation method.
     * Branch 2: touched and (getY() + getDimension().getHeight() > 576)
     */
    @Test
    public void testEndFlagUpdateLocation01() {
        ImageLoader imageLoader = new ImageLoader();
        BufferedImage flagStyle = imageLoader.loadImage("/sprite.png");
        flagStyle = imageLoader.getSubImage(flagStyle, 5, 1, 48, 48);
        EndFlag flag = new EndFlag(50, 50, flagStyle);

        flag.setTouched(true);
        flag.updateLocation();

        assert(flag.getX() == 50);
        assert(flag.getY() == 50);
    }

    /**
     * test EndFlag class updateLocation method.
     * Branch 2: not touched
     */
    @Test
    public void testEndFlagUpdateLocation02() {
        ImageLoader imageLoader = new ImageLoader();
        BufferedImage flagStyle = imageLoader.loadImage("/sprite.png");
        flagStyle = imageLoader.getSubImage(flagStyle, 5, 1, 48, 48);
        EndFlag flag = new EndFlag(50, 50, flagStyle);

        flag.setTouched(false);
        flag.updateLocation();

        assert(flag.getX() == 50);
        assert(flag.getY() == 50);
    }
}
