/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Algs;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author ADMIN
 */
public abstract class Algs {

    private ArrayList<Integer> requests = new ArrayList<>();
    private int seekRate;
    private final int minCyl = 0;
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
        this.setTotalCylinders(Inputter.util.getintGreater("Enter total cylinders (min 1): ", 1));
        int totalReq = Inputter.util.getintGreater("Enter total number of request: ", 1);
        for (int i = 0; i < totalReq; i++) {
            requests.add(Inputter.util.getintMinMax("Request " + (i + 1) + " (" + minCyl + "-" + maxCyl + "): ", minCyl, maxCyl));
        }
    }

    public void randomRequests() {
        int totalReq = Inputter.util.getintGreater("Enter total number of request: ", 1);
        this.setTotalCylinders(Inputter.util.getintGreater("Enter total cylinders (min 1): ", 1));
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
        requests.forEach((t) -> {
            System.out.print(t + " ");
        });
        System.out.println("");
    }

    /**
     * Read information from text file
     * @param filename
     * @return true if file read successfully
     * @since 05-11-2021
     */
    public boolean loadRequests(String filename) {
        File f = new File(filename);
        if (!f.exists()) {
            return false;
        }
        try {
            try (FileReader fr = new FileReader(f); BufferedReader bf = new BufferedReader(fr)) {
                bf.readLine(); //consume the first line
                int count = 0;
                String line; //line to be read
                if ((line = bf.readLine()) != null) {
                    line = line.trim();
                    if (line.length() > 0) {
                        StringTokenizer stk = new StringTokenizer(line, " ");
                        while (stk.hasMoreTokens()) {
                            this.requests.add(Integer.parseInt(stk.nextToken().trim()));
                            count++;
                        }
                    }
                }
                bf.readLine();
                while ((line = bf.readLine()) != null) {
                    line = line.trim();
                    if (line.length() > 0) {
                        StringTokenizer stk = new StringTokenizer(line, " ");
                        if (stk.hasMoreTokens()) {
                            this.setTotalCylinders(Integer.parseInt(stk.nextToken().trim()));
                        }
                    }
                }         
            }
        } catch (IOException | NumberFormatException e) {
            System.err.println(e.getMessage());
        }
        return true;
    }

    
    
    public abstract void Run();

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
    }
}
