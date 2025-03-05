package sosGame_project;

import static org.junit.Assert.*;
import javax.swing.*;
import org.junit.Test;
import java.awt.event.ActionEvent;
import org.junit.Before;
import org.junit.After;

public class BoardCreationTest {
	
	// instances of game to test
	private SOSGame sosGame;  // references the class being tested 
	private JTextField boardSizeText;  // used to display for user input for board dimensions
	private SOSGame.TextFieldListener textListener;  // reacts to actions performed in text box field
	
	// runs before each test to initialize the SOSGame and methods needed
	@Before
	public void setUp() {
		sosGame = new SOSGame();  // creates new instance to test
		
		boardSizeText = new JTextField("3", 3);  // initializes JTextField box to size of 3 columns 
		
		textListener = sosGame.getTextFieldListener();  // retrieves TextFieldListener from sosGame instance
	}
	
	// runs after each test, resetting the state
	@After
	public void tearDown() {
		sosGame = null;
		boardSizeText = null;
		textListener = null;
	}
	
	// test for input outside of the restricted numerical range (3-11)
	@Test
	public void testOutOfRangeForBoardSize() {
		boardSizeText.setText("2");  // setting game board size to 2 (too small)
		
		// processes the input by triggering action performed method of textListener to process "2"
		textListener.actionPerformed(new ActionEvent(boardSizeText, ActionEvent.ACTION_PERFORMED, null));
		
		// calls the getBoardSize method to ensure the invalid input is not updated, and default stays 3
		assertEquals(3, sosGame.getBoardSize());
		
		// same test, but for too large
		boardSizeText.setText("12");
		textListener.actionPerformed(new ActionEvent(boardSizeText, ActionEvent.ACTION_PERFORMED, null));  // process input
		assertEquals(3, sosGame.getBoardSize());
	}
	
	// test for non-integer user input
	@Test
	public void testForNonIntegerInput() {
		// set text field to a string or character
		boardSizeText.setText("oreo");
		
		// process the input
		textListener.actionPerformed(new ActionEvent(boardSizeText, ActionEvent.ACTION_PERFORMED, null));
		
		// ensure invalid input is not update and 3 maintains the default
		assertEquals(3, sosGame.getBoardSize());
		
	}
}
