package GIS;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class Path2KML {
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


	public static void main(String[] args) throws IOException{
	
		String csvFile1 ="game_1543685769754.csv";//Read from this file
		String csvFile2 = "game_1543684662657.csv"; 
		String csvFile3 ="game_1543693822377.csv";
		String csvFile4 =" game_1543693911932.csv";
		String csvFile5 =" game_1543693911932_a.csv";
		String csvFile6 =" game_1543693911932_b.csv";
		String line = "";
		String cvsSplitBy = ",";
		System.out.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"+ 
				"<kml xmlns=\"http://www.opengis.net/kml/2.2\">\n");

		//try and catch for the reading part
	
		try (BufferedReader br = new BufferedReader(new FileReader(csvFile1))) 
		
		{
			br.readLine();
			br.readLine();

			while ((line = br.readLine()) != null) //if the third line in the read file is not empty, read from it
			{
				String[] userInfo = line.split(cvsSplitBy); //userInfo is an array of all the information in a row
				int counter;
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
				String[] userInfo;
				System.out.println("MAC: " + userInfo[0] + " , SSID: " + userInfo[1] +
						" ,AuthMode: " + userInfo[2] + " ,FirstSeen: " + " ,Channel: "+ userInfo[4]+
						" ,RSSI: "+userInfo[5]+" ,CurrentLatitude: "+userInfo[6]+" ,CurrentLongitude: "+userInfo[7]+
						" ,AltitudeMeters: "+userInfo[8]+" ,AccuracyMeters: "+userInfo[9]+" ,Type: "+userInfo[10]);
			}

		} 
	catch (IOException e){
		
			e.printStackTrace();
		}

		String fileName = "game_1543685769754.csv"; //write to this file
		String fileName1= " game_1543684662657.csv";
		String fileName2="game_1543693822377.csv ";
		String fileName3="game_1543693911932.csv";
		String fileName4="game_1543693911932_a.csv";
		String fileName5="game_1543693911932_b.csv";
		String kmlstart="<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"+ 
				"<kml xmlns=\"http://www.opengis.net/kml/2.2\">\n";
		PrintWriter pw =null;
		File kmlFile=new File(fileName);

		String kmlend="</kml>";
		String kmldoc="<Document>";
		String sb1 = new String();

		//try and catch for the writing part
		try{ 
		
			pw = new PrintWriter(kmlFile);
			pw.write(kmlstart.toString());
			pw.write(kmldoc.toString());

			@SuppressWarnings("resource")
			BufferedReader br = new BufferedReader(new FileReader(csvFile));
			br.readLine();
			br.readLine();
			while ((line = br.readLine()) != null) {

				String[] userInfo = line.split(cvsSplitBy);
				int counter;
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
				sb1="<Placemark>"+"<name>"+ userInfo[1] +"</name>"+"<description>" +" FirstSeen: "+userInfo[3] +"<br />"+
						" ,Channel: " + userInfo[4] + 
						" ,Type: "+userInfo[10]+"</description> "+"<Point>"+"<coordinates>"+userInfo[7]+" "+userInfo[6]+" "+userInfo[8]+
						"</coordinates>"+"</Point>"+"</Placemark>";


				pw.write(sb1.toString());
				pw.write("\n");

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

