package eg.edu.alexu.csd.oop.draw;
import java.util.Map;
import java.awt.Graphics;
import java.awt.Point;
import java.util.HashMap;
public class StraightLine extends Shapes {
    StraightLine(Point p1, Point p2){
    	Map<String, Double> temp = new HashMap<>();
    	temp.put("X1", p1.getX());
    	temp.put("X2", p2.getX());
    	temp.put("Y1", p1.getY());
    	temp.put("Y2", p2.getY());
    	setProperties(temp);
    	setPosition(p1);
    }
	@Override
	public void draw(Graphics canvas) {
		canvas.setColor(super.getColor());
		canvas.drawLine((int)Math.round(super.getProperties().get("X1")),(int)Math.round(super.getProperties().get("Y1")),(int)Math.round(super.getProperties().get("X2")),(int)Math.round(super.getProperties().get("Y2")));
	}
	public Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return null;
	}

}
