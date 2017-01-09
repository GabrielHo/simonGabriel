package simon;

import java.awt.Color;
import java.util.ArrayList;

import guiPractice.sampleGames.ClickableScreen;
import guiPractice.components.Action;
import guiPractice.components.Button;
import guiPractice.components.TextLabel;
import guiPractice.components.Visible;

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
		changeText("Simon's turn.");
		label.setText("");
		playSequence();
		changeText("Your turn.");
		acceptingInput = true;
		sequenceIndex =0;
		
	}

	private void playSequence() {
		ButtonInterfaceGabriel b =null;
		for(MoveInterfaceGabriel sequence: moves){
			if(b!=null){
				b.dim();
				b=sequence.getButton();
				b.highlight();
				int sleepTime = (int)(long)(2000*(2.0/roundNumber+2));
				try {
					Thread.sleep(sleepTime);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		b.dim();
	}

	private void changeText(String string) {
		label.setText(string);
	}

	@Override
	public void initAllObjects(ArrayList<Visible> viewObjects) {
		addButtons(viewObjects);
		progress = getProgress();
		label = new TextLabel(130,230,300,40,"Let's play Simon!");
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
		ButtonInterfaceGabriel b = null;
		//code that randomly selects a ButtonInterfaceX
		return getMove(b);//partner codes this part
	}

	private MoveInterfaceGabriel getMove(ButtonInterfaceGabriel b) {
		// TODO Auto-generated method stub
		return null;
	}

	private ProgressInterfaceGabriel getProgress() {
		/**
		Placeholder until partner finishes implementation of ProgressInterface
		*/
		return null;
	}

	public void addButtons(ArrayList<Visible> viewObjects) {
		int numberOfButtons = 6;
		Color[] colorArray = {Color.red, Color.blue, new Color(240,160,70), new Color(20,255,140), Color.yellow, new Color(180,90,210)};
		final ButtonInterfaceGabriel b = getAButton();
		for(int i =0; i <numberOfButtons;i++){
			b.setColor(colorArray[i]);//need partner to finish
			b.setX(250);//need partner to finish
			b.setY(500);//need partner to finish
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
		// TODO Auto-generated method stub
		return null;
	}
		

}
