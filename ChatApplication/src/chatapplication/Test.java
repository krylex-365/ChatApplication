/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chatapplication;

import java.util.Scanner;

/**
 *
 * @author User
 */
public class Test extends Thread {
    public Test() {
    }

    @Override
    public void run() {
        Scanner s = new Scanner(System.in);
        String a;
        while (true) {
            a = s.nextLine();
            if (a.equals("stop")) {
                System.out.println("end");
                break;
            } else {
                System.out.println("a = " + a);
            }
        }
    }
}
