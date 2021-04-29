package src.Themes; 

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.*;
import javax.imageio.ImageIO;

import src.PanelImage;
import src.JeuDeLaVie;
import src.JeuDeLaVieUI; 

/**
 * La classe ThemeFlappyBird représente un thème Flappy Bird. 
 * Hérite de la classe Theme. 
 * @author Mathilde MOTTAY 
 */
public class ThemeFlappyBird extends Theme {
    /**
     * Image pour représenter une cellule vivante 
     */
    private BufferedImage flappyBird; 

    /**
     * Constructeur ThemeFlappyBird. 
     * @param jeu Jeu de la vie 
     * @param jeuUI Interface graphique du jeu de la vie 
     * @throws IOException
     */
    public ThemeFlappyBird(JeuDeLaVie jeu, JeuDeLaVieUI jeuUI) throws IOException{
        super(jeu, jeuUI); 
        flappyBird = ImageIO.read(new File("img/flappyBird.png"));             
    }

    /**
     * Retourne le chemin de l'image en arrière-plan du thème 
     * @return Chemin de l'image en arrière-plan du thème 
     */
    @Override
    public String getBackground() {
        return "img/backgroundFlappyBird.png";
    }

    /**
     * Applique le thème en affichant les cellules vivantes 
     * @param g Partie graphique 
     * @param panel PanelImage 
     */
    @Override
    public void appliquerTheme(Graphics g, PanelImage panel) {
        int width = 10 * jeu.getZoom(); 
        int height = width*100/60; 
        int espaceX = width/2+10; 
        int espaceY = width/2+10; 

        for(int x = 0; x <= getJeu().getXMax(); x++){
            for(int y = 0; y <= getJeu().getYMax(); y++){
                if(getJeu().getGrilleXY(x,y).estVivante()){
                   g.drawImage(flappyBird,panel.getLocation().x+x*espaceX,panel.getLocation().y+y*espaceY, height, width, getJeuUI());
                }
            }
        } 
    }
}
