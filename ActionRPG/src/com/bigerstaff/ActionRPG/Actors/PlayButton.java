package com.bigerstaff.ActionRPG.Actors;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.bigerstaff.ActionRPG.ActionRPG;
import com.bigerstaff.ActionRPG.LoadScreen;

public class PlayButton extends Actor {
	TextureRegion texture;
	ActionRPG game2;
	
	public PlayButton(TextureRegion tempTexture, float x, float y, float width, float height, ActionRPG game){		
		this.game2 = game;
		this.setX(x);
		this.setY(y);
	    this.setWidth(width);
	    this.setHeight(height);
	    this.setScaleX(1);
	    this.setScaleY(1);
	    texture = tempTexture;
	    
	    this.addListener(new InputListener() {
	        public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
	            System.out.println("Play Pressed");
	            game2.setScreen(new LoadScreen(game2));
	            return false;
	        }
	    });
		
	}
	
    public void draw(SpriteBatch batch, float parentAlpha){
    	batch.draw(texture, getX(), getY(), getOriginX(), getOriginY(), getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
    }
}

