/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package network;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import static network.Sever.bos;
import static network.Sever.sc;

/**
 *
 * @author Admin
 */
public class Cilent {
    
        static BufferedReader bis=null;
        static BufferedWriter bos=null;
      static Socket socket=null;
         public static void main(String[] args) {
        try {
                //để kết nối tới sever chỉ cần new socket
            System.out.println("Dang ket noi toi client...");
             socket= new Socket("localhost", 9999);
            System.out.println("Ket noi thanh cong den sever....");
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        bis= new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    } catch (IOException ex) {
                        Logger.getLogger(Cilent.class.getName()).log(Level.SEVERE, null, ex);
                    }
            while (true) {                
                
            
            String reaLine = null;
                        try {
                            reaLine = bis.readLine();
                        } catch (IOException ex) {
                            Logger.getLogger(Cilent.class.getName()).log(Level.SEVERE, null, ex);
                        }
            System.out.println("Toi:"+reaLine);
            }
                    
                }
            }).start();
           
             bos=new BufferedWriter(new OutputStreamWriter(sc.getOutputStream()));
            while (true) {                
                
            
            String mes=new Scanner(System.in).nextLine();
            bos.write(mes);
            bos.newLine();
            bos.flush();
            }
        } catch (IOException ex) {
            Logger.getLogger(Cilent.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                bos.close();
                bis.close();
            } catch (IOException ex) {
                Logger.getLogger(Cilent.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                socket.close();
            } catch (IOException ex) {
                Logger.getLogger(Cilent.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}

