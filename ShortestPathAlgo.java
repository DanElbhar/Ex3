package GIS;
import java.lang.StackOverflowError;

public class ShortestPathAlgo {
/**
 * The class receives GAME (a collection of fruit and pecmans) and calculates the optimal route,
 *  so that all fruits will eat as quickly as possible.
 *  The goal of the algorithm is to minimize the amount of time it takes for all the pecmans 
 *  to eat the fruits.
 * 
 */
	Game g= new Game();
	static double Pac_x=g.getPacmanArray().iterator().next().getX();
	static double Pac_y=g.getPacmanArray().iterator().next().getY();
	static double Fru_x=g.getFruitArray().iterator().next().getX();
	static double Fru_y=g.getFruitArray().iterator().next().getY();
	boolean Pac_hasNext=g.getPacmanArray().iterator().hasNext();
	boolean Fruit_hasNext=g.getFruitArray().iterator().hasNext();
	double Pac_speed=g.getPacmanArray().iterator().next().getSpeed();
	
	public ShortestPathAlgo(Game g)
	{
		if(g.getPacmanArray()==null && g.getFruitArray()==null)
		{
			System.out.println("The game is empty");
		}
		
		if(g.getPacmanArray()!=null && g.getFruitArray()!=null)
		{
			getAIMove();
			getDisttToPacman(Pac_x,Pac_y,Fru_x,Fru_y);
			getPacmanDirection(Pac_x,Pac_y);
			
		}
	}
	protected static Object getAIMove(){
//		 if fruit is within a set Distance from pacman
		int minDistance=0;
		Object curDirection=0;
		Object lastDirection=0;
		int targetY=0;
		int targetX=0;
		int curY=0;
		int curX = 0;
		if(getDisttToPacman(curX, curY, targetX, targetY) < minDistance){
			// it tries to go the same direction as pacman
			try{
				curDirection = getPacmanDirection(Pac_x, Pac_y);
			}
			// just incase something goes wrong, it sets the direction as the last direction
			catch(NullPointerException NPE){
				curDirection = lastDirection;
			}
			// and if it can't go that direction, it'll just move according to the standard
			// ai and try to eat pacman
			if(!this.moveIsAllowed(curDirection)){
				tryMove(curX, curY, targetX, targetY);
			}
		}

		lastDirection = curDirection;
		return  curDirection;

	}

	private void tryMove(int i, int j, int k, int l) {
		// TODO Auto-generated method stub

	}

	private boolean moveIsAllowed(Object curDirection) {
		// TODO Auto-generated method stub
		return false;
	}
	/**
	 * The function calculates distance to find fruit in the minimum time.
	 * @param d
	 * @param e
	 * @param f
	 * @param g
	 * @return distance
	 */
	private static double getDisttToPacman(double d, double e, double f, double g){
		double Dist = 0;
		Dist = Math.sqrt(Math.pow((d - f), 2) + Math.pow((e - g), 2));
		return  Dist;
	}

	/**
	 * This function calculates where the pacman should move
	 * @param PacX
	 * @param PacY
	 * @return the location of the pecman
	 */
	private static double [] getPacmanDirection(double PacX, double PacY){
		
		double locationPac_x=Pac_x;
		double locationPac_y=Pac_y;
		double [] loc_x_y=null;
		
		//If the location of the pacman in the X axis is equal to the fruit position on the X axis 
		//and the position of the pacman in the Y axis is different from the fruit position on the Y axis
		if(Pac_x == Fru_x && Pac_y!= Fru_y){
			if(Pac_y > Fru_y ){
				locationPac_y = locationPac_y-Pac_speed;
			}
			else if(Pac_y < Fru_y ){
				locationPac_y = locationPac_y+Pac_speed;
			}
		}
		
		//If the location of the pacman in the Y axis is equal to the fruit position on the Y axis 
		//and the position of the pacman in the X axis is different from the fruit position on the X axis
		if(Pac_x != Fru_x && Pac_y== Fru_y) {
			if(Pac_x > Fru_x ){
				locationPac_x = locationPac_x-Pac_speed;
			}
			else if(Pac_x < Fru_x ){
				locationPac_x = locationPac_x+Pac_speed;
			}
		}
		
		//If the location of the pacman in the X axis is changed to the position of the fruit in the X axis 
		//and the position of the pacman in the Y axis is different from the fruit position on the Y axis
		if(Pac_x != Fru_x && Pac_y!= Fru_y) {
			double temp=locationPac_x;
			double ans=angleBetPacFruit(locationPac_x, locationPac_y,Fru_x, Fru_y );
			locationPac_x = locationPac_x+locationPac_x/Math.cos(ans);
		}
		
		loc_x_y[0]= locationPac_x;
		loc_x_y[1]= locationPac_y;
		return loc_x_y;
	}
	
	/**
	 * If the pecman distance to the fruit is smaller than its feeding distance (its radius) 
	 * then it will eat the fruit 
	 * and if it is farther away then it will promote the pecman
	 * @param g
	 */
	private void eat(Game g)
	{
		if(Pac_hasNext && Fruit_hasNext)
		{
			if(g.getPacmanArray().iterator().next().getEatR()<=getDisttToPacman(Pac_x,Pac_y,Fru_x,Fru_x))
					{
				g.getFruitArray().iterator().remove();
					}
			else
			{
				getPacmanDirection(Pac_x,Pac_y);
				//eat(g);
			}
		}
	}
	
	/**
	 * This function calculates the angle between the pecman and the fruit
	 * @param PacmanX
	 * @param PacmanY
	 * @param FruitX
	 * @param FruitY
	 * @return the angle between the pecman and the fruit
	 */
	public static double  angleBetPacFruit (double PacmanX, double PacmanY, double FruitX, double FruitY) {
		float R=6371000; //Radios of earth in meter
		double Lon_Norm=Math.cos(Math.toRadians(PacmanX));
		double disLat=(FruitX*Math.PI/180)-(PacmanX*Math.PI/180);
		double disLon=(FruitY*Math.PI/180)-(PacmanY*Math.PI/180);
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
		//System.out.println("The angel between the PAC and FRUIT: "+azimuth);
		return azimuth;
	}
	
	public static void main(String[] args) {
		try{
            main(null);
        }
        catch(StackOverflowError e){
            System.err.println("ouch!");
        }
		
		getAIMove();
		getDisttToPacman(Pac_x,Pac_y,Fru_x,Fru_y);
		getPacmanDirection(Pac_x,Pac_y);
	}
}