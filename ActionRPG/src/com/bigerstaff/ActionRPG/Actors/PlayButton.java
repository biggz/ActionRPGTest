package com.bigerstaff.ActionRPG.Actors;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.bigerstaff.ActionRPG.ActionRPG;

public class PlayButton extends Actor {
	TextureRegion texture;
	ActionRPG game;
	public boolean isTouched;
		
	public PlayButton(TextureRegion tempTexture, float x, float y, float width, float height, ActionRPG game){
		this.game = game;
		this.setX(x);
		this.setY(y);
	    this.setWidth(width);
	    this.setHeight(height);
	    this.setScaleX(1);
	    this.setScaleY(1);
	    this.texture = tempTexture;
	    
	    this.addListener(new InputListener() {
	       public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
	            System.out.println("Play Pressed");
	            isTouched = true;
	            return false;
	        }
	    });
	    
	    this.addListener(new InputListener() {
		       public void touchUp(InputEvent event, float x, float y, int pointer, int button){
		            System.out.println("Play unpressed");
		            isTouched = false;
		            return;
		        }
	    });
		
	}
	
	
    public void draw(SpriteBatch batch, float parentAlpha){
    	batch.draw(texture, getX(), getY(), getOriginX(), getOriginY(), getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
    }
}

