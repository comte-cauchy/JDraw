package jdraw.bmark.figurehandles;

import java.awt.Cursor;
import java.awt.Point;
import java.awt.Rectangle;

import jdraw.framework.Figure;

public class SouthWestHandleState implements HandleState {
	private static SouthWestHandleState state = new SouthWestHandleState();

	@Override
	public Point adaptedLocation(Figure figure) {
		return new Point((int)figure.getBounds().getMinX(),(int) figure.getBounds().getMaxY());
	}

	@Override
	public Cursor getCursor() {
		return Cursor.getPredefinedCursor(Cursor.SW_RESIZE_CURSOR);
	}

	@Override
	public Rectangle newBounds(int x, int y, Rectangle oldBounds) {
		if (x > oldBounds.getMaxX())
			x = (int) oldBounds.getMaxX();
		if (y < oldBounds.getMinY())
			y = (int) oldBounds.getMinY();
		oldBounds.width=(int) (oldBounds.getMaxX()-x);
		oldBounds.x=x;
		oldBounds.height = (int) (y-oldBounds.getMinY());
		return oldBounds;
	}

	public static SouthWestHandleState getState() {
		return state;
	}

}
