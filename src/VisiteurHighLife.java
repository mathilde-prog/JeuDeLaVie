package src; 
/**
  * La classe VisiteurHighLife représente un visiteur HighLife. 
  * Hérité de la classe Visiteur. 
  * @see Visiteur 
  * 
  * @author Mathilde MOTTAY 
 */
public class VisiteurHighLife extends Visiteur {

    /**
     * Constructeur VisiteurHighLife. 
     * @param jeu Jeu de la vie 
     */
    public VisiteurHighLife(JeuDeLaVie jeu) {
        super(jeu);
    }

    /**
     * Les règles d'évolution du mode HighLife sont les suivantes : 
     * - Une cellule morte naît à l'étape suivante si elle est entourée de 3 ou 6 voisines vivantes
     * - Une cellule vivante survit à l'étape suivante si elle est entourée de 2 ou 3 voisines vivantes
     */


    /**
     * Visite une cellule vivante 
     * @param cellule Cellule vivante visitée 
     */
    @Override
    public void visiteCelluleVivante(Cellule cellule) {
        int nbVoisinesVivantes = cellule.nombreVoisinesVivantes(jeu); 

        if((nbVoisinesVivantes != 2) && (nbVoisinesVivantes != 3)){
            jeu.ajouteCommande(new CommandeMeurt(cellule));
        }        
    }
    
    /**
     * Visite une cellule morte 
     * @param cellule Cellule morte visitée 
     */
    @Override
    public void visiteCelluleMorte(Cellule cellule) {
        int nbVoisinesVivantes = cellule.nombreVoisinesVivantes(jeu); 

        if((nbVoisinesVivantes == 3) || (nbVoisinesVivantes == 6)){
            jeu.ajouteCommande(new CommandeVit(cellule));
        }
    }
}
