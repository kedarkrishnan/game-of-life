package com.unitec.java;

import javax.swing.JLabel;

/**
 * @author Kedar
 * The cell maintaining the state of current and new generation
 * and reference to JLabel for rendering the state on screen 
 */
public class Cell {
	private boolean isAlive = false;
	private boolean wasAlive = false;
	private JLabel cellLabel;
	
	public boolean isAlive() {
		return isAlive;
	}
	public void setAlive(boolean isAlive) {
		this.isAlive = isAlive;
	}
	public boolean isWasAlive() {
		return wasAlive;
	}
	public void setWasAlive(boolean wasAlive) {
		this.wasAlive = wasAlive;
	}
	public JLabel getCellLabel() {
		return cellLabel;
	}
	public void setCellLabel(JLabel cellLabel) {
		this.cellLabel = cellLabel;
	} 
	
}
