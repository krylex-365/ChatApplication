/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Client;

import java.net.Socket;
import java.util.Scanner;
import javax.swing.SwingUtilities;

/**
 *
 * @author User
 */
public class Client {
    public static void main(String[] args) {
        
        SwingUtilities.invokeLater (new Runnable ()
        {
            @Override
            public void run ()
            {
                new StartApp();
                System.out.println("OK");
            }

        });
//        try {
//            Scanner s = new Scanner(System.in);
//            Socket socket = new Socket("localhost", 5000);
//            ServerListener client = new ServerListener(socket);
//            client.start();
//            while (true) {
//                String line = s.nextLine();
//                if (line.equals("stop")) {
//                    System.exit(0);
//                }
//                client.Send(1, line);
//            }
//        } catch (Exception e) {
//            System.out.println(e);
//            System.exit(0);
//        }
    }
}
