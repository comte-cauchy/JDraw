package jdraw.bmark.figuretools;

import java.awt.Cursor;
import java.awt.Point;
import java.awt.event.MouseEvent;

import javax.swing.Icon;
import javax.swing.ImageIcon;

import jdraw.framework.DrawContext;
import jdraw.framework.DrawTool;
import jdraw.framework.DrawView;
import jdraw.framework.Figure;

public abstract class FigureTool implements DrawTool {

	protected static final String IMAGES = "/images/";
	
	protected String FigureName;
	protected Figure newFigure = null;
	
	protected DrawContext context;
	protected DrawView view;

	
	protected Point anchor=null;

	
	public FigureTool(DrawContext context) {
		this.context = context;
		this.view = context.getView();
	}
	@Override
	public void deactivate() {
		this.context.showStatusText("");

	}
	
	@Override
	public void activate(){
		String temp = new String(FigureName);
		temp = temp+ "Mode";
		this.context.showStatusText(temp);
	}


	@Override
	public Cursor getCursor() {
		return Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR);
	}

	@Override
	public Icon getIcon() {
		String temp =new String(FigureName);
		temp = IMAGES + temp + ".png";
		System.out.println(temp);
		return new ImageIcon(getClass().getResource(temp.toLowerCase()));
	}

	@Override
	public String getName(){
		return FigureName;
	}
	@Override
	abstract public void mouseDown(int x, int y, MouseEvent e);

	@Override
	public void mouseDrag(int x, int y, MouseEvent e){
		newFigure.setBounds(anchor, new Point(x, y));
		java.awt.Rectangle r = newFigure.getBounds();
		this.context.showStatusText("w: " + r.width + ", h: " + r.height );
	}

	@Override
	public void mouseUp(int x, int y, MouseEvent e){
		newFigure = null;
		anchor = null;
		String temp = new String(FigureName);
		temp = temp + " Mode";
		this.context.showStatusText(temp);
	}
}
