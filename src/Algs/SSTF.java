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
class node {

    int distance = 0;
    boolean accessed = false;

    public node() {
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public boolean isAccessed() {
        return accessed;
    }

    public void setAccessed(boolean accessed) {
        this.accessed = accessed;
    }

}

public class SSTF extends Algs {

    public SSTF() {
    }

    public SSTF(int seekRate, int totalCylinders, int headPos) {
        super(seekRate, totalCylinders, headPos);
    }

    /**
     *
     * @param requests
     * @param head
     * @param diff
     */
    public void calculateDiff(ArrayList<Integer> requests, int head, ArrayList<node> diff) {
        for (int i = 0; i < diff.size(); i++) {
            diff.get(i).setDistance(Math.abs(requests.get(i) - head));
        }
    }

    public int findMin(ArrayList<node> diff) {
        int index = -1;
        int minimum = Integer.MAX_VALUE;
        Iterator<node> it = diff.iterator();
        while (it.hasNext()) {
            node curNode = it.next();
            if (!curNode.isAccessed() && minimum > curNode.distance) {
                minimum = curNode.getDistance();
                index = diff.indexOf(curNode);
            }
        }
        return index;
    }

    public boolean writeResults(String filename, int seekCount, int head, ArrayList<Integer> seek) {
        try {
            try (FileWriter fw = new FileWriter(filename); PrintWriter pw = new PrintWriter(fw)) {
                Iterator it = this.getRequests().iterator();
                pw.println("SSTF ALGORITHM");
                pw.println("Requests sequence: ");
                while (it.hasNext()) {
                    pw.print(it.next() + " ");
                }
                pw.println("\nHead position: " + head);
                pw.println("\nTotal head movement = " + Inputter.util.thousandSeparator(seekCount));
                pw.println("Total seek time = " + seekCount + "*" + super.getSeekRate() + " = " + Inputter.util.thousandSeparator(seekCount * super.getSeekRate()) + "ms");
                pw.println("Seek sequence: ");
                pw.println(seek);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return false;
        }
        System.out.println("Save successfully");
        return true;
    }

    @Override
    public void Run() {

        if (super.getRequests().isEmpty()) {
            System.err.println("Empty requests!! Please input requests sequence");
            return;
        }

        int head = Inputter.util.getintMinMax("Enter current position of the head: ", super.getMinCyl(), super.getMaxCyl());
        this.setHeadPos(head);
        int seekCount = 0; //count total head mvment
        ArrayList<node> diff = new ArrayList<>(); //intialize diff arraylist
        Iterator<Integer> it = this.getRequests().iterator();
        while (it.hasNext()) {
            Integer requestNext = it.next();
            diff.add(new node());
        }
        ArrayList<Integer> seek = new ArrayList<>(); //stores sequence in which disk access is done
        it = this.getRequests().iterator();
        while (it.hasNext()) {
            int cur = it.next();
            seek.add(head);
            calculateDiff(this.getRequests(), head, diff);
            int index = findMin(diff);
            diff.get(index).setAccessed(true);

            seekCount += diff.get(index).getDistance();
            head = this.getRequests().get(index);
        }
        seek.add(head);
        char c = Inputter.util.getChar("Do you want to see the calculation ? (y/n): ", "[YyNn]");
        if (c == 'Y' || c == 'y') {
            for (int i = 0; i < seek.size() - 1; i++) {

                if (seek.get(i) > seek.get(i + 1)) {
                    System.out.print("(" + seek.get(i) + " - " + seek.get(i + 1));
                } else {
                    System.out.print("(" + seek.get(i + 1) + " - " + seek.get(i));
                }
                if (i < seek.size() - 2) {
                    System.out.print(") + ");
                } else {
                    System.out.println(") = " + seekCount);
                }
                if ((i != 0) && (((i + 1) % 6) == 0)) {
                    System.out.println("");
                }
            }
        }
        System.out.println("Total head movement = " + Inputter.util.thousandSeparator(seekCount));
        System.out.println("Total seek time = " + seekCount + "*" + super.getSeekRate() + " = " + Inputter.util.thousandSeparator(seekCount * super.getSeekRate()) + "ms");
        System.out.println("Seek sequence");
        System.out.println(seek);
        c = Inputter.util.getChar("Do you want save the results ? (y/n): ", "[YyNn]");
        if (c == 'Y' || c == 'y') {
            writeResults("src\\SSTF.txt", seekCount, this.getHeadPos(), seek);
        } else {
            System.out.println("Finished");
        }
    }

    public static void main(String[] args) {
        Algs n = new SSTF();

        n.inputRequests();
        n.Run();
    }
}
