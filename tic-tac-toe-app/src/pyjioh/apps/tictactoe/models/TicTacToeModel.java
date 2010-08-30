package pyjioh.apps.tictactoe.models;

import java.util.Arrays;
import java.util.Random;

public class TicTacToeModel {
	private static TicTacToeModel instance;

	private TicTacToeModel() {
	}

	public static synchronized TicTacToeModel getInstance() {
		if (instance == null)
			instance = new TicTacToeModel();
		return instance;
	}

	public static final int EMPTY = 0;
	public static final int NOUGHT = 1;
	public static final int CROSS = 2;

	public static final int STATE_NONE = 4;
	public static final int STATE_DRAW = 5;
	public static final int STATE_WIN = 6;

	public static final int EASY_DIF = 7;
	public static final int MEDIUM_DIF = 8;
	public static final int HARD_DIF = 9;

	private static int[][] gameField = new int[3][3];

	private int state = STATE_NONE;
	private int winner = EMPTY;
	private int difficulty = HARD_DIF;

	private void clear() {
		for (int i = 0; i < 3; i++) {
			Arrays.fill(gameField[i], EMPTY);
		}
		winner = EMPTY;
	}

	private boolean isWinner(int element) {
		return /* horizontal */
		(gameField[0][0] == element && gameField[0][1] == element && gameField[0][2] == element)
				|| (gameField[1][0] == element && gameField[1][1] == element && gameField[1][2] == element)
				|| (gameField[2][0] == element && gameField[2][1] == element && gameField[2][2] == element)
				/* vertical */
				|| (gameField[0][0] == element && gameField[1][0] == element && gameField[2][0] == element)
				|| (gameField[0][1] == element && gameField[1][1] == element && gameField[2][1] == element)
				|| (gameField[0][2] == element && gameField[1][2] == element && gameField[2][2] == element)
				/* diagonal */
				|| (gameField[0][0] == element && gameField[1][1] == element && gameField[2][2] == element)
				|| (gameField[0][2] == element && gameField[1][1] == element && gameField[2][0] == element);
	}

	private boolean isDraw() {
		boolean isDraw = state != STATE_WIN;
		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 3; j++)
				isDraw = isDraw && (gameField[i][j] != EMPTY);
		return isDraw;
	}

	private void doSmartMove() {

	}

	private void doStupidMove() {
		int emptyCells = 0;
		for (int x = 0; x < 3; x++) {
			for (int y = 0; y < 3; y++) {
				if (gameField[x][y] == EMPTY) 
					emptyCells++;
			}
		}
		
		if (emptyCells > 0) {
			int numberCell = new Random().nextInt(emptyCells) + 1;
			emptyCells = 0;
			for (int x = 0; x < 3 && emptyCells < numberCell; x++) {
				for (int y = 0; y < 3 && emptyCells < numberCell; y++) {
					if (gameField[x][y] == EMPTY) {
						emptyCells++;
						if (numberCell == emptyCells)
							gameField[x][y] = NOUGHT;
					}
				}
			}
		}
	}

	private void doAutoMove() {
		switch (difficulty) {
		case EASY_DIF:
			doStupidMove();
			break;
		case MEDIUM_DIF:
			if (new Random().nextBoolean())
				doStupidMove();
			else
				doSmartMove();
			break;
		case HARD_DIF:
			doSmartMove();
			break;
		}
		analyzeState();
	}

	private void analyzeState() {
		if (isWinner(NOUGHT)) {
			state = STATE_WIN;
			winner = NOUGHT;
		} else if (isWinner(CROSS)) {
			state = STATE_WIN;
			winner = CROSS;
		} else if (isDraw()) {
			state = STATE_DRAW;
			winner = EMPTY;
		} else {
			state = STATE_NONE;
			winner = EMPTY;
		}
	}

	public int getWinner() {
		return winner;
	}

	public int getState() {
		return state;
	}

	public void doMove(int x, int y) {
		if (x < 3 && y < 3)
			if (gameField[x][y] == EMPTY) {
				gameField[x][y] = CROSS;
				analyzeState();
				if (getState() == STATE_NONE)
					doAutoMove();
			}
	}

	public int[][] getGameField() {
		return gameField;
	}

	public void newGame() {
		clear();
	}

	public void setDifficulty(int difficulty) {
		this.difficulty = difficulty;
	}

	public int getDifficulty() {
		return difficulty;
	}

}
