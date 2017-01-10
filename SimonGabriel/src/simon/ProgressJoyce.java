package simon;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import guiPractice8.component.Component;

public class ProgressJoyce extends Component implements ProgressInterfaceGabriel {

	private static final int WIDTH = 120;
	private static final int HEIGHT = 50;

	private boolean gameOver;
	private String round;
	private String sequence;
	
	public ProgressJoyce() {
		super(60, 60, WIDTH, HEIGHT);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void setRound(int roundNumber) {
		round = "Round "+roundNumber;
		update();
	}

	@Override
	public void setSequenceSize(int sequenceNumber) {
		sequence = "Sequence length "+sequenceNumber;
		update();
	}

	@Override
	public void gameOver() {
		gameOver = true;
		update();
	}

	@Override
	public void update(Graphics2D g) {
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		FontMetrics fm = g.getFontMetrics();
		if(gameOver){
//			g.setColor(Color.white);
//			g.fillRect(0, 0, WIDTH, HEIGHT);
			g.setColor(Color.red);
			g.fillOval(0, 0, WIDTH, HEIGHT);
			g.setColor(Color.black);
			String go = "you're a fool";
			g.drawString(go, (WIDTH - fm.stringWidth(go))/2, 20);
			g.drawString(sequence, (WIDTH - fm.stringWidth(sequence))/2, 40);

		}else{
//			g.setColor(Color.green);
//			g.fillRect(0, 0, WIDTH, HEIGHT);
			g.setColor(Color.yellow);
			g.fillOval(0, 0, WIDTH, HEIGHT);
			g.setColor(Color.black);
			//g.drawOval(0, 0, WIDTH, HEIGHT);
			if(round !=null && sequence != null){

				g.drawString(round, (WIDTH - fm.stringWidth(round))/2, 20);
				g.drawString(sequence, (WIDTH - fm.stringWidth(sequence))/2, 40);
			}
		}
	}

}
