import java.awt.*; 
import javax.swing.*;
import java.awt.event.*;
public class BoardPanel extends JPanel implements MouseListener, MouseMotionListener, ActionListener
{

    public void mouseEntered(MouseEvent e)
    {
        //System.out.print("Entered");
        if (SimpleDraw1.pressed)
        {
            mouseDragged(e);
        }
    }

    public void mouseExited(MouseEvent e)
    {
    }

    public void mousePressed(MouseEvent e)
    {
        SimpleDraw1.pressed = true;
    }

    public void mouseClicked(MouseEvent e)
    {
        //System.out.print("clicked");
    }

    public void mouseReleased(MouseEvent e)
    {
        SimpleDraw1.pressed = false;
    }

    public void mouseDragged(MouseEvent e)
    {
        if (e.getButton() == e.BUTTON1)
        {
            // make panel a color
            //System.out.println("B1");
            //this.paintComponent(getGraphics());
            //this.repaint();
            //Graphics a = getGraphics();
            //super.paintComponent(a);
            //a.setColor(Color.yellow);

            this.setBackground(SimpleDraw1.color);
            Graphics a = getGraphics();
            Graphics2D gg = (Graphics2D)a;
            gg.setBackground(SimpleDraw1.color);
            update(gg);

            //this.setOpaque(true);
            //this.setBackground(Color.yellow);

            //this.paintComponent(a);
            //super.paintComponent(getGraphics());

        }else if (e.getButton() == e.BUTTON3)
        {
            // make paenl white

            this.setBackground(Color.white);
            Graphics a = getGraphics();
            Graphics2D gg = (Graphics2D)a;
            gg.setBackground(Color.white);
            update(gg);
            //super.paintComponent(getGraphics());
            //this.setBackground(Color.red);
        }

    }

    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        //this.setBackground(Color.red);
        //Graphics2D gg = (Graphics2D)g;
        //gg.setBackground(color);
    }

    public void mouseMoved(MouseEvent e)
    {
    }

    public void actionPerformed(ActionEvent e)
    {
        if (e.getActionCommand().equals("RED"))
        {
            // make color blue
            SimpleDraw1.color = Color.red;
        }else if (e.getActionCommand().equals("BLUE"))
        {
            // make panel white
            SimpleDraw1.color = Color.blue;
        }else if (e.getActionCommand().equals("GREEN"))
        {
            SimpleDraw1.color = Color.green;
        }else if (e.getActionCommand().equals("CLEAR"))

        {
            //  make all white
            for (int i = 0; i<SimpleDraw1.arr.length; i++)
            {
                SimpleDraw1.arr[i].setBackground(Color.white);
                Graphics a = getGraphics();
                Graphics2D gg = (Graphics2D)a;
                //gg.setBackground(Color.white);
                update(gg);
            }
            Life.generation = 0;
            SimpleDraw1.frame.setTitle("Life Generation " + Life.generation);
        } else if (e.getActionCommand().equals("CUSTOM"))
        {
            SimpleDraw1.color = JColorChooser.showDialog(new JFrame(), "Custom Colours" , Color.white);
        }else if (e.getActionCommand().equals("RUN"))
        {
            // RUN LIFE SOMEHOW
            Life a = new Life("");
            a.current = generateLifeArray();
            a.runLife(1);
            showLifeOnScreen(a.current);
            SimpleDraw1.frame.setTitle("Life Generation " + a.generation);
        }else if (e.getActionCommand().equals("RUNCONT"))
        {
            SimpleDraw1.continuous = !SimpleDraw1.continuous;
            if(SimpleDraw1.continuous)
            {
                SimpleDraw1.timer.start();
            }else
            {
                SimpleDraw1.timer.stop();
            }
        }
    }

    public  void showLifeOnScreen(int[][] ad)
    {
        int index = 0;
        for (int i = 0; i<SimpleDraw1.ROWS; i++)
        {
            for (int j = 0; j<SimpleDraw1.COLS; j++)
            {
                if (ad[i][j] == 0)
                {
                    SimpleDraw1.arr[index].setBackground(Color.white);
                    Graphics a = getGraphics();
                    Graphics2D gg = (Graphics2D)a;
                    //gg.setBackground(Color.white);
                    update(gg);
                }else
                {
                    SimpleDraw1.arr[index].setBackground(SimpleDraw1.color);
                    Graphics a = getGraphics();
                    Graphics2D gg = (Graphics2D)a;
                    //gg.setBackground(Color.white);
                    update(gg);
                }
                index++;
            }
        }
    }

    public int[][] generateLifeArray () // live cells are 1, dead are 0 
    {
        int[][] ans = new int[SimpleDraw1.ROWS][SimpleDraw1.COLS];
        int index = 0;
        for (int i = 0; i<SimpleDraw1.ROWS; i++)
        {
            for (int j = 0; j<SimpleDraw1.COLS; j++)
            {
                if (SimpleDraw1.arr[index].getBackground() == Color.white)
                {
                    ans[i][j] = 0;
                }else
                {
                    ans[i][j] = 1;
                }
                index++;
            }
        }
        return ans;
    }

}
