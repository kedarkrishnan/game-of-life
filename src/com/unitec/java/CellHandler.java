package com.unitec.java;

public class CellHandler {
	
	private Cell[][] universe;
	private int rows;
	private int cols;
	
	CellHandler(Cell[][] universe,int rows, int cols){
		this.universe = universe;
		this.rows = rows;
		this.cols = cols;
	}
	
	protected void createRandomLife(){
		
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
		
		

		makeCellAlive(universe[3][3],true);
		makeCellAlive(universe[3][4],true);
		makeCellAlive(universe[3][5],true);
		makeCellAlive(universe[2][5],true);
		makeCellAlive(universe[1][4],true);
		
	}
	
	private void makeCellAlive(Cell cell,boolean isNew){
		cell.getCellLabel().setOpaque(true);
		if(isNew){
			cell.setWasAlive(true);
		}else{
			cell.setWasAlive(cell.isAlive());	
		}		
		cell.setAlive(true);			
	}
	
	private void makeCellDie(Cell cell){
		cell.getCellLabel().setOpaque(false);
		cell.setWasAlive(cell.isAlive());
		cell.setAlive(false);		
	}
	
	protected void nextGeneration(){
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				applyRules(universe[i][j],getNeighourLives(i,j));
			}
		}
		render();
	}
	
	private void render(){
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				universe[i][j].setWasAlive(universe[i][j].isAlive());				
			}
		}		
	}
	
	private int getNeighourLives(int row , int col){
		int lifeCount = 0;
		//top cells
		if(row-1 >= 0){			
			lifeCount = col-1 >= 0 ? (universe[row-1][col-1].isWasAlive() ? ++lifeCount : lifeCount) : lifeCount;
			lifeCount = universe[row-1][col].isWasAlive() ? ++lifeCount : lifeCount;
			lifeCount = col+1 < cols ? (universe[row-1][col+1].isWasAlive() ? ++lifeCount : lifeCount):lifeCount;
		}
		//bottom cells
		if(row+1 < rows){
			
			lifeCount = col-1 >= 0 ? (universe[row+1][col-1].isWasAlive() ? ++lifeCount : lifeCount) : lifeCount;
			lifeCount = universe[row+1][col].isWasAlive() ? ++lifeCount : lifeCount;
			lifeCount = col+1 < cols ? (universe[row+1][col+1].isWasAlive() ? ++lifeCount : lifeCount) : lifeCount;
		}
		//middle cells
		lifeCount = col-1 >= 0 ? (universe[row][col-1].isWasAlive() ? ++lifeCount : lifeCount) : lifeCount;
		lifeCount = col+1 < cols ? (universe[row][col+1].isWasAlive() ? ++lifeCount : lifeCount) : lifeCount;
		return lifeCount;
	}
	
	private void applyRules(Cell cell,int lifeCount){
		if(lifeCount<2){
			makeCellDie(cell);
		}
		if(lifeCount>3){
			makeCellDie(cell);
		}
		if(lifeCount==3){
			makeCellAlive(cell,false);
		}
	}
}
