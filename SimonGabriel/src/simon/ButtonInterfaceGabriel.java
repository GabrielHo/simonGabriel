package simon;

import java.awt.Color;

import guiPractice8.component.Action;
import guiPractice8.component.Clickable;

public interface ButtonInterfaceGabriel extends Clickable {
	
	void setColor(Color color);
	
	void setAction(Action action);

	void highlight();

	void dim();

	void setX(int x);

	void setY(int i); 
}
