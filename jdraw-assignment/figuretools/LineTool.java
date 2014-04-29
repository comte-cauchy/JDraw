package jdraw.bmark.figuretools;

import java.awt.Point;
import java.awt.event.MouseEvent;

import jdraw.bmark.figures.Line;

import jdraw.framework.DrawContext;


public class LineTool extends FigureTool {

	
	public LineTool(DrawContext context ){
		super(context);
		FigureName = new String("line");
		
	}
	
	@Override
	public void mouseDown(int x, int y, MouseEvent e) {
		if (newFigure != null) {
			throw new IllegalStateException();
		}
		anchor = new Point(x, y);
		newFigure = new Line(x, y, x,y);
		view.getModel().addFigure(newFigure);

	}
		

	

}
