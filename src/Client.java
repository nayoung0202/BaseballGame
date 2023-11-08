import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;
import java.util.ArrayList;

public class Client {
    static DataOutputStream dos = null;
    static DataInputStream dis = null;
    public static void main(String[] args) {
        Socket socket = new Socket();

        Scanner sc = new Scanner(System.in);
        try {

            socket.connect(new InetSocketAddress("192.168.0.5", 50001)); // 누구한테 연결할거야??? -> 서버정보가 필요
            System.out.println("야구게임이 시작됩니다. " + socket.getLocalPort());
            dos = new DataOutputStream(socket.getOutputStream());
            dis = new DataInputStream(socket.getInputStream());

            System.out.print("사용자 아이디를 만들어주세요 : ");
            dos.writeUTF(sc.nextLine());

            // 서버로부터 데이터를 읽는 로직
            // 읽는거만 처리하는 스레드를 만들어주자!!!
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