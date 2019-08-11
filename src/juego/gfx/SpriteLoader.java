package juego.gfx;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;


public class SpriteLoader {
    private static BufferedImage spriteSheet;
    private static final int TILE_SIZE = 64;
    
    public static BufferedImage loadSpriteSheet(String path) {
        try {
            return ImageIO.read(SpriteLoader.class.getResource(path));
        } catch (IOException ex) {
            System.out.println("no se pudo cargar la hoja de sprites");
            Logger.getLogger(SpriteLoader.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public static BufferedImage getSprite(String file, int y, int x, int size) {
        spriteSheet = loadSpriteSheet("/res/textures/" + file + ".png");
        return spriteSheet.getSubimage(x * size, y * size, size, size);
    }
    
    public static BufferedImage getSprite2(String file, int x, int y, int size) {
        spriteSheet = loadSpriteSheet("/res/textures/" + file + ".png");
        return spriteSheet.getSubimage(x, y, size, size);
    }
    
    public static BufferedImage getSprite3(String file, int x, int y, int sizeX, int sizeY) {
        spriteSheet = loadSpriteSheet("/res/textures/" + file + ".png");
        return spriteSheet.getSubimage(x, y, sizeX, sizeY);
    }
}
