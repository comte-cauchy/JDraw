package jdraw.bmark.figurehandles;

import java.awt.Cursor;
import java.awt.Point;
import java.awt.Rectangle;

import jdraw.framework.Figure;

public class SouthHandleState implements HandleState {
	private static SouthHandleState state = new SouthHandleState();

	@Override
	public Point adaptedLocation(Figure figure) {
		return new Point((int) figure.getBounds().getCenterX(), (int) figure
				.getBounds().getMaxY());
	}

	@Override
	public Cursor getCursor() {
		return Cursor.getPredefinedCursor(Cursor.S_RESIZE_CURSOR);
	}

	@Override
	public Rectangle newBounds(int x, int y, Rectangle oldBounds) {
		if (y < oldBounds.getMinY())
			y = (int) oldBounds.getMinY();
		oldBounds.height = (int) (y - oldBounds.getMinY());
		return oldBounds;
	}

	public static HandleState getState() {
		return state;
	}

}
