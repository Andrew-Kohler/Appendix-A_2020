package com.mygdx.ebbirari.game;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.ebbirari.screens.MainScreen;
import com.mygdx.ebbirari.screens.MinigameScreen;

public class Ebbirari extends Game {

	// Constants
	public static final int kHeight = 960;
	public static final int kWidth = 1600;

	public SpriteBatch batch;

	public ShapeRenderer shapeRenderer;

	public OrthographicCamera camera;

	public Viewport viewport;

	public MainScreen mainScreen;
	public MinigameScreen minigameScreen;

	@Override
	public void create () {

		//Set up the sprite batch
		batch = new SpriteBatch();

		//Set up the shape renderer
		shapeRenderer = new ShapeRenderer();

		//Camera stuff
		this.camera = new OrthographicCamera();
		this.camera.setToOrtho(false, kWidth, kHeight);
		this.camera.update();

		//Viewport
		viewport = new FitViewport(kWidth,kHeight,camera);
		viewport.apply();

		//Create the screen
		mainScreen = new MainScreen(this);
		//Set the screen
		this.setScreen(mainScreen);
	}

	public void resize(int width, int height){
		viewport.update(width,height);
	}
}
