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
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;

public class PaintAppGUI {

	private JFrame frame;

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
	private Color BrushColor = (Color.black);
	private Color FillColor = (Color.black);

	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(0, 0, 128));
		frame.setResizable(false);
		frame.setBounds(100, 100, 851, 650);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.getContentPane().addMouseListener(new MyMouse());
		JPanel panel = new JPanel();
		panel.setBounds(12, 74, 821, 528);
		panel.setBackground(Color.WHITE);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JSlider BrushSize = new JSlider();
		BrushSize.setBounds(220, 34, 200, 26);
		frame.getContentPane().add(BrushSize);
		
		JLabel lblBrushSize = new JLabel("Brush Size");
		lblBrushSize.setFont(new Font("Neo Tech Alt Medium", Font.BOLD, 17));
		lblBrushSize.setForeground(new Color(255, 255, 255));
		lblBrushSize.setBounds(220, 13, 156, 16);
		frame.getContentPane().add(lblBrushSize);
		
		JComboBox MyShapes = new JComboBox();
		MyShapes.setModel(new DefaultComboBoxModel(new String[] {"Free Draw", "Circle", "Triangle", "Rectangle", "Ellipse", "Square", "Line"}));
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
		UndoBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		UndoBtn.setIcon(new ImageIcon("C:\\Users\\ramez\\eclipse-workspace\\DrawingApplication\\Images\\iconfinder_icon-ios7-undo_211838.png"));
		UndoBtn.setBounds(136, 13, 30, 50);
		frame.getContentPane().add(UndoBtn);
		
		JButton RedoBtn = new JButton("");
		RedoBtn.setIcon(new ImageIcon("C:\\Users\\ramez\\eclipse-workspace\\DrawingApplication\\Images\\iconfinder_icon-ios7-redo_211811.png"));
		RedoBtn.setBounds(178, 13, 30, 50);
		frame.getContentPane().add(RedoBtn);
		
		JButton BrushBtn = new JButton("");
		BrushBtn.setBounds(432, 13, 50, 50);
		frame.getContentPane().add(BrushBtn);
		BrushBtn.setIcon(new ImageIcon("C:\\Users\\ramez\\eclipse-workspace\\DrawingApplication\\Images\\iconfinder_paintbrush2_1055017.png"));
		
		JButton EraseBtn = new JButton("");
		EraseBtn.setBounds(494, 13, 50, 50);
		frame.getContentPane().add(EraseBtn);
		EraseBtn.setBackground(SystemColor.menu);
		EraseBtn.setIcon(new ImageIcon("C:\\Users\\ramez\\eclipse-workspace\\DrawingApplication\\Images\\iconfinder_05_Eraser_2064480.png"));
		
		JButton ColorsBtn = new JButton("");
		ColorsBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BrushColor = JColorChooser.showDialog(null, "Pick the brush color", BrushColor);
				if (BrushColor == null ) {
					BrushColor = (Color.black);
				}
				
			}
		});
		ColorsBtn.setBounds(556, 13, 50, 50);
		frame.getContentPane().add(ColorsBtn);
		ColorsBtn.setIcon(new ImageIcon("C:\\Users\\ramez\\eclipse-workspace\\DrawingApplication\\Images\\iconfinder_paintcan_1055016.png"));
		
		JButton FillBtn = new JButton("");
		FillBtn.setBounds(618, 13, 50, 50);
		frame.getContentPane().add(FillBtn);
		FillBtn.setBackground(new Color(210, 105, 30));
		FillBtn.setIcon(new ImageIcon("C:\\Users\\ramez\\eclipse-workspace\\DrawingApplication\\Images\\iconfinder_eyedropper_1055064.png"));
	}
}
