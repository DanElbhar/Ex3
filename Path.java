package GIS;

import java.util.ArrayList;
import Geom.Point3D;

public class Path {
	ArrayList<Fruit> thepath;
	Map the_map=new Map();
	public double time_path;
	ArrayList<Pacman> test_t_path;
	ArrayList<Point3D> PathOfPoints=new ArrayList<>();
	Point3D p=new Point3D();
	double x,y;
	Point3D p1=new Point3D( x,  y);
	Point3D f1=new Point3D( x,  y);

	double dis=0;
	private double timeFor2;
	Pacman pac= new Pacman();
	Fruit fru = new Fruit();
	public Path() {
		this.PathOfPoints=new ArrayList<>();
	}

	/**
	 * @return the time_path
	 */

	public ArrayList<Point3D> getPosInTime(Point3D p1)
	{
		this.PathOfPoints.add(p1);
		System.out.println(this.PathOfPoints.toString());
		return this.PathOfPoints;	
	}

	public double lentghOfPath(Point3D p1)
	{
		dis=dis+p.distance3D(p1);
		return this.dis;

	}
	public double CalPathTime(Pacman packman) {
		double cal = 0;
		double totalTime = 0;
		Pacman t = new Pacman(packman);
		Point3D p=new Point3D();
		Point3D f=new Point3D();

		for (int i = 0; i < ((ArrayList<Fruit>) ( packman.getpath())).size(); i++) {
			cal =Time2Points(p1(test_t_path.get(i).getX(),test_t_path.get(i).getY()),
					(f1 (thepath.get(i).getX(),thepath.get(i).getY())));
			totalTime +=cal;
			//packman.setPackLocation(((ArrayList<Fruit>) packman.getpath().getCpath()).get(i).getfruit());
		}
		((Path) packman.getpath()).setTime_path(totalTime);
		packman.getX();
		packman.getY();


		return totalTime;
	}
	private Point3D f1(double x2, double y2) {
		return this.f1(x2,y2);	
	}

	private Point3D p1(double x2, double y2) {
		return this.p1(x2,y2);		

	}

	public ArrayList<Fruit> getCpath() {
		return this.thepath;
	}

	public void setTime_path(double timeFor2) {
		this.timeFor2=timeFor2;
	}


	public double Time2Points(Point3D p , Point3D f) 
	{
		the_map.disBetPoints(p1(pac.getX(),pac.getY()), f1(fru.getX(),fru.getY()));
		if (Double.parseDouble(the_map.toString()) < pac.getSpeed()) {
			return 0;
		}
		else {	
			the_map.disBetPoints(p, f);
			return (Double.parseDouble(the_map.toString())-pac.getSpeed())/pac.getSpeed();

		}
	}

	public void setpath1(ArrayList<Fruit> cpath) {
		// TODO Auto-generated method stub
		this.thepath = cpath;
	}
}


//package GIS;
//
//import java.util.ArrayList;
//
//import Geom.Point3D;
//
//public class Path {
//	
//	ArrayList<Point3D> PathOfPoints=new ArrayList<>();
//	Point3D p=new Point3D();
//	double dis=0;
//	
//	public Path() {
//		this.PathOfPoints=new ArrayList<>();
//	}
//	
//	public ArrayList<Point3D> getPosInTime(Point3D p1)
//	{
//		this.PathOfPoints.add(p1);
//		System.out.println(this.PathOfPoints.toString());
//		return this.PathOfPoints;	
//	}
//	
//	public double lentghOfPath(Point3D p1)
//	{
//		dis=dis+p.distance3D(p1);
//		return this.dis;
//
//	}
//
//}
