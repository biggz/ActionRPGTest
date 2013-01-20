package com.bigerstaff.ActionRPG;

import java.util.Iterator;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;

public class BSPTree {
	Array<BSPCell> BSPCells, splitBSPCells;
	Iterator<BSPCell> iterSplitBSPCells;
	int width, height, currentLevel, minSplit;
	
	public BSPTree(int width, int height){
		this.width = width;
		this.height = height;
		this.currentLevel = 1;
		minSplit = 3; //Used to determine split point.
		CreateCells();
	}

	private void CreateCells() {		
		//Store all BSPCells
		BSPCells = new Array<BSPCell>();
		//Stores an array list of BSPCells that we need to split
		splitBSPCells = new Array<BSPCell>();
		//Create a single BSPCell at location 0,0 with width & height 128. This is the size of the BSP tree
		BSPCells.add(new BSPCell(0, 0, width, height));
		//Iterate over the entire BSPCells array list, and create a splitBSPCells array list, only containing cells we need to split
		for (int i = 0; i <= 2; i+=1){
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
		PrintCellsAscii(BSPCells);
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
	
	private void PrintCellsAscii(Array<BSPCell> temp) {
		char coords[][] = new char[temp.first().width][temp.first().height];
		//Find deepest level
		int deepestLevel = 0;
		for (BSPCell tempCell : temp){
			if (tempCell.level > deepestLevel){
				deepestLevel = tempCell.level;
			}
		}
		//Put all cells with deepestLevel in to new array list
		Array<BSPCell> drawBSPCells;
		drawBSPCells = new Array<BSPCell>();
		for (BSPCell tempCell : temp) {
			if (tempCell.level == deepestLevel){
				drawBSPCells.add(tempCell);
			}
			//System.out.print("drawBSPCells: " + drawBSPCells.size + "\r");
		}
		//Iterate over drawBSPCells. Draw on coords[x][y] : # around the edge of the cell and leaving the rest empty.
		Iterator <BSPCell> iter;
		System.out.print("drawBSPCells: " + drawBSPCells.size + "\r");
		iter = drawBSPCells.iterator();		
		while (iter.hasNext()){
			BSPCell iterBSPCell = iter.next();
			for (int row = 0; row < iterBSPCell.width; row += 1){
				for (int col = 0; col < iterBSPCell.height; col += 1){
					if ( iterBSPCell.posX + row == iterBSPCell.posX || iterBSPCell.posX + row == iterBSPCell.posX + iterBSPCell.width || iterBSPCell.posY + col == iterBSPCell.posY  || iterBSPCell.posY + col == iterBSPCell.posY + iterBSPCell.height ) {
						if (iterBSPCell.posY + col == iterBSPCell.posY + iterBSPCell.height){
							System.out.print("iterBSPCell.posY(" + iterBSPCell.posY + ") + col(" + col + ") == iterBSPCell.posY(" + iterBSPCell.posY + ") + iterBSPCell.height(" + iterBSPCell.height + ")\r"); 
						}
						coords[iterBSPCell.posX+row][iterBSPCell.posY+col] = '#';
					}
					else {
						coords[iterBSPCell.posX+row][iterBSPCell.posY+col] = '.';
					}
				}
			}	
		}
		for (int row = 0; row < coords.length; row += 1){
			for (int col = 0; col < coords[0].length; col += 1){
				System.out.print(coords[row][col]);
			}
			System.out.print("\r");
		}
	}
}
