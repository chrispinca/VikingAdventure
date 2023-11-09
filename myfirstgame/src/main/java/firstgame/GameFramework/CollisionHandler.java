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

    // makes the calculations for the player's hitbox and accounts for the
    // displacement of the actual hitbox vs the entire player tile
    public void collisionCalc(Player entityy, LevelHandler level) {
        entityLeftX = entityy.x + 21;
        entityRightX = entityy.x + 21 + entityy.getHitboxWidth();
        entityTopY = entityy.y + 55;
        entityBottomY = entityy.y + 55 + entityy.getHitboxHeight();

        entityTopRow = entityTopY / level.getTileSize();
        entityBottomRow = entityBottomY / level.getTileSize();
        entityLeftCol = entityLeftX / level.getTileSize();
        entityRightCol = entityRightX / level.getTileSize();
    }

    // checks for collision with the closest two tiles in the direction the player
    // is moving in
    public void checkTile(Player entityy, LevelHandler level) {
        collisionCalc(entityy, level);

        switch (entityy.direction) {
            case UP:
                entityTopRow = (entityTopY - entityy.speed) / level.getTileSize();
                tileNum1 = gp.level.tileMap[entityTopRow][entityLeftCol];
                tileNum2 = gp.level.tileMap[entityTopRow][entityRightCol];
                if (tileNum1.collision == true || tileNum2.collision == true) {
                    entityy.collisionOn = true;
                }
                break;
            case DOWN:
                entityBottomRow = (entityBottomY + entityy.speed) / level.getTileSize();
                tileNum1 = gp.level.tileMap[entityBottomRow][entityLeftCol];
                tileNum2 = gp.level.tileMap[entityBottomRow][entityRightCol];
                if (tileNum1.collision == true || tileNum2.collision == true) {
                    entityy.collisionOn = true;
                }
                break;

            case LEFT:
                entityLeftCol = (entityLeftX - entityy.speed) / level.getTileSize();
                tileNum1 = gp.level.tileMap[entityTopRow][entityLeftCol];
                tileNum2 = gp.level.tileMap[entityBottomRow][entityLeftCol];
                if (tileNum1.collision == true || tileNum2.collision == true) {
                    entityy.collisionOn = true;
                    entityy.jumpOn = false;
                }
                break;

            case RIGHT:
                entityRightCol = (entityRightX + entityy.speed) / level.getTileSize();
                tileNum1 = gp.level.tileMap[entityTopRow][entityRightCol];
                tileNum2 = gp.level.tileMap[entityBottomRow][entityRightCol];
                if (tileNum1.collision == true || tileNum2.collision == true) {
                    entityy.collisionOn = true;
                    entityy.jumpOn = false;
                }
                break;

            case JUMP:
                entityTopRow = (int) ((entityTopY - 5) / level.getTileSize());
                tileNum1 = gp.level.tileMap[entityTopRow][entityLeftCol];
                tileNum2 = gp.level.tileMap[entityTopRow][entityRightCol];
                if (tileNum1.collision == true || tileNum2.collision == true) {
                    entityy.collisionOn = true;
                    entityy.jumpOn = false;

                } else {
                    entityy.jumpOn = true;
                }
                break;

            case JUMP_RIGHT:
                entityTopRow = (int) ((entityTopY - entityy.airspeed - 5) / level.getTileSize() + 2);
                entityRightCol = (int) ((entityRightX + entityy.speed) / level.getTileSize());
                tileNum1 = gp.level.tileMap[entityTopRow][entityRightCol];
                if (tileNum1.collision == true) {
                    entityy.collisionOn = true;
                    entityy.jumpOn = false;
                } else {
                    entityy.jumpOn = true;
                }
                break;

            case JUMP_LEFT:
                entityTopRow = (int) ((entityTopY - entityy.airspeed - 5) / level.getTileSize() + 2);
                entityLeftCol = (entityLeftX - entityy.speed) / level.getTileSize();
                tileNum1 = gp.level.tileMap[entityTopRow][entityLeftCol];
                if (tileNum1.collision == true) {
                    entityy.collisionOn = true;
                    entityy.jumpOn = false;
                } else {
                    entityy.jumpOn = true;
                }
                break;

        }
    }

    public void checkGround(Player entityy, LevelHandler level) {
        // Check if the player is on the ground
        entityBottomRow = (entityBottomY + entityy.speed) / level.getTileSize();
        tileNum1 = gp.level.tileMap[entityBottomRow][entityLeftCol];
        tileNum2 = gp.level.tileMap[entityBottomRow][entityRightCol];
        if ((tileNum1.collision || tileNum2.collision && entityy.collisionOn == false)
                || (tileNum1.collision || tileNum2.collision && entityy.onGround)) {
            // Player is on the ground
            entityy.onGround = true;

            int tileTopY = entityBottomRow * level.getTileSize();
            entityy.y = tileTopY - entityy.getHitboxHeight() - 56; // adjust player y-coordinate
        } else {
            // Player is falling
            entityy.onGround = false;
            for (int i = entityBottomRow + 1; i < level.getNumRows(); i++) {
                Tile tile1 = gp.level.tileMap[i][entityLeftCol];
                Tile tile2 = gp.level.tileMap[i][entityRightCol];
                if (tile1.collision || tile2.collision) {
                    // Player has landed on a tile
                    int tileTopY = i * level.getTileSize();
                    // entityy.y = tileTopY - entityy.getHitboxHeight() - 55; // adjust player
                    // y-coordinate
                    break;
                }
            }
        }
        if (entityy.onGround) {
            entityy.jumpCheck = false;
            entityy.jumpOn = false;
            entityy.airspeed = 0;
        }
    }

}
