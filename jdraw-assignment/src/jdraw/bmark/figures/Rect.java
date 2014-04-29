/*
 * Copyright (c) 2000-2014 Fachhochschule Nordwestschweiz (FHNW)
 * All Rights Reserved. 
 */

package jdraw.bmark.figures;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.List;
import java.util.ArrayList;

import jdraw.bmark.figurehandles.*;
import jdraw.framework.Figure;
import jdraw.framework.FigureHandle;
import jdraw.framework.FigureListener;
import jdraw.framework.FigureEvent;

/**
 * Represents rectangles in JDraw.
 * 
 * @author Christoph Denzler
 * 
 */
public class Rect extends AbstractFigure {

	private java.awt.Rectangle rectangle;

	/**
	 * Create a new rectangle of the given dimension.
	 * 
	 * @param x
	 *            the x-coordinate of the upper left corner of the rectangle
	 * @param y
	 *            the y-coordinate of the upper left corner of the rectangle
	 * @param w
	 *            the rectangle's width
	 * @param h
	 *            the rectangle's height
	 */
	public Rect(int x, int y, int w, int h) {
		rectangle = new java.awt.Rectangle(x, y, w, h);
		handles.add(new Handle(this, NorthHandleState.getState()));
		handles.add(new Handle(this, EastHandleState.getState()));
		handles.add(new Handle(this, SouthHandleState.getState()));
		handles.add(new Handle(this, WestHandleState.getState()));
		handles.add(new Handle(this, NorthEastHandleState.getState()));
		handles.add(new Handle(this, SouthEastHandleState.getState()));
		handles.add(new Handle(this, SouthWestHandleState.getState()));
		handles.add(new Handle(this, NorthWestHandleState.getState()));
	}

	/**
	 * Draw the rectangle to the given graphics context.
	 * 
	 * @param g
	 *            the graphics context to use for drawing.
	 */
	@Override
	public void draw(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
		g.setColor(Color.BLACK);
		g.drawRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
	}

	@Override
	public void setBounds(Point origin, Point corner) {
		rectangle.setFrameFromDiagonal(origin, corner);
		informAll();
	}

	@Override
	public boolean contains(int x, int y) {
		return rectangle.contains(x, y);
	}

	@Override
	public Rectangle getBounds() {
		return rectangle.getBounds();
	}

}
