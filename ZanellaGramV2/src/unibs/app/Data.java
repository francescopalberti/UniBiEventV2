package unibs.app;

public class Data {
	private int giorno, mese, anno;

public Data(int gg, int mm, int yy) {
		this.giorno = gg;
		this.mese = mm;
		this.anno = yy;
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

	@Override
	public String toString() {
		return "Data: Giorno= " + giorno + ", Mese= " + mese + ", Anno= " + anno;
	}
	
	
	
}
