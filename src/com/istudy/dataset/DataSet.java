package com.istudy.dataset;

public class DataSet {
	
	//update variables with new library name
	//follow android naming convention
	public static String[] themeIdArray = {"mustaches","city_skylines","aliens","art","superheroes","usflags","dogs","microscopic","animal_patterns","cartoons","celeb_yearbook"};
	
	public static String[] themeTitleArray = {"Famous Mustaches","City SkyLines","Aliens","Art & Artists","Super Heroes","US Flags","Dogs","Micro- scopic","Animal Patterns","Cartoons","Celebrities"};
	
	// In future they will be updated by web service to reflect trending albums
	// Currently same as themeIdArray
	public static String[] trendArray = themeIdArray;
	
	// Add a new row for each library
	// Names of actual images (follow android naming convention)
	public static String[][] themes = new String[][]{
		{"chaplin","einstein","dali","frank_zappa","hulk_hogan","jhonny_depp","prince","sasha_baron","john_waters","will_ferrel"},
		{"amsterdam","chicago","dubai","hongkong","london","newyork","pittsburgh","prague","sanfrancisco","seattle","sydney","tokyo","venice"},
		{"fifthelement","alf","alien","et","hgttg","ij","marsattacks","mib","sw","wow"},
		{"bosch","goya","kahlo","klimt","picasso","pollock","titian","vg","warhol","wyeth"},
		{"batman","captain","flash","gambit","hulk","ironman","spiderman","storm","superman","wonder"},
		{"ar","az","ca","fl","la","md","nc","ok","ri","wa"},		
		{"aussie","beagle","dalmatian","german","golden","labrador","poodle","rottweiler","sanbernardo","snauzer"},
		{"eyelashes","guitarstring","mascara_brush","needle_thread","saltpepper","toothbrush","tp","used_dental_floss","velcro","vinyllp"},
		{"fish","giraffe","cheetah","peacock","snake","tiger","turkey"},
		{"arnold","bender","cartman","charlie_brown","doug","homer","krumm","rocko","stewie"},
		{"ap","eb","gwb","ow","rdj","rz","sp","zd"}
	};
	
			
	}
