package unibs.app;

public class Ora {
	
	private int ora;
	private int minuti;
	
	public Ora(int ora, int minuti) {
		this.ora=ora;
		this.minuti=minuti;
	}

	public int getOra() {
		return ora;
	}

	public int getMinuti() {
		return minuti;
	}
	
	public boolean isPrecedente(Ora o) {
		if (o.getOra()<ora) return false;
		else {
			if (o.getMinuti()<minuti) return false;
		}
		
		return true;
	}

}
