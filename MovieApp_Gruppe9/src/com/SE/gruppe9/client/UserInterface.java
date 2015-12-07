package com.SE.gruppe9.client;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.gwtext.client.widgets.Panel;
import com.gwtext.client.widgets.layout.HorizontalLayout;
import com.gwtext.client.widgets.layout.LayoutData;
import com.gwtext.client.widgets.layout.VerticalLayout;

public class UserInterface {

	// Arrays to get the data for the Listboxes
	private final String[] BoxOfficeRevenue = { "Box Office Revenue", "≤ 100'000", "100'000 - 1'000'000", "≥ 1'000'000" };
	private final String[] Runtime = { "Runtime", "≤ 60", "≤ 90", "> 90" };
	private String[] allLanguages = {"Aboriginal Malay languages", "Aceh Language", "Adamawa Language", "Afrikaans Language", "Akan Language", "Albanian language", "Algonquin Language", "American English", "American Sign Language", "Amharic Language", "Ancient", "Ancient Greek", "Apache", "Arabic Language", "Aramaic language", "Armenian Language", "Assamese Language", "Assyrian language", "Assyrian Neo-Aramaic Language", "Australian Aboriginal Pidgin English", "Australian English", "Awadhi Language", "Azerbaijani language", "Bambara language", "Banyumasan language", "Belarusian language", "Bengali Language", "Bhojpuri Language", "Bosnian language", "Brazilian Portuguese", "Bulgarian Language", "Burmese Language", "Cantonese", "Catalan language", "Cebuano language", "Central Language", "Chadian Arabic", "Chechen Language", "Chewa language", "Cheyenne Language", "Chhattisgarhi Language", "Chinese", "Chinese language", "Classical Arabic", "Corsican Language", "Cree language", "Croatian language", "Crow Language", "Czech Language", "Danish Language", "Dari", "Deutsch", "Dutch Language", "Dzongkha Language", "Egyptian", "Egyptian Arabic", "English Language", "Esperanto Language", "Estonian Language", "Farsi", "Filipino language", "Finnish Language", "Flemish language", "French Language", "French Sign Language", "Frisian", "Friulian Language", "Fula language", "Fulfulde", "Gaelic", "Galician Language", "Georgian Language", "German", "German Language", "Greek Language", "Guanzhong Hua", "Gujarati Language", "Gumatj Language", "Hainanese", "Hakka Language", "Hariyani", "Haryanvi Language", "Hausa Language", "Hawaiian language", "Hazaragi Language", "Hebrew Language", "Hiligaynon language", "Hindi Language", "Hindustani language", "Hinglish", "Hmong language", "Hokkien", "Hopi Language", "Hungarian language", "Hungary", "Icelandic Language", "Indian English", "Indonesian Language", "Inuktitut", "Irish", "Italian", "Italian Language", "Jamaican Creole English Language", "Japan", "Japanese Language", "Jinyu Language", "Judeo-Georgian Language", "Kannada Language", "Khmer", "Khmer language", "Kinyarwanda language", "Klingon language", "Korean Language", "Korean Sign Language", "Krio Language", "Kriolu", "Kuna language", "Kurdish language", "Latin Language", "Lithuanian language", "Luxembourgish language", "M\u0101ori language", "Maasai Language", "Macedonian Language", "Malay Language", "Malayalam Language", "Mandarin Chinese", "Maninka language", "Marathi Language", "Maya", "Mende Language", "Min Nan", "Mohawk Language", "Mongolian language", "Nahuatl languages", "Napoletano-Calabrese Language", "Navajo Language", "Nepali Language", "North Language", "Northeastern Language", "Norwegian Language", "Old English language", "Oriya Language", "Osetin Language", "Palawa kani", "Papiamento language", "Pashto language", "Pawnee Language", "Persian Language", "Picard Language", "Plautdietsch Language", "Polish Language", "Portuguese Language", "Punjabi language", "Quechua", "Rajasthani language", "Romani language", "Romanian Language", "Russian Language", "Saami", "Sami languages", "Sanskrit Language", "Scanian Language", "Scottish Gaelic language", "Serbian language", "Serbo-Croatian", "Shanghainese", "Shanxi", "Sicilian Language", "Silent film", "Sinhala Language", "Sioux language", "Slovak Language", "Slovenian language", "Somali Language", "Sotho language", "South African English", "Southwestern Mandarin", "Spanish Language", "Standard Cantonese", "Standard Mandarin", "Standard Tibetan", "Sumerian", "Sunda Language", "Swahili Language", "Swedish Language", "Swiss German Language", "Tagalog language", "Taiwanese", "Tamang language", "Tamil Language", "Telugu language", "Teochew", "Thai", "Thai Language", "Tibetan languages", "Tok Pisin Language", "Tulu Language", "Turkish Language", "Tzotzil language", "Ukrainian Language", "Urdu Language", "Uzbek language", "Venetian Language", "Vietnamese Language", "Welsh Language", "Western Language", "Wolof Language", "Xhosa Language", "Yiddish Language", "Yolngu Matha", "Yucat\u00e1n Language", "Zulu Language"};
	static Map<String, String> allCountries = new HashMap<String, String>();
	private String[] allGenres = {"Absurdism", "Acid western", "Action", "Action Comedy", "Action Thrillers", "Action/Adventure", "Addiction Drama", "Adult", "Adventure", "Adventure Comedy", "Airplanes and airports", "Albino bias", "Alien Film", "Alien invasion", "Americana", "Animal Picture", "Animals", "Animated cartoon", "Animated Musical", "Animation", "Anime", "Anthology", "Anthropology", "Anti-war", "Anti-war film", "Apocalyptic and post-apocalyptic fiction", "Archaeology", "Archives and records", "Art film", "Auto racing", "Avant-garde", "B-movie", "B-Western", "Backstage Musical", "Baseball", "Beach Film", "Beach Party film", "Bengali Cinema", "Biker Film", "Biographical film", "Biography", "Biopic [feature]", "Black comedy", "Black-and-white", "Blaxploitation", "Bloopers & Candid Camera", "Bollywood", "Boxing", "Breakdance", "British Empire Film", "British New Wave", "Bruceploitation", "Buddy cop", "Buddy film", "Buddy Picture", "Business", "C-Movie", "Camp", "Caper story", "Cavalry Film", "Chase Movie", "Chick flick", "Childhood Drama", "Children's", "Children's Entertainment", "Children's Fantasy", "Children's Issues", "Children's/Family", "Chinese Movies", "Christian film", "Christmas movie", "Clay animation", "Cold War", "Combat Films", "Comdedy", "Comedy", "Comedy film", "Comedy horror", "Comedy of Errors", "Comedy of manners", "Comedy Thriller", "Comedy Western", "Comedy-drama", "Coming of age", "Coming-of-age film", "Computer Animation", "Computers", "Concert film", "Conspiracy fiction", "Costume Adventure", "Costume drama", "Costume Horror", "Courtroom Comedy", "Courtroom Drama", "Creature Film", "Crime", "Crime Comedy", "Crime Drama", "Crime Fiction", "Crime Thriller", "Cult", "Culture & Society", "Cyberpunk", "Czechoslovak New Wave", "Dance", "Demonic child", "Detective", "Detective fiction", "Disaster", "Docudrama", "Documentary", "Dogme 95", "Domestic Comedy", "Doomsday film", "Drama", "Dystopia", "Ealing Comedies", "Early Black Cinema", "Education", "Educational", "Ensemble Film", "Environmental Science", "Epic", "Epic Western", "Erotic Drama", "Erotic thriller", "Erotica", "Escape Film", "Essay Film", "Existentialism", "Experimental film", "Exploitation", "Expressionism", "Extreme Sports", "Fairy tale", "Family & Personal Relationships", "Family Drama", "Family Film", "Family-Oriented Adventure", "Fan film", "Fantasy", "Fantasy Adventure", "Fantasy Comedy", "Fantasy Drama", "Feature film", "Female buddy film", "Feminist Film", "Fictional film", "Filipino", "Filipino Movies", "Film", "Film & Television History", "Film \u00e0 clef", "Film adaptation", "Film noir", "Film-Opera", "Filmed Play", "Finance & Investing", "Foreign legion", "Future noir", "Gangster Film", "Gay", "Gay Interest", "Gay pornography", "Gay Themed", "Gender Issues", "Giallo", "Glamorized Spy Film", "Goat gland", "Gothic Film", "Graphic & Applied Arts", "Gross out", "Gross-out film", "Gulf War", "Hagiography", "Hardcore pornography", "Haunted House Film", "Health & Fitness", "Heaven-Can-Wait Fantasies", "Heavenly Comedy", "Heist", "Hip hop movies", "Historical Documentaries", "Historical drama", "Historical Epic", "Historical fiction", "History", "Holiday Film", "Homoeroticism", "Horror", "Horror Comedy", "Horse racing", "Humour", "Hybrid Western", "Illnesses & Disabilities", "Indian Western", "Indie", "Inspirational Drama", "Instrumental Music", "Interpersonal Relationships", "Inventions & Innovations", "Japanese Movies", "Journalism", "Jukebox musical", "Jungle Film", "Juvenile Delinquency Film", "Kafkaesque", "Kitchen sink realism", "Language & Literature", "Latino", "Law & Crime", "Legal drama", "LGBT", "Libraries and librarians", "Linguistics", "Live action", "Malayalam Cinema", "Marriage Drama", "Martial Arts Film", "Master Criminal Films", "Media Satire", "Media Studies", "Medical fiction", "Melodrama", "Mockumentary", "Mondo film", "Monster", "Monster movie", "Movie serial", "Movies About Gladiators", "Mumblecore", "Music", "Musical", "Musical comedy", "Musical Drama", "Mystery", "Mythological Fantasy", "Natural disaster", "Natural horror films", "Nature", "Neo-noir", "Neorealism", "New Hollywood", "New Queer Cinema", "News", "Ninja movie", "Northern", "Nuclear warfare", "Operetta", "Outlaw", "Outlaw biker film", "Parkour in popular culture", "Parody", "Patriotic film", "Period Horror", "Period piece", "Pinku eiga", "Plague", "Point of view shot", "Political cinema", "Political Documetary", "Political drama", "Political satire", "Political thriller", "Pornographic movie", "Pornography", "Pre-Code", "Prison", "Prison escape", "Prison film", "Private military company", "Propaganda film", "Psycho-biddy", "Psychological horror", "Psychological thriller", "Punk rock", "Race movie", "Reboot", "Religious Film", "Remake", "Revenge", "Revisionist Fairy Tale", "Revisionist Western", "Road movie", "Road-Horror", "Roadshow theatrical release", "Roadshow/Carny", "Rockumentary", "Romance Film", "Romantic comedy", "Romantic drama", "Romantic fantasy", "Romantic thriller", "Samurai cinema", "Satire", "School story", "Sci Fi Pictures original films", "Sci-Fi Adventure", "Sci-Fi Horror", "Sci-Fi Thriller", "Science Fiction", "Science fiction Western", "Screwball comedy", "Sex comedy", "Sexploitation", "Short Film", "Silent film", "Silhouette animation", "Singing cowboy", "Slapstick", "Slasher", "Slice of life story", "Social issues", "Social problem film", "Softcore Porn", "Space opera", "Space western", "Spaghetti Western", "Splatter film", "Sponsored film", "Sports", "Spy", "Stand-up comedy", "Star vehicle", "Statutory rape", "Steampunk", "Stoner film", "Stop motion", "Superhero", "Superhero movie", "Supermarionation", "Supernatural", "Surrealism", "Suspense", "Swashbuckler films", "Sword and Sandal", "Sword and sorcery", "Sword and sorcery films", "Tamil cinema", "Teen", "Television movie", "The Netherlands in World War II", "Therimin music", "Thriller", "Time travel", "Tokusatsu", "Tollywood", "Tragedy", "Tragicomedy", "Travel", "Vampire movies", "War effort", "War film", "Werewolf fiction", "Western", "Whodunit", "Women in prison films", "Workplace Comedy", "World cinema", "World History", "Wuxia", "Z movie", "Zombie Film"};
	static Map<String, String> allYC = new HashMap<String, String>();
	
