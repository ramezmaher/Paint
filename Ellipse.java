package eg.edu.alexu.csd.oop.draw;

import java.util.Map;
import java.awt.Graphics;
import java.awt.Point;
import java.util.HashMap;
public class Ellipse extends Shapes {

	Ellipse(Point p1, Point p2){
		double x,y,maj =0.0 ,min = 0.0; 
		x = Math.abs(p1.getX() - p2.getY());
		y = Math.abs(p1.getY() - p2.getY());
		if (x > y) {
			maj = x ;
			min = y;
		}
		else {
			y=maj;
			x=min;
		}
		 Map<String , Double> temp = new HashMap<>();
		 temp.put("EllipseMajor", maj);
		 temp.put("EllipseMinor", min);
		 setProperties(temp);
		 setPosition(p1);
	}
	@Override
	public void draw(Graphics canvas) {
		canvas.setColor(super.getColor());
		canvas.drawOval((int)super.getPosition().getX(),(int)super.getPosition().getY(),(int)Math.round(super.getProperties().get("EllipseMajor")),(int)Math.round(super.getProperties().get("EllipseMinor")));
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return null;
	}

}
