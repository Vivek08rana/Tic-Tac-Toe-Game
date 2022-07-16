/**
 This program is a driver class that sets up details of the frame for the tic tac toe game
 */
import javax.swing.*;

public class TicTacToe
{
    public static void main(String[] args)
    {
        JFrame frame = new TicTacToeGame();
        frame.setTitle("Tic Tac Toe Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}