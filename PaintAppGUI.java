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
import javax.swing.JMenuItem;

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
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.Toolkit;
import java.awt.Dimension;
import javax.swing.SwingConstants;
import java.awt.Window.Type;

public class PaintAppGUI {
//See Line 197 for Move,resize,Change color, Delete
	private JFrame frmWorldOfColors;
	Point p1 = new Point();
	Point p2 = new Point();
	Color BrushColor = (Color.black);
	Color BrushColorPrev = (Color.black);
	Color FillColor = (Color.white); 
	JComboBox<String> MyShapes = new JComboBox<String>();
	JSlider BrushSize = new JSlider(5,100,30);
	int brush = 30;
	PaintApp drawer = new PaintApp();
	JPanel panel = new JPanel();
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PaintAppGUI window = new PaintAppGUI();
					window.frmWorldOfColors.setVisible(true);
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
		frmWorldOfColors = new JFrame();
		frmWorldOfColors.setForeground(SystemColor.menu);
		frmWorldOfColors.setFont(new Font("Comic Sans MS", Font.BOLD, 23));
		frmWorldOfColors.setTitle("Dream Paint ");
		frmWorldOfColors.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\ramez\\eclipse-workspace\\DrawingApplication\\Images\\paint.png"));
		frmWorldOfColors.getContentPane().setBackground(new Color(0, 0, 128));
		frmWorldOfColors.setResizable(false);
		frmWorldOfColors.setBounds(100, 100, 998, 669);
		frmWorldOfColors.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmWorldOfColors.getContentPane().setLayout(null);
		
		drawer.getSupportedShapes();
		
