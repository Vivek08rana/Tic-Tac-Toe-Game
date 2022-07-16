/**
 This program creates a fully functioning tic tac toe game
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToeGame extends JFrame {
    //INSTANCE VARIABLES
    //Buttons
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton button4;
    private JButton button5;
    private JButton button6;
    private JButton button7;
    private JButton button8;
    private JButton button9;
    private JButton reset;
    //Labels
    private JLabel player1;
    private JLabel player2;
    private JLabel tie;
    //Text Fields and Text Areas
    private JTextArea explainTurn;
    private JTextField score1;
    private JTextField score2;
    private JTextField score3;
    //String
    private String playerTurn = "1";
    private String buttonLoc;
    //Int
    private int p1score = 0;
    private int p2score = 0;
    private int tieScore = 0;
    //Boolean
    private boolean activeTie = false;
    private boolean validMove = true;
    //Other
    private JOptionPane messageBox = new JOptionPane();
    private JFrame frame = new JFrame();
    private JScrollPane scroll;
    private JPanel buttonPanel; //Needed to make some tasks more efficient


    //CONSTRUCTOR
    /**
     * Goes to the methods that creates the game board
     */
    public TicTacToeGame() {
        createComponents();
        functionComponents();
        createPanels();
        setSize(600, 500);
    }

    //METHODS
    /**
     * Explains the moves that the player makes by stating the locations of the buttons pressed
     */
    private void updateTurnBoard() {
        String turn = "\nPlayer " + playerTurn + ": (" + (buttonLoc) + ")";
        explainTurn.setText(explainTurn.getText() + turn);
    }

    /**
     * Creates the components (buttons, labels, etc.) of the game board
     */
    private void createComponents() {
        //Buttons
        button1 = new JButton("");
        button2 = new JButton("");
        button3 = new JButton("");
        button4 = new JButton("");
        button5 = new JButton("");
        button6 = new JButton("");
        button7 = new JButton("");
        button8 = new JButton("");
        button9 = new JButton("");
        reset = new JButton("reset");
        //Labels
        player1 = new JLabel("Player 1: ");
        player2 = new JLabel("Player 2: ");
        tie = new JLabel("tie: ");
        //Text Fields and Areas and Panes
        explainTurn = new JTextArea("        ");
        scroll = new JScrollPane (explainTurn);
        scroll.setVerticalScrollBarPolicy (ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS );
        score1 = new JTextField("0");
        score2 = new JTextField("0");
        score3 = new JTextField("0");
        messageBox = new JOptionPane(); //creates pop up message box for telling players stuff
        //Disable text fields and areas to prevent user entries
        explainTurn.setEditable(false);
        score1.setEditable(false);
        score2.setEditable(false);
        score3.setEditable(false);
    }

    /**
     * Adds listeners to the buttons to make them functional
     */
    private void functionComponents() {
        //Creates listeners for the buttons
        MarkListener listener = new MarkListener();
        ResetListener listener2 = new ResetListener();
        //Adds listener for the 9 locations that can be marked in the game
        button1.addActionListener(listener);
        button2.addActionListener(listener);
        button3.addActionListener(listener);
        button4.addActionListener(listener);
        button5.addActionListener(listener);
        button6.addActionListener(listener);
        button7.addActionListener(listener);
        button8.addActionListener(listener);
        button9.addActionListener(listener);
        //Adds listener for the reset button
        reset.addActionListener(listener2);
    }

    /**
     * Creates the panel and adds components to them
     */
    private void createPanels() {
        //MAIN PANEL
        JPanel mainPanel = new JPanel(); //Contains other panels
        mainPanel.setLayout(new BorderLayout());
        //BUTTON PANEL
        buttonPanel = new JPanel(); //Contains buttons
        buttonPanel.setLayout(new GridLayout(3,3));
        //PLAYER BOARD PANEL
        JPanel playerBoard = new JPanel(); //Contains player board info like wins
        playerBoard.setLayout(new FlowLayout());

        //Add buttons to button panel
        buttonPanel.add(button1);
        buttonPanel.add(button2);
        buttonPanel.add(button3);
        buttonPanel.add(button4);
        buttonPanel.add(button5);
        buttonPanel.add(button6);
        buttonPanel.add(button7);
        buttonPanel.add(button8);
        buttonPanel.add(button9);
        //Add buttons, labels, and fields to player board panel
        playerBoard.add(reset);
        playerBoard.add(player1);
        playerBoard.add(score1);
        playerBoard.add(player2);
        playerBoard.add(score2);
        playerBoard.add(tie);
        playerBoard.add(score3);
        //Add panels to main panel
        mainPanel.add(playerBoard,(BorderLayout.NORTH));
        mainPanel.add(buttonPanel, BorderLayout.CENTER);
        mainPanel.add(scroll,(BorderLayout.EAST));
        //Add main panel to frame
        add(mainPanel);
    }

    /**
     * Changes the player's turn
     */
    private void changeTurn(){
        if(playerTurn.equals("1")){
            playerTurn = "2";
        }
        else{
            playerTurn = "1";
        }
    }

    /**
     * Updates the message box to warn user of an invalid move
     */
    private void invalidmessage(){
        messageBox.showMessageDialog (frame, "Invalid Spot! Press a different button.", "Warning", JOptionPane.WARNING_MESSAGE);
        validMove = false; //Invalid move so set to false to prevent the move from being explained at the side
        changeTurn();//When player presses a wrong button a turn passes so skip turn to make it back to their turn
    }

    /**
     * Updates the message box to inform players that someone has won
     */
    private void winnerMessage(){
        messageBox.showMessageDialog (frame, ("Player " + playerTurn + " is the Winner!"), "Game End", JOptionPane.INFORMATION_MESSAGE);
        increaseScore(); //goes to method to increase score of a player
    }

    /**
     * Updates the message box to inform players that a tie has occurred
     */
    private void tieMessage(){
        messageBox.showMessageDialog (frame, ("There is no Winner! It is a Tie!"), "Game End", JOptionPane.INFORMATION_MESSAGE);
        activeTie = true; //Ensures that neither players score increase and instead the tie score increases
        increaseScore(); //goes to method to increase tie score
    }

    /**
     * Increases the score if a player wins or if there is a tie then disables all buttons
     */
    private void increaseScore(){
        if(activeTie == true){ //Increase tie score and updates score board
            tieScore++;
            score3.setText(String.valueOf(tieScore));
            activeTie = false;
        }
        else if(playerTurn.equals("1")){ //Increases player 1 score and updates score board
            p1score++;
            score1.setText(String.valueOf(p1score));
        }
        else if(playerTurn.equals("2")){ //Increases player 2 score and updates score board
            p2score++;
            score2.setText(String.valueOf(p2score));
        }
        disableAll(); //disables buttons to indicate the game has ended
    }

    /**
     * Disables all buttons (except reset) when a winner is determined.
     */
    private void disableAll(){
        Component [] com = buttonPanel.getComponents();
        for(Component component : com) { //Disables all buttons in button panel
            component.setEnabled(false);
        }
        buttonPanel.setVisible(false); //Needed for score update to show up on screen
        buttonPanel.setVisible(true);
    }

    /**
     * Reverts the text of all buttons when the reset button is pressed and enables buttons
     */
    private void resetAll(){
        Component [] com = buttonPanel.getComponents();
        for(Component component : com) { //Enables all buttons in button panel
            component.setEnabled(true);
        }
        button1.setText("");
        button2.setText("");
        button3.setText("");
        button4.setText("");
        button5.setText("");
        button6.setText("");
        button7.setText("");
        button8.setText("");
        button9.setText("");
    }

    /**
     * Checks all possible winning scenarios to determine if a player has won
     */
    private void checkWinner(){
        if(button1.getText().equals(playerTurn) && button5.getText().equals(playerTurn) && button9.getText().equals(playerTurn)){
            winnerMessage();
        }
        else if(button3.getText().equals(playerTurn) && button5.getText().equals(playerTurn) && button7.getText().equals(playerTurn)){
            winnerMessage();
        }
        else if(button1.getText().equals(playerTurn) && button2.getText().equals(playerTurn) && button3.getText().equals(playerTurn)){
            winnerMessage();
        }
        else if(button4.getText().equals(playerTurn) && button5.getText().equals(playerTurn) && button6.getText().equals(playerTurn)){
            winnerMessage();
        }
        else if(button7.getText().equals(playerTurn) && button8.getText().equals(playerTurn) && button9.getText().equals(playerTurn)){
            winnerMessage();
        }
        else if(button1.getText().equals(playerTurn) && button4.getText().equals(playerTurn) && button7.getText().equals(playerTurn)){
            winnerMessage();
        }
        else if(button2.getText().equals(playerTurn) && button5.getText().equals(playerTurn) && button8.getText().equals(playerTurn)){
            winnerMessage();
        }
        else if(button3.getText().equals(playerTurn) && button6.getText().equals(playerTurn) && button9.getText().equals(playerTurn)){
            winnerMessage();
        }
        //Check for tie
        else if(!button1.getText().isEmpty() && !button2.getText().isEmpty() && !button3.getText().isEmpty() && !button4.getText().isEmpty() && !button5.getText().isEmpty() && !button6.getText().isEmpty() && !button7.getText().isEmpty() && !button8.getText().isEmpty() && !button9.getText().isEmpty()){
            tieMessage();
        }
    }

    /**
     * Performs methods when a player marks/presses one of the 9 locations in the tic tac toe game
     */
    class MarkListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
                if (event.getSource() == button1) { //Check which buttons was pressed
                    if (button1.getText().isEmpty()) { //Check if the button has text
                        button1.setText(playerTurn); //If there is no text then mark it as the player's spot
                        buttonLoc = ("1,1"); //Store location of button to explain the move at the side
                    }else{
                        invalidmessage(); //If there is text then tell player, invalid move
                    }
                }
                if (event.getSource() == button2) {
                    if (button2.getText().isEmpty()) {
                        button2.setText(playerTurn);
                        buttonLoc = ("1,2");
                    }else{
                        invalidmessage();
                    }
                }
            if (event.getSource() == button3) {
                if (button3.getText().isEmpty()) {
                    button3.setText(playerTurn);
                    buttonLoc = ("1,3");
                }else{
                    invalidmessage();
                }
            }
            if (event.getSource() == button4) {
                if (button4.getText().isEmpty()) {
                    button4.setText(playerTurn);
                    buttonLoc = ("2,1");
                }else{
                    invalidmessage();
                }
            }
            if (event.getSource() == button5) {
                if (button5.getText().isEmpty()) {
                    button5.setText(playerTurn);
                    buttonLoc = ("2,2");
                }else{
                    invalidmessage();
                }
            }
            if (event.getSource() == button6) {
                if (button6.getText().isEmpty()) {
                    button6.setText(playerTurn);
                    buttonLoc = ("2,3");
                }else{
                    invalidmessage();
                }
            }
            if (event.getSource() == button7) {
                if (button7.getText().isEmpty()) {
                    button7.setText(playerTurn);
                    buttonLoc = ("3,1");
                }else{
                    invalidmessage();
                }
            }
            if (event.getSource() == button8) {
                if (button8.getText().isEmpty()) {
                    button8.setText(playerTurn);
                    buttonLoc = ("3,2");
                }else{
                    invalidmessage();
                }
            }
            if (event.getSource() == button9) {
                if (button9.getText().isEmpty()) {
                    button9.setText(playerTurn);
                    buttonLoc = ("3,3");
                }else{
                    invalidmessage();
                }
            }
            //Change turn, update board and check for winner
            if(validMove == true) { //Only explains the move at the side if the move is valid
                updateTurnBoard();
            }
                checkWinner();
                changeTurn();
                validMove = true;
            }
        }

    /**
     * Resets buttons when reset button is pressed and makes player 1 start
     */
        class ResetListener implements ActionListener{
            public void actionPerformed(ActionEvent e) {
                resetAll();
                playerTurn = "1";
            }
        }
    }
