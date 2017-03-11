package com.unitec.java;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;


/**
 * @author Kedar
 * The universe on which the cells are placed which go live or dead as per the game rules
 */
public class Board {

	private Cell[][] universe;
	
	private int rows;
	private int cols;
	private int cellWith;
	private int cellHeight;
	private JFrame frame;
	private CellHandler gameHandler;
	
	Board(JFrame frame,int rows, int cols){
				
		this.frame = frame;
		this.rows = rows;
		this.cols = cols;
		this.cellWith = frame.getWidth()/rows;
		this.cellHeight = frame.getHeight()/cols -1;
		
		createUniverse();
		gameHandler = new CellHandler(universe,rows,cols);
		gameHandler.createRandomLife();
	}
	
	
	/**
	 * Paint a new board
	 */
	public void resetBoard(){
		gameHandler.resetCells();
		gameHandler.createRandomLife();
		frame.repaint();
	}
	
	/**
	 * Place the cells as per the rows and cols on the board
	 */
	private void createUniverse(){
		universe = new Cell[rows][cols];

		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				universe[i][j] = generateCell();
			}
		}
	}
	
	/**
	 * Start next generation
	 */
	protected void nextGeneration(){
		gameHandler.nextGeneration();
		frame.repaint();
	}
	
	
	/**
	 * Generate the cell 
	 * @return @Cell to be placed on the board
	 */
	private Cell generateCell(){
		Cell cell = new Cell();
		JLabel cellLabel = new JLabel();  
		cellLabel.setBackground(Color.BLUE);
		cellLabel.setOpaque(false);
		cellLabel.setPreferredSize(new Dimension(cellWith, cellHeight));
		cellLabel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
		cell.setCellLabel(cellLabel);	
		frame.add(cellLabel);
		return cell;
	}
	
	
}
