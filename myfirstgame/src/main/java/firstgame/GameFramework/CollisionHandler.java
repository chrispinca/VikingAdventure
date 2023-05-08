package firstgame.GameFramework;

import firstgame.entity.Entity;
import firstgame.entity.Player;
import firstgame.entity.Levels.LevelHandler;
import firstgame.entity.Levels.Tile;

public class CollisionHandler {

    int entityLeftX, entityRightX, entityTopY, 
    entityBottomY, entityTopRow, entityBottomRow, 
    entityLeftCol, entityRightCol;
        
    Tile tileNum1, tileNum2 = null;

    GamePanel gp;
    
    public CollisionHandler(GamePanel gp) {
        this.gp = gp;
    }

    //makes the calculations for the player's hitbox and accounts for the displacement of the actual hitbox vs the entire player tile
    public void collisionCalc(Player entityy, LevelHandler level) {
        entityLeftX = entityy.x + 21;
        entityRightX = entityy.x + 21+ entityy.getHitboxWidth();
        entityTopY = entityy.y + 55;
        entityBottomY = entityy.y+ 55 + entityy.getHitboxHeight();

        entityTopRow = entityTopY / level.getTileSize();
        entityBottomRow = entityBottomY / level.getTileSize();
        entityLeftCol = entityLeftX / level.getTileSize();
        entityRightCol = entityRightX / level.getTileSize();
    }

    //checks for collision with the closest two tiles in the direction the player is moving in
    public void checkTile(Player entityy, LevelHandler level) {
        collisionCalc(entityy, level);

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

