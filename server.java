 


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class server {
    public static void main(String[] args){
      


        Scanner scanner = new Scanner(System.in);

        try {
            ServerSocket serverSocket = new ServerSocket(5000);
          Socket  clientSocket = serverSocket.accept();
          PrintWriter out = new PrintWriter(clientSocket.getOutputStream());
          BufferedReader   in = new BufferedReader (new InputStreamReader(clientSocket.getInputStream()));
             
            System.out.println("mrn is online ");
            Thread send = new Thread(new Runnable() {
                String message; 
                @Override  
                public void run() {
                    while(true){
                      
                        message = scanner.nextLine(); 
                        out.println( message);  
                        System.out.println(); 
                        out.flush(); 
                    }
                }
            });
            send.start();

            Thread accept= new Thread(new Runnable() {
                String message ;
                @Override
                public void run() {
                    try {
                        message = in.readLine();
                     
                        while(message !=null){
                            System.out.println("\t\t\t  mrn : " + message);
                            message = in.readLine();
                        }

                         

                        out.close();
                        clientSocket.close();
                        serverSocket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            accept.start();
        } catch (IOException e) {
            
            e.printStackTrace();
        }


    }
}