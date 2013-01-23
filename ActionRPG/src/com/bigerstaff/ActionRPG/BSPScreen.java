package com.bigerstaff.ActionRPG;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;

public class BSPScreen implements Screen {

	private ActionRPG game;
	private BSPTree level;
	private Array<BSPCell> drawableBSPCells;
	private Array<Rectangle> rooms;
    boolean print = true;
    int BSPSize;

	public BSPScreen(ActionRPG tmpGame){
		game = tmpGame;
		BSPSize = 128;
		level = new BSPTree(BSPSize,BSPSize);
		drawableBSPCells = level.GetDrawableCells();
		rooms = level.GetRooms(drawableBSPCells);
		level.PrintRoomsAscii(rooms, BSPSize);
		game.camera.setToOrtho(false, BSPSize, BSPSize);
	}
	
	@Override
    public void render(float delta) {
    	Gdx.gl.glClearColor(0.294f, 0.294f, 0.294f, 1f);
        Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
        game.camera.update();        
        game.shapeRenderer.setProjectionMatrix(game.camera.combined);                     
        //Draw cell outlines
        game.shapeRenderer.begin(ShapeType.Box);
        game.shapeRenderer.setColor(1, 0, 0, 1);
        for (BSPCell temp : drawableBSPCells){
            game.shapeRenderer.box(temp.posX, temp.posY, 0f, temp.width, temp.height, 0f);            
            if (print){
            	//System.out.print("posX: " + temp.posX + " | posY: " + temp.posY + " | width: " + temp.width + " | height: " + temp.height + "\r");
            }
        }
        game.shapeRenderer.end();
        //Draw rooms
        game.shapeRenderer.begin(ShapeType.FilledRectangle);
        game.shapeRenderer.setColor(0, 0, 1, 1);
        for (Rectangle room : rooms){
        	game.shapeRenderer.filledRect(room.x, room.y, room.width, room.height);
        }
        game.shapeRenderer.end();
        print = false;
        
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
