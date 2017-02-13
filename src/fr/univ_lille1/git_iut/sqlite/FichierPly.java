 package fr.univ_lille1.git_iut.sqlite;
 
 public class FichierPly {
 	String nom;
 	String chemin;
 	String date;
 	String description;
 	
 	public FichierPly (String nom,String chemin,String date,String description) {
 		this.nom = nom;
 		this.chemin = chemin;
 		this.date = date;
 		this.description = description;
 	}
 
 	public String getNom() {
 		return nom;
 	}
 
 	public String getChemin() {
 		return chemin;
 	}
 
 	public String getDate() {
 		return date;
 	}
 
 	public String getDescription() {
 		return description;
 	}
 
 	@Override
 	public String toString() {
 		return "FichierPly [nom=" + nom + ", chemin=" + chemin + ", date=" + date + ", description=" + description +"]";
 	}
 }
