package unibs.app;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Utility {
		
	public static int scegli(String titoloMain, String[] vociMain, String query, int dim)
	{
		System.out.println("________________________________________________");
		System.out.println(titoloMain);
		System.out.println("________________________________________________");
		for(int i=0;i<dim;i++) {
			System.out.println(i+") "+vociMain[i]);
		}
		int a = leggiIntero(query);
		return a;
	}
	
	public static int leggiIntero(String query) 
	{
		int scelta=0;
		try {
			System.out.println(query + " -->");
			Scanner in= new Scanner(System.in);
			String line=in.nextLine();
			scelta= Integer.parseInt(line);
			
		}catch(InputMismatchException e)
		{
			System.out.println("Errore di inserimento!");
		}
		return scelta;
	}
	
	public static String leggiStringa() 
	{
		String lettura = null;
		try {
			
			Scanner in= new Scanner(System.in);
			lettura=in.nextLine();
		}catch(InputMismatchException e)
		{
			System.out.println("Errore di inserimento!");
		}
		return lettura;
	}
	
	public static int sceltaDaLista(String messaggio, int dim) {
		boolean fine = false;
		int a;
		do {
			System.out.println(messaggio);
			a = leggiIntero(messaggio);
			if(a>dim){
				System.out.println("Inserimento non valido!");
			}else{
				fine=true;
			}
		}while(!fine);
		return a;
	}
	

}