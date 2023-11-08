import jdk.dynalink.beans.StaticClass;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {
    static ServerSocket serverSocket = null;
    static ArrayList<Socket> socketList = new ArrayList<>();
    public static void main(String[] args) {
        try {
            serverSocket = new ServerSocket(50001);
            while(socketList.size() <= 2){
                System.out.println("대기중...");
                Socket socket = serverSocket.accept();

                socketList.add(socket);

                new Thread(() -> {

                }).start();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}