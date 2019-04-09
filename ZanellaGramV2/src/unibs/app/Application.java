package unibs.app;

import java.io.*;
import java.util.*;


public class Application implements Serializable {
	
	
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
	
	private String[] categorie = {"Partite di calcio"};
	private Data dataOdierna;
	private Ora oraAttuale;
	private SpazioPersonale mioProfilo;
	private String titoloMain = "HOME";
	private Vector<PartitaDiCalcio> listaPartite;
	private String[] vociMain = {"Esci e salva","Vedi eventi", "Crea evento", "Vedi profilo"};
	private String[] vociSpazioPersonale = {"Esci","Vedi eventi a cui sono iscritto","Vedi notifiche"};
	
	private Campo[] campi;
	
	public Application() throws ClassNotFoundException, IOException {
		initObjects();
	}

	@SuppressWarnings("unchecked")
	private void initObjects() throws ClassNotFoundException, IOException {
		mioProfilo = new SpazioPersonale();
		listaPartite = new Vector<PartitaDiCalcio>();
	}
	
	public void runApplication(Data dataOdierna, Ora oraAttuale) throws IOException {
		this.dataOdierna=dataOdierna;
		this.oraAttuale=oraAttuale;
		boolean fine=false;
		while(!fine)
		{	
			System.out.println("\nUniBiEvent V2.0");
			int i = Utility.scegli(titoloMain,vociMain,"Seleziona una voce",4);
			switch(i) {
				case 0: fine=true;
					break;
				case 1:vediCategorie();
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


	private void controlloEventi() {
		for (Categoria evento : listaPartite) 
			evento.aggiornaStato(dataOdierna);
	}

	private void creaEvento() {
		stampaCategorie();
		int scelta= Utility.sceltaDaLista("Seleziona categoria (0 per tornare alla home)",categorie.length);
		switch(scelta)
		{
			case 1: creaPartita();
				break;
			case 0: return;
		}
	}
	
	
	private void creaPartita() {
		Campo [] campi = new Campo[14];
		assegnaPartitaDiCalcio(campi);
		for (int i = 0; i < campi.length; i++) {
			System.out.print(campi[i].toString());
			switch (i)
			{
			   case NUMERO_PARTECIPANTI:
			   case QUOTA:
				   campi[i].setValore(Utility.leggiInteroOpzionale(""));
			      break;
			   case TITOLO:
			   case LUOGO:
			   case COMPRESO_IN_QUOTA:
			   case NOTE:
			   case GENERE:
				   campi[i].setValore(Utility.leggiStringa(""));
			      break;
			   case FASCIA_DI_ETA:
				   Integer min=Utility.leggiInteroOpzionale("\nEtà min");
				   Integer max=Utility.leggiInteroOpzionale("Età max");
				   if(!(min==null && max==null)) {
					   FasciaDiEta fascia = new FasciaDiEta(min, max);
					   campi[i].setValore(fascia);	
				   }
				  break;
			   case TERMINE_ISCRIZIONI:
			   case DATA:
			   case DATA_CONCLUSIVA:
				   Boolean formatoDataErrato=false;
				   Boolean incoerenzaData=false;
				   Data date;
				   Integer gg, mm, aa;
				   do {
					   gg=Utility.leggiInteroOpzionale("\nGiorno");
					   mm=Utility.leggiInteroOpzionale("Mese");
					   aa=Utility.leggiInteroOpzionale("Anno");
					   if(gg==null && mm==null && aa==null) {
						   date=null;
						   formatoDataErrato=false;
					   }
					   else {
						   date = new Data(gg, mm, aa);
						   formatoDataErrato=!date.controlloData();
						   incoerenzaData=date.isPrecedente(dataOdierna);
						   if (formatoDataErrato) System.out.println("Hai inserito una data nel formato errato!");
						   else if(incoerenzaData) System.out.println("Hai inserito una data già passata!");   
						   else campi[i].setValore(date);
					   }
				   } while(formatoDataErrato || incoerenzaData);
				      break;
			   case ORA:
			   case DURATA:
			   case ORA_CONCLUSIVA:
				   Boolean formatoOraErrato=false;
				   Ora orario;
				   Integer ora,minuti;
				   do {
					   ora=Utility.leggiInteroOpzionale("\nOra");
					   minuti=Utility.leggiInteroOpzionale("Minuti");
					   if(ora==null && minuti==null) {
						   orario=null;
						   formatoOraErrato=false;
					   }
					   else {
						   orario = new Ora(ora, minuti);
						   formatoOraErrato=!orario.controlloOra();
						   if (formatoOraErrato) System.out.println("Hai inserito un orario nel formato errato!");
						   else campi[i].setValore(orario);
					   }
				   } while(formatoOraErrato);
			   		break;
			}
		}
		if(controlloCompilazione(campi)){
			PartitaDiCalcio unaPartita = new PartitaDiCalcio(Arrays.copyOfRange(campi, 0, 11), Arrays.copyOfRange(campi, 12, 13));
			listaPartite.add(unaPartita);
			mioProfilo.addEvento(unaPartita);
		} else {
			System.out.println("Non hai compilato alcuni campi obbligatori");
		}
	}
	
	public Boolean controlloCompilazione(Campo [] campi) {
		for (int i = 0; i < campi.length; i++) {
			if(campi[i].isObbligatorio()) {
				if(campi[i].getValore()==null) return false;
			}
		}
		return true;
		
	}

	public void vediCategorie()
	{
		stampaCategorie();
		int scelta= Utility.sceltaDaLista("Seleziona categoria (0 per tornare alla home)",categorie.length);
		switch(scelta)
		{
			case 1: vediEventi(getEventiDisponibili());
					scegliEvento(getEventiDisponibili());
				break;
			case 0: return;
		}
	}
	
	

	public void stampaCategorie()
	{
		for (int i = 0; i < categorie.length; i++) {
			System.out.println(i+1 + ") " + categorie[i]);
		}
	}
	
	private Vector<Categoria> getEventiDisponibili(){
		Vector<Categoria> disponibili = new Vector<Categoria>();
			for(Categoria p:listaPartite) 
				if(!(mioProfilo.isPartecipante(p)) && p.isAperto()) disponibili.add(p);		
		return disponibili;
	}
	
	public void vediEventi(Vector<Categoria> disponibili)
	{
		for(int i=0; i<disponibili.size(); i++) { 
			System.out.println(disponibili.get(i).getNome() + " " + (i+1));
			System.out.println(disponibili.get(i).getCampiCompilati());
		}
	}
	
	private void scegliEvento(Vector<Categoria> disponibili) {
		int a = Utility.sceltaDaLista("Seleziona partita a cui vuoi aderire (0 per uscire):", disponibili.size());
		
			if(a==0) return;
			else{
				partecipaEvento(disponibili.get(a));
			}	
		
	}
	
	private void visualizzaSpazioPersonale() {
		boolean fine=false;
		do {
			int i = Utility.scegli("SPAZIO PERSONALE",vociSpazioPersonale,"Seleziona una voce",3);
			switch(i) {
				case 0:fine=true;
					break;
				case 1:
					if(mioProfilo.hasEventi()) mioProfilo.stampaIMieiEventi();
					fine=true;
					break;
				case 2:
					gestioneNotifiche();
					fine=true;
					break;
				default: System.out.println("Scelta non valida!");
					break;
			
				}
		}while(!fine);
	}
		
	public void gestioneNotifiche() {
		int a;
		Boolean fine=false;
		do {
			if(mioProfilo.noNotifiche()) {
				System.out.println("NON hai notifiche da visualizzare");
				fine=true;
			}else {
				mioProfilo.stampaNotifiche();
				a = Utility.sceltaDaLista("Seleziona notifica che vuoi eliminare (0 per uscire):", mioProfilo.getNumeroNotifiche());
				if(a==0) fine=true;
				else{
					mioProfilo.deleteNotifica(a-1); 
				}
			}
		}while(!fine);
	}
	
	private void partecipaEvento(Categoria evento) {
		evento.aggiungiPartecipante(mioProfilo);
		mioProfilo.addEvento(evento);
		controlloEventi();
	}
	
	
	
	public void assegnaEvento(Campo[] campi) 
	{
		campi[TITOLO]= new Campo<String>("Titolo","Titolo dell'evento",false);
		campi[NUMERO_PARTECIPANTI]=new Campo<Integer>("Numero partecipanti","Indica il numero massimo di partecipanti",true);
		campi[TERMINE_ISCRIZIONI]=new Campo<Data>("Data termine iscrizione","Indica la data limite entro cui iscriversi",true);
		campi[LUOGO]=new Campo<String>("Luogo","Indica il luogo dell'evento",true);
		campi[DATA]=new Campo<Data>("Data","Indica la data di svolgimento dell'evento",true);
		campi[ORA]=new Campo<Ora>("Ora","Indica l'ora di inizio dell'evento",true);
		campi[DURATA]=new Campo<Ora>("Durata","Indica la durata dell'evento",false);
		campi[QUOTA]=new Campo<Integer>("Quota iscrizione","Indica la spesa da sostenere per partecipare all'evento",true);
		campi[COMPRESO_IN_QUOTA]=new Campo<String>("Compreso in quota","Indica le voci di spesa comprese nella quota",false);
		campi[DATA_CONCLUSIVA]=new Campo<Data>("Data conclusiva","Indica la data di conclusione dell'evento",false);
		campi[ORA_CONCLUSIVA]=new Campo<Ora>("Ora conclusiva","Indica l'ora conclusiva dell'evento",false);
		campi[NOTE]=new Campo<String>("Note","Informazioni aggiuntive",false);		
	}
	
	public void assegnaPartitaDiCalcio(Campo[] campi) {
		
		assegnaEvento(campi);
		campi[GENERE]=new Campo<String>("Genere","Indica il genere dei giocatori",true);
		campi[FASCIA_DI_ETA]=new Campo<FasciaDiEta>("Fascia di età","Indica la fascia di età dei giocatori",true);
		
	}
		
}
