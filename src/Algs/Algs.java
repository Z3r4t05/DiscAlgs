/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Algs;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author ADMIN
 */
public class Algs {

    private ArrayList<Integer> requests = new ArrayList<>();
    private int seekRate;
    private int minCyl = 0;
    private int totalCylinders;
    private int maxCyl;
    private int headPos;

    public Algs() {
    }

    public Algs(int seekRate, int totalCylinders, int headPos) {
        this.seekRate = seekRate;
        this.totalCylinders = totalCylinders;
        this.maxCyl = totalCylinders - 1;
        this.headPos = headPos;
    }

    public void inputRequests() {
        int totalReq = Inputter.util.getintGreater("Enter total number of request: ", 1);
        for (int i = 0; i < totalReq; i++) {
            requests.add(Inputter.util.getintMinMax("Request " + (i + 1) + " (" + minCyl + "-" + maxCyl + "): ", minCyl, maxCyl));
        }
    }

    public void randomRequests() {
        int totalReq = Inputter.util.getintGreater("Enter total number of request: ", 1);
        System.out.println("Generating " + totalReq + " request(s) with value from " + this.minCyl + " to " + this.maxCyl);
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }

        for (int i = 0; i < totalReq; i++) {
            int randInt = (int) ((Math.random()) * totalCylinders);
            this.requests.add(randInt);
        }
        System.out.println("Generate successfully!");
        for (Integer t : requests) {
            System.out.print(t + " ");
        }
        System.out.println("");
    }

    public void loadRequests() {

    }

    public void writeResults() {

    }

    public void FCFS() {
        int head = Inputter.util.getintMinMax("Enter current position of the head: ", minCyl, maxCyl);
        int seekCount = 0;
        int distance, curTrack;
        char c = Inputter.util.getChar("Do you want to see the calculation (y/n)? ", "[YyNn]");
        if (c == 'Y' || c == 'y') {
            for (int i = 0; i < this.requests.size(); i++) {
                System.out.print("(");
                curTrack = requests.get(i);
                if (curTrack > head) {
                    System.out.print(curTrack + " - " + head);
                } else {
                    System.out.print(head + " - " + curTrack);
                }
                distance = Math.abs(curTrack - head); //calculate distance
                seekCount += distance; //increase total count
                head = curTrack; //newPos  
                System.out.print(")");
                if (i < this.requests.size() - 1) {
                    System.out.print(" + ");
                }
                if ((i != 0) && (((i + 1) % 6) == 0)) {
                    System.out.println("");
                }
            }
            System.out.println(" = " + seekCount);
        } else {
            for (int i = 0; i < this.requests.size() - 1; i++) {
                curTrack = this.requests.get(i);
                distance = Math.abs(curTrack - headPos); //calculate distance
                seekCount += distance; //increase total count
                headPos = curTrack; //newPos     
            }
        }
        System.out.println("Total head movement = " + Inputter.util.thousandSeparator(seekCount));
        System.out.println("Total seek time = " + Inputter.util.thousandSeparator(seekCount * seekRate));
    }

    public int getMinCyl() {
        return minCyl;
    }

    public int getTotalCylinders() {
        return totalCylinders;
    }

    public void setTotalCylinders(int totalCylinders) {
        this.totalCylinders = totalCylinders;
        this.maxCyl = this.totalCylinders - 1;
    }

    public int getMaxCyl() {
        return maxCyl;
    }

    public ArrayList<Integer> getRequests() {
        return requests;
    }

    public void setRequests(ArrayList<Integer> requests) {
        this.requests = requests;
    }

    public int getSeekRate() {
        return seekRate;
    }

    public void setSeekRate(int seekRate) {
        this.seekRate = seekRate;
    }

    public int getHeadPos() {
        return headPos;
    }

    public void setHeadPos(int headPos) {
        this.headPos = headPos;
    }

    public static void main(String[] args) {
        Algs n = new Algs(0, 200, 55);
        n.randomRequests();
        n.FCFS();
    }
}
