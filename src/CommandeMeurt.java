package src; 

/**
 * La classe CommandeMeurt est une commande qui fait mourir la cellule. 
 * @see Commande 
 * @author Mathilde MOTTAY
 */
public class CommandeMeurt extends Commande {
    /**
     * Constructeur CommandeMeurt. 
     * @param cellule Cellule qu'on va faire mourir
     */
    public CommandeMeurt(Cellule cellule){
        super(cellule); 
    }

    /**
     * Fait mourir la cellule 
     */
    @Override
    public void executer(){
        cellule.meurt(); 
    }
}
