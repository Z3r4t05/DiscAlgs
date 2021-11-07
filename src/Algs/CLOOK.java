/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Algs;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.Iterator;
import java.util.Vector;

/**
 *
 * @author ADMIN
 */
public class CLOOK extends Algs {

    @Override
    public void Run() {
        if (super.getRequests().isEmpty()) {
            System.err.println("Empty requests!! Please input requests sequence");
            return;
        }
        int head = Inputter.util.getintMinMax("Enter current position of the head (" + this.getMinCyl() + "-" + this.getMaxCyl() + "): ", super.getMinCyl(), super.getMaxCyl());
        this.setSeekRate(Inputter.util.getintGreater("Enter seek rate: ", 0));
        char direction = Inputter.util.getChar("Enter direction, left or right? (l/r): ", "[RrLl]");
        this.setHeadPos(head);
        int seekCount = 0;
        int distance, curTrack;
        char dir1 = direction;
        Vector<Integer> left = new Vector<>(),
                right = new Vector<>(),
                seek = new Vector<>();
        for (Integer a : this.getRequests()) {
            if (a < head) {
                left.add(a);
            } else if (a > head) {
                right.add(a);
            }
        }
        Collections.sort(left);
        Collections.sort(right);
        if (Character.toLowerCase(direction) == 'r') {
            for (int i = 0; i < right.size(); i++) {
                curTrack = right.get(i);
                seek.add(curTrack);
                distance = Math.abs(curTrack - head);
                seekCount += distance;
                head = curTrack;
            }
            seekCount += Math.abs(head - left.get(0));
            head = left.get(0);
            for (int i = 0; i < left.size(); i++) {
                curTrack = left.get(i);
                seek.add(curTrack);
                distance = Math.abs(curTrack - head);
                seekCount += distance;
                head = curTrack;
            }
        } else if (Character.toLowerCase(direction) == 'l') {
            Collections.reverse(left);
            Collections.reverse(right);
            for (int i = 0; i < left.size(); i++) {
                curTrack = left.get(i);
                seek.add(curTrack);
                distance = Math.abs(curTrack - head);
                seekCount += distance;
                head = curTrack;
            }
            seekCount += Math.abs(head - right.get(0));
            head = right.get(0);
            for (int i = 0; i < right.size(); i++) {
                curTrack = right.get(i);
                seek.add(curTrack);
                distance = Math.abs(curTrack - head);
                seekCount += distance;
                head = curTrack;
            }
        }
        char choice = Inputter.util.getChar("Do you want to see the calculation ? (y/n): ", "[YyNn]");
        if (choice == 'Y' || choice == 'y') {
            int a = this.getHeadPos();
            int b;
            for (int i = 0; i < seek.size(); i++) {
                b = seek.get(i);
                if (a > b) {
                    System.out.print("(" + a + " - " + b + ")");
                } else {
                    System.out.print("(" + b + " - " + a + ")");
                }
                a = b;
                if (i < super.getRequests().size() - 1) {
                    System.out.print(" + ");
                }
                if ((i != 0) && (((i + 1) % 6) == 0)) {
                    System.out.println("");
                }
            }
            System.out.println(" = " + seekCount);
        }
        System.out.println("Total head movement = " + Inputter.util.thousandSeparator(seekCount));
        System.out.println("Total seek time = " + seekCount + "*" + super.getSeekRate() + " = " + Inputter.util.thousandSeparator(seekCount * super.getSeekRate()) + "ms");
        System.out.println("Seek sequence: ");
        System.out.println(seek);
        char c = Inputter.util.getChar("Do you want save the result ? (y/n): ", "[YyNn]");
        if (c == 'Y' || c == 'y') {
            writeResults("src\\CLOOK.txt", seekCount, this.getHeadPos(), seek, this.getSeekRate(), dir1);
        } else {
            System.out.println("Finished");
        }
    }

    public static void main(String[] args) {
        Algs n = new CLOOK();
        n.inputRequests();
        n.Run();
    }
    
    public boolean writeResults(String filename, int seekCount, int head, Vector<Integer> seek, int seekRate, char direction) {
        try {
            try (FileWriter fw = new FileWriter(filename); PrintWriter pw = new PrintWriter(fw)) {
                Iterator it = this.getRequests().iterator();
                pw.println("C-LOOK(Circular LOOK) ALGORITHM");
                pw.println("Requests sequence: ");
                while (it.hasNext()) {
                    pw.print(it.next() + " ");
                }
                pw.println("\nHead position: " + head);
                pw.println("Seek rate: " + seekRate);
                if (Character.toLowerCase(direction) == 'l') {
                    pw.println("Direction: Left (Moving from right to left)");
                } else if (Character.toLowerCase(direction) == 'r') {
                    pw.println("Direction: Right(Moving from left to right)");
                }
                pw.println("\nTotal head movement = " + Inputter.util.thousandSeparator(seekCount));
                pw.println("Total seek time = " + seekCount + "*" + super.getSeekRate() + " = " + Inputter.util.thousandSeparator(seekCount * super.getSeekRate()) + "ms");
                pw.println("Seek sequence: \n" + seek);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return false;
        }
        System.out.println("Save successfully");
        return true;
    }
}
