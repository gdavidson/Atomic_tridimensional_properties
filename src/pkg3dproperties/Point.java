/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg3dproperties;

import java.util.ArrayList;
import pdbparsing.AtomCart;

/**
 * Classe qui modélise un point.
 * @author Davidson
 */
public class Point {
    /**
     * Coordonée
     */
    private double x,y,z;
   
/**
 * retourne x
 * @return  x
 */
    public double getX() {
        return x;
    }
/**
 * retourne y
 * @return y
 */
    public double getY() {
        return y;
    }
/**
 * retourne z
 * @return z
 */
    public double getZ() {
        return z;
    }
/**
 * Contructeur direct.
 * @param x abscisse
 * @param y ordonée
 * @param z cote
 */
    public Point(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
    /**
     * Retourne la distance du point par rapport a un plan P.
     * @param p un plan P.
     * @return la distance du plan par rapport a P.
     */
    public double getDistanceFromPlane(Plane p){
        double a = p.getA(); double b = p.getB(); double c = p.getC(); double d = p.getD();
        double num=a*this.x+b*this.y+c*this.z+d;
        double denom=Math.sqrt(a*a+b*b+c*c);
        double dist = num/denom;
        return dist;
    }
    /**
     * Convertit une liste d'atomes en points.
     * @param atList la liste d'atomes à coordonnées cartésiennes
     * @return une liste de points avec les coordonnées des atomes.
     */
    public static ArrayList<Point> convertIntoPoints(ArrayList<AtomCart> atList){
       ArrayList<Point> pointList = new ArrayList();
        for (int i=0;i<atList.size();i++) {
           AtomCart at = atList.get(i);
           Point point = new Point(at.getX(),at.getY(),at.getZ());
           pointList.add(point);
       }
        return pointList;
    }
    
}
