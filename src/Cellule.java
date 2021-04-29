package src; 

/**
 * La classe Cellule représente une cellule.
 * @author Mathilde MOTTAY  
 */
public class Cellule {
    
    /**
     * Coordonnée en abscisse de la cellule dans la grille
     */
    private int x;

    /**
     * Coordonnée en ordonnée de la cellule dans la grille
     */
    private int y; 

    /**
     * Etat de la cellule
     */
    private CelluleEtat etat; 

    /**
     * Constructeur Cellule. 
     * @param x Coordonnée en abscisse de la cellule dans la grille 
     * @param y Coordonnée en ordonnée de la cellule dans la grille
     * @param etat Etat de la cellule 
     */
    public Cellule (int x, int y, CelluleEtat etat){
        this.x = x; 
        this.y = y; 
        this.etat = etat; 
    }

    /**
     * La cellule passe à l'état vivante. 
     */
    public void vit(){
        this.etat = etat.vit(); 
    }

    /**
     * La cellule passe à l'état morte. 
     */
    public void meurt(){
        this.etat = etat.meurt(); 
    }

    /**
     * Retourne vrai si la cellule est vivante. faux si elle est morte. 
     * @return true si la cellule est vivante. false sinon. 
     */
    public boolean estVivante(){
        return this.etat.estVivante(); 
    }

    /**
     * Retourne le nombre de cellules vivantes se trouvant dans le voisinage de la cellule 
     * @param jeu Le jeu de la vie 
     * @return Nombre de cellules vivantes se trouvant dans le voisinage de la cellule 
     */
    public int nombreVoisinesVivantes (JeuDeLaVie jeu){
        int nbVoisinesVivantes = 0; 

        // On récupère les 8 cellules voisines qui entourent la cellule receveur. 
        Cellule nord = jeu.getGrilleXY(x,y-1); 
        Cellule nordEst = jeu.getGrilleXY(x+1,y-1); 
        Cellule nordOuest = jeu.getGrilleXY(x-1,y-1); 
        Cellule sud = jeu.getGrilleXY(x,y+1); 
        Cellule sudEst = jeu.getGrilleXY(x+1,y+1); 
        Cellule sudOuest = jeu.getGrilleXY(x-1,y+1); 
        Cellule ouest = jeu.getGrilleXY(x-1,y); 
        Cellule est = jeu.getGrilleXY(x+1,y); 

        //  On teste pour chacune si elle existe et si elle est vivante 

        if((nord != null) && (nord.estVivante())){
            nbVoisinesVivantes++; 
        }

        if((nordEst != null) && (nordEst.estVivante())){
            nbVoisinesVivantes++; 
        }

        if((nordOuest != null) && (nordOuest.estVivante())){
            nbVoisinesVivantes++; 
        }

        if((sud != null) && (sud.estVivante())){
            nbVoisinesVivantes++; 
        }

        if((sudEst != null) && (sudEst.estVivante())){
            nbVoisinesVivantes++; 
        }

        if((sudOuest != null) && (sudOuest.estVivante())){
            nbVoisinesVivantes++; 
        }

        if((ouest != null) && (ouest.estVivante())){
            nbVoisinesVivantes++; 
        }

        if((est != null) && (est.estVivante())){
            nbVoisinesVivantes++; 
        }

        return nbVoisinesVivantes; 
    }

    /**
     * Retourne le nombre de cellules mortes se trouvant dans le voisinage de la cellule 
     * @param jeu Le jeu de la vie 
     * @return Nombre de cellules mortes se trouvant dans le voisinage de la cellule 
     */
    public int nombreVoisinesMortes (JeuDeLaVie jeu){
        int nbVoisinesMortes = 0; 

        // On récupère les 8 cellules voisines qui entourent la cellule receveur. 
        Cellule nord = jeu.getGrilleXY(x,y-1); 
        Cellule nordEst = jeu.getGrilleXY(x+1,y-1); 
        Cellule nordOuest = jeu.getGrilleXY(x-1,y-1); 
        Cellule sud = jeu.getGrilleXY(x,y+1); 
        Cellule sudEst = jeu.getGrilleXY(x+1,y+1); 
        Cellule sudOuest = jeu.getGrilleXY(x-1,y+1); 
        Cellule ouest = jeu.getGrilleXY(x-1,y); 
        Cellule est = jeu.getGrilleXY(x+1,y); 

        //  On teste pour chacune si elle existe et si elle est morte 

        if((nord != null) && (!nord.estVivante())){
            nbVoisinesMortes++; 
        }

        if((nordEst != null) && (!nordEst.estVivante())){
            nbVoisinesMortes++; 
        }

        if((nordOuest != null) && (!nordOuest.estVivante())){
            nbVoisinesMortes++; 
        }

        if((sud != null) && (!sud.estVivante())){
            nbVoisinesMortes++; 
        }

        if((sudEst != null) && (!sudEst.estVivante())){
            nbVoisinesMortes++; 
        }

        if((sudOuest != null) && (!sudOuest.estVivante())){
            nbVoisinesMortes++; 
        }

        if((ouest != null) && (!ouest.estVivante())){
            nbVoisinesMortes++; 
        }

        if((est != null) && (!est.estVivante())){
            nbVoisinesMortes++; 
        }

        return nbVoisinesMortes; 
    }

    /** 
     * Accepte le visiteur passé en paramètre 
     * @param visiteur Un visiteur 
     */
    public void accepte (Visiteur visiteur){
        etat.accepte(visiteur,this); 
    }
    
    /** 
     * Retourne une chaine de caractères décrivant la cellule (ses coordonnées)
     * @return Chaîne de caractères décrivant la cellule (ses coordonnées)
     */
    public String toString(){
        return "Cellule (x = " + x + ", y = " + y + ")"; 
    }
}