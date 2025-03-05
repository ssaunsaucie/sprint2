package sosGame_project;

import static org.junit.Assert.*;

import java.awt.event.ActionEvent;

import org.junit.Before;
import org.junit.After;
import javax.swing.*;

import org.junit.Test;

public class NewGameTest {
	// instances for testing
	private SOSGame sosGame;
	private SOSGameLogic sosLogic;
	private JTextField boardSizeText;  // used to display user input for board size
	private SOSGame.TextFieldListener textListener;  // reacts to actions performed
	private JButton newGameButton;  // references "New Game" button
	
	// before each test, initialize the game state
	@Before 
	public void setUp() {
		sosGame = new SOSGame();
		sosLogic = sosGame.getGameLogic(); // retrieves the game logic instance
		boardSizeText = new JTextField("3", 3);
		textListener = sosGame.getTextFieldListener();
		newGameButton = sosGame.getNewGameButton();
	}
	
	// runs after each test, resetting the game state
	@After
	public void tearDown() {
		sosGame = null;
		sosLogic = null;
		boardSizeText = null;
		textListener = null;
		newGameButton = null;
	}
	
	// test for successful selection of the "New Game" button
	@Test
	public void testNewGameButton() {
		// simulate environment, board size 3x3 and game mode simple
		boardSizeText.setText("3");
		textListener.actionPerformed(new ActionEvent(boardSizeText, ActionEvent.ACTION_PERFORMED, null));
		
		// set game mode to simple
		sosGame.getSimpleRadioButton().setSelected(true);
		
		// simulate clicking the new game button
		newGameButton.doClick();
		
		// verify board size is reset to its original size, 3
		assertEquals(3, sosGame.getBoardSize());
		
		// verify the game mode is reset to simple
		assertTrue(sosGame.isSimpleGame());
		
		// verify game is not over
		assertFalse(sosLogic.isGameOver());
	}
}
