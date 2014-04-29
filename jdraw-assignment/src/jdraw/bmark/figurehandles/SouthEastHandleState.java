package jdraw.bmark.figurehandles;

import java.awt.Cursor;
import java.awt.Point;
import java.awt.Rectangle;

import jdraw.framework.Figure;

public class SouthEastHandleState implements HandleState {

	private static SouthEastHandleState state = new SouthEastHandleState();

	@Override
	public Point adaptedLocation(Figure figure) {
		return new Point((int) figure.getBounds().getMaxX(), (int) figure
				.getBounds().getMaxY());
	}

	@Override
	public Cursor getCursor() {
		return Cursor.getPredefinedCursor(Cursor.SE_RESIZE_CURSOR);
	}

	@Override
	public Rectangle newBounds(int x, int y, Rectangle oldBounds) {
		if (x < oldBounds.getMinX())
			x = (int) oldBounds.getMinX();
		if (y < oldBounds.getMinY())
			y = (int) oldBounds.getMinY();
		oldBounds.width = (int) (x - oldBounds.getMinX());
		oldBounds.height = (int) (y - oldBounds.getMinY());
		return oldBounds;
	}

	public static SouthEastHandleState getState() {
		return state;
	}

}
