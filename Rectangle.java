package eg.edu.alexu.csd.oop.draw;

import java.awt.Graphics;
import java.awt.Point;
import java.util.HashMap;
import java.util.Map;

public class Rectangle extends Shapes {

	public Rectangle(Point P1, Point P2)
	{
		Map<String,Double> temp = new HashMap<String,Double>();
		Double l1,l2;
		l1 = Math.abs(P1.getX()-P2.getX());
		l2 = Math.abs(P1.getY()-P2.getY());
		temp.put("width", l1);
		temp.put("height", l2);
		setPosition(P1);
		setProperties(temp);
		
	}
	@Override
	public void draw(Graphics canvas) {
		canvas.setColor(super.getColor());
		canvas.drawRect((int)super.getPosition().getX(), (int)super.getPosition().getY(), (int)Math.round(super.getProperties().get("width")),(int)Math.round(super.getProperties().get("height")));
		
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return null;
	}

}
