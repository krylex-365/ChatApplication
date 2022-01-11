/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

//import Client.*;
import Common.Message;
//import Common.UserInfo;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import static java.lang.System.exit;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServerListener extends Thread {
    private Socket socket;
    private InputStream inputStream;
    private ObjectInputStream in;
    private OutputStream outputStream;
    private ObjectOutputStream out;
//        public UserInfo userInfo;
    InReceive receive;

    public ServerListener(Socket socket) throws IOException {
        this.socket = socket;
        inputStream = socket.getInputStream();
        in = new ObjectInputStream(inputStream);
        outputStream = socket.getOutputStream();
        out = new ObjectOutputStream(outputStream);
    }

    public void send(int type, Object obj) {
        try {
            out.reset();
            out.writeObject(new Message(type, obj));
        } catch (IOException e) {
            System.out.println("Loi gui data.");
        }
    }

    @Override
    public void run() {
        try {
            while (true) {
                Object obj = in.readObject();
                if (obj != null && receive != null) {
                    receive.Receive((Message) obj);
                }
            }
        } catch (IOException ex) {
            try {
                in.close();
                out.close();
                socket.close();
            } catch (IOException ex1) {
                System.out.println("Ngat ket noi server");
                //Logger.getLogger(ServerListener.class.getName()).log(Level.SEVERE, null, ex1);
            }
            System.out.println("Ngat ket noi server");
            //Logger.getLogger(ServerListener.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ServerListener.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
