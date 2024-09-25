import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * A simple calculator UI that uses CalcEngine for calculations.
 */
public class CalculatorUI {
    private CalcEngine engine;
    private JFrame frame;
    private JTextField display;

    public CalculatorUI() {
        engine = new CalcEngine();
        createUI();
    }

    private void createUI() {
        frame = new JFrame("Java Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 400);
        frame.setLayout(new BorderLayout());

        display = new JTextField();
        display.setEditable(false);
        display.setHorizontalAlignment(SwingConstants.RIGHT);
        frame.add(display, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4, 4));

        // Number buttons
        for (int i = 1; i <= 9; i++) {
            buttonPanel.add(createButton(String.valueOf(i)));
        }
        buttonPanel.add(createButton("0"));
        buttonPanel.add(createButton("+"));
        buttonPanel.add(createButton("-"));
        buttonPanel.add(createButton("="));
        buttonPanel.add(createButton("C"));

        frame.add(buttonPanel, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    private JButton createButton(String label) {
        JButton button = new JButton(label);
        button.addActionListener(new ButtonClickListener());
        return button;
    }

    private class ButtonClickListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();

            if (command.matches("\\d")) { // If the button is a digit
                engine.numberPressed(Integer.parseInt(command));
            } else if (command.equals("+")) {
                engine.plus();
            } else if (command.equals("-")) {
                engine.minus();
            } else if (command.equals("=")) {
                engine.equals();
            } else if (command.equals("C")) {
                engine.clear();
            }

            // Update the display
            display.setText(String.valueOf(engine.getDisplayValue()));
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(CalculatorUI::new);
    }
}
