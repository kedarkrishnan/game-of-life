package com.unitec.java;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import javax.swing.JLabel;

import org.junit.Before;
import org.junit.Test;

/**
 * @author Kedar
 *
 */
public class GameOfLifeTest {
	
	private static final int ROWS = 30;
	private static final int COLS = 30;	
	Cell[][] universe;
	CellHandler  cellHandler;
	
	@Before
	public void setBoard(){
		universe = new Cell[ROWS][COLS];
		cellHandler = new CellHandler(universe,ROWS,COLS);
		for (int i = 0; i < ROWS; i++) {
			for (int j = 0; j < COLS; j++) {
				JLabel cellLabel = new JLabel();
				Cell cell = new Cell();
				cell.setCellLabel(cellLabel);
				universe[i][j] = cell;
			}
		}
	}
	
	@Test
	public void test_StillLives(){
		//Set still life pattern
		universe[12][12].setAlive(true);
		universe[12][12].setWasAlive(true);
		universe[12][13].setAlive(true);
		universe[12][13].setWasAlive(true);
		universe[13][13].setAlive(true);
		universe[13][13].setWasAlive(true);
		universe[13][12].setAlive(true);
		universe[13][12].setWasAlive(true);
		
		//Assert that the life remains still over generations
		cellHandler.nextGeneration();
		
		assertTrue("Cell remains alive",universe[12][12].isAlive());
		assertTrue("Cell remains alive",universe[12][13].isAlive());
		assertTrue("Cell remains alive",universe[13][13].isAlive());
		assertTrue("Cell remains alive",universe[13][12].isAlive());
		
		cellHandler.nextGeneration();
		
		assertTrue("Cell remains alive",universe[12][12].isAlive());
		assertTrue("Cell remains alive",universe[12][13].isAlive());
		assertTrue("Cell remains alive",universe[13][13].isAlive());
		assertTrue("Cell remains alive",universe[13][12].isAlive());
	}
	
	@Test
	public void test_Oscillators(){
		//Set oscillating pattern
		universe[3][16].setAlive(true);
		universe[3][16].setWasAlive(true);
		universe[4][16].setAlive(true);
		universe[4][16].setWasAlive(true);
		universe[5][16].setAlive(true);	
		universe[5][16].setWasAlive(true);
		
		//Assert that the life oscillates over generations
		cellHandler.nextGeneration();		
		
		assertFalse("Cell should be dead",universe[3][16].isAlive());
		assertTrue("Cell should be still alive",universe[4][16].isAlive());
		assertFalse("Cell should be dead",universe[5][16].isAlive());
		
		assertTrue("Cell should have become alive",universe[4][15].isAlive());
		assertTrue("Cell should have become alive",universe[4][17].isAlive());
		
		cellHandler.nextGeneration();
		
		assertTrue("Cell should become alive",universe[3][16].isAlive());
		assertTrue("Cell should be still alive",universe[4][16].isAlive());
		assertTrue("Cell should become alive",universe[5][16].isAlive());
		
		assertFalse("Cell should be dead",universe[4][15].isAlive());
		assertFalse("Cell should be dead",universe[4][17].isAlive());
	}

}
