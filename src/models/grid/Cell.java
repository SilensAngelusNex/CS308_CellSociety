package models.grid;

import models.Point;

public class Cell {
	private Point myLocation;
	private CellState myCurrState;
	private CellState myNextState;

	/**
	 * Moves the cell to its next state. Next state must be precalculated (by the grid).
	 */
	public void tick(){
		if (myNextState != null){
			myCurrState = myNextState;
			myNextState = null;
		}
	}
	
	/**
	 * @param nextState the state that the cell will switch to on the next call of tick
	 */
	public void setNextState(CellState nextState){
		myNextState = nextState;
	}
	
	public CellState getState(){
		return myCurrState;
	}
	
	public int getState(String key){
		return myCurrState.getStateAttrib(key);
	}
	public int getStateID(){
		return myCurrState.getStateID();
	}
	
	public void setNextStateAttrib(int val){
		if (myNextState == null)
			myNextState = myCurrState.clone();
		myNextState.setStateID(val);
	}
	public void setNextStateAttrib(int val, String key){
		if (myNextState == null)
			myNextState = myCurrState.clone();
		myNextState.setStateAtttrib(val, key);
	}
	
	public void incrementState(String key){
		if (myNextState == null)
			myNextState = myCurrState.clone();
		myNextState.increment(key);
	}
	
	public Point getLocation(){
		return myLocation;
	}
	
}
	
