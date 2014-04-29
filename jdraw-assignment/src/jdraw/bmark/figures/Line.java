package jdraw.bmark.figures;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.LinkedList;
import java.util.List;

import jdraw.framework.Figure;
import jdraw.framework.FigureEvent;
import jdraw.framework.FigureHandle;
import jdraw.framework.FigureListener;

public class Line extends AbstractFigure {

	private static final long serialVersionUID = 1L;

	private java.awt.geom.Line2D.Float line;

	public Line(int x, int y, int w, int h) {
		line = new java.awt.geom.Line2D.Float((float) x, (float) y, (float) x
				+ w, (float) y + h);
	}

	@Override
	public void draw(Graphics g) {
		g.drawLine((int) line.getX1(), (int) line.getY1(), (int) line.getX2(),
				(int) line.getY2());
	}

	@Override
	public boolean contains(int x, int y) {

		return line.contains((double) x, (double) y);
	}

	@Override
	public void setBounds(Point origin, Point corner) {
		java.awt.geom.Line2D.Float original = line;
		line.setLine(origin, corner);
		if (!(original.x1 == line.x1 && original.x2 == line.x2
				&& original.y1 == line.y1 && original.y2 == line.y2)) {

		}
		informAll();

	}

	@Override
	public Rectangle getBounds() {

		return line.getBounds();

	}

}
