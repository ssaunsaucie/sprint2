package sosGame_project;

import static org.junit.Assert.*;

import java.awt.event.ActionEvent;

import org.junit.Before;
import javax. swing.*;

import org.junit.Test;

public class GameModeTest {
	// instances of game to test
	private SOSGameLogic sosLogic;
	private SOSGame sosGame;
	private final int testBoardSize = 3;  // used to determine how to form SOS sequence
	
	// before the tests run, initiate instances for testing
	@Before
	public void setUp() {
		// create instances for testing 
		sosLogic = new SOSGameLogic(testBoardSize, true);  // defaults to simple game 
		sosGame = new SOSGame();  
	}
	
	// test game behavior when simple mode is selected
	@Test 
	public void testSimpleGameMode() {
		sosLogic.setGameMode(true);  // set game mode to simple
		
		// verify simple game mode is set
		assertTrue("Game mode should be set to Simple", sosLogic.isSimpleGame());
		
		// test the GUI, as selecting the radio button is in the GUI
		// extract the radio buttons using getter method
		JRadioButton simpleGameButton = sosGame.getSimpleRadioButton();
		
		// simulate clicking the simple mode radio button to test
		if (simpleGameButton != null) {
			ActionEvent setSimple = new ActionEvent(simpleGameButton, ActionEvent.ACTION_PERFORMED, "");
			
			// get the radio button listener from SOSGame and trigger it
			simpleGameButton.getActionListeners()[0].actionPerformed(setSimple);
			
			// verify the game behavior was updated to simple mode by checking board size
			assertEquals("Board Size should match after mode is selected", testBoardSize, sosGame.getBoardSize());
			
			// verify simple game mode
			assertTrue("Game mode should be set to simple", sosLogic.isSimpleGame());
		}
	}
	
	// test game behavior when general mode is selected
	@Test
	public void testGeneralGameMode() {
		sosLogic.setGameMode(false);   // sets to General mode
		
		// verify general game mode is set
		assertFalse("Game mode should be set to general", sosLogic.isSimpleGame());
	
		// test GUI radio buttons using getter method
		JRadioButton generalGameButton = sosGame.getGeneralRadioButton();
		
		// simulate clicking the general game radio button
		if (generalGameButton != null) {
			ActionEvent setGeneral = new ActionEvent(generalGameButton, ActionEvent.ACTION_PERFORMED, "");
			
			// get the radio button listener from SOSGame and trigger it
			generalGameButton.getActionListeners()[0].actionPerformed(setGeneral);
						
			// verify the game behavior was updated to simple mode by checking board size
			assertEquals("Board Size should match after mode is selected", testBoardSize, sosGame.getBoardSize());
						
			// verify general game mode
			assertFalse("Game mode should be set to general", sosLogic.isSimpleGame());
		}
	}
}
