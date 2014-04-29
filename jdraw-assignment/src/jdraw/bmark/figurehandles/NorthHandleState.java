package jdraw.bmark.figurehandles;

import java.awt.Cursor;
import java.awt.Point;
import java.awt.Rectangle;

import jdraw.framework.Figure;

public class NorthHandleState implements HandleState {
	private static final NorthHandleState state = new NorthHandleState();

	@Override
	public Point adaptedLocation(Figure figure) {
		return new Point((int) figure.getBounds().getCenterX(), (int) figure
				.getBounds().getMinY());
	}

	@Override
	public Cursor getCursor() {
		return Cursor.getPredefinedCursor(Cursor.N_RESIZE_CURSOR);
	}

	@Override
	public Rectangle newBounds(int x, int y, Rectangle oldBounds) {

		if (y > oldBounds.getMaxY())
			y = (int) oldBounds.getMaxY();
		oldBounds.height = (int) (oldBounds.getMaxY() - y);
		oldBounds.y = y;

		return oldBounds;
	}

	public static NorthHandleState getState() {
		return state;
	}

}
