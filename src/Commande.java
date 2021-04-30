package src; 

/**
 * La classe Commande est une classe abstraite représentant une commande. 
 * @author Mathilde MOTTAY
 */
public abstract class Commande {
    
    /**
     * Cellule sur laquelle on exécute la commande 
     */ 
    protected Cellule cellule; 

    /**
     * Constructeur Commande. 
     * @param cellule Cellule sur laquelle on exécute la commande 
     */
    public Commande(Cellule cellule){
        this.cellule = cellule; 
    }

    /**
     * Exécute la commande 
     */
    public abstract void executer(); 
}
