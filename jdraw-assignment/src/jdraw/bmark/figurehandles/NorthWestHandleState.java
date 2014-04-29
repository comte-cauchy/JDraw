package jdraw.bmark.figurehandles;

import java.awt.Cursor;
import java.awt.Point;
import java.awt.Rectangle;

import jdraw.framework.Figure;

public class NorthWestHandleState implements HandleState {
	public static NorthWestHandleState state = new NorthWestHandleState();

	@Override
	public Point adaptedLocation(Figure figure) {
		return new Point(figure.getBounds().x, figure.getBounds().y);
	}

	@Override
	public Cursor getCursor() {
		return Cursor.getPredefinedCursor(Cursor.NW_RESIZE_CURSOR);
	}

	@Override
	public Rectangle newBounds(int x, int y, Rectangle oldBounds) {
		if (x > oldBounds.getMaxX())
			x = (int) oldBounds.getMaxX();
		if (y > oldBounds.getMaxY())
			y = (int) oldBounds.getMaxY();
		oldBounds.width = (int) (oldBounds.getMaxX() - x);
		oldBounds.height = (int) (oldBounds.getMaxY() - y);
		oldBounds.x = x;
		oldBounds.y = y;
		return oldBounds;
	}

	public static HandleState getState() {
		return state;
	}
}
