package com.bigerstaff.ActionRPG;

import com.badlogic.gdx.math.Rectangle;

public class BSPCell extends Rectangle {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int posX, posY, width, height, parent, level;
	
	public BSPCell(int tempPosX, int tempPosY, int tempWidth, int tempHeight){
		createBSP(tempPosX, tempPosY, tempWidth, tempHeight, 0);
	}

	public BSPCell(int tempPosX, int tempPosY, int tempWidth, int tempHeight, int tempParent){
		createBSP(tempPosX, tempPosY, tempWidth, tempHeight, tempParent);
	}
	
	public void createBSP(int tempPosX, int tempPosY, int tempWidth, int tempHeight, int tempParent){
		posX = tempPosX;
		posY = tempPosY;
		width = tempWidth;
		height = tempHeight;
		parent = tempParent;
		level = parent + 1;
	}


}

