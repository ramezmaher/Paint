package eg.edu.alexu.csd.oop.draw;

import java.awt.Graphics;
import java.awt.Point;
import java.util.Map;
import java.util.HashMap;
public class Circle extends Shapes {
    
  Circle(Point p1, Point p2){
	  Map<String , Double> temp = new HashMap<>();
	  double diameter,x,y;
	  x = Math.abs(p1.getX() - p2.getX());
	  y = Math.abs(p1.getY() - p2.getY());
	  diameter = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
	  temp.put("Diameter", diameter);
	  setProperties(temp);
	  setPosition(p1);
  }
    @Override
    	public void draw(Graphics canvas) {
    		canvas.setColor(super.getColor());
    		canvas.drawOval((int)super.getPosition().getX(), (int)super.getPosition().getY(), (int)Math.round(super.getProperties().get("Diameter")),(int)Math.round(super.getProperties().get("Diameter")));
    	}
    public Object clone() throws CloneNotSupportedException {
    	// TODO Auto-generated method stub
    	return null;
    }
    
  
   

}
