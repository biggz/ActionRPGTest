package com.bigerstaff.ActionRPG;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class ActionRPG implements ApplicationListener {
	OrthographicCamera camera;
	SpriteBatch spriteBatch;
	
	@Override
	public void create() {
		//Create Camera
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 1280, 720);
		//Create SpriteBatch
		spriteBatch = new SpriteBatch();
	}

	@Override
	public void dispose() {
	}

	@Override
	public void render() {
		//Set Background colour to green, so it's obvious if it shows
		Gdx.gl.glClearColor(0, 1f, 0, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		camera.update();
		
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
