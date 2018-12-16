package GIS;

import java.awt.BorderLayout;
import java.awt.Image;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import Coords.MyCoords;
import Geom.Point3D;

public class Map {

	static MyCoords coo= new MyCoords();

	public static void mapFrame() {
		try {
			Image img = null;
			img = ImageIO.read(new File("Ariel1.png"));
			JFrame frame = new JFrame();
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setLayout(new BorderLayout());
			frame.add(new MapScale(img));
			frame.pack();
			frame.setVisible(true);
		} catch (Exception exp) {
			exp.printStackTrace();
		}
	}

	public static void  angleBetPoints (Point3D p1, Point3D p2) {
		float R=6371000; //Radios of earth in meter
		double Lon_Norm=Math.cos(Math.toRadians(p1.x()));
		double disLat=(p2.x()*Math.PI/180)-(p1.x()*Math.PI/180);
		double disLon=(p2.y()*Math.PI/180)-(p1.y()*Math.PI/180);
		//	System.out.println("The disLat is: "+disLat);
		//	System.out.println("The disLon is: "+disLon);
		double disLat_in_meter=Math.sin(disLat)*R;
		double disLon_in_meter=Math.sin(disLon)*R*Lon_Norm;
		double azimuth=0;
		azimuth=Math.atan(disLon_in_meter/disLat_in_meter);
		azimuth=Math.toDegrees(azimuth);
		if(disLat_in_meter<0) azimuth=180-azimuth;
		if(disLat_in_meter<0 && disLon_in_meter<0) azimuth=180+azimuth;
		if(disLon_in_meter<0) azimuth=360+azimuth;
		System.out.println("The angel between the two points is: "+azimuth);
	}

	public static void  disBetPoints (Point3D p1, Point3D p2) {
		double dxy=coo.distance3d(p1,p2)*coo.distance3d(p1,p2);
		double dz=p2.z()-p1.z();
		double dist=Math.sqrt(dxy*dxy+dz*dz);
		System.out.println("The distance in meterã bwtween the two points is: "+dist);
	}


	public static void main(String[] args) {
		Point3D p1=new Point3D(32.103315,35.209039,670.0);
		Point3D p2=new Point3D(32.106352 ,35.205225 ,650.0);
		Map.mapFrame();
		Map.angleBetPoints(p1, p2);
		Map.disBetPoints(p1, p2);

	}

}