	private DataImportServiceAsync filter = GWT.create(DataImportService.class);
	
	// all Buttons Tetxtbox and Listboxes
	private final Button goButton = new Button("GO!");
	private final TextBox searchMovieField = new TextBox();
	static ListBox listBoxYear = new ListBox();
	private final ListBox listBoxOffice = new ListBox();
	private final ListBox listBoxRuntime = new ListBox();
	private final ListBox listBoxLanguage = new ListBox();
	private final ListBox listBoxCountry = new ListBox();
	private final ListBox listBoxGenre = new ListBox();
	private final Button deleteButton = new Button("Delete");
	private final Button exportTabelButton = new Button("export Table");

	private Panel hPanel = new Panel();
	private Panel h1Panel = new Panel();
	private Panel vPanel = new Panel();
	private Table table = new Table();
	private Export export = new Export();
	final static RootLayoutPanel rp = RootLayoutPanel.get();

	private int count = 0;
	
	/**
	 * get all countries
	 * @return
	 */
	public static Map<String, String> getAllCountries(){
		return allCountries;
	}
	
	/**
	 * get all countries and years
	 * @return
	 */
	public static Map<String, String> getAllYC(){
		return allYC;
	}
	/**
	 * get horizontale Panel with the added widgets
	 * @return
	 */
	public Panel getHPanel() {
		return hPanel;
	}

