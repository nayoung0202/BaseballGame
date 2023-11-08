import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        Socket socket = new Socket();
        Scanner sc = new Scanner(System.in);
        try{
            socket.connect(new InetSocketAddress("192.168.55.43",50001));
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            DataInputStream dis = new DataInputStream(socket.getInputStream());

            System.out.print("ID >> ");
            dos.writeUTF(sc.nextLine());

            while(true){
                String sysNum = dis.readUTF();

                switch (sysNum){
                    case "1":
                        System.out.print(dis.readUTF());
                        dos.writeUTF(sc.nextLine());
                        break;
                    case "2":
                        System.out.println(dis.readUTF());
                        break;
                    default:
                        return;
                }


            }
        } catch (IOException e) {
            return;
        }
    }
}
