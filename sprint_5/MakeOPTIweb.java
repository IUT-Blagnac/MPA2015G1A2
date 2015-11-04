import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


public class MakeOPTIweb {

	public static void main(String[] args) {
		//Lecteur des fichier csv.
		LibCSV csv_reader= new LibCSV();
		
		//Arraylist contenant les données des fichiers csv.
		ArrayList<String[]> al_projet= csv_reader.Read("data/projets2014_2015.csv");
		ArrayList<String[]> al_etudiant= csv_reader.Read("data/etudiants2014_2015.csv");
		ArrayList<String[]> al_sujet= csv_reader.Read("data/sujets2014_2015.csv");
		ArrayList<String[]> al_intervenant= csv_reader.Read("data/intervenants2014_2015.csv");
		
		String html="";
		
		String header = "<!DOCTYPE html>\n<html>\n<head>\n<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />\n<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n<meta name=\"generator\" content=\"OPTIweb VOPTIweb\" />\n<title>0.1 - V0.1</title>\n<link rel=\"stylesheet\" href=\"http://netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.css\" />    <!-- 1 -->\n<link rel=\"stylesheet\" href=\"http://code.jquery.com/mobile/1.4.2/jquery.mobile-1.4.2.min.css\" /> \n<!-- 2 --><script src=\"http://code.jquery.com/jquery-1.9.1.min.js\"></script>    <!-- 3 -->\n<script src=\"http://code.jquery.com/mobile/1.4.2/jquery.mobile-1.4.2.min.js\"></script>    <!-- 4 -->\n<style type='text/css'>    /* 5 */\n@media all and (orientation:portrait) { .landscape {display: none;} }\n@media all and (orientation:landscape) { .landscape {display: inline;} }\n</style>\n</head>\n<body>";
		String footer = "<script>\n $( 'li[data-find]' ).on( 'click',function(event){\n  $(\"#autocomplete-input-projet\").val($(this).attr('data-find')).trigger('change');\n });\n</script>\n</body>\n</html>";
	
		//Page d'accueil
		String accueil = "<!-- DEBUT page accueil -->\n<div data-role=\"page\" id=\"accueil\" data-title=\"OPTIweb - V0.1\">\n<div data-role=\"header\" data-add-back-btn=\"true\">\n<h1>P<span class=\"landscape\">rojets </span>tut<span class=\"landscape\">orés</span> 2014-2015<br/>Département INFO<span class=\"landscape\">RMATIQUE</span><br/>IUT de Blagnac</h1>\n<a href=\"#credits\" data-theme=\"b\" class=\"ui-btn-right\">Crédits</a>   <!-- 1 -->\n</div>\n<div data-role=\"content\">\n<ul data-role=\"listview\" data-inset=\"true\" id=\"listeSources\">\n  <li><a href=\"#projets\"><i class=\"fa fa-tasks\"></i> Projets</a></li>   <!-- 1 -->\n  <li><a href=\"#sujets\"><i class=\"fa fa-copy\"></i> Sujets</a></li>   <!-- 1 -->\n  <li><a href=\"#etudiants\"><i class=\"fa fa-group\"></i> Etudiants</a></li>   <!-- 1 -->\n  <li><a href=\"#intervenants\"><i class=\"fa fa-group\"></i> Intervenants</a></li>   <!-- 1 -->\n</ul>\n</div>\n<div data-role=\"footer\">\n <h4>OPTIweb V<span class=\"landscape\">ersion </span>0.1 <i class=\"fa fa- fa-2x\"></i></h4>\n</div>\n</div>\n<!-- FIN page accueil -->";
		
		
		
		//Page projets
		String debutProjet = "<!-- DEBUT page projets -->\n<div data-role=\"page\" id=\"projets\" data-title=\"OPTIweb - V0.1\">\n<div data-role=\"header\" data-add-back-btn=\"true\">\n<h1>Projets 2014-2015</h1>\n</div>\n<div data-role=\"content\">\n<form class=\"ui-filterable\">\n<input id=\"autocomplete-input-projet\" name=\"projet\" data-type=\"search\" placeholder=\"Vous cherchez ?...\">   <!-- 1 -->\n</form>\n<ol id=\"listeprojets\" data-role=\"listview\" data-inset=\"true\" data-filter=\"true\" data-filter-reveal=\"false\" data-input=\"#autocomplete-input-projet\">";
		
		//Récupération des infos projets du CSV
	
		for(int i=1; i<al_projet.size();i++){
			debutProjet+="\n<li>\n<p>\n<b>["+al_sujet.get(new Integer(al_projet.get(i)[2]))[1]+"]</b>"+al_sujet.get(new Integer(al_projet.get(i)[2]))[2]+"</p>\n<p>\n<b>Client :</b> "+al_intervenant.get(new Integer(al_projet.get(i)[3]))[1]+" "+al_intervenant.get(new Integer(al_projet.get(i)[3]))[2]+"</p>\n<p>\n<b>Superviseur :</b> "+al_intervenant.get(new Integer(al_projet.get(i)[4]))[1]+" "+al_intervenant.get(new Integer(al_projet.get(i)[4]))[2]+"</p>\n<p>\n<b>Groupe "+al_projet.get(i)[1]+":</b> ";
			for(int j=0;j<al_etudiant.size();j++){
				if(al_etudiant.get(j)[0].equals(al_projet.get(i)[1])){
					debutProjet+=al_etudiant.get(j)[3].substring(0,al_etudiant.get(j)[3].length()-1)+" "+al_etudiant.get(j)[2]+" - ";
				}
			}
			debutProjet+="\n</p>\n</li>";
		}
		
		//System.out.print(debutProjet);		
		
		String finProjet = "</ol>\n</div>\n<div data-role=\"footer\">\n <h4>OPTIweb V<span class=\"landscape\">ersion </span>0.1 <i class=\"fa fa-tasks fa-2x\"></i></h4>\n</div>\n</div>\n<!-- FIN page projets -->";
	
		
		
		
		//Page sujet
		String debutSujet = "<!-- DEBUT page sujets -->\n<div data-role=\"page\" id=\"sujets\" data-title=\"OPTIweb - V0.1\">\n<div data-role=\"header\" data-add-back-btn=\"true\">    <!-- 1 -->\n<h1>Sujets 2014-2015</h1>\n</div>\n<div data-role=\"content\">\n<form class=\"ui-filterable\">\n<input id=\"autocomplete-input-sujet\" name=\"sujet\" data-type=\"search\" placeholder=\"Vous cherchez ?\">    <!-- 2 -->\n</form>\n<ol id=\"listesujets\" data-role=\"listview\" data-inset=\"true\" data-filter=\"true\" data-filter-reveal=\"false\" data-input=\"#autocomplete-input-sujet\" data-divider-theme=\"b\" data-count-theme=\"a\">\n<li data-role=\"list-divider\">    <!-- 3 -->\nSujet<span class=\"ui-li-count\" title=\"Groupe\" style=\"right: 40px !important;\">Groupe</span>\n</li>"	;
		String finSujet = "</ol>\n</div>\n<div data-role=\"footer\">\n <h4>OPTIweb V<span class=\"landscape\">ersion </span>0.1 <i class=\"fa fa-copy fa-2x\"></i></h4>\n</div>\n</div>\n<!-- FIN page sujets -->\n";
		for (int i = 1; i < al_sujet.size(); i++) {
			String groupe="";
			for (int j = 1; j < al_projet.size(); j++) {
				if(al_sujet.get(i)[0].equals(al_projet.get(j)[2]))
					groupe+=""+al_projet.get(j)[1];
			}
			debutSujet+= "<li data-find=\"["+al_sujet.get(i)[1]+"]\">\n<a href=\"#projets\">["+al_sujet.get(i)[1]+"] <br/>\n<div style=\"white-space:normal;\">\n <span><b>"+al_sujet.get(i)[2]+"</b>\n</span><span class=\"ui-li-count\">"+groupe+"</span>\n</div>\n</a>\n</li>";
		}
		//System.out.println(debutSujet);
		
		
		//Page Etudiants
		String debutEtudiant = "<!-- DEBUT page étudiants -->\n<div data-role=\"page\" id=\"etudiants\" data-title=\"OPTIweb - V0.1\">\n<div data-role=\"header\" data-add-back-btn=\"true\">\n<h1>Etudiants 2014-2015</h1>\n</div>\n<div data-role=\"content\">\n<form class=\"ui-filterable\">\n<input id=\"autocomplete-input-etudiant\" name=\"etudiant\" data-type=\"search\" placeholder=\"Etudiant ou Groupe X\">\n</form>\n<ol id=\"listeetudiants\" data-role=\"listview\" data-inset=\"true\" data-filter=\"true\" data-filter-reveal=\"false\" data-input=\"#autocomplete-input-etudiant\" data-divider-theme=\"b\">\n<li data-role=\"list-divider\">    <!-- 3 -->\nEtudiant<span class=\"ui-li-count\" title=\"Groupe\" style=\"right: 40px !important;\">Groupe</span>\n</li>"	;
		String finEtudiant = "</ol>\n</div>\n<div data-role=\"footer\">\n <h4>OPTIweb V<span class=\"landscape\">ersion </span>0.1 <i class=\"fa fa-group fa-2x\"></i></h4>\n</div>\n</div>\n<!-- FIN page etudiants -->\n";
		for (int i = 1; i < al_etudiant.size(); i++) {
			debutEtudiant+= "<li data-find=\""+al_etudiant.get(i)[3]+" "+al_etudiant.get(i)[2]+"\">\n<a href=\"#projets\">"+al_etudiant.get(i)[2]+" "+al_etudiant.get(i)[3]+"\n<span class=\"ui-li-count\" title=\"Groupe\"> Groupe "+al_etudiant.get(i)[0]+"</span></a></li>";
		}
		
		//Page intervenants
		String debutIntervenants = "<!-- DEBUT page intervenants -->\n<div data-role=\"page\" id=\"intervenants\" data-title=\"OPTIweb - V0.1\">\n<div data-role=\"header\" data-add-back-btn=\"true\">\n<h1>Intervenants 2014-2015</h1>\n</div>\n<div data-role=\"content\">\n<form class=\"ui-filterable\">\n<input id=\"autocomplete-input-intervenant\" name=\"intervenant\" data-type=\"search\" placeholder=\"Intervenant\"></form><ul id=\"listeintervenants\" data-role=\"listview\" data-inset=\"true\" data-filter=\"true\" data-filter-reveal=\"false\" data-input=\"#autocomplete-input-intervenant\" data-divider-theme=\"b\"><li data-role=\"list-divider\">Intervenant<span class=\"ui-li-count\" style=\"right: 110px !important;\" title=\"Client\">Client</span><span class=\"ui-li-count\" title=\"Superviseur\">Superviseur</span><li>";
		String finIntervenants = "</ol>\n</div>\n<div data-role=\"footer\">\n <h4>OPTIweb V<span class=\"landscape\">ersion </span>0.1 <i class=\"fa fa-group fa-2x\"></i></h4>\n</div>\n</div>\n<!-- FIN page etudiants -->\n";
		for (int i = 1; i < al_intervenant.size(); i++) {
			int nbrClients = 0;
			int nbrSuper = 0;
			for (int j = 0; j < al_projet.size(); j++) {
				if(al_projet.get(j)[3].equals(al_intervenant.get(i)[0]))
					nbrClients++;
				if(al_projet.get(j)[4].equals(al_intervenant.get(i)[0]))
					nbrSuper++;
			}
			debutIntervenants+= "<li data-find=\""+al_intervenant.get(i)[2]+" "+al_intervenant.get(i)[1]+"\">\n<a href=\"#projets\">"+al_intervenant.get(i)[2]+" "+al_intervenant.get(i)[1]+"\n<span class=\"ui-li-count\" style=\"right: 120px !important;\" title=\"Client\">"+nbrClients+"</span><span class=\"ui-li-count\" title=\"Superviseur\">"+nbrSuper+"</span></a></li>";
		}
				
				
		html+=header;
		html+=accueil;
		html+=debutSujet;
		html+=finSujet;
		html+=debutProjet;
		html+=finProjet;
		html+=debutEtudiant;
		html+=finEtudiant;
		html+=debutIntervenants;
		html+=finIntervenants;
		
		
		 try
	        
	        {
	            FileWriter fw = new FileWriter ("OPTIweb.html");
	            fw.write(html);
	            fw.close();
	        }
	        catch (IOException exception)
	        {
	            System.out.println ("Erreur lors de la lecture : " + exception.getMessage());
	        }
		
	}
}