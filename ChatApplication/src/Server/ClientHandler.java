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
    ClientHandler thatClient;
    Socket socket;
    ObjectInputStream in;
    ObjectOutputStream out;

    public ClientHandler(Socket socket) throws IOException {
        this.socket = socket;
        out = new ObjectOutputStream(socket.getOutputStream());
        in = new ObjectInputStream(socket.getInputStream());
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

    public void updateList(String thatUser) {
        listName = new ArrayList<>();
        for (String a : Server.map.keySet()) {
            if (Server.map.get(a).status == 0 && !Server.map.get(a).nickname.equals(nickname)
                    && !Server.map.get(a).nickname.equals(thatUser)) {
                listName.add(Server.map.get(a).nickname);
            }
        }
    }

    public void setStatusWait(String thisUser, String thatUser) {
        if (Server.map.containsKey(thisUser)) {
            Server.map.get(thisUser).setStatus(0);
        }
        if (Server.map.containsKey(thatUser)) {
            Server.map.get(thatUser).setStatus(0);
        }
    }

    public void Finding() {
        if (listName.isEmpty() || listName == null) {
            Send(Utils.WAIT, "" + nickname + "");
            System.out.println("Empty");
            return;
        }
        for (String s : listName) {
            if (Server.map.containsKey(s) && Server.map.get(s).status == 0) {
                //hàm ghép đôi
                thatClient = Server.map.get(s);
                thatClient.thatClient = this;
                setStatus(1);
                thatClient.setStatus(1);
                Send(Utils.REQUEST, "" + thatClient.nickname + "");
                thatClient.Send(Utils.REQUEST, "" + nickname + "");
//                System.out.println("status thatClient: " + thatClient.status);
//                System.out.println("status thatClientMap: " + Server.map.get(s).status);
                System.out.println("Chờ chấp nhận");
                return;
            }
        }
        Send(Utils.WAIT, "" + nickname + "");
        System.out.println("Waiting");
//        if (Server.map)
    }

    public void sendChat(ClientHandler client, String str) {
        if (thatClient != null) {
            thatClient.Send(Utils.RECEIVECHAT, nickname + ": " + str);
        }
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
                        String thatUser = (String) m.obj;
                        status = 2;
                        if (thatClient.status == 2) {
                            Send(Utils.STARTCHAT, thatUser);
                            thatClient.Send(Utils.STARTCHAT, nickname);
                        } else {
                            Send(Utils.WAITCHAT, thatUser);
                        }
                        break;
                    }
                    case Utils.DENY: {
//                        String thatUser = (String) m.obj;
                        // update list cho user bị deny
//                        setStatusWait(nickname, thatUser);
                        setStatus(0);
                        thatClient.Send(Utils.BEDENIED, "");
//                        Server.map.get(thatUser).Send(status, out);
                        // update list cho user deny
//                        updateList(thatUser);
                        thatClient = null;
                        Send(Utils.DENY, nickname);
                        break;
                    }
                    case Utils.FINDOTHER: {
                        setStatus(0);
                        updateList(thatClient.nickname);
                        thatClient = null;
                        Finding();
                        break;
                    }
                    case Utils.CHAT: {
                        String chat = (String) m.obj + "\n";
                        sendChat(thatClient, chat);
                        break;
                    }
                    case Utils.OUTCHAT: {
                        setStatus(0);
                        thatClient.Send(Utils.BEDENIED, "");
                        thatClient = null;
                        Finding();
                        break;
                    }
                }
            }
        } catch (IOException ex) {
            if (thatClient != null) {
                thatClient.Send(Utils.BEDENIED, "");
            }
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
