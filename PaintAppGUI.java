package eg.edu.alexu.csd.oop.draw;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.ImageIcon;
import java.awt.SystemColor;
import javax.swing.JProgressBar;
import javax.swing.JSlider;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;

import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.JTextField;

public class PaintAppGUI {

	private JFrame frame;
	Point p1 = new Point();
	Point p2 = new Point();
	Color BrushColor = (Color.black);
	Color BrushColorPrev = (Color.black);
	Color FillColor = (Color.white); 
	JComboBox<String> MyShapes = new JComboBox<String>();
	JSlider BrushSize = new JSlider(5,100,30);
	int brush = 30;
	PaintApp drawer = new PaintApp();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PaintAppGUI window = new PaintAppGUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public PaintAppGUI() {
		initialize();
	}
	
	Map<String,Integer> m = new HashMap<>();
	private JTextField textFieldBrush;
	private JTextField textFieldFill;
	private void setM() {
		m.put(" ", 0);
		m.put("Circle", 1);
		m.put("Triangle", 2);
		m.put("Rectangle", 3);
		m.put("Ellipse", 4);
		m.put("Square", 5);
		m.put("Line", 6);
		m.put("Brush",7);
	}
	
	
		
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(0, 0, 128));
		frame.setResizable(false);
		frame.setBounds(100, 100, 851, 650);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
	
		JPanel panel = new JPanel();
		drawer.SetMyPanel(panel);
		panel.addMouseMotionListener(new MouseMotionAdapter() {
			
			@Override
			public void mouseDragged(MouseEvent e) {
				setM();
				int Index = m.get((String)MyShapes.getSelectedItem());
				Point Pnow = new Point() ;
				Pnow.x = e.getX();
			    Pnow.y = e.getY();
				if (Index == 7) {
				Graphics canvas = panel.getGraphics();
				canvas.setColor(BrushColor);
				canvas.fillOval(e.getX(), e.getY(), brush, brush);
				}	
				else {
					
					Shape currentArr [] = drawer.getShapes();
				    Graphics canvas = panel.getGraphics();
				    Shape s = getShape(Index,p1,Pnow);
					s.setColor(BrushColor);
					s.setFillColor(FillColor);
					if (drawer.ArrSize() == 0) {
						drawer.addShape(s);
					}
					else {
					panel.update(canvas);	
					drawer.updateShape(currentArr[drawer.ArrSize()-1], s);
					}
					drawer.refresh(canvas);
				}
				super.mouseDragged(e);
			
			}
		});
		
		panel.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				
					p1.x = e.getX();
					p1.y = e.getY();
					
					super.mousePressed(e);
				}
			@Override
			public void mouseReleased(MouseEvent e) {
				
					p2.x = e.getX();
					p2.y = e.getY();
					
		
					
					Graphics canvas = panel.getGraphics();
					setM();
					
					int shapeIndex = m.get((String)MyShapes.getSelectedItem());
					if (shapeIndex != 0 && shapeIndex != 7 ) {
					Shape s = getShape(shapeIndex,p1,p2);
					s.setColor(BrushColor);
					s.setFillColor(FillColor);
					drawer.addShape(s);
					drawer.refresh(canvas);
			        }
					super.mouseReleased(e);
				}
			
		}
		);
		panel.setBounds(12, 74, 821, 528);
		panel.setBackground(Color.WHITE);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		
		
		
		BrushSize.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				JSlider s = (JSlider)e.getSource();
				brush = s.getValue();
				
			}
		});
		
		BrushSize.createStandardLabels(5,5);
		BrushSize.setMajorTickSpacing(10);
		BrushSize.setMinorTickSpacing(1);
		BrushSize.setBounds(220, 34, 200, 26);
		frame.getContentPane().add(BrushSize);
		
		JLabel lblBrushSize = new JLabel("Brush Size");
		lblBrushSize.setBackground(Color.WHITE);
		lblBrushSize.setFont(new Font("Neo Tech Alt Medium", Font.BOLD, 17));
		lblBrushSize.setForeground(new Color(255, 255, 255));
		lblBrushSize.setBounds(220, 13, 156, 16);
		frame.getContentPane().add(lblBrushSize);
		MyShapes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (BrushColor == Color.WHITE) {
					BrushColor = BrushColorPrev;
				}
			}
		});
		
		
		MyShapes.setModel(new DefaultComboBoxModel<String>(new String[] {" " ,"Circle", "Triangle", "Rectangle", "Ellipse", "Square", "Line" , "Brush"}));
		MyShapes.setBounds(680, 34, 133, 26);
		frame.getContentPane().add(MyShapes);
		
		
		JLabel lblShapes = new JLabel("Shapes");
		lblShapes.setForeground(new Color(255, 255, 255));
		lblShapes.setFont(new Font("Neo Tech Alt Medium", Font.BOLD, 18));
		lblShapes.setBounds(680, 5, 87, 24);
		frame.getContentPane().add(lblShapes);
		
		JButton SaveBtn = new JButton("");
		SaveBtn.setIcon(new ImageIcon("C:\\Users\\ramez\\eclipse-workspace\\DrawingApplication\\Images\\iconfinder_floppy_285657.png"));
		SaveBtn.setBounds(12, 13, 50, 50);
		frame.getContentPane().add(SaveBtn);
		
		JButton LoadBtn = new JButton("");
		LoadBtn.setIcon(new ImageIcon("C:\\Users\\ramez\\eclipse-workspace\\DrawingApplication\\Images\\iconfinder_get-money_3338979.png"));
		LoadBtn.setBounds(74, 13, 50, 50);
		frame.getContentPane().add(LoadBtn);
		
		JButton UndoBtn = new JButton("");
		UndoBtn.setIcon(new ImageIcon("C:\\Users\\ramez\\eclipse-workspace\\DrawingApplication\\Images\\iconfinder_icon-ios7-undo_211838.png"));
		UndoBtn.setBounds(136, 13, 30, 50);
		frame.getContentPane().add(UndoBtn);
		
		JButton RedoBtn = new JButton("");
		RedoBtn.setIcon(new ImageIcon("C:\\Users\\ramez\\eclipse-workspace\\DrawingApplication\\Images\\iconfinder_icon-ios7-redo_211811.png"));
		RedoBtn.setBounds(178, 13, 30, 50);
		frame.getContentPane().add(RedoBtn);
		
		JButton BrushBtn = new JButton("");
		BrushBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MyShapes.setSelectedItem("Brush");
				if (BrushColor == Color.WHITE) {
					BrushColor = BrushColorPrev;
				}
			}
		});
		BrushBtn.setBounds(432, 13, 50, 50);
		frame.getContentPane().add(BrushBtn);
		BrushBtn.setIcon(new ImageIcon("C:\\Users\\ramez\\eclipse-workspace\\DrawingApplication\\Images\\iconfinder_paintbrush2_1055017.png"));
		
		JButton EraseBtn = new JButton("");
		EraseBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				setM();
				if (m.get((String)MyShapes.getSelectedItem()) == 7) {
					BrushColor = Color.WHITE ;
					textFieldBrush.setBackground(BrushColorPrev);
				}
			}
		});
		EraseBtn.setBounds(494, 13, 50, 50);
		frame.getContentPane().add(EraseBtn);
		EraseBtn.setBackground(SystemColor.menu);
		EraseBtn.setIcon(new ImageIcon("C:\\Users\\ramez\\eclipse-workspace\\DrawingApplication\\Images\\iconfinder_05_Eraser_2064480.png"));
		
		JButton ColorsBtn = new JButton("");
		ColorsBtn.setBounds(556, 13, 50, 50);
		frame.getContentPane().add(ColorsBtn);
		ColorsBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BrushColor = JColorChooser.showDialog(null, "Pick the brush color", BrushColor);
				if (BrushColor == null ) {
					BrushColor = (Color.black);
					BrushColorPrev = (Color.black);
					textFieldBrush.setBackground(BrushColor);
				}
				BrushColorPrev = BrushColor ;
				textFieldBrush.setBackground(BrushColor);
			}
		});
		ColorsBtn.setIcon(new ImageIcon("C:\\Users\\ramez\\eclipse-workspace\\DrawingApplication\\Images\\iconfinder_paintcan_1055016.png"));
		
		JButton FillBtn = new JButton("");
		FillBtn.setBounds(618, 13, 50, 50);
		frame.getContentPane().add(FillBtn);
		FillBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FillColor = JColorChooser.showDialog(null, "Pick the fill color", FillColor);
				if (BrushColor == null ) {
					FillColor = (Color.white);
					textFieldFill.setBackground(FillColor);
				}
				textFieldFill.setBackground(FillColor);
			}
		});
		FillBtn.setBackground(new Color(210, 105, 30));
		FillBtn.setIcon(new ImageIcon("C:\\Users\\ramez\\eclipse-workspace\\DrawingApplication\\Images\\iconfinder_eyedropper_1055064.png"));
		
		textFieldBrush = new JTextField();
		textFieldBrush.setBounds(556, 0, 50, 10);
		frame.getContentPane().add(textFieldBrush);
		textFieldBrush.setEditable(false);
		textFieldBrush.setBackground(BrushColor);
		textFieldBrush.setColumns(10);
		
		textFieldFill = new JTextField();
		textFieldFill.setBounds(618, 0, 50, 10);
		frame.getContentPane().add(textFieldFill);
		textFieldFill.setEditable(false);
		textFieldFill.setBackground(FillColor);
		textFieldFill.setColumns(10);
	}
	private Shape getShape (int x,Point p1,Point p2)
	{
		Shape s;
		switch (x)
		{
		
		case 1:
			s = new Circle(p1,p2);
			break;
		case 2:
			s = new Triangle(p1,p2);
			break;
		case 3:
			s = new Rectangle(p1,p2);
			break;
		case 4:
			s = new Ellipse(p1,p2);
			break;
		case 5:
			s = new Square(p1,p2);
			break;
		case 6:
			s = new StraightLine(p1,p2);
			break;
			default:
				
				s = new FreeDraw(1,p1);
			
		}
		
		return s;
		
	}
}
