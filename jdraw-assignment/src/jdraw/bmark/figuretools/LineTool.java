package jdraw.bmark.figuretools;

import java.awt.Point;
import java.awt.event.MouseEvent;

import jdraw.bmark.figures.Line;

import jdraw.framework.DrawContext;
import jdraw.framework.Figure;


public class LineTool extends FigureTool {

		
	
	public LineTool(DrawContext context ){
		super(context);
		FigureName = "line";
	}
	
	@Override
	protected Figure newFigureAtSinglePoint(int x, int y) {
			return new Line(x,y,0,0);
	}
		
}
