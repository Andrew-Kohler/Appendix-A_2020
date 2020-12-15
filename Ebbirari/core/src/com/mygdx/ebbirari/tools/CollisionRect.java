package com.mygdx.ebbirari.tools;


import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.ebbirari.screens.MainScreen;

public class CollisionRect {

    // Constants
    private final int kCollisionRange = 16;

    // Variable Instantiations
    float x, y;
    float width, height;

    public CollisionRect (float x, float y, float width, float height) {
        // Variable Declarations
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public void move (float x, float y) {
        this.x = x;
        this.y = y;
    }

    public void resize(float width, float height){
        this.width = width;
        this.height = height;
    }

    public boolean collidesWith (CollisionRect rect) {
        return x < rect.x + rect.width && y < rect.y + rect.height && x + width > rect.x && y + height > rect.y;
    }

    public boolean collidesWithBottomOf(CollisionRect rect) {
        return new CollisionRect(x, y + height - kCollisionRange, width, kCollisionRange).collidesWith(new CollisionRect(rect.x, rect.y, rect.width, kCollisionRange));
    }

    public boolean collidesWithTopOf(CollisionRect rect) {
        return new CollisionRect(x, y, width, kCollisionRange).collidesWith(new CollisionRect(rect.x, rect.y + rect.height - kCollisionRange, rect.width, kCollisionRange));
    }

    public boolean collidesWithRightOf(CollisionRect rect) {
        return new CollisionRect(x, y, kCollisionRange, height).collidesWith(new CollisionRect(rect.x + rect.width - kCollisionRange, rect.y, kCollisionRange, rect.height));
    }

    public boolean collidesWithLeftOf(CollisionRect rect) {
        return new CollisionRect(x + width - kCollisionRange, y, kCollisionRange, height).collidesWith(new CollisionRect(rect.x, rect.y, kCollisionRange, rect.height));
    }

    public boolean isUnder(CollisionRect rect) {
        return x < rect.x + rect.width && x + width > rect.x && y + height < rect.y;
    }

    public void renderCollisionRect(SpriteBatch batch) {
        batch.draw(MainScreen.kPlaceholder, x, y, width, height); //Needs a texture to draw
    }

}