package jdraw.bmark.figures;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.List;
import java.util.*;

import jdraw.bmark.figurehandles.EastHandleState;
import jdraw.bmark.figurehandles.Handle;
import jdraw.bmark.figurehandles.NorthHandleState;
import jdraw.bmark.figurehandles.SouthHandleState;
import jdraw.bmark.figurehandles.WestHandleState;
import jdraw.framework.*;

public class Oval extends AbstractFigure {
	private Ellipse2D.Float oval;

	public Oval(int x, int y, int w, int h) {
		oval = new Ellipse2D.Float(x, y, w, h);
		handles.add(new Handle(this, NorthHandleState.getState()));
		handles.add(new Handle(this, EastHandleState.getState()));
		handles.add(new Handle(this, SouthHandleState.getState()));
		handles.add(new Handle(this, WestHandleState.getState()));
		System.out.println("fisch");
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillOval((int) oval.getX(), (int) oval.getY(), (int) oval.getWidth(),
				(int) oval.getHeight());
		g.setColor(Color.BLACK);
		g.drawOval((int) oval.getX(), (int) oval.getY(), (int) oval.getWidth(),
				(int) oval.getHeight());
	}

	@Override
	public boolean contains(int x, int y) {
		return oval.contains(x, y);
	}

	@Override
	public void setBounds(Point origin, Point corner) {
		oval.setFrameFromDiagonal(origin, corner);
		informAll();
	}

	@Override
	public Rectangle getBounds() {
		return oval.getBounds();
	}


}
