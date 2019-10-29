package eg.edu.alexu.csd.oop.draw;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

public class PaintApp implements DrawingEngine {
	private ArrayList<Shape> ShapeArr = new ArrayList<Shape>();
    JPanel panel;
	public void SetMyPanel(JPanel p) {
		this.panel = p ;
	}
	public int ArrSize() {
		return ShapeArr.size();
	}
	
	@Override
	public void refresh(Graphics canvas) {

		
	    for (int i=0 ; i<ShapeArr.size() ; i++) {
	    	ShapeArr.get(i).draw(canvas);
	    }
	   

	}

	@Override
	public void addShape(Shape shape) {
		ShapeArr.add(shape);
		System.out.println(ShapeArr.size());

	}

	@Override
	public void removeShape(Shape shape) {
		
		Shape s;
		for(int i=0;i<ShapeArr.size();i++)
		{
			s = ShapeArr.get(i);
			if(s == shape)
			{
				ShapeArr.remove(i);
			}
		}
		
	}

	@Override
	public void updateShape(Shape oldShape, Shape newShape) {
	     if (oldShape == newShape) {
	    	 return;
	     } 
	     else {
	    	 Shape s;
	    	 s = oldShape;
	    	 for (int i=0 ; i<ShapeArr.size() ; i++ ) {
	    		 if (s == ShapeArr.get(i)) {
	    			 ShapeArr.set(i, newShape) ;
	    		 }
	    	 }
	    	 
	     }
	}

	@Override
	public Shape[] getShapes() {
		int y = ShapeArr.size();
		Shape s[] = new Shapes[y] ;
		for (int i=0 ; i<y ; i++ ) {
			s[i] = ShapeArr.get(i) ;
		}
		return s;
	}

	@Override
	public List<Class<? extends Shape>> getSupportedShapes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void undo() {
		// TODO Auto-generated method stub

	}

	@Override
	public void redo() {
		// TODO Auto-generated method stub

	}

	@Override
	public void save(String path) {
		// TODO Auto-generated method stub

	}

	@Override
	public void load(String path) {
		// TODO Auto-generated method stub

	}

}
