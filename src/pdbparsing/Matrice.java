/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pdbparsing;

/**
 * Classe qui permet de stocker des nombres dans une matrice.
 * Une fois instanciée utiliser la variable "values"(tableau) pour stocker puis utiliser les nombres.
 */
public class Matrice {
	
	/**
	* Constructeur avec 2 paramètres
	* @param int nbLigne , nombre de lignes de la matrice.
	* @param int nbColonnes , nombre de colonnes de la matrice.
	*/
	public Matrice(int nbLignes, int nbColonnes) {
		super();
	    this.nbLignes = nbLignes;
		this.nbColonnes = nbColonnes;
		this.values = new double[nbLignes][nbColonnes];
	}


	 int nbLignes;
	 int nbColonnes;
	public double values [][];
	
}