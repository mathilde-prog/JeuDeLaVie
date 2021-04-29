package src; 

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent; 
import java.awt.event.MouseListener;
import java.io.IOException;
import java.awt.event.ActionListener;
import javax.swing.event.ChangeListener; 
import javax.swing.event.ChangeEvent; 
import src.Themes.Theme;
import src.Themes.ThemeBambi;
import src.Themes.ThemeFlappyBird;
import src.Themes.ThemeOrange;
/**
 * La classe JeuDeLaVieUI représente l'interface graphique du jeu de la vie. 
 * Hérite de la classe JFrame et implémente l'interface Observateur. 
 * @see Observateur
 * @see JFrame  
 * @author Mathilde MOTTAY 
 */
public class JeuDeLaVieUI extends JFrame implements Observateur {
    
    private static final long serialVersionUID = 1L;
    
    /**
     * Jeu de la vie 
     */
    private JeuDeLaVie jeu; 

    /**
     * Interface graphique du jeu de la vie 
     */
    private JeuDeLaVieUI jeuUI; 

    /**
     * Thème du jeu 
     */
    private Theme theme; 

    /**
     * Container principal 
     */
    private Container mainContainer; 

    /**
     * Panel contenant les boutons boutonAvancerGeneration, boutonMarcheArret et boutonRemiseAZero
     */ 
    private JPanel boutonsPanel;

    /**
     * Panel contenant l'étiquette du numéro de la génération et le panel boutonsPanel (en haut de l'écran)
     */
    private JPanel topPanel;

    /**
     * Panel contenant les comboBox pour choisir le mode de jeu et le thème ainsi que le panel zoneReglages (à l'est de l'écran)
     */ 
    private JPanel eastPanel;

    /** 
     * Panel contenant les Panel ligneModePanel et ligneThemePanel
     */
    private JPanel combo; 

    /**
     * Panel contenant l'étiquette et la comboBox relatives au choix du mode de jeu
     */ 
    private JPanel ligneModePanel; 

    /** 
     * Panel contenant l'étiquette et la comboBox relatives au choix du thème du jeu
     */ 
    private JPanel ligneThemePanel; 
    
    /**
     * Panel contenant les éléments pour gérer la vitesse, le zoom, les dimensions de la grille, la densité et l'étiquette A Propos
     */
     private JPanel zoneReglages; 

    /**
     * Panel pour la simulation où sont affichées les cellules 
     */
    private PanelImage simulationPanel; 

    /**
     * Scrollpane pour la simulation 
     */ 
    private JScrollPane scrollPanelSimulation; 

    /**
     * Bouton pour lancer/arrêter la simulation
     */ 
    private JButton boutonMarcheArret; 

    /**
     * Bouton pour avancer d'une génération 
     */
    private JButton boutonAvancerGeneration;
    
    /**
     * Bouton pour remettre à zéro 
     */
    private JButton boutonRemiseAZero; 

    /**
     * Bouton pour changer les dimensions 
     */
    private JButton boutonDimensions; 

    /**
     * Bouton pour changer la densité 
     */
    private JButton boutonDensite; 
    
    /** 
     * Etiquette du numéro de la génération courante 
     */
    private JLabel etiquetteNumeroGenerationCourante;
    
    /**
     * Etiquette pour la vitesse 
     */
    private JLabel etiquetteSliderVitesse; 

    /**
     * Etiquette pour le zoom 
     */
    private JLabel etiquetteSliderZoom;
    
    /**
     * Etiquette pour le choix du mode 
     */
    private JLabel etiquetteChoixMode; 

    /** 
     * Etiquette pour le choix du thème 
     */
    private JLabel etiquetteChoixTheme; 

    /** 
     * Etiquette pour la densité 
     */
    private JLabel etiquetteDensite;
    
    /**
     * Etiquette A Propos 
     */
    private JLabel etiquetteAPropos;
    
    /**
     * Etiquette pour les dimensions 
     */
    private JLabel etiquetteDimensions;
    
    /** 
     * Etiquette pour le nombre de colonnes 
     */
    private JLabel etiquetteNbColonnes; 

    /** 
     * Etiquette pour le nombre de lignes 
     */
    private JLabel etiquetteNbLignes; 

    /**
     * Slider pour la vitesse 
     */
    private JSlider sliderVitesse;

