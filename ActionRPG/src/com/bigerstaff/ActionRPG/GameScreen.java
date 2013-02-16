package com.bigerstaff.ActionRPG;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
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
	OrthographicCamera camera;
	SpriteBatch spriteBatch;
	TextureAtlas atlas;
	Animation playerAnimationWalkUp, playerAnimationWalkDown, playerAnimationWalkRight, playerAnimationWalkLeft;
	Animation playerAnimationSwordUp, playerAnimationSwordDown, playerAnimationSwordRight, playerAnimationSwordLeft;
	Array<Sprite> spriteWalkUp, spriteWalkDown, spriteWalkRight, spriteWalkLeft;
	Array<Sprite> spriteSwordUp, spriteSwordDown, spriteSwordRight;
	Sprite idleUp, idleDown, idleRight, idleLeft;
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
	String playerState;
	BSPTree level;
	
   // constructor to keep a reference to the main Game class
    public GameScreen(ActionRPG tmpGame){
		game = tmpGame;
		//Load sprite pack and load in to animations
		spriteBatch = new SpriteBatch();
		atlas = new TextureAtlas(Gdx.files.internal("data/pack.atlas"));
		idleUp = atlas.createSprite("walkup", 4);
		idleDown = atlas.createSprite("walkdown", 4);
		idleRight = atlas.createSprite("walkright", 4);
		idleLeft = atlas.createSprite("walkright", 4);
		idleLeft.flip(true, false);
		spriteWalkUp = atlas.createSprites("walkup");
		spriteWalkDown = atlas.createSprites("walkdown");
		spriteWalkRight = atlas.createSprites("walkright");
		spriteWalkLeft = atlas.createSprites("walkright");
		//flip each frame for playerAnimationWalkLeft
		for (Sprite tempSprite : spriteWalkLeft){
			tempSprite.flip(true, false);
		}
		
		//spriteSwordUp = atlas.createSprites("swordup");
		//spriteSwordDown = atlas.createSprites("sworddown");
		//spriteSwordRight = atlas.createSprites("swordright");
		playerAnimationWalkUp = new Animation(0.1f, spriteWalkUp);
		playerAnimationWalkDown = new Animation(0.1f, spriteWalkDown);
		playerAnimationWalkRight = new Animation(0.1f, spriteWalkRight);
		playerAnimationWalkLeft = new Animation(0.1f, spriteWalkLeft);
		
		//playerAnimationSwordUp = new Animation(0.06f, spriteSwordUp);
		//playerAnimationSwordDown = new Animation(0.06f, spriteSwordDown);
		//playerAnimationSwordRight = new Animation(0.06f, spriteSwordRight);

		//Load player texture
		playerTexture = new Texture(Gdx.files.internal("data/player.png"));
		playerTextureRegion = new TextureRegion(playerTexture);
		player = new Player(idleUp.getWidth(), idleUp.getHeight(), camera.viewportWidth, camera.viewportHeight);
		
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
		
		//Testing Level Generation
		level = new BSPTree(64,64, 4);
		//level.PrintMap();
		
    }
    
    @Override
    public void render(float delta) {
    	stateTime += Gdx.graphics.getDeltaTime();
    	Gdx.gl.glClearColor(0.294f, 0.294f, 0.294f, 1f);
        Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
        camera.update();

        //Update player based on delta and X/Y of touchpad
        //uncomment this ;/ player.update(Gdx.graphics.getDeltaTime(), touchpad.getKnobPercentX(), touchpad.getKnobPercentY());
        playerState = player.getState();
        
        //Draw
        spriteBatch.setProjectionMatrix(camera.combined);       
        spriteBatch.begin();
        
        if (playerState == "walkRight") {
        	spriteBatch.draw(playerAnimationWalkRight.getKeyFrame(stateTime, true), player.x, player.y);        	
        }
    	else if (playerState == "walkLeft"){
    		spriteBatch.draw(playerAnimationWalkLeft.getKeyFrame(stateTime, true), player.x, player.y); 
    	}
    	else if (playerState == "walkUp"){
    		spriteBatch.draw(playerAnimationWalkUp.getKeyFrame(stateTime, true), player.x, player.y);
    	}
    	else if (playerState == "walkDown"){
    		spriteBatch.draw(playerAnimationWalkDown.getKeyFrame(stateTime, true), player.x, player.y);
    	}
    	else if (playerState == "idleUp"){
    		spriteBatch.draw(idleUp, player.x, player.y);
    	}
    	else if (playerState == "idleDown"){
    		spriteBatch.draw(idleDown, player.x, player.y);
    	}
    	else if (playerState == "idleLeft"){
    		spriteBatch.draw(idleLeft, player.x, player.y);
    	}
    	else if (playerState == "idleRight"){
    		spriteBatch.draw(idleRight, player.x, player.y);
    	}
    	else if (playerState == "idle"){
    		spriteBatch.draw(idleDown, player.x, player.y);
    	}  	    
        spriteBatch.end();
	    stage.act(Gdx.graphics.getDeltaTime());	    
	    stage.draw();
    }
 

   @Override
    public void resize(int width, int height) {
	   stage.setViewport(width, height, true);
       float aspectRatio = (float) width / (float) height;
	   camera = new OrthographicCamera();
       camera.setToOrtho(false, 10f*aspectRatio, 10f);
    }
	 

   @Override
    public void show() {
       // called when this screen is set as the screen with game.setScreen();
	   stage = new Stage(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), true, spriteBatch);
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