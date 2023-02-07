package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        Random rand = new Random();
        Swap swap = new Swap();
        BubbleSort bs = new BubbleSort();
        QuickSort qs = new QuickSort();
        int size = 400;


        int [] randArr = IntStream.iterate(1, n -> n + 1).limit(size).toArray();
        for(int i=0;i<=size;i++){
            randArr = swap.swap(randArr,rand.nextInt(size),rand.nextInt(size));
        }
        qs.create();
        qs.sort(Arrays.copyOfRange(randArr,0,randArr.length),0, randArr.length-1);

        JFrame mainFrame = new JFrame();
        mainFrame.setSize(816,488);
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        JPanel jp = new JPanel();
        jp.setSize(816,488);
        jp.setLayout(null);
        JButton b1 = new JButton("Bubblesort");
        b1.setBounds(0,0,408,488);
        JButton b2 = new JButton("Quicksort");
        b2.setBounds(408,0,408,488);

        b1.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                try {
                    int [] randArr = IntStream.iterate(1, n -> n + 1).limit(size).toArray();
                    for(int i=0;i<=size;i++){
                        randArr = swap.swap(randArr,rand.nextInt(size),rand.nextInt(size));
                    }

                    bs.sort(Arrays.copyOfRange(randArr,0,randArr.length));
                } catch (InterruptedException interruptedException) {
                    interruptedException.printStackTrace();
                }
            }
        });

        b2.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                try {
                    int [] randArr = IntStream.iterate(1, n -> n + 1).limit(size).toArray();
                    for(int i=0;i<=size;i++){
                        randArr = swap.swap(randArr,rand.nextInt(size),rand.nextInt(size));
                    }

                    qs.create();
                    qs.sort(Arrays.copyOfRange(randArr,0,randArr.length),0, randArr.length-1);
                } catch (InterruptedException interruptedException) {
                    interruptedException.printStackTrace();
                }
            }
        });

        jp.add(b1);
        jp.add(b2);
        mainFrame.add(jp);
        mainFrame.setVisible(true);
    }
}

class BubbleSort{

    public GUI gui = new GUI();
    public Swap swap = new Swap();

    public void sort (int [] in) throws InterruptedException {
        gui.create("Bubble-Sort");
        for(int i=0;i<in.length-1; i++){
            for(int j=0;j< in.length-1-i;j++) {
                //vergleiche Objekt in Array mit nächstem Objekt -> wenn größer tausche
                if (in[j] > in[j + 1]) {
                    in = swap.swap(in,j,j+1);
                }
                gui.draw(in);
                Thread.sleep(1);
            }
        }
    }
}

class QuickSort{

    public GUI gui = new GUI();
    public Swap swap = new Swap();

    public void create(){
        gui.create("Quick-Sort");
    }

    public int [] sort(int [] in,int start,int end) throws InterruptedException {
        if(start>=end){
            return in;
        }
        int pivotIndex = start;
        int pivotVal = in[end];
        for(int i=start;i<end;i++){
            if(in[i]<pivotVal){
                in=swap.swap(in,i,pivotIndex);
                pivotIndex++;
            }
            gui.draw(in);
            Thread.sleep(0,5);
        }
        in=swap.swap(in,pivotIndex,end);
        gui.draw(in);
        Thread.sleep(0,5);

        sort(in,start,pivotIndex-1);
        sort(in,pivotIndex+1,end);
        return in;
    }
}

class Swap{
    public int [] swap(int [] in,int index1,int index2){
        int help = in[index1];
        in[index1] = in[index2];
        in[index2] = help;

        return in;
    }
}

class GUI{

    public JFrame jf = new JFrame();
    public Canvas c;

    public void create(String name){
        jf.setSize(816,488);
        jf.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        jf.setTitle(name);
        c = new Canvas(450,new int [] {});
        jf.add(c);
        jf.setVisible(true);
    }

    public void draw(int [] randArr){
        c = new Canvas(450,randArr);
        jf.add(c);
        jf.repaint();
    }
}

class Canvas extends JComponent{

    public int height;
    public int [] rectHeight;

    public Canvas(int height,int [] rectHeight){
        this.height=height;
        this.rectHeight=rectHeight;
    }

    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        for(int i=0;i<rectHeight.length;i++){
            g2d.fillRect(i*(8/3),height-(rectHeight[i]),8/3,rectHeight[i]);
        }
    }
}
