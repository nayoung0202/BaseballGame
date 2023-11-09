import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Arrays;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        Socket socket = new Socket();
        Scanner sc = new Scanner(System.in);
        try{
            socket.connect(new InetSocketAddress("192.168.0.141",50001));
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            DataInputStream dis = new DataInputStream(socket.getInputStream());

            System.out.print("ID >> ");
            dos.writeUTF(sc.nextLine());

            while(true){
                String sysNum = dis.readUTF();

                switch (sysNum){
                    case "1":
                        boolean loop = true;
                        String m = dis.readUTF();
                        while (loop) {
                            System.out.print(m);
                            String s = sc.nextLine();
                            if (Arrays.stream(s.split("")).distinct().count() == s.length()) {
                                dos.writeUTF(s);
                            }
                        }
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
