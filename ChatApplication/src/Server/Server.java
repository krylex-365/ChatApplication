/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author User
 */
public class Server {
    public static ServerSocket server = null;
//    ArrayList<ClientHandler> users = new ArrayList<>();
    public static HashMap<String, ClientHandler> map = new HashMap<>();
    
    public static void main(String[] args) {
        try {
            server = new ServerSocket(5000);
            System.out.println("Server started...");
            while (true) {
                Socket socket = server.accept();
                System.out.println("Client " + socket.getInetAddress() + " kết nối.");
                ClientHandler user = new ClientHandler(socket);
                System.out.println("OK");
//                users.add(user);
                user.start();
//                user.send(1, "hello");
            }
        } catch (IOException ex) {
            System.out.println(ex);
            for (String s : map.keySet()) {
                map.get(s).close();
            }
//          Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
