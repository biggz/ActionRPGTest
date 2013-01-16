package com.bigerstaff.ActionRPG;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class ActionRPG extends Game {
	OrthographicCamera camera;
	SpriteBatch spriteBatch;
	LoadScreen loadScreen;
	MenuScreen menuScreen;
	GameScreen gameScreen;
	
	@Override
	public void create() {
		//Screen instances
		loadScreen = new LoadScreen(this);
		menuScreen = new MenuScreen(this);
		gameScreen = new GameScreen(this);
		
		//Camera set to 1280x720 world units to match resolution
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 1280, 720);
		
		spriteBatch = new SpriteBatch();
		
		//Show loadScreen
		setScreen(menuScreen);
	}

	@Override
	public void dispose() {
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}
}