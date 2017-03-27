package com.unitec.java;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.swing.JButton;
import javax.swing.JFrame;

/**
 * @author Kedar, Jagbir, Abhishek
 * Main entry point for the Game
 */
public class GameOfLife extends JFrame{
	
	private static final long serialVersionUID = -7396038433229830205L;
	private static final int WIDTH = 600;
	private static final int HEIGHT = 600; 
	private static final int ROWS = 50;
	private static final int COLS = 50;	
	private static final int STEP_TIME = 500;
	
	public GameOfLife(int rows,int cols){
		setLayout(new FlowLayout(FlowLayout.CENTER,0,0));
		setSize(WIDTH, HEIGHT);
		setTitle("Game of life");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
				
		Board board = new Board(this,rows,cols);
		addButtons(board);		
		setVisible(true);		
	}
		
	
	/**
	 * Add buttons to board
	 * @param board
	 */
	private void addButtons(Board board){
		//Start / Stop
		JButton btnStart = new JButton();
		btnStart.setText("Start");
		btnStart.addActionListener(new ActionListener() {
			StartGame game;
			@Override
			public void actionPerformed(ActionEvent e) {
				if(game == null){
					game =new StartGame(board);
					Thread t = new Thread(game);
					t.start();
					btnStart.setText("Stop");
				}else{
					game.stop();
					game = null;
					btnStart.setText("Start");
				}
				
			}
		});
		add(btnStart);
				
		//Step next generation
		JButton btnNew = new JButton();
		btnNew.setText("Step");
		btnNew.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				board.nextGeneration();					
			}
		});
		add(btnNew);
		
		//Start new game
		JButton btnStep = new JButton();
		btnStep.setText("New");
		btnStep.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				board.resetBoard();
			}
		});
		add(btnStep);
		
		
	}
	
	public class StartGame implements Runnable{
	    private AtomicBoolean gameOn = new AtomicBoolean(false);
		private Board board;
		
		StartGame(Board board){			
			this.board = board;
		}
		
		public void stop() {
			gameOn.set(false);
		}
		
		@Override
		public void run() {
			gameOn.set(true);
			while(gameOn.get()){
				board.nextGeneration();	
				try {
					Thread.sleep(STEP_TIME);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
			}			
		}
	}	
	
	public static void main(String[] args) {
		new GameOfLife(ROWS,COLS);		
	}

}
