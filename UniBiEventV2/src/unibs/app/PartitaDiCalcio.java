package unibs.app;

import java.io.*;

public class PartitaDiCalcio extends Categoria implements Serializable{
	
	private static final int GENERE=12;
	private static final int FASCIA_DI_ETA=13;
	private static final String descrizione = "Una partita di calcio";
	private static final String nome = "Partita di Calcio";
	private Campo[] campiSpecifici;
	
	private static final String lineSeparator="\n";

	public PartitaDiCalcio(Campo[] _campiGenerici, Campo[] _campiSpecifici) {
		super(nome, descrizione, _campiGenerici);
		campiSpecifici = new Campo[2];
		campiSpecifici = _campiSpecifici;
	}

	public Campo[] getCampiSpecifici() {
		return campiSpecifici;
	}
	
	
	public String getCampiCompilati()
	{
		StringBuffer s = new StringBuffer();
		s.append(super.getCampiCompilati());
		for(int j=0; j<campiSpecifici.length; j++) {
			s.append("   " + campiSpecifici[j].toStringValore());
			s.append(lineSeparator);
		}
		s.append("Stato: " + getStato());
		return s.toString();
	}


}
