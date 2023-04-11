#include <stdlib.h>
#include <stdio.h>
#include <time.h>
#include "biblioNavale.h"

int main(int argc, char const argv[])
{       
        /*Initialisation des premieres variables qui permettront de definir le gagnant*/
		srand(time(NULL));
        int w=0;
        int q=0;
        
        /*demande au joueurs si ils veulent jouer en recuperant le caractère Y pour jouer ou N pour ne pas jouer*/
        char i,t;
        printf("Bonjour, voulez-vous jouer a la Bataille Navale (Y)es ou (N)o\n");
        scanf("%c",&i);
        t=i;

        /*Si les joueurs tapent Y alors le jeu se lance sinon la boucle while de se lance pas*/
        while(i=='Y'&&t=='Y'){
        /* demande au premier joueur de placer 
        aleatoirememt ces pieces ou manuellement sur le plateau*/
        printf("Joueur 1 :\n");
        char t1[10][10];
        initPlateau(t1);
        printf("\n");
        /* demande au deuxieme joueur de placer 
        aleatoirememt ces pieces ou manuellement sur le plateau*/
        printf("Joueur 2 :\n");
        char T2[10][10];
        initPlateau(T2);
        printf("\n");
        /*Affiche les tableaux des deux joueurs avec les bateaux masques */
        afficheduo(t1,T2);
        printf("\n");
        /*Initialisation des variables pour afficher les bateaux coules*/
        int a=0;
        int b=0;
        int c=0;
        int d=0;
        int e=0;
        int f=0;
        int g=0;
        int h=0;
        int i=0;
        int j=0;
        /*Initialisation d'une variable qui permet de donner la main a un joueur*/
        int l=0;
        /*Creation d'une boucle qui s'arretera lorsque l'un des deux joueurs aura gagne*/
        while(w<17 && q<17){ 
            int o;
    /*Lorsaue l est pair le joeur 1 a la main, tandis que lorsque l est impair le joueur 2 a la main*/
            if(l%2==0){
        printf("Joueur 1 :\n");
        /*C'est le joueur 1 qui joue sur le plateau du joueur 2,
        on fonction de la case touche sur le plateau, la variable o aura 
        une valeur differente qui fera execute les differentes conditions*/
        o=jouerJoueur(T2);
        switch(o){
    case -1: printf("A l'eau\n");
    l++;
    break;

    case 0: printf("Toucher !\n");
    a++;
    w++;
    if(a==5){printf("Vous avez coulez le %s !\n", navire(0));}
    break;

    case 1: printf("Toucher !\n");
    b++;
    w++;
    if(b==4){printf("Vous avez coulez le %s !\n", navire(1));}
    break;

    case -3: printf("Hors plateau !\n");
    break;

    case 2: printf("Toucher !\n");
    c++;
    w++;
    if(c==3){printf("Vous avez coulez le %s !\n", navire(2));}
    break;

    case 3: printf("Toucher !\n");
    d++;
    w++;
    if(d==3){printf("Vous avez coulez le %s !\n", navire(3));}
    break;

    case 4: printf("Toucher !\n");
    e++;
    w++;
    if(e==2){printf("Vous avez coulez le %s !\n", navire(4));}
    break;
}
        afficheduo(t1,T2);

    }else{
        printf("Joueur 2 :\n");
        /*C'est le joueur 2 qui joue sur le plateau du joueur 1,
        on fonction de la case touche sur le plateau, la variable o aura 
        une valeur differente qui fera execute les differentes conditions*/
          o=jouerJoueur(t1);
        switch(o){
    case -1: printf("A l'eau\n");
    l++;
    break;

    case 0: printf("Toucher !\n");
    f++;
    q++;
    if(f==5){printf("Vous avez coulez le %s !\n", navire(0));}
    break;

    case 1: printf("Toucher !\n");
    g++;
    q++;
    if(g==4){printf("Vous avez coulez le %s !\n", navire(1));}
    break;

    case -3: printf("Hors plateau !\n");
    break;

    case 2: printf("Toucher !\n");
    h++;
    q++;
    if(h==3){printf("Vous avez coulez le %s !\n", navire(2));}
    break;

    case 3: printf("Toucher !\n");
    i++;
    q++;
    if(i==3){printf("Vous avez coulez le %s !\n", navire(3));}
    break;

    case 4: printf("Toucher !\n");
    j++;
    q++;
    if(j==2){printf("Vous avez coulez le %s !\n", navire(4));}
    break;
}
         /*Apres avoir jouer, les deux tableau se 
        reaffiche avec les coups de l'adversaire placer desssus*/
        afficheduo(t1,T2);
    }
printf("\n");
}   
        /*Affiche le gagnant selon w a la fin de la condition de la boucle "w<17 || q<17"*/
        printf("\n");
        if(w==17){
            printf("Le Joueur 1 a gagner ! \n");
        }else{
            printf("Le Joueur 2 a gagner ! \n");
        }
        while(getchar()!='\n');
        printf("Voulez-vous rejouer ? (Y)es ou (N)o\n");
        scanf("%c",&t);
        w=0;
        q=0;
    }
    printf("Merci à la prochaine fois !\n");
        return 0;
    }
