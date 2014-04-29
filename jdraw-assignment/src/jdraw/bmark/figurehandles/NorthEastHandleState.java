package jdraw.bmark.figurehandles;

import java.awt.Cursor;
import java.awt.Point;
import java.awt.Rectangle;

import jdraw.framework.Figure;

public class NorthEastHandleState implements HandleState {
	private static final NorthEastHandleState state = new NorthEastHandleState();

	@Override
	public Point adaptedLocation(Figure figure) {
		return new Point((int) figure.getBounds().getMaxX(), (int) figure
				.getBounds().getMinY());
	}

	@Override
	public Cursor getCursor() {
		return Cursor.getPredefinedCursor(Cursor.NE_RESIZE_CURSOR);
	}

	@Override
	public Rectangle newBounds(int x, int y, Rectangle oldBounds) {
		if (x < oldBounds.getMinX())
			x = (int) oldBounds.getMinX();
		if (y > oldBounds.getMaxY())
			y = (int) oldBounds.getMaxY();
		oldBounds.width = (int) (x - oldBounds.getMinX());
		oldBounds.height = (int) (oldBounds.getMaxY() - y);
		oldBounds.y = y;

		return oldBounds;
	}

	public static NorthEastHandleState getState() {
		return state;
	}

}
