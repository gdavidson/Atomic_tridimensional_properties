package pdbparsing;
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
/**
* Classe qui permet de lire un fichier pdb et d'en extraire certaines informations.
* Les informations pouvant être extraites sont les suivantes:
* -La matrice de conversion coordonées cartésiennes -> coordonées fractionnaires.
* -La limite de diffraction maximale.
* -Les paramètres de la maille cristalline: les normes des vecteurs a,b et c ainsi que les angles alpha,beta et gamma.
* -La liste de tout les atomes et leur paramètres sous forme d'objets instanciés de la classe "Atom" stockés dans une ArrayList.
* Exemple d'utilisation:
* PDBReader P = new PDBReader(File.pdb);
* P.read();
* Puis utiliser les différentes méthode pour retourner l'information voulue.
*  
*/

public class PDBReader {
private String FileName;
private ArrayList<Atom> AtomList = new ArrayList<>();
private double limmax = 0;
private Matrice Scale = new Matrice (3,4);
private ArrayList<Double> MailleParamList = new ArrayList<>();
private ArrayList<AtomCart> AtomCartList = new ArrayList<>();
private ArrayList<String> AtomParamList = new ArrayList<> ();
/**
* Constructeur avec 1 paramètre
* @param FileName , nom du fichier pdb à utiliser sous la forme "nom.pdb".
*/
public PDBReader(String fileName) {
	super();
	FileName = fileName;
}
/**
 * Methode principale qui va lire le fichier et extraire les informations, il faut utiliser cette methode avant de pouvoir appeler les methodes suivantes.
 */
public void read() {
	
	// lecture du fichier pdb et stockage des lignes dans une ArrayList "al"
			try {
            try (InputStream ips = new FileInputStream(FileName); InputStreamReader ipsr = new InputStreamReader(ips)) {
                BufferedReader br = new BufferedReader(ipsr);
            ArrayList<String> al = new ArrayList<> ();
                
            String ligne;
       
                int numligne=0;
        
        
                while ((ligne = br.readLine()) != null) {
                        
                        al.add(numligne,ligne);
                        
                        numligne++;
                        }
//Parcours de la liste et récupèration des données atomiques de la matrice et de la maille.
       Iterator<String> it = al.iterator();
       
       int numligneatp=0;
      while ( it.hasNext()) {
      String s = (String) it.next();
      //Recupère les paramètres des atomes dans une arraylist "AtomParamList".
                if (s.startsWith("ATOM") || s.startsWith("HETATM")){
              AtomParamList.add(numligneatp,s);
              numligneatp++;
                }
      //Recupère la matrice de conversion dans une matrice "Scale"
              if (s.startsWith("SCALE1")){
                       String ss11 = s.substring(11,20).trim();
                       double scale11=Double.parseDouble(ss11);
                       Scale.values[0][0] = scale11;
                       String ss12 = s.substring(21,30).trim();
                       double scale12=Double.parseDouble(ss12);
                       Scale.values[0][1] = scale12;
                       String ss13 = s.substring(31,40).trim();
                       double scale13=Double.parseDouble(ss13);
                       Scale.values[0][2] = scale13;
                       String su1 = s.substring(46,55).trim();
                       Double u1 = Double.parseDouble(su1);
                       Scale.values[0][3]=u1;
              }
              if (s.startsWith("SCALE2")){
                      String ss21 = s.substring(11,20).trim();
                      double scale21=Double.parseDouble(ss21);
                      Scale.values[1][0]=scale21;
                         String ss22 = s.substring(21,30).trim();
                         double scale22=Double.parseDouble(ss22);
                         Scale.values[1][1] = scale22;
                         String ss23 = s.substring(31,40).trim();
                         double scale23=Double.parseDouble(ss23);
                         Scale.values[1][2] = scale23;
                         String su2 = s.substring(46,55).trim();
                         Double u2 = Double.parseDouble(su2);
                         Scale.values[1][3]=u2;
              }
              if (s.startsWith("SCALE3")){
                      String ss31 = s.substring(11,20);
                      double scale31=Double.parseDouble(ss31);
                      Scale.values[2][0]=scale31;
                         String ss32 = s.substring(21,30).trim();
                         double scale32=Double.parseDouble(ss32);
                         Scale.values[2][1] = scale32;
                         String ss33 = s.substring(31,40).trim();
                         double scale33=Double.parseDouble(ss33);
                         Scale.values[2][2] = scale33;
                         String su3 = s.substring(46,55).trim();
                         Double u3 = Double.parseDouble(su3);
                         Scale.values[2][3]=u3;
              }
              //Recupère les paramètres de la maille dans une ArrayList "MailleParamList".
              if (s.startsWith("CRYST1")){
                      String sa = s.substring(7,15).trim();
                      double a = Double.parseDouble(sa);
                      MailleParamList.add(0,a);
                      String sb = s.substring(16,24).trim();
                      double b = Double.parseDouble(sb);
                      MailleParamList.add(1,b);
                      String sc = s.substring(25,33).trim();
                      double c = Double.parseDouble(sc);
                      MailleParamList.add(2,c);
                      String salpha = s.substring(34,40).trim();
                      double alpha = Double.parseDouble(salpha);
                      MailleParamList.add(3,alpha);
                      String sbeta = s.substring(41,47).trim();
                      double beta = Double.parseDouble(sbeta);
                      MailleParamList.add(4,beta);
                      String sgamma = s.substring(48,54).trim();
                      double gamma = Double.parseDouble(sgamma);
                      MailleParamList.add(5,gamma);
                                           
            }
              
              //Récupère la limite de diffraction max
              if (s.startsWith("REMARK 200  RESOLUTION RANGE HIGH")){
                      String sdmin = s.substring(45,51).trim();
                      double dmin = Double.parseDouble(sdmin);
                      limmax=dmin;
                      
                      
              }
              }
            

  //Parcours les atomes et génère un objet de type "Atom" pour chaque atome du fichier qui sont ainsi stockés dans une ArrayList "AtomList".
  //Génère également une liste "AtomCartList" avec les coordonées cartésiennes.
          
         Iterator<String> it2 = AtomParamList.iterator();
        
         
         int ind=0;
         while ( it2.hasNext()) {
             String s = (String) it2.next();
                    String sx=s.substring(31,38).trim();
                    double Xcart=Double.parseDouble(sx);
                    String sy=s.substring(39,46).trim();
                    double Ycart=Double.parseDouble(sy);
                    String sz=s.substring(47,54).trim();
                    double Zcart=Double.parseDouble(sz);
                    //double Xfrac=Scale.values[0][0]*Xcart+Scale.values[0][1]*Ycart+Scale.values[0][2]*Zcart+Scale.values[0][3];
                    //double Yfrac=Scale.values[1][0]*Xcart+Scale.values[1][1]*Ycart+Scale.values[1][2]*Zcart+Scale.values[1][3];
                    //double Zfrac=Scale.values[2][0]*Xcart+Scale.values[2][1]*Ycart+Scale.values[2][2]*Zcart+Scale.values[2][3];
                    String sb= s.substring(61,66).trim();
                    double b=Double.parseDouble(sb);
                    String AtomName=s.substring(14,17).trim();
                    AtomCart A = new AtomCart (AtomName,Xcart,Ycart,Zcart,b);
                    AtomCartList.add(ind,A);
                    //Atom B = new Atom (AtomName,Xfrac,Yfrac,Zfrac,b);
                    //AtomList.add(ind,B);
                    ind++;
         }
br.close();
            }
	} catch (IOException | NumberFormatException e) {
			System.out.println(e.toString());
			}
			}
/**
 * Retourne une ArrayList avec des objets de type "Atom" dont les coordonées sont fractionnaires.
 * @return ArrayList<Atom>
 */
public ArrayList<Atom> GetAtoms(){
	return AtomList;
}
/**
 * Retourne les paramètres de la maille dans une ArrayList de doubles.
 * @return ArrayList<Double> qui contient selon l'index: 0-a;1-b;2-c;3-alpha;4-beta;5-gamma.
 * 
 */
public ArrayList<Double> GetParamMaille(){
	return MailleParamList;
}
/**
 * Retourne la limite de diffraction maximale.
 * @return un double
 */
public double Getlimmax(){
	return limmax;
}
/**
 * Retourne un objet de type "Matrice" qui permet de convertir les coordonnées cartésiennes  en coordonées fractionaires.
 * @return Matrice avec 3 lignes, 4 colonnes. La dernière colonne contient les vecteurs U1 U2 et U3
 */
public Matrice GetScale(){
	return Scale;
}
/**
 * Retourne une ArrayList avec des objets de type "AtomCart" dont les coordonées sont cartésiennes.
 * @return ArrayList<Atom>
 */
public ArrayList<AtomCart> GetAtomsCart(){
	return AtomCartList;
}
/**
 * Retourne les infos sur les atomes
 * @return une liste de string avec les infos sur les atomes.
 */
    public ArrayList<String> getAtomParamList() {
        return AtomParamList;
    }

}
