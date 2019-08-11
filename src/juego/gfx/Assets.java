package juego.gfx;

import java.awt.image.BufferedImage;


public class Assets {
    
    private static final int width=32, height=32;
    
    public static BufferedImage bloque,bloqueganar,bloque2;
    public static BufferedImage[] linkIdle, linkAttack, linkJump, linkMove, linkHurt;
    public static BufferedImage[] shadowIdle, shadowAttack, shadowJump, shadowMove, shadowHurt,shadowVacio;
    public static BufferedImage[] sonicIdle, sonicAttack, sonicJump, sonicMove, sonicHurt;
    public static BufferedImage[] megaIdle, megaAttack, megaJump, megaMove, megaHurt;
    public static BufferedImage robotizq,robotder,proyectil,proyectilder;
    public static BufferedImage background, backgroundSnow, backgroundSnow2, fondoTest;
    public static BufferedImage fondito;
     
    public static BufferedImage linkStock,shadowStock,sonicStock,megaStock;
    
    public static void init(){
        initLink();
        initShadow();
        initSonic();
        initMega();
        
        robotizq=SpriteLoader.loadSpriteSheet("/res/textures/" + "robot_0" + ".png");
        robotder=SpriteLoader.loadSpriteSheet("/res/textures/" + "robot_1" + ".png");
       
        backgroundSnow = SpriteLoader.loadSpriteSheet("/res/textures/" + "Background" + ".png");
        backgroundSnow2=SpriteLoader.loadSpriteSheet("/res/textures/" + "Background2" + ".png");
        bloque= SpriteLoader.loadSpriteSheet("/res/textures/" + "Bloque" + ".png");
        bloque2=SpriteLoader.loadSpriteSheet("/res/textures/" + "Bloque2" + ".png");
        bloqueganar = SpriteLoader.loadSpriteSheet("/res/textures/" + "Bloque_Ganar" + ".png");
        fondito=SpriteLoader.loadSpriteSheet("/Imagenes/" + "Estadistica_fondo" + ".jpg");
    }
    
     private static void initLink() {

        linkIdle = new BufferedImage[3];
        linkAttack = new BufferedImage[5];
        linkMove = new BufferedImage[6];
        linkJump = new BufferedImage[1];
        linkHurt = new BufferedImage[1];

        linkIdle[0] = SpriteLoader.getSprite("LinkSheet", 1, 1, 30);
        linkIdle[1] = SpriteLoader.getSprite("LinkSheet", 1, 0, 30);
        linkIdle[2] = SpriteLoader.getSprite("LinkSheet", 1, 2, 30);

        linkAttack[0] = SpriteLoader.getSprite2("LinkSheet", 238, 180, 29);
        linkAttack[1] = SpriteLoader.getSprite2("LinkSheet", 265, 179, 29); 
        linkAttack[2] = SpriteLoader.getSprite2("LinkSheet", 294, 179, 29); 
        linkAttack[3] = SpriteLoader.getSprite2("LinkSheet", 326, 180, 29);
        linkAttack[4] = SpriteLoader.getSprite2("LinkSheet", 359, 176, 29);

        linkMove[0] = SpriteLoader.getSprite("LinkSheet", 4, 8, 30);
        linkMove[1] = SpriteLoader.getSprite("LinkSheet", 4, 9, 30);
        linkMove[2] = SpriteLoader.getSprite("LinkSheet", 4, 10, 30);
        linkMove[3] = SpriteLoader.getSprite("LinkSheet", 4, 11, 30);
        linkMove[4] = SpriteLoader.getSprite("LinkSheet", 4, 12, 30);
        linkMove[5] = SpriteLoader.getSprite("LinkSheet", 4, 13, 30);

        linkJump[0] = SpriteLoader.getSprite("LinkSheet", 2, 6, 30);
        
        linkHurt[0] = SpriteLoader.getSprite("LinkSheet", 7, 4, 30);
        
     
    }
     
