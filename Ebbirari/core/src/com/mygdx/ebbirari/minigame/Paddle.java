package com.mygdx.ebbirari.minigame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Paddle {
    int x;
    int y;
    int width;
    int height;
    int xSpeed;

    int halfWidth;

    public Paddle(int width, int height, int xSpeed) {
        this.x = 100;
        this.y = 50;
        this.width = width;
        this.height = height;
        this.xSpeed = xSpeed;
        halfWidth = width/2;
    }

    public void draw(ShapeRenderer shape){
        shape.setColor(Color.WHITE);
        shape.rect(x, y, width, height);
    }

    public void update() {
        x = Gdx.input.getX();
        if (x < 0 || x > Gdx.graphics.getWidth()) {
            xSpeed = -xSpeed;
        }
    }



}
