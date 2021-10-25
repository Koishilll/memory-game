package memgame;

import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;
import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

class ClickAction implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        MyButton be = MemoryGame.beforeButton;
        MyButton mb = (MyButton)e.getSource();
        if (mb.goodbye) { return; }

        if (be == null) {
            MemoryGame.beforeButton = mb;
            mb.setVisible();
        } else {
            if (be == mb) {
                return;
            }
            int bx = be.x;
            int by = be.y;
            int mx = mb.x;
            int my = mb.y;
            if (MemoryGame.randAr[bx][by] ==
                MemoryGame.randAr[mx][my]
            ) {
                be.goodbye = true;
                mb.goodbye = true;
                be.setInvisible();
                mb.setInvisible();
                MemoryGame.numGoodbye += 2;
                if (MemoryGame.numGoodbye == 30) {
                    Object options[] = {
                        "再开",
                        "退出",
                    };
                    int ret = JOptionPane.showOptionDialog(
                        null,
                        "恭喜通关！可喜可贺！",
                        "通关",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.INFORMATION_MESSAGE,
                        new ImageIcon("./memgame/look.jpg"),
                        options,
                        options[0]
                    );
                    System.out.println("按钮返回值: " + ret);
                    if (ret == 0) {
                        MemoryGame.init();
                    } else {
                        System.exit(0);
                    }
                }
            } else {
                mb.setVisible();
                Timer timer = new Timer();
                TimerTask timerTask = new TimerTask() {
                    public MyButton mb1 = be, mb2 = mb;
                    public void run() {
                        mb1.setQuestion();
                        mb2.setQuestion();
                    }
                };
                timer.schedule(timerTask, 250);
                // be.setQuestion();
                // mb.setQuestion();
            }
            MemoryGame.beforeButton = null;
        }
        // System.out.print(mb.x + ", " + mb.y + "    ");
    }
}
