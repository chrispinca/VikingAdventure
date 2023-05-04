package firstgame.entity;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

//values belonging to any object of type Entity
public abstract class Entity {
    public float x, y;
    public int speed;
    public String direction;
    public int spriteCounter = 0;
    public static int spriteNum = 1;
    protected int width, height;
    protected Rectangle hitbox;

        public Entity() {
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
            initHitbox();
        }

        protected void drawHitbox(Graphics g) {
            //for debugging hitbox
            g.setColor(Color.PINK);
            g.drawRect(hitbox.x, hitbox.y, hitbox.width, hitbox.height);
        }

        private void initHitbox() {
            hitbox = new Rectangle((int)x, (int)y, width, height);
        }
        protected void updateHitbox() {
            hitbox.x = (int)x; 
            hitbox.y = (int)y;
        }
        public Rectangle getHitbox() {
            return hitbox;
        }
}


