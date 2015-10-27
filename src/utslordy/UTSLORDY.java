/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utslordy;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author baskoro
 */
public class UTSLORDY {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            byte[] buf = new byte[50];
            int len=0;
            
            Socket socket = new Socket("10.151.34.155", 6666);
            InputStream is;
            OutputStream os;
            
            is = socket.getInputStream();
            os = socket.getOutputStream();
            //end of header
            String strbuf = new String(buf);
            Scanner inputlink = new Scanner(socket.getInputStream());
            Scanner input = new Scanner(System.in);
            PrintWriter output = new PrintWriter(socket.getOutputStream(),true);
            
            String message;
            message=inputlink.nextLine();
            System.out.println(message);
            message=inputlink.nextLine();
            System.out.println(message);
            
            message=input.nextLine();
            os.write(new String("Username:" + message+"\n").getBytes());
            System.out.println("Username:" + message+"\n");
            //os.write("Username:5113100049\n".getBytes());
            message=inputlink.nextLine();
            System.out.println(message);
            //end of phase 1
            message=inputlink.nextLine();
            while(true)
            {
              System.out.println(message);
              //cacah kata
              //message=input.nextLine(); //input
              String[] kata= message.split(" ");
              
              int[] kataInt;
                kataInt = new int[3];
              kataInt[0]=Integer.parseInt(new String(kata[0]));
              kataInt[1]=Integer.parseInt(new String(kata[2]));
              int sum;
              //System.out.println(kataInt[0]+" "+kataInt[1]);
              //end of cacah kata
              //process
              if(kata[1].equals("+"))
                sum=kataInt[0]+kataInt[1];
              else if(kata[1].equals("-"))
                sum=kataInt[0]-kataInt[1];
              else if(kata[1].equals("x"))
                sum=kataInt[0]*kataInt[1];
              else sum=kataInt[0]%kataInt[1];
              message= Integer.toString(sum);
              System.out.println(message);
              //end of process
              os.write(new String("Result:"+message+"\n").getBytes());
              message=inputlink.nextLine();
              System.out.println(message);
              if(message.startsWith("Hash"))
              {
                    message=inputlink.nextLine();
                    System.out.println(message);
                    message=inputlink.nextLine();
                    System.out.println(message);
                    os.write(new String("Hash:"+message+"\n").getBytes());
                    System.out.println("Hash:"+message);
              }
              message=inputlink.nextLine();
              System.out.println(message);
              if(message.startsWith("666")) break;
            }
            //udah nemu hash"
            //message=inputlink.nextLine();
            //System.out.println(message);
            
            //if(message.startsWith("Hash"))
              //{
              //}
           
           
            os.close();
            is.close();
            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(UTSLORDY.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
