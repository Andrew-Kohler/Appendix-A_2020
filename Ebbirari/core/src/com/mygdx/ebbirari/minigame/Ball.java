package com.mygdx.ebbirari.minigame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Ball {
    int x;
    int y;
    int size;
    int xSpeed;
    int ySpeed;

    public Ball(int x, int y, int size, int xSpeed, int ySpeed) {
        this.x = x;
        this.y = y;
        this.size = size;
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
    }

    public void update() {
        x += xSpeed;
        y += ySpeed;
        if (x < 0 || x > Gdx.graphics.getWidth()) {
            xSpeed = -xSpeed;
        }
        if (y < 0 || y > Gdx.graphics.getHeight()) {
            ySpeed = -ySpeed;
        }
    }

    public void draw(ShapeRenderer shape){
        shape.setColor(Color.BLUE);
        shape.circle(x, y, size);
    }

    public void checkCollision(Paddle paddle) {
//        if(collidesWith(paddle)){
//            ySpeed = -ySpeed;
//        }
        if(collidesWithRight(paddle)){
            if(xSpeed < 0){
                xSpeed += 2;
            }
            else if(xSpeed > 0){
                xSpeed += 2;
            }
            ySpeed = -ySpeed;
        }
        else if(collidesWithLeft(paddle)){
            if(xSpeed < 0){
                xSpeed -= 2;
            }
            else if(xSpeed > 0){
                xSpeed -= 2;
            }
            ySpeed = -ySpeed;
        }

    }

    public void checkBotCollision(Block block){
        if(collidesWithBot(block)){
            ySpeed = - ySpeed;
            block.destroyed = true;
        }
    }

    public void checkSideCollision(Block block){
        if(collidesWithSide(block)){
            xSpeed = - xSpeed;
            block.destroyed = true;
        }
    }

    public void checkTopCollision(Block block){
        if(collidesWithTop(block)){
            ySpeed = - ySpeed;
            block.destroyed = true;
        }
    }

//    private boolean collidesWith(Paddle paddle) {
//        if(y - size < paddle.y + paddle.height){
//            if(paddle.x - 50 < x && x < paddle.x + paddle.width + 50){
//                return true;
//            }
//            else{
//                return false;
//            }
//        }
//        else{
//            return false;
//        }
//    }

    private boolean collidesWithLeft(Paddle paddle) {
        if(y - size <= paddle.y + paddle.height){
            if(paddle.x - 50 <= x && x <= paddle.x + paddle.halfWidth){
                return true;
            }
            else{
                return false;
            }
        }
        else{
            return false;
        }
    }

    private boolean collidesWithRight(Paddle paddle) {
        if(y - size <= paddle.y + paddle.height){
            if(paddle.x + paddle.halfWidth < x && x < paddle.x + paddle.width + 50){
                return true;
            }
            else{
                return false;
            }
        }
        else{
            return false;
        }
    }


    private boolean collidesWithBot(Block block) {
        if(y + size >= block.y){
            if(block.x < x && x < block.x + block.width){
                return true;
            }
            else{
                return false;
            }
        }
        else{
            return false;
        }
    }

    private boolean collidesWithSide(Block block) {
        if(y + size > block.y && y - size < block.y + block.height){
            if(block.x == x + size || x - size == block.x + block.width){
                return true;
            }
            else{
                return false;
            }
        }
        else{
            return false;
        }
    }

    private boolean collidesWithTop(Block block) {
        if(y - size >= block.y + block.height && y < block.y + block.height){
            if(block.x < x && x < block.x + block.width){
                return true;
            }
            else{
                return false;
            }
        }
        else{
            return false;
        }
    }






    public int checkY(){
        return y;
    }

}
