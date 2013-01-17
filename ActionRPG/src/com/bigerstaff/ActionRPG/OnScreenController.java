package com.bigerstaff.ActionRPG;

public class OnScreenController {
	private float originX;
	private float originY;
	private float knobX;
	private float knobY;
	private float joyStickRadius;
	private float knobRadius;
	
	
	public OnScreenController(float locationX, float locationY, float radius, float knobRadius){ 
		this.originX = locationX;
		this.originY = locationY;
		this.joyStickRadius = radius;
		this.knobX = locationX;
		this.knobY = locationY; 
		this.knobRadius = knobRadius;
	}

	public void updateKnobPosition(float x, float y){
		if ((x - originX) * (x - originX) + (y - originY) * (y - originY) <= joyStickRadius * joyStickRadius) {
			knobX = x;
			knobY = y;
		}
		else {
			resetKnob();
		}
	}
  
	public void resetKnob(){
		knobX = originX;
		knobY = originY;
	}


	private float deltaX(){
		return knobX - originX;
	}
	  
	private float deltaY(){
		return knobY - originY;
	}
	  
	public float x_Normalized(){
		return deltaX() /joyStickRadius;
	}
	  
	public float y_Normalized(){
		return deltaY()/joyStickRadius;
	}
	
	public float angle(){
		return (float) (Math.atan2(deltaY(), deltaX()) * 180 / Math.PI);
	}
	
	 
	public float getDrawOriginX(){
		return originX - joyStickRadius;
	}
	  
	public float getDrawOriginY(){
		return originY - joyStickRadius;
	}

	public float getDrawKnobX(){
		return knobX - knobRadius;
	}
	  
	public float getDrawKnobY(){
		return knobY - knobRadius;
	}
	
}