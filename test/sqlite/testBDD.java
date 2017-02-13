package sqlite;
import java.util.ArrayList;

import javax.swing.DefaultBoundedRangeModel;

import sqlite.BDD;

public class testBDD {
	
	//Creation d'une base sous sqlite
	public void main(String []args){
		creation_table("demo.db");
		insertion_table("demo.db","url_test_1");
		insertion_table("demo.db","url_test_2");
		insertion_table("demo.db","url_test_3");
		List<String> res = new ArrayList<>();
		res = selection_tout_table("demo.db");
		imprimer_table(res);
	}
	
	/*// POUR TESTER;
	public static void main(String []args){
		creation_table("demo.sqlite");
		insertion_table("demo.sqlite","url_test_13","src/date","1997-07-07","lol");
		insertion_table("demo.sqlite","url_test_14","src/date","1997-07-07","lol");
		insertion_table("demo.sqlite","url_test_15","src/date","1997-07-07","lol");
		List<FichierPly> res = new ArrayList<>();
		res = selection_tout_table("demo.sqlite");
		imprimer_table(res);
		System.out.println("-----------------");
		suppression_table("demo.sqlite","url_test_10");
		res = selection_tout_table("demo.sqlite");
		imprimer_table(res);
		FichierPly file = selection_unique_table("demo.sqlite","url_test_15");
		System.out.println(file.toString());
		System.out.println("-----------------");
		suppression_table("demo.sqlite","url_test_10");
		res = selection_tout_table("demo.sqlite");
		imprimer_table(res);
		FichierPly file = selection_unique_table("demo.sqlite","url_test_15");
		System.out.println(file.toString());
	}*/
}
