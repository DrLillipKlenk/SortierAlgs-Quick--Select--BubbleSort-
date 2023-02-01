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
        mainFrame mf = new mainFrame();

        mf.create();

        Swap swap = new Swap();
        Random rand = new Random();
        int size = 400;

        int [] randArr = IntStream.iterate(1, n -> n + 1).limit(size).toArray();
        for(int i=0;i<=size*4;i++){
            randArr = swap.swap(randArr,rand.nextInt(size),rand.nextInt(size));
        }

        BubbleSort bs = new BubbleSort();

        bs.sort(Arrays.copyOfRange(randArr,0,randArr.length));

        QuickSort qs = new QuickSort();

        qs.create();
        qs.sort(Arrays.copyOfRange(randArr,0,randArr.length),0,randArr.length-1);

    }
}

class mainFrame{
    JFrame jf = new JFrame();
    JPanel jp = new JPanel();
    JButton b1 = new JButton();
    JButton b2 = new JButton();

    public void create(){
        jf.setSize(816,488);
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jf.setTitle("SortierAlgs");
        jf.setVisible(true);

        b1.setText("Quicksort");
        b1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });
        b1.setBounds(150,144,200,200);

        b2.setText("Bubblesort");
        b2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){

            }
        });
        b2.setBounds(466,144,200,200);

        jp.setSize(816,488);
        jp.setLayout(null);
        jp.add(b1);
        jp.add(b2);

        jf.add(jp);
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
            Thread.sleep(1);
        }
        in=swap.swap(in,pivotIndex,end);

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

    JFrame jf = new JFrame();
    Canvas c;

    public void create(String name){
        jf.setSize(816,488);
        jf.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        jf.setTitle(name);
        c = new Canvas(450,new int [] {});
        c.setVisible(true);
        c.setLayout(null);
        jf.add(c);
        jf.setVisible(true);
    }

    public void draw(int [] randArr){
        c = new Canvas(450,randArr);
        c.setVisible(true);
        c.setLayout(null);
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
