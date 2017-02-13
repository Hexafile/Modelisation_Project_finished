package fr.univ_lille1.git_iut.sqlite;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.sun.tracing.dtrace.ArgsAttributes;

public class BDD {
	
	private Connection con;
	private Statement stmt;
	private ResultSet rs = null;
	private FichierPly file;
	
	String nom_fichier;

	/**
	 * Methode de connection au driver
	 */
	public void connection_driver(){
		try {
			Class.forName("org.sqlite.JDBC");
			System.out.println("Connexion driver OK");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("!!!!!!Erreur driver");
			deconnection_base(con);
		}
	}
	
	/**
	 * Methode d'ouverture de la base
	 * @param nom de la base de donnes a connecter
	 */
	public void connection_base(String nom_base){
		String url = "jdbc:sqlite:data/"+nom_base;
		try {
			con = DriverManager.getConnection(url);
			System.out.println("Connexion base sqlite OK");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("!!!!!!Erreur connection de la base");
			deconnection_base(con);
		}
	}
	
	/**
	 * Methode de fermeture du statement
	 */
	public void deconnection_statement(){
		try {
			stmt.close();
			System.out.println("Fermeture statement OK");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("!!!!!!Erreur fermeture statement");
			deconnection_base(con);
		}
	}
	/**
	 * Methode de fermeture de la connection a la base
	 * @param fichier de connexion
	 */
	public void deconnection_base(Connection con) {
		try {
			con.close();
			System.out.println("Fermeture connexion OK");
			System.out.println("-----------------");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("!!!!!!Erreur fermeture de connexion");
			System.out.println("-----------------");
		}
	}
	
	/**
	 * Creation d'une table
	 * @param nom_base ou la table sera creer
	 * si elle n existe pas, elle est creer
	 * @return un boolean si l'action s'est realise
	 */
	public boolean creation_table(String nom_base) {
		boolean resultat;
		nom_fichier = nom_base;
		connection_driver();
		connection_base(nom_base);
		try {
			stmt = con.createStatement();
			String query = "create table plyBase ("+
					"nom varchar(20) PRIMARY KEY NOT NULL,"+
					"chemin varchar(200) NOT NULL,"+
					"date varchar(10),"+
					"description varchar(200));";
			stmt.executeUpdate(query);
			System.out.println(query);
			System.out.println("Execution requete OK");
			resultat = true;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("!!!!!!Erreur requete SQL");
			deconnection_base(con);
			resultat = false;
		}
		deconnection_statement();
		deconnection_base(con);
		return resultat;
	}
	
	/**
	 * Insertion d'une ligne dans la base
	 * @param nom_base
	 * @param nom du fichier ply
	 * @param chemin d acces au fichier ply
	 * @param date de creation
	 * @param description
	 * @return un booolean si l action c est realise
	 */
	public boolean insertion_table(String nom_base,String nom,String chemin,String date,String description){
		boolean resultat;
		connection_driver();
		connection_base(nom_base);
		try {
			stmt = con.createStatement();
			String query = "INSERT INTO plyBase VALUES ('"+nom+"','"+chemin+"','"+date+"','"+description+"');";
			stmt.executeUpdate(query);
			System.out.println(query);
			System.out.println("Execution requete OK");
			resultat = true;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("!!!!!!Erreur requete SQL");
			deconnection_base(con);
			resultat = false;
		}
		deconnection_statement();
		deconnection_base(con);
		return resultat;
	}
	
	/**
	 * Methode de selection de toute une table
	 * @param nom_table
	 * @return une liste descriptive de fichier ply
	 */
	public List<FichierPly> selection_tout_table(String nom_table){
		List<FichierPly> urList = new ArrayList<>();
		connection_driver();
		connection_base(nom_table);
		try {
			stmt = con.createStatement();
			String query =("Select * from plyBase;");
			rs = stmt.executeQuery(query);
			System.out.println("Execution requete OK");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("!!!!!!Erreur requete SQL");
			deconnection_base(con);
		}
		// LISTE DES URLS
		try {
			while (rs.next()){
				String nom = rs.getString("nom");
				String chemin = rs.getString("chemin");
				String date = rs.getString("date");
				String description = rs.getString("description");
				FichierPly file = new FichierPly(nom, chemin, date, description);
				urList.add(file);
			}
			System.out.println("Execution selection des urls OK");
		} catch (SQLException e1) {
			System.out.println("!!!!!!Erreur selection des urls");
			deconnection_base(con);
			e1.printStackTrace();
		}
		deconnection_statement();
		deconnection_base(con);
		return urList;
	}
	
