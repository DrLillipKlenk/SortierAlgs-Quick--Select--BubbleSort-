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
        BogoSort boso = new BogoSort();
        int size = 400;

        int [] randArr = IntStream.iterate(1, n -> n + 1).limit(size).toArray();
        for(int i=0;i<=size;i++){
            randArr = swap.swap(randArr,rand.nextInt(size),rand.nextInt(size));
        }

        //boso.sort(Arrays.copyOfRange(randArr,0,9));

        qs.create();
        qs.sort(Arrays.copyOfRange(randArr,0,randArr.length),0,randArr.length-1);

        ss.sort(Arrays.copyOfRange(randArr,0,randArr.length));

        bs.sort(Arrays.copyOfRange(randArr,0,randArr.length));
    }
}

class GnomeSort{

    public GUI gui = new GUI();
    Insert insert= new Insert();

    public void sort(int [] in) throws InterruptedException {

        for(int i=0;i<in.length;i++){
            int [] sortedArr =Arrays.copyOfRange(in,0,i);
            insert.insert(in,findIndex(sortedArr,i),i);

            gui.draw(in);
            Thread.sleep(1);
        }
    }

    public int findIndex(int [] in,int toFind){
        int out=0;

        if(in.length==1){
            if(toFind<in[0]){
                out=1;
            }else {
                out = 0;
            }
        }else{
            for(int i=1;i<in.length-1;i++){

            }
        }

        return out;
    }
}

class BogoSort{

    public GUI gui = new GUI();
    public Swap swap = new Swap();
    public Random rnd = new Random();

    public void sort (int [] in) throws InterruptedException {
        gui.create("Bogo-Sort");
        while(!sorted(in)) {
            for (int i = 0; i < in.length - 1; i++) {
                in = swap.swap(in, rnd.nextInt(in.length), rnd.nextInt(in.length));
            }
            gui.draw(in);
        }
    }

    public Boolean sorted(int [] in){
        for(int i=0;i<in.length-1;i++){
            if(!(in[i+1]>in[i])){
                return false;
            }
        }
        return true;
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

class SelectionSort{

    public GUI gui = new GUI();
    public Swap swap = new Swap();

    public void sort(int [] in) throws InterruptedException {
        gui.create("Selection-Sort(Max-Sort)");
        for(int i=0;i<in.length;i++){
            int maxIndex = 0;
            for(int j=0;j<in.length-i;j++){
                //vergleiche Objekt in Array mit aktuell größtem gespeichertem Objekt -> wenn größer setze maxIndex auf aktuellen Index
                if(in[j]>in[maxIndex]){
                    maxIndex=j;
                }
                gui.draw(in);
                Thread.sleep(1);
            }
            //tausche Größtes Objekt mit dem Letzen nicht sortierten Objekt
            in = swap.swap(in,in.length-1-i,maxIndex);
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

class Insert{
    public int [] insert(int [] in,int index1,int idex2){

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
            g2d.fillRect(i*(8/3),height-(rectHeight[i]),8/3,rectHeight[i]);
        }
    }
}
