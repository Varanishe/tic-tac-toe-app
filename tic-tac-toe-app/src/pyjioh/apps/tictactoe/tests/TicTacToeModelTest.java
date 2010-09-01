/**
 * Tests for the model
 * You need add JUnit 4 library to the build path 
 */

package pyjioh.apps.tictactoe.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import pyjioh.apps.tictactoe.models.TicTacToeModel;


public class TicTacToeModelTest {
	private TicTacToeModel model = TicTacToeModel.getInstance();
	
	@Test
	public void test_NewGame() {
		model.setDifficulty(TicTacToeModel.HARD_DIF);
		model.newGame();
		assertTrue(TicTacToeModel.EMPTY == model.getWinner());
		assertTrue(TicTacToeModel.STATE_NONE == model.getState());
	}

	@Test
	public void test_HardDraw1() {
		model.setDifficulty(TicTacToeModel.HARD_DIF);
		model.newGame();
		model.doMove(0, 0);
		model.doMove(2, 0);
		model.doMove(1, 2);
		model.doMove(2, 1);
		model.doMove(1, 0);
		assertTrue(model.getState() == TicTacToeModel.STATE_DRAW);
	}

	@Test
	public void test_HardDraw2() {
		model.setDifficulty(TicTacToeModel.HARD_DIF);
		model.newGame();

	}

	@Test
	public void test_HardDraw3() {
		model.setDifficulty(TicTacToeModel.HARD_DIF);
		model.newGame();

	}

}
