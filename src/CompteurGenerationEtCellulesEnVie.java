package src; 

/**
 * La classe CompteurGenerationEtCellulesEnVie est un observateur en mode texte qui 
 * affiche dans la console Java le numéro de génération courante et le nombre de 
 * cellules actuellement en vie.  
 * Implémente l'interface Observateur. 
 * @see Observateur
 * @author Mathilde MOTTAY 
 */
public class CompteurGenerationEtCellulesEnVie implements Observateur {

    /**
     * Jeu de la vie 
     */ 
    private JeuDeLaVie jeu; 

    /**
     * Constructeur CompteurGenerationEtCellulesEnVie. 
     * @param jeu Jeu de la vie 
     */
    public CompteurGenerationEtCellulesEnVie(JeuDeLaVie jeu){
        this.jeu = jeu; 
    }

    /**
     * Retourne le nombre de cellules actuellement en vie dans le jeu 
     * @return Nombre de cellules actuellement en vie 
     */
    private int getNbCellulesEnVie(){
        int nbCellulesEnVie = 0; 

        // Parcours de la grille du jeu 
        for(int x = 0; x < jeu.getXMax(); x++){
            for(int y = 0; y < jeu.getYMax(); y++){
                if(jeu.getGrilleXY(x,y).estVivante()){
                    // On incrémente le nombre de cellules en vie si la cellule est vivante. 
                    nbCellulesEnVie++; 
                }
            }
        }

        return nbCellulesEnVie; 
    } 

    /**
     * Actualise l'affichage du numéro de la génération courante et du nombre de cellules actuellement en vie 
     */
    @Override
    public void actualise() {
        System.out.println("\nNuméro de la génération courante : " + jeu.getNumeroGeneration()); 
        System.out.println("Nombre de cellules actuellement en vie : " + getNbCellulesEnVie());
    }    
}
