Documentation swagger utilisable quand le CRM_back est lancé : http://localhost:8080/swagger-ui/index.html#/

Pour lancer le projet :

Dans un terminal, aller dans le dossier racine "projet" ensuite faite la commande :

cd CRM_back

Puis

./gradlew bootRun

Voila le back est lancé. Maintenant pour le front :

Dans un terminal, aller dans le dossier racine "projet" ensuite faite la commande :

cd CRM

Puis :

npm install

npm run dev

Changement de la base pour passer de MySQL à h2 pour avoir la base en local afin de vous le rendre sans passer par un serveur :

Pour y accéder

Url quand le back est lancé : http://localhost:8080/h2-console

Dans la page login :

JDBC URL : jdbc:h2:file:./database/crm_database

User Name : sa

Password :

Et cliquer sur connect 
