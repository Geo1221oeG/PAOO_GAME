package PaooGame.inputs;


import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static java.awt.event.KeyEvent.*;

public class Key_Handler implements KeyListener {
    boolean[] pressed=new boolean[10];


    public Key_Handler()
    {

    }
    @Override
    public void keyTyped(KeyEvent e) {}
    enum Taste {
        SPACE, //0
        TASTA1, //1
        TASTA2,//2
        TASTA3,//3
        TASTA4,//4
        W,//5
        A,//6
        S,//7
        D,//8
        E//9
    };

    @Override
    public void keyPressed(KeyEvent e) {
        //e verific daca are unu din codurile date din tabel si schimb in vectorul de frecventa

        switch(e.getKeyCode()) {
            case VK_A:
                pressed[Taste.A.ordinal()]= true;

                //player.update(-5,0);
                break;
            case VK_W:
                pressed[Taste.W.ordinal()]= true;

                break;
            case VK_E:
                pressed[Taste.E.ordinal()]= true;
                //player.update(-5,0); todo:aici o sa trebuiasca facut altfel pentru culeS
                break;
            case VK_S:
                pressed[Taste.S.ordinal()]= true;

                break;
            case VK_D:
                pressed[Taste.D.ordinal()]= true;

                break;
            case VK_1:
                pressed[Taste.TASTA1.ordinal()]= true;
                //todo:special si aici
                break;
            case VK_2:
                pressed[Taste.TASTA2.ordinal()]= true;
                //todo:special si aici
                break;
            case VK_3:
                pressed[Taste.TASTA3.ordinal()]= true;
                //todo:special si aici
                break;
            case VK_4:
                pressed[Taste.TASTA4.ordinal()]=true;
                //todo:special si aici
                break;
            case VK_SPACE:
                pressed[Taste.SPACE.ordinal()]=true;

                break;

        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

        switch(e.getKeyCode()) {
            case VK_A:
                pressed[Taste.A.ordinal()]= false;
                break;
            case VK_W:
                pressed[Taste.W.ordinal()]= false;
                break;
            case VK_E:
                pressed[Taste.E.ordinal()]= false;
                break;
            case VK_S:
                pressed[Taste.S.ordinal()]= false;
                break;
            case VK_D:
                pressed[Taste.D.ordinal()]= false;
                break;
            case VK_1:
                pressed[Taste.TASTA1.ordinal()]= false;
                break;
            case VK_2:
                pressed[Taste.TASTA2.ordinal()]= false;
                break;
            case VK_3:
                pressed[Taste.TASTA3.ordinal()]= false;
                break;
            case VK_4:
                pressed[Taste.TASTA4.ordinal()]=false;
                break;
            case VK_SPACE:
                pressed[Taste.SPACE.ordinal()]=false;
                break;
            default:
                break;
        }
    }
}