    /** 
     * Slider pour le zoom 
     */
    private JSlider sliderZoom;

    /**
     * Noms des modes 
     */
    private String[] nomsModes = {"Classique","HighLife", "Day & Night"}; 
    
    /**
     * Noms des thèmes 
     */
    private String[] nomsThemes = {"Orange", "Flappy bird", "Bambi"}; 

    /** 
     * ComboBox pour les modes
     */
    private JComboBox<String> modes;
    
    /**
     * ComboBox pour les thèmes 
     */
    private JComboBox<String> themes; 

    /**
     * Image pour le jeu en pause 
     */
    private ImageIcon pause = new ImageIcon("img/pause.png"); 
    
    /**
     * Image pour le jeu en marche
     */  
    private ImageIcon play = new ImageIcon("img/play.png"); 

    /**
     * Image pour remettre le jeu à zéro 
     */ 
    private ImageIcon reset = new ImageIcon("img/reset.png"); 

    /**
     * Constructeur JeuDeLaVieUI. 
     * @param jeu Jeu de la vie 
     * @throws IOException
     */
    public JeuDeLaVieUI (JeuDeLaVie jeu) throws IOException {
        this.jeu = jeu; 
        theme = new ThemeOrange(jeu,this); 

        /**
         * Configuration fenêtre
         */
        this.setTitle("TP Jeu de la vie - Mathilde MOTTAY");
        this.setSize(900,650); 
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false); // Fenêtre non-redimensionnable 
        this.setBackground(new Color(44,43,41));

        /**
         * Création et configuration du bouton marche-arrêt 
         */
        boutonMarcheArret = new JButton(); 
        boutonMarcheArret.setIcon(play);
        boutonMarcheArret.addMouseListener(new boutonsHover(boutonMarcheArret)); 
        boutonMarcheArret.setSize(new Dimension(50,18)); 
        boutonMarcheArret.setBackground(new Color(255,165,51));
        boutonMarcheArret.setOpaque(true); 
        boutonMarcheArret.setBorderPainted(false);
        boutonMarcheArret.setToolTipText("Cliquez sur ce bouton pour lancer la simulation."); 

        /**
         * Création et configuration du bouton pour avancer d'une génération
         */
        boutonAvancerGeneration = new JButton("+ 1 génération");
        boutonAvancerGeneration.setFont(new Font("Rockwell", Font.BOLD, 16)); 
        boutonAvancerGeneration.addMouseListener(new boutonsHover(boutonAvancerGeneration)); 
        boutonAvancerGeneration.setEnabled(true); 
        boutonAvancerGeneration.setPreferredSize(new Dimension(200,18)); 
        boutonAvancerGeneration.setBackground(new Color(255,165,51));
        boutonAvancerGeneration.setOpaque(true);
        boutonAvancerGeneration.setBorderPainted(false);
        boutonAvancerGeneration.setToolTipText("Cliquez sur ce bouton pour avancer d'une génération."); 

        /**
         * Création et configuration du bouton pour la remise à zéro 
         */
        boutonRemiseAZero = new JButton(); 
        boutonRemiseAZero.setIcon(reset); 
        boutonRemiseAZero.setFont(new Font("Rockwell", Font.PLAIN, 14)); 
        boutonRemiseAZero.addMouseListener(new boutonsHover(boutonRemiseAZero)); 
        boutonRemiseAZero.setEnabled(true); 
        boutonRemiseAZero.setPreferredSize(new Dimension(50,18)); 
        boutonRemiseAZero.setBackground(new Color(255,165,51));
        boutonRemiseAZero.setOpaque(true);
        boutonRemiseAZero.setBorderPainted(false);
        boutonRemiseAZero.setToolTipText("Cliquez sur ce bouton pour tout remettre à zéro."); 

        /**
         * Création et configuration du bouton pour changer les dimensions 
         */
        boutonDimensions = new JButton("Changer les dimensions"); 
        boutonDimensions.setFont(new Font("Rockwell", Font.BOLD, 16)); 
        boutonDimensions.addMouseListener(new boutonsHover(boutonDimensions)); 
        boutonDimensions.setEnabled(true); 
        boutonDimensions.setPreferredSize(new Dimension(200,18)); 
        boutonDimensions.setBackground(new Color(255,165,51));
        boutonDimensions.setOpaque(true);
        boutonDimensions.setBorderPainted(false);
        boutonDimensions.setToolTipText("Cliquez sur ce bouton pour modifier les dimensions.");                    


