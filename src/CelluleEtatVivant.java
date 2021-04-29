package src; 

/**
 * La classe CelluleEtatVivant représente l'état d'une cellule vivante. 
 * Implémente l'interface CelluleEtat
 * @see CelluleEtat
 * 
 * @author Mathilde MOTTAY
 */
public class CelluleEtatVivant implements CelluleEtat {
    
    // Instance unique de l'état vivant d'une cellule 
    private static CelluleEtatVivant instanceUniqueCelluleEtatVivant = null; 

    /**
     * Retourne l'unique instance de l'état vivant d'une cellule (Design Pattern Singleton)
     * @return L'unique instance de l'état vivant d'une cellule
     */
    public static CelluleEtatVivant getInstance(){
        if(instanceUniqueCelluleEtatVivant == null){
            instanceUniqueCelluleEtatVivant = new CelluleEtatVivant(); 
        } 
        return instanceUniqueCelluleEtatVivant; 
    }

    /**
     * Retourne l'état (déjà vivant) de la cellule 
     * @return Etat vivant de la cellule 
     */
    @Override
    public CelluleEtat vit(){
        return this;         
    }

    /**
     * Retourne l'unique instance de l'état mort d'une cellule 
     * @return L'unique instance de l'état mort d'une cellule 
     */
    @Override
    public CelluleEtat meurt(){
        return CelluleEtatMort.getInstance(); 
    }

    /**
     * Retourne true puisque la cellule est à l'état vivante. 
     */
    @Override
    public boolean estVivante(){
        return true; 
    }

    /**
     * Accepte que le visiteur passé en paramètre visite la cellule passée en paramètre  
     * @param visiteur Un visiteur 
     * @param cellule Une cellule 
     */
    @Override
    public void accepte(Visiteur visiteur, Cellule cellule){
        visiteur.visiteCelluleVivante(cellule);
    }
}
