package src.Themes; 
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.*;
import javax.imageio.ImageIO;
import src.JeuDeLaVie;
import src.PanelImage;
import src.JeuDeLaVieUI;
/**
 * La classe ThemeBambi représente un thème Bambi. 
 * Hérite de la classe Theme. 
 * @author Mathilde MOTTAY 
 */
public class ThemeBambi extends Theme {
 
    /**
     * Image pour représenter une cellule vivante 
     */
    private BufferedImage bambi; 

    /**
     * Constructeur ThemeBambi. 
     * @param jeu Jeu de la vie 
     * @param jeuUI Interface graphique du jeu de la vie 
     * @throws IOException
     */
    public ThemeBambi(JeuDeLaVie jeu, JeuDeLaVieUI jeuUI) throws IOException{
        super(jeu, jeuUI); 
        bambi = ImageIO.read(new File("img/bambi.png"));             
    }

    /**
     * Retourne le chemin de l'image en arrière-plan du thème 
     * @return Chemin de l'image en arrière-plan du thème 
     */
    @Override
    public String getBackground() {
        return "img/backgroundBambi.jpg";
    }

    /**
     * Applique le thème en affichant les cellules vivantes 
     * @param g Partie graphique 
     * @param panel PanelImage 
     */
    @Override
    public void appliquerTheme(Graphics g, PanelImage panel) {
        int dimension = 10 * jeu.getZoom(); 
        int espaceX = dimension/2+dimension-10; 
        int espaceY = dimension/2+dimension-10; 

        for(int x = 0; x <= getJeu().getXMax(); x++){
            for(int y = 0; y <= getJeu().getYMax(); y++){
                if(getJeu().getGrilleXY(x,y).estVivante()){
                    g.drawImage(bambi, panel.getLocation().x+15+x*espaceX, panel.getLocation().y+10+y*espaceY, dimension, dimension, getJeuUI());  
                }
            }
        }     
    }
}
