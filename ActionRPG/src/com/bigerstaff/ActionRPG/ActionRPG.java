package com.bigerstaff.ActionRPG;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.OrthographicCamera;

public class ActionRPG extends Game {
	OrthographicCamera camera;
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
		
		//Show loadScreen
		setScreen(loadScreen);
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