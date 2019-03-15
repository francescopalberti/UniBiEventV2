package unibs.app;

import java.io.Serializable;

public class Data implements Serializable {
	private Integer giorno, mese, anno;

public Data(Integer gg, Integer mm, Integer yy) {
		this.giorno = gg;
		this.mese = mm;
		this.anno = yy;
	}
	
	/**
	 * @return the giorno
	 */
	public Integer getGiorno() {
		return giorno;
	}

	/**
	 * @return the mese
	 */
	public Integer getMese() {
		return mese;
	}

	/**
	 * @return the anno
	 */
	public Integer getAnno() {
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
		return giorno + "/" + mese + "/" + anno;
	}
	
	
	
}
