import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import javax.swing.*;

public class VuePlateau extends JFrame implements ActionListener {
    private JButton up, down, left, right, quitter;
    private Case[][] cases; // Matrice pour représenter les cases du plateau
    private JLabel healthLabel; // Label pour afficher les points de vie
    private int heroRow, heroCol; // Position du héros sur le plateau
    private Hero hero; // Référence au héros
    private Monstre[][] monstres;
    private Potion[][] potions;
    private SacDor[][] sacdors;
    private Arme[][] armes;
    private JFrame fenetre;
    private JPanel jeux;
    private int randomNumber;
    private Random random;
    private int monstrevie;
    private int monstrebattu;
    private JLabel[][] pvlabels;

    public VuePlateau() {
        fenetre = new JFrame("Donjon Infini");
        fenetre.setSize(1000, 1000);
        fenetre.setLocation(500, 0);
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        hero = new Hero(100, 0, 0);

        jeux = new JPanel(new GridLayout(3, 3));
        cases = new Case[3][3];
        monstres = new Monstre[3][3];
        armes = new Arme[3][3];
        potions = new Potion[3][3];
        sacdors = new SacDor[3][3];
        pvlabels = new JLabel[3][3];


        random = new Random();
        monstrevie=0;
        monstrebattu=0;

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                cases[row][col] = new Case();
                cases[row][col].setBorder(BorderFactory.createLineBorder(Color.BLACK));
                jeux.add(cases[row][col]);

                if (row == 1 && col == 1) {
                    cases[row][col].setIcon(new ImageIcon("1.png"));
                    heroRow = row;
                    heroCol = col;
                    pvlabels[row][col] =new JLabel();
                        cases[row][col].setLayout(new BorderLayout());
                        cases[row][col].add(pvlabels[row][col],BorderLayout.SOUTH);
                } else {
                    double prob = random.nextDouble();

                    if (prob < 0.5) {
                        // Monstre
                        int randompv = random.nextInt(41) + 20;
                        Monstre monstre = new Monstre(randompv);
                        monstres[row][col] = monstre;
                        cases[row][col].setIcon(new ImageIcon("2.png"));

                        pvlabels[row][col] =new JLabel();
                        pvlabels[row][col].setText(Integer.toString(monstre.getpv()));
                        cases[row][col].setLayout(new BorderLayout());
                        cases[row][col].add(pvlabels[row][col],BorderLayout.SOUTH);
                       
                    } else if (prob < 0.7) {
                        // Sac d'or
                        int randompv = random.nextInt(41) + 20;
                        SacDor sacdor = new SacDor(randompv);
                        sacdors[row][col] = sacdor;
                        cases[row][col].setIcon(new ImageIcon("3.png"));

                        pvlabels[row][col] =new JLabel();
                        pvlabels[row][col].setText(Integer.toString(sacdor.getpv()));
                        cases[row][col].setLayout(new BorderLayout());
                        cases[row][col].add(pvlabels[row][col],BorderLayout.SOUTH);
                        
                    } else if (prob < 0.875) {
                        // Potion
                        int randompv = random.nextInt(41) + 20;
                        Potion potion = new Potion(randompv);
                        potions[row][col] = potion;
                        cases[row][col].setIcon(new ImageIcon("4.png"));

                        pvlabels[row][col] =new JLabel();
                        pvlabels[row][col].setText(Integer.toString(potion.getpv()));
                        cases[row][col].setLayout(new BorderLayout());
                        cases[row][col].add(pvlabels[row][col],BorderLayout.SOUTH);
                       
                    } else {
                        // Arme
                        int randompv = random.nextInt(41) + 20;
                        Arme arme = new Arme(randompv);
                        armes[row][col] = arme;
                        cases[row][col].setIcon(new ImageIcon("5.png"));

                        pvlabels[row][col] =new JLabel();
                        pvlabels[row][col].setText(Integer.toString(arme.getpv()));
                        cases[row][col].setLayout(new BorderLayout());
                        cases[row][col].add(pvlabels[row][col],BorderLayout.SOUTH);
                       
                    }
                }
           
 }
}
        fenetre.add(jeux);

        up = new JButton("Haut");
        up.addActionListener(this);
        down = new JButton("Bas");
        down.addActionListener(this);
        left = new JButton("Gauche");
        left.addActionListener(this);
        right = new JButton("Droite");
        right.addActionListener(this);
        quitter = new JButton("Quitter");
        quitter.addActionListener(this);

        JPanel buttons = new JPanel();
        buttons.add(up);
        buttons.add(down);
        buttons.add(left);
        buttons.add(right);
        buttons.add(quitter);

        fenetre.add(buttons, BorderLayout.SOUTH);



