import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.*;
public class MyFrame extends JFrame {
    private final int width;
    private final int height;
    private Image img;
    private JPanel menuPanel = new JPanel();
    private JFrame about = new JFrame("About");
    private JFrame scores = new JFrame("High scores");
    private JButton aboutButton = new JButton("About");
    private JButton startButton = new JButton("New Game");
    private JButton scoresButton = new JButton("High Scores");
    private class ButtonsListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();
            if (command.equals("about")) {
                about.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                about.setResizable(false);
                about.setLocationRelativeTo(null); // окно - в центре экрана
                about.setSize(width, height + 40);

                img = new ImageIcon("src/about.png").getImage();
                JPanel panel = new JPanel() {
                    @Override
                    protected void paintComponent(Graphics g) {
                        super.paintComponent(g);
                        g.drawImage(img, 0, 0, width, height, null);
                    }
                };
                about.add(panel);
                about.setVisible(true);
            }
            else if (command.equals("scores")) {
                scores.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                scores.setResizable(false);
                scores.setLocationRelativeTo(null); // окно - в центре экрана
                scores.setSize(width, height);
                Image img1 = new ImageIcon("src/1.png").getImage();
                Image img2 = new ImageIcon("src/2.png").getImage();
                Image img3 = new ImageIcon("src/3.png").getImage();
                JPanel panel = new JPanel() {
                    @Override
                    protected void paintComponent(Graphics g) {
                        super.paintComponent(g);
                        g.drawImage(img1, 55, 35, 60, 75,  null);
                        g.drawImage(img2, 55, 145, 60, 75,  null);
                        g.drawImage(img3, 55, 255, 60, 75, null);
                        g.drawRect(200, 45, 150, 50);
                        g.drawRect(200, 155, 150, 50);
                        g.drawRect(200, 265, 150, 50);
                    }
                };

                JLabel label = new JLabel(Integer.toString(Controller.getRecords()[0]));
                label.setFont(new Font("Arial", Font.PLAIN, 28));
                label.setBounds(220, 45, 150, 50);

                JLabel label2 = new JLabel(Integer.toString(Controller.getRecords()[1]));
                label2.setFont(new Font("Arial", Font.PLAIN, 28));
                label2.setBounds(220, 155, 150, 50);

                JLabel label3 = new JLabel(Integer.toString(Controller.getRecords()[2]));
                label3.setFont(new Font("Arial", Font.PLAIN, 28));
                label3.setBounds(220, 265, 150, 50);

                panel.setLayout(null);

                panel.add(label);
                panel.add(label2);
                panel.add(label3);

                scores.add(panel);
                scores.setVisible(true);
            }
            else if (command.equals("start")) {
                setVisible(false);
                about.setVisible(false);
                scores.setVisible(false);
                Main.frame.setVisible(true);
                Controller.action = "down";
            }
        }
    }
    MyFrame(String name, int w, int h) {
        super(name);
        width = w;
        height = h;
        setSize(width, height);

        ActionListener myButtonsListener = new ButtonsListener();

        // добавляем команду кнопке Старт
        aboutButton.setActionCommand("about");
        startButton.setActionCommand("start");
        scoresButton.setActionCommand("scores");

        // к каждой из кнопок добавляем слушателя
        aboutButton.addActionListener(myButtonsListener);
        startButton.addActionListener(myButtonsListener);
        scoresButton.addActionListener(myButtonsListener);

        // добавляем кнопки на панель
        menuPanel.add(aboutButton);
        menuPanel.add(startButton);
        menuPanel.add(scoresButton);

        // размещаем панель на Frame
        getContentPane().add(menuPanel);
    }
}
