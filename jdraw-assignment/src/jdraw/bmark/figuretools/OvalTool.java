package jdraw.bmark.figuretools;

import java.awt.Cursor;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;

import javax.swing.Icon;
import javax.swing.ImageIcon;

import jdraw.bmark.figures.Oval;
import jdraw.bmark.figures.Rect;
import jdraw.framework.DrawContext;
import jdraw.framework.DrawTool;
import jdraw.framework.DrawView;

public class OvalTool implements DrawTool {
	private static final String IMAGES = "/images/";
	private DrawContext context;
	private DrawView view;
	private Oval newOval = null;
	private Point anchor = null;
	
	public OvalTool(DrawContext context) {
		this.context = context;
		this.view = context.getView();
	}

	@Override
	public void activate() {
		this.context.showStatusText("Rectangle Mode");

	}

	@Override
	public void deactivate() {
		this.context.showStatusText("");

	}

	@Override
	public void mouseDown(int x, int y, MouseEvent e) {
		if (newOval != null) {
			throw new IllegalStateException();
		}
		anchor = new Point(x, y);
		newOval = new Oval(x, y, 0, 0);
		view.getModel().addFigure(newOval);

	}

	@Override
	public void mouseDrag(int x, int y, MouseEvent e) {
		newOval.setBounds(anchor, new Point(x, y));
		Rectangle r = newOval.getBounds();
		this.context.showStatusText("w: " + r.width + ", h: " + r.height);

	}

	@Override
	public void mouseUp(int x, int y, MouseEvent e) {
		newOval = null;
		anchor = null;
		this.context.showStatusText("Rectangle Mode");

	}

	@Override
	public Cursor getCursor() {
		return Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR);
	}

	@Override
	public Icon getIcon() {
		return new ImageIcon(getClass().getResource(IMAGES + "oval.png"));
	}

	@Override
	public String getName() {
		return "Oval";
	}

}
