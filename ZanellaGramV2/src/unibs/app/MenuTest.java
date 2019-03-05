package unibs.app;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MenuTest {
	
	private static String query;
	private String titolo;
	private String[] voci;
	int dim;
	
	public MenuTest(String titolo, String[]voci, String query, int dim)
	{
		this.query=query;
		this.dim=dim;
		this.titolo=titolo;
		this.voci=voci;
	}
	
	public int scegli()
	{
		System.out.println("________________________________________________");
		System.out.println(titolo);
		System.out.println("________________________________________________");
		for(int i=0;i<dim;i++) {
			System.out.println(i+") "+voci[i]);
		}
		int a = leggi_intero();
		return a;
	}
	
	public static int leggi_intero() 
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
	
	public static int sceltaDaLista(String messaggio, int dim) {
		boolean fine = false;
		int a;
		do {
			System.out.println(messaggio);
			a = leggi_intero();
			if(a>dim){
				System.out.println("Inserimento non valido!");
			}else{
				fine=true;
			}
		}while(!fine);
		return a;
	}
	

}
