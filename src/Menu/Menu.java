/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Menu;

import java.util.Scanner;

/**
 *
 * @author ADMIN
 */
public class Menu {
    public static int getChoice (String[] ops) {
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < ops.length; i++) {
            System.out.println("\n" + (i + 1) + "-" + ops[i]);
        }
        System.out.println("\n Choose 1.." + ops.length + ": ");
        return Integer.parseInt(sc.nextLine());
    }
}
