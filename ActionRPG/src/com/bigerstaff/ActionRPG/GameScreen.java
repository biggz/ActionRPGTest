package com.bigerstaff.ActionRPG;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad.TouchpadStyle;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;

public class GameScreen implements Screen {
	TextureRegion playerTextureRegion;
	Texture playerTexture;
	ActionRPG game;
	Stage stage;
	Touchpad touchpad;
	TouchpadStyle touchpadStyle;
	Skin touchpadSkin;
	Drawable touchBackground;
	Drawable touchKnob;
	Player player;
	
   // constructor to keep a reference to the main Game class
    public GameScreen(ActionRPG tmpGame){
		game = tmpGame;
		playerTexture = new Texture(Gdx.files.internal("data/player.png"));
		playerTextureRegion = new TextureRegion(playerTexture);
		player = new Player(playerTexture);
		touchpadSkin = new Skin();
		touchpadSkin.add("touchBackground", new Texture("data/touchBackground.png"));
		touchpadSkin.add("touchKnob", new Texture("data/touchKnob.png"));
		touchpadStyle = new TouchpadStyle();
		touchBackground = touchpadSkin.getDrawable("touchBackground");
		touchKnob = touchpadSkin.getDrawable("touchKnob"); 
		touchpadStyle.background = touchBackground;
		touchpadStyle.knob = touchKnob;
		touchpad = new Touchpad(10, touchpadStyle);
		touchpad.setBounds(15, 15, 200, 200);	
    }
    
    @Override
    public void render(float delta) {
    	Gdx.gl.glClearColor(0.294f, 0.294f, 0.294f, 1f);
        Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
        game.camera.update();
        player.update(Gdx.graphics.getDeltaTime(), touchpad.getKnobPercentX(), touchpad.getKnobPercentY());
        
        game.spriteBatch.setProjectionMatrix(game.camera.combined);       
        game.spriteBatch.begin();      
        game.spriteBatch.draw(playerTexture, player.x, player.y);      	    
        game.spriteBatch.end();
	    stage.act(Gdx.graphics.getDeltaTime());	    
	    stage.draw();
    }
 

   @Override
    public void resize(int width, int height) {
	   stage.setViewport(width, height, true);
    }
	 

   @Override
    public void show() {
       // called when this screen is set as the screen with game.setScreen();
	   stage = new Stage(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), true, game.spriteBatch);
	   stage.addActor(touchpad);			
	   Gdx.input.setInputProcessor(stage);
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