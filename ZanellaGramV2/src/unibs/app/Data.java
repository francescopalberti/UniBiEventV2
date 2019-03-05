package unibs.app;

public class Data {
	private int giorno, mese, anno, ora, minuti;

	public Data(int gg, int mm, int yy) {
		this.giorno = gg;
		this.mese = mm;
		this.anno = yy;
	}

	public Data(int hh, int min) {
		this.ora = hh;
		this.minuti = min;
	}
	
	/**
	 * @return the giorno
	 */
	public int getGiorno() {
		return giorno;
	}

	/**
	 * @return the mese
	 */
	public int getMese() {
		return mese;
	}

	/**
	 * @return the anno
	 */
	public int getAnno() {
		return anno;
	}

	/**
	 * @return the ora
	 */
	public int getOra() {
		return ora;
	}

	/**
	 * @return the minuti
	 */
	public int getMinuti() {
		return minuti;
	}

	public Boolean isPrecedente(Data unaData) {
		if (unaData.getAnno()<anno) return false;
		else {
			if (unaData.getMese()<mese) return false;
			else {
				if (unaData.getGiorno()<=giorno) return false;
			}
		}
		
		return true;
	}
	
}
