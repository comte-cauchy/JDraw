package jdraw.bmark.figures;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.LinkedList;
import java.util.List;

import jdraw.framework.Figure;
import jdraw.framework.FigureEvent;
import jdraw.framework.FigureHandle;
import jdraw.framework.FigureListener;

public class Line implements Figure {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private java.awt.geom.Line2D.Float line;
	private LinkedList<FigureListener> listeners;
	
	void informall(){
		FigureEvent e = new FigureEvent(this);
		for(FigureListener x : listeners){
			x.figureChanged(e);
		}
	}
	
	public Line(int x, int y, int w , int h){
		
		line = new java.awt.geom.Line2D.Float((float)x,(float)y,(float)x,(float)y);
		listeners = new LinkedList<FigureListener>();
		
	}

	@Override
	public void draw(Graphics g) {
		g.drawLine((int) line.getX1(), (int)line.getY1(),(int) line.getX2(),(int) line.getY2());
	

	}

	@Override
	public void move(int dx, int dy) {
		if(dx != 0 || dy != 0){
			line.setLine((int) line.getX1() +dx, (int) line.getY1()+dy, (int) line.getX2()+dx, (int) line.getY2() +dy);
			informall();
		}

	}

	@Override
	public boolean contains(int x, int y) {
		
		return line.contains((double) x, (double) y);
	}

	@Override
	public void setBounds(Point origin, Point corner) {
		java.awt.geom.Line2D.Float original = line;
		line.setLine(origin, corner);
		//if(!original.equals(line)){
			informall();
		//}

	}

	@Override
	public Rectangle getBounds() {
		
		return line.getBounds();
	
	}

	@Override
	public List<FigureHandle> getHandles() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addFigureListener(FigureListener listener) {
		
		listeners.add(listener);

	}

	@Override
	public void removeFigureListener(FigureListener listener) {
		listeners.remove(listener);

	}
	@Override
	public Figure clone(){
		return null;
		
	}

}
