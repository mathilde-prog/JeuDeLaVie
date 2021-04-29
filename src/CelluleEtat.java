package src; 

/**
 * Interface CelluleEtat
 * @author Mathilde MOTTAY
 */
public interface CelluleEtat {
    
    /**
     * On fait vivre la cellule. La cellule retournée est vivante. 
     * @return Cellule vivante 
     */
    public CelluleEtat vit(); 

    /**
     * On fait mourir la cellule. La cellule retournée est morte. 
     * @return Cellule morte
     */
    public CelluleEtat meurt(); 

    /**
     * Retourne vrai si la cellule est vivante. faux si elle est morte. 
     * @return true si la cellule est vivante. false sinon. 
     */
    public boolean estVivante(); 

    /**
     * Accepte que le visiteur passé en paramètre visite la cellule passée en paramètre  
     * @param visiteur Un visiteur 
     * @param cellule Une cellule 
     */
    public void accepte(Visiteur visiteur, Cellule cellule); 
}
