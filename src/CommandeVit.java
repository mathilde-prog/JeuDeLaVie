package src; 

/**
 * La classe CommandeVit est une commande qui fait vivre la cellule. 
 * @see Commande 
 * @author Mathilde MOTTAY
 */
public class CommandeVit extends Commande {
    /**
     * Constructeur CommandeVit. 
     * @param cellule Cellule qu'on va faire vivre
     */
    public CommandeVit(Cellule cellule){
        super(cellule); 
    }

    /**
     * Fait vivre la cellule. 
     */
    @Override
    public void executer(){
        cellule.vit(); 
    }
}
