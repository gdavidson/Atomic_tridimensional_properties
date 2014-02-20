/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg3dproperties;

/**
 * Classe qui modélise un vecteur.
 * @author Davidson
 */
public class Vecteur {
    /**
     * abscisse x.
     */
    private double x;
    /**
     * ordonée y.
     */
    private double y;
    /**
     * cote z.
     */
    private double z;
/**
 * Retourne l'abscisse.
 * @return coordonée x
 */
    public double getX() {
        return x;
    }
/**
 * Retourne l'ordonée.
 * @return  coordonée y
 */
    public double getY() {
        return y;
    }
/**
 * Retourne la cote.
 * @return coordonée z
 */
    public double getZ() {
        return z;
    }
/**
 * Constructeur avec les coordonées.
 * @param x abscisse
 * @param y ordonée
 * @param z cote
 */
    public Vecteur(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
    /**
     * Constructeur à partir de deux points.
     * @param a un point
     * @param b un point
     */
    public Vecteur(Point a, Point b){
        this.x= b.getX()-a.getX();
        this.y= b.getY()-a.getY();
        this.z= b.getZ()-a.getZ();
    }
    /**
     * Produit vectoriel.
     * @param v un vecteur
     * @return un vecteur orthogonal aux deux autres
     */
    public Vecteur crossProduct(Vecteur v){
        double xres = this.getY()*v.getZ()-this.getZ()*v.getY();
        double yres = this.getZ()*v.getX()-this.getX()*v.getZ();
        double zres = this.getX()*v.getY()-this.getY()*v.getX();
        Vecteur result = new Vecteur(xres,yres,zres);
        return result;
    }
    /**
     * Produit scalaire.
     * @param v un vecteur
     * @return le résultat du produit scalaire
     */
    public double dotProduct(Vecteur v){
        double result = this.getX()*v.getX()+this.getY()*v.getY()+this.getZ()*v.getZ();
        return result;
    }
    /**
     * Retourne un vecteur normal à un plan.
     * @param a un point du plan
     * @param b un point du plan
     * @param c un point du plan
     * @return un vecteur normal au plan
     */
    public static Vecteur getNormalVector(Point a, Point b, Point c){
        Vecteur v1 = new Vecteur(a,b);
        Vecteur v2 = new Vecteur(a,c);
        Vecteur vnorm = v1.crossProduct(v2);
        return vnorm;
    }
    /**
     * Retourne la norme du vecteur.
     * @return le module du vecteur.
     */
    public double getNorm(){
        double squarenorm = Math.pow(this.x, 2)+Math.pow(this.y, 2)+Math.pow(this.z, 2);
        double norm = Math.sqrt(squarenorm);
        return norm;
        
    }
    /**
     * Retourne le cosinus de l'angle entre deux vecteurs.
     * @param v un vecteur.
     * @return le cosinus de l'angle entre les deux vecteurs. 
     */
    public double getAngleCos(Vecteur v){
        double costeta = this.dotProduct(v)/(this.getNorm()*v.getNorm());
        return costeta;
    }
}
