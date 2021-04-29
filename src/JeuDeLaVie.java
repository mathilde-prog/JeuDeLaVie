package src; 

import java.lang.Math; 
import java.util.List;
import java.util.ArrayList;


/**
 * La classe JeuDeLaVie représente un jeu de la vie. 
 * Implémente l'interface Observable. 
 * @see Observable 
 * @author Mathilde MOTTAY 
 */
public class JeuDeLaVie implements Observable {
    
    /**
     * Grille de cellules du jeu 
     */ 
    private Cellule[][] grille; 

    /**
     * Coordonnée maximale en abscisse de la grille (largeur)
     */
    private int xMax; 

    /**
     * Coordonnée maximale en ordonnée de la grille (hauteur)
     */
    private int yMax; 

    /**
     * Liste des observateurs du jeu 
     */
    private List<Observateur> observateurs; 

    /**
     * Liste des commandes à exécuter 
     */
    private List<Commande> commandes; 

    /**
     * Visiteur du jeu 
     */
    private Visiteur visiteur; 

    /**
     * Numéro de génération courante 
     */
    private int numGeneration; 

    /**
     * Indicateur booléen si le jeu est en pause
     */ 
    private boolean enPause; 

    /** 
     * Vitesse d'exécution de la simulation
     */ 
    private int vitesse; 

    /**
     * Valeur du zoom  
     */
    private int zoom; 

    /** 
     * Valeur de la densité (comprise entre 0 et 1)
     */
    private double densite; 

    /**
     * Constructeur JeuDeLaVie. 
     */
    public JeuDeLaVie(){
        xMax = 29;  
        yMax = 29;   
        vitesse = 247;
        zoom = 5; 
        densite = 0.5; 
        numGeneration = 1; 
        initialiseGrille(); 
        observateurs = new ArrayList<Observateur>(); 
        commandes = new ArrayList<Commande>(); 
        visiteur = new VisiteurClassique(this); 
        enPause = true; 
    }

    /**
     * Peuple aléatoirement (selon la densité) la grille avec des cellules vivantes ou mortes
     */ 
    public void initialiseGrille(){
        int x, y; 
        Double nbAleatoire; 

        grille = new Cellule[yMax+1][xMax+1]; 
        // Parcours de la grille pour la peupler 
        for(x = 0; x <= xMax; x++){
            for(y = 0; y <= yMax; y++){
                nbAleatoire = Math.random(); // Retourne un double compris entre 0 et 1
                if(nbAleatoire <= densite){
                    grille[y][x] = new Cellule(x,y,CelluleEtatVivant.getInstance()); 
                }
                else {
                    grille[y][x] = new Cellule(x,y,CelluleEtatMort.getInstance()); 
                }
            }
        }
    }

    /**
     * Retourne la cellule qui se trouve dans la grille aux coordonnées passées en paramètre.
     * Retourne null si les coordonnées passées en paramètres ne sont pas valides. 
     * @param x Coordonnée en abscisse 
     * @param y Coordonnée en ordonnée 
     * @return Cellule aux coordonnées x et y ou null 
     */
    public Cellule getGrilleXY(int x, int y){
        // Vérification de la validité des coordonnées 
        if((x < 0) || (x > xMax) || (y < 0) || (y > yMax)){
            return null; 
        }
        return grille[y][x]; 
    }

    /**
     * Incrémente le numéro de la génération courante 
     */
    public void incrementeNumeroGeneration() {
        numGeneration += 1; 
    }

    /**
     * Retourne le numéro de la génération courante 
     * @return Numéro de la génération courante
     */
    public int getNumeroGeneration(){
        return numGeneration; 
    }

    /**
     * Affecte la valeur passée en paramètre au numéro de la génération   
     * @param value Nouvelle valeur du numéro de génération 
     */
    public void setNumeroGeneration(int value){
        numGeneration = value; 
    }

    /**
     * Affecte le visiteur passé en paramètre au visiteur du jeu 
     * @param v Visiteur 
     */
    public void setVisiteur(Visiteur v){
        this.visiteur = v; 
    }
    
