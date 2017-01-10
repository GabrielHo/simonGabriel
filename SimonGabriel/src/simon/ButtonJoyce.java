package simon;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import guiPractice8.component.Action;
import guiPractice8.component.Component;

public class ButtonJoyce extends Component implements ButtonInterfaceGabriel {

	private boolean highlight;
	private Color color;
	private Action action;
	private Color displayColor;
	private static int WIDTH = 50;
	private static int HEIGHT = 50;

	public ButtonJoyce() {
		super(0, 0, WIDTH, HEIGHT);
	}

	@Override
	public void act() {
		action.act();
	}

	@Override
	public void setColor(Color color) {
		this.color = color;
		update();
	}

	@Override
	public void setAction(Action action) {
		this.action = action;
	}

	@Override
	public void highlight() {
		highlight= true;
		displayColor = color;
		update();
	}

	@Override
	public void dim() {
		displayColor = Color.gray;
		highlight = false;
		update();
	}

	@Override
	public boolean isHovered(int x, int y) {
		double distance = Math.sqrt(Math.pow(x-(getX()+WIDTH/2), 2)+Math.pow(y-(getY()+HEIGHT/2), 2));
		return distance < WIDTH/2;
	}

	@Override
	public void update(Graphics2D g) {
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		if(displayColor != null) g.setColor(displayColor);
		else g.setColor(Color.gray);
		g.fillOval(0, 0, WIDTH, HEIGHT);
		g.setColor(Color.black);
		g.drawOval(0, 0, WIDTH-1, HEIGHT-1);
		if(highlight){
			
		}
	}

}
