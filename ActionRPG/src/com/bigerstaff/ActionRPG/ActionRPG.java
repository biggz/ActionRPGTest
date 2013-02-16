package com.bigerstaff.ActionRPG;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

public class ActionRPG extends Game {
	OrthographicCamera camera;
	Rectangle glViewport;
	//SpriteBatch spriteBatch;
	//ShapeRenderer shapeRenderer;
	//LoadScreen loadScreen;
	//MenuScreen menuScreen;
	//GameScreen gameScreen;
	//Sprite d_f;
	//TextureAtlas atlas;
	//Texture texSquare;
	//Sprite sprSquare;
		
	@Override
	public void create() {
		//Screen instances
		//loadScreen = new LoadScreen(this);
		//menuScreen = new MenuScreen(this);
		//gameScreen = new GameScreen(this);
		
		//Camera set to 1280x720 world units to match resolution
		//camera = new OrthographicCamera();
		//camera.setToOrtho(false, 320, 180); //320,180 (1280,720 to debug)
		
		//spriteBatch = new SpriteBatch();
		//shapeRenderer = new ShapeRenderer();
		//Show loadScreen
		setScreen(new MenuScreen(this));
		
		//LoadAssets();
		//texSquare = new Texture(Gdx.files.internal("data/dungeon_floor.png"));
		//sprSquare = new Sprite(texSquare);
	}
	
	/*@Override
	public void render () {
		Gdx.app.log("ActionRPG", "render()");
	}
	*/

	@Override
	public void dispose() {
	}

	@Override
	public void resize(int width, int height) {
		Gdx.app.log("ActionRPG", "resize()");
		//spriteBatch = new SpriteBatch();
		camera = new OrthographicCamera();
		glViewport = new Rectangle(0, 0, width, height);
		float aspectRatio = (float) width / (float) height;  
		camera.setToOrtho(false, 5f*aspectRatio, 5f);
		camera.position.set(1,1,1);	       	      
	    //this is setting cam's projection matrix for mixing with 3D cam
		//spriteBatch.setProjectionMatrix(camera.combined);	       
	    //this is setting cam's projection matrix the standard way
		//spriteBatch.getProjectionMatrix().setToOrtho2D(0f,0f,10f*aspectRatio,10f);
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}
}