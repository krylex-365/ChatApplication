/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Common;

import java.io.Serializable;

/**
 *
 * @author User
 */
public class Message implements Serializable {
    public int type;
    public Object obj;

    public Message(int type, Object obj) {
        this.type = type;
        this.obj = obj;
    }
}
