package MonkeyIsland_Alt;

import java.awt.MouseInfo;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class KeyHandler implements KeyListener, MouseListener{
	Variablen var = new Variablen();
	Label label = new Label();
	int modus;
	
	float posY=400;
	
	int rectPosX=495;
	int rectPosY=245;
	
	int rect2PosX=495;
	int rect2PosY=345;
	
	double sizeX=86;
	float sizeY=240;
	double sizeMaker;
	boolean key_A=false;
	boolean key_D=false;
	byte Richtung=3;
	String cursor="Sword";
	String auswahl="Cursor";
	String redoButton="BackSpace";
	
	KeyHandler(){
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		
		//Bewegung des Spielers
		if(e.getKeyCode()==KeyEvent.VK_A) {key_A=true;Richtung=1;}
		if(e.getKeyCode()==KeyEvent.VK_D) {key_D=true;Richtung=0;}
		
		//Tiefenbewegungn des Spielers
		if(posY>340&&e.getKeyCode()==KeyEvent.VK_W) {
			sizeMaker=0.99;
			sizeX*=sizeMaker;
			sizeY*=sizeMaker;
			posY--;
		}
		if(posY+sizeY<=800 && e.getKeyCode()==KeyEvent.VK_S) {
			sizeMaker=1.01;
			sizeX*=sizeMaker;
			sizeY*=sizeMaker;
			posY++;
		}
//________________________________________________________________________________________________________________________________________________________________________________________________________________
		//Spielmodus wird aktiviert
		if(modus==0) {
			if(e.getKeyCode()==KeyEvent.VK_ENTER) {
				modus=1; 
			}
		}
		//Settingsmodus wird aktiviert
		if(modus==0) {
			if(e.getKeyCode()==KeyEvent.VK_SPACE) modus=2; 
		}
		
//________________________________________________________________________________________________________________________________________________________________________________________________________________
		//Cursor Auswahl im Settings Menü durch bewegen des Rechtecks um die Cursor
		if(modus==2&&rectPosX<795&&auswahl=="Cursor") {
			if(e.getKeyCode()==KeyEvent.VK_RIGHT) {
				rectPosX+=100; 
			}
		}
		//Cursor Auswahl im Settings Menü durch bewegen des Rechtecks um die Cursor
		if(modus==2&&rectPosX>495&&auswahl=="Cursor") {
			if(e.getKeyCode()==KeyEvent.VK_LEFT) {
				rectPosX-=100; 
			}
		}
		//Bestätigen des Schwert Cursors
		if(modus==2&&rectPosX==495&&auswahl=="Cursor") {
			if(e.getKeyCode()==KeyEvent.VK_ENTER) {
				cursor="Sword"; 
			}
		}
		 //Bestätigen des Bananen Cursors
		if(modus==2&&rectPosX==595&&auswahl=="Cursor") {
			if(e.getKeyCode()==KeyEvent.VK_ENTER) {
				cursor="Banana";
			}
		}
		//Bestätigen des Haken Cursors
		if(modus==2&&rectPosX==695&&auswahl=="Cursor") {
			if(e.getKeyCode()==KeyEvent.VK_ENTER) {
				cursor="Hook"; 
			}
		}
		//Bestätigen des Bier Cursors
		if(modus==2&&rectPosX==795&&auswahl=="Cursor") {
			if(e.getKeyCode()==KeyEvent.VK_ENTER) {
				cursor="Beer"; 
			}
		}
//________________________________________________________________________________________________________________________________________________________________________________________________________________
		//Wechsel von der Cursor- zur RedoButton Auswahl
		if(auswahl=="Cursor") {
			if(e.getKeyCode()==KeyEvent.VK_DOWN) {
				auswahl="Redo"; 
			}
		}
		//Wechsel von der RedoButton- zur Cursor Auswahl
		if(auswahl=="Redo") {
			if(e.getKeyCode()==KeyEvent.VK_UP) {
				auswahl="Cursor"; 
			}
		}
//________________________________________________________________________________________________________________________________________________________________________________________________________________
		//RedoButton Auswahl im Settings Menü durch bewegen des Rechtecks um die Möglichkeiten
		if(modus==2&&rect2PosX<1018&&auswahl=="Redo") {
			if(e.getKeyCode()==KeyEvent.VK_RIGHT) {
				rect2PosX+=259; 
			}
		}
		//RedoButton Auswahl im Settings Menü durch bewegen des Rechtecks um die Möglichkeiten
		if(modus==2&&rect2PosX>495&&auswahl=="Redo") {
			if(e.getKeyCode()==KeyEvent.VK_LEFT) {
				rect2PosX-=259; 
			}
		}
		//Bestätigen von BackSpace als RedoButton
		if(modus==2&&rect2PosX==495&&auswahl=="Redo") {
			if(e.getKeyCode()==KeyEvent.VK_ENTER) {
				redoButton="BackSpace"; 
			}
		}
		//Bestätigen von Enter als RedoButton
		if(modus==2&&rect2PosX==754&&auswahl=="Redo") {
			if(e.getKeyCode()==KeyEvent.VK_ENTER) {
				redoButton="Escape"; 
			}
		}
		//Bestätigen von E als RedoButton
		if(modus==2&&rect2PosX==1013&&auswahl=="Redo") {
			if(e.getKeyCode()==KeyEvent.VK_ENTER) {
				redoButton="E"; 
			}
		}
//________________________________________________________________________________________________________________________________________________________________________________________________________________
		//Zurück zum Start Menü über einen der Ausgewahlten Button
		if(modus==1 || modus==2 && redoButton=="BackSpace") {
			if(e.getKeyCode()==KeyEvent.VK_BACK_SPACE)modus=0;
		}
		if(modus==1 || modus==2 &&redoButton=="Escape") {
			if(e.getKeyCode()==KeyEvent.VK_ESCAPE)modus=0;
		}
		if(modus==1 || modus==2 &&redoButton=="E") {
			if(e.getKeyCode()==KeyEvent.VK_E)modus=0;
		}
		//Schließen des Spiels durch drücken von 0 im Auswahl Menü
		if(modus==0&&e.getKeyCode()==KeyEvent.VK_NUMPAD0 || e.getKeyCode()==KeyEvent.VK_0)System.exit(0); 
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_A) {key_A=false;Richtung=4;}
		if(e.getKeyCode()==KeyEvent.VK_D) {key_D=false;Richtung=3;}	
		
		//Spielmodus wird aktiviert
		if(modus==0) {
			if(e.getKeyCode()==KeyEvent.VK_ENTER)modus=1;
		}
		//Spielmodus wird aktiviert
		if(modus==0) {
			if(e.getKeyCode()==KeyEvent.VK_SPACE) modus=2;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {}

	boolean[] mouse_posXOnButton= {false,false,false,false,false,false,false,false,false,false};
	boolean[] mouse_posYOnButton= {false,false,false,false,false,false,false,false,false,false};
	boolean[] mouseOnButton= {false,false,false,false,false,false,false,false,false,false};
	public void getMouseOnButton() {
	
		if(modus==1) {
		//Maus auf der X-Achse
			for(int i=0; i<mouse_posXOnButton.length;i++) {
				if(MouseInfo.getPointerInfo().getLocation().x>=var.buttons_posX[i] && MouseInfo.getPointerInfo().getLocation().x<=var.buttons_posX[i]+var.button_Size) {mouse_posXOnButton[i]=true;}
				else {mouse_posXOnButton[i]=false;}
			}
			
			
//________________________________________________________________________________________________________________________________________________________________________________________________________________
		//Mouse auf der Y-Achse 
			//Wenn die Maus auf der Y-Achse über den Buttons ist
			for(int i=0; i<mouse_posYOnButton.length;i++) {
				if(MouseInfo.getPointerInfo().getLocation().y>=var.button_posY && MouseInfo.getPointerInfo().getLocation().y<=var.button_posY+var.button_Size) {mouse_posYOnButton[i]=true;}
				else {mouse_posYOnButton[i]=false;}
			}
	
//________________________________________________________________________________________________________________________________________________________________________________________________________________	
			//Wenn die Maus auf der X- und Y-Achse über dem Button 1 ist 
			for(int i=0; i<mouseOnButton.length;i++) {
				if(mouse_posXOnButton[i]==true && mouse_posYOnButton[i]==true ) {mouseOnButton[i]=true;}
				else if(mouse_posXOnButton[i]==false && mouse_posYOnButton[i]==false){mouseOnButton[i]=false;}
			}
			
//			System.out.println(mouseOnButton[0]+"	"+mouseOnButton[1]+"	"+mouseOnButton[2]+"	" +mouseOnButton[3]+"	"+mouseOnButton[4]+"	" +mouseOnButton[5]+"	"+mouseOnButton[6]+"	" +mouseOnButton[7]+"	"+mouseOnButton[8]+"	" +mouseOnButton[9]);
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		//In der For-Schleife wird abgefragt über welchem Button die Maus gerade ist, und bei einem  Mausclick wird geschriben welcher Button das war
		for(int i=0; i<mouseOnButton.length;i++) {
			int button = i+1;
			if(mouseOnButton[i]==true) {System.out.println("B"+button);mouseOnButton[i]=false;}
		}
		//Beispiel, wie man es machen kann, wenn verschiedene Aktionen für die Button ausgeführt werden sollen
//		if(mouseOnButton[0]==true) {System.out.println("B1");mouseOnButton[0]=false;}
//		if(mouseOnButton[1]==true) {System.out.println("B2");mouseOnButton[1]=false;}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	

	
}
