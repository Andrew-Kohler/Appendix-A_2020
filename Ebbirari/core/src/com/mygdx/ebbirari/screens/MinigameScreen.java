package com.mygdx.ebbirari.screens;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.GL20;

import com.mygdx.ebbirari.game.Ebbirari;
import com.mygdx.ebbirari.minigame.Ball;
import com.mygdx.ebbirari.minigame.Block;
import com.mygdx.ebbirari.minigame.Paddle;

import java.util.ArrayList;

public class MinigameScreen extends ApplicationAdapter implements Screen {
    ShapeRenderer shape;
    Ball ball;
    Paddle paddle;
    Ebbirari game;
    ArrayList<Block> blocks;

    public MinigameScreen(Ebbirari game){
        this.game = game;
        blocks = new ArrayList<>();
        shape = new ShapeRenderer();
        ball = new Ball(150, 200, 20, 7, 5);
        paddle = new Paddle(70, 12, 5);

        int blockWidth = 100;
        int blockHeight = 45;
        for (int y = Gdx.graphics.getHeight() / 2; y < Gdx.graphics.getHeight(); y += blockHeight + 10) {
            for (int x = 0; x < Gdx.graphics.getWidth(); x += blockWidth + 10) {
                blocks.add(new Block(x, y, blockWidth, blockHeight));
            }
        }

    }

    @Override
    public void create() {



    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        ball.update();
        paddle.update();

        shape.begin(ShapeRenderer.ShapeType.Filled);
        ball.draw(shape);
        paddle.draw(shape);

        ball.checkCollision(paddle);

        for (Block b : blocks) {
            b.draw(shape);
            ball.checkBotCollision(b);
            ball.checkSideCollision(b);
            ball.checkTopCollision(b);
        }

        for (int i = 0; i < blocks.size(); i++) {
            Block b = blocks.get(i);
            if (b.destroyed) {
                blocks.remove(b);
                // we need to decrement i when a ball gets removed, otherwise we skip a ball!
                i--;
            }
        }

        //Add a straight win condition
        if(blocks.size() == 0){
            this.dispose();
            game.camera.update();
            game.setScreen(game.mainScreen);
            game.mainScreen.pong = false;
            if(game.mainScreen.versionB){
                game.mainScreen.currency += 15;
                game.mainScreen.saveFile.putInteger("Energy", game.mainScreen.energy);
            }
            else {
                game.mainScreen.currency += 20;
            }
        }

        //Game is lost
        if(ball.checkY() <= 0){
            if(game.mainScreen.versionB){
                if(blocks.size() < 70){
                    game.mainScreen.currency += 5;
                }
                else if(blocks.size() < 46){
                    game.mainScreen.currency += 10;
                }
            }
            else{
                if(blocks.size() < 70){
                    game.mainScreen.currency += 10;
                }
                else if(blocks.size() < 46){
                    game.mainScreen.currency += 15;
                }
            }

            this.dispose();
            game.camera.update();
            game.setScreen(game.mainScreen);
            game.mainScreen.pong = false;
            game.mainScreen.saveFile.putInteger("Energy", game.mainScreen.energy);
        }

        shape.end();

    }

    @Override
    public void hide() {

    }
}
