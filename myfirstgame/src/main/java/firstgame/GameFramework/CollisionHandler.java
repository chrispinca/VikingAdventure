package firstgame.GameFramework;

import firstgame.entity.Entity;
import firstgame.entity.Player;
import firstgame.entity.Levels.LevelHandler;
import firstgame.entity.Levels.Tile;

public class CollisionHandler {

    GamePanel gp;
    
    public CollisionHandler(GamePanel gp) {
        this.gp = gp;
    }

    public void checkTile(Player entityy, LevelHandler level) {

       

        int entityLeftX = entityy.x + 21;
        int entityRightX = entityy.x + 21+ entityy.getHitboxWidth();
        int entityTopY = entityy.y + 55;
        int entityBottomY = entityy.y+ 55 + entityy.getHitboxHeight();


        int entityTopRow = entityTopY / level.getTileSize();
        int entityBottomRow = entityBottomY / level.getTileSize();
        int entityLeftCol = entityLeftX / level.getTileSize();
        int entityRightCol = entityRightX / level.getTileSize();
        
        Tile tileNum1, tileNum2 = null;

        switch(entityy.direction) {
            case "up":
                entityTopRow = (entityTopY - entityy.speed)/level.getTileSize();
                tileNum1 = gp.level.tileMap[entityTopRow][entityLeftCol];
                tileNum2 = gp.level.tileMap[entityTopRow][entityRightCol];
                if(tileNum1.collision == true || tileNum2.collision == true) {
                    entityy.collisionOn = true;
                }
                break;
            case "down":
                entityBottomRow = (entityBottomY + entityy.speed)/level.getTileSize();
                tileNum1 = gp.level.tileMap[entityBottomRow][entityLeftCol];
                tileNum2 = gp.level.tileMap[entityBottomRow][entityRightCol];
                if(tileNum1.collision == true || tileNum2.collision == true) {
                    entityy.collisionOn = true;
                }
                break;

            case "left":
                entityLeftCol = (entityLeftX - entityy.speed)/level.getTileSize();
                tileNum1 = gp.level.tileMap[entityTopRow][entityLeftCol];
                tileNum2 = gp.level.tileMap[entityBottomRow][entityLeftCol];
                if(tileNum1.collision == true || tileNum2.collision == true) {
                    entityy.collisionOn = true;
                }
                break;

            case "right":
                entityRightCol = (entityRightX + entityy.speed)/level.getTileSize();
                tileNum1 = gp.level.tileMap[entityTopRow][entityRightCol];
                tileNum2 = gp.level.tileMap[entityBottomRow][entityRightCol];
                if(tileNum1.collision == true || tileNum2.collision == true) {
                    entityy.collisionOn = true;
                }
                break;
            
        }
    
    }
    
    
}

