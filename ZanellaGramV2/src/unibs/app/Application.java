package unibs.app;

import java.util.Date;
import java.util.Vector;

//ciao
public class Application {
	private static final int TITOLO=0;
	private static final int NUMERO_PARTECIPANTI=1;
	private static final int TERMINE_ISCRIZIONI=2;
	private static final int LUOGO=3;
	private static final int DATA=4;
	private static final int ORA=5;
	private static final int DURATA=6;
	private static final int QUOTA=7;
	private static final int COMPRESO_IN_QUOTA=8;
	private static final int DATA_CONCLUSIVA=9;
	private static final int ORA_CONCLUSIVA=10;
	private static final int NOTE=11;
	
	private static final int GENERE=12;
	private static final int FASCIA_DI_ETA=13;
	
	private static MenuTest mainMenu;
	private String[] categorie = {"Partite di calcio"};

	private SpazioPersonale mioProfilo = new SpazioPersonale();
	
	private Vector<PartitaDiCalcio> listaPartite = new Vector<PartitaDiCalcio>();

	public Application() {
		initObjects();
	}

	private void initObjects() {
		Campo[] campi = new Campo[14];
		
		String titoloMain = "HOME";
		String[] vociMain = {"Esci e salva","Vedi eventi", "Crea evento", "Vedi profilo"};
		
		mainMenu = new MenuTest(titoloMain,vociMain,"Seleziona una voce",3);
		
	}
	
	public void runApplication() {
		boolean fine=false;
		while(!fine)
		{	
			int i = mainMenu.scegli();
			switch(i) {
				case 0: //esci();
					fine=true;
					break;
				case 1:vediEventi();
					break;
				case 2:creaEvento();
					break;
				case 3: visualizzaSpazioPersonale();
					break;
				default: System.out.println("Scelta non valida!");
					break;
				
			}
		}
	}


	private void creaEvento() {
		// TODO Auto-generated method stub
		
	}
	
	
	public void vediEventi()
	{
		MenuTest menuCat = new MenuTest("Categorie",categorie,"Seleziona categoria (0 per tornare alla home)",1);
		int scelta= menuCat.scegli();
		switch(scelta)
		{
			case 1: vediPartite();
				break;
			case 0: return;
		}
	}
	
	public void vediPartite()
	{
		for(int i=0; i<listaPartite.size(); i++) System.out.println(i+1 + ") " +listaPartite.get(i).getDescrizioneCampi());
		int a = MenuTest.sceltaDaLista("Seleziona partita a cui vuoi aderire (0 per uscire):", listaPartite.size());
		
			if(a==0) return;
			else{
				partecipaEvento(listaPartite.get(a));
			}	
	}
	
	
	
	private void visualizzaSpazioPersonale() {
		int a;
		if(mioProfilo.noNotifiche()) {
			System.out.println("NON hai notifiche da visualizzare");
		}else {
			mioProfilo.stampaNotifiche();
			do {
				a = MenuTest.sceltaDaLista("Seleziona notifica che vuoi eliminare (0 per uscire):", mioProfilo.getNumeroNotifiche());
				if(a==0) return;
				else{
					mioProfilo.deleteNotifica(a-1); 
				}
			}while(a!=0);
		}
	}
	
	private void partecipaEvento(Categoria evento) {
		evento.aggiungiPartecipante();
		mioProfilo.addEvento(evento);
	}
	
	
	
	public void assegnaEvento(Campo[] campi) 
	{
		campi[TITOLO]= new Campo<String>("Titolo","Titolo dell'evento",false);
		campi[NUMERO_PARTECIPANTI]=new Campo<Integer>("Numero partecipanti","Indica il numero massimo di partecipanti",true);
		campi[TERMINE_ISCRIZIONI]=new Campo<Date>("Data termine iscrizione","Indica la data limite entro cui iscriversi",true);
		campi[LUOGO]=new Campo<String>("Luogo","Indica il luogo dell'evento",true);
		campi[DATA]=new Campo<Date>("Data","Indica la data di svolgimento dell'evento",true);
		campi[ORA]=new Campo<Date>("Ora","Indica l'ora di inizio dell'evento",true);
		campi[DURATA]=new Campo<Date>("Durata","Indica la durata dell'evento",false);
		campi[QUOTA]=new Campo<Integer>("Numero partecipanti","Indica la spesa da sostenere per partecipare all'evento",true);
		campi[COMPRESO_IN_QUOTA]=new Campo<String>("Compreso in quota","Indica le voci di spesa comprese nella quota",false);
		campi[DATA_CONCLUSIVA]=new Campo<Date>("Data conclusiva","Indica la data di conclusione dell'evento",false);
		campi[ORA_CONCLUSIVA]=new Campo<Date>("Ora conclusiva","Indica l'ora conclusiva dell'evento",false);
		campi[NOTE]=new Campo<String>("Note","Informazioni aggiuntive",false);		
	}
	
	public void assegnaPartitaDiCalcio(Campo[] campi) {
		
		assegnaEvento(campi);
		campi[GENERE]=new Campo<String>("Genere","Indica il genere dei giocatori",true);
		campi[FASCIA_DI_ETA]=new Campo<FasciaDiEta>("Fascia di età","Indica la fascia di età dei giocatori",true);
		
	}
		
		
}
