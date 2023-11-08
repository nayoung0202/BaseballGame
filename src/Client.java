import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static javax.management.remote.JMXConnectorFactory.connect;

public class Client {
    static DataOutputStream dos = null;
    static DataInputStream dis = null;
    public static void main(String[] args) {
        Socket socket = new Socket();
        Scanner sc = new Scanner(System.in);

        try {
            socket.connect(new InetSocketAddress("192.168.0.121",50001));
            System.out.println("게임 서버에 연결되었습니다.");
            dos = new DataOutputStream(socket.getOutputStream());
            dis = new DataInputStream(socket.getInputStream());

            System.out.print("ID를 입력하세요 : ");
            dos.writeUTF(sc.nextLine());

            Class input = Class.forName("Input");

            new Thread(() -> {
                try {
                    while (true) {
                        String message = dis.readUTF();
                        System.out.println(message);
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }).start();

            while (true) {
                System.out.print(">>");
                dos.writeUTF(sc.nextLine());
                dos.flush();
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                dos.close();
                socket.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
