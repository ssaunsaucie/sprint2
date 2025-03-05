package sosGame_project;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class SOSGame extends JFrame 
{
	private JPanel topPanel;			// references top panel
	private JPanel middlePanel;			//references middle panel
	private JPanel bottomPanel;			// references bottom panel
	private JPanel bluePlayerPanel;		// references blue player panel
	private JPanel redPlayerPanel;		// references red player panel
	private JLabel boardSizeLabel;		// references board size label
	private JLabel redPlayerLabel;		// references red player
	private JRadioButton simpleGame;	// reference simple game button
	private JRadioButton generalGame;	// references general game button
	private JLabel sosGame;				// references title of game
	private JLabel bluePlayerLabel;		// references blue player
	private JTextField boardSizeText;	// references text field for user input
	private JRadioButton redPlayerS;	// references red player's S token
	private JRadioButton redPlayerO;	// references red player's O token
	private JRadioButton bluePlayerS;	// references blue player's S token
	private JRadioButton bluePlayerO;	// references blue player's O token
	private JRadioButton redHuman, blueHuman;	//references human radio button for both players
	private JRadioButton redComputer, blueComputer;	// references computer RB for both players
	private ButtonGroup redTokenGroup;		// group for red player token radio buttons
	private ButtonGroup blueTokenGroup;		// group for blue player token radio buttons
	private ButtonGroup blueOptionGroup;	// group for blue player human or computer options
	private ButtonGroup redOptionGroup;		// group for red player human or computer options
	private JButton replayButton, newGameButton;	// references replay and new game buttons
	private JCheckBox recordGame;		// references check box for record
	private final int WINDOW_WIDTH = 800;	// window width
	private final int WINDOW_HEIGHT = 600;	// window height
	private JPanel gridPanel;		// references panel for the grid layout
	private int boardSize = 3;		// references automatic starting size
	private SOSGameLogic gameLogic;  // references SOSGameLogic class
	private JLabel turnLabel;		// references label for displaying player's turn
	
	// 2D array to store grid buttons
	private JButton[][] gridButtons;
	
	
	// Constructor
	
	public SOSGame()
	{
		// set the title of the game
		setTitle("SOS Game");
		
		// set the size of the window
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		
		// close window
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
		// set layout for the game
		setLayout(new BorderLayout());
	
		// initialize panels
		createTopPanel();
		createMiddlePanel();
		createBottomPanel();
		
		// make the frame visible
		setVisible(true);
		
		// initialize game logic with default settings for board size and game mode
		gameLogic = new SOSGameLogic(boardSize, true);
		
		// initial grid setup
		updateGrid();
		
		// organize the layout
		pack();
	}
	
		
	// create top panel and components for top section of game and add to panel
	private void createTopPanel() 
	{
		topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));	// sets top panel components in the center
		sosGame = new JLabel("SOS Game");
		simpleGame = new JRadioButton("Simple Game", true);  // default selection
		generalGame = new JRadioButton("General Game");
		boardSizeLabel = new JLabel("Board Size");
		boardSizeText = new JTextField("3", 3);		// creates with a width of 3 and the default 3 (size of textFieldBox)
		
		// group the game type radio buttons
		ButtonGroup gameTypeGroup = new ButtonGroup();
		gameTypeGroup.add(simpleGame);
		gameTypeGroup.add(generalGame);
		
		// add an action listener to update the grid from user input
		boardSizeText.addActionListener(new TextFieldListener());
		simpleGame.addActionListener(new RadioButtonListener());
		generalGame.addActionListener(new RadioButtonListener());
		
		
		// add components to top panel
		topPanel.add(sosGame);
		topPanel.add(simpleGame);
		topPanel.add(generalGame);
		topPanel.add(boardSizeLabel);
		topPanel.add(boardSizeText);
		add(topPanel, BorderLayout.NORTH);	// positions panel to be located on top of window
	}
		
	// create middle panel where players and grid will be located 
	private void createMiddlePanel()
	{
		middlePanel = new JPanel(new BorderLayout());
		
		// create blue player panel
		bluePlayerPanel = new JPanel();
		bluePlayerPanel.setLayout(new BoxLayout(bluePlayerPanel, BoxLayout.Y_AXIS));	//structures blue player panel to be 
		setupBluePlayerPanel();															// displayed vertically and from top to bottom
		
		
		// create red player panel
		redPlayerPanel = new JPanel();
		redPlayerPanel.setLayout(new BoxLayout(redPlayerPanel, BoxLayout.Y_AXIS));
		setupRedPlayerPanel();		// allows for better organization for creating and adding components
		
		// create a wrapper panel using gridContainer
		JPanel gridContainer = new JPanel(new BorderLayout());
		gridPanel = new JPanel();   // initialize grid panel
		gridPanel.setPreferredSize(new Dimension(200, 200));  // set dimension of the grid
		
		// create scorePanel to hold scoreLabel and turnLabel
		JPanel scorePanel = new JPanel();
		scorePanel.setLayout(new BoxLayout(scorePanel, BoxLayout.X_AXIS));
		turnLabel = new JLabel("Blue Player's turn");
		
		
		// add turnLabel and scoreLabel to scorePanel
		scorePanel.add(turnLabel);
	
		
		// add gridPanle and ScorePanel to gridContainer
		gridContainer.add(gridPanel, BorderLayout.CENTER);
		gridContainer.add(scorePanel, BorderLayout.SOUTH);
		
		// add panels red and blue player panels to the middle panel
		middlePanel.add(bluePlayerPanel, BorderLayout.WEST);		// blue player options on the left
		middlePanel.add(redPlayerPanel, BorderLayout.EAST);	        // red player options on the right
		middlePanel.add(gridContainer, BorderLayout.CENTER);
		add(middlePanel, BorderLayout.CENTER);						// adding middle panel to the center
	}
	
	// set up the blue player panel and create components and add them to panel
	private void setupBluePlayerPanel()
	{
		// create blue player panel objects
		bluePlayerLabel = new JLabel("Blue Player");
		blueHuman = new JRadioButton("Human", true);	
		blueComputer = new JRadioButton("Computer");
		bluePlayerS = new JRadioButton("S", true);		
		bluePlayerO = new JRadioButton("O");
				
		// add active listener for radio buttons
		blueHuman.addActionListener(new RadioButtonListener());
		blueComputer.addActionListener(new RadioButtonListener());
		bluePlayerS.addActionListener(new RadioButtonListener());
		bluePlayerO.addActionListener(new RadioButtonListener());
			
		// group the blue player radio button options 
		blueOptionGroup = new ButtonGroup();
		blueOptionGroup.add(blueHuman);
		blueOptionGroup.add(blueComputer);
		
		// group the blue player radio button tokens
		blueTokenGroup = new ButtonGroup();
		blueTokenGroup.add(bluePlayerS);
		blueTokenGroup.add(bluePlayerO);
		
		// add components to the blue player panel for organization
		bluePlayerPanel.add(bluePlayerLabel);
		bluePlayerPanel.add(blueHuman);
		bluePlayerPanel.add(blueComputer);
		bluePlayerPanel.add(Box.createVerticalStrut(20));	// creates space (20 characters) for tokens under objects
		bluePlayerPanel.add(bluePlayerS);
		bluePlayerPanel.add(bluePlayerO);
	}
		
	// set up red player panel and create components and add them to panel
	private void setupRedPlayerPanel()
	{
		// create red player panel objects
		redPlayerLabel = new JLabel("Red Player");
		redHuman = new JRadioButton("Human");		
		redComputer = new JRadioButton("Computer", true);
		redPlayerS = new JRadioButton("S");		
		redPlayerO = new JRadioButton("O", true);
		
		// add active listeners for the red player radio buttons
		redHuman.addActionListener(new RadioButtonListener());
		redComputer.addActionListener(new RadioButtonListener());
		redPlayerS.addActionListener(new RadioButtonListener());
		redPlayerO.addActionListener(new RadioButtonListener());
		
		
		// group the red player radio button options
		redOptionGroup = new ButtonGroup();
		redOptionGroup.add(redHuman);
		redOptionGroup.add(redComputer);
		
		// group the red player radio button tokens
		redTokenGroup = new ButtonGroup();
		redTokenGroup.add(redPlayerS);
		redTokenGroup.add(redPlayerO);
		
		// add components to the red player panel for organization
		redPlayerPanel.add(redPlayerLabel);
		redPlayerPanel.add(redHuman);
		redPlayerPanel.add(redComputer);
		redPlayerPanel.add(Box.createVerticalStrut(20));	// creates space for tokens under objects
		redPlayerPanel.add(redPlayerS);
		redPlayerPanel.add(redPlayerO);
		
	}
	
	// create bottom panel and components and add them to the bottom panel
	private void createBottomPanel()
	{
		// create bottom panel objects
		bottomPanel = new JPanel(new FlowLayout());
		recordGame = new JCheckBox("Record Game");
		replayButton = new JButton("Replay");
		newGameButton = new JButton("New Game");
		
		// add active listeners for check box and buttons 
		recordGame.addActionListener(new CheckBoxListener());
		replayButton.addActionListener(new ButtonListener());
		newGameButton.addActionListener(new ButtonListener());
		
		// add components to bottom panel
		bottomPanel.add(recordGame);
		bottomPanel.add(replayButton);
		bottomPanel.add(newGameButton);
		add(bottomPanel, BorderLayout.SOUTH);		// adding panel which is to be located at the bottom of window
	}
	
	// update grid based on user input for the board size and create grid to 
	// be interactive using buttons
	private void updateGrid()
	{
		gridPanel.removeAll(); 		// clears the previous grid layout
		gridPanel.setLayout(new GridLayout(boardSize, boardSize));		// sets a new layout, rows and columns are the same size
		
		// stores buttons that make up grid in a 2D array
		gridButtons = new JButton[boardSize][boardSize];
		
		// create interactive grid by adding buttons 
		for (int row = 0; row < boardSize; row++)		// loop for cell size 
		{
			for (int col = 0; col < boardSize; col++) {
				JButton gridButton = new JButton();		// empty button for each cell to detect a token
				gridButton.setPreferredSize(new Dimension(60, 60));		// set the size of the buttons/cells to be uniform
				
				// store the row and column as properties of the button
				gridButton.putClientProperty("row", row);
				gridButton.putClientProperty("col", col);
				
				gridButton.addActionListener(new GridButtonListener());		// add active listener to detect user interaction
				gridPanel.add(gridButton);		// adds button to the grid panel
				gridButtons[row][col] = gridButton;   // store the button in the array
			}
		}
		
		gridPanel.revalidate();		// update the grid layout after changes
		gridPanel.repaint();		// set up the new grid or refresh the layout
		
		// update the turn and score labels 
		updateTurnLabel();
		
	}
	
	
	// private inner listener classes that handles the event when the
	// user clicks the check box, inserts text, or clicks a button
	
	// listener class for the radio button components 
	public class RadioButtonListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			JRadioButton source = (JRadioButton) e.getSource();		// creating method to listen and get the radio buttons
			
			// checks if user chooses simple or general mode
			if(source == simpleGame || source == generalGame) {
				// calls method from SOSGameLogic to handle user choice, checks if simpleGame is selected
				gameLogic.setGameMode(simpleGame.isSelected());
				
				updateGrid(); // update the grid
			}
			
			// update the turn label 
			updateTurnLabel();
		}
	}
	
	// listener class for the button components 
	private class ButtonListener implements ActionListener 
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			if (e.getSource() == newGameButton)		// if user selects new game button
			{
				// when "New Game" button clicked, call resetGame method to reset the game, and:
				gameLogic.resetGame();
				updateGrid();   // update the grid
				updateTurnLabel();  // update the player turn 
				
			}
			else if (e.getSource() == replayButton) {
				System.out.println("Replay");
				
			}
		}
	}
	

	// Listener class for the check box component
	private class CheckBoxListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			System.out.println("Record game" + recordGame.isSelected());	// prints to console "record game" when box is selected
		}
	}
	
	// listener class for board size text field
	public class TextFieldListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			try 
			{
				int newSize = Integer.parseInt(boardSizeText.getText());	// must translate string to an integer and create container for user input
				if (newSize >= 3 && newSize <= 11)		// setting reasonable restrictions on size of game board
				{
					boardSize = newSize;	// update board size is what user inputs
					
					updateGrid();		// must update grid after correct (3-11) number is input
				}
				else 
				{
					JOptionPane.showMessageDialog(null, 
							"Please enter a number between 3 and 11");		// user must input number between 3 and 11
					boardSizeText.setText(String.valueOf(boardSize));	// reset the text field to current board size ensuring valid input
				}
			}
			
			catch (NumberFormatException exception)
			{
				JOptionPane.showMessageDialog(null, "Please enter a vaild number");		// user must input valid integer, not strings or characters
				boardSizeText.setText(String.valueOf(boardSize));
			}
		}		
	}
	
	// method to handle when grid button is triggered
	private class GridButtonListener implements ActionListener {
	    @Override
	    public void actionPerformed(ActionEvent e) {
	        JButton button = (JButton) e.getSource();    // retrieves the button that was selected
	        
	        // get the row and column from the button properties
	        int row = (int) button.getClientProperty("row");
	        int col = (int) button.getClientProperty("col");
	        
	        // determine the current player's token based on radio button selection
	        char letter;
	        Color tokenColor;
	        
	        if (gameLogic.isPlayerTurn()) {  // blue player's turn
	            letter = bluePlayerS.isSelected() ? 'S' : 'O';
	            tokenColor = Color.blue;
	            
	        } else {  // red player's turn
	            letter = redPlayerS.isSelected() ? 'S' : 'O';
	            tokenColor = Color.red;
	        }
	        
	        // only allow moves on empty cells and when game is not over
	        if (button.getText().equals("") && !gameLogic.isGameOver()) {
	            // attempt to make the move
	            if (gameLogic.makeMove(row, col, letter)) {
	                // if move is successful, update the button
	                button.setText(String.valueOf(letter));
	                button.setForeground(tokenColor);
	                
	                // update turn and score labels
	                updateTurnLabel();
	                
	                
	                // check if game is over
	                if (gameLogic.isGameOver()) {
	                    JOptionPane.showMessageDialog(null, gameLogic.getWinner());
	                }
	            }
	        }
	    }
	}
	
	// Update turn label to show which player's turn it is
	private void updateTurnLabel() {
	    turnLabel.setText(gameLogic.isPlayerTurn() ? "Blue Player's turn" : "Red Player's turn");
	}
	    		
	// getter for boardSize
	public int getBoardSize() {
	    return boardSize;
	}
	
	// getter for simple mode
	public boolean isSimpleGame() {
		return gameLogic.isSimpleGame();
	}
	
	// getter for Simple radio button
	public JRadioButton getSimpleRadioButton() {
		return simpleGame;
	}
	
	// getter for General radio button
	public JRadioButton getGeneralRadioButton() {
		return generalGame;
	}

	// method to access the TextFieldListener
	public TextFieldListener getTextFieldListener() {
	    return new TextFieldListener();
	}
	
	// getter for newGameButton
	public JButton getNewGameButton() {
	    return newGameButton;
	}
	
	public SOSGameLogic getGameLogic() {
	    return gameLogic;
	}
	
	public JButton[][] getGridButtons() {
		return gridButtons;
	}
	
	// main method
	public static void main(String[] args)
	{
		 new SOSGame();	// starts game
	}
}