	/**
	 * get vertival Panel with the added widget
	 * @return
	 */
	public Panel getVPanel() {
		return vPanel;
	}

	/**
	 * Create all Buttons, Listboxes, TextBox
	 */
	private void createAll() {

		hPanel.setLayout(new HorizontalLayout(10));
		vPanel.setLayout(new VerticalLayout());
		h1Panel.setLayout(new HorizontalLayout(10));
		hPanel.setBorder(false);
		vPanel.setBorder(false);
		h1Panel.setBorder(false);
		rp.setVisible(false);

		// Textbox for movie name
		searchMovieField.setText("Entre a movie name");
		hPanel.add(searchMovieField);

		// go button for movie name
		hPanel.add(goButton);

		// listbox for release year
		listBoxYear.addItem("Year");
		for (int i = 2016; i >= 1888; i--) {
			listBoxYear.addItem(Integer.toString(i));
			}
		listBoxYear.setVisibleItemCount(1);
		hPanel.add(listBoxYear);

		// listbox for box office revenue
		for (int i = 0; i < BoxOfficeRevenue.length; i++) {
			listBoxOffice.addItem(BoxOfficeRevenue[i]);
		}
		listBoxOffice.setVisibleItemCount(1);
		hPanel.add(listBoxOffice);

		// listbox for runtime
		for (int i = 0; i < Runtime.length; i++) {
			listBoxRuntime.addItem(Runtime[i]);
		}
		listBoxRuntime.setVisibleItemCount(1);
		hPanel.add(listBoxRuntime);
	
		// exportButton
		hPanel.add(exportTabelButton);
		
		// listbox for language
		importAllEntries();
		listBoxLanguage.addItem("Language");
		for (int i = 0; i < allLanguages.length; i++){
			listBoxLanguage.addItem(allLanguages[i]);
		}
		listBoxLanguage.setVisibleItemCount(1);
		h1Panel.add(listBoxLanguage);

		// listbox for country
		listBoxCountry.addItem("Country");
		listBoxCountry.setVisibleItemCount(1);
		h1Panel.add(listBoxCountry);

		// listbox for genre
		listBoxGenre.addItem("Genre");
		for (int i = 0; i < allGenres.length; i++){
			listBoxGenre.addItem(allGenres[i]);
		}
		listBoxGenre.setVisibleItemCount(1);
		h1Panel.add(listBoxGenre);
		
		// button to delete filter
		h1Panel.add(deleteButton);

		vPanel.add(h1Panel);
	}

