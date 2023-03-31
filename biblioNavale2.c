#include <stdio.h>
#include <stdlib.h>
#include <time.h>

void ajoutNavireAleatoire2(char tab[10][10], char b, int t){

	int a,j,x,r,y,k=0;
	/*Choisis un nombre aléatoire entre 1 et 2 pour horizontale 
	ou verticale*/
	a=rand()%2+1;
x=9-t;
a=rand()%x+1;
y=a;
r=rand()%9+1;
/*Si rand =1 alors horizontale, mais on vérifie d'abord que les cases 
choisis sont bien vides , si ce n'est pas le cas on choisis un autre nombre
aléatoire*/
if(a==1){
for(j=0;j<t;j++){
	if(tab[r][a]!=' '){
		j=0;
		a=rand()%x+1;
		r=rand()%9+1;
		k++;
	}else{
		a++;
	}
	}
}else{
	/*Pareil pour verticale , on vérifie que ce sois  bien vide*/
	for(j=0;j<t;j++){
	if(tab[a][r]!=' '){
		j=0;
		a=rand()%x+1;
		r=rand()%9+1;
		k++;
	}else{
		a++;
	}
	}
}
	if(k==0){
		a=y;
	}
	if(a==1){
		//on place alors horizontale

for(j=0;j<t;j++){
	tab[r][a]=b;
	a++;

}


	}else{
		//on place alors en verticale
	for(j=0;j<t;j++){
		tab[a][r]=b;
		a++;
	}
	}
}




char* navire2(int i){
	// En fonction de l'entier entrée, la fonction renverra la chaine de 
	// caractère correspondante.
	char x;
	if(i==0){
		return "Porte-Avion";
	}
	if(i==1){
		return "Croiseur";
	}
	if(i==2){
		return "Sous-Marin";
	}
	if(i==3){
		return "Mous-Sarin";
}
	if(i==4){
		return "Torpilleur";
}

}




int verif2(char tab[10][10]){
	int x,i,j;
	/* Ici, la boucle vérifie le nombre de case qui ne sont pas vide
	   Il doit y en avoir 17 normalement.
	   Si c'est bon on renvoie l'entier 1 sinon 0.*/
	for(i=1;i<10;i++){
		for(j=1;j<10;j++){
			if(tab[i][j]!=' '){
				x++;
			}
		}
	}
	if(x==17){
		x=1;
	}else{
		x=0;
	}
return x;
}


void affichePlateau2(char t[10][10]){
	int z,w;
	/*Boucle qui va afficher les 10 lignes du plateau*/
	for (z=0;z<10;z++){
		/*Affichage de la première ligne avec les caractères*/
    if(z==0){
    	for(w=0;w<10;w++){
    
    		if(w==0){
    			printf("_|");
    		}else{
    			printf("%d|", t[z][w]);
    		}
   		}
	}else{

		/*Affichage des lignes suivantes*/
		for(w=0;w<10;w++){
		printf("%c|", t[z][w]);
		}
	}

printf("\n");

}
}











void initPlateau2(char plat[10][10]){
		int i,k,m,r;
		char choix;
		srand(time(NULL));
    printf("\n");
    m='A';
    for (k=0;k<10;k++){
    	/*On créer la première ligne du tableau avec des caractères*/
    if(k==0){
    	for(i=0;i<10;i++){
    
    		plat[k][i]=i;

   		}
	}else{

		/*On fait ensuite les autres lignes*/
		for(i=0;i<10;i++){
			if(i==0){
			plat[k][i]=m;
		}else{
			plat[k][i]=' ';
		}
		}
		m++;
	}
 }
 /*Le joueur va pouvoir choisir de poser ses navires a la main ou 
 aléatoirement, si il rentre une autre lettre cela fait erreur*/

/*Ici on est obliger de vider le buffer puisque nous avons posé la question si on voulait joué
donc pour le joueur 1 il n'y aura rien d'anormal cependant il faudra donc appuyer sur entrée
pour le joueur 2 car cela va vider le buffer alors qu'il sera déja vide , nous ne savons pas
comment y remédier.*/
 while(getchar() != '\n');
 printf("Souhaitez placer vos navires à la (M)ain, ou (A)leatoirement ?\n");
 scanf("%c", &choix);
 while(choix!='M' && choix!='A'){
 	printf("Erreur !\n");
 	printf("Souhaitez placer vos navires à la (M)ain, ou (A)leatoirement ?\n");
 	while(getchar() != '\n');
 	scanf("%c", &choix);
 }
 /*Si il choisis alatoirement on va donc faire appel a la fonction
 AjouNavirealéatoire*/
 if(choix=='A'){
char n;
 	int l;
for(i=0;i<5;i++){
	/*Nous allons placer des lettresdifférentes en fonction 
	des différents navire*/
	if(i==0){
		n=5;
		l='P';
	}if(i==1){
		n=4;
		l='C';
	}if(i==2){
		n=3;
		l='S';
	}if(i==3){
		n=3;
		l='M';
	}if(i==4){
		n=2;
		l='T';
	}
ajoutNavireAleatoire2(plat,l,n);

}
 }else{
 	/*Le joueur choisis donc de placer ses navires a la main*/
	char lettre,a,b,virgule;
 	int c,h,longueur,j,r,z,u,caca;
 	int p=0;
 	lettre='A';
 	longueur=5;
 	printf("Vous devez placer vos navires.\n");
 	printf("Pour chaque navire, indiquez horizontal (h) ou vertical (v), suivi de la case\n");
 	printf("la plus au nord-ouest (ex : h,E4).\n");
 	/*Cela va afficher le plateau et va demander de placer 
 	les différents Navire*/
 	 	for(j=0;j<5;j++){
 	affichePlateau2(plat);
 	printf("\n");
 	while(getchar() != '\n');
 	if(j==3){
 	longueur=3;
 	lettre = 'M';
 	printf("Placez votre %c (longueur %d) :\n",lettre,longueur);
 	}else if(j==0){
 		lettre = 'P';
 	printf("Placez votre %c (longueur %d) :\n",lettre,longueur);
 }else if(j==4){
 	lettre = 'T';
 	printf("Placez votre %c (longueur %d) :\n",lettre,longueur);
 }else if(j==2){
 	lettre = 'S';
 	printf("Placez votre %c (longueur %d) :\n",lettre,longueur);
 }else if(j==1){
 	lettre = 'C';
 	printf("Placez votre %c (longueur %d) :\n",lettre,longueur);
 }
 	scanf("%c%c%c%d", &a,&virgule,&b,&c);
 	z=c+longueur;
 	/*Cette boucle cherche le numéro de la ligne pour la stocker 
 	dans une variable*/
 		for(h=0;h<10;h++){
			if(plat[h][0]==b){
			r=h;
			}
			
		}
		u=r;
		/*Nous vérifions que les cases choisis soit bien vide
		si c'est le cas p=0 et l'on place les bateaux sinon cela met erreur*/
		if(a=='h'){
		for(h=c;h<z;h++){
			if(plat[r][h]!=' '){
				p=1;
			}
		}
	}
	if(a=='v'){
		for(h=c;h<z;h++){
			if(plat[r][c]!=' '){
				p=1;
			
			}
				r++;
		}
	}
		r=u;	
if(p==0){
/*la variable z vérifie que le navire ne sorte pas du plateau
sinon renvoie Hors plateau*/
if(z<=10){
 	if(a=='h'){ 

		for(h=0;h<longueur;h++){
			plat[r][c]=lettre;
			c++;
		}

 	lettre++;
 	longueur--;
 }else if(a=='v'){

		for(h=0;h<longueur;h++){
			plat[r][c]=lettre;
			r++;
		}
 	lettre++;
 	longueur--;
 }else{
 		printf("erreur\n");
 		j--;
 	}
 
 }else{
 	printf("erreur , Hors plateaux \n");
 	j--;
 }

 }else{
 	printf("erreur , Hors plateaux\n");
 	p=0;
 	j--;
 }
}
}
/*On affiche le tableau complet*/
affichePlateau2(plat);
while(getchar() != '\n');
printf("\n");

}




