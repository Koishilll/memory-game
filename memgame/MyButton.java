package memgame;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;


class MyButton extends JButton {
    MyButton() {
        if (space == null) {
            space = new ImageIcon("./memgame/space.png");
        }
        if (question == null) {
            question = new ImageIcon("./memgame/question.png");
        }
    }
    MyButton(int x, int y) {
        this();
        this.x = x;
        this.y = y;
    }
    @Override
    public void setIcon(Icon defaultIcon) {
        super.setIcon(defaultIcon);
        myImage = defaultIcon;
    }
    public void setVisible() {
        super.setIcon(myImage);
    }
    public void setInvisible() {
        super.setIcon(space);
    }
    public void setQuestion() {
        super.setIcon(question);
    }
    public int x, y;
    public boolean goodbye;
    public Icon myImage;
    public static Icon space;
    public static Icon question;
}
