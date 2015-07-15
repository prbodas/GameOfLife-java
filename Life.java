import java.util.Scanner;
import java.util.Arrays;
import java.io.*;
/**
 * 
 * 
 * @author Prachi Bodas Period III
 */
public class Life
{
    public int[][] current;
    int[][] copy;
    public static int generation = 0;
    String fileName;
    public Life(String f)
    {
        fileName = f;
    }

    public void readFile()
    {
        File bluebell = new File (fileName);
        Scanner in;
        try
        {
            in = new Scanner(bluebell);
        }catch (FileNotFoundException e)
        {
            in = null;
        }
        current = new int[in.nextInt()][in.nextInt()];
        copy = new int[current.length][current[0].length];
        while(in.hasNext())
        {
            current[in.nextInt()][in.nextInt()] = 1;
        }
    }

    public void printCurrent()
    {
        String a = (current.length-1) + "";
        int aa = a.length()+4;
        //System.out.printf("%" + aa + "s", "");
        for (int i = 1; i<= current[0].length; i++)
        {
            //System.out.print(getLastDigit(i-1));
        }
        //System.out.println();
        for (int i = 1; i<=current.length; i++) //must subtract one
        {
            //System.out.printf("%" + a.length() + "d", i-1);
            //System.out.print("\t");
            for (int j = 0; j<current[0].length; j++)
            {
                printCell(i-1,j);
            }
            //System.out.println();
        }
        //System.out.println();
        //System.out.println("Number in Row 9 ---> " + rowCount(9));
        //System.out.println();
        //System.out.println("Number in Column 9 ---> " + colCount(9));
        //System.out.println("Number of living cells total ---> " + totalCount());
    }

    public void printCell(int row, int column)
    {
        if (current[row][column] == 1)
        {
            //System.out.print("*");
        }else{
            //System.out.print(" ");
        }
    }

    public String getLastDigit (int i)
    {
        String s = i+"";
        return s.charAt(s.length()-1)+"";
    }

    public int totalCount()
    {
        int n = 0;
        for(int i = 0; i<current.length; i++)
        {
            n+=rowCount(i);
        }
        return n;
    }

    public int colCount(int col)
    {
        if (!inBounds(0,col)){return -1;}
        int n = 0;
        for (int i = 0; i<current.length; i++)
        {
            if(current[i][col] == 1)
            {
                n++;
            }
        }
        return n;
    }

    public int rowCount(int row)
    {
        if (!inBounds(row,0)){return -1;}
        int n = 0;
        for (int i = 0; i<current[row].length; i++)
        {
            if(current[row][i] == 1)
            {
                n++;
            }
        }
        return n;
    }

    public void nextGeneration()
    {
        currentIntoCopy();
        clearCurrent();
        for (int i = 0; i<current.length; i++) // y coord row
        {
            for (int j = 0; j<current[0].length; j++) // x coord column
            {
                evaluate(i,j);
            }
        }
        generation++;
    }
    
    public void currentIntoCopy ()
    {
        copy = new int[current.length][current[0].length];
        for (int i = 0; i<current.length; i++)
        {
            for (int j = 0; j<current[0].length; j++)
            {
                copy[i][j] = current[i][j];
            }
        }
    }

    public void clearCurrent()
    {
        for (int i = 0; i<current.length; i++)
        {
            for (int j = 0; j<current[0].length; j++)
            {
                current[i][j] = 0;
            }
        }
    }

    public void evaluate (int row, int column)
    {
        int n = countLiveNeighbors(row, column);
        if (!isAlive(row, column) && n==3)
        {
            current[row][column] = 1; // 1 denotes live
        }else if (isAlive(row, column) && (n==2 || n==3))
        {
            current[row][column] = 1;
        }else if (isAlive(row, column) && (n>=4||n==1||n==0))
        {
            current[row][column] = 0;
        }else if (!isAlive(row,column) && n!=3)
        {
            current[row][column] = 0;
        }
    }

    public int countLiveNeighbors (int row, int column)
    {
        int n = 0;
        if (inBounds(row+1,column))
        {
            if (isAlive(row+1, column)){
                n+=1;
            }
        }
        if (inBounds(row-1,column) )
        {
            if (isAlive(row-1, column)){
                n+=1;
            }
        }
        if (inBounds(row,column-1) && isAlive(row, column-1))
        {
            n+=1;
        }
        if (inBounds(row,column+1) && isAlive(row, column+1))
        {
            n+=1;
        }
        if (inBounds(row-1,column-1) && isAlive(row-1, column-1))
        {
            n+=1;
        }
        if (inBounds(row-1,column+1) && isAlive(row-1, column+1))
        {
            n+=1;
        }
        if (inBounds(row+1,column-1) && isAlive(row+1, column-1))
        {
            n+=1;
        }
        if (inBounds(row+1,column+1) && isAlive(row+1, column+1))
        {
            n+=1;
        }
        return n;
    }

    public boolean inBounds (int row, int column)
    {
        return (row < current.length) && (column<current[0].length) && row>-1 && column>-1;
    }

    public boolean isAlive (int row, int column)
    {
        return (copy[row][column] == 1);
    }

    public void runLife (int numGenerations)
    {
        //readFile();
        for (int i = 0; i<numGenerations; i++)
        {
            nextGeneration();
        }
        //printCurrent(); 
    }

    public static void main (String[] args)
    {
        Life l = new Life("life100.txt");
        l.runLife(5);
    }
}
