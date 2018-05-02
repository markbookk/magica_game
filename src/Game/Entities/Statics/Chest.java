package Game.Entities.Statics;

import Game.Entities.Creatures.Player;
import Game.GameStates.State;
import Game.Items.Item;
import Main.Handler;
import Resources.Images;
import Worlds.BaseWorld;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by Elemental on 2/2/2017.
 */


public class Chest extends StaticEntity {

    private Rectangle ir = new Rectangle();
    public Boolean EP = false;

    private BaseWorld world;
    
    private BufferedImage chestStateImage;
    public static int skullsPicked = 0;
    public static int stickesPicked = 0;
    public static boolean ePressedStatic = false;
    public static boolean completedQuest = false;
    

    public Chest(Handler handler, float x, float y,BaseWorld world) {
        super(handler, x, y, 80, 125);
        this.world=world;
        health=10000000;
        bounds.x=0;
        bounds.y=0;
        bounds.width = 125;
        bounds.height = 80;
        

        ir.width = bounds.width;
        ir.height = bounds.height;
        int irx=(int)(bounds.x-handler.getGameCamera().getxOffset()+x);
        int iry= (int)(bounds.y-handler.getGameCamera().getyOffset()+height);
        ir.y=iry;
        ir.x=irx;
        
        //ME
        chestStateImage = Images.chest;
    }

    @Override
    public void tick() {

        if(isBeinghurt()){
        		chestStateImage = Images.openedChest;
            setHealth(10000000);
            handler.getWorld().getEntityManager().getPlayer().itemsToChest();
            
        }

        if(handler.getKeyManager().attbut){
            EP=true;
            ePressedStatic = true;

        }else if(!handler.getKeyManager().attbut){
            EP=false;
            ePressedStatic = false;
        }
        
        if ((skullsPicked >= 3) && (stickesPicked) >=3) {
        		completedQuest = true;
        }
        
    }
    

    @Override
    public void render(Graphics g) {
    		g.drawImage(chestStateImage,(int)(x-handler.getGameCamera().getxOffset()),(int)(y-handler.getGameCamera().getyOffset()),width,height,null);
//        checkForPlayer(g, handler.getWorld().getEntityManager().getPlayer());
        if (chestStateImage == Images.openedChest) {
        		g.setColor(Color.BLACK);
        		g.setFont(new Font("Lucida", Font.BOLD, 20));
    			g.drawString("Sticks:" + stickesPicked + "\nSkulls:" + skullsPicked,(int)(x-handler.getGameCamera().getxOffset()-10),(int)(y-handler.getGameCamera().getyOffset()-30));
    			g.drawString("Items needed:",(int)(x-handler.getGameCamera().getxOffset()),(int)(y-handler.getGameCamera().getyOffset()-10));
        }
    }

//    private void checkForPlayer(Graphics g, Player p) {
//        Rectangle pr = p.getCollisionBounds(0, 0);
//
//        if(ir.contains(pr) && !EP){
//        		System.out.println("test");
//            g.drawImage(Images.E,(int) x+width,(int) y+10,32,32,null);
//        }else if(ir.contains(pr) && EP){
//            g.drawImage(Images.EP,(int) x+width,(int) y+10,32,32,null);
//            g.drawImage(Images.loading,0,0,800,600,null);
//            handler.setWorld(world);
//
//        }
//
//
//    }

    @Override
    public void die() {

    }
}
