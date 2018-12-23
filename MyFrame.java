
package GUI;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import GIS.Fruit;
import GIS.Game;
import GIS.MapScale;
import GIS.Pacman;

/**
 *
 * 
 */
public class MyFrame extends JFrame implements MouseListener {
	
	private List<Pacman> pacmanArray;
	private List<Fruit> fruitArray;
	
	private static final long serialVersionUID = 1L;
	// private variables
	private Container window;
	private JPanel _panel;
	private static Graphics _paper;
	private int x, y;
	private boolean isGamer;
	public BufferedImage myImage;
	static Game g=new Game();

	public MyFrame(){
		super("Map Demo"); //setTitle("Map Counter");  // "super" Frame sets its title
		//	Moves and resizes this component. 
		//	The new location of the top-left corner is  specified by x and y, 
		//	and the new size is specified by width and height
		//	setBounds(x,y,width,height)
		//this.setBounds(0,0,1433,700); //setSize(1433,700);       
		// "super" Frame sets its initial window size
		//      Exit the program when the close-window button clicked
		try {
			 myImage = ImageIO.read(new File("Ariel1.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}	
		//System.out.println("The size of the image: ("+myImage.getWidth()+","+myImage.getHeight()+")");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//pack();	

	}
	
	public MyFrame(String csv_file_name) {
		this.pacmanArray = new ArrayList<Pacman>();
		this.fruitArray = new ArrayList<Fruit>();
		new Game(this.fruitArray, this.pacmanArray );
		String line = "";
		String cvsSplitBy = ",";
		try (BufferedReader br = new BufferedReader(new FileReader(new File(csv_file_name)))) 
		{
			int counter = 0;
			br.readLine();
			
			while ((line = br.readLine()) != null) //if the third line in the read file is not empty, read from it
			{
				String[] userInfo = line.split(cvsSplitBy); //userInfo is an array of all the information in a row
				if(counter > 0) {
					if(userInfo[0].contains("P")) {
						Pacman pacman = new Pacman(Double.parseDouble(userInfo[3]),Double.parseDouble(userInfo[2]),Double.parseDouble(userInfo[4]) , Double.parseDouble(userInfo[5]), Double.parseDouble(userInfo[6]),0);
						pacmanArray.add(pacman);
					}
					else {
						Fruit fruit = new Fruit(Double.parseDouble(userInfo[3]),Double.parseDouble(userInfo[2]),Double.parseDouble(userInfo[4]));
						fruitArray.add(fruit);
					}
				}
				counter++;
			}
		}

catch (IOException e) 
		{
			e.printStackTrace();
		}
	}

//	private void g( List<Fruit> fruitArray2, List<Pacman> pacmanArray2) {
//		
//		g(this.fruitArray,this.pacmanArray);
//		
//	}

	public void createGui(){              				
		//	A Container is a component  that can contain other GUI components
		
		window = this.getContentPane(); 
		window.setLayout(new FlowLayout());

		//	Add "panel" to be used for drawing            
		_panel = new JPanel();
		Dimension d= new Dimension(myImage.getWidth(),myImage.getHeight());
		_panel.setPreferredSize(d);		               
		window.add(_panel);

		// A menu-bar contains menus. A menu contains menu-items (or sub-Menu)
		JMenuBar menuBar;   // the menu-bar
		JMenu menu;        // each menu in the menu-bar
		JMenuItem menuItem1, menuItem2, Save; // an item in a menu

		menuBar = new JMenuBar();

		// First Menu
		menu = new JMenu("Menu A");
		menu.setMnemonic(KeyEvent.VK_A);  // alt short-cut key
		menuBar.add(menu);  // the menu-bar adds this menu

		menuItem1 = new JMenuItem("Packman", KeyEvent.VK_P);
		menu.add(menuItem1); // the menu adds this item
		menuItem1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				isGamer = true;
			}
		});
		menuItem2 = new JMenuItem("Fruit", KeyEvent.VK_F);
		menu.add(menuItem2); // the menu adds this item
		menuItem2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				isGamer = false;
			}
		}); 
		
//		// Second Menu
//				menu = new JMenu("Menu B");
//				menu.setMnemonic(KeyEvent.VK_B);  // alt short-cut key
//				menuBar.add(menu);  // the menu-bar adds this menu
//
//				Save = new JMenuItem("Save", KeyEvent.VK_S);
//				menu.add(Save); // the menu adds this item
//				Save.addActionListener(new ActionListener() {
//					@Override
//					public void actionPerformed(ActionEvent e) {
//						isGamer = true;
//					}
//				});
//				
		setJMenuBar(menuBar);  // "this" JFrame sets its menu-bar

		// Prepare an ImageIcon
		String imgMapFilename = "Ariel1.png";    
		ImageIcon imgBck = new ImageIcon(getClass().getResource(imgMapFilename));
		JLabel labelMap = new JLabel();
		labelMap.setIcon(imgBck);
		_panel.add(labelMap);

		// panel (source) fires the MouseEvent.
		//panel adds "this" object as a MouseEvent listener.
		_panel.addMouseListener(this);
	}

	protected void paintElement() {
		//	The method getGraphics is called to obtain a Graphics object
		_paper = _panel.getGraphics();
		if(isGamer){
			_paper.setColor(Color.YELLOW);
			_paper.fillOval(x,y,20,20);
		} else {
			_paper.setColor(Color.RED);
			_paper.fillOval(x,y,10,10);
		}
		_paper.setFont(new Font("Monospaced", Font.PLAIN, 14));               
		_paper.drawString("("+Integer.toString(x)+", "+Integer.toString(y)+")",x,y-10);
	}

	//	public void mouseClicked(MouseEvent event){
	@Override
	public void mousePressed(MouseEvent event) {
		x = event.getX();
		y = event.getY();  
		System.out.println("mouse Clicked");
		System.out.println("("+ event.getX() + "," + event.getY() +")");
		paintElement();
	}
	// Not Used, but need to provide an empty body for compilation
	public void mouseReleased(MouseEvent event){}
	public void mouseClicked(MouseEvent event){}
	public void mouseExited(MouseEvent event){}
	public void mouseEntered(MouseEvent event){}
	
	public static void main(String[] args) {
		 try {
	       
		 
		MyFrame frame = new MyFrame("game_1543684662657.csv");
		MapScale scale= new MapScale("Ariel1.png");
//		new MyFrame("p1_Ariel.csv");
		frame.setBounds(0, 0, 1433, 642);
		frame.createGui();
		frame.setVisible(true);
//		scale.invalidate();
//		scale.getPreferredSize();
//		scale.paintComponent(_paper);
		//Map.mapFrame();
		//ShortestPathAlgo(g);
		 }
		catch (NullPointerException  e) {
            System.err.println("NullPointerException caught");
        }
	
	}


}
