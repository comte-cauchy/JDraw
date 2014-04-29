package jdraw.bmark.figurehandles;

import java.awt.Cursor;
import java.awt.Point;
import java.awt.Rectangle;

import jdraw.framework.Figure;

public interface HandleState {
	public Point adaptedLocation(Figure figure);
	
	public Cursor getCursor();

	public Rectangle newBounds(int x, int y, Rectangle oldBounds);

}
