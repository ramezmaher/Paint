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
		temp.put("length", l1);
		temp.put("width", l2);
		
		setProperties(temp);
		
		int x,y;
		x = Math.min((int)P1.getX(),(int) P2.getX());
		y = Math.min((int)P1.getY(),(int) P2.getY());
		
		Point p = new Point(x,y);
		setPosition(p);
		
	}
	public Rectangle() {
		super();
	}

	@Override
	public void draw(Graphics canvas) {
		canvas.setColor(this.getColor());
		double length = this.getProperties().get("width");
		double width = this.getProperties().get("length");
		double x = this.getPosition().getX();
		double y = this.getPosition().getY();
		
		canvas.drawRect((int)x,(int) y,(int) width,(int) length);
		canvas.setColor(this.getFillColor());
		
		canvas.fillRect((int) x+1, (int) y+1,(int) width-1,(int) length-1);
		
		
	}
	public boolean contain (Point p) {
		if ((p.getX() >= this.getPosition().getX()) && (p.getY() >= this.getPosition().getY()) && (p.getX() <= (this.getPosition().getX() + this.getProperties().get("width"))) && (p.getY() <= (this.getPosition().getY() + this.getProperties().get("length")))) {
		 return true;	
		}
		else return false;
	} 
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return null;
	}

	

}