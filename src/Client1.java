import java.io.DataInputStream;
        import java.io.DataOutputStream;
        import java.io.IOException;
        import java.net.InetSocketAddress;
        import java.net.Socket;
        import java.util.Scanner;

public class Client1 {
    public static void main(String[] args) {
        Socket socket = new Socket();
        Scanner sc = new Scanner(System.in);
        try{
            socket.connect(new InetSocketAddress("192.168.0.141",50001));
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            DataInputStream dis = new DataInputStream(socket.getInputStream());

            System.out.println("ID");
            dos.writeUTF(sc.nextLine());

            while(true){
                String sysNum = dis.readUTF();
                System.out.println(sysNum);
                switch (sysNum){
                    case "1":
                        System.out.println(dis.readUTF());
                        dos.writeUTF(sc.nextLine());
                        break;
                    case "2":
                        System.out.println(dis.readUTF());
                        dos.writeUTF(sc.nextLine());
                        break;
                    case "3":
                        System.out.println(dis.readUTF());
                        break;
                    case "4":
                        System.out.println(dis.readUTF());
                        dos.writeUTF(sc.nextLine());
                    default:
                        return;
                }


            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
