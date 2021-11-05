/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Algs;

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
        int seekCount = 0;
        int distance, curTrack;
        char c = Inputter.util.getChar("Do you want to see the calculation (y/n)? ", "[YyNn]");
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
        //To change body of generated methods, choose Tools | Templates.

    }

    @Override
    public void writeResults() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public static void main(String[] args) {
        Algs n = new FCFS(2, 200, 50);
        String filename = "src\\requests.txt";
        if (n.loadRequests(filename)) {
            System.out.println("load success");
        } else 
            System.out.println("error");
        n.Run();
    }
    
}
