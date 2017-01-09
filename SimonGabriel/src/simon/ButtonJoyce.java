package simon;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import guiPractice.components.Action;
import guiPractice.components.Component;

public class ButtonJoyce extends Component implements ButtonInterfaceGabriel {

	private boolean highlight;
	private Color color;
	private Action action;
	private Color displayColor;
	private int WIDTH = 50;
	private int HEIGHT = 50;

	public ButtonJoyce(int x, int y, int w, int h) {
		super(x, y, w, h);
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
	public boolean isHovered(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void update(Graphics2D g) {
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.setColor(displayColor);
		g.fillOval(0, 0, WIDTH, HEIGHT);
		
	}

}
