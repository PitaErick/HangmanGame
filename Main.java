
import javax.swing.SwingUtilities;

import visual.frmGame;

public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new frmGame();
        });
    }
}
