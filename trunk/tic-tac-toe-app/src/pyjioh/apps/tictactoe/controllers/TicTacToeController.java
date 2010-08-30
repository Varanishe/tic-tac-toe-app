package pyjioh.apps.tictactoe.controllers;

import pyjioh.apps.tictactoe.activities.R;
import pyjioh.apps.tictactoe.models.TicTacToeModel;
import android.widget.Button;

public class TicTacToeController {
	
	private static TicTacToeController instance;

	private TicTacToeController() {
	}

	public static synchronized TicTacToeController getInstance() {
		if (instance == null)
			instance = new TicTacToeController();
		return instance;
	}

	private static TicTacToeModel model = TicTacToeModel.getInstance();

	private Button[] buttons;

	private void drawButton(Button btn, int state) {
		if (state == TicTacToeModel.NOUGHT)
			btn.setBackgroundResource(R.drawable.o_96);
		else if (state == TicTacToeModel.CROSS)
			btn.setBackgroundResource(R.drawable.x_96);
		else
			btn.setBackgroundResource(R.drawable.clear);
	}

	public void refreshGame() {
		for (int i = 0; i < buttons.length; i++)
			drawButton(buttons[i], model.getGameField()[i / 3][i % 3]);
	}

	public void setButtons(Button[] buttons) {
		this.buttons = buttons;
	}

}
