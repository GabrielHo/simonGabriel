package simon;

public class MoveJoyce implements MoveInterfaceGabriel {

	private ButtonInterfaceGabriel b;	
	
	public MoveJoyce(ButtonInterfaceGabriel b) {
		this.b = b;
	}

	@Override
	public ButtonInterfaceGabriel getButton() {
		return b;
	}

}
