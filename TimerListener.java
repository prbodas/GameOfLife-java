import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.*;
public class TimerListener implements ActionListener
{
    public void actionPerformed(ActionEvent e)
    {
        SimpleDraw1.timer.setDelay(SliderListener.sliderValue);
        Life a = new Life("");
        BoardPanel b = new BoardPanel();
        a.current = b.generateLifeArray();
        a.runLife(1);
        b.showLifeOnScreen(a.current);
        SimpleDraw1.frame.setTitle("Life Generation " + a.generation);
    }
}
