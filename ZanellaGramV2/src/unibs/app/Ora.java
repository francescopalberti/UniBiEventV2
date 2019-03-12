package unibs.app;

public class Ora {
	
	private Integer ora;
	private Integer minuti;
	
	public Ora(Integer ora, Integer minuti) {
		this.ora=ora;
		this.minuti=minuti;
	}

	public Integer getOra() {
		return ora;
	}

	public Integer getMinuti() {
		return minuti;
	}
	
	public boolean isPrecedente(Ora o) {
		if (o.getOra()<ora) return false;
		else {
			if (o.getMinuti()<minuti) return false;
		}
		return true;
	}
	
	@Override
	public String toString() {
		return ora + ":" + minuti;
	}
	
	

}