// Panel pour afficher les points de vie
JPanel statusPanel = new JPanel();
healthLabel = new JLabel("Points de vie : " + hero.getpv() + "  points du heros : " + hero.getpoints());
statusPanel.add(healthLabel);
fenetre.add(statusPanel, BorderLayout.NORTH);

fenetre.setVisible(true);
}

private void miseAJourLabelsPV() {
    for (int row = 0; row < 3; row++) {
        for (int col = 0; col < 3; col++) {
            if (cases[row][col].getIcon() != null) {
                if (cases[row][col].getIcon().toString().equals("2.png")) {
                    pvlabels[row][col].setText(Integer.toString(monstres[row][col].getpv()));
                } else if (cases[row][col].getIcon().toString().equals("3.png")) {
                    pvlabels[row][col].setText(Integer.toString(sacdors[row][col].getpv()));
                } else if (cases[row][col].getIcon().toString().equals("4.png")) {
                    pvlabels[row][col].setText(Integer.toString(potions[row][col].getpv()));
                } else if (cases[row][col].getIcon().toString().equals("5.png")) {
                    pvlabels[row][col].setText(Integer.toString(armes[row][col].getpv()));
                }
            }
        }
    }
}











public void actionPerformed(ActionEvent e) {
int newHeroRow = heroRow;
int newHeroCol = heroCol;
int pointdevieact = hero.getpv();
int pointher = hero.getpoints();
int reaparraitre=0;
int sacsimonstre=0;


if(monstrevie!=0){
    reaparraitre=monstrevie;
}


if(monstrebattu!=0){
    sacsimonstre=monstrebattu;
}









if (e.getSource() == up && heroRow > 0) {
    newHeroRow--;
} else if (e.getSource() == down && heroRow < 2) {
    newHeroRow++;
} else if (e.getSource() == left && heroCol > 0) {
    newHeroCol--;
} else if (e.getSource() == right && heroCol < 2) {
    newHeroCol++;
}else if (e.getSource() == quitter){
     fenetre.setVisible(false);
}







if (newHeroRow != heroRow || newHeroCol != heroCol) {
    // Vérifier l'objet rencontré à la nouvelle position
    Icon icon = cases[newHeroRow][newHeroCol].getIcon();
    





    if (icon != null) {



        if (icon.toString().equals("2.png")) {
            Monstre monstre = monstres[newHeroRow][newHeroCol];
            int pvMonstre = monstre.getpv();
            if(hero.getarme()!=0){
                monstre.setpv(pvMonstre-hero.getarme());
                hero.setarme(hero.getarme()-pvMonstre);
                if(monstre.getpv()<=0){
                    monstrevie=0;
                    monstrebattu= pvMonstre;
                }
                if(hero.getarme()<0){
                    hero.setarme(0);
                    if(monstre.getpv()>0){
                        hero.setpv(pointdevieact-monstre.getpv());
                        monstrevie=monstre.getpv();
                        monstrebattu=0;
                    }
                }

            }else{
            hero.setpv(pointdevieact - pvMonstre);
            monstrevie=monstre.getpv();
            monstrebattu=0;
        }

        } 



        else if (icon.toString().equals("3.png")) {
            SacDor sacdor = sacdors[newHeroRow][newHeroCol];
            int pvSacDor = sacdor.getpv();
            // Sac d'or 
            hero.setpoints(pointher + pvSacDor);
              monstrebattu=0;
              monstrevie=0;

        } 



        else if (icon.toString().equals("4.png")) {
            Potion potion = potions[newHeroRow][newHeroCol];
            int pvPotion = potion.getpv();
            hero.setpv(pointdevieact + pvPotion);
            hero.setpoints(pointher + pvPotion);
            monstrebattu=0;
              monstrevie=0;
 
        }else{
           Arme arme = armes[newHeroRow][newHeroCol];
           int pvArme = arme.getpv();
            if(hero.getarme()==0 || hero.getarme()<arme.getpv() ){
                hero.setarme(arme.getpv());
                hero.setpoints(pointher + arme.getpv());
        }else{
            hero.setpoints(pointher + arme.getpv());
        } 
            monstrebattu=0;
              monstrevie=0;
            }
        }
    


 

    // Déplacer le héros sur le plateau
    cases[heroRow][heroCol].setIcon(null); // Supprimer l'icône du héros de la position actuelle
    cases[newHeroRow][newHeroCol].setIcon(new ImageIcon("1.png")); // Placer l'icône du héros à la nouvelle position














    double prob = random.nextDouble();


    if(reaparraitre!=0){
Monstre monstre = new Monstre(reaparraitre);                  
monstres[heroRow][heroCol] = monstre;
cases[heroRow][heroCol].setIcon(new ImageIcon("2.png"));
if(pvlabels[heroRow][heroCol] == null) {  // Si le label n'existe pas, le créer
    pvlabels[heroRow][heroCol] = new JLabel();
    cases[heroRow][heroCol].add(pvlabels[heroRow][heroCol], BorderLayout.SOUTH);
}
pvlabels[heroRow][heroCol].setText(Integer.toString(monstre.getpv()));
    }


    else if(sacsimonstre!=0){
         SacDor sacdor = new SacDor(sacsimonstre);
                        sacdors[heroRow][heroCol]=sacdor;
                        cases[heroRow][heroCol].setIcon(new ImageIcon("3.png"));
                        if(pvlabels[heroRow][heroCol] == null) {  // Si le label n'existe pas, le créer
    pvlabels[heroRow][heroCol] = new JLabel();
    cases[heroRow][heroCol].add(pvlabels[heroRow][heroCol], BorderLayout.SOUTH);
}
pvlabels[heroRow][heroCol].setText(Integer.toString(sacdor.getpv()));
    }

    else{
                   
                    if (prob<0.5) {
                        // Monstre
                         int randompv = random.nextInt(41)+20;
                        Monstre monstre = new Monstre(randompv);                  
                        monstres[heroRow][heroCol] = monstre;
                        cases[heroRow][heroCol].setIcon(new ImageIcon("2.png"));
                        if(pvlabels[heroRow][heroCol] == null) {  // Si le label n'existe pas, le créer
    pvlabels[heroRow][heroCol] = new JLabel();
    cases[heroRow][heroCol].add(pvlabels[heroRow][heroCol], BorderLayout.SOUTH);
}
pvlabels[heroRow][heroCol].setText(Integer.toString(monstre.getpv()));

                    } else if (prob<0.7) {
                        // Sac d'or
                         int randompv = random.nextInt(41)+20;
                        SacDor sacdor = new SacDor(randompv);
                        sacdors[heroRow][heroCol]=sacdor;
                        cases[heroRow][heroCol].setIcon(new ImageIcon("3.png"));
                        if(pvlabels[heroRow][heroCol] == null) {  // Si le label n'existe pas, le créer
    pvlabels[heroRow][heroCol] = new JLabel();
    cases[heroRow][heroCol].add(pvlabels[heroRow][heroCol], BorderLayout.SOUTH);
}
pvlabels[heroRow][heroCol].setText(Integer.toString(sacdor.getpv()));
                        
                    } else if (prob<0.875) {
                        // Potion
                         int randompv = random.nextInt(41)+20;
                       Potion potion = new Potion(randompv);
                       potions[heroRow][heroCol]= potion;
                        cases[heroRow][heroCol].setIcon(new ImageIcon("4.png"));
                        if(pvlabels[heroRow][heroCol] == null) {  // Si le label n'existe pas, le créer
    pvlabels[heroRow][heroCol] = new JLabel();
    cases[heroRow][heroCol].add(pvlabels[heroRow][heroCol], BorderLayout.SOUTH);
}
pvlabels[heroRow][heroCol].setText(Integer.toString(potion.getpv()));
                    }else{
                        // Arme
                         int randompv = random.nextInt(41)+20;
                        Arme arme = new Arme(randompv);
                        armes[heroRow][heroCol]= arme; 
                        cases[heroRow][heroCol].setIcon(new ImageIcon("5.png"));
                        if(pvlabels[heroRow][heroCol] == null) {  // Si le label n'existe pas, le créer
    pvlabels[heroRow][heroCol] = new JLabel();
    cases[heroRow][heroCol].add(pvlabels[heroRow][heroCol], BorderLayout.SOUTH);
}
pvlabels[heroRow][heroCol].setText(Integer.toString(arme.getpv()));
                    }
                }

    heroRow = newHeroRow;
    heroCol = newHeroCol;
   // miseAJourLabelsPV();

}

// Mettre à jour l'affichage des points de vie
if(hero.getarme()==0){
healthLabel.setText("Points de vie : " + hero.getpv() + "  points du heros : " + hero.getpoints());
}else{
healthLabel.setText("Points de vie : " + hero.getpv() + "  points du heros : " + hero.getpoints() + "  votre arme a : "+hero.getarme()); 
}





if (hero.getpv() <= 0){

        fenetre.setVisible(false);
        
    JFrame fin = new JFrame("Vou n'avez plus de vie");
       fin.setSize(1000, 1000);
        fin.setLocation(500, 0);
        fin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fin.setVisible(true);
        JLabel findepartie = new JLabel("Merci d'avoir jouer vos points était de "+hero.getpoints());
        findepartie.setHorizontalAlignment(JLabel.CENTER);
        findepartie.setVerticalAlignment(JLabel.CENTER);
        fin.add(findepartie,BorderLayout.CENTER);
    }
}
}
