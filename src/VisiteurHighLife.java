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
