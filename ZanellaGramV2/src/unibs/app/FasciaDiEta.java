package unibs.app;

//
public class FasciaDiEta {
	
	private int et‡Minima;
	private int et‡Massima;
	
	public FasciaDiEta(int min,int max) {
		if(min<max){
			this.et‡Minima=min;
		    this.et‡Massima=max;
		}
		else {
			this.et‡Massima=min;
			this.et‡Minima=max;
		}
	}

	public int getMin() {
		return et‡Minima;
	}

	public void setMin(int et‡Minima) {
		this.et‡Minima = et‡Minima;
	}

	public int getMax() {
		return et‡Massima;
	}

	public void setMax(int et‡Massima) {
		this.et‡Massima = et‡Massima;
	}
	
	public String toString() {
		return et‡Minima + " - " + et‡Massima;
	}

}
