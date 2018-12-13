package GIS;

import GUI.My_GUI;

public class ConGPSPIX {
	/**
	 * based on: https://stackoverflow.com/questions/14329691/convert-latitude-longitude-point-to-a-pixels-x-y-on-mercator-projection?rq=1
	 * 
	 */
	

	public static void getXYfromLatLon(double latitude, double longitude) {
		My_GUI frame = new My_GUI();
		// get x value
		double mapWidth = frame.myImage.getWidth(), mapHeight = frame.myImage.getHeight();
		double pX = ((longitude+180.0)*(mapWidth/360.0));

		// convert from degrees to radians
		double latRad = latitude*Math.PI/180.0;

		// get y value
		double mercN = Math.log(Math.tan((Math.PI/4.0)+(latRad/2.0)));
		double pY = (mapHeight/2.0)-(mapWidth*mercN/(2.0*Math.PI));
		System.out.println("x = "+pX+", y = "+pY);
	}

	public static void main(String[] args) {
		/////////
		double longitude    = 32.1042768; 
		double latitude   = 35.21035679;   
		getXYfromLatLon(latitude, longitude);
		
		latitude    = 32.105; 
		longitude   = 35.2096;   
		getXYfromLatLon(latitude, longitude);
		
	}

}
