package com.bigerstaff.ActionRPG;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;

public class Player extends Rectangle {

	private static final long serialVersionUID = 1L;
	private int movementSpeed;
	private String playerState, previousState;
	private float stateTime;
	
	TextureAtlas atlas;
	Animation playerAnimationWalkUp, playerAnimationWalkDown, playerAnimationWalkRight, playerAnimationWalkLeft;
	Animation playerAnimationSwordUp, playerAnimationSwordDown, playerAnimationSwordRight, playerAnimationSwordLeft;
	Array<Sprite> spriteWalkUp, spriteWalkDown, spriteWalkRight, spriteWalkLeft;
	Array<Sprite> spriteSwordUp, spriteSwordDown, spriteSwordRight;
	Sprite idleUp, idleDown, idleRight, idleLeft;
	TextureRegion playerTextureRegion;
	Texture playerTexture;
	
	public Player(float tempWidth, float tempHeight, float startX, float startY){
		this.width = tempWidth;//playerTexture.getWidth();
		this.height = tempHeight;//playerTexture.getHeight();
		this.x = startX / 2 - this.width/2;
		this.y = startY / 2 - this.height/2;
		movementSpeed = 5;
		playerState = "idle";
		previousState = "idle";
		LoadAssets();
	}
	
	public void update(SpriteBatch batch, float delta, float touchX, float touchY){
		stateTime += delta;
		//detect input
		if(Gdx.input.isKeyPressed(Keys.LEFT)){
			x -= movementSpeed * delta;
		}
		if(Gdx.input.isKeyPressed(Keys.RIGHT)){
			x += movementSpeed * delta;
		}
		if(Gdx.input.isKeyPressed(Keys.UP)){
			y += movementSpeed * delta;
		}
		if(Gdx.input.isKeyPressed(Keys.DOWN)) {
			y -= movementSpeed * delta;
		}
		x += touchX * movementSpeed * delta;
		y += touchY * movementSpeed * delta;
		UpdateState(touchX, touchY);
		DetectCollision();		
		DrawPlayer(batch);
	}
	
	private void LoadAssets() {
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

		playerAnimationWalkUp = new Animation(0.1f, spriteWalkUp);
		playerAnimationWalkDown = new Animation(0.1f, spriteWalkDown);
		playerAnimationWalkRight = new Animation(0.1f, spriteWalkRight);
		playerAnimationWalkLeft = new Animation(0.1f, spriteWalkLeft);
		
		//Load player texture
		playerTexture = new Texture(Gdx.files.internal("data/player.png"));
		playerTextureRegion = new TextureRegion(playerTexture);		
	}
	
	// Calculate player state based on input
	private void UpdateState(float touchX, float touchY) {
		//Player is moving right
		if(touchX > 0){
			//Moving more right then up or down
			if (Math.abs(touchX) > Math.abs(touchY)){
				previousState = playerState;
				playerState = "walkRight";
			}		
			else if (touchY > 0){ //Moving up 
				previousState = playerState;
				playerState = "walkUp";
			}
			else if (touchY < 0){ //Moving down
				previousState = playerState;
				playerState = "walkDown";
			}
		}
		else if (touchX < 0){ //Moving left
			if (Math.abs(touchX) > Math.abs(touchY)){ //Moving more left than up or down
				previousState = playerState;
				playerState = "walkLeft";
			}
			else if (touchY > 0){
				previousState = playerState;
				playerState = "walkUp"; //Moving up
			}
			else if (touchY < 0){
				previousState = playerState;
				playerState = "walkDown"; //Moving down
			}			
		}// Key input
		else if(Gdx.input.isKeyPressed(Keys.LEFT)){ 
			previousState = playerState;
			playerState = "walkLeft";
		}
		else if(Gdx.input.isKeyPressed(Keys.RIGHT)) {
			previousState = playerState;
			playerState = "walkRight";
		}
		else if(Gdx.input.isKeyPressed(Keys.UP)) {
			previousState = playerState;
			playerState = "walkUp";
		}
		else if(Gdx.input.isKeyPressed(Keys.DOWN)) {
			previousState = playerState;
			playerState = "walkDown";
		}
		else { //Not moving
			if(previousState == "walkRight"){				
				playerState = "idleRight";
			}
			else if (previousState == "walkLeft"){
				playerState = "idleLeft";
			}
			else if (previousState == "walkUp"){
				playerState = "idleUp";
			}
			else if (previousState == "walkDown"){
				playerState = "idleDown";
			}
			else {//Shouldn't really be called
				playerState = "idle";
			}
		}
		
	}
	
	private void DetectCollision(){
		if (playerState == "walkLeft"){
			
		}
		else if (playerState == "walkRight"){
			
		}
		else if (playerState == "walkUp"){
			
		}
		else if (playerState == "walkDown"){
			
		}		
	}

	private void DrawPlayer(SpriteBatch batch) {
		if (playerState == "walkRight") {
        	batch.draw(playerAnimationWalkRight.getKeyFrame(stateTime, true), x, y, 0.66f, 1f);        	
        }
    	else if (playerState == "walkLeft"){
    		batch.draw(playerAnimationWalkLeft.getKeyFrame(stateTime, true), x, y, 0.66f, 1f); 
    	}
    	else if (playerState == "walkUp"){
    		batch.draw(playerAnimationWalkUp.getKeyFrame(stateTime, true), x, y, 0.66f, 1f);
    	}
    	else if (playerState == "walkDown"){
    		batch.draw(playerAnimationWalkDown.getKeyFrame(stateTime, true), x, y, 0.66f, 1f);
    	}
    	else if (playerState == "idleUp"){
    		batch.draw(idleUp, x, y, 0.66f, 1f);
    	}
    	else if (playerState == "idleDown"){
    		batch.draw(idleDown, x, y, 0.66f, 1f);
    	}
    	else if (playerState == "idleLeft"){
    		batch.draw(idleLeft, x, y, 0.66f, 1f);
    	}
    	else if (playerState == "idleRight"){
    		batch.draw(idleRight, x, y, 0.66f, 1f);
    	}
    	else if (playerState == "idle"){
    		batch.draw(idleDown, x, y, 0.66f, 1f);
    	}  	    
	}
	
	public String getState(){
		return playerState;
	}
} 