	/**
	 * all change and click events
	 * 
	 * @return
	 */
	public Table choseEvents() {
		createAll();
		searchMovieField.addKeyDownHandler(new KeyDownHandler() {
			public void onKeyDown(KeyDownEvent event) {
				if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {

					String itemStringSelected = searchMovieField.getValue();

					if (count < 1) {
						table.firstFilter(itemStringSelected, 2);
					} else {
						table.clearFlexTable();
						table.setFlexTableHeader();
						table.secondFilter(itemStringSelected, 0);
					}
					count++;

				}
			}
		});

		goButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {

				String itemStringSelected = searchMovieField.getValue();

				if (count < 1) {
					table.firstFilter(itemStringSelected, 2);
				} else {
					table.clearFlexTable();
					table.setFlexTableHeader();
					table.secondFilter(itemStringSelected, 0);
				}
				count++;

			}
		});

		// change event for listbox year
		listBoxYear.addChangeHandler(new ChangeHandler() {
			public void onChange(ChangeEvent event) {

				// Get the index of the selected Item
				int itemSelected = listBoxYear.getSelectedIndex();

				// Get the string value of the item that has been selected
				String itemStringSelected = listBoxYear.getValue(itemSelected);
				if (count < 1) {
					table.firstFilter(itemStringSelected, 3);
				} else {
					table.clearFlexTable();
					table.setFlexTableHeader();
					table.secondFilter(itemStringSelected, 1);
				}
				count++;
			}

		});

		// change event for listbox box office revenue
		listBoxOffice.addChangeHandler(new ChangeHandler() {
			public void onChange(ChangeEvent event) {

				// Get the index of the selected Item
				int itemSelected = listBoxOffice.getSelectedIndex();

				// Get the string value of the item that has been selected
				String itemStringSelected = listBoxOffice
						.getValue(itemSelected);
				if (count < 1) {
					table.firstFilter(itemStringSelected, 4);
				} else {
					table.clearFlexTable();
					table.setFlexTableHeader();
					table.secondFilter(itemStringSelected, 2);
				}
				count++;
			}

		});

		// change event for listbox runtime
		listBoxRuntime.addChangeHandler(new ChangeHandler() {
			public void onChange(ChangeEvent event) {

				// Get the index of the selected Item
				int itemSelected = listBoxRuntime.getSelectedIndex();

				// Get the string value of the item that has been selected
				String itemStringSelected = listBoxRuntime
						.getValue(itemSelected);
				if (count < 1) {
					table.firstFilter(itemStringSelected, 5);
				} else {
					table.clearFlexTable();
					table.setFlexTableHeader();
					table.secondFilter(itemStringSelected, 3);
				}
				count++;
			}
		});

		// change event for listbox language
		listBoxLanguage.addChangeHandler(new ChangeHandler() {
			public void onChange(ChangeEvent event) {

				// Get the index of the selected Item
				int itemSelected = listBoxLanguage.getSelectedIndex();

				// Get the string value of the item that has been selected
				String itemStringSelected = listBoxLanguage
						.getValue(itemSelected);
				if (count < 1) {
					table.firstFilter(itemStringSelected, 6);
				} else {
					table.clearFlexTable();
					table.setFlexTableHeader();
					table.secondFilter(itemStringSelected, 4);
				}
				count++;
			}
		});

		// change event for listbox country
		listBoxCountry.addChangeHandler(new ChangeHandler() {
			public void onChange(ChangeEvent event) {

				// Get the index of the selected Item
				int itemSelected = listBoxCountry.getSelectedIndex();

				// Get the string value of the item that has been selected
				String itemStringSelected = listBoxCountry
						.getValue(itemSelected);
				if (count < 1) {
					table.firstFilter(itemStringSelected, 7);
				} else {
					table.clearFlexTable();
					table.setFlexTableHeader();
					table.secondFilter(itemStringSelected, 5);
				}
				count++;
			}
		});

		// change event for listbox genre
		listBoxGenre.addChangeHandler(new ChangeHandler() {
			public void onChange(ChangeEvent event) {

				// Get the index of the selected Item
				int itemSelected = listBoxGenre.getSelectedIndex();

				// Get the string value of the item that has been selected
				String itemStringSelected = listBoxGenre.getValue(itemSelected);
				if (count < 1) {
					table.firstFilter(itemStringSelected, 8);
				} else {
					table.clearFlexTable();
					table.setFlexTableHeader();
					table.secondFilter(itemStringSelected, 6);
				}
				count++;
			}
		});
		
		// click event for delete Button 
		deleteButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				count = 0;
				table.clearFlexTable();
				table.clearMap();
				listBoxYear.setSelectedIndex(0);
				listBoxOffice.setSelectedIndex(0);
				listBoxRuntime.setSelectedIndex(0);
				listBoxLanguage.setSelectedIndex(0);
				listBoxCountry.setSelectedIndex(0);
				listBoxGenre.setSelectedIndex(0);
				searchMovieField.setText("Entre a movie name");
				table.setFlexTableHeader();
			}
		});
		
		// click event for export Button
		exportTabelButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				rp.setVisible(true);
				rp.add(export.export());
			}
		});
		return table;
	}
	
	/**
	 * get all filter options
	 */
	public void importAllEntries() {
		
		// Initialize the service proxy.
		if (filter == null) {
			filter = GWT.create(DataImportService.class);
		}
		// Set up the callback object.
		final AsyncCallback<Map<String, String>> callback = new AsyncCallback<Map<String, String>>() {
			public void onFailure(Throwable caught) {

			}

			public void onSuccess(Map<String, String> result) {
				String[] movies;
				
				// all years and countries in HashMap
				for (Map.Entry<String, String> entry : result.entrySet()) {
					movies = entry.getValue().split("==");
					
					allYC.put(entry.getKey(), movies[0] + "==" + movies[1]);
				}
//				
//				// all years in HashMap - no Duplicates
//				for (Map.Entry<String, String> entry : result.entrySet()) {
//					movies = entry.getValue().split("==");
//					
//					if (movies[0].isEmpty() == false) {
//						allYears.put(movies[0], "");
//					}
//				}
//				// all Languages in HashMap - no Duplicates
//				for (Map.Entry<String, String> entry : result.entrySet()) {
//					movies = entry.getValue().split("==");
//					
//					String[] tmp = movies[1].split(", ");
//
//					int i = 0;
//					while (i < tmp.length) {
//						if (!tmp[i].equals("{}") && !tmp[i].equals("France") && tmp[i].length() < 40) {
//							allLanguages.put(tmp[i], "");
//						}
//						i++;
//					}
//				}
				
				// all Countries in HashMap - no Duplicates
				for (Map.Entry<String, String> entry : result.entrySet()) {
					movies = entry.getValue().split("==");

					String[] tmp = movies[1].split(", ");

					int i = 0;
					while (i < tmp.length) {
						if (!tmp[i].equals("{}") && !tmp[i].contains("Language") && !tmp[i].equals("Crime")) {
							allCountries.put(tmp[i], "");
						}
						i++;
					}
				}
				
//				// all Genres in HashMap - no Duplicates
//				for (Map.Entry<String, String> entry : result.entrySet()) {
//					movies = entry.getValue().split("==");
//					
//					String[] tmp = movies[3].split(", ");
//					
//					int i = 0;
//					while (i < tmp.length) {
//						if (!tmp[i].equals("{}")) {
//							allGenres.put(tmp[i], "");
//						}
//						i++;
//					}
//				}
//				
//				// create a new array, fill it with the map keys and sort it
//				String[] yearSorted = new String[allYears.size()];
//				int countY = 0;
//				for (Map.Entry<String, String> entry : allYears.entrySet()) {
//					yearSorted[countY] = entry.getKey();
//					countY++;
//				}
//				yearSorted = sortArray(yearSorted);
//				
//				// add Years to ListBox
//				for (int i = 0; i < yearSorted.length; i++) {
//					listBoxYear.addItem(yearSorted[i]);
//				}
//				// create a new array, fill it with the map keys and sort it
//				String[] languagesSorted = new String[allLanguages.size()];
//				int countL = 0;
//				for (Map.Entry<String, String> entry : allLanguages.entrySet()) {
//					languagesSorted[countL] = entry.getKey();
//					countL++;
//				}
//				languagesSorted = sortArray(languagesSorted);
//				
//				// add Languages to ListBox
//				for (int i = 0; i < languagesSorted.length; i++) {
//					listBoxLanguage.addItem(languagesSorted[i]);
//				}
				
				// create a new array, fill it with the map keys and sort it
				String[] countriesSorted = new String[allCountries.size()];
				int countC = 0;
				for (Map.Entry<String, String> entry : allCountries.entrySet()) {
					countriesSorted[countC] = entry.getKey();
					countC++;
				}
				countriesSorted = sortArray(countriesSorted);
				
				// add Countries to ListBox
				for (int i = 0; i < countriesSorted.length; i++) {
					listBoxCountry.addItem(countriesSorted[i]);
				}
				
//				// create a new array, fill it with the map keys and sort it
//				String[] genresSorted = new String[allGenres.size()];
//				int countG = 0;
//				for (Map.Entry<String, String> entry : allGenres.entrySet()) {
//					genresSorted[countG] = entry.getKey();
//					countG++;
//				}
//				genresSorted = sortArray(genresSorted);
//				
//				// add Genres to ListBox
//				for(int i = 0; i < genresSorted.length; i++){
//					listBoxGenre.addItem(genresSorted[i]);
//				}	
			}
		};
		filter.getAllYLCG(callback);
	}
	
	public String[] sortArray(String[] arrayToSort) {	

		boolean flag = true; // will determine when the sort is finished
		String temp;
		while (flag) {
			flag = false;
			for (int i = 0; i < arrayToSort.length - 1; i++ ) {
				if (arrayToSort [i].compareToIgnoreCase( arrayToSort[i + 1] ) > 0) {	// ascending sort
					temp = arrayToSort[i];
					arrayToSort[i] = arrayToSort[i + 1]; // swapping
					arrayToSort [i + 1] = temp;
					flag = true;
				}
			}
		}
		return arrayToSort;
	}
}
