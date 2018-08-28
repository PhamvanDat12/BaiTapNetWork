/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package network;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import static network.Cilent.bis;
import static network.Cilent.socket;

/**
 *
 * @author Admin
 */
public class Sever {

    /**
     * @param args the command line arguments
     */
     static   BufferedWriter bos =null;
     static BufferedReader bis=null;
      static  Socket sc=null;
    public static void main(String[] args) {
        try {
            // TODO code application logic here
            ServerSocket serverSocket= new ServerSocket(9999);
            System.out.println("Dang cho ket noi...");
             sc = serverSocket.accept();
         //Mở cổng,chờ kết nối từ cilent;
         //dừng mọi xử lý của thread và chờ client tới mới làm tiếp;
         //Khi có client kết nối tới thì ltuc trả về 1 socket tương ứng và chạy tiếp
            System.out.println("Ket noi den sever thanh cong");
           // OutputStream out = accept.getOutputStream();   
          // bos= new BufferedOutputStream(sc.getOutputStream());
          new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        bis= new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    } catch (IOException ex) {
                        Logger.getLogger(Sever.class.getName()).log(Level.SEVERE, null, ex);
                    }
            while (true) {                
            String reaLine = null;
                        try {
                            reaLine = bis.readLine();
                        } catch (IOException ex) {
                            Logger.getLogger(Sever.class.getName()).log(Level.SEVERE, null, ex);
                        }
            System.out.println("Cilent:"+reaLine);
            }
                    
                }
            }).start();
          bos=new BufferedWriter(new OutputStreamWriter(sc.getOutputStream()));
            while (true) {                
                System.out.println("Toi:");
            
            String mes=new Scanner(System.in).nextLine();
            bos.write(mes);
            bos.newLine();
            bos.flush();
            }
             
        } catch (IOException ex) {
            Logger.getLogger(Sever.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                bis.close();
                bos.close();
                
            } catch (IOException ex) {
                Logger.getLogger(Sever.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                sc.close();
            } catch (IOException ex) {
                Logger.getLogger(Sever.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
