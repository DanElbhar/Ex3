package GIS;

import Coords.MyCoords;
import GUI.My_GUI;
import Geom.Point3D;

public class Map {
	
//	private JPanel _panel;
	
//	public Map(){
//		Point3D p2 = null;
//		Point3D p1 = null;
//		map(p1, p2);
//	}
	public static void  map (Point3D p1, Point3D p2) {

	My_GUI frame= new My_GUI();
	frame.setBounds(0, 0, frame.myImage.getWidth(), frame.myImage.getHeight());
	frame.createGui();
	frame.setVisible(true);
	MyCoords coo= new MyCoords();
//	Point3D p=new Point3D();
	coo.azimuth_elevation_dist(p1, p2);
		
}
	
	public static void main(String[] args) {
		
		Point3D gps0=new Point3D(32.103315,35.209039,670.0);
		Point3D gps1=new Point3D(32.106352 ,35.205808194261195 ,650.0);
		map(gps0, gps1);
		System.out.println();
		
	}
}
