import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NumbGuess extends JFrame implements ActionListener {
    private int secretNumber;
    private int attempts;
    private JTextField guessInput;
    private JLabel feedbackLabel;
    private JLabel attemptsLabel;
    private JButton submitButton;
    private JButton playAgainButton;

    public NumbGuess() {
        setTitle("Number Guessing Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 250);
        setLocationRelativeTo(null);
        setResizable(false);

        // Initialize game
        secretNumber = (int)(Math.random() * 100) + 1;
        attempts = 0;

        // Main panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Title label
        JLabel titleLabel = new JLabel("Guess a number between 1 and 100");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 14));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(titleLabel);

        mainPanel.add(Box.createVerticalStrut(20));

        // Input panel
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.X_AXIS));
        inputPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel inputLabel = new JLabel("Your guess: ");
        guessInput = new JTextField(10);
        guessInput.setMaximumSize(new Dimension(100, 30));
        guessInput.addActionListener(this);

        inputPanel.add(inputLabel);
        inputPanel.add(Box.createHorizontalStrut(10));
        inputPanel.add(guessInput);

        mainPanel.add(inputPanel);

        mainPanel.add(Box.createVerticalStrut(15));

        // Submit button
        submitButton = new JButton("Submit");
        submitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        submitButton.addActionListener(this);
        mainPanel.add(submitButton);

        mainPanel.add(Box.createVerticalStrut(15));

        // Feedback label
        feedbackLabel = new JLabel("Enter a number and click Submit!");
        feedbackLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        feedbackLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(feedbackLabel);

        mainPanel.add(Box.createVerticalStrut(10));

        // Attempts label
        attemptsLabel = new JLabel("Attempts: 0");
        attemptsLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        attemptsLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(attemptsLabel);

        mainPanel.add(Box.createVerticalStrut(15));

        // Play Again button
        playAgainButton = new JButton("Play Again");
        playAgainButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        playAgainButton.addActionListener(this);
        playAgainButton.setVisible(false);
        mainPanel.add(playAgainButton);

        add(mainPanel);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submitButton || e.getSource() == guessInput) {
            try {
                int guess = Integer.parseInt(guessInput.getText());
                guessInput.setText("");

                if (guess < 1 || guess > 100) {
                    feedbackLabel.setText("Please enter a number between 1 and 100.");
                    return;
                }

                attempts++;
                attemptsLabel.setText("Attempts: " + attempts);

                if (guess < secretNumber) {
                    feedbackLabel.setText("Too low! Try again.");
                } else if (guess > secretNumber) {
                    feedbackLabel.setText("Too high! Try again.");
                } else {
                    feedbackLabel.setText("Congratulations! You guessed it in " + attempts + " attempts!");
                    submitButton.setEnabled(false);
                    guessInput.setEnabled(false);
                    playAgainButton.setVisible(true);
                }
            } catch (NumberFormatException ex) {
                feedbackLabel.setText("Please enter a valid number.");
            }
        } else if (e.getSource() == playAgainButton) {
            // Reset game
            secretNumber = (int)(Math.random() * 100) + 1;
            attempts = 0;
            guessInput.setText("");
            guessInput.setEnabled(true);
            submitButton.setEnabled(true);
            feedbackLabel.setText("Enter a number and click Submit!");
            attemptsLabel.setText("Attempts: 0");
            playAgainButton.setVisible(false);
            guessInput.requestFocus();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new NumbGuess());
    }
}