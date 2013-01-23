package com.bigerstaff.ActionRPG;

import java.util.Iterator;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;

public class BSPTree {
	Array<BSPCell> BSPCells, splitBSPCells;
	Iterator<BSPCell> iterSplitBSPCells;
	int width, height, currentLevel, minSplit;
	private Array<BSPCell> drawableBSPCells;
	
	public BSPTree(int width, int height){
		this.width = width;
		this.height = height;
		this.currentLevel = 1;
		minSplit = 3; //Used to determine split point.
		drawableBSPCells = new Array<BSPCell>();
		CreateCells();
	}

	private void CreateCells() {		
		//Store all BSPCells
		BSPCells = new Array<BSPCell>();
		//Stores an array list of BSPCells that we need to split
		splitBSPCells = new Array<BSPCell>();
		//Create a single BSPCell at location 0,0 with width & height as specified in the constuctor. This is the size of the BSP tree
		BSPCells.add(new BSPCell(0, 0, width, height));
		//Iterate over the entire BSPCells array list, and create a splitBSPCells array list, only containing cells we need to split
		for (int i = 0; i <= 3; i+=1){
			Iterator <BSPCell> iterBSPCells;
			iterBSPCells = BSPCells.iterator();
			while (iterBSPCells.hasNext()){
				BSPCell iterBSPCell = iterBSPCells.next();
				if (iterBSPCell.level == currentLevel){
					splitBSPCells.add(iterBSPCell);
				}			
				
			}
			iterSplitBSPCells = splitBSPCells.iterator();
			while (iterSplitBSPCells.hasNext()){
				BSPCell iterSplitBSPCell = iterSplitBSPCells.next();
				//Random to decide if horizontal or vertical split. If even, split horizontal.
				if (MathUtils.random(1,100)%2 == 0){ //Even - Split horizontal
					SplitCell(iterSplitBSPCell, true);	
				}
				else { // Odd - Split vertical
					SplitCell(iterSplitBSPCell, false);	
				}
				iterSplitBSPCells.remove(); //if this it not removed it is still in the ArrayList on the next cycle.
			}			
		}
		PrintCells(BSPCells);
		//PrintCellsAscii(BSPCells);
	}
	
	//Given a BSP Cell, split in to two
	private void SplitCell(BSPCell tempBSPCell, boolean horizontal){
		int splitPoint = 0;
		int horizontalVariance = GetVariance(tempBSPCell.height, 0.1f);
		int verticalVariance = GetVariance(tempBSPCell.width, 0.1f);

		if (horizontal){
			splitPoint = (tempBSPCell.height / 2) + MathUtils.random(0-horizontalVariance,horizontalVariance);
			BSPCells.add(new BSPCell(tempBSPCell.posX, tempBSPCell.posY, tempBSPCell.width, splitPoint, tempBSPCell.level));
			BSPCells.add(new BSPCell(tempBSPCell.posX, tempBSPCell.posY + splitPoint, tempBSPCell.width, tempBSPCell.height - splitPoint, tempBSPCell.level));
		}
		else { //Vertical Split
			splitPoint = (tempBSPCell.width / 2) + MathUtils.random(0-verticalVariance, verticalVariance);			
			BSPCells.add(new BSPCell(tempBSPCell.posX, tempBSPCell.posY, splitPoint, tempBSPCell.height, tempBSPCell.level));
			BSPCells.add(new BSPCell(tempBSPCell.posX + splitPoint, tempBSPCell.posY, tempBSPCell.width - splitPoint, tempBSPCell.height, tempBSPCell.level));			
		}
		//Increment current level, if a new level is created
		if (currentLevel == tempBSPCell.level){
			currentLevel += 1;
		}
		splitPoint = 0;
	}
		
	//
	private int GetVariance(int tempInt, float tempFloat) {
		return MathUtils.round(tempInt * tempFloat);
	}

	private void PrintCells(Array<BSPCell> temp){
		for (BSPCell tempBSPCell : temp){
			System.out.print("Level: " + tempBSPCell.level + " | Parent: " + tempBSPCell.parent + " | PosX: " + tempBSPCell.posX + " | PosY: " + tempBSPCell.posY + " | Width: " + tempBSPCell.width + " | Height: " + tempBSPCell.height + "\r");
		}
	}
	
	public void PrintRoomsAscii(Array<Rectangle> rooms, int dimension) {
		System.out.print("PrintCellsAscii();\r");
		char coords[][] = new char[dimension][dimension];
		//Set all to #
		for (int i = 0; i < coords.length; i+=1){
			for (int j = 0; j < coords[0].length; j+=1){
				coords[i][j] = '#';
			}
		}
		//Carve out rooms with empty space
		for (Rectangle room : rooms) {
			for(int i = (int)room.x; i < room.x + room.width; i+=1){
				for (int j = (int)room.y; j < room.y + room.height; j+=1){
					coords[i][j] = ' ';
				}
			}
		}
		//Display 2D array
		for (int i = 0; i < coords.length; i+=1){
			for (int j = 0; j < coords[0].length; j+=1){
				System.out.print(coords[i][j]);
			}
			System.out.print("\r");
		}
		
	}
	
	public Array<BSPCell> GetDrawableCells(){		
		//Find deepest level
		int deepestLevel = 0;
		for (BSPCell tempCell : BSPCells){
			if (tempCell.level > deepestLevel){
				deepestLevel = tempCell.level;
			}
		}
		//Put all cells with deepestLevel in to new array list
		for (BSPCell tempCell : BSPCells) {
			if (tempCell.level == deepestLevel){
				drawableBSPCells.add(tempCell);
			}
		}		
		return drawableBSPCells;
	}
	
	public Array<Rectangle> GetRooms(Array<BSPCell> bspCells){
		//For each cell, create a random sized rectangle that fits within it's bounds
		Array<Rectangle> rooms = new Array<Rectangle>();
		for ( BSPCell temp : bspCells ){
			rooms.add(CreateRoom(temp));
		}
		return rooms;
	}
	
	private Rectangle CreateRoom(BSPCell tempCell){
		float minRatio = 0.6f;
		float maxRatio = 0.8f;
		int roomBuffer = 1; //size of space between room and cell boundry
		int width = MathUtils.random((int)(tempCell.width*minRatio), (int)(tempCell.width*minRatio)); 
		int height = MathUtils.random((int)(tempCell.height*minRatio), (int)(tempCell.height*maxRatio));
		int x = MathUtils.random(tempCell.posX+roomBuffer, tempCell.posX + (tempCell.width - width)-roomBuffer);
		int y = MathUtils.random(tempCell.posY+roomBuffer, tempCell.posY + (tempCell.height - height)-roomBuffer);
		Rectangle room = new Rectangle(x, y, width, height);
		return room;
	}
}