		panel.addMouseMotionListener(new MouseMotionAdapter() {
			
			@Override
			public void mouseDragged(MouseEvent e) {
				setM();
				int Index = m.get((String)MyShapes.getSelectedItem());
				Point Pnow = new Point() ;
				Pnow.x = e.getX();
			    Pnow.y = e.getY();
			    Graphics canvas = panel.getGraphics();
				if (Index == 7) {
				canvas.setColor(BrushColor);
				canvas.fillOval(e.getX(), e.getY(), brush, brush);
				}
				else if (Index == 0) {
					//this is the empty case used to get order from the cursor
				}
				else {
					
					Shape currentArr [] = drawer.getShapes();
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
					setM();
					Graphics canvas = panel.getGraphics();
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
			
			@Override
			public void mouseClicked(MouseEvent e) {
				setM();
				int Index = m.get((String)MyShapes.getSelectedItem());
				Graphics canvas = panel.getGraphics();
				if (Index == 0) {
					Point pclick = new Point(e.getX(),e.getY());
					Shape s;
					s = drawer.GetSelectedShape(pclick);
					if (s != null) {	
					System.out.println(s.getClass().getName());
					JPopupMenu menu = new JPopupMenu();
					JMenuItem item1 = new JMenuItem("Move");
					JMenuItem item2 = new JMenuItem("Resize");
					JMenuItem item3 = new JMenuItem("Change Color");
					JMenuItem item4 = new JMenuItem("Delete");
					menu.add(item1);
					menu.add(item2);
					menu.add(item3);
					menu.add(item4);
					menu.show(e.getComponent(),e.getX(),e.getY());
					drawer.refresh(canvas);
					item1.addActionListener(new ActionListener(){
						public void actionPerformed(ActionEvent e) {
							//Move 
						}
					});
					item2.addActionListener(new ActionListener(){
						public void actionPerformed(ActionEvent e) {
							//resize
						}
					});
					item3.addActionListener(new ActionListener(){
						public void actionPerformed(ActionEvent e) {
							//Change Color 
						}
					});
					item1.addActionListener(new ActionListener(){
						public void actionPerformed(ActionEvent e) {
							//Delete 
						}
					});
					}
				}
			}
		}
		);
		panel.setBounds(12, 74, 968, 547);
		panel.setBackground(Color.WHITE);
		frmWorldOfColors.getContentPane().add(panel);
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
		frmWorldOfColors.getContentPane().add(BrushSize);
		
		JLabel lblBrushSize = new JLabel("Brush Size");
		lblBrushSize.setBackground(Color.WHITE);
		lblBrushSize.setFont(new Font("Neo Tech Alt Medium", Font.BOLD, 17));
		lblBrushSize.setForeground(new Color(255, 255, 255));
		lblBrushSize.setBounds(220, 13, 156, 16);
		frmWorldOfColors.getContentPane().add(lblBrushSize);
		
		
		MyShapes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (BrushColor == Color.WHITE) {
					BrushColor = BrushColorPrev;
				}
				Graphics canvas = panel.getGraphics();
				drawer.refresh(canvas);
			}
		});
		
		
		MyShapes.setModel(new DefaultComboBoxModel<String>(new String[] {" " ,"Circle", "Triangle", "Rectangle", "Ellipse", "Square", "Line" , "Brush"}));
		MyShapes.setBounds(734, 35, 184, 26);
		frmWorldOfColors.getContentPane().add(MyShapes);
		
		
		JLabel lblShapes = new JLabel("Shapes");
		lblShapes.setForeground(new Color(255, 255, 255));
		lblShapes.setFont(new Font("Neo Tech Alt Medium", Font.BOLD, 18));
		lblShapes.setBounds(734, 5, 87, 24);
		frmWorldOfColors.getContentPane().add(lblShapes);
		
		JButton SaveBtn = new JButton("");
		SaveBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				drawer.save(drawer.ChooseFileToSave());
				
			}
		});
		SaveBtn.setIcon(new ImageIcon(PaintAppGUI.class.getResource("/Images/Save.png")));
		SaveBtn.setBounds(12, 13, 50, 50);
		frmWorldOfColors.getContentPane().add(SaveBtn);
		
		JButton LoadBtn = new JButton("");
		LoadBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				drawer.load(drawer.ChooseFileToLoad());
				Graphics canvas = panel.getGraphics();
				drawer.refresh(canvas);
				
			}
		});
		LoadBtn.setIcon(new ImageIcon(PaintAppGUI.class.getResource("/Images/load.png")));
		LoadBtn.setBounds(74, 13, 50, 50);
		frmWorldOfColors.getContentPane().add(LoadBtn);
		
		JButton UndoBtn = new JButton("");
		UndoBtn.setIcon(new ImageIcon(PaintAppGUI.class.getResource("/Images/undo.png")));
		UndoBtn.setBounds(136, 13, 30, 50);
		frmWorldOfColors.getContentPane().add(UndoBtn);
		
		JButton RedoBtn = new JButton("");
		RedoBtn.setIcon(new ImageIcon(PaintAppGUI.class.getResource("/Images/redo.png")));
		RedoBtn.setBounds(178, 13, 30, 50);
		frmWorldOfColors.getContentPane().add(RedoBtn);
		
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
		frmWorldOfColors.getContentPane().add(BrushBtn);
		BrushBtn.setIcon(new ImageIcon(PaintAppGUI.class.getResource("/Images/brush.png")));
		
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
		frmWorldOfColors.getContentPane().add(EraseBtn);
		EraseBtn.setBackground(SystemColor.menu);
		EraseBtn.setIcon(new ImageIcon(PaintAppGUI.class.getResource("/Images/Eraser.png")));
		
		JButton ColorsBtn = new JButton("");
		ColorsBtn.setBounds(556, 13, 50, 50);
		frmWorldOfColors.getContentPane().add(ColorsBtn);
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
		ColorsBtn.setIcon(new ImageIcon(PaintAppGUI.class.getResource("/Images/color.png")));
		
		JButton FillBtn = new JButton("");
		FillBtn.setBounds(672, 13, 50, 50);
		frmWorldOfColors.getContentPane().add(FillBtn);
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
		FillBtn.setIcon(new ImageIcon(PaintAppGUI.class.getResource("/Images/fill.png")));
		
		textFieldBrush = new JTextField();
		textFieldBrush.setBounds(618, 13, 20, 50);
		frmWorldOfColors.getContentPane().add(textFieldBrush);
		textFieldBrush.setEditable(false);
		textFieldBrush.setBackground(BrushColor);
		textFieldBrush.setColumns(10);
		
		textFieldFill = new JTextField();
		textFieldFill.setBounds(640, 13, 20, 50);
		frmWorldOfColors.getContentPane().add(textFieldFill);
		textFieldFill.setEditable(false);
		textFieldFill.setBackground(FillColor);
		textFieldFill.setColumns(10);
		
		JButton CursorBtn = new JButton("");
		CursorBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MyShapes.setSelectedItem(" ");
			}
		});
		CursorBtn.setIcon(new ImageIcon(PaintAppGUI.class.getResource("/Images/cursor.png")));
		CursorBtn.setBounds(930, 13, 50, 50);
		frmWorldOfColors.getContentPane().add(CursorBtn);
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
