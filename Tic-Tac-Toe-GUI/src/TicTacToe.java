import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class TicTacToe {
    private JFrame frame;
    private JButton[][] buttons = new JButton[3][3];
    private boolean currentPlayer = true; // true for player X, false for player O
    private boolean isVsAI = false; // Flag to check if it's a vs AI game

    public TicTacToe() {
        String[] options = { "Player vs Player", "Player vs AI", "Cancel" };
        int choice = JOptionPane.showOptionDialog(frame, "Choose Game Mode", "Tic-Tac-Toe",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
        if (choice == 0) {
            isVsAI = false;
        } else if (choice == 1) {
            isVsAI = true;
        } else {
            System.exit(0);
        }
        frame = new JFrame("Tic-Tac-Toe");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(3, 3));
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j] = new JButton();
                buttons[i][j].setFont(new Font("Berlin Sans FB Demi", Font.ROMAN_BASELINE, 60));
                buttons[i][j].setFocusPainted(false);
                buttons[i][j].setActionCommand(i + "," + j); // Set action command to coordinates
                buttons[i][j].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        JButton buttonclicked = (JButton) e.getSource();
                        String[] pos = buttonclicked.getActionCommand().split(",");
                        int row = Integer.parseInt(pos[0]);
                        int col = Integer.parseInt(pos[1]);
                        
                        if (buttons[row][col].getText().equals("")) { // Check if the button is empty
                            buttons[row][col].setText(currentPlayer ? "X" : "O");
                            buttons[row][col].setForeground(currentPlayer ? Color.RED : Color.BLUE);
                            if (checkWin(row, col)) {
                                JOptionPane.showMessageDialog(frame,
                                        "Player " + (currentPlayer ? "X" : "O") + " wins!"); // Show win message
                                resetGame();
                            } else if (isBoardFull()) {
                                JOptionPane.showMessageDialog(frame, "It's a draw!"); // Show draw message
                                resetGame();
                            }
                            if (isVsAI && currentPlayer) {
                                AImove(); // AI makes a move if it's player O's turn
                                currentPlayer = !currentPlayer; // Switch back to player X after AI move
                            }
                            currentPlayer = !currentPlayer; // Switch player
                        }
                    }
                });
                frame.add(buttons[i][j]); // Add button to the frame
            }
        }
    }

    public void Display() {
        frame.setSize(400, 400);
        frame.setVisible(true);
    }

    public boolean checkWin(int row, int col) {

        // Check row
        if (buttons[row][0].getText().equals(buttons[row][1].getText())
                && buttons[row][0].getText().equals(buttons[row][2].getText())
                && !buttons[row][0].getText().equals("")) {
            buttons[row][0].setBackground(Color.GREEN);
            buttons[row][1].setBackground(Color.GREEN);
            buttons[row][2].setBackground(Color.GREEN);
            return true;
        }
        // Check column
        if (buttons[0][col].getText().equals(buttons[1][col].getText())
                && buttons[0][col].getText().equals(buttons[2][col].getText())
                && !buttons[0][col].getText().equals("")) {
            buttons[0][col].setBackground(Color.GREEN);
            buttons[1][col].setBackground(Color.GREEN);
            buttons[2][col].setBackground(Color.GREEN);
            return true;
        }
        // Check diagonal
        if (row == col) {
            if (buttons[0][0].getText().equals(buttons[1][1].getText())
                    && buttons[0][0].getText().equals(buttons[2][2].getText())
                    && !buttons[0][0].getText().equals("")) {
                buttons[0][0].setBackground(Color.GREEN);
                buttons[1][1].setBackground(Color.GREEN);
                buttons[2][2].setBackground(Color.GREEN);
                return true;
            }
        }
        if (row + col == 2) {
            if (buttons[0][2].getText().equals(buttons[1][1].getText())
                    && buttons[0][2].getText().equals(buttons[2][0].getText())
                    && !buttons[0][2].getText().equals("")) {
                buttons[0][2].setBackground(Color.GREEN);
                buttons[1][1].setBackground(Color.GREEN);
                buttons[2][0].setBackground(Color.GREEN);
                return true;
            }
        }
        return false;
    }

    public boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (buttons[i][j].getText().equals("")) {
                    return false; // Found an empty cell
                }
            }
        }
        return true; // No empty cells found
    }

    public void resetGame() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText(""); // Clear the button text
                buttons[i][j].setBackground(Color.WHITE); // Reset button background color
            }
        }
        currentPlayer = true; // Reset to player X
    }

    public void color() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setBackground(java.awt.Color.WHITE); // Reset button background color
            }
        }
        currentPlayer = true; // Reset to player X
    }

    public void AImove() {
        Random random = new Random();
        int row, col;
        do {
            row = random.nextInt(3);
            col = random.nextInt(3);
        } while (!buttons[row][col].getText().equals("")); // Find an empty cell
        buttons[row][col].setText("O"); // AI plays as O
        buttons[row][col].setForeground(Color.BLUE);
        if (checkWin(row, col)) {
            JOptionPane.showMessageDialog(frame, "Aman Legend wins!"); // Show AI win message
            resetGame();
        } else if (isBoardFull()) {
            JOptionPane.showMessageDialog(frame, "It's a draw!"); // Show draw message
            resetGame();
        }
    }
}

