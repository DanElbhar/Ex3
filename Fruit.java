package GIS;

public class Fruit{
	/**
	 * A class that represents a "target" in a known geographic location (without movement)
	 * @return getX, getY, getZ
	 */
	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getZ() {
		return z;
	}

	public void setZ(double z) {
		this.z = z;
	}

	private double x;
	private double y;
	private double z;
	
	public Fruit() {
		this.x = 0;
		this.y = 0;
		this.z = 0;
	}
	
	public Fruit(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
		}
	
	public Fruit(Fruit f) {
		this.x = f.x;
		this.y = f.y;
		this.z = f.z;
	}

}
