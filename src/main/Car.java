package main;

public class Car {
	private int id;
	private String brend;
	private String model;
	private int godiste;
	private double cena;
	
	public Car(int id, String brend, String model, int godiste, double cena) {
		super();
		this.id = id;
		this.brend = brend;
		this.model = model;
		this.godiste = godiste;
		this.cena = cena;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getBrend() {
		return brend;
	}
	public void setBrend(String brend) {
		this.brend = brend;
	}
	
	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public int getGodiste() {
		return godiste;
	}
	public void setGodiste(int godiste) {
		this.godiste = godiste;
	}
	public double getCena() {
		return cena;
	}
	public void setCena(double cena) {
		this.cena = cena;
	}
	
	
	
}
