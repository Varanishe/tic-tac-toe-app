package pyjioh.apps.tictactoe.activities;

import pyjioh.apps.tictactoe.R;
import pyjioh.apps.tictactoe.models.TicTacToeModel;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RadioButton;

public class Options extends Activity {

	private void initButton() { 
		RadioButton easy = (RadioButton) findViewById(R.id.easy_radiobutton);
		easy.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				TicTacToeModel.getInstance().setDifficulty(TicTacToeModel.EASY_DIF);
			}
		});
		
		RadioButton medium = (RadioButton) findViewById(R.id.medium_radiobutton);
		medium.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				TicTacToeModel.getInstance().setDifficulty(TicTacToeModel.MEDIUM_DIF);
			}
		});
		
		RadioButton hard = (RadioButton) findViewById(R.id.hard_radiobutton);
		hard.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				TicTacToeModel.getInstance().setDifficulty(TicTacToeModel.HARD_DIF);
			}
		});
		
		switch (TicTacToeModel.getInstance().getDifficulty()) {
		case TicTacToeModel.EASY_DIF:
			easy.setChecked(true);
			break;
		case TicTacToeModel.MEDIUM_DIF:
			medium.setChecked(true);
			break;
		case TicTacToeModel.HARD_DIF:
			hard.setChecked(true);
			break;
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.options);
		initButton();
	}

}
