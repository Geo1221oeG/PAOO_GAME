package PaooGame.util;

import PaooGame.DataBase.DatabaseForLevelResume;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class WindowManager implements WindowListener {
    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {

        DatabaseForLevelResume.insertData(Referinta.player.level,Referinta.player.getX(),Referinta.player.getY(),Referinta.player.getHp(),Referinta.player.getAfine(),Timer.timp); //for saving press P :D

    }

    @Override
    public void windowClosed(WindowEvent e) {

    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }
}
