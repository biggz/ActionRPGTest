package com.bigerstaff.ActionRPG;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad.TouchpadStyle;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Array;

public class GameScreen implements Screen {
	ActionRPG game;
	TextureAtlas atlas;
	Animation playerAnimationWalkUp, playerAnimationWalkDown, playerAnimationWalkRight;
	Animation playerAnimationSwordUp, playerAnimationSwordDown, playerAnimationSwordRight;
	Array<Sprite> spriteWalkUp, spriteWalkDown, spriteWalkRight;
	Array<Sprite> spriteSwordUp, spriteSwordDown, spriteSwordRight;
	TextureRegion playerTextureRegion;
	Texture playerTexture;
	Stage stage;
	Touchpad touchpad;
	TouchpadStyle touchpadStyle;
	Skin touchpadSkin;
	Drawable touchBackground;
	Drawable touchKnob;
	Player player;
	float stateTime;
	
   // constructor to keep a reference to the main Game class
    public GameScreen(ActionRPG tmpGame){
		game = tmpGame;
		
		//Load sprite pack and load in to animations
		atlas = new TextureAtlas(Gdx.files.internal("data/pack.atlas"));
		spriteWalkUp = atlas.createSprites("walkup");
		spriteWalkDown = atlas.createSprites("walkdown");
		spriteWalkRight = atlas.createSprites("walkright");
		spriteSwordUp = atlas.createSprites("swordup");
		spriteSwordDown = atlas.createSprites("sworddown");
		spriteSwordRight = atlas.createSprites("swordright");
		playerAnimationWalkUp = new Animation(0.1f, spriteWalkUp);
		playerAnimationWalkDown = new Animation(0.1f, spriteWalkDown);
		playerAnimationWalkRight = new Animation(0.1f, spriteWalkRight);
		playerAnimationSwordUp = new Animation(0.04f, spriteSwordUp);
		playerAnimationSwordDown = new Animation(0.04f, spriteSwordDown);
		playerAnimationSwordRight = new Animation(0.04f, spriteSwordRight);
		
		//Load player texture
		playerTexture = new Texture(Gdx.files.internal("data/player.png"));
		playerTextureRegion = new TextureRegion(playerTexture);
		player = new Player(playerTexture);
		//Load on screen touchpad
		
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
    	stateTime += Gdx.graphics.getDeltaTime();
    	Gdx.gl.glClearColor(0.294f, 0.294f, 0.294f, 1f);
        Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
        game.camera.update();

        //Update player based on delta and X/Y of touchpad
        player.update(Gdx.graphics.getDeltaTime(), touchpad.getKnobPercentX(), touchpad.getKnobPercentY());

        //Draw
        game.spriteBatch.setProjectionMatrix(game.camera.combined);       
        game.spriteBatch.begin();
        game.spriteBatch.draw(playerAnimationWalkUp.getKeyFrame(stateTime, true), 500, 100);
        game.spriteBatch.draw(playerAnimationWalkDown.getKeyFrame(stateTime, true), 500, 200);
        game.spriteBatch.draw(playerAnimationWalkRight.getKeyFrame(stateTime, true), 500, 300);
        game.spriteBatch.draw(playerAnimationSwordUp.getKeyFrame(stateTime, true), 600, 100);
        game.spriteBatch.draw(playerAnimationSwordDown.getKeyFrame(stateTime, true), 600, 200);
        game.spriteBatch.draw(playerAnimationSwordRight.getKeyFrame(stateTime, true), 600, 300);
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