package sosGame_project;

import static org.junit.Assert.*;
import org.junit.Before;

import org.junit.Test;

public class SimpleGameMoveTest {

	private SOSGameLogic logic;
    @Before
    public void setUp() {
        // sets up a default board size 3x3 and mode to Simple
        logic = new SOSGameLogic(3, true);  
    }
    
    // test unsuccessful move on an occupied cell
    @Test
    public void testUnsuccessfulMoveOnOccupiedCell() {
        // occupy a cell with a token
        int row = 1;
        int col = 1;
        char initialToken = 'S';
        logic.makeMove(row, col, initialToken);
        
        // store the initial player turn
        boolean initialPlayerTurn = logic.isPlayerTurn();
        
        // try to place another token on the same cell
        boolean moveResult = logic.makeMove(row, col, 'O');
        
        // assert
        assertFalse("Move on an occupied cell should return false", moveResult);
        assertEquals("Cell should remain unchanged", initialToken, logic.getCell(row, col));
        assertEquals("Player turn should not remain the same", initialPlayerTurn, logic.isPlayerTurn());
    }
    
    // test successful move on an unoccupied cell
    @Test
    public void testSuccessfulMoveOnUnoccupiedCell() {
        // create an empty cell
        int row = 1;
        int col = 1;
        char token = 'S';
        
        // initial player turn, blue player
        boolean initialPlayerTurn = logic.isPlayerTurn();
        
        // place token on an empty cell
        boolean moveResult = logic.makeMove(row, col, token);
        
        // assertions
        assertTrue("Move on an unoccupied cell should return true", moveResult);
        assertEquals("Cell should contain the placed token", token, logic.getCell(row, col));
        assertNotEquals("Player's turn should change", initialPlayerTurn, logic.isPlayerTurn());
    }
    
   
}