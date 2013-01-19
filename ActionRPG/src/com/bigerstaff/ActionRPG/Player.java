package com.bigerstaff.ActionRPG;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class Player extends Rectangle {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int movementSpeed;
	private String playerState, previousState;
	
	public Player(float tempWidth, float tempHeight, float startX, float startY){
		this.width = tempWidth;//playerTexture.getWidth();
		this.height = tempHeight;//playerTexture.getHeight();
		this.x = startX / 2 - this.width/2;
		this.y = startY / 2 - this.height/2;
		movementSpeed = 130;
		playerState = "idle";
		previousState = "idle";
	}
	
	public void update(float delta, float touchX, float touchY){
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
		updateState(touchX, touchY);
	}
	
	// Calculate player state based on input
	public void updateState(float touchX, float touchY) {
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
	
	public String getState(){
		return playerState;
	}
} 