     private static void initShadow() {
        shadowIdle = new BufferedImage[5];
        shadowAttack = new BufferedImage[4];
        shadowMove = new BufferedImage[5];
        shadowJump = new BufferedImage[6];
        shadowHurt = new BufferedImage[1];
        shadowVacio=new BufferedImage[1];
       
        
        shadowIdle[0] = SpriteLoader.getSprite3("ShadowSheet", 3, 4, 27, 34);
        shadowIdle[1] = SpriteLoader.getSprite3("ShadowSheet", 35, 4, 28, 34);
        shadowIdle[2] = SpriteLoader.getSprite3("ShadowSheet", 70, 4, 30, 34);
        shadowIdle[3] = SpriteLoader.getSprite3("ShadowSheet", 106, 4, 30, 34);
        shadowIdle[4] = SpriteLoader.getSprite3("ShadowSheet", 140, 4, 28, 34);
        
        shadowMove[0] = SpriteLoader.getSprite3("ShadowSheet", 174, 4, 28, 34);
        shadowMove[1] = SpriteLoader.getSprite3("ShadowSheet", 210, 4, 27, 34);
        shadowMove[2] = SpriteLoader.getSprite3("ShadowSheet", 244, 4, 27, 35);
        shadowMove[3] = SpriteLoader.getSprite3("ShadowSheet", 285, 4, 30, 34);
        shadowMove[4] = SpriteLoader.getSprite3("ShadowSheet", 328, 4, 30, 34);
        
        shadowAttack[0] = SpriteLoader.getSprite3("ShadowSheet", 366, 526, 36, 34);
        shadowAttack[1] = SpriteLoader.getSprite3("ShadowSheet", 412, 526, 36, 34);
        shadowAttack[2] = SpriteLoader.getSprite3("ShadowSheet", 457, 524, 36, 34);
        shadowAttack[3] = SpriteLoader.getSprite3("ShadowSheet", 501, 524, 36, 34);
        
        shadowJump[0] = SpriteLoader.getSprite3("ShadowSheet", 184, 100, 32, 38);
        shadowJump[1] = SpriteLoader.getSprite3("ShadowSheet", 224, 108, 32, 34);
        shadowJump[2] = SpriteLoader.getSprite3("ShadowSheet", 266, 104, 32, 34);
        shadowJump[3] = SpriteLoader.getSprite3("ShadowSheet", 342, 110, 32, 28);
        shadowJump[4] = SpriteLoader.getSprite3("ShadowSheet", 446, 102, 32, 34);
        shadowJump[5] = SpriteLoader.getSprite3("ShadowSheet", 526, 108, 30, 34);
        
        shadowHurt[0] = SpriteLoader.getSprite3("ShadowSheet", 698, 573, 34, 34);
           
    }
    private static void initSonic(){
        sonicIdle=new BufferedImage[5];
        sonicAttack= new BufferedImage[8];
        sonicMove= new BufferedImage[12];
        sonicJump=new BufferedImage[1];
        sonicHurt=new BufferedImage[6];
                
        sonicIdle[0]= SpriteLoader.getSprite2("Sonic1", 8, 12,40);
        sonicIdle[1]= SpriteLoader.getSprite2("Sonic1", 54, 12,40);
        sonicIdle[2]= SpriteLoader.getSprite2("Sonic1", 100, 12,40);
        sonicIdle[3]= SpriteLoader.getSprite2("Sonic1", 146, 12,40);
        sonicIdle[4]= SpriteLoader.getSprite2("Sonic1", 192, 12,40);
        
        sonicMove[0]= SpriteLoader.getSprite2("Sonic1", 8, 67,40);
        sonicMove[1]= SpriteLoader.getSprite2("Sonic1", 54, 67,40);
        sonicMove[2]= SpriteLoader.getSprite2("Sonic1", 100, 67,40);
        sonicMove[3]= SpriteLoader.getSprite2("Sonic1", 146, 67,40);
        sonicMove[4]= SpriteLoader.getSprite2("Sonic1", 192, 67,40);
        sonicMove[5]= SpriteLoader.getSprite2("Sonic1", 238, 67,40);
        sonicMove[6]= SpriteLoader.getSprite2("Sonic1", 284, 67,40);
        sonicMove[7]= SpriteLoader.getSprite2("Sonic1", 330, 67,40);
        sonicMove[8]= SpriteLoader.getSprite2("Sonic1", 376, 67,40);
        sonicMove[9]= SpriteLoader.getSprite2("Sonic1", 422, 67,40);
        sonicMove[10]= SpriteLoader.getSprite2("Sonic1", 468, 67,40);
        sonicMove[11]= SpriteLoader.getSprite2("Sonic1", 514, 67,40);
            
        sonicAttack[0]=SpriteLoader.getSprite2("Sonic1", 8, 118,40);
        sonicAttack[1]=SpriteLoader.getSprite2("Sonic1", 54, 118,40);
        sonicAttack[2]=SpriteLoader.getSprite2("Sonic1", 100, 118,40);
        sonicAttack[3]=SpriteLoader.getSprite2("Sonic1", 154, 118,40);
        sonicAttack[4]=SpriteLoader.getSprite2("Sonic1", 200, 118,40);
        sonicAttack[5]=SpriteLoader.getSprite2("Sonic1", 250, 118,40);
        sonicAttack[6]=SpriteLoader.getSprite2("Sonic1", 300, 118,40);
        sonicAttack[7]=SpriteLoader.getSprite2("Sonic1", 340, 118,40);

        sonicHurt[0]= SpriteLoader.getSprite2("Sonic1", 8, 12,40);
        sonicHurt[1]= SpriteLoader.getSprite2("sonicTra1", 8, 12,40);
        sonicHurt[2]= SpriteLoader.getSprite2("sonicTra2", 8, 12,40);
        sonicHurt[3]= SpriteLoader.getSprite2("Sonic1", 400, 118,40);
        sonicHurt[4]= SpriteLoader.getSprite2("Sonic1", 400, 118,40);
        sonicHurt[5]= SpriteLoader.getSprite2("Sonic1", 400, 118,40);
        
        sonicJump[0]=SpriteLoader.getSprite2("Sonic1", 8, 173,40);
        
        
    }
    
