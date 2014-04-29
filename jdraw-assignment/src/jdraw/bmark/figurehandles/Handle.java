package jdraw.bmark.figurehandles;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;

import jdraw.framework.DrawView;
import jdraw.framework.Figure;
import jdraw.framework.FigureEvent;
import jdraw.framework.FigureHandle;
import jdraw.framework.FigureListener;

public class Handle implements FigureHandle, FigureListener {

	private Point location;
	private HandleState state;
	private Figure owner;

	public Handle(Figure owner, HandleState initialState) {
		this.owner = owner;
		state = initialState;
		location = state.adaptedLocation(owner);
		owner.addFigureListener(this);
	}

	@Override
	public void figureChanged(FigureEvent e) {
		state = adaptedState(owner);
		location = state.adaptedLocation(owner);
	}

	@Override
	public Figure getOwner() {
		return owner;
	}

	@Override
	public Point getLocation() {
		return location;
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect(location.x - 2, location.y - 2, 5, 5);
		g.setColor(Color.BLACK);
		g.drawRect(location.x - 2, location.y - 2, 5, 5);
	}

	@Override
	public Cursor getCursor() {
		return state.getCursor();
	}

	@Override
	public boolean contains(int x, int y) {

		return (x >= location.x - 2 && x <= location.x + 2)
				&& (y >= location.y - 2 && y <= location.y + 2);
	}

	@Override
	public void startInteraction(int x, int y, MouseEvent e, DrawView v) {
	}

	@Override
	public void dragInteraction(int x, int y, MouseEvent e, DrawView v) {
		Rectangle r = state.newBounds(x, y, owner.getBounds());
		owner.setBounds(r.getLocation(),
				new Point((int) r.getMaxX(), (int) r.getMaxY()));
	}

	@Override
	public void stopInteraction(int x, int y, MouseEvent e, DrawView v) {
		Rectangle r = state.newBounds(x, y, owner.getBounds());
		if (r.width < 0)
			r.width = 0;
		if (r.height < 0)
			r.height = 0;
		owner.setBounds(r.getLocation(),
				new Point((int) r.getMaxX(), (int) r.getMaxY()));
	}

}
