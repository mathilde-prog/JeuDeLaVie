package src; 

/**
 * La classe CelluleEtatMort représente l'état d'une cellule morte. 
 * Implémente l'interface CelluleEtat
 * @see CelluleEtat
 * 
 * @author Mathilde MOTTAY
 */
public class CelluleEtatMort implements CelluleEtat {
    
    // Instance unique de l'état mort d'une cellule 
    private static CelluleEtatMort instanceUniqueCelluleEtatMort = null; 

    /**
     * Retourne l'unique instance de l'état mort d'une cellule (Design Pattern Singleton)
     * @return L'unique instance de l'état mort d'une cellule
     */
    public static CelluleEtatMort getInstance(){
        if(instanceUniqueCelluleEtatMort == null){
            instanceUniqueCelluleEtatMort = new CelluleEtatMort(); 
        }

        return instanceUniqueCelluleEtatMort; 
    }

    /**
     * Retourne l'unique instance de l'état vivant d'une cellule 
     * @return L'unique instance de l'état vivant d'une cellule 
     */
    @Override
    public CelluleEtat vit(){
        return CelluleEtatVivant.getInstance(); 
    }

    /**
     * Retourne l'état (déjà mort) de la cellule 
     * @return Etat mort de la cellule 
     */
    @Override
    public CelluleEtat meurt(){
        return this; 
    }

    /**
     * Retourne false puisque la cellule est à l'état morte. 
     */
    @Override
    public boolean estVivante(){
        return false; 
    }

    /**
     * Accepte que le visiteur passé en paramètre visite la cellule passée en paramètre  
     * @param visiteur Un visiteur 
     * @param cellule Une cellule 
     */
    @Override
    public void accepte(Visiteur visiteur, Cellule cellule){
        visiteur.visiteCelluleMorte(cellule);
    }
}
