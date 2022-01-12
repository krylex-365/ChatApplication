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
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
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
    Set<String> listDeny;
    ClientHandler thatClient;
    Socket socket;
    ObjectInputStream in;
    ObjectOutputStream out;

    public ClientHandler(Socket socket) throws IOException {
        listDeny = new HashSet<>();
        this.socket = socket;
        out = new ObjectOutputStream(socket.getOutputStream());
        in = new ObjectInputStream(socket.getInputStream());
    }

    public void send(int type, Object obj) {
        try {
            out.reset();
            out.writeObject(new Message(type, obj));
        } catch (IOException e) {
            System.out.println("Loi gui data.");
        }
    }
    
    public void close(){
        try{
            in.close();
            out.close();
            socket.close(); 
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void initList() {
        listName = new ArrayList<>();
        for (String a : Server.map.keySet()) {
            if (Server.map.get(a).status == 0) {
                listName.add(Server.map.get(a).nickname);
            }
        }
    }

    public void updateList(String thatUser) {
        listName = new ArrayList<>();
        for (String a : Server.map.keySet()) {
            if (Server.map.get(a).status == 0 && !Server.map.get(a).nickname.equals(nickname)
                    && !Server.map.get(a).nickname.equals(thatUser)
                    && !listDeny.contains(Server.map.get(a).nickname)) {
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

    public void finding() {
        if (listName.isEmpty() || listName == null) {
            send(Utils.WAIT, "" + nickname + "");
            System.out.println("Empty");
            return;
        }
        Random rand = new Random();
        String randUser = listName.get(rand.nextInt(listName.size()));
//        System.out.println("rand: " + randUser);
//        while () {
//            
//        }
        if (Server.map.containsKey(randUser)
                && Server.map.get(randUser).status == 0) {
            //hàm ghép đôi
            thatClient = Server.map.get(randUser);
            thatClient.thatClient = this;
            setStatus(1);
            thatClient.setStatus(1);
            send(Utils.REQUEST, thatClient.nickname);
            thatClient.send(Utils.REQUEST, nickname);
//                System.out.println("status thatClient: " + thatClient.status);
//                System.out.println("status thatClientMap: " + Server.map.get(s).status);
            System.out.println("Chờ chấp nhận");
            return;
        }
        send(Utils.WAIT, "" + nickname + "");
        System.out.println("Waiting");
    }

    public void sendChat(ClientHandler client, String str) {
        if (thatClient != null) {
            thatClient.send(Utils.RECEIVECHAT, nickname + ": " + str);
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
//                        System.out.println(name);
                        if (Server.map.containsKey(name)) {
                            send(Utils.LOGINFAIL, "Nickname đã tồn tại!");
                        } else {
                            initList();
                            Server.map.put(name, this);
                            nickname = name;
                            finding();
                        }
                        break;
                    }
                    case Utils.ACCEPT: {
                        String thatUser = (String) m.obj;
                        status = 2;
                        if (thatClient.status == 2) {
                            send(Utils.STARTCHAT, thatUser);
                            thatClient.send(Utils.STARTCHAT, nickname);
                        } else {
                            send(Utils.WAITCHAT, thatUser);
                        }
                        break;
                    }
                    case Utils.DENY: {
//                        String thatUser = (String) m.obj;
                        // update list cho user bị deny
//                        setStatusWait(nickname, thatUser);
                        setStatus(0);
                        listDeny.add(thatClient.nickname);
                        thatClient.send(Utils.BEDENIED, "");
//                        Server.map.get(thatUser).send(status, out);
                        // update list cho user deny
//                        updateList(thatUser);
                        updateList(thatClient.nickname);
                        thatClient = null;
//                        send(Utils.DENY, nickname);
                        finding();
                        break;
                    }
                    case Utils.FINDOTHER: {
                        setStatus(0);
                        listDeny.add(thatClient.nickname);
                        updateList(thatClient.nickname);
                        thatClient = null;
                        finding();
                        break;
                    }
                    case Utils.CHAT: {
                        String chat = (String) m.obj + "\n";
                        sendChat(thatClient, chat);
                        break;
                    }
                    case Utils.OUTCHAT: {
                        setStatus(0);
                        updateList(thatClient.nickname);
                        thatClient.send(Utils.BEDENIED, "");
                        thatClient = null;
                        finding();
                        break;
                    }
                }
            }
        } catch (IOException ex) {
            if (thatClient != null) {
                thatClient.send(Utils.BEDENIED, "");
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

    @Override
    public String toString() {
        return "ClientHandler{" + "nickname=" + nickname + ", status=" + status + ", listName=" + listName + ", listDeny=" + listDeny + ", thatClient=" + thatClient + ", socket=" + socket + ", in=" + in + ", out=" + out + '}';
    }
}
