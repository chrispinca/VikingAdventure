package firstgame.GameFramework;

import firstgame.entity.Entity;

public class CollisionHandler {

    GamePanel gp;
    
    public CollisionHandler(GamePanel gp) {
        this.gp = gp;
    }

        public void checkTile(Entity entity) {
            int entityLeftX = (int)entity.x + 8;
            int entityRightX = (int)entity.x + 8 + 32;
            int entityTopY = (int)entity.y + 16;
            int entityBottomY = (int)entity.y + 16 + 32;

            int entityLeftCol = entityLeftX/gp.tileSize;
            int entityRightCol = entityRightX/gp.tileSize;
            int entityTopRow = entityTopY/gp.tileSize;
            int entityBottomRow = entityBottomY/gp.tileSize;

            int tileNum1, tileNum2;

        }
}
