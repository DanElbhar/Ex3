package GIS;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Game {
/**
 * A class that includes a collection of fruit and a collection of pacmans.
 * The class has the ability to build from the csv file and save its information to such a file
 * @return addPacman, getPacmanArray, getFruitArray
 */
	private List<Pacman> pacmanArray;
	private List<Fruit> fruitArray;

	public Game () {
		this.pacmanArray = new ArrayList<Pacman>();
		this.fruitArray = new ArrayList<Fruit>();
	}

	public Game (List<Fruit> fArray, List<Pacman> pArray) {
		this.fruitArray = new ArrayList<Fruit>(fArray);
		this.pacmanArray = new ArrayList<Pacman>(pArray);
	}	

	public boolean addPacman(Pacman pacman) {
		this.pacmanArray.add(pacman);
		return true;
	}

	public boolean addPacman(Fruit pacman) {
		this.fruitArray.add(pacman);
		return true;
	}


	public Game(String csv_file_name) {
		this.pacmanArray = new ArrayList<Pacman>();
		this.fruitArray = new ArrayList<Fruit>();
		String line = "";
		String cvsSplitBy = ",";
		try (BufferedReader br = new BufferedReader(new FileReader(new File(csv_file_name)))) {
			int counter = 0;
			while ((line = br.readLine()) != null) {
				String[] userInfo = line.split(cvsSplitBy);
				if(counter > 0) {
					if(userInfo[0].contains("P")) {
						Pacman pacman = new Pacman(Double.parseDouble(userInfo[3]),Double.parseDouble(userInfo[2]),Integer.parseInt(userInfo[4]) , Double.parseDouble(userInfo[5]), Double.parseDouble(userInfo[6]),0);
						pacmanArray.add(pacman);
					}
					else {
						Fruit fruit = new Fruit(Double.parseDouble(userInfo[3]),Double.parseDouble(userInfo[2]),Integer.parseInt(userInfo[4]));
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

	public List<Pacman> getPacmanArray() {
		return pacmanArray;
	}

	public void setPacmanArray(List<Pacman> pacmanArray) {
		this.pacmanArray = pacmanArray;
	}

	public List<Fruit> getFruitArray() {
		return fruitArray;
	}

	public void setFruitArray(List<Fruit> fruitArray) {
		this.fruitArray = fruitArray;
	}

	public static void main(String[] args) {
		String csv_file_name="data1.csv";
		Game g=new Game();

		Pacman P=new Pacman();
		Fruit F=new Fruit();
		g.addPacman(P);
		System.out.println(g.addPacman(P));
		System.out.println(g.getPacmanArray());
		System.out.println(g.getFruitArray());
		g.toString();

	}
}
