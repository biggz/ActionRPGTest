package com.bigerstaff.ActionRPG;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;

public class BSPScreen implements Screen {
	
	ShapeRenderer shapeRenderer;
	Rectangle glViewport;
	SpriteBatch spriteBatch;
	private ActionRPG game;
	private BSPTree level;
	private Array<BSPCell> drawableBSPCells;
	private Array<Rectangle> rooms;
    boolean print = true;
    int BSPSize, BSPBranches,blocksize;
    String[][] tiles;
    Player player;
    
    //Assets
    TextureAtlas atlas;
    Sprite d_w_n, d_w_ne, d_w_e, d_w_se, d_w_s, d_w_sw, d_w_w, d_w_nw, d_w; //Dungeon Wall
    Sprite d_f_n, d_f_ne, d_f_e, d_f_se, d_f_s, d_f_sw, d_f_w, d_f_nw, d_f; //Dungeon Floor

	public BSPScreen(ActionRPG tempGame){
		Gdx.app.log("BSPScreen", "BSPScreen()");
		this.game = tempGame;
		spriteBatch = new SpriteBatch();
		BSPSize = 64;
		BSPBranches=3;
		tiles = new String[BSPSize][BSPSize];
		level = new BSPTree(BSPSize,BSPSize,BSPBranches);
		drawableBSPCells = level.GetDrawableCells();
		rooms = level.GetRooms(drawableBSPCells);
		level.PrintRoomsAscii(rooms, BSPSize);
		CreateTiles();
		LoadAssets();
		player = new Player(0.66f,1f,1f,1f);
	}
	
	@Override
    public void render(float delta) {
		
		Gdx.gl.glClearColor(0.294f, 0.294f, 0.294f, 1f);
        Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		UpdateCamera();
		Gdx.app.log("BSPScreen", "player.state= " + player.getState());
        game.camera.update();
        spriteBatch.setProjectionMatrix(game.camera.combined);
        //game.shapeRenderer.setProjectionMatrix(game.camera.combined);                     
        //drawDebugBSPTree();   
        spriteBatch.begin();
        drawBSPTree(BSPSize);
        player.update(spriteBatch, delta, 0f, 0f);
        spriteBatch.end();
        //spriteBatch.begin();
        //spriteBatch.draw(d_f, 0f,0f, 1f, 1f);
        //spriteBatch.end();                
    }
 

