package GIS;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import GIS.Fruit;
import GIS.Game;
import GIS.Pacman;

public class Path2KML {
	private static List<Pacman> pacmanArray;
	private static List<Fruit> fruitArray;

	//	<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
	//	<kml xmlns="http://www.opengis.net/kml/2.2" >
	//	<Placemark>
	//	<name>Java User Group Hessen - JUGH!</name>
	//	<visibility>true</visibility>
	//	<open>false</open>
	//	<description>die Java User Group Hessen [...]</description>
	//	<styleUrl>styles.kml#jugh_style</styleUrl>
	//	<Point>
	//	<extrude>false</extrude>
	//	<altitudeMode>clampToGround</altitudeMode>
	//	<coordinates>9.444652669565212,51.30473589438118</coordinates>
	//	</Point>
	//	</Placemark>
	//	</kml>


	//	@SuppressWarnings("null")
	public static void main(String[] args) throws IOException 
	{
		pacmanArray = new ArrayList<Pacman>();
		fruitArray = new ArrayList<Fruit>();
		new Game(fruitArray, pacmanArray );

		String csvFile1 ="game_1543684662657.csv";//Read from this file
		//		String csvFile2 = "game_1543684662657.csv"; 
		//		String csvFile3 ="game_1543693822377.csv";
		//		String csvFile4 =" game_1543693911932.csv";
		//		String csvFile5 =" game_1543693911932_a.csv";
		//		String csvFile6 =" game_1543693911932_b.csv";
		String line = "";
		String cvsSplitBy = ",";

		System.out.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"+ 
				"<kml xmlns=\"http://www.opengis.net/kml/2.2\">\n");

		//try and catch for the reading part

		try (BufferedReader br = new BufferedReader(new FileReader(csvFile1))) 

		{

			br.readLine();

			while ((line = br.readLine()) != null) //if the third line in the read file is not empty, read from it
			{
				String[] userInfo = line.split(cvsSplitBy); //userInfo is an array of all the information in a row
				System.out.println("Type: " + userInfo[0] + " , id: " + userInfo[1] +
						" ,Lat: " + userInfo[2] + " ,Lon: "+ userInfo[3]+
						" ,Alt: "+userInfo[4]+" ,Speed: "+userInfo[5]);
				int counter = 0;
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

		catch (IOException e){

			e.printStackTrace();
		}

		String fileName1 = "game_1543685769754.kml"; //write to this file
		//		String fileName2= " game_1543684662657.kml";
		//		String fileName3="game_1543693822377.kml ";
		//		String fileName4="game_1543693911932.kml";
		//		String fileName5="game_1543693911932_a.kml";
		//		String fileName6="game_1543693911932_b.kml";
		String kmlstart="<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"+ 
				"<kml xmlns=\"http://www.opengis.net/kml/2.2\">\n";
		PrintWriter pw =null;
		File kmlFile=new File(fileName1);

		String kmlend="</kml>";
		String kmldoc="<Document>";
		String sb1 = new String();

		//try and catch for the writing part
		try { 

			pw = new PrintWriter(kmlFile);
			pw.write(kmlstart.toString());
			pw.write(kmldoc.toString());

			@SuppressWarnings("resource")
			BufferedReader br = new BufferedReader(new FileReader(csvFile1));

			br.readLine();
			while ((line = br.readLine()) != null) {

				String[] userInfo = line.split(cvsSplitBy);
				sb1="<Placemark>"+"<name>"+ userInfo[1] +"</name>"+"<description>" +  
						" ,Type: "+userInfo[0]+"</description> "+"<Point>"+"<coordinates>"+userInfo[3]+" "+userInfo[2]+" "+userInfo[4]+
						"</coordinates>"+"</Point>"+"</Placemark>";
				pw.write(sb1.toString());
				pw.write("\n");
				int counter = 0;
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


			pw.write("</Document>");
			pw.write("\n");

			pw.write(kmlend.toString());
			pw.close();
			System.out.println("done!");
		}
		catch (FileNotFoundException e) {

			e.printStackTrace();
			return;
		}
	}	
}

