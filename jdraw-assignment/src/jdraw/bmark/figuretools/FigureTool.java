package jdraw.bmark.figuretools;

import java.awt.Cursor;
import java.awt.Point;
import java.awt.event.MouseEvent;

import javax.swing.Icon;
import javax.swing.ImageIcon;

import jdraw.bmark.figures.Line;
import jdraw.framework.DrawContext;
import jdraw.framework.DrawTool;
import jdraw.framework.DrawView;
import jdraw.framework.Figure;

/**
 * Abstract Base class for all DrawTools Written to prevent redundant work in
 * the extending classes.
 * 
 * As wee see it, the abstraction could be driven even further, by making
 * FigureName a part of the figures and by requiring all AbstractFigure
 * constructors to be of the kind Figure(x,y,w,h) the extending DrawTools would
 * then be obsolete.
 * 
 * @author Mark Ballandies
 * @author Linus Groner
 */
public abstract class FigureTool implements DrawTool {

	protected static final String IMAGES = "/images/";

	protected String FigureName;
	protected Figure newFigure = null;

	protected DrawContext context;
	protected DrawView view;

	protected Point anchor = null;

	public FigureTool(DrawContext context) {
		this.context = context;
		this.view = context.getView();
	}

	/**
	 * If the Tool is unselected, this is called
	 */
	@Override
	public void deactivate() {
		this.context.showStatusText("");

	}

	/**
	 * Upon selecting the Tool, the DrawContext will display its name.
	 */
	@Override
	public void activate() {
		String temp = new String(FigureName);
		temp = temp + "Mode";
		this.context.showStatusText(temp);
	}

	/**
	 * Determins what shape the Mouse Cursor will have for FigureTool Mode
	 */
	@Override
	public Cursor getCursor() {
		return Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR);
	}

	/**
	 * Determines what the Tool's Button will look like
	 */
	@Override
	public Icon getIcon() {
		String temp = new String(FigureName);
		temp = IMAGES + temp + ".png";
		System.out.println(temp);
		return new ImageIcon(getClass().getResource(temp.toLowerCase()));
	}

	/**
	 * returns the Figures descriptive name string
	 */
	@Override
	public String getName() {
		return FigureName;
	}

	/**
	 * upon clicking, a Figure of size zero is drawn.
	 */
	@Override
	public void mouseDown(int x, int y, MouseEvent e) {
		if (newFigure != null) {
			throw new IllegalStateException();
		}
		anchor = new Point(x, y);
		newFigure = newFigureAtSinglePoint(x, y);
		view.getModel().addFigure(newFigure);

	};

	/**
	 * Wrapper for the constructor of extended figure tools s.t. mouseDown could
	 * be implemented in the abstract Class for all FigureTools.
	 * 
	 * @param x
	 *            x-coordinate where a FigureTool of Size 0 is to be constructed
	 * @param y
	 *            x-coordinate where a FigureTool of Size 0 is to be constructed
	 * @return Figure of size 0 at point (x,y)
	 */
	protected abstract Figure newFigureAtSinglePoint(int x, int y);

	/**
	 * When the mouse is dragged, the new shape will follow the mouse motion, to
	 * this end, the Bounds are set according to the mouse position
	 */
	@Override
	public void mouseDrag(int x, int y, MouseEvent e) {
		newFigure.setBounds(anchor, new Point(x, y));
		java.awt.Rectangle r = newFigure.getBounds();
		this.context.showStatusText("w: " + r.width + ", h: " + r.height);
	}

	/**
	 * When the mouse button is released, everything is reset to the initial configuration.
	 */
	@Override
	public void mouseUp(int x, int y, MouseEvent e) {
		newFigure = null;
		anchor = null;
		String temp = new String(FigureName);
		temp = temp + " Mode";
		this.context.showStatusText(temp);
	}
}