    /**
     * Retourne la grille du jeu 
     * @return Grille du jeu 
     */
    public Cellule[][] getGrille(){
        return grille; 
    }

    /**
     * Retourne la coordonnée maximale en abscisse de la grille (largeur)
     * @return Coordonnée maximale en abscisse de la grille (largeur)
     */
    public int getXMax(){
        return xMax; 
    }

    /**
     * Retourne la coordonnée maximale en ordonnée de la grille (hauteur)
     * @return Coordonnée maximale en ordonnée de grille (hauteur)
     */
    public int getYMax(){
        return yMax; 
    }

    /**
     * Retourne la densité 
     * @return Densité 
     */
    public double getDensite(){
        return densite; 
    }

    /**
     * Affecte la valeur passée en paramètre à la densité 
     * @param value Nouvelle valeur de la densité 
     */
    public void setDensite(double value){
        densite = value; 
    }

    /**
     * Attache un observateur 
     * @param o Observateur à attacher 
     */
    public void attacheObservateur(Observateur o){
        observateurs.add(o); 
    }

    /**
     * Détache un observateur 
     * @param o Observateur à détacher 
     */
    public void detacheObservateur(Observateur o){
        observateurs.remove(o); 
    }

    /**
     * Notifie les observateurs pour qu'ils s'actualisent 
     */
    public void notifieObservateurs(){
        for(Observateur o : observateurs){
            o.actualise();
        }
    }

    /**
     * Ajoute une commande 
     * @param c Commande à ajouter 
     */
    public void ajouteCommande(Commande c){
        commandes.add(c); 
    }

    /**
     * Exécute toutes les commandes et vide la liste. 
     */
    public void executeCommandes(){
        for(Commande c :commandes){
            c.executer();
        }

        commandes.clear(); // Efface les commandes quand elles sont toutes exécutées.  
    }

    /**
     * Distribue le visiteur à toutes les cellules de la grille 
     */
    public void distribueVisiteur(){
        for(int x = 0; x <= xMax; x++){
            for(int y = 0; y <= yMax; y++){
                getGrilleXY(x,y).accepte(visiteur); 
            }
        }
    }

    /**
     * Calcule la génération suivante
     */
    public void calculerGenerationSuivante(){
        incrementeNumeroGeneration(); 
        distribueVisiteur();   // 1. Distribuer un visiteur 
        executeCommandes();    // 2. Exécuter les commandes 
        notifieObservateurs(); // 3. Actualiser les observateurs
    }

    /**
     * Met la simulation du jeu en pause 
     */
    public void suspend() {
        this.enPause = true;  
    }

    /**
     * Met la simulation du jeu en marche 
     */
    public void reprend(){
        this.enPause = false; 
    }

    /**
     * Retourne vrai si la simulation du jeu est en pause. faux sinon. 
     * @return true si la simulation du jeu est en pause. false sinon. 
     */
    public boolean estEnPause(){
        return this.enPause; 
    }

    /**
     * Affecte la valeur passée en paramètre à la vitesse 
     * @param valeur Nouvelle valeur de la vitesse 
     */
    public void setVitesse(int valeur){
        vitesse = valeur; 
    }
    
    /**
     * Retourne la vitesse d'exécution 
     * @return Vitesse d'exécution 
     */
    public int getVitesse(){
        return vitesse; 
    }

    /**
     * Affecte la valeur passée en paramètre au zoom 
     * @param value Nouvelle valeur du zoom 
     */
    public void setZoom(int value) {
        this.zoom = value; 
    }

    /**
     * Retourne la valeur du zoom
     * @return Valeur du zoom 
     */
    public int getZoom() {
        return zoom;
    }

    /**
     * Remet le jeu à zéro en réinitialisant la grille 
     */
    public void remetAZero(){
        numGeneration = 1; 
        initialiseGrille(); 
        enPause = true; 
    }

    /**
     * Redimensionne la grille et la remet à zéro 
     * @param dimensions Tableau contenant le nombre de lignes et colonnes choisis par le joueur 
     */
    public void redimensionner(int[] dimensions) {
        yMax = dimensions[0]-1;
        xMax = dimensions[1]-1;
		remetAZero();
    }
}