void afficheduo2(char t[][10],char p[][10]){
	int z,w,a;
	a=0;
	/*On afffiche les deux plateaux cote a cote en répétant Deux fois les 
	lignes mais sépareer par des espaces et remplis avec les valeurs des deux joueurs*/
	for (z=0;z<10;z++){
    if(z==0){
    	//Première ligne du premier tableau
    	for(w=0;w<10;w++){
    
    		if(w==0){
    			printf("_|");
    		}else{
    			printf("%d|", t[z][w]);
    		}
   		}
   		//espace entre les deux tableau
   		printf("               ");
   		//première ligne du deuxième tableau
   		for(w=0;w<10;w++){
    
    		if(w==0){
    			printf("_|");
    		}else{
    			
    			printf("%d|", p[z][w]);
    		
    		}
   		}
	}else{

		//affiche les lignes du premier tableau avec les bateau touchés si il y en a
	for(w=0;w<10;w++){
			if(w==0){
			printf("%c|", t[z][w]);
			}else{
	if(t[z][w]!='.' && t[z][w]!='x'){
			if(t[z][w]!=' ' ){
    				printf(" |");
    			}else{
		printf("%c|", t[z][w]);
	}
	}else{
		printf("%c|", t[z][w]);
}
		}
	}
		//espace entre les deux tableau
		printf("               ");
		//affiche les lignes du deuxième tableau avec les bateaux touchés si il y en a
		for(w=0;w<10;w++){
			if(w==0){
			printf("%c|", p[z][w]);
			}else{
				if(p[z][w]!='.' && p[z][w]!='x'){
			if(p[z][w]!=' '){
    				printf(" |");
    			}else{
		printf("%c|", p[z][w]);
	}
}else{
		printf("%c|", p[z][w]);
}
		}
	}

	}

printf("\n");

}

}

int jouerJoueur2(char adv[][10]){
	char a;
	int b,x,h,r;
	scanf("%c%d", &a,&b);
	/*On vérifie qu'on a rentrer des valeurs correct*/
	if(b==0){
		x=-3;
	}else{
		/*On cherche lu numéro de ligne que l'on stock dans une variable*/
	 for(h=0;h<10;h++){
			if(adv[h][0]==a){
			r=h;
			}
		}
		/*On vérifie qu'on ne sorte pas du plateaux*/
		if(r>9 || b>9){
			x=-3;
		}else{
		/*Si le coup est a l'eau on met un point sinon on va faire varier
		x en fonction des différents navire toucher*/
		if(adv[r][b]==' '){
			adv[r][b]='.';
			x=-1;
		}
			if(adv[r][b]=='P'){
			adv[r][b]='x';
			x=0;
		}	if(adv[r][b]=='C'){
			adv[r][b]='x';
			x=1;
		}
			if(adv[r][b]=='S'){
			adv[r][b]='x';
			x=2;
		}	if(adv[r][b]=='M'){
			adv[r][b]='x';
			x=3;
		}
			if(adv[r][b]=='T'){
			adv[r][b]='x';
			x=4;
		}
		
}
	}
		printf("\n");
/*La fonction ci dessous permet de réinitialier notre scanf*/
while(getchar() != '\n');
	return x;

}