        /**
         * Création et configuration du bouton pour changer la densité 
         */
        boutonDensite = new JButton("Changer la densité"); 
        boutonDensite.setFont(new Font("Rockwell", Font.BOLD, 16)); 
        boutonDensite.addMouseListener(new boutonsHover(boutonDensite)); 
        boutonDensite.setEnabled(true); 
        boutonDensite.setPreferredSize(new Dimension(200,18)); 
        boutonDensite.setBackground(new Color(255,165,51));
        boutonDensite.setOpaque(true);
        boutonDensite.setBorderPainted(false);
        boutonDensite.setToolTipText("Cliquez sur ce bouton pour modifier la densité.");                    

        
        /**
         * Création et configuration de l'étiquette du numéro de la génération courante 
         */
        etiquetteNumeroGenerationCourante = new JLabel(); 
        etiquetteNumeroGenerationCourante.setForeground(new Color(255,255,255));
        etiquetteNumeroGenerationCourante.setPreferredSize(new Dimension(250,10));
        
        /**
         * Création et configuration de l'étiquette pour la densité 
         */
        etiquetteDensite = new JLabel(); 
        etiquetteDensite.setForeground(new Color(255,255,255));
        etiquetteDensite.setPreferredSize(new Dimension(100,10));
        etiquetteDensite.setText("Densité : " + jeu.getDensite()*100 + " %"); 
        etiquetteDensite.setFont(new Font("Rockwell", Font.PLAIN, 14)); 

        /**
         * Création et configuration de l'étiquette pour les dimensions 
         */
        etiquetteDimensions = new JLabel(); 
        etiquetteDimensions.setForeground(new Color(255,255,255));
        etiquetteDimensions.setPreferredSize(new Dimension(100,10));
        etiquetteDimensions.setText("Dimensions actuelles de la grille : "); 
        etiquetteDimensions.setFont(new Font("Rockwell", Font.PLAIN, 14)); 

        /**
         * Création et configuration de l'étiquette pour le nombre de colonnes 
         */
        etiquetteNbColonnes = new JLabel(); 
        etiquetteNbColonnes.setForeground(new Color(255,255,255));
        etiquetteNbColonnes.setPreferredSize(new Dimension(100,10));
        etiquetteNbColonnes.setText("- Nombre de colonnes : " + (jeu.getXMax()+1));
        etiquetteNbColonnes.setFont(new Font("Rockwell", Font.PLAIN, 14)); 

        /**
         * Création et configuration de l'étiquette pour le nombre de lignes 
         */
        etiquetteNbLignes = new JLabel(); 
        etiquetteNbLignes.setForeground(new Color(255,255,255));
        etiquetteNbLignes.setPreferredSize(new Dimension(100,10));
        etiquetteNbLignes.setText("- Nombre de lignes : " + (jeu.getYMax()+1));
        etiquetteNbLignes.setFont(new Font("Rockwell", Font.PLAIN, 14)); 

        /**
         * Création et configuration de l'étiquette pour la vitesse 
         */
        etiquetteSliderVitesse = new JLabel("Vitesse : " + jeu.getVitesse() + " générations par minute"); 
        etiquetteSliderVitesse.setFont(new Font("Rockwell", Font.PLAIN, 14)); 
        etiquetteSliderVitesse.setForeground(new Color(255,255,255));
        etiquetteSliderVitesse.setPreferredSize(new Dimension(50,10));

        /**
         * Création et configuration du slider vitesse 
         */
        sliderVitesse = new JSlider(JSlider.HORIZONTAL,5,500,jeu.getVitesse()); 
        sliderVitesse.setBackground(new Color(44,43,41)); 
        sliderVitesse.setForeground(new Color(255,255,255));
        sliderVitesse.setMajorTickSpacing(55);
        sliderVitesse.setPaintTicks(true);
        sliderVitesse.setPaintLabels(true);
        sliderVitesse.setFont(new Font("Rockwell", Font.PLAIN, 14)); 

        /**
         * Création et configuration de l'étiquette pour le zoom 
         */
        etiquetteSliderZoom = new JLabel("Zoom : "); 
        etiquetteSliderZoom.setFont(new Font("Rockwell", Font.PLAIN, 14)); 
        etiquetteSliderZoom.setForeground(new Color(255,255,255));
        etiquetteSliderZoom.setPreferredSize(new Dimension(50,10));

