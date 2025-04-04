package Multi_Threaded;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.function.Consumer;

public class Server
{
    public Consumer<Socket> getConsumer(){
        return (clientSocket)->{
            try {
                PrintWriter toClient = new PrintWriter(clientSocket.getOutputStream());
                toClient.println("hello from the Server");
                toClient.close();
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        };
    }
    public static void main(String[] args) {
        int port = 8010;
        Server server = new Server();
        try {
            ServerSocket socket = new ServerSocket(port);
            socket.setSoTimeout(10000);
            System.out.println("Server is listening on port :" + port );
            while(true){
                Socket acceptedSocket = socket.accept();
                Thread thread = new Thread(()-> server.getConsumer().accept(acceptedSocket));
                thread.start();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
