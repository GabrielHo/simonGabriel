package simon;

import guiPractice.GUIApplication;

public class SimonGameGabriel extends GUIApplication {

	public SimonGameGabriel() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void initScreen() {
		SimonScreenGabriel wams = new SimonScreenGabriel(getWidth(), getHeight());
		setScreen(wams);

	}

	public static void main(String[] args) {
		SimonGameGabriel game = new SimonGameGabriel();
		Thread app = new Thread(game);
		app.start();

	}

}
