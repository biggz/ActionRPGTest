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
	
	public Player(Texture playerTexture){
		this.width = playerTexture.getWidth();
		this.height = playerTexture.getHeight();
		this.x = Gdx.graphics.getWidth() / 2 - this.width;
		this.y = Gdx.graphics.getHeight() / 2 - this.height;
		movementSpeed = 200;
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
			
	}
}