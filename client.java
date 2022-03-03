

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class client {
    public static void main(String[] args){
         
        final Scanner scanner = new Scanner(System.in);
        try {
            Socket  clientSocket = new Socket("127.0.0.1",5000);
            PrintWriter  out = new PrintWriter(clientSocket.getOutputStream());
            BufferedReader      in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

               
              System.out.println("mr server is online ");
            
            Thread accept = new Thread(new Runnable() {
                String message;
                @Override
                public void run() {
                    try {
                        message = in.readLine();
                        while(message != null){
                            System.out.println("\t\t\t mr server : "+ message);
                            message = in.readLine();
                        }
                        
                        out.close();
                        clientSocket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            accept.start();








            Thread send = new Thread(new Runnable() {
                String message;
                @Override
                public void run() {
                    while(true){
                      
                        message = scanner.nextLine();
                        out.println(message); 
                        System.out.println(); 
                        out.flush();
                    }
                }
            });



            send.start();




    }catch (IOException e){
        System.out.println("mr server is offline ");
      
        }
    }
}