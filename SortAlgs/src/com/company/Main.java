package com.company;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        Random rand = new Random();
        BubbleSort bs = new BubbleSort();
        SelectionSort ss = new SelectionSort();
        Swap swap = new Swap();
        QuickSort qs = new QuickSort();

        int [] randArr = IntStream.iterate(1, n -> n + 1).limit(100).toArray();
        for(int i=0;i<=100;i++){
            randArr = swap.swap(randArr,rand.nextInt(100),rand.nextInt(100));
        }

        qs.create();
        qs.sort(Arrays.copyOfRange(randArr,0,randArr.length),0,randArr.length-1);

        ss.sort(Arrays.copyOfRange(randArr,0,randArr.length));

        bs.sort(Arrays.copyOfRange(randArr,0,randArr.length));
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
                Thread.sleep(10);
            }
        }
    }
}

class SelectionSort{

    public GUI gui = new GUI();
    public Swap swap = new Swap();

    public void sort(int [] in) throws InterruptedException {
        gui.create("Selection-Sort");
        for(int i=0;i<in.length;i++){
            int maxIndex = 0;
            for(int j=0;j<in.length-i;j++){
                //vergleiche Objekt in Array mit aktuell größtem gespeichertem Objekt -> wenn größer setze maxIndex auf aktuellen Index
                if(in[j]>in[maxIndex]){
                    maxIndex=j;
                }
            }
            //tausche Größtes Objekt mit dem Letzen nicht sortierten Objekt
            in = swap.swap(in,in.length-1-i,maxIndex);

            gui.draw(in);
            Thread.sleep(10);
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
        }
        in=swap.swap(in,pivotIndex,end);

        gui.draw(in);
        Thread.sleep(10);

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
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jf.setTitle(name);
        jf.setVisible(true);
        c = new Canvas(450,new int [] {});
        jf.add(c);
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
            g2d.fillRect(i*8,height-(rectHeight[i]*3),8,rectHeight[i]*3);
        }
    }
}