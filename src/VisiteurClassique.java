package src; 

/**
 * La classe VisiteurClassique représente un visiteur classique. 
 * Hérite de la classe Visiteur. 
 * @see Visiteur 
 * 
 * @author Mathilde MOTTAY 
 */
public class VisiteurClassique extends Visiteur {
    
    /**
     * Constructeur VisiteurClassique 
     * @param jeu Jeu de la vie 
     */
    public VisiteurClassique(JeuDeLaVie jeu){
        super(jeu); 
    } 

    /**
     * Les règles d'évolution du mode classique sont les suivantes : 
     * - Si une cellule possède moins de 2 voisines, elle va mourir de solitude.
     * - Si une cellule possède plus de 3 voisines, elle va mourir d'étouffement. 
     * - Si un emplacement vide possède 3 voisines, une nouvelle cellule va naître.  
     */

    /**
     * Visite une cellule vivante 
     * @param cellule Cellule vivante visitée 
     */
    public void visiteCelluleVivante(Cellule cellule){
        int nbVoisinesVivantes = cellule.nombreVoisinesVivantes(jeu); 

        if((nbVoisinesVivantes < 2) || (nbVoisinesVivantes > 3)){
            jeu.ajouteCommande(new CommandeMeurt(cellule));
        }
    }

    /**
     * Visite une cellule morte 
     * @param cellule Cellule morte visitée 
     */
    public void visiteCelluleMorte(Cellule cellule){
        int nbVoisinesVivantes = cellule.nombreVoisinesVivantes(jeu); 

        if(nbVoisinesVivantes == 3){
            jeu.ajouteCommande(new CommandeVit(cellule));
        }
    }
}
