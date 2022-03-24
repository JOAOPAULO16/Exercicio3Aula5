package controller;

import java.util.concurrent.Semaphore;

import view.Main;

public class Formula1 extends Thread {
	
	private int idEquipe;
	private Semaphore mutexlargada;
	private Semaphore mutexequipe;
	public static int carrosnopit = 0;
	
	public Formula1 (int id, Semaphore mutexlargada, Semaphore mutexequipe) {
		this.idEquipe = id;
		this.mutexequipe = mutexequipe;
		this.mutexlargada = mutexlargada;
	}
	
	public void run(){
		
		for (int i = 1; i < 3; i++) {
			try {
				mutexlargada.acquire();
				CarroNaPista(i);
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				mutexlargada.release();
				System.out.println("O carro de " + i + " da equipe " + idEquipe +  " entrou nos boxes");
				carrosnopit++;
			}
		}
		if (carrosnopit == 14) {
			ClassificaGrid();
		}
	}
	
	private void CarroNaPista (int carro) {
		
		System.out.println("O Carro de " + carro + " da equipe " + idEquipe + " saiu dos boxes");
		for (int i = 1; i < 4; i++) {
			int voltacompleta = (int)((Math.random()*180)+60);
				try {
					sleep(voltacompleta*30);
				} catch (Exception e) {
					e.printStackTrace();
				}
		
				System.out.println("Equipe: " + idEquipe + " Piloto " + carro + " Volta " + i + " Tempo: " + voltacompleta + " segundos");
		
			try {
				mutexequipe.acquire();
				if (voltacompleta < Main.voltaTempo[(2*idEquipe - carro)] || Main.voltaTempo[(2 * idEquipe - carro)] == 0) {
					Main.voltaTempo[(2 * idEquipe - 2 + carro) - 1] = voltacompleta;
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally{
				mutexequipe.release();
			}
		}
	}
	
	public void ClassificaGrid() {
		int aux;
		String auxiliar;
		for (int i = 0; i < 13; i++) {
			for (int j = i + 1; j < 14; j++) {
				if (Main.voltaTempo[i] > Main.voltaTempo[j]) {
					aux = Main.voltaTempo[i];
					Main.voltaTempo[i] = Main.voltaTempo[j];
					Main.voltaTempo[j] = aux;
					auxiliar = Main.PilotosEquipes[i];
					Main.PilotosEquipes[i] = Main.PilotosEquipes[j];
					Main.PilotosEquipes[j] = auxiliar;
				}
			}
		}
		for (int i = 0; i < 14; i++) {
			System.out.println("Posição " + (i + 1) + ": "+ Main.PilotosEquipes[i] + Main.voltaTempo[i] + " segundos");
		}
	}
}