        /**
         * Création et configuration du slider pour le zoom 
         */
        sliderZoom = new JSlider(JSlider.HORIZONTAL,1,20,jeu.getZoom()); 
        sliderZoom.setBackground(new Color(44,43,41)); 

        /**
         * Création et configuration de l'étiquette pour le choix du mode. 
         */
        etiquetteChoixMode = new JLabel("Choix du mode : "); 
        etiquetteChoixMode.setFont(new Font("Rockwell", Font.PLAIN, 14)); 
        etiquetteChoixMode.setForeground(new Color(255,255,255));
        etiquetteChoixMode.setPreferredSize(new Dimension(130,10));

        /**
         * Création et configuration de la comboBox pour le choix du mode 
         */
        modes = new JComboBox<String>(nomsModes); 
        modes.setFont(new Font("Rockwell", Font.PLAIN, 14)); 

        /**
         * Création et configuration de l'étiquette pour le choix du thème. 
         */
        etiquetteChoixTheme = new JLabel("Choix du thème : "); 
        etiquetteChoixTheme.setFont(new Font("Rockwell", Font.PLAIN, 14)); 
        etiquetteChoixTheme.setForeground(new Color(255,255,255));
        etiquetteChoixTheme.setPreferredSize(new Dimension(130,10));

        /**
         * Création et configuration de la comboBox pour le choix du thème 
         */
        themes = new JComboBox<String>(nomsThemes); 
        themes.setFont(new Font("Rockwell", Font.PLAIN, 14)); 

        /**
         * Création et configuration de l'étiquette pour le A Propos 
         */
        etiquetteAPropos = new JLabel(); 
        etiquetteAPropos.setForeground(new Color(255,255,255));
        etiquetteAPropos.setPreferredSize(new Dimension(100,10));
        etiquetteAPropos.setText("                   TP Jeu de la vie - Mathilde MOTTAY"); 
        etiquetteAPropos.setFont(new Font("Rockwell", Font.PLAIN, 10)); 

        organiseLaFenetre(); 
        
        jeuUI = this; 

