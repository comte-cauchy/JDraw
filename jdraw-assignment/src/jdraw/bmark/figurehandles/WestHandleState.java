package jdraw.bmark.figurehandles;

import java.awt.Cursor;
import java.awt.Point;
import java.awt.Rectangle;

import jdraw.framework.Figure;

public class WestHandleState implements HandleState {
	public static WestHandleState state = new WestHandleState();

	@Override
	public Point adaptedLocation(Figure figure) {
		return new Point((int) figure.getBounds().getMinX(), (int) figure
				.getBounds().getCenterY());
	}

	@Override
	public Cursor getCursor() {
		return Cursor.getPredefinedCursor(Cursor.W_RESIZE_CURSOR);
	}

	@Override
	public Rectangle newBounds(int x, int y, Rectangle oldBounds) {
		if (x > oldBounds.getMaxX())
			x = (int) oldBounds.getMaxX();
		oldBounds.width = (int) (oldBounds.getMaxX() - x);
		oldBounds.x = x;
		return oldBounds;
	}

	public static HandleState getState() {
		return state;
	}

}
