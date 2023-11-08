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
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket();

        Scanner sc = new Scanner(System.in);
        try {

            socket.connect(new InetSocketAddress("192.168.0.5", 50001)); // 누구한테 연결할거야??? -> 서버정보가 필요
            System.out.println("야구게임이 시작됩니다. " + socket.getLocalPort());
            dos = new DataOutputStream(socket.getOutputStream());
            dis = new DataInputStream(socket.getInputStream());

            System.out.print("사용자 아이디를 만들어주세요 : ");
            dos.writeUTF(sc.nextLine());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        while (true){
            switch (dis.readUTF()){
                case "1": // 몇글자


                    break;
                case "2": // 숫자입력

                    break;
                case "3": // 결과

                    break;
                case "4": // 재시작

                    break;
            }
        }

    }
}