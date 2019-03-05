package unibs.app;

public class PartitaDiCalcio extends Categoria{
	
	private static final int GENERE=12;
	private static final int FASCIA_DI_ETA=13;
	private static final String descrizione = "Una partita di calcio";
	private static final String nome = "Partita di Calcio";
	private Campo[] campiSpecifici = new Campo[2];

	public PartitaDiCalcio(Campo[] _campiGenerici, Campo[] _campiSpecifici) {
		super(nome, descrizione, _campiGenerici);
		campiSpecifici = _campiSpecifici;
	}

	public Campo[] getCampiSpecifici() {
		return campiSpecifici;
	}
	
	
	public String getDescrizioneCampi()
	{
		String campi=null;
		for(int i=0; i<12; i++)  campi.concat(super.getCampiBase()[i].toStringValore()+ "\n") ;
		for(int j=0;j<3;j++) campi.concat(campiSpecifici[j].toStringValore()+ "\n");
		return campi;
	}

	/*public void setCampiSpecifici(Campo[] campiSpecifici) {
		this.campiSpecifici = campiSpecifici;
	}*/
	

}
