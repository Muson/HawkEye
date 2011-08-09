<?php

	///////////////////////////////////////////////////
	//         HawkEye Interface Lang File           //
	//                 by oliverw92                  //
	///////////////////////////////////////////////////
	//         French Lang File by oliverw92         //
	///////////////////////////////////////////////////
	$lang = array(
					
					"pageTitle"  => "HawkEye Navigateur",
					"title" => "HawkEye",
					
					"filter" => array("title" => "Options de filtrage",
									  "players" => "Joueurs",
									  "xyz" => "XYZ",
									  "range" => "Gamme",
									  "keys" => "Mots-cl�s",
									  "worlds" => "Mondes",
									  "dFrom" => "Date de d�but",
									  "dTo" => "Date de fin",
									  "block" => "Block",
									  "search" => "Rechercher",
									  "exclude" => "Exclure les filtres"),
					
					"tips" => array("hideFilter" => "Afficher / Masquer les options de filtre",
									"hideResults" => "Afficher / Masquer les r�sultats",
									"actions" => "Actions � rechercher. Vous devez en s�lectionner au moins un",
									"password" => "Mot de passe pour utiliser le navigateur. Requis seulement si il a �t� d�fini",
									"players" => "(Facultatif) Liste des joueurs que vous souhaitez rechercher s�par�s par des virgules",
									"xyz" => "(Facultatif) Coordonn�es auxquelles vous souhaitez rechercher",
									"range" => "(Facultatif) Etendue des coordonn�es sp�cifi�es ci-dessus pour rechercher",
									"keys" => "(Facultatif) Liste des mots-cl�s s�par�s par des virgules",
									"worlds" => "(Facultatif) Liste des mondes s�par�s par des virgules. Laissez vide pour tous les mondes",
									"dFrom" => "(Facultatif) Date et heure de d�but de la p�riode de recherche",
									"dTo" => "(Facultatif) Date et heure de fin de la p�riode de recherche",
									"block" => "(Facultatif) Blocs � rechercher dans les �v�nements 'Block Break' et 'Block Place'",
									"reverse" => "Si elle est coch�e, les r�sultats seront dans l'ordre chronologique inverse. D�cochez la case pour afficher les journaux de conversation",
									"exclude" => "(Facultatif) liste des mots cl�s � exclure des r�sultats s�par�s par des virgules"),
						
					"actions" => array("Bloc cass�",
									   "Bloc pos�",
									   "Panneau plac�",
									   "Chat",
									   "Commande",
									   "Join",
									   "Quit",
									   "T�l�port",
									   "Seau de Lave",
									   "Seau d'eau",
									   "Ouverture coffre",
									   "Interaction porte",
									   "Mort PVP",
									   "Briquet",
									   "Levier",
									   "Bouton",
									   "Autres",
									   "Explosions",
									   "Combustion de bloc",
									   "Formation de neige",
									   "Tombe de feuilles",
									   "Mort Mob",
									   "Mort Autre",
									   "D�poser l'Article",
									   "Ramassage Article"),
					
					"results" => array("title" => "R�sultats",
									   "id" => "ID",
									   "date" => "Date",
									   "player" => "Joueur",
									   "action" => "Action",
									   "world" => "Monde",
									   "xyz" => "XYZ",
									   "data" => "Don��es"),
									   
					"login" => array("password" => "Mot de Passe: ",
									 "login" => "Login"),
					
					"messages" => array("clickTo" => "Cliquez sur Rechercher pour r�cup�rer des donn�es",
										"breakMe" => "Arr�tez d'essayer de me m\'arr�ter!",
									    "invalidPass" => "Mot de passe invalide!",
									    "noActions" => "Vous devez s�lectionner au moins 1 action � rechercher !",
									    "noResults" => "Aucun r�sultat correspondant � ces options",
									    "error" => "Erreur!",
									    "notLoggedIn" => "Vous n'�tes pas connect�!")
					
					);
	
	//Convert foreign characters to entities
	array_walk_recursive($lang, "ents");
	function ents(&$item, $key) {
		$item = htmlentities($item);
	}

?>