   @Override
    public void resize(int width, int height) {
	   Gdx.app.log("BSPScreen", "resize()");
	   game.camera.position.set(0,0,0);
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
   
   private void drawBSPTree(int size){
	   
		//Display 2D array
		for (int i = 0; i < tiles.length; i+=1){
			for (int j = 0; j < tiles[0].length; j+=1){
				//Draw Wall
				if (tiles[i][j] == "d_w") {
					spriteBatch.draw(d_w, i,j, 1f,1f);
				}
				else if (tiles[i][j] == "d_f") {
					spriteBatch.draw(d_f, i,j, 1f,1f);
				}
				else if (tiles[i][j] == "d_f_n") {
					spriteBatch.draw(d_f_n, i,j, 1f,1f);
				}
				else if (tiles[i][j] == "d_f_ne") {
					spriteBatch.draw(d_f_ne, i,j, 1f,1f);
				}
				else if (tiles[i][j] == "d_f_e") {
					spriteBatch.draw(d_f_e, i,j, 1f,1f);
				}
				else if (tiles[i][j] == "d_f_se") {
					spriteBatch.draw(d_f_se, i,j, 1f,1f);
				}
				else if (tiles[i][j] == "d_f_s") {
					spriteBatch.draw(d_f_s, i,j, 1f,1f);
				}
				else if (tiles[i][j] == "d_f_sw") {
					spriteBatch.draw(d_f_sw, i,j, 1f,1f);
				}
				else if (tiles[i][j] == "d_f_w") {
					spriteBatch.draw(d_f_w, i,j, 1f,1f);
				}
				else if (tiles[i][j] == "d_f_nw") {
					spriteBatch.draw(d_f_nw, i,j, 1f,1f);
				}
				else if (tiles[i][j] == "d_w_n") {
					spriteBatch.draw(d_w_n, i,j, 1f,1f);
				}
				else if (tiles[i][j] == "d_w_ne") {
					spriteBatch.draw(d_w_ne, i,j, 1f,1f);
				}
				else if (tiles[i][j] == "d_w_e") {
					spriteBatch.draw(d_w_e, i,j, 1f,1f);
				}
				else if (tiles[i][j] == "d_w_se") {
					spriteBatch.draw(d_w_se, i,j, 1f,1f);
				}
				else if (tiles[i][j] == "d_w_s") {
					spriteBatch.draw(d_w_s, i,j, 1f,1f);
				}
				else if (tiles[i][j] == "d_w_sw") {
					spriteBatch.draw(d_w_sw, i,j, 1f,1f);
				}
				else if (tiles[i][j] == "d_w_w") {
					spriteBatch.draw(d_w_w, i,j, 1f,1f);
				}
				else if (tiles[i][j] == "d_w_nw") {
					spriteBatch.draw(d_w_nw, i,j, 1f,1f);
				}
			}
		}
   }
   
   private void UpdateCamera(){
	   game.camera.position.set(player.x, player.y, 0f);
	   /*
	   if(Gdx.input.isKeyPressed(Keys.LEFT)) {
		   if (game.camera.position.x > 0){
			   game.camera.translate(-1, 0, 0);
		   }
		   else {
			   game.camera.position.x = 0;
		   }
	   }
	   if(Gdx.input.isKeyPressed(Keys.RIGHT)) {
		   if (game.camera.position.x < BSPSize * blocksize) {
			   game.camera.translate(1, 0, 0);
		   }
		   else {
			   game.camera.position.x = BSPSize * blocksize;
		   }
	   }
	   if(Gdx.input.isKeyPressed(Keys.UP)) {
		   if (game.camera.position.y < BSPSize * blocksize) {
			   game.camera.translate(0, 1, 0);
		   }
		   else {
			   game.camera.position.y = BSPSize * blocksize;
		   }
	   }
	   if(Gdx.input.isKeyPressed(Keys.DOWN)) {
		   if (game.camera.position.y > 0) {
			   game.camera.translate(0, -1, 0);
		   }
		   else {
			   game.camera.position.y = 0;
		   }
	   }
	*/
   }
   
   private void CreateTiles(){
	   	System.out.print("tiles.length: " + tiles.length);
	   	//Set all tiles to dungeon wall
		for (int i = 0; i < tiles.length; i+=1){
			for (int j = 0; j < tiles[0].length; j+=1){
				tiles[i][j] = "d_w";
			}
		}
		//Carve out rooms with dungeon floor
		for (Rectangle room : rooms) {
			for(int x = (int)room.x; x < room.x + room.width; x+=1){
				for (int y = (int)room.y; y < room.y + room.height; y+=1){
					if( x == (int)room.x && y == (int)room.y){ //Bottom left tile of room
						tiles[x][y] = "d_f_sw"; 
						//Set Dungeon walls around tile
						tiles[x-1][y] = "d_w_w";
						tiles[x-1][y-1] = "d_w_sw";
						tiles[x][y-1] = "d_w_s";
					}
					else if( x == (int)room.x + room.width-1 && y == (int)room.y){ //Bottom right tile of room
						tiles[x][y] = "d_f_se"; 
						//Set Dungeon walls around tile
						tiles[x+1][y] = "d_w_e";
						tiles[x+1][y-1] = "d_w_se";
						tiles[x][y-1] = "d_w_s";						
					}
					else if(y==(int)room.y){ //Bottom Edge of room
						tiles[x][y] = "d_f_s";
						//Set Dungeon Wall south
						tiles[x][y-1] = "d_w_s";
					}
					else if (x == (int)room.x && y == (int)room.y + room.height-1){ //Top left tile of room
						tiles[x][y] = "d_f_nw"; 
						//Set Dungeon walls around tile
						tiles[x-1][y] = "d_w_w";
						tiles[x-1][y+1] = "d_w_nw";
						tiles[x][y+1] = "d_w_n";
					}
					else if (x == (int)room.x + room.width-1 && y == (int)room.y + room.height-1){//Top right tile of room
						tiles[x][y] = "d_f_ne"; 
						//Set Dungeon walls around tile
						tiles[x+1][y] = "d_w_e";
						tiles[x+1][y+1] = "d_w_ne";
						tiles[x][y+1] = "d_w_n";
					}
					else if (y==(int)room.y+room.height-1){ //Top edge of room
						tiles[x][y] = "d_f_n";
						//Set Dungeon wall around tile
						tiles[x][y+1] = "d_w_n"; 
					}
					else if (x == room.x) { //Left edge of room
						tiles[x][y] = "d_f_w";
						//Set Dungeon wall around tile
						tiles[x-1][y] = "d_w_w";
					}
					else if (x== room.x + room.width -1) { //Right edge of room
						tiles[x][y] = "d_f_e";
						//Set Dungeon wall around tile
						tiles[x+1][y] = "d_w_e";
					}
					else {//Normal floor tile
						tiles[x][y] = "d_f";
					}
						
				}
			}
		}
		//Carve out dungeon walls on floor border
		//	    NW	if (SE) = floor
		//outerLoop:
		//for (int x = 0; x<tiles.length; x+=1){
		//	innerLoop:
		//	for (int y = 0; y < tiles[0].length; y+=1){
		//		if (x == tiles[0].length-1){ break outerLoop;}
		//		if ( y == 0) { break innerLoop;}
		//		if (tiles[x+1][y-1] == "d_f"){
		//			tiles[x][y] = "d_w_nw";
		//			System.out.print("HELLO!!");
		//		}		
		//	}
		//}
		//		N 	if (S) = floor
		//		NE 	if (SW) = floor
		//		E 	if (W) = floor
		//		SE 	if (NW) = floor
		//		S 	if (N) = floor
		//		SW 	if (NE) = floor
		//		W 	if (E) = floor
   }
   
   private void drawDebugBSPTree(){
       //Draw cell outlines
       shapeRenderer.begin(ShapeType.Box);
       shapeRenderer.setColor(1, 0, 0, 1);
       for (BSPCell temp : drawableBSPCells){
           shapeRenderer.box(temp.posX, temp.posY, 0f, temp.width, temp.height, 0f);            
           if (print){
           	//System.out.print("posX: " + temp.posX + " | posY: " + temp.posY + " | width: " + temp.width + " | height: " + temp.height + "\r");
           }
       }
       shapeRenderer.end();
       //Draw rooms
       shapeRenderer.begin(ShapeType.FilledRectangle);
       shapeRenderer.setColor(0, 0, 1, 1);
       for (Rectangle room : rooms){
    	   shapeRenderer.filledRect(room.x, room.y, room.width, room.height);
       }
       shapeRenderer.end();
       print = false;
	   
   }
   
   private void LoadAssets(){
		atlas = new TextureAtlas(Gdx.files.internal("data/pack.atlas"));
		//Wall Tiles
		d_w_n = atlas.createSprite("dungeon_wall_n");
		d_w_ne = atlas.createSprite("dungeon_wall_ne");
		d_w_e = atlas.createSprite("dungeon_wall_e");
		d_w_se = atlas.createSprite("dungeon_wall_se");
		d_w_s = atlas.createSprite("dungeon_wall_s");
		d_w_sw = atlas.createSprite("dungeon_wall_sw");
		d_w_w = atlas.createSprite("dungeon_wall_w");
		d_w_nw = atlas.createSprite("dungeon_wall_nw");
		d_w = atlas.createSprite("dungeon_wall");
		//Floor Tiles
		d_f_n = atlas.createSprite("dungeon_floor_n");
		d_f_ne = atlas.createSprite("dungeon_floor_ne");
		d_f_e = atlas.createSprite("dungeon_floor_e");
		d_f_se = atlas.createSprite("dungeon_floor_se");
		d_f_s = atlas.createSprite("dungeon_floor_s");
		d_f_sw = atlas.createSprite("dungeon_floor_sw");
		d_f_w = atlas.createSprite("dungeon_floor_w");
		d_f_nw = atlas.createSprite("dungeon_floor_nw");
		d_f = atlas.createSprite("dungeon_floor");
		blocksize = (int) d_f.getWidth();
   }
}
