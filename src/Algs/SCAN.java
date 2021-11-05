/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Algs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Vector;

/**
 *
 * @author ADMIN
 */
public class SCAN extends Algs {

    public boolean writeResults(String filename, int seekCount, int head, ArrayList<Integer> seek) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

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
        Vector<Integer> left = new Vector<>(),
                right = new Vector<>(),
                seek = new Vector<Integer>();
        char direction = Inputter.util.getChar("Enter direction, left or right? (l/r)", "[RrLl]");
        if (direction == 'l' || direction == 'L') {
            left.add(0);
        } else {
            right.add(this.getMaxCyl());
        }
        for (Integer request : this.getRequests()) {
            if (request < head) {
                left.add(request);
            }
            if (request > head) {
                right.add(request);
            }
        }
        Collections.sort(left);
        Collections.sort(right);
        int run = 2;
        while (run-- > 0) {
            if (direction == 'l' || direction == 'L') {
                for (int i = left.size() - 1; i >= 0; i--) {
                    curTrack = left.get(i);
                    seek.add(curTrack);
                    distance = Math.abs(curTrack - head);
                    seekCount += distance;
                    head = curTrack;
                }
                direction = 'r';
            } else if (direction == 'r' || direction == 'R') {
                for (int i = 0; i < right.size(); i++) {
                    curTrack = right.get(i);
                    seek.add(curTrack);
                    distance = Math.abs(curTrack - head);
                    seekCount += distance;
                    head = curTrack;
                }
                direction = 'l';
            }
        }
        System.out.println("Total head movement = " + Inputter.util.thousandSeparator(seekCount));
        System.out.println("Total seek time = " + seekCount + "*" + super.getSeekRate() + " = " + Inputter.util.thousandSeparator(seekCount * super.getSeekRate()) + "ms");
        System.out.println("Seek sequence: ");
        System.out.println(seek);
    }

    public static void main(String[] args) {
        Algs n = new SCAN();
        n.randomRequests();
        n.Run();
    }
}
