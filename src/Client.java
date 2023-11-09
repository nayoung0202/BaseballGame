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
                            if (Integer.parseInt(s) < 10 && Integer.parseInt(s) > 0) {
                                dos.writeUTF(s);
                                loop = false;
                            } else {
                                System.out.println("자릿수 틀림!");
                            }
                        }
                        break;
                    case "2":
                        loop = true;
                        m = dis.readUTF();
                        while (loop) {
                            System.out.print(m);
                            String s = sc.nextLine();
                            if (Arrays.stream(s.split("")).distinct().count() == s.length() && Integer.parseInt(dis.readUTF()) == s.length()) {
                                dos.writeUTF(s);
                                loop = false;
                            } else {
                                System.out.println("중복 혹은 자릿수 틀림!");
                            }
                        }
                        break;
                    case "3":
                        System.out.println(dis.readUTF());
                        break;
                    case "4":
                        loop = true;
                        m = dis.readUTF();
                        while (loop) {
                            System.out.print(m);
                            String s = sc.nextLine();
                            if (s.equalsIgnoreCase("Y") || s.equalsIgnoreCase("N")) {
                                dos.writeUTF(s);
                                loop = false;
                            } else {
                                System.out.println("양식 틀림!");
                            }
                        }
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
