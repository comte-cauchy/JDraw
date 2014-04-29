package jdraw.bmark.figures;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import jdraw.bmark.figurehandles.Handle;
import jdraw.framework.Figure;
import jdraw.framework.FigureEvent;
import jdraw.framework.FigureHandle;
import jdraw.framework.FigureListener;

/**
 * Abstract Base class for all shapes that will be displayed in JDraw. Written
 * to prevent redundant work in the extending classes.
 * 
 * 
 * @author Mark Ballandies
 * @author Linus Groner
 */
public abstract class AbstractFigure implements Figure {
	protected LinkedList<FigureListener> listeners = new LinkedList<FigureListener>();
	protected List<FigureHandle> handles = new LinkedList<FigureHandle>();

	/**
	 * When a Figure is changed, its listeners are informed through a call of
	 * informAll()
	 */
	protected void informAll() {
		FigureEvent e = new FigureEvent(this);
		for (FigureListener x : listeners) {
			x.figureChanged(e);
		}

	}

	/**
	 * draw Figure to a given Graphics context g
	 */
	public abstract void draw(Graphics g);

	/**
	 * Moves the figure from its original position by offset (dx,dy)
	 * 
	 * @param dx offset from original position in x-direction
	 * 
	 * @param dy offset from original position in y-direction
	 */
	@Override
	public void move(int dx, int dy) {
		Rectangle r = getBounds();
		setBounds(new Point(r.x + dx, r.y + dy), new Point(r.x + r.width + dx,
				r.y + dy + r.height));
		if (!(dx == 0 && dy == 0))
			informAll();
	}

	/**
	 * returns true, if the Point (x,y) is inside the represented Shape.
	 */
	@Override
	public abstract boolean contains(int x, int y);

	/**
	 * set the Position and size of the Figure by specifzing the upper left and
	 * the lower right corner of a rectangle that wraps around the figure
	 */
	@Override
	public abstract void setBounds(Point origin, Point corner);

	/**
	 * returns the rectangle that wraps around the figure
	 */
	@Override
	public abstract Rectangle getBounds();

	/**
	 * Returns a list of 8 handles for this Rectangle.
	 * 
	 * @return all handles that are attached to the targeted figure.
	 * @see jdraw.framework.Figure#getHandles()
	 */
	@Override
	public List<FigureHandle> getHandles() {
		return handles;
	}

	/**
	 * adds a FigureListener to the list of listeners, s.t. it is informed
	 * through informAll
	 */
	@Override
	public void addFigureListener(FigureListener listener) {
		listeners.add(listener);

	}

	/**
	 * removes a FigureListener from the list of listeners, s.t. it will no
	 * longer be informed by informAll
	 */
	@Override
	public void removeFigureListener(FigureListener listener) {
		listeners.remove(listener);
	}

	/*
	 * This is just here to suppress an annoying error.
	 */
	@Override
	public Figure clone() {
		return null;
	}

}
