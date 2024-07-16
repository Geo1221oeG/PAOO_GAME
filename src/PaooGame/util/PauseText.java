package PaooGame.util;

import java.awt.*;

public class PauseText {
    public static void Draw(Graphics g) //pentru scrierea textului P=PAUSE
    {
        Font fnt = new Font("DialogInput", Font.BOLD, 15);
        g.setFont(fnt);
        g.setColor(Color.white);
        g.drawString("P=Pause", 20, 550);

    }
}
