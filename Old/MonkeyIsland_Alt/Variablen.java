package MonkeyIsland_Alt;

public class Variablen {
	// Die Links zu den verschiedenen Bildern werden als String abgespeichert
	String bild_Guybrush_Rechts = "MonkeyIsland_Alt/Guybrush_Rechts.png";
	String bild_Guybrush_Links = "MonkeyIsland_Alt/Guybrush_Links.png";
	String bild_Background = "MonkeyIsland_Alt/Background.png";
	String bild_Start = "MonkeyIsland_Alt/StartMenu.png";
	String bild_Button = "MonkeyIsland_Alt/Button.png";
	String bild_CursorSword = "MonkeyIsland_Alt/Cursor.png";
	String bild_CursorBanana = "MonkeyIsland_Alt/Cursor_Banana.png";
	String bild_CursorHook = "MonkeyIsland_Alt/Cursor_Hook.png";
	String bild_CursorBeer = "MonkeyIsland_Alt/Cursor_Beer.png";
	String bild_InvisibleCursor = "MonkeyIsland_Alt/InvisibleCursor.png";
	String bild_ButtonLeiste = "MonkeyIsland_Alt/ButtonLeiste.png";

	String bilder_Auswahl[] = { "MonkeyIsland_Alt/Auswahl1.png", "MonkeyIsland_Alt/Auswahl2.png",
			"MonkeyIsland_Alt/Auswahl1.png", "MonkeyIsland_Alt/Auswahl1.png", "MonkeyIsland_Alt/Auswahl1.png",
			"MonkeyIsland_Alt/Auswahl1.png", "MonkeyIsland_Alt/Auswahl1.png", "MonkeyIsland_Alt/Auswahl1.png",
			"MonkeyIsland_Alt/Auswahl1.png", "MonkeyIsland_Alt/Auswahl1.png" };

	int frameSizeX = 1600;
	int frameSizeY = 900;

	// Das sind die Koordinaten, sowie Höhe und Breite für die Minimap
	int miniMap_posX = 10;
	int miniMap_posY = 10;
	int miniMap_sizeX = 300;
	int miniMap_sizeY = 89;

	int auswahlX = 600;
	int auswahlY = 600;

	int bg_SizeX = 3034;
	boolean starten;

	int bl_posY = 800;

	final int button_posY = 815;
	int button_Size = 70;

	// Berechnung der Positionen erfolgte grob durch Teilen der Button Leiste mit
	// der Anzahl an Buttons: 1400/10; 1400, weil die Button Leiste an diesem Punkt
	// einen neuen Abschnitt beginnt
	int[] buttons_posX = { 140, 280, 420, 560, 700, 840, 980, 1120, 1260, 1450 };

	Variablen() {
	}
}
