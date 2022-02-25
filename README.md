# IN203_BatailleNavale

Vous trouverez dans ce fichier mon projet pour le cours de IN203. 
Pour le compiler il suffit de se placer dans le dossier bataille-navale-squelette et utiliser la commande :  mvn clean install exec:java 
Il y a deux modes de jeu différent : joueur vs AI et joueur vs joueur. Dans le main du testboard actuel c'est le mode multijoueur qui est utilisé. Si vous voulez jouer contre une IA
il faut remplacer game = game.init(); par game = game.init_vs_AI() et game.run(); par game.run_vs_AI();


Une fois le jeu lancer il suffit de placer les bateaux à l'aide d'une entrée de la forme  : A0 north, une fois tout les bateaux installés pour attaquer il suffit d'entrée l'attaque
sous la forme : A0. Si on tente t'attaquer à un endroit ou on a déjà attaqué le jeu demandera à rejouer le coup de même si on est out of bound il faudra entrer à nouveau une frappe.
