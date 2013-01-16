package com.bigerstaff.ActionRPG;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.bigerstaff.ActionRPG.Actors.PlayButton;


public class MenuScreen implements Screen {
		ActionRPG game;
		Stage stage;
		TextureRegion playButtonTextureRegion;
		Texture playButtonTexture;
		PlayButton playButton;

		// constructor to keep a reference to the main Game class
        public MenuScreen(ActionRPG game){
        	this.game = game;
			stage = new Stage();
			playButtonTexture = new Texture(Gdx.files.internal("data/play.png"));
			playButtonTextureRegion = new TextureRegion(playButtonTexture);
			playButton = new PlayButton(playButtonTextureRegion, Gdx.graphics.getWidth()/2 - 600/2, Gdx.graphics.getHeight()/2 - 200/2, 600, 200, this.game);
			stage.addActor(playButton);
	        Gdx.input.setInputProcessor(stage);
        }       
        
        @Override
        public void render(float delta) {
            Gdx.gl.glClearColor(0.294f, 0.294f, 0.294f, 1f);
            Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
            game.camera.update();
            
			game.spriteBatch.setProjectionMatrix(game.camera.combined);
			game.spriteBatch.begin();
			//game.spriteBatch.draw(playButton, play.x, play.y);
		    stage.act(Gdx.graphics.getDeltaTime());
		    stage.draw();
			game.spriteBatch.end();
        }
 

       @Override
        public void resize(int width, int height) {
    	   stage.setViewport(width, height, true);
        }
 

       @Override
        public void show() {
    	   // called when this screen is set as the screen with game.setScreen();
        }
 

       @Override
        public void hide() {
             // called when current screen changes from this to a different screen
        }
 

       @Override
        public void pause() {
        }
 

       @Override
        public void resume() {
        }
 

       @Override
        public void dispose() {
                // never called automatically
        }
 }