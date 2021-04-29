package src; 

/**
 * La classe abstraite Visiteur représente un visiteur. 
 * @author Mathilde MOTTAY 
 */
public abstract class Visiteur {
    /**
     * Jeu de la vie 
     */
    protected JeuDeLaVie jeu; 

    /**
     * Constructeur Visiteur. 
     * @param jeu Jeu de la vie 
     */
    public Visiteur (JeuDeLaVie jeu){
        this.jeu = jeu; 
    }

    /**
     * Visite une cellule vivante 
     * @param cellule Cellule vivante visitée 
     */
    public abstract void visiteCelluleVivante(Cellule cellule);
    
    /**
     * Visite une cellule morte 
     * @param cellule Cellule morte visitée 
     */
    public abstract void visiteCelluleMorte(Cellule cellule); 
}
