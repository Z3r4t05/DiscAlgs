/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Algs;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author ADMIN
 */
public class FCFS extends Algs {

    public FCFS() {
    }

    public FCFS(int seekRate, int totalCylinders, int headPos) {
        super(seekRate, totalCylinders, headPos);
    }

    /**
     * Run FCFS algorithm
     */
    @Override
    public void Run() {
        if (super.getRequests().isEmpty()) {
            System.err.println("Empty requests!! Please input requests sequence");
            return;
        }
        int head = Inputter.util.getintMinMax("Enter current position of the head: ", super.getMinCyl(), super.getMaxCyl());
        this.setHeadPos(head);
        int seekCount = 0;
        int distance, curTrack;
        char c = Inputter.util.getChar("Do you want to see the calculation ? (y/n): ", "[YyNn]");
        if (c == 'Y' || c == 'y') {
            for (int i = 0; i < super.getRequests().size(); i++) {
                System.out.print("(");
                curTrack = super.getRequests().get(i);
                if (curTrack > head) {
                    System.out.print(curTrack + " - " + head);
                } else {
                    System.out.print(head + " - " + curTrack);
                }
                distance = Math.abs(curTrack - head); //calculate distance
                seekCount += distance; //increase total count
                head = curTrack; //newPos  
                System.out.print(")");
                if (i < super.getRequests().size() - 1) {
                    System.out.print(" + ");
                }
                if ((i != 0) && (((i + 1) % 6) == 0)) {
                    System.out.println("");
                }
            }
            System.out.println(" = " + seekCount);
        } else {
            for (int i = 0; i < super.getRequests().size() - 1; i++) {
                curTrack = super.getRequests().get(i);
                distance = Math.abs(curTrack - head); //calculate distance
                seekCount += distance; //increase total count
                head = curTrack; //newPos     
            }
        }
        System.out.println("Total head movement = " + Inputter.util.thousandSeparator(seekCount));
        System.out.println("Total seek time = " + seekCount + "*" + super.getSeekRate() + " = " + Inputter.util.thousandSeparator(seekCount * super.getSeekRate()) + "ms");
        c = Inputter.util.getChar("Do you want save the results ? (y/n): ", "[YyNn]");
        if (c == 'Y' || c == 'y') {
            writeResults("src\\FCFS.txt", seekCount, this.getHeadPos());
        } else {
            System.out.println("Finished");
        }
        //To change body of generated methods, choose Tools | Templates.

    }

    @Override
    public boolean writeResults(String filename, int seekCount, int head) {
        try {
            try (FileWriter fw = new FileWriter(filename); PrintWriter pw = new PrintWriter(fw)) {
                Iterator it = this.getRequests().iterator();
                pw.println("FCFS ALGORITHM");
                pw.println("Requests sequence: ");
                while(it.hasNext()) {
                    pw.print(it.next() + " ");
                }
                pw.println("\nHead position: " + head);
                pw.println("\nTotal head movement = " + Inputter.util.thousandSeparator(seekCount));
                pw.println("Total seek time = " + seekCount + "*" + super.getSeekRate() + " = " + Inputter.util.thousandSeparator(seekCount * super.getSeekRate()) + "ms");
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return false;
        }
        System.out.println("Save successfully");
        return true;
    }
    
    public static void main(String[] args) {
        Algs n = new FCFS(2, 200, 50);
        String filename = "src\\requests.txt";
        n.loadRequests(filename);
        n.Run();
    }

    @Override
    public boolean writeResults(String filename, int seekCount, int head, ArrayList<Integer> seek) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
