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
            while(!message.startsWith("Hash"))
            {
              System.out.println(message);
              //cacah kata
              message=input.nextLine(); //input
              //masukin ke string message
              os.write(new String("Result:"+message+"\n").getBytes());
              message=inputlink.nextLine();
              System.out.println(message);
              message=inputlink.nextLine();
            }
           
            os.close();
            is.close();
            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(UTSLORDY.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
