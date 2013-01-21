package com.bigerstaff.ActionRPG;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.utils.Array;

public class BSPScreen implements Screen {

	private ActionRPG game;
	private BSPTree level;
	private Array<BSPCell> drawableBSPCells;

	public BSPScreen(ActionRPG tmpGame){
		game = tmpGame;
		level = new BSPTree(16,16);
		drawableBSPCells = level.GetDrawableCells();
		game.camera.setToOrtho(false, 16, 16);
	}
	
	@Override
    public void render(float delta) {
    	Gdx.gl.glClearColor(0.294f, 0.294f, 0.294f, 1f);
        Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
        game.camera.update();
        
        game.shapeRenderer.setProjectionMatrix(game.camera.combined); 
        game.shapeRenderer.begin(ShapeType.Box);      
        System.out.print("Start Render\r");
        for (BSPCell temp : drawableBSPCells){
        	game.shapeRenderer.setColor(0, 0, 1, 1);
            game.shapeRenderer.box(temp.posX, temp.posY, 0f, temp.width, temp.height, 0f);
            System.out.print("posX: " + temp.posX + " | posY: " + temp.posY + " | width: " + temp.width + " | height: " + temp.height + "\r");
        }
        System.out.print("End Render\r");
        game.shapeRenderer.end();
    }
 

   @Override
    public void resize(int width, int height) {	   
    }
	 

   @Override
    public void show() {
       // called when this screen is set as the screen with game.setScreen();
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
