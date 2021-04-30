package src; 

/**
 * La classe VisiteurDayNight représente un visiteur Day & Night. 
 * Hérite de la classe Visiteur. 
 * @see Visiteur 
 * 
 * @author Mathilde MOTTAY 
 */
public class VisiteurDayNight extends Visiteur {

    /**
     * Constructeur VisiteurDayNight. 
     * @param jeu Jeu de la vie 
     */
    public VisiteurDayNight(JeuDeLaVie jeu) {
        super(jeu);
    }

    /**
     * Les règles d'évolution du mode Day & Night sont les suivantes : 
     * - Une cellule morte naît à l'étape suivante si elle est entourée de 3, 6, 7 ou 8 voisines mortes 
     * - Une cellule vivante survit à l'étape suivante si elle est entourée de 3, 4, 6, 7 ou 8 voisines vivantes
     */

     /**
     * Visite une cellule vivante 
     * @param cellule Cellule vivante visitée 
     */
    @Override
    public void visiteCelluleVivante(Cellule cellule) {
        int nbVoisinesVivantes = cellule.nombreVoisinesVivantes(jeu); 

        if((nbVoisinesVivantes == 1) || (nbVoisinesVivantes == 2) || (nbVoisinesVivantes == 5)){
            jeu.ajouteCommande(new CommandeMeurt(cellule));
        }    
    }

    /**
     * Visite une cellule morte 
     * @param cellule Cellule morte visitée 
     */
    @Override
    public void visiteCelluleMorte(Cellule cellule) {
        int nbVoisinesMortes = cellule.nombreVoisinesMortes(jeu); 

        if((nbVoisinesMortes == 3) || (nbVoisinesMortes == 6) || (nbVoisinesMortes == 7) || (nbVoisinesMortes == 8)){
            jeu.ajouteCommande(new CommandeVit(cellule));
        }   
    }
    
}
