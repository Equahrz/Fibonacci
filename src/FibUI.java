import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigInteger;

public class FibUI {
    private JFrame frame;
    private JTextField textField;
    private JLabel resultLabel;
    private FibCalculator calculator;

    public FibUI() {
        this.calculator = new FibCalculator();
    }

    public void createAndShowGUI() {

        frame = new JFrame("Fibonacci Rechner");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 200);
        frame.setLayout(new BorderLayout());
        frame.setResizable(false);


        frame.setLocationRelativeTo(null);


        JPanel panel = new JPanel();
        JLabel label = new JLabel("Bitte geben Sie die Position der Fibonacci-Zahl ein:");
        textField = new JTextField(10);
        JButton button = new JButton("Berechnen");
        resultLabel = new JLabel("Die Fibonacci-Zahl wird hier angezeigt.");

        panel.add(label);
        panel.add(textField);
        panel.add(button);

        frame.add(panel, BorderLayout.CENTER);
        frame.add(resultLabel, BorderLayout.SOUTH);


        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculateFibonacci();
            }
        });


        frame.setVisible(true);
    }

    private void calculateFibonacci() {
        try {
            int n = Integer.parseInt(textField.getText());

            if (n < 0) {
                resultLabel.setText("Bitte geben Sie eine positive ganze Zahl ein.");
            } else {

                SwingWorker<BigInteger, Void> worker = new SwingWorker<BigInteger, Void>() {
                    @Override
                    protected BigInteger doInBackground() throws Exception {
                        return calculator.fibonacci(n);
                    }

                    @Override
                    protected void done() {
                        try {
                            BigInteger fibonacciNumber = get();
                            resultLabel.setText("Die " + n + ". Fibonacci-Zahl ist: " + fibonacciNumber);
                        } catch (Exception ex) {
                            resultLabel.setText("Fehler bei der Berechnung.");
                        }
                    }
                };
                worker.execute();
            }
        } catch (NumberFormatException ex) {
            resultLabel.setText("Ungültige Eingabe. Bitte geben Sie eine ganze Zahl ein.");
        }
    }
}