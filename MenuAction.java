import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.io.*;
import java.util.*;

public class MenuAction implements ActionListener
{
    public File file = null;
    Scanner scan = null;
    public void actionPerformed(ActionEvent e) //throws FileNotFoundException
    {
        if (e.getActionCommand().equals("OPEN"))
        {
            //System.out.println("OPEN"); // dEB
            JFileChooser a = new JFileChooser();
            a.showOpenDialog(new JFrame());
            file = a.getSelectedFile();
            //System.out.println("File  " + file); //DEB
            //SimpleDraw1.openFile(file); //OPEN FILE SOMEHOW
            try
            {
                readFile();
                openFile();
            }catch(FileNotFoundException qwert)
            {
                System.out.println("File wasn't a ppm file!");
                System.exit(0);
            }
        }else if (e.getActionCommand().equals("SAVE"))
        {
            //System.out.println("SAVE"); // DEB
            JFileChooser a = new JFileChooser();
            a.showSaveDialog(new JFrame());
            file = a.getSelectedFile(); 
            //System.out.println(f); //DEB
            //SAVE FILE SOMEHOW
            FileWriter writer;
            try
            {
                writer = new FileWriter(file);
            }catch (IOException ever)
            {
                writer = null;
                System.out.println("UNABLE TO WRITE");
            }
            try
            {
                writer.write("P3\n" + SimpleDraw1.ROWS + " " + SimpleDraw1.COLS + "\n" + 255 + "\n");
                for (int i = 0; i<(SimpleDraw1.ROWS*SimpleDraw1.COLS); i++)
                {
                    Color c = SimpleDraw1.arr[i].getBackground();
                    writer.write("   " + c.getRed() + " " + c.getGreen() + " " + c.getBlue() + "   ");
                }
                writer.flush();
                writer.close();
            } catch(IOException eeeee)
            {
                System.out.println("problem");
            }
        }else if (e.getActionCommand().equals("CLEAR"))
        {
            //System.out.println("CLEAR"); // DEB
            BoardPanel temp = new BoardPanel();
            temp.actionPerformed(e);
        }
    }
    public Color [] openColors = null;
    @SuppressWarnings("unchecked")
    public void readFile() throws FileNotFoundException// modifies openColors and sets Panel colors
    {

        try
        {
            //System.out.println(file); // DEB
            scan = new Scanner(file);
        }catch (FileNotFoundException e)
        {
            //System.out.println(file); // DEB
            System.out.println("File not found!");
            System.exit(0);
        }
        if (!(scan.next().equals("P3")))
        {
            throw new FileNotFoundException();
        }

        
        int rows = Integer.parseInt(scan.next());
        int cols = Integer.parseInt(scan.next());
        int depth = Integer.parseInt(scan.next());
        openColors = new Color[rows*cols];
        SimpleDraw1.ROWS = rows;
        SimpleDraw1.COLS = cols;
        //System.out.println("r " + rows+ "  c " + cols); //DEB
        SimpleDraw1.arr = new BoardPanel[rows*cols];
        SimpleDraw1.draw();
        for (int r = 0; r<(rows*cols); r++)
        {
            String[] temp = new String[3];
            
            temp[0] = scan.next();
            temp[1] = scan.next();
            temp[2] = scan.next();
            
            openColors[r] = new Color(Integer.parseInt(temp[0]),
                Integer.parseInt(temp[1]),Integer.parseInt(temp[2]));
        }
    }


    public void openFile()
    {
        for (int i = 0; i<openColors.length; i++)
        {     
            SimpleDraw1.arr[i].setBackground(openColors[i]);
            Graphics a = SimpleDraw1.arr[i].getGraphics();
            Graphics2D gg = (Graphics2D)a;
            gg.setBackground(openColors[i]);
            SimpleDraw1.arr[i].update(gg);
        }
    }
}

