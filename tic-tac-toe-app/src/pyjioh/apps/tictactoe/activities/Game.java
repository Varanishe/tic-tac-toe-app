package pyjioh.apps.tictactoe.activities;

import pyjioh.apps.tictactoe.controllers.TicTacToeController;
import pyjioh.apps.tictactoe.models.TicTacToeModel;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Game extends Activity implements OnClickListener {

	private static TicTacToeModel model = TicTacToeModel.getInstance();
	private static TicTacToeController controller = TicTacToeController
			.getInstance();

	private Button[] buttons;

	private void initListeners() {
		buttons = new Button[9];
		buttons[0] = (Button) findViewById(R.id.button_11);
		buttons[1] = (Button) findViewById(R.id.button_12);
		buttons[2] = (Button) findViewById(R.id.button_13);
		buttons[3] = (Button) findViewById(R.id.button_21);
		buttons[4] = (Button) findViewById(R.id.button_22);
		buttons[5] = (Button) findViewById(R.id.button_23);
		buttons[6] = (Button) findViewById(R.id.button_31);
		buttons[7] = (Button) findViewById(R.id.button_32);
		buttons[8] = (Button) findViewById(R.id.button_33);

		for (Button btn : buttons) {
			btn.setOnClickListener(this);
		}

		findViewById(R.id.human_vs_droid).setOnClickListener(this);
	}

	private void injectionController() {
		controller.setButtons(buttons);
		controller.setScores((TextView) findViewById(R.id.human_score),
				(TextView) findViewById(R.id.droid_score));
	}

	private void doMove(Button btn) {
		switch (btn.getId()) {
		case R.id.button_11:
			model.doMove(0, 0);
			break;
		case R.id.button_12:
			model.doMove(0, 1);
			break;
		case R.id.button_13:
			model.doMove(0, 2);
			break;
		case R.id.button_21:
			model.doMove(1, 0);
			break;
		case R.id.button_22:
			model.doMove(1, 1);
			break;
		case R.id.button_23:
			model.doMove(1, 2);
			break;
		case R.id.button_31:
			model.doMove(2, 0);
			break;
		case R.id.button_32:
			model.doMove(2, 1);
			break;
		case R.id.button_33:
			model.doMove(2, 2);
			break;
		}
	}

	private void newRound() {
		model.newRound();
		controller.refreshGame();
	}

	private void newGame() { 
		model.newGame();
		controller.refreshGame();
	}

	private void showAlertDialog(int status) {
		new AlertDialog.Builder(this).setTitle(R.string.message_title)
				.setMessage(status).setNeutralButton("Ok",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dlg, int sumthin) {
								newRound();
							}
						}).show();
	}

	private void showRestartDialog() {
		new AlertDialog.Builder(this).setTitle(R.string.question_title)
				.setMessage(R.string.restart_game).setPositiveButton("Yes",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dlg, int sumthin) {
								newGame();
							}
						}).setNegativeButton("No", null).show();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.game);
		initListeners();
		injectionController();
		newRound();
	}

	public void onClick(View v) {
		if (v instanceof Button) {
			doMove((Button) v);
			controller.refreshGame();
			if (model.getState() == TicTacToeModel.STATE_DRAW)
				showAlertDialog(R.string.draw_game);
			else if (model.getState() == TicTacToeModel.STATE_WIN) {
				if (model.getWinner() == TicTacToeModel.NOUGHT)
					showAlertDialog(R.string.nought_win_game);
				else if (model.getWinner() == TicTacToeModel.CROSS)
					showAlertDialog(R.string.cross_win_game);
			}

		} else if (v instanceof ImageView) {
			showRestartDialog();
		}
	}

}
