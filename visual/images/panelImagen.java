/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package visual.images;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Y2K
 */
public class panelImagen extends JPanel {

    private JLabel lblImage;

    public panelImagen() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setPreferredSize(new Dimension(300, 200));
        lblImage = new JLabel();
        lblImage.setPreferredSize(new Dimension(150, 200));
        lblImage.setAlignmentX(Component.CENTER_ALIGNMENT);
        changeImage(0);
        add(lblImage, BorderLayout.CENTER);

    }

    public void changeImage(int numberTries) {
        if (numberTries <= 7) {
            try {
                lblImage.setIcon(new ImageIcon(
                        ImageIO.read(getClass().getResource("/visual/images/" + (numberTries) + ".png"))
                                .getScaledInstance(150, 200, WIDTH)));
            } catch (IOException ex) {
            }
            this.revalidate();
            this.repaint();
        }
    }

}
