package MonkeyIsland_New_OK;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JLabel;


public class Background {
	private Player_Keyboard player_Keyboard;
	
	private ArrayList<Rectangle> rectangles = new ArrayList<>();
	private JLabel schrankLabel = new JLabel();

	private BufferedImage backgroundImage, backgroundClosed, backgroundOpened, barrolsImage, ladderImage;
	private double posX = 0, posY = 0, vel_x = 0.00005f, width, height, sizeFactor, image_width, image_height;
	private boolean end_left, end_right, schrankAdded, schrankOffen, mouseClicked;
	private final double refWidth = 1600d, refHeight = 950d;
	private int skillInt;

	public Background(Player_Keyboard player, int width, int height) {
		backgroundClosed = ImageLoader.getImageByFilename("Backgrounds/MonkeyIsland-Schrank_geschlossen.jpg");
		backgroundOpened = ImageLoader.getImageByFilename("Backgrounds/MonkeyIsland-Schrank_geöffnet.jpg");
		backgroundImage = backgroundClosed;
		
		barrolsImage = ImageLoader.getImageByFilename("Backgrounds/Barrols.png");
		ladderImage = ImageLoader.getImageByFilename("Backgrounds/Ladder.png");
		this.player_Keyboard = player;
		this.width = width;
		this.height = height;
		this.sizeFactor = (double) backgroundImage.getHeight() / (double) this.height;
		this.image_width = backgroundImage.getWidth() * sizeFactor;
		this.image_height = backgroundImage.getHeight() * sizeFactor;
		setRectangles();
		
		schrankLabel.setLocation(relX(279d, refWidth), relY(354d, refHeight));
		schrankLabel.setSize(relX(439d-279d, refWidth), relY(589d-334d, refHeight));
		schrankLabel.setFocusable(false);
		schrankLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				super.mousePressed(e);
				if(e.getButton() == MouseEvent.BUTTON1) {
					if(skillInt == 0){
						mouseClicked = true;
					}
					if(skillInt == 5 && !schrankOffen) {
						backgroundImage = backgroundOpened;
						schrankOffen = true;
					}
					if(skillInt == 6 && schrankOffen) {
						backgroundImage = backgroundClosed;
						schrankOffen = false;
					}
				}
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				super.mouseReleased(e);
				if(e.getButton() == MouseEvent.BUTTON1) {
					if(skillInt == 0) {
						mouseClicked = false;
					}
				}
			}
		});
	}

	private void setRectangles() {
//		Kessel
		rectangles.add(new Rectangle(relX(855d, refWidth), relY(730d, refHeight), relX(2d, refWidth), relY(86d, refHeight)));
		rectangles.add(new Rectangle(relX(855d + 266d, refWidth), relY(730d, refHeight), relX(2d, refWidth), relY(86d, refHeight)));
		rectangles.add(new Rectangle(relX(855d, refWidth), relY(730d+86d, refHeight), relX(266d, refWidth), relY(2d, refHeight)));

//		Rechte Grenzen
		rectangles.add(new Rectangle(relX(1187d, refWidth), relY(750d, refHeight), relX(1269d-1187d, refWidth), relY(2d, refHeight)));
		rectangles.add(new Rectangle(relX(1400d, refWidth), relY(850d, refHeight), relX(2d, refWidth), relY(163, refHeight)));;
		rectangles.add(new Rectangle(relX(1380d, refWidth), relY(755d, refHeight), relX(2d, refWidth), relY(817d-755d, refHeight)));
		
//		Linke Grenzen
		rectangles.add(new Rectangle(relX(124d, refWidth), relY(850d, refHeight), relX(2d, refWidth), relY(100d, refHeight)));
		rectangles.add(new Rectangle(relX(277d, refWidth), relY(764d, refHeight), relX(2d, refWidth), relY(860-764, refHeight)));
		rectangles.add(new Rectangle(relX(435d, refWidth), relY(737d, refHeight), relX(2d, refWidth), relY(834-737, refHeight)));
		rectangles.add(new Rectangle(relX(555d, refWidth), relY(730d, refHeight), relX(2d, refWidth), relY(791-730, refHeight)));
		rectangles.add(new Rectangle(relX(640d, refWidth), relY(750d, refHeight), 730-640, relY(2d, refHeight)));
	}

	public ArrayList<Rectangle> getRectangles() {
		return rectangles;
	}

	private int relX(double x, double refWidth) {
		return (int) (width * (x / refWidth));
	}

	private int relY(double y, double refHeight) {
		return (int) (height * (y / refHeight));
	}

	public void drawBackground(Graphics g) {
		g.drawImage(backgroundImage, (int) posX, (int) posY, (int) width, (int) height, null);
//		backgroundMove();
	}

	public void drawForeground(Graphics g) {
		g.drawImage(barrolsImage, (int) (width - (width * (barrolsImage.getWidth() / 1600d))),
				(int) (height - (height * (barrolsImage.getHeight() / 950d))),
				(int) (width * (barrolsImage.getWidth() / 1600d)), (int) (height * (barrolsImage.getHeight() / 950d)),
				null);
		g.drawImage(ladderImage, 0, (int) (height - (height * (ladderImage.getHeight() / 950d))),
				(int) (width * (ladderImage.getWidth() / 1600d)), (int) (height * (ladderImage.getHeight() / 950d)),
				null);
	}

	private void backgroundMove() {
		if (player_Keyboard != null) {
			if (posX < 0) {
				if (player_Keyboard.getBorder_left()) {
					posX += vel_x;
					end_right = false;
				}
				end_right = true;
			} else if (posX > 0) {
				if (player_Keyboard.getBorder_right()) {
					posX -= vel_x;
					end_left = false;
				}
				end_left = true;
			}
			if (player_Keyboard.getBorder_left()) {
				posX += player_Keyboard.getVel_x();
			}
			if (player_Keyboard.getBorder_right()) {
				posX -= player_Keyboard.getVel_x();
			}
		}
	}
	
	public void addSchrankLabel(JLabel label) {
		if(!schrankAdded) {
			label.add(schrankLabel);
			schrankAdded = true;
		}
	}
	
	public void removeSchrankLabel(JLabel label) {
		if(schrankAdded) {
			label.remove(schrankLabel);
			schrankAdded = false;
		}
	}
	
	public void setSkillInt(int skillInt) {
		this.skillInt = skillInt;
	}

	public boolean getEnd_left() {
		return end_left;
	}

	public boolean getEnd_right() {
		return end_right;
	}

	public BufferedImage getBackgroundImage() {
		return backgroundImage;
	}
	
	public boolean getSchrankOffen() {
		return schrankOffen;
	}
	public void setSchrankOffen(boolean schrankOffen) {
		this.schrankOffen = schrankOffen;
		if(this.schrankOffen) {
			backgroundImage = backgroundOpened;
		}else {
			backgroundImage = backgroundClosed;
		}
	}
	
	public boolean getMouseClickedSchrank() {
		return mouseClicked;
	}
}
