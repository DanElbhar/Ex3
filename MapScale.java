package GIS;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;


	public class MapScale extends JPanel{
		
	  private static final long serialVersionUID = 1L;
	  
	  private Image myImage;
	  private Image scaled;

	  public MapScale(String myImage) {
	    this(new ImageIcon(myImage).getImage());
	  }

	  public MapScale(Image myImage) {
	    this.myImage = myImage;
	  }

	  @Override
	  public void invalidate() {
	    super.invalidate();
	    int width = getWidth();
	    int height = getHeight();

	    if (width > 0 && height > 0) {
	      scaled = myImage.getScaledInstance(getWidth(), getHeight(),Image.SCALE_SMOOTH);
	    }
	  }

	  @Override
	  public Dimension getPreferredSize() {
	    return myImage == null ? new Dimension(200, 200) : new Dimension(
	    		myImage.getWidth(this), myImage.getHeight(this));
	  }

	  @Override
	  public void paintComponent(Graphics g) {
	    super.paintComponent(g);
	    g.drawImage(scaled, 0, 0, null);
	  }
	  
	}

