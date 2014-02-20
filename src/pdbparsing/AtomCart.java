/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pdbparsing;

import pdbparsing.Atom;

/**
 * Classe qui représente un atome selon les paramètres requis pour calculer le facteur de structure, les coordonées sont cartésiennes.
 */

public class AtomCart extends Atom {
	/**
	* Constructeur avec 5 paramètres
	* @param AtomName , nom de l'atome représenté par son élément chimique en une lettre. (String)
	* @param x , abscisse x en coordonée cartésienne. (double)
	* @param y , ordonée y en coordonée cartésienne. (double)
	* @param z , cote z en coordonée cartésienne. (double)
	* @param b , facteur traduisant le désordre dynamique de l’atome. (double)
	*/
	public AtomCart(String atomName, double x, double y, double z, double b) {
		super(atomName, x, y, z, b);
		
	}

}
