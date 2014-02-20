/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pdbparsing;

/**
 * Classe qui représente un atome selon les paramètres requis pour calculer le facteur de structure, les coordonées sont fractionnaires.
 */
public class Atom {
private String AtomName;
private double x;
private double y;
private double z;
private static int q=1;
private double b;
/**
* Constructeur avec 5 paramètres
* @param AtomName , nom de l'atome représenté par son élément chimique en une lettre. (String)
* @param x , abscisse x en coordonée fractionnaire. (double)
* @param y , ordonée y en coordonée fractionnaire. (double)
* @param z , cote z en coordonée fractionnaire. (double)
* @param b , facteur traduisant le désordre dynamique de l’atome. (double)
*/
public Atom(String atomName, double x, double y, double z, double b) {
    super();
	AtomName = atomName;
	this.x = x;
	this.y = y;
	this.z = z;
	this.b = b;
}
/**
 * Retourne le nom de l'atome.
 * @return un String d'une lettre symbolisant l'élément chimique.
 */
public String getAtomName() {
	return AtomName;
}
public void setAtomName(String atomName) {
	AtomName = atomName;
}
/**
 * Retourne l'abscisse de l'atome.
 * @return un double : l'abscisse x.
 */
public double getX() {
	return x;
}
public void setX(double x) {
	this.x = x;
}
/**
 * Retourne l'ordonée de l'atome.
 * @return un double : l'ordonée y.
 */
public double getY() {
	return y;
}
public void setY(double y) {
	this.y = y;
}
/**
 * Retourne la cote de l'atome.
 * @return un double : la cote z.
 */
public double getZ() {
	return z;
}
public void setZ(double z) {
	this.z = z;
}
/**
 * Retourne le facteur traduisant le désordre dynamique de l’atome.
 * @return un double étant le facteur traduisant le désordre dynamique de l’atome.
 */
public double getB() {
	return b;
}
public void setB(double b) {
	this.b = b;
}
/**
 * Retourne le facteur Q ramené à 1 par simplification.
 * @return int initialisé a 1.
 */
public int getQ(){
	return q;
}
/**
*Renvoi le nombre d'électrons selon le nom de l'atome.
* 
* @return int le nombre d'électrons
*/
public int getE(){
	
	int e = 0;
	if (this.AtomName.equals("H")){
		e=1;
		
	}
	if (this.AtomName.equals("C")){
		e=6;
		
	}
	if (this.AtomName.equals("N")){
		e=7;
		
	}
	if (this.AtomName.equals("O")){
		e=8;
		
	}
	if (this.AtomName.equals("S")){
		e=16;
		
	}
	return e;		
}
}

