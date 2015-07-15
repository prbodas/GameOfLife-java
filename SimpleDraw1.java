import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
/**
 * It took me about 2.5 hours.
 * This lab was not too difficult,
 * as I've grown used to GUI etc.
 * The greatest difficulty I had,
 * really, was learning how to use the
 * swing timer. And I understand that my 
 * program is fairly disorganized, but it 
 * works, so I hope it's alright for now
 * but I will try to keep my programs more
 * organized in the future. However, I do 
 * believe I left the class files judiciously 
 * commented, so I suppose that would help
 * with a sort of pseudo-organization.
 * From the extra features I added just one:
 * that my program can open a pre-existing 
 * ppm file of any size. 
 * 
 * Prachi Bodas Period 3
 */
public class SimpleDraw1
{
    public static boolean pressed = false;
    public static Color color = Color.white;
    public static BoardPanel[] arr = new BoardPanel[20*20];
    public static int ROWS = 20;
    public static int COLS = 20;
    public static JSlider slider;
    
    public static boolean continuous = false;
    
    public static Timer timer = new javax.swing.Timer(500, new TimerListener());
    
    public static JFrame frame;
    public static void draw()
    {
        // make new JFrame window
        frame = new JFrame("SimpleDraw1");
        frame.setLayout(new BorderLayout());
        
        JPanel main = new JPanel();
        main.setBackground(Color.gray);
        main.setPreferredSize(new Dimension(400,400));

        frame.setResizable(true);
        main.setLayout(new GridLayout(ROWS,COLS));
        main.setBorder(BorderFactory.createLineBorder(Color.gray, 20, false));

        for (int i = 0; i<(ROWS*COLS); i++)
        {
            BoardPanel panel = new BoardPanel();
            panel.setBackground(Color.white);
            panel.setBorder(BorderFactory.createLineBorder(Color.black));
            panel.addMouseListener(panel);
            panel.addMouseMotionListener(panel);
            arr[i] = panel;
            //panel.addMouseMotionListener(new MyListener());
            //addMouseMotionListener(panel);
            main.add(panel);
        }

        JPanel buttons = new JPanel();
        //buttons.setPreferredSize(new Dimension(frame.getWidth(), frame.getHeight()/2));
        buttons.setPreferredSize(new Dimension(100, 100));

        buttons.setLayout(new GridLayout(2,1));
        JPanel top = new JPanel();
        JPanel bottom = new JPanel();

        JRadioButton red = new JRadioButton("Red");
        red.setActionCommand("RED");
        red.addActionListener(new BoardPanel());
        top.add(red);

        JRadioButton blue = new JRadioButton("Blue");
        blue.setActionCommand("BLUE");
        blue.addActionListener(new BoardPanel());
        top.add(blue);

        JRadioButton green = new JRadioButton("Green");
        green.setActionCommand("GREEN");
        green.addActionListener(new BoardPanel());
        top.add(green);
        
        JRadioButton custom = new JRadioButton("Custom");
        custom.setActionCommand("CUSTOM");
        custom.addActionListener(new BoardPanel());
        top.add(custom);

        JButton clear = new JButton ("Clear");
        clear.setActionCommand("CLEAR");
        clear.addActionListener(new BoardPanel());
        bottom.add(clear);
        
        JButton run = new JButton("Run One Life Generation");
        run.setActionCommand("RUN");
        run.addActionListener(new BoardPanel());
        bottom.add(run); //runs life
        
        JToggleButton runCont = new JToggleButton("Run Life Continuously");
        runCont.setActionCommand("RUNCONT");
        runCont.addActionListener(new BoardPanel());
        bottom.add(runCont); //runs life
        
        slider = new JSlider(0,1000,500);
        //slider.setActionCommand("SLIDER");
        slider.addChangeListener(new SliderListener());
        slider.setMajorTickSpacing(200);
        slider.setMinorTickSpacing(100);
        slider.setPaintTicks(true);
        bottom.add(slider);

        //add to buttons
        buttons.add(top);
        buttons.add(bottom);
        
        // SimpleDraw2
        JMenuBar menuBar = new JMenuBar();
        // file menu
        JMenu fileMenu = new JMenu("File");
        JMenuItem open = new JMenuItem("Open");
        open.setActionCommand("OPEN");
        open.addActionListener(new MenuAction());
        JMenuItem save = new JMenuItem("Save");
        save.setActionCommand("SAVE");
        save.addActionListener(new MenuAction());
        // edit menu
        JMenu editMenu = new JMenu("Edit");
        JMenuItem clearer = new JMenuItem("Clear");
        clearer.setActionCommand("CLEAR");
        clearer.addActionListener(new MenuAction());
        // add bar
        fileMenu.add(open);
        fileMenu.add(save);
        editMenu.add(clearer);
        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        frame.setJMenuBar(menuBar);

        //after initial settings
        buttons.setVisible(true);
        frame.add(main, BorderLayout.PAGE_START);
        frame.add(buttons, BorderLayout.PAGE_END);
        frame.pack();
        frame.setVisible(true);

    }
    

    public static void main (String[] args)
    {
        //SimpleDraw1 a = new SimpleDraw1();
        SimpleDraw1.draw();
    }
}

