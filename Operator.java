package eg.edu.alexu.csd.oop.draw;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Operator extends MouseAdapter {
	Point p1 = new Point();
	Point p2 = new Point();
		public void mousePressed(MouseEvent e) {
		if ((e.getX()>= 12) && (e.getX()<= 833) && (e.getY()>= 74) && (e.getY()<= 602) ) {
			p1.x = e.getX();
			p1.y = e.getY();
			System.out.println(e.getX() + ":" + e.getY());
			}	
			super.mousePressed(e);
		}
		@Override
			public void mouseReleased(MouseEvent e) {
			if ((e.getX()>= 12) && (e.getX()<= 833) && (e.getY()>= 74) && (e.getY()<= 602) ) {
				p2.x = e.getX();
				p2.y = e.getY();
				System.out.println(e.getX() + "  " + e.getY());
				}	
				super.mouseReleased(e);
			}
@Override
public void mouseDragged(MouseEvent e) {
	if ((e.getX()>= 12) && (e.getX()<= 833) && (e.getY()>= 74) && (e.getY()<= 602) ) {
		p2.x = e.getX();
		p2.y = e.getY();
		System.out.println(e.getX() + " ," + e.getY());
		}	
	super.mouseDragged(e);
}
}