	/**
	 * Methode de selection de toute une table avec description
	 * @param nom_table
	 * @param description du fichier
	 * @return une liste descriptive de fichier ply
	 */
	public List<FichierPly> selection_tout_table_avec_description(String nom_table,String descriptionply){
		List<FichierPly> urList = new ArrayList<>();
		connection_driver();
		connection_base(nom_table);
		try {
			stmt = con.createStatement();
			String query =("Select * from plyBase where description ='"+descriptionply+"'");
			rs = stmt.executeQuery(query);
			System.out.println("Execution requete OK");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("!!!!!!Erreur requete SQL");
			deconnection_base(con);
		}
		// LISTE DES URLS
		try {
			while (rs.next()){
				String nom = rs.getString("nom");
				String chemin = rs.getString("chemin");
				String date = rs.getString("date");
				String description = rs.getString("description");
				FichierPly file = new FichierPly(nom, chemin, date, description);
				urList.add(file);
			}
			System.out.println("Execution selection des urls OK");
		} catch (SQLException e1) {
			System.out.println("!!!!!!Erreur selection des urls");
			deconnection_base(con);
			e1.printStackTrace();
		}
		deconnection_statement();
		deconnection_base(con);
		return urList;
	}
	
	/**
	 * Affichage de la liste descriptive du fichier
	 * @param liste
	 */
	public void imprimer_table(List<FichierPly> urList){
		System.out.println("Liste des urls :");
		for(int i=0; i<urList.size(); i++){
			System.out.println("url "+i+": "+urList.get(i)+";");
		}
	}
	
	/**
	 * Verification de la presence d un fichier dans la table
	 * @param nom_table
	 * @param nom du fichier
	 * @return un boolean representant la presence du fichier
	 */
	public boolean verification_presence_table(String nom_table,String nom){
		boolean res = false;
		connection_driver();
		connection_base(nom_table);
		try {
			stmt = con.createStatement();
			String query =("Select * from plyBase where nom ='"+nom+"';");
			rs = stmt.executeQuery(query);
			System.out.println("Execution requete OK");
			if(rs.next()) res = true;
			System.out.println("Execution verif OK");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("!!!!!!Erreur requete SQL");
			deconnection_base(con);
		}
		deconnection_statement();
		deconnection_base(con);
		return res;
	}
	
	/**
	 * Selection  d'un ficher en particulier
	 * @param nom_table
	 * @param nom_url
	 * @return
	 */
	public FichierPly selection_unique_table(String nom_table,String nom_url){
		file = null;
		connection_driver();
		connection_base(nom_table);
		try {
			stmt = con.createStatement();
			String query =("Select * from plyBase where nom ='"+nom_url+"';");
			rs = stmt.executeQuery(query);
			System.out.println("Execution requete OK");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("!!!!!!Erreur requete SQL");
			deconnection_base(con);
		}
		// LISTE DES URLS
		try {
			while (rs.next()){
				String nom = rs.getString("nom");
				String chemin = rs.getString("chemin");
				String date = rs.getString("date");
				String description = rs.getString("description");
				file = new FichierPly(nom, chemin, date, description);
			}
			System.out.println("Execution recuperation des urls OK");
		} catch (SQLException e1) {
			System.out.println("!!!!!!Erreur ajout des urls");
			System.out.println("!!!!!!Fichier ply non present");
			deconnection_base(con);
		}
		deconnection_statement();
		deconnection_base(con);
		return file;
	}
	
	/**
	 * Supprimer une ligne dans la table
	 * @param nom_base
	 * @param nom du fichier a supprimer
	 * @return un boolean si l action est realise
	 */
	public boolean suppression_table(String nom_base,String nom){
		boolean resultat = false;
		connection_driver();
		connection_base("demo.sqlite");
		try {
			stmt = con.createStatement();
			String query = "DELETE FROM plyBase WHERE nom = '"+nom+"';";
			stmt.executeUpdate(query);
			System.out.println(query);
			System.out.println("Execution suppression OK");
			resultat = true;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("!!!!!!Erreur supression SQL");
			deconnection_base(con);
			resultat = false;
		}
		deconnection_statement();
		deconnection_base(con);
		return resultat;
	}
	
	/**
	 * Mise a jour d une donnees
	 * @param nom_base
	 * @param url_avant changement
	 * @param url_apres si chnager sinon mettre null
	 * @param chemin si changer sinon mettre null
	 * @param date si changer sinon mettre null
	 * @param description si changer sinon mettre null
	 */
	@SuppressWarnings("unused")
	public void mise_a_jour_table(String nom_base,String nom_avant,String nom_apres,String chemin,String date,String description){
		FichierPly file_save = selection_unique_table(nom_base, nom_avant);
		suppression_table(nom_base,nom_avant);
		insertion_table(nom_base,nom_apres,chemin,date,description);
	}
	
	public FichierPly getFile() {
		return selection_unique_table("demo.sqlite","shark.ply");
	}
	
	@SuppressWarnings("unused")
	public BDD (){
		BDDname bn;
		BDDedit be;
		BDDdelete bd;
		BDDall ba;
		BDDfind bf;
		BDDadd baa;
		
		
	}
	
	public static void main (String[] args){
		new BDD();
	}
}
