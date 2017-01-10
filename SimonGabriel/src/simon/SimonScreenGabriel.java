package simon;

import java.awt.Color;
import java.util.ArrayList;

import guiPractice8.ClickableScreen;
import guiPractice8.component.Action;
import guiPractice8.component.Button;
import guiPractice8.component.TextLabel;
import guiPractice8.component.Visible;

public class SimonScreenGabriel extends ClickableScreen implements Runnable {
	
	private ArrayList<MoveInterfaceGabriel> moves;
	private TextLabel label;
	private ButtonInterfaceGabriel[] button;
	private ProgressInterfaceGabriel progress;
	
	private int roundNumber;
	private boolean acceptingInput;
	private int sequenceIndex;
	private int lastSelectedButton;
	

	public SimonScreenGabriel(int width, int height) {
		super(width, height);
		Thread app = new Thread(this);
		app.start();

	}

	@Override
	public void run() {
		label.setText("");
	    nextRound();
	}

	public void nextRound() {
		acceptingInput = false;
		roundNumber++;
		moves.add(randomMove());
		progress.setRound(roundNumber);
		progress.setSequenceSize(moves.size());
		changeText("not ur turn.");
		label.setText("");
		playSequence();
		changeText("ur turn.");
		acceptingInput = true;
		sequenceIndex =0;
		
	}

	private void playSequence() {
		ButtonInterfaceGabriel b =null;
		for(MoveInterfaceGabriel sequence: moves){
			if(b!=null)//{
				b.dim();
				b=sequence.getButton();
				b.highlight();
				int sleepTime = (int)(long)(2000*(2.0/(roundNumber+2)));
				try {
					Thread.sleep(sleepTime);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			//}
		}
		b.dim();
	}

	private void changeText(String string) {
		try{
			label.setText(string);
			Thread.sleep(1000);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	@Override
	public void initAllObjects(ArrayList<Visible> viewObjects) {
		addButtons(viewObjects);
		progress = getProgress();
		label = new TextLabel(300,150,300,40,"Let's play Simon!");
		moves = new ArrayList<MoveInterfaceGabriel>();
		//add 2 moves to start
		lastSelectedButton = -1;
		moves.add(randomMove());
		moves.add(randomMove());
		roundNumber = 0;
		viewObjects.add(progress);
		viewObjects.add(label);
	}

	private MoveInterfaceGabriel randomMove() {
		//ButtonInterfaceGabriel b = null;
		int choose = (int) (Math.random()*button.length);
		while(choose == lastSelectedButton){
			choose = (int) (Math.random()*button.length);
		}
		lastSelectedButton = choose;
		return new MoveJoyce(button[choose]);
	}

	private MoveInterfaceGabriel getMove(ButtonInterfaceGabriel b) {
		// TODO Auto-generated method stub
		return null;
	}

	private ProgressInterfaceGabriel getProgress() {
		return new ProgressJoyce();
	}

	public void addButtons(ArrayList<Visible> viewObjects) {
		int numberOfButtons = 6;
		Color[] colorArray = {Color.red, Color.blue, new Color(240,160,70), new Color(20,255,140), Color.yellow, new Color(180,90,210)};
		button = new ButtonInterfaceGabriel[numberOfButtons];
		for(int i =0; i <numberOfButtons;i++){
			button[i] = getAButton();
			button[i].setColor(colorArray[i]);
			button[i].setX(320 + (int)(100*Math.cos(i*2*Math.PI/(2*numberOfButtons))));
			button[i].setY(200 - (int)(100*Math.sin(i*2*Math.PI/(2*numberOfButtons))));
			final ButtonInterfaceGabriel b = button[i];
			b.dim();
			b.setAction(new Action(){
				public void act(){
					if(acceptingInput){
						Thread blink = new Thread(new Runnable(){
							
							public void run(){
								b.highlight();
								try{
									Thread.sleep(800);
								} catch (InterruptedException e){
									e.printStackTrace();
								}
								b.dim();//stops the button from shining
							}
							
						});
						
						blink.start();
						
						if(acceptingInput && moves.get(sequenceIndex).getButton() == b){
							sequenceIndex++;
						}
						else if(acceptingInput){
							progress.gameOver();
							return;
						}
						if(sequenceIndex == moves.size()){
							Thread nextRound = new Thread(SimonScreenGabriel.this);
							nextRound.start();
						}
					}
				}
			});
			viewObjects.add(b);
		}
	}

	private ButtonInterfaceGabriel getAButton() {
		return new ButtonJoyce();
	}
		

}