     private static void initMega(){
        megaIdle=new BufferedImage[1];
        megaAttack= new BufferedImage[8];
        megaMove= new BufferedImage[5];
        megaJump=new BufferedImage[11];
        megaHurt=new BufferedImage[6];
        
         megaIdle[0]=SpriteLoader.getSprite2("mg_move", 0, 4,45);

         megaMove[0]=SpriteLoader.getSprite2("mg_move", 55, 4,45);
         megaMove[1]=SpriteLoader.getSprite2("mg_move", 110, 4,45);
         megaMove[2]=SpriteLoader.getSprite2("mg_move", 165, 4,45);
         megaMove[3]=SpriteLoader.getSprite2("mg_move", 220, 4,45);
         megaMove[4]=SpriteLoader.getSprite2("mg_move", 275, 4,45);

         megaJump[0]=SpriteLoader.getSprite3("mg_jump", 0, 7,43,48);
         megaJump[1]=SpriteLoader.getSprite3("mg_jump", 35, 7,43,48);
         megaJump[2]= SpriteLoader.getSprite3("mg_jump", 70,7,43,48);
         megaJump[3]= SpriteLoader.getSprite3("mg_jump", 110,7,43,48);
         megaJump[4]= SpriteLoader.getSprite3("mg_jump", 146,9,43,48);
         megaJump[5]= SpriteLoader.getSprite3("mg_jump", 186,9,43,48);
         megaJump[6]= SpriteLoader.getSprite3("mg_jump", 228,9,43,48);
         megaJump[7]= SpriteLoader.getSprite3("mg_jump", 263,9,43,48);
         megaJump[8]= SpriteLoader.getSprite3("mg_jump", 305,9,43,48);
         megaJump[9]= SpriteLoader.getSprite3("mg_jump", 340,9,43,48);
         megaJump[10]= SpriteLoader.getSprite3("mg_jump", 380,9,43,48);
     
        megaAttack[0]=SpriteLoader.getSprite2("mg_impulse",0,5,50);
        megaAttack[1]=SpriteLoader.getSprite2("mg_impulse",83,5,50); 
        megaAttack[2]=SpriteLoader.getSprite2("mg_impulse",166,5,50); 
        megaAttack[3]=SpriteLoader.getSprite3("mg_impulse",252,5,70,50); 
        megaAttack[4]=SpriteLoader.getSprite3("mg_impulse",338,5,80,50); 
        megaAttack[5]=SpriteLoader.getSprite3("mg_impulse",422,5,80,50); 
        megaAttack[6]=SpriteLoader.getSprite3("mg_impulse",505,5,80,50);
        megaAttack[7]=SpriteLoader.getSprite3("mg_impulse",588,5,80,50); 
        
        megaHurt[0]=SpriteLoader.getSprite2("mg_move", 0, 4,45);
        megaHurt[1]=SpriteLoader.getSprite2("megaprueba1", 0, 4,45);
        megaHurt[2]=SpriteLoader.getSprite2("megaprueba", 0, 4,45);
        megaHurt[3]= SpriteLoader.getSprite2("Sonic1", 400, 118,40);
        megaHurt[4]= SpriteLoader.getSprite2("Sonic1", 400, 118,40);
        megaHurt[5]= SpriteLoader.getSprite2("Sonic1", 400, 118,40);
     }
}
