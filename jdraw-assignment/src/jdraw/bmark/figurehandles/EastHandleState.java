package jdraw.bmark.figurehandles;

import java.awt.Cursor;
import java.awt.Point;
import java.awt.Rectangle;

import jdraw.framework.Figure;

public class EastHandleState implements HandleState {
	private static final EastHandleState state = new EastHandleState();

	@Override
	public Point adaptedLocation(Figure figure) {
		return new Point((int) figure.getBounds().getMaxX(), (int) figure
				.getBounds().getCenterY());
	}

	@Override
	public Cursor getCursor() {
		return Cursor.getPredefinedCursor(Cursor.E_RESIZE_CURSOR);
	}

	@Override
	public Rectangle newBounds(int x, int y, Rectangle oldBounds) {
		if (x < oldBounds.getMinX())
			x = (int) oldBounds.getMinX();
		oldBounds.width = (int) (x - oldBounds.getMinX());
		return oldBounds;
	}

	public static EastHandleState getState() {
		return state;
	}

}
