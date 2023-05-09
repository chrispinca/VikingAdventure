package firstgame.entity;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

//values belonging to any object of type Entity
public abstract class Entity {
    public int x, y;
    public int speed;
    public String direction;
    public int spriteCounter = 0;
    public static int spriteNum = 1;
    public int width, height;
    protected Rectangle hitbox;
    public boolean collisionOn = false;
    public boolean inAir = true;

        public Entity() {
        }

        protected void drawHitbox(Graphics g) {
            //for debugging hitbox
            g.setColor(Color.PINK);
            g.drawRect(hitbox.x, hitbox.y, hitbox.width, hitbox.height);
        }

        protected void initHitbox(int width, int height) {
            hitbox = new Rectangle(x, y, width, height);
        }

        public void updateHitbox(int x, int y) {
            hitbox.x = x; 
            hitbox.y = y;
        }
        public Rectangle getHitbox() {
            return hitbox;
        }
        public int getHitboxX() {
            return hitbox.x;
        }
        
        public int getHitboxY() {
            return hitbox.y;
        }
        
        public int getHitboxWidth() {
            return hitbox.width;
        }
        
        public int getHitboxHeight() {
            return hitbox.height;
        }
        
}


