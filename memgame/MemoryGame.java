package memgame;

import java.awt.Color;
import java.awt.FlowLayout;
import java.util.Date;
import java.util.Random;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;


public class MemoryGame {
    public static void main(String[] args) {
        JFrame jFrame = new JFrame("记忆力测试小游戏");
        jFrame.setLayout(new FlowLayout(FlowLayout.LEFT, 15, 15)); //按钮间距
        buttons = new MyButton[30];
        MyButton myButton;

        for (int i = 0; i < 5; ++i) {
            for (int j = 0; j < 6; ++j) {
                ClickAction clickAction = new ClickAction();
                myButton = new MyButton(i, j);
                //设定透明效果
                myButton.setOpaque(false);
                //去掉背景点击效果
                myButton.setContentAreaFilled(false);
                //去掉聚焦线
                myButton.setFocusPainted(false);
                //去掉边框
                myButton.setBorder(null);
                myButton.addActionListener(clickAction);
                jFrame.add(myButton);
                buttons[i * 6 + j] = myButton;
            }
        }

        init();

        jFrame.setSize(720, 630);    //设置容器大小
        jFrame.setVisible(true);     //显示容器
        jFrame.setLocation(400, 100);    //设置容器位置
        jFrame.getContentPane().setBackground(Color.ORANGE); //设置背景颜色

        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    //关掉窗口结束程序的运行
    }

    public static void init() {
        int ar[] = {
            1, 2, 3, 4, 5, 1, 2, 3, 4, 5, 1, 2, 3, 4, 5,
            1, 2, 3, 4, 5, 1, 2, 3, 4, 5, 1, 2, 3, 4, 5,
        };

        Random random = new Random(new Date().getTime());

        for (int i = 0; i < 100000; ++i) {
            int idx1 = random.nextInt(30);
            int idx2 = random.nextInt(30);
            if (idx1 == idx2) { continue; }
            int temp = ar[idx1];
            ar[idx1] = ar[idx2];
            ar[idx2] = temp;
        }

        randAr = new int[5][];
        for (int i = 0; i < 5; ++i) {
            randAr[i] = new int[6];
            for (int j = 0; j < 6; ++j) {
                randAr[i][j] = ar[i * 6 + j];

                Icon icon = new ImageIcon("./memgame/" + randAr[i][j] + ".png");
                MyButton myButton = buttons[i * 6 + j];
                ((JButton)myButton).setIcon(icon);
                myButton.setQuestion();
                myButton.goodbye = false;
            }
        }
        numGoodbye = 0;
    }
    static int[][] randAr;
    static MyButton[] buttons;
    static MyButton beforeButton;
    static int numGoodbye;
}
