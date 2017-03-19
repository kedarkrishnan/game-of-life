package com.unitec.java;

import java.awt.Color;
import java.util.Random;

/**
 * @author Kedar
 * Handle the state of the cells in the universe as per the game rules
 */

public class CellHandler {
	
	private Cell[][] universe;
	private int rows;
	private int cols;
	private int initalLives;
	
	public CellHandler(Cell[][] universe,int rows, int cols){
		this.universe = universe;
		this.rows = rows;
		this.cols = cols;
		this.initalLives = (int) (rows * cols * 0.10);
	}
	
	
	/**
	 * Mark all cells dead
	 */
	protected void resetCells(){
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				Cell cell = universe[i][j];
				makeCellDie(cell);
				cell.getCellLabel().setOpaque(false);
			}
		}
	}
	
	/**
	 * Generate random live cells
	 */
	protected void createRandomLife(){
		
//		makeCellAlive(universe[0][0],true);
//		makeCellAlive(universe[rows-1][0],true);
//		makeCellAlive(universe[rows-2][0],true);
//		makeCellAlive(universe[1][0],true);
//		makeCellAlive(universe[2][0],true);
//		
//		makeCellAlive(universe[0][cols-1],true);
//		makeCellAlive(universe[1][cols-1],true);
//		makeCellAlive(universe[2][cols-1],true);
		
//		makeCellAlive(universe[3][16],true);
//		makeCellAlive(universe[4][16],true);
//		makeCellAlive(universe[5][16],true);	
//		
//		makeCellAlive(universe[10][10],true);
//		makeCellAlive(universe[10][11],true);
//		makeCellAlive(universe[11][10],true);
//		
//		makeCellAlive(universe[12][13],true);
//		makeCellAlive(universe[13][13],true);
//		makeCellAlive(universe[13][12],true);
		
		Random random = new Random();
		int count = 0;
		while (count<initalLives) {
			makeCellAlive(universe[random.nextInt(rows)][random.nextInt(cols)],true);
			count++;
		}
//
//		makeCellAlive(universe[3][3],true);
//		makeCellAlive(universe[3][4],true);
//		makeCellAlive(universe[3][5],true);
//		makeCellAlive(universe[2][5],true);
//		makeCellAlive(universe[1][4],true);
		
	}
	
	/**
	 * Make the cell alive
	 * @param cell
	 * @param isNew
	 */
	private void makeCellAlive(Cell cell,boolean isNew){
		cell.getCellLabel().setOpaque(true);
		if(isNew){			
			// As the cell is new wasAlive and isAlive state is same, 
			//used when the board is rendered for first time
			cell.setWasAlive(true);
		}else{
			cell.setWasAlive(cell.isAlive());	
		}		
		cell.setAlive(true);
		cell.getCellLabel().setBackground(Color.BLUE);
	}
	
	/**
	 * Make the cell dead
	 * @param cell
	 */
	private void makeCellDie(Cell cell){
		//cell.getCellLabel().setOpaque(false);
		cell.setWasAlive(cell.isAlive());
		cell.setAlive(false);
		cell.getCellLabel().setBackground(Color.GRAY);
	}
	
	/**
	 * Process the whole universe and change the cell current state based on game rules
	 */
	protected void nextGeneration(){
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				applyRules(universe[i][j],getNeighbourLives(i,j));
			}
		}
		startNextGeneration();
	}
	
	/**
	 * Set wasAlive state same as isAlive to begin the next generation
	 */
	private void startNextGeneration(){
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				universe[i][j].setWasAlive(universe[i][j].isAlive());				
			}
		}		
	}
	
	/**
	 * @param row
	 * @param col
	 * @return Number of live cells in the neighbour
	 */
	private int getNeighbourLives(int row , int col){
		int lifeCount = 0;
		int topRow = 0;
		int bottomRow = 0;
		int leftCol = 0;
		int rightCol = 0;
		
		//Get Neighbour cells row / cols with wrap around
		topRow = (row-1 >= 0)? row-1 : rows-1;
		bottomRow = (row+1 < rows)? row+1 : 0;
		leftCol = (col-1 >= 0)? col-1 : cols-1;
		rightCol = (col+1 < cols)? col+1 : 0;
		
		//top cells
		lifeCount = universe[topRow][leftCol].isWasAlive() ? ++lifeCount : lifeCount;
		lifeCount = universe[topRow][col].isWasAlive() ? ++lifeCount : lifeCount;
		lifeCount = universe[topRow][rightCol].isWasAlive() ? ++lifeCount : lifeCount;
		
		//bottom cells
		lifeCount = universe[bottomRow][leftCol].isWasAlive() ? ++lifeCount : lifeCount;
		lifeCount = universe[bottomRow][col].isWasAlive() ? ++lifeCount : lifeCount;
		lifeCount = universe[bottomRow][rightCol].isWasAlive() ? ++lifeCount : lifeCount;
		
		//middle cells
		lifeCount = universe[row][leftCol].isWasAlive() ? ++lifeCount : lifeCount;
		lifeCount = universe[row][rightCol].isWasAlive() ? ++lifeCount : lifeCount;
		
//		//top cells
//		if(row-1 >= 0){			
//			lifeCount = col-1 >= 0 ? (universe[row-1][col-1].isWasAlive() ? ++lifeCount : getWrapColLives(row,cols-1,lifeCount)) : lifeCount;
//			lifeCount = universe[row-1][col].isWasAlive() ? ++lifeCount : lifeCount;
//			lifeCount = col+1 < cols ? (universe[row-1][col+1].isWasAlive() ? ++lifeCount : getWrapColLives(row,0,lifeCount)):lifeCount;
//		}else{
//			//lifeCount = col-1 >= 0 ? (universe[row-1][col-1].isWasAlive() ? ++lifeCount : lifeCount) : lifeCount;
//			lifeCount = universe[rows-1][col].isWasAlive() ? ++lifeCount : lifeCount;
//			//lifeCount = col+1 < cols ? (universe[row-1][col+1].isWasAlive() ? ++lifeCount : lifeCount):lifeCount;
//		}
//		//bottom cells
//		if(row+1 < rows){
//			
//			lifeCount = col-1 >= 0 ? (universe[row+1][col-1].isWasAlive() ? ++lifeCount : lifeCount) : lifeCount;
//			lifeCount = universe[row+1][col].isWasAlive() ? ++lifeCount : lifeCount;
//			lifeCount = col+1 < cols ? (universe[row+1][col+1].isWasAlive() ? ++lifeCount : lifeCount) : lifeCount;
//		}else{
//			lifeCount = universe[0][col].isWasAlive() ? ++lifeCount : lifeCount;
//		}
//		//middle cells
//		lifeCount = col-1 >= 0 ? (universe[row][col-1].isWasAlive() ? ++lifeCount : lifeCount) : lifeCount;
//		lifeCount = col+1 < cols ? (universe[row][col+1].isWasAlive() ? ++lifeCount : lifeCount) : lifeCount;
		return lifeCount;
	}
	
	
	/**
	 * Apply the game rules and make the cell live or dead
	 * @param cell
	 * @param lifeCount
	 */
	private void applyRules(Cell cell,int lifeCount){
		//Any live cell with fewer than two live neighbours dies.
		if(lifeCount<2){
			makeCellDie(cell);
		}
		//Any live cell with more than three live neighbours dies.
		if(lifeCount>3){
			makeCellDie(cell);
		}
		//Any dead cell with exactly three live neighbours becomes a live cell
		if(lifeCount==3){
			makeCellAlive(cell,false);
		}
		
		//Any live cell with two or three live neighbours lives on to the next generation
		//Handles automatically due to top rules
	}
}
