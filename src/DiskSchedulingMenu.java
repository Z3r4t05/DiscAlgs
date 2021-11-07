
import Menu.Menu;
import Algs.*;
import java.util.concurrent.TimeUnit;

public class DiskSchedulingMenu {

    public static void main(String[] args) {
        String[] ops = {
            "(FCFS) First Come First Served",
            "(SSTF) Shortest Seek Time First",
            "(SCAN) Elevator",
            "(CSCAN) Circular Elevator",
            "(LOOK)",
            "(CLOOK) Circular LOOK",
            "Quit"
        };
        String[] ops2 = {
            "Input Requests From Keyboard",
            "Random Requests",
            "Load Requests From File"
        };
        int choice, choice2;
        String src = "src\\requests.txt";
        do {
            choice = Menu.getChoice(ops);
            switch (choice) {
                case 1:
                    Algs n = new FCFS();
                    choice2 = Menu.getChoice(ops2);
                    switch (choice2) {
                        case (1):
                            n.inputRequests();
                            break;
                        case (2):
                            n.randomRequests();
                            break;
                        case (3):
                            n.loadRequests(src);
                            break;
                    }
                    n.Run();
                    break;
                case 2:
                    n = new SSTF();
                    choice2 = Menu.getChoice(ops2);
                    switch (choice2) {
                        case (1):
                            n.inputRequests();
                            break;
                        case (2):
                            n.randomRequests();
                            break;
                        case (3):
                            n.loadRequests(src);
                            break;
                    }
                    n.Run();
                    break;
                case 3:
                    n = new SCAN();
                    choice2 = Menu.getChoice(ops2);
                    switch (choice2) {
                        case (1):
                            n.inputRequests();
                            break;
                        case (2):
                            n.randomRequests();
                            break;
                        case (3):
                            n.loadRequests(src);
                            break;
                    }
                    n.Run();
                    break;
                case 4:
                    n = new CSCAN();
                    choice2 = Menu.getChoice(ops2);
                    switch (choice2) {
                        case (1):
                            n.inputRequests();
                            break;
                        case (2):
                            n.randomRequests();
                            break;
                        case (3):
                            n.loadRequests(src);
                            break;
                    }
                    n.Run();
                    break;
                case 5:
                    n = new LOOK();
                    choice2 = Menu.getChoice(ops2);
                    switch (choice2) {
                        case (1):
                            n.inputRequests();
                            break;
                        case (2):
                            n.randomRequests();
                            break;
                        case (3):
                            n.loadRequests(src);
                            break;
                    }
                    n.Run();
                    break;
                case 6:
                    n = new CLOOK();
                    choice2 = Menu.getChoice(ops2);
                    switch (choice2) {
                        case (1):
                            n.inputRequests();
                            break;
                        case (2):
                            n.randomRequests();
                            break;
                        case (3):
                            n.loadRequests(src);
                            break;
                    }
                    n.Run();
                    break;
                case 7:
                    System.out.println("\nExit!");
                    try {
                        TimeUnit.MILLISECONDS.sleep(500);
                    } catch (InterruptedException e) {
                        System.out.println(e.getMessage());
                    }
                    System.exit(0);

            }
        } while (choice > 0 && choice < ops.length);
    }
}
