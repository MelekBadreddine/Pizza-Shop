package models;

import java.util.List;

public class Commande {
    private String id;
    private String nom;
    private String prenom;
    private String adresse;
    private List<models.Pizza> pizzas;
    private List<models.Boisson> boissons;
    private double totalPrice;

    public Commande(String id, String nom, String prenom, String adresse, List<models.Pizza> pizzas, List<models.Boisson> boissons, double totalPrice) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.pizzas = pizzas;
        this.boissons = boissons;
        this.totalPrice = totalPrice;
    }

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public List<models.Pizza> getPizzas() {
		return pizzas;
	}

	public void setPizzas(List<models.Pizza> pizzas) {
		this.pizzas = pizzas;
	}

	public List<models.Boisson> getBoissons() {
		return boissons;
	}

	public void setBoissons(List<models.Boisson> boissons) {
		this.boissons = boissons;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

    // Getters and setters for all fields
    // ...
}
