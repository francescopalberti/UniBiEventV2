package unibs.app;

import java.util.Date;

public class Categoria {
	
	private static String nome;
	private static String descrizione;
	
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
	private Campo[] campiBase;
	private int partecipantiAttuali;
	
	public Categoria(String _nome, String _descrizione) {
		campiBase = new Campo[12];
		nome=_nome;
		descrizione=_descrizione;
		partecipantiAttuali=1;
	}

	public String getNome() {
		return nome;
	}

	public String getDescrizione() {
		return descrizione;
	}

	
	public Campo[] getCampiBase() {
		return campiBase;
	}

	/*public void setCampiBase(Campo[] campiBase) {
		this.campiBase = campiBase;
	}*/
	
	public String toString() {
		
		String S=nome + ": " + descrizione;
		
		return S;
	}
	
	public void aggiungiPartecipante() {
		partecipantiAttuali++;
	}
}
