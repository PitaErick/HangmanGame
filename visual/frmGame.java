package visual;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import model.InstanceWord;
import visual.images.panelImagen;

/**
 *
 * @author Y2K
 */
public class frmGame extends JFrame {

    private JPanel pnMain;
    private panelImagen pnImage;
    private JLabel title;
    private JTextField txtChar;
    private JLabel lblWord;
    private JButton btnReset;
    private int numberTries = 0;
    private Font font = new Font("Consolas", Font.BOLD, 20);

    public frmGame() {
        init();
        pnMain.add(Box.createVerticalGlue());
        pnMain.add(title, BorderLayout.CENTER);
        pnMain.add(pnImage, BorderLayout.CENTER);
        JPanel pn = new JPanel();
        pn.setLayout(new BoxLayout(pn, BoxLayout.Y_AXIS));
        pn.add(lblWord);
        pn.add(txtChar);
        pnMain.add(pn, BorderLayout.CENTER);
        pnMain.add(Box.createVerticalGlue());
        pnMain.add(btnReset, BorderLayout.SOUTH);
        pnMain.add(Box.createVerticalGlue());
        getContentPane().add(pnMain);
        this.revalidate();
        this.repaint();
    }

    private void init() {
        pnMain = new JPanel();
        pnImage = new panelImagen();
        title = new JLabel("JUEGO DEL AHORCADO");
        lblWord = new JLabel(InstanceWord.getInstance().getUnderscores());
        lblWord.setFont(font);
        title.setFont(font);
        btnReset = new JButton("Reset");
        txtChar = new JTextField();
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        pnMain.setLayout(new FlowLayout());
        pnMain.setBorder(new EmptyBorder(20, 50, 20, 50));
        title.setBorder(new EmptyBorder(0, 0, 20, 0));
        lblWord.setBorder(new EmptyBorder(30, 0, 20, 0));
        btnReset.setAlignmentY(Component.BOTTOM_ALIGNMENT);
        setTitle("Ahorcado");
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(250, 460));
        this.setLocationRelativeTo(null);
        pack();
        txtChar.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isLetter(c) || txtChar.getText().length() == 1) {
                    e.consume();
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
        txtChar.addActionListener((ActionEvent e) -> {
            try {
                String newWord = InstanceWord.getInstance().checkLetter(txtChar.getText().trim());
                if (newWord != null) {
                    lblWord.setText(newWord.toUpperCase());
                    if (!lblWord.getText().contains("_")) {
                        notifyWin(true);
                    }
                } else {
                    numberTries++;
                    pnImage.changeImage(numberTries);
                    if (numberTries == 7) {
                        notifyWin(false);
                    }
                }
            } catch (java.lang.StringIndexOutOfBoundsException ex) {
            }
            txtChar.setText("");
            this.revalidate();
            this.repaint();

        });
        btnReset.addActionListener((e) -> {
            numberTries = 0;
            InstanceWord.getInstance().resetWord();
            pnImage.changeImage(numberTries);
            lblWord.setText(InstanceWord.getInstance().getUnderscores());
            txtChar.setText("");
            txtChar.setEnabled(true);
        });
    }

    private void notifyWin(boolean WinOrLost) {
        if (WinOrLost) {
            JOptionPane.showMessageDialog(this, "You Win!!!");
        } else {
            lblWord.setText(InstanceWord.getInstance().getWord().toUpperCase());
            JOptionPane.showMessageDialog(this, "You Lost!!!");
        }
        txtChar.setEnabled(false);
    }

}
