import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
public class SliderListener implements ChangeListener
{
    public static int sliderValue = 500;
    public void stateChanged(ChangeEvent e)
    {
        sliderValue = SimpleDraw1.slider.getValue();
    }
}
