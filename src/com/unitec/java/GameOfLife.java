package com.unitec.java;

import java.awt.FlowLayout;

import javax.swing.JFrame;

public class GameOfLife extends JFrame{
	
	private static final long serialVersionUID = -7396038433229830205L;
	private static final int WIDTH = 600;
	private static final int HEIGHT = 600; 
	private static final int ROWS = 20;
	private static final int COLS = 20;
	
	GameOfLife(int rows, int cols) throws InterruptedException{
		setLayout(new FlowLayout(FlowLayout.CENTER,0,0));
		setSize(WIDTH, HEIGHT);
		setTitle("Game of life");
		setDefaultCloseOperation(EXIT_ON_CLOSE);			
		Board board = new Board(this,rows,cols);
		setVisible(true);	
		
		System.out.println("Game of life");
		Thread.sleep(5000);		
		while(true){
			board.nextGeneration();	
			Thread.sleep(1000);
		}
	}
		

	public static void main(String[] args) throws InterruptedException {
		new GameOfLife(ROWS,COLS);		
	}

}
