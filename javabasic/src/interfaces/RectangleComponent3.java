package interfaces;
//import java.awt.*;

/**
 * This component displays a rectangle that can be moved
 * */
public class RectangleComponent3 extends JComponent{
	private static final int BOX_X = 100;
	private static final int BOX_Y = 100;
	private static final int BOX_WIDTH = 20;
	private static final int BOX_HEIGHT = 30;
	
	private Rectangle box;
	
	public RectangleComponent3() {
		//The rectangle that the paintComponent method draws
		box = new Rectangle(BOX_X, BOX_Y, BOX_WIDTH, BOX_HEIGHT);
	}
	
	public void paintComponent(Graphics g) {
	//	Graphics2D g2 = (Graphics2D) g;
	//	g2.draw(box);
	}
}
