package views.grid;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import models.grid.Cell;
import resources.AppResources;
import views.styles.CellStyleGuide;

/**
 * A super class used to build the Grid View
 * @author Guhan Muruganandam
 *
 */
public abstract class GridViewUpdate {
	
	double myWidth;
	double myHeight;
	double myCellWidth;
	double myCellHeight;
	double xOffset;
	double yOffset;
	int myCellx;
	int myCelly;
	Dimension myDimensions;
	Group myRoot;
	CellStyleGuide myGuide;
	Collection<Cell> myCells;
	ArrayList<Shape> myShapeCollection=new ArrayList<Shape>();
	Map<Cell,Shape> cellMap=new HashMap<Cell,Shape>();
	
	public GridViewUpdate(int width,int height,Dimension dimensions,Group root,CellStyleGuide csg,Collection<Cell> cells){
		myWidth=width;
		myHeight=height;
		myDimensions=dimensions;
		myRoot=root;
		myGuide=csg;
		myCells=cells;
		myCellWidth=(myWidth*AppResources.HALF)/(myDimensions.getWidth());
		myCellHeight=(myHeight*AppResources.HALF)/(myDimensions.getHeight());
		xOffset=myWidth*(AppResources.OFFSET);
		yOffset=myHeight*(AppResources.OFFSET);
	}

	public void makeGrid(){
		for(Cell c: myCells){
			addCell(c);
		}
	}
	
	public void stepGrid(Collection<Cell> cells){
		Iterator<Shape> shapeIterator= myShapeCollection.iterator();
		for(Cell c: cells){
			Shape myShape=shapeIterator.next();
			myRoot.getChildren().remove(myShape);
			colorCell(c);
			myRoot.getChildren().add(myShape);
		}
			
	}
	public void cellSetup(Cell currcell,Shape shape){
		cellMap.put(currcell, shape);
		colorCell(currcell);
		myRoot.getChildren().add(shape);
		myShapeCollection.add(shape); 
	}
	
	public void colorCell(Cell c) {
		Shape s= getShape(c);
		int currcellstate =c.getStateID();
		s.setFill(Color.web(myGuide.getColor(currcellstate)));
		s.setStroke(Color.BLACK); ////FIX THIS USE RESOURCE FILE
	}
	
	public Iterator<Shape> getShapeIterator(){
		Iterator<Shape> shapeIterator= myShapeCollection.iterator();
		return shapeIterator;
	}
	
	public void getCellLocation(Cell c){
		myCellx=c.getLocation().getX();
		myCelly=c.getLocation().getY();
	}
	
	public Shape getShape(Cell c){
		return cellMap.get(c);
	}
	
	public void clearGrid(){
		for (Shape shape: myShapeCollection) {
			myRoot.getChildren().remove(shape);
		}
		
//		if(!myShapeCollection.isEmpty()){
//			Iterator<Shape> gridClearer= getShapeIterator();
//			while(gridClearer.hasNext()){
//				Shape s=gridClearer.next();
//				myRoot.getChildren().remove(s);
//			}	
//		}
	}

	public abstract void addCell(Cell currcell); 
	
}
// Comment all public methods and explain
// Chnage protected variables to privvate and add getters