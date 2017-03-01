package com.unitec.java;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;

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
	
	private void createUniverse(){
		universe = new Cell[rows][cols];

		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				universe[i][j] = setCell();
			}
		}
	}
	
	protected void nextGeneration(){
		gameHandler.nextGeneration();
		frame.repaint();
	}
	
	private Cell setCell(){
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
