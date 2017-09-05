Projet démo pour comprendre les bases de Play Scala
=================

Petite application de gestion de cave

* Liste des bouteilles de la cave
* Ajout d'une bouteille à la cave en AJAX
* Détail d'une bouteille


Pré-requis
-----------------

Installation de la base de données postgres

Version locale :

    createdb -U eol -O eol 'cave-vin'

Version docker :

     docker exec -it psql /bin/bash -c "createdb -U eol -O eol 'cave-vin'"
     
Puis exécuter le script SQL `scripts/bouteilles.sql`


Lancement
-----------------

Version avec Postgresql installé en local :

    sbt run

Version avec Postgresql sur un conteneur docker nommé "psql" :

    sbt run -Dconfig.file=conf/docker.conf