package A06_Tiefensuche;

public class Film {

	private final String titel;
	
	private final double länge;

	public Film(String titel, double länge) {
		this.titel = titel;
		this.länge = länge;
	}

	public String getTitel() {
		return titel;
	}

	public double getLänge() {
		return länge;
	}

	@Override
	public String toString() {
		return titel + " (Länge: " + länge + ")";
	}

}
