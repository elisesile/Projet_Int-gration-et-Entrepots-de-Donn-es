package data;

import java.util.Date;

public class Film {
	private String titre;
	private Date dateSortie;
	private String genre;
	private String distributeur;
	private Double budget;
	private Double revenusEtatsUnis;
	private Double revenusMondiaux;
	private String plot;
	private String realisateur;
	private String producteur;
	private String acteurs;
	
	public Film(String titre, String realisateur, String producteur, String acteur) {
		this.titre = titre;
		this.realisateur = realisateur;
		this.producteur = producteur;
		this.acteurs = acteur;
	}
	
	public Film(String titre) {
		this.titre = titre;
	}

	public String getTitre() {
		return titre;
	}

	public Date getDateSortie() {
		return dateSortie;
	}

	public String getGenre() {
		return genre;
	}

	public String getDistributeur() {
		return distributeur;
	}

	public Double getBudget() {
		return budget;
	}

	public Double getRevenusEtatsUnis() {
		return revenusEtatsUnis;
	}

	public Double getRevenusMondiaux() {
		return revenusMondiaux;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public void setDateSortie(Date dateSortie) {
		this.dateSortie = dateSortie;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public void setDistributeur(String distributeur) {
		this.distributeur = distributeur;
	}

	public void setBudget(Double budget) {
		this.budget = budget;
	}

	public void setRevenusEtatsUnis(Double revenusEtatsUnis) {
		this.revenusEtatsUnis = revenusEtatsUnis;
	}

	public void setRevenusMondiaux(Double revenusMondiaux) {
		this.revenusMondiaux = revenusMondiaux;
	}

	public String getRealisateur() {
		return realisateur;
	}

	public void setRealisateur(String realisateur) {
		this.realisateur = realisateur;
	}

	public String getProducteur() {
		return producteur;
	}

	public void setProducteur(String producteur) {
		this.producteur = producteur;
	}

	public String getActeurs() {
		return acteurs;
	}

	public void setActeurs(String acteurs) {
		this.acteurs = acteurs;
	}

	public String getPlot() {
		return plot;
	}

	public void setPlot(String plot) {
		this.plot = plot;
	}
	
	@Override
	public String toString() {
		return "Film [titre=" + titre + ", dateSortie=" + dateSortie + ", genre=" + genre + ", distributeur="
				+ distributeur + ", budget=" + budget + ", revenusEtatsUnis=" + revenusEtatsUnis + ", revenusMondiaux="
				+ revenusMondiaux + ", plot=" + plot + ", realisateur=" + realisateur + ", producteur=" + producteur
				+ ", acteurs=" + acteurs + "]";
	}

	public String getFilmInformationForFilm() {
		return "dateSortie=" + dateSortie + ", genre=" + genre + ", distributeur="
				+ distributeur + ", budget=" + budget.intValue() + ", revenusEtatsUnis=" + revenusEtatsUnis.intValue() + ", revenusMondiaux="
				+ revenusMondiaux.intValue() + ", realisateur=" + realisateur + ", resume=" + plot + ", acteurs=" + acteurs + ", producteur" + producteur;
	}
	public String getFilmInformationForActor() {
		return "titre=" + titre + ", dateSortie=" + dateSortie + ", genre=" + genre + ", distributeur="
				+ distributeur + ", realisateur=" + realisateur + ", producteur=" + producteur;
	}
}