        /* 
         * Listener pour le bouton boutonMarcheArret
         * Quand on clique sur ce bouton, la simulation est lancée ou suspendue. 
         */  
        boutonMarcheArret.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent evt){
                // Pour lancer la simulation 
                if(boutonMarcheArret.getIcon() == play){
                    actualise(); 
                    boutonMarcheArret.setIcon(pause); // Changement d'icône 
                    jeu.reprend();

                    boutonMarcheArret.setToolTipText("Cliquez sur ce bouton pour suspendre la simulation."); 

                    // On désactive les boutons boutonAvancerGeneration, boutonDensite et boutonDimensions. 
                    boutonAvancerGeneration.setEnabled(false);         
                    boutonAvancerGeneration.setToolTipText("Vous ne pouvez pas avancer d'une génération tant que la simulation est en cours."); 
                
                    boutonDensite.setEnabled(false);         
                    boutonDensite.setToolTipText("Vous ne pouvez pas modifier la densité tant que la simulation est en cours."); 

                    boutonDimensions.setEnabled(false);         
                    boutonDimensions.setToolTipText("Vous ne pouvez pas changer les dimensions tant que la simulation est en cours.");
                }
                // Pour arrêter la simulation 
                else if (boutonMarcheArret.getIcon() == pause){  
                    actualise(); 
                    boutonMarcheArret.setIcon(play); // Changement d'icône 
                    jeu.suspend(); 

                    boutonMarcheArret.setToolTipText("Cliquez sur ce bouton pour lancer la simulation."); 

                    // On active les boutons boutonAvancerGeneration, boutonDensite et boutonDimensions. 
                    boutonAvancerGeneration.setEnabled(true); 
                    boutonAvancerGeneration.setToolTipText("Cliquez sur ce bouton pour avancer d'une génération.");              
                
                    boutonDensite.setEnabled(true); 
                    boutonDensite.setToolTipText("Cliquez sur ce bouton pour modifier la densité.");                    
                
                    boutonDimensions.setEnabled(true); 
                    boutonDimensions.setToolTipText("Cliquez sur ce bouton pour modifier les dimensions.");                    
                }
            }
        }); 

        /*
         * Listener pour le bouton boutonRemiseAZero 
         * Lorsqu'on clique sur ce bouton, le jeu est remis à zéro. 
         */ 
        boutonRemiseAZero.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent evt){
                jeu.remetAZero(); // On demande au jeu de se remettre à zéro. 
                boutonMarcheArret.setIcon(play);  // Changement d'icône 
                jeu.suspend(); 

                boutonMarcheArret.setToolTipText("Cliquez sur ce bouton pour lancer la simulation."); 

                // On active les boutons boutonAvancerGeneration, boutonDensite et boutonDimensions. 
                boutonAvancerGeneration.setEnabled(true);   
                boutonAvancerGeneration.setToolTipText("Cliquez sur ce bouton pour avancer d'une génération.");              
                
                boutonDensite.setEnabled(true); 
                boutonDensite.setToolTipText("Cliquez sur ce bouton pour modifier la densité.");                    
                
                boutonDimensions.setEnabled(true); 
                boutonDimensions.setToolTipText("Cliquez sur ce bouton pour modifier les dimensions.");                    
           
                actualise(); 
            }

        }); 

        /*
         * Listener pour le bouton boutonAvancerGeneration 
         * Lorsqu'on clique sur ce bouton, on avance d'une génération. 
         */ 
        boutonAvancerGeneration.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent evt){
                jeu.calculerGenerationSuivante();
                actualise(); 
            }
        });

        /**
         * Listener pour le slider lié à la vitesse d'exécution.
         * Actualise la vitesse d'exécution de la simulation quand la valeur de ce slider change. 
         */
        sliderVitesse.addChangeListener(new ChangeListener(){

            @Override
            public void stateChanged(ChangeEvent e) {
                etiquetteSliderVitesse.setText ("Vitesse : " + sliderVitesse.getValue() + " générations par minute"); 
                jeu.setVitesse(sliderVitesse.getValue());                 
            }

        });

        /**
         * Listener pour le slider lié au zoom. 
         * Actualise le zoom quand la valeur de ce slider change. 
         */
        sliderZoom.addChangeListener(new ChangeListener(){

            @Override
            public void stateChanged(ChangeEvent e) {
                jeu.setZoom(sliderZoom.getValue()); 
                simulationPanel.repaint();                 
            }

        });

        /**
         * Listener pour la comboBox relative au choix du mode 
         * Le visiteur du jeu (relatif au mode) s'actualise selon le choix de l'utilisateur. 
         */
        modes.addActionListener (new ActionListener() {
            Visiteur visiteurClassique = new VisiteurClassique(jeu);  
            Visiteur visiteurDayNight = new VisiteurDayNight(jeu); 
            Visiteur visiteurHighLife = new VisiteurHighLife(jeu); 

            @Override
            public void actionPerformed(ActionEvent e) {
                // Récupération du mode de jeu choisi par le joueur 
                String choixUtilisateur = (String) modes.getSelectedItem();
                switch(choixUtilisateur) {
                   case "Classique": jeu.setVisiteur(visiteurClassique); break;
                   case "HighLife": jeu.setVisiteur(visiteurDayNight); break;
                   case "Day & Night": jeu.setVisiteur(visiteurHighLife); break; 
                   default: break; 
                }
            }
        }); 

        /**
         * Listener pour la comboBox relative au choix du thème  
         * Le thème du jeu s'actualise selon le choix de l'utilisateur. 
         */
        themes.addActionListener (new ActionListener () {
            Theme themeOrange = new ThemeOrange(jeu, jeuUI);  
            Theme themeFlappyBird = new ThemeFlappyBird(jeu, jeuUI);  
            Theme themeBambi = new ThemeBambi(jeu,jeuUI); 

            @Override
            public void actionPerformed(ActionEvent e) {
                // Récupération du thème choisi par le joueur 
                String choixUtilisateur = (String) themes.getSelectedItem();
                switch(choixUtilisateur) {
                   case "Orange": setTheme(themeOrange); simulationPanel.setTheme(themeOrange); break;
                   case "Flappy bird":  setTheme(themeFlappyBird); simulationPanel.setTheme(themeFlappyBird); break;
                   case "Bambi" : setTheme(themeBambi); simulationPanel.setTheme(themeBambi); break; 
                   default: break; 
                }

                try {
                    simulationPanel.setImage(theme.getBackground());
                    actualise(); 
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }); 

        /*
         * Listener pour le bouton boutonDensite
         * Lorsqu'on clique sur ce bouton, le joueur peut modifier la densité. 
         */ 
        boutonDensite.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent evt){
                // Affiche une fenêtre pour choisir la densité et récupère la valeur saisie par le joueur 
                double densite = afficherFenetreChoixDensite();

                // Si la densité saisie par le joueur est valide 
                if(densite != -1){
                    jeu.setDensite(densite);
                    etiquetteDensite.setText ("Densité : " + jeu.getDensite() * 100 + " %"); 
                    jeu.remetAZero();
                    boutonMarcheArret.setIcon(play);  
                    jeu.suspend(); 
                    boutonAvancerGeneration.setEnabled(true);
                    actualise();       
                }
            }
        }); 

        /**
         * Listener pour le bouton boutonDimensions.  
         * Lorsqu'on clique sur ce bouton, le joueur peut modifier les dimensions de la grille 
         */
        boutonDimensions.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent evt){
                // Affiche une fenêtre pour choisir les dimensions et récupère un tableau contenant les valeurs saisies par le joueur 
                int dimensions[] = afficherFenetreChoixDimensions();

                // Si les dimensions saisies par le joueur sont valides  
                if(dimensions != null){
                    if(dimensions[1] > 1){
                        etiquetteNbColonnes.setText("- Nombre de colonnes : " + dimensions[1]);
                    }
                    else {
                        etiquetteNbColonnes.setText("- Nombre de colonne : " + dimensions[1]);
                    }

                    if(dimensions[0] > 1){
                        etiquetteNbLignes.setText("- Nombre de lignes : " + dimensions[0]); 
                    }
                    else {
                        etiquetteNbLignes.setText("- Nombre de ligne : " + dimensions[0]); 
                    }
                    jeu.redimensionner(dimensions);
                    repaint();
                }
            }
        });

        actualise(); 
        this.setVisible(true);    
    }

    /**
     * Actualise le numéro de la génération courante à l'écran ainsi que l'affichage de la simulation 
     */
    public void actualise(){
        etiquetteNumeroGenerationCourante.setText("    Numéro génération courante : " + jeu.getNumeroGeneration());
        etiquetteNumeroGenerationCourante.setFont(new Font("Rockwell", Font.PLAIN, 14)); 
        repaint(); 
    }

    /**
     * Dessine à l'écran
     * @param g Partie graphique du JPanel
     */
    public void paint(Graphics g){
        super.paint(g); 
    }

    /**
     * Affecte au thème le thème passé en paramètre
     * @param t Nouveau thème 
     */
    public void setTheme(Theme t){
        theme = t; 
    }

    /**
     * La classe boutonsHover représente des boutons dont la couleur change à leur survol. 
     * Implémente l'interface MouseListener. 
     */
    private class boutonsHover implements MouseListener{

        // Bouton 
        private JButton bouton; 

        /**
         * Constructeur boutonsHover. 
         * @param bouton Bouton 
         */
        public boutonsHover(JButton bouton){
            this.bouton = bouton; 
        }

        /**
         * Change la couleur quand la souris survole le bouton 
         */
        @Override
        public void mouseEntered(MouseEvent evt) {
            if((bouton.isEnabled() == false) && (bouton.getText() == "+ 1 génération")){
                this.bouton.setBackground(new Color( 199, 0, 57 )); // en rouge
            }
            else if ((bouton.isEnabled() == false) && (bouton.getText() == "Changer la densité")){
                this.bouton.setBackground(new Color( 199, 0, 57 )); // en rouge
            }
            else if ((bouton.isEnabled() == false) && (bouton.getText() == "Changer les dimensions")){
                this.bouton.setBackground(new Color( 199, 0, 57 )); // en rouge
            }
            else {
                this.bouton.setBackground(new Color( 122, 234, 95)); // en vert 
            }
        }
    
        /**
         * Change la couleur quand la souris ne survole plus le bouton 
         */
        @Override
        public void mouseExited(MouseEvent evt) {
            this.bouton.setBackground(new Color(255,165,51)); // en orange 
        }

        @Override
        public void mouseClicked(MouseEvent e) {            
        }

        @Override
        public void mousePressed(MouseEvent e) {            
        }

        @Override
        public void mouseReleased(MouseEvent e) {           
        }        
    }

    /**
     * Affiche une fenêtre permettant au joueur de saisir la densité qu'il souhaite 
     * @return Valeur de la densité saisie par le joueur 
     */
    public double afficherFenetreChoixDensite(){
        JPanel panelChoixDensite = new JPanel(); 
        JTextField champSaisieDensite = new JTextField(3); 

        panelChoixDensite.add(new JLabel("Densité [0-1] :"));
        panelChoixDensite.add(champSaisieDensite); 

        if(JOptionPane.showConfirmDialog(this, panelChoixDensite, "Changer la densité", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
            try {
                Double d = Double.parseDouble(champSaisieDensite.getText()); 

                // La densité doit être comprise entre 0 et 1. 
                if((d >= 0) && (d <= 1)){
                    return d; 
                }
                // Fenêtre d'erreur si valeur incorrecte 
                else {
                   JPanel panelError = new JPanel();
                   JOptionPane.showMessageDialog(panelError, "La densité doit être comprise entre 0 et 1. Votre choix n'est pas pris en compte.", "Attention", JOptionPane.WARNING_MESSAGE);
                   return -1;
                }
            }
            catch(NumberFormatException e){
                return -1; 
            }
        }

        return -1; 

    }

    /**
     * Affiche une fenêtre permettant au joueur de saisir les dimensions de la grille 
     * @return Tableau contenant les dimensions saisies par le joueur 
     */
    public int[] afficherFenetreChoixDimensions(){
        JPanel panelChoixDimensions = new JPanel();
        JTextField champSaisieNbLignes = new JTextField(6);
        JTextField champSaisieNbColonnes = new JTextField(6);

        panelChoixDimensions.add(new JLabel("Nombre de lignes :"));
        panelChoixDimensions.add(champSaisieNbLignes);
        panelChoixDimensions.add(new JLabel("Nombre de colonnes :"));
        panelChoixDimensions.add(champSaisieNbColonnes);

        if(JOptionPane.showConfirmDialog(this, panelChoixDimensions, "Changer les dimensions", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {

            try {
                int choixDimensions[] = new int[2];
                choixDimensions[0] = Integer.parseInt(champSaisieNbLignes.getText());
                choixDimensions[1] = Integer.parseInt(champSaisieNbColonnes.getText());

                // Les nombres de lignes et colonnes doivent être strictement supérieurs à 0. 
                if ((choixDimensions[0] > 0) && (choixDimensions[1] > 0)){
                    return choixDimensions;
                }
                // Fenêtre d'erreur si valeurs incorrectes 
                else {
                   JPanel panelError = new JPanel();
                   JOptionPane.showMessageDialog(panelError, "Vous devez saisir des dimensions strictement supérieures à 0. Votre choix n'est pas pris en compte.", "Attention", JOptionPane.WARNING_MESSAGE);
                   return null;
                }
            }
            catch(NumberFormatException e){
                return null; 
            }
        }

        return null;
    }

    /**
     * Organise tous les panels dans le container principal de la fenêtre 
     * @throws IOException
     */
    public void organiseLaFenetre() throws IOException{
        mainContainer = this.getContentPane(); 
        mainContainer.setLayout(new BorderLayout(8,6)); 
        this.getRootPane().setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        boutonsPanel = new JPanel(); 
        boutonsPanel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5)); 
        boutonsPanel.setLayout(new BorderLayout(20,8));
        boutonsPanel.setBackground(new Color(44,43,41));
        boutonsPanel.add(boutonAvancerGeneration,BorderLayout.WEST); 
        boutonsPanel.add(boutonMarcheArret,BorderLayout.CENTER); 
        boutonsPanel.add(boutonRemiseAZero,BorderLayout.EAST); 

        topPanel = new JPanel(); 
        topPanel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5)); 
        topPanel.setLayout(new BorderLayout(20,8));
        topPanel.setBackground(new Color(44,43,41));
        topPanel.add(etiquetteNumeroGenerationCourante,BorderLayout.WEST); 
        topPanel.add(boutonsPanel, BorderLayout.CENTER);

        simulationPanel = new PanelImage(theme.getBackground(), theme); 
        scrollPanelSimulation = new JScrollPane(simulationPanel,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        
        simulationPanel.setBorder(BorderFactory.createLineBorder(new Color(255,174,102), 3, true)); // bordure orange 
        simulationPanel.setBackground(new Color(14,196,200));

        eastPanel = new JPanel();  
        eastPanel.setBorder(BorderFactory.createEmptyBorder(3,3,3,3)); 
        eastPanel.setLayout(new BorderLayout(20,8));
        eastPanel.setBackground(new Color(44,43,41));

        ligneModePanel = new JPanel(); 
        ligneModePanel.setBorder(BorderFactory.createEmptyBorder(3,3,3,3)); 
        ligneModePanel.setBackground(new Color(44,43,41));
        ligneModePanel.setLayout(new BorderLayout(20,8));
        ligneModePanel.add(etiquetteChoixMode,BorderLayout.WEST); 
        ligneModePanel.add(modes,BorderLayout.CENTER); 

        etiquetteChoixTheme = new JLabel("Choix du thème : "); 
        etiquetteChoixTheme.setFont(new Font("Rockwell", Font.PLAIN, 14)); 
        etiquetteChoixTheme.setForeground(new Color(255,255,255));
        etiquetteChoixTheme.setPreferredSize(new Dimension(130,10));
        themes = new JComboBox<String>(nomsThemes); 
        themes.setFont(new Font("Rockwell", Font.PLAIN, 14)); 

        ligneThemePanel = new JPanel(); 
        ligneThemePanel.setBorder(BorderFactory.createEmptyBorder(3,3,3,3)); 
        ligneThemePanel.setBackground(new Color(44,43,41));
        ligneThemePanel.setLayout(new BorderLayout(20,8));
        ligneThemePanel.add(etiquetteChoixTheme,BorderLayout.WEST); 
        ligneThemePanel.add(themes,BorderLayout.CENTER); 

        combo = new JPanel(); 
        combo.setBorder(BorderFactory.createEmptyBorder(3,3,3,3)); 
        combo.setBackground(new Color(44,43,41));
        combo.setLayout(new BorderLayout(20,8));

        combo.add(ligneModePanel,BorderLayout.CENTER); 
        combo.add(ligneThemePanel, BorderLayout.NORTH); 

        zoneReglages = new JPanel(); 
        zoneReglages.setBorder(BorderFactory.createEmptyBorder(3,3,3,3)); 
        zoneReglages.setLayout(new BoxLayout(zoneReglages,BoxLayout.Y_AXIS));
        zoneReglages.setBackground(new Color(44,43,41));
        zoneReglages.add(Box.createRigidArea(new Dimension(10,10)));
        zoneReglages.add(etiquetteSliderVitesse); 
        zoneReglages.add(Box.createRigidArea(new Dimension(10,10)));
        zoneReglages.add(sliderVitesse); 
        zoneReglages.add(Box.createRigidArea(new Dimension(30,30))); 
        zoneReglages.add(etiquetteSliderZoom); 
        zoneReglages.add(Box.createRigidArea(new Dimension(10,10)));
        zoneReglages.add(sliderZoom); 
        zoneReglages.add(Box.createRigidArea(new Dimension(30,30)));
        zoneReglages.add(etiquetteDimensions); 
        zoneReglages.add(etiquetteNbLignes); 
        zoneReglages.add(etiquetteNbColonnes); 
        zoneReglages.add(Box.createRigidArea(new Dimension(10,10)));
        zoneReglages.add(boutonDimensions); 
        zoneReglages.add(Box.createRigidArea(new Dimension(30,30)));
        zoneReglages.add(etiquetteDensite); 
        zoneReglages.add(Box.createRigidArea(new Dimension(10,10)));
        zoneReglages.add(boutonDensite); 
        zoneReglages.add(Box.createRigidArea(new Dimension(40,40))); 
        zoneReglages.add(etiquetteAPropos); 

        eastPanel.add(combo, BorderLayout.NORTH); 
        eastPanel.add(zoneReglages, BorderLayout.CENTER); 

        mainContainer.add(topPanel,BorderLayout.NORTH);
        scrollPanelSimulation.setViewportView(simulationPanel); 
        scrollPanelSimulation.repaint(); 
        simulationPanel.setPreferredSize(new Dimension(3000,3000));
        mainContainer.add(scrollPanelSimulation,BorderLayout.CENTER); 
        mainContainer.add(eastPanel,BorderLayout.EAST); 
        mainContainer.setBackground(new Color(44,43,41));
    }
}
