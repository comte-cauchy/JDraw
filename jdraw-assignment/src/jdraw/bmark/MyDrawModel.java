/*
 * Copyright (c) 2000-2013 Fachhochschule Nordwestschweiz (FHNW)
 * All Rights Reserved. 
 */

package jdraw.bmark;

//import java.util.ArrayList;
import java.util.LinkedList;
//import java.util.ArrayList;




import jdraw.framework.DrawCommandHandler;
import jdraw.framework.DrawModel;
import jdraw.framework.DrawModelListener;
import jdraw.framework.Figure;
import jdraw.framework.FigureEvent;
import jdraw.framework.FigureListener;
import jdraw.framework.DrawModelEvent.Type;
import jdraw.framework.DrawModelEvent;
import jdraw.std.EmptyDrawCommandHandler;


/**
 * Provide a standard behavior for the drawing model. This class initially does not implement the methods
 * in a proper way.
 * It is part of the course assignments to do so.
 * @author Mark Ballandies
 *
 */
public class MyDrawModel implements DrawModel, FigureListener {
	
	private LinkedList<Figure> figures;
	private LinkedList<DrawModelListener> listeners;
	
	private void informall(Figure f, Type T){
		DrawModelEvent e = new DrawModelEvent(this,f,T);
		for(DrawModelListener x: listeners){
			x.modelChanged(e);
		}
	}
	
	public MyDrawModel(){
		figures =  new LinkedList<Figure>();
		listeners= new LinkedList<DrawModelListener>();
	}
	@Override
	public void addFigure(Figure f) {
		f.addFigureListener(this);
		figures.add(f);
		
		//informs all Listeners that a Figure was added
		informall(f, Type.FIGURE_ADDED);
		
		
		//System.out.println("StdDrawModel.addFigure has to be implemented");
	}

	@Override
	public Iterable<Figure> getFigures() {
		return figures; 
	}

	@Override
	public void removeFigure(Figure f) {
		f.removeFigureListener(this);
		figures.remove(f);
		
		//informs all Listeners that a Figure was added
		informall(f, Type.FIGURE_REMOVED);
		
		
		//System.out.println("StdDrawModel.removeFigure has to be implemented");
	}

	@Override
	public void addModelChangeListener(DrawModelListener listener) {
		listeners.add(listener);
		//System.out.println("StdDrawModel.addModelChangeListener has to be implemented");
	}

	@Override
	public void removeModelChangeListener(DrawModelListener listener) {
		listeners.remove(listener);
		//System.out.println("StdDrawModel.removeModelChangeListener has to be implemented");
	}

	/** The draw command handler. Initialized here with a dummy implementation. */
	// TODO initialize with your implementation from the undo/redo-assignment.
	private DrawCommandHandler handler = new EmptyDrawCommandHandler();

	/**
	 * Retrieve the draw command handler in use.
	 * @return the draw command handler.
	 */
	//not needed now
	@Override
	public DrawCommandHandler getDrawCommandHandler() {
		return handler;
	}
	
	@Override
	public void setFigureIndex(Figure f, int index) {
		Figure temp = figures.get(index);
		figures.set(index, f);
		figures.add(temp);
		informall(f,Type.DRAWING_CHANGED);
		//System.out.println("StdDrawModel.setFigureIndex has to be implemented");
	}

	@Override
	public void removeAllFigures() {
		for(Figure x : figures){
			x.removeFigureListener(this);
		}
		Figure temp = figures.getFirst();
		figures.clear();
		
		//just pass temp, because something has to be passed?
		informall(temp, Type.DRAWING_CLEARED);
		
		
	}

	//implements the FigureListener
	@Override
	public void figureChanged(FigureEvent e) {
		
		Figure source = e.getFigure();
		
		//Inform all Listener that Figure changed
		informall(source,Type.FIGURE_CHANGED);
	}

}
