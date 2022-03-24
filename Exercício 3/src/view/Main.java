package view;

import java.util.concurrent.Semaphore;

import controller.Formula1;

public class Main {
	
	public static String [] PilotosEquipes = {"Red Bull | Max Verstappen | Melhor volta: ",
			"Red Bull | Sérgio Pérez | Melhor volta: ", "Mercedes | Lewis Hamilton | Melhor volta: ",
			"Mercedes | George Russell | Melhor volta: ", "Ferrari | Charles Leclerc | Melhor volta: ",
			"Ferrari | Carlos Sainz Jr. | Melhor volta: ", "McLaren | Lando Norris | Melhor volta: ", 
			"McLaren | Daniel Ricciardo | Melhor volta: ", "Alpine | Fernando Alonso | Melhor volta: ",
			"Alpine | Esteban Ocon | Melhor volta: ", "AlphaTauri | Pierre Gasly | Melhor volta: ", 
			"AlphaTauri | Yuki Tsunoda | Melhor volta: ", "Aston Martin | Sebastian Vettel | Melhor volta: ", 
			"Aston Martin | Lance Stroll | Melhor volta:  "};
	
	public static int[] voltaTempo = new int [14];
	
	public static void main (String[] args) {
		int largada = 5;
		int CarroEquipe = 1;
		Semaphore mutexlargada = new Semaphore(largada);
		Semaphore mutexequipe = new Semaphore (CarroEquipe);
		
		for (int idEquipe = 1; idEquipe < 8; idEquipe++) {
			Thread Formula1 = new Formula1 (idEquipe, mutexlargada, mutexequipe);
			Formula1.start();
		}
		
	}

}
