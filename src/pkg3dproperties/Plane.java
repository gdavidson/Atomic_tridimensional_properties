/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg3dproperties;

import java.util.ArrayList;

/**
 * Classe qui modélise un plan.
 * @author Davidson
 */
public class Plane {
    /**
     * Constante de l'équation: ax+by+cz+d=0.
     */
    private double a,b,c,d;
/**
 * Constructeur direct.
 * @param a constante a de l'équation: ax+by+cz+d=0.
 * @param b constante b de l'équation: ax+by+cz+d=0.
 * @param c constante c de l'équation: ax+by+cz+d=0.
 * @param d  constante d de l'équation: ax+by+cz+d=0.
 */
    public Plane(double a, double b, double c, double d) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
    }
    /**
     * Constructeur à partir de trois points du plan.
     * @param a un point du plan
     * @param b un point du plan
     * @param c un point du plan
     */
    public Plane (Point a, Point b, Point c){
        Vecteur vnorm = Vecteur.getNormalVector(a, b, c);
        this.a=vnorm.getX();
        this.b=vnorm.getY();
        this.c=vnorm.getZ();
        this.d=vnorm.getX()*a.getX()*(-1)+vnorm.getY()*a.getY()*(-1)+vnorm.getZ()*a.getZ()*(-1);
    }
    /**
     * Calcule l'angle dièdre formé par quatre points.
     * @param a un point
     * @param b un point
     * @param c un point
     * @param d un point
     * @return l'angle dièdre entre les plans (a,b,c) et (b,c,d)
     */
    public static double getDihedralAngle(Point a, Point b, Point c, Point d){
        Vecteur vAB = new Vecteur (b,a);
        Vecteur vCB = new Vecteur (b,c);
        Vecteur v1 = vAB.crossProduct(vCB);
        Vecteur vBC = new Vecteur (c,b);
        Vecteur vDC = new Vecteur (c,d);
        Vecteur v2 = vBC.crossProduct(vDC);
        double costeta = v1.getAngleCos(v2);
        Vecteur vDB = new Vecteur (b,d);
        double volume = v1.dotProduct(vDB);
        int sign = 1;
        if (volume < 0) {sign=-1;}
        double phi = Math.acos(costeta);
        double signedphi = phi * sign;
        return signedphi;
    }
    /**
     * Renvoi l'angle dièdre entre deux plans.
     * @param p un plan
     * @return  l'angle entre les deux plans.
     */
    public double getAngle(Plane p){
        double num = this.a* p.a + this.b*p.b + this.c*p.c;
        double sum1= Math.pow(this.a, 2)+Math.pow(this.b, 2)+Math.pow(this.c, 2);
        double sum2=Math.pow(p.a, 2)+Math.pow(p.b, 2)+Math.pow(p.c, 2);
        double denom = Math.sqrt((sum1*sum2));
        double costeta = Math.abs(num/denom);
        double angle= Math.acos(costeta);
        return angle;
    }
    /**
     * Renvoi le plan moyen d'une liste de points.
     * @param pointList la liste des points.
     * @return le plan moyen de l'échantillon de points.
     */
    public static Plane getAveragePlane(ArrayList<Point> pointList){
      double sxx=0;
      double sxy=0;
      double sx=0;
      double sxz=0;
      double syy=0;
      double syz=0;
      double sz=0;
      double sy=0;
      double szz =0;
      double s = pointList.size();
      for (int i=0;i<pointList.size();i++){
          Point p = pointList.get(i);
          double x = p.getX(); double y = p.getY(); double z = p.getZ();
          sxx+=(x*x);
          sxy+=(x*y);
          sx+=x;
          sxz+=(x*z);
          syy+=(y*y);
          syz+=(y*z);
          sz+=z;
          sy+=y;
          szz+=(z*z);
      }
      double dyz = s*syz-sy*sz;
      double dxy = s*sxy-sx*sy;
      double dxz= s*sxz-sx*sz;
      double dyy= s*syy-sy*sy;
      double dxx= s*sxx-sx*sx;
      double dzz=s*szz-sz*sz;
      double dz= dxy*dxy-dxx*dyy;
      double dx= dyz*dyz-dyy*dzz;
      double dy= dxz*dxz-dxx*dzz;
      //plan en fct de x
      double afx = -1;                      
      double bfx = (dxz*dyz - dxy*dzz) / dx;
      double cfx = (dxy*dyz - dxz*dyy) / dx;
      double dfx= (sx-bfx*sy - cfx*sz)/s; 
      //plan en fct de y
      double afy = (dxy*dxz - dyz*dxx) / dy;          
      double bfy = -1;
      double cfy = (dyz*dxz - dxy*dzz) / dy;
      double dfy = (sy-cfy*sz - afy*sx) / s;
      //plan en fct de z
      double afz = (dyz*dxy-dxz*dyy)/dz;
      double bfz = (dxz*dxy-dyz*dxx)/dz;
      double cfz=-1;
      double dfz = (sz-afz*sx-bfz*sy)/s;
      System.out.println(dfx+"    "+dfy+"      "+dfz);
      //selection du plan ac le d le plus élevé
      Plane avgPlane = null;
      //calcul p/r à z
      if (dfz>dfx && dfz>dfy){
      Plane plane = new Plane (afz,bfz,cfz,dfz);
      avgPlane = plane;
            }
      //calcul p/r à x
      if (dfx>dfz && dfx>dfy){
      Plane plane = new Plane (afx,bfx,cfx,dfx);
      avgPlane = plane;
            }
      //calcul p/r à y
      if (dfy>dfx && dfy>dfz){
      Plane plane = new Plane (afy,bfy,cfy,dfy);
      avgPlane = plane;
            }
     
      return avgPlane;
          }
/**
 * retourne a
 * @return a
 */
    public double getA() {
        return a;
    }
/**
 * retourne b
 * @return b
 */
    public double getB() {
        return b;
    }
/**
 * retourne c
 * @return c
 */
    public double getC() {
        return c;
    }
/**
 * retourne d
 * @return d
 */
    public double getD() {
        return d;
    }
    
}
