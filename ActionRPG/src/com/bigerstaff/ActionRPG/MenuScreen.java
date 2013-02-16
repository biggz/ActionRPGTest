package com.bigerstaff.ActionRPG;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.bigerstaff.ActionRPG.Actors.PlayButton;


public class MenuScreen implements Screen {
		ActionRPG game;
		SpriteBatch spriteBatch;
		Stage stage;
		TextureRegion playButtonTextureRegion;
		Texture playButtonTexture;
		PlayButton playButton;

		// constructor to keep a reference to the main Game class
        public MenuScreen(ActionRPG tempGame){
        	System.out.print("lar\r");
        	this.game = tempGame;
     	   	game.camera = new OrthographicCamera();
     	   	spriteBatch = new SpriteBatch();
			playButtonTexture = new Texture(Gdx.files.internal("data/play.png"));
			playButtonTextureRegion = new TextureRegion(playButtonTexture);
			playButton = new PlayButton(playButtonTextureRegion, Gdx.graphics.getWidth()/2 - 600/2, Gdx.graphics.getHeight()/2 - 200/2, 600, 200, this.game);
        }       
        
        //@Override
        public void render(float delta) {
        	
            Gdx.gl.glClearColor(0.294f, 0.294f, 0.294f, 1f);
            Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
            game.camera.update();
			//spriteBatch.setProjectionMatrix(game.camera.combined);
			spriteBatch.begin();
		    stage.act(Gdx.graphics.getDeltaTime());
		    stage.draw();
			spriteBatch.end();
			
			if(playButton.isTouched){			
				//game.setScreen(new GameScreen(game));
				dispose();
				game.setScreen(new BSPScreen(game));			
			}
        }
 

       @Override
        public void resize(int width, int height) {    	   
    	   stage.setViewport(width, height, true);
           float aspectRatio = (float) width / (float) height;
           game.camera.setToOrtho(false, 10f*aspectRatio, 10f);           
        }
 

       @Override
        public void show() {
    	   // called when this screen is set as the screen with game.setScreen();
    	   stage = new Stage();
    	   stage.addActor(playButton);			
    	   Gdx.input.setInputProcessor(stage);
        }
 

       @Override
        public void hide() {
           // called when current screen changes from this to a different screen
    	   //stage.dispose();
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
    	   Gdx.app.log("MenuScreen", "dispose()");    	   
    	   spriteBatch.dispose();
        }
 }