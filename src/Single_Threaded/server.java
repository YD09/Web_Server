//package Single_Threaded;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
public class server
{
    public void run(){
        int port = 8010;
        ServerSocket socket = null;
        try{
            socket = new ServerSocket(port);
            socket.setSoTimeout(10000);
        } catch (IOException e) {
            e.printStackTrace();
        }
        while(true){
           try{
               System.out.println("Server is Listening on Port " + port);
               Socket acceptConnection = socket.accept();
               System.out.println("Connection accept from Client"+acceptConnection.getRemoteSocketAddress());
               PrintWriter toClient = new PrintWriter(acceptConnection.getOutputStream());
               BufferedReader fromClient =  new BufferedReader(new InputStreamReader(acceptConnection.getInputStream()));
               toClient.println("Hello from the Server");
               toClient.close();
               fromClient.close();
               acceptConnection.close();
           } catch (IOException e) {
               e.printStackTrace();
           }
        }

    }

    public static void main(String[] args) {
        server server = new server();
        try {
            server.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
