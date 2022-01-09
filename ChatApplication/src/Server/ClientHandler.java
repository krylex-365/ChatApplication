/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Server;

import Common.Message;
import Common.Utils;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author User
 */
public class ClientHandler extends Thread {

    String nickname;
    int status = 0;
    ArrayList<String> listName;
    Socket socket;
    ObjectInputStream in;
    ObjectOutputStream out;

    public ClientHandler(Socket socket) throws IOException {
        System.out.println("1");
        this.socket = socket;
        System.out.println("2");
        out = new ObjectOutputStream(socket.getOutputStream());
        System.out.println("4");
        in = new ObjectInputStream(socket.getInputStream());
        System.out.println("3");
    }

    public void Send(int type, Object obj) {
        try {
            out.reset();
            out.writeObject(new Message(type, obj));
        } catch (IOException e) {
            System.out.println("Loi gui data.");
        }
    }

    public void setStatus(int status) {
        this.status = status;
    }
    
    public void initList() {
        listName = new ArrayList<>();
        for (String a : Server.map.keySet()) {
            if (Server.map.get(a).status == 0 && !Server.map.get(a).nickname.equals(nickname)) {
                listName.add(Server.map.get(a).nickname);
            }
        }
    }
    
    public void Finding() {
        if (listName.isEmpty() || listName == null) {
            Send(Utils.WAIT, "Đợi người dùng khác.");
            System.out.println("Empty");
            return;
        }
        for (String s : listName) {
            if (Server.map.containsKey(s) && Server.map.get(s).status==0) {
                //hàm ghép đôi
                setStatus(1);
                Server.map.get(s).setStatus(1);
                Send(Utils.REQUEST, "" + Server.map.get(s).nickname + "");
                Server.map.get(s).Send(Utils.REQUEST, "" + nickname + "");
                System.out.println("Chờ chấp nhận");
                return;
            }
        }
        Send(Utils.WAIT, "Đợi người dùng khác");
        System.out.println("Waiting");
//        if (Server.map)
    }

    @Override
    public void run() {
        try {
            while (true) {
                Message m = (Message) in.readObject();
                switch (m.type) {
                    case Utils.LOGIN: {
                        String name = (String) m.obj;
                        System.out.println(name);
                        if (Server.map.containsKey(name)) {
                            Send(Utils.LOGINFAIL, "Nickname đã tồn tại!");
                        } else {
                            Server.map.put(name, this);
                            nickname = name;
                            initList();
                            Finding();
//                            Send(Utils.LOGIN, "OK");
                        }
                        break;
                    }
                    case Utils.ACCEPT: {
                        break;
                    }
                    case Utils.DENY: {
                        break;
                    }
                }
            }
        } catch (IOException ex) {
            Server.map.remove(nickname);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                in.close();
                out.close();
                socket.close();
            } catch (IOException ex) {
                Